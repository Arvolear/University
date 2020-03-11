#include "server.hpp"

Server::Server(int port, int bufferSize)
{
	this->bufferSize = bufferSize;

	masterSocket = socket(AF_INET, SOCK_STREAM, 0);

	if (masterSocket < 0)
	{
		throw(runtime_error("masterSocket"));
	}

	servAddr.sin_family = AF_INET;
	servAddr.sin_addr.s_addr = htonl(INADDR_ANY);
	servAddr.sin_port = htons(port);

	if (bind(masterSocket, (sockaddr*)&servAddr, sizeof(servAddr)) < 0)
	{
		throw(runtime_error("bind"));
	}

	if (listen(masterSocket, 5) < 0)
	{
		throw(runtime_error("listen"));
	}
}

void Server::checkNewConnections()
{
	if (FD_ISSET(masterSocket, &readfds))
	{
		sockaddr_in cliAddr;
		socklen_t cliLen = sizeof(cliAddr);

		int newClient = accept(masterSocket, (sockaddr*)&cliAddr, &cliLen);

		if (newClient < 0)
		{
			throw(runtime_error("accept"));
		}

		int emptyInd = -1;

		for (size_t i = 0; i < clients.size(); i++)
		{
			if (clients[i] == newClient)
			{
				return;
			}

			if (clients[i] == 0)
			{
				emptyInd = i;
			}
		}

		cout << "New client connected: " << inet_ntoa(cliAddr.sin_addr) << ":" << ntohs(cliAddr.sin_port) << endl;

		if (emptyInd == -1)
		{
			clients.push_back(newClient);
			clientsInfo.push_back(cliAddr);
		}
		else
		{
			clients[emptyInd] = newClient;
			clientsInfo[emptyInd] = cliAddr;
		}
	}
}

void Server::disconnect(int id)
{
	cout << "Client disconnected: " << inet_ntoa(clientsInfo[id].sin_addr) << ":" << ntohs(clientsInfo[id].sin_port) << endl;

	close(clients[id]);
	clients[id] = 0;
}

string Server::getMessage(int clientSocket, int id)
{
	string msgLen = "";
	int msgLength = 0;

	string message = "";

	while (true)
	{
		bool esc = false;
		char buffer[bufferSize];
		char* pt = buffer;

		int bytesRead = recv(clientSocket, buffer, bufferSize, 0);

		if (bytesRead == 0)
		{
			disconnect(id);
			throw(runtime_error("disconnect"));
		}

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
		char buffer[bufferSize];
		char* pt = buffer;

		int bytesRead = recv(clientSocket, buffer, bufferSize, 0);

		if (bytesRead == 0)
		{
			disconnect(id);
			throw(runtime_error("disconnect"));
		}

		for (; pt <= &buffer[bytesRead - 1]; pt++)
		{
			message += (*pt);
		}
	}

	return message;
}

void Server::displayMessage(const string &msg, int id)
{
	cout << "Message from: " << inet_ntoa(clientsInfo[id].sin_addr) << ":" << ntohs(clientsInfo[id].sin_port) << endl;
	cout << msg << endl;
}

void Server::sendMessage(int id, const string &message)
{		
	string msg = to_string(message.length()) + '\n' + message;

	send(clients[id], msg.data(), msg.length(), 0);
}

void Server::answer()
{
	for (size_t i = 0; i < clients.size(); i++)
	{
		if (FD_ISSET(clients[i], &readfds))
		{
			string message = "";

			try
			{
				message = getMessage(clients[i], i);
				displayMessage(message, i);
			}
			catch (exception &ex) 
			{
			}

			sendMessage(i, message);
		}
	}
}

void Server::checkActivity(int timeToWait)
{
	FD_ZERO(&readfds);
	FD_SET(masterSocket, &readfds);

	int maxSocket = masterSocket;

	for (size_t i = 0; i < clients.size(); i++)
	{
		if (clients[i] > 0)
		{
			FD_SET(clients[i], &readfds);
		}

		maxSocket = max(maxSocket, clients[i]);	
	}

	timeout.tv_sec = 0;
	timeout.tv_usec = timeToWait;

	int activity = select(maxSocket + 1, &readfds, NULL, NULL, &timeout);

	if (activity < 0)
	{
		throw(runtime_error("select"));
	}

	if (activity > 0)
	{
		checkNewConnections();
		answer();
	}
}

Server::~Server()
{
	for (size_t i = 0; i < clients.size(); i++)
	{
		if (clients[i] > 0)
		{
			close(clients[i]);
		}
	}

	close(masterSocket);
}
