#include "client.hpp"

void Client::log(const string &msg)
{
	cout << "Log :: " << inet_ntoa(servAddr.sin_addr) << ":" << ntohs(servAddr.sin_port) << endl;
	cout << msg << endl;
}

Client::Client(string serverIP, int serverPort, int inputBufferSize, int outputBufferSize)
{
	this->inputBufferSize = inputBufferSize;
	this->outputBufferSize = outputBufferSize;

	sock = socket(AF_INET, SOCK_STREAM, 0);

	if (sock < 0)
	{
		throw(runtime_error("socket"));
	}

	servAddr.sin_family = AF_INET; 
	servAddr.sin_addr.s_addr = inet_addr(serverIP.data()); 
	servAddr.sin_port = htons(serverPort); 

	if (connect(sock, (sockaddr*)&servAddr, sizeof(servAddr)) < 0)
	{
		throw(runtime_error("connect"));
	}

	log("Successful connection");
}

void Client::sendMessage(const string &message)
{
	if (message == "")
	{
		return;
	}

	string msg = to_string(message.length()) + '\n' + message;	
	string tmp = "";

	for (size_t i = 0; i < msg.length(); i++)
	{
		tmp += msg[i];

		if ((i + 1) % outputBufferSize == 0 || i == msg.length() - 1)
		{
			send(sock, tmp.data(), tmp.length(), 0);
			tmp = "";
		}
	}
}

void Client::getMessage()
{
	Message message;
	Message tail;
		
	char buffer[inputBufferSize];
	int bytesRead = 0;
	int bufferInd = 0;
	
	string msgLen = "";

	bool skip = false;

	if (!messages.empty() && !messages[messages.size() - 1].fine)
	{
		string localBuffer = messages[messages.size() - 1].message;
		bytesRead = localBuffer.size();

		for (int i = 0; i < bytesRead; i++)
		{
			buffer[i] = localBuffer[i];
		}

		for (int i = 0; i < bytesRead; i++)
		{
			if (buffer[i] == '\n')
			{
				bufferInd = i;
				break;
			}
			
			msgLen += buffer[i];
		}
		
		/* MSG has length */
		if (bufferInd)
		{
			skip = true;		
		}
	}
	
	if (!skip)
	{
		/* get MSG len */
		while (true)
		{
			memset(buffer, 0, inputBufferSize);
			bytesRead = recv(sock, buffer, inputBufferSize, 0);

			if (bytesRead == 0)
			{
				throw(runtime_error("disconnect"));
			}

			for (int i = 0; i < bytesRead; i++)
			{
				if (buffer[i] == '\n')
				{
					bufferInd = i;
					break;
				}

				msgLen += buffer[i];
			}

			if (bufferInd)
			{
				break;
			}
		}
	}

	bufferInd++;

	message.totalLength = stoi(msgLen);

	for (int i = 0; i < message.totalLength; i++, bufferInd++)
	{
		if (bufferInd >= bytesRead)
		{
			break;
		}

		message.message += buffer[bufferInd];
	}

	if ((int)message.message.size() == message.totalLength)
	{
		message.fine = true;

		/* copy tail if MSG is filled */
		for (int i = bufferInd; i < bytesRead; i++)
		{
			tail.message += buffer[i];
		}
	}
	else
	{
		while ((int)message.message.size() < message.totalLength)
		{
			memset(buffer, 0, inputBufferSize);
			bufferInd = 0;
			
			bytesRead = recv(sock, buffer, inputBufferSize, 0);

			if (bytesRead == 0)
			{
				throw(runtime_error("disconnect"));
			}

			for (int i = 0; i < bytesRead; i++)
			{
				message.message += buffer[i];
				
				if ((int)message.message.size() == message.totalLength)
				{
					bufferInd = i + 1;
					message.fine = true;

					break;
				}
			}
				
			/* copy tail if MSG is filled */
			if (message.fine)
			{
				for (int i = bufferInd; i < bytesRead; i++)
				{
					tail.message += buffer[i];
				}
			}	
		}
	}
	
	unique_lock < mutex > serverLk(serverMutex);

	if (!messages.empty() && !messages[messages.size() - 1].fine)
	{
		messages.pop_back();
	}

	messages.push_back(message);
	messages.push_back(tail);
}

void Client::checkActivity()
{
	getMessage();
}

vector < string > Client::getServerMessages()
{
	vector < string > res;

	unique_lock < mutex > serverLk(serverMutex);

	for (auto &message : messages)
	{
		if (message.fine)
		{
			res.push_back(message.message);
			messages.pop_front();
		}
	}

	return res;
}

Client::~Client()
{
	close(sock);
}
