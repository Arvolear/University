#include "client.hpp"

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

	cout << "Successful connection to: " << inet_ntoa(servAddr.sin_addr) << ":" << ntohs(servAddr.sin_port) << endl;
}

void Client::sendMessage(const string &message)
{
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

string Client::getMessage()
{
	string msgLen = "";
	int msgLength = 0;

	string message = "";

	while (true)
	{
		bool esc = false;
		char buffer[inputBufferSize];
		char* pt = buffer;

		int bytesRead = recv(sock, buffer, inputBufferSize, 0);

		for (; pt <= &buffer[bytesRead - 1]; pt++)
		{
			if ((*pt) == '\n')
			{
				esc = true;
				break;
			}

			msgLen += (*pt);
		}

		pt++;

		for (; pt <= &buffer[bytesRead - 1]; pt++)
		{
			message += (*pt);
		}

		if (esc)
		{
			break;
		}
	}

	msgLength = stoi(msgLen);

	while ((int)message.length() < msgLength)
	{
		char buffer[inputBufferSize];
		char* pt = buffer;

		int bytesRead = recv(sock, buffer, inputBufferSize, 0);
		
		for (; pt <= &buffer[bytesRead - 1]; pt++)
		{
			message += (*pt);
		}
	}

	return message;	
}

void Client::displayMessage(const string &msg)
{
	cout << "Received message: " << inet_ntoa(servAddr.sin_addr) << ":" << ntohs(servAddr.sin_port) << endl;
	cout << msg << endl;
}

void Client::receiveMessage()
{
	string msg = getMessage();
	displayMessage(msg);
}

Client::~Client()
{
	close(sock);
}
