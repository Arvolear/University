#include "server.hpp"

int Server::clientID = 0;

void Server::log(const string &msg, sockaddr_in info)
{
	unique_lock < mutex > logLk(writeMutex);

	cout << "Log :: " << inet_ntoa(info.sin_addr) << ":" << ntohs(info.sin_port) << endl;
	cout << msg << endl;
}

Server::Server(int port, int inputBufferSize, int outputBufferSize)
{
	this->inputBufferSize = inputBufferSize;
	this->outputBufferSize = outputBufferSize;

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

	if (listen(masterSocket, MAX_CLIENTS_QUEUE) < 0)
	{
		throw(runtime_error("listen"));
	}
}

bool Server::isCollision(int newSock)
{
	for (auto& i : clients)
	{
		if (i.second.sock == newSock)
		{
			return true;
		}
	}

	return false;
}

void Server::checkNewConnections()
{
	if (FD_ISSET(masterSocket, &readfds))
	{
		sockaddr_in cliAddr;
		socklen_t cliLen = sizeof(cliAddr);

		int newSock = accept(masterSocket, (sockaddr*)&cliAddr, &cliLen);

		if (newSock < 0)
		{
			throw(runtime_error("accept"));
		}

		if (isCollision(newSock))
		{
			return;
		}

		log("New client connected", cliAddr);
		
		unique_lock < mutex > clientsLk(clientsMutex);

		Client client(clientID, newSock, cliAddr, ClientType::NEW);
		clients.insert({client.id, client});

		++clientID;
	}
}

void Server::markDisconnect(Client client)
{
	unique_lock < mutex > clientsLk(clientsMutex);
	
	auto it = clients.find(client.id);

	if (it == clients.end())
	{
		return;
	}

	it->second.type = ClientType::OLD;
	it->second.messages.clear();

	log("Client marked", it->second.info);
}

void Server::getMessage(Client client)
{
	int clientSocket = client.sock;

	Client::Message clientMessage;
	Client::Message clientTail;
		
	char buffer[inputBufferSize];
	int bytesRead = 0;
	int bufferInd = 0;
	
	string msgLen = "";

	bool skip = false;

	/* client has incomplete MSG */
	if (!client.messages.empty() && !client.messages[client.messages.size() - 1].fine)
	{
		string localBuffer = client.messages[client.messages.size() - 1].message;
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
			bytesRead = recv(clientSocket, buffer, inputBufferSize, 0);

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

	clientMessage.totalLength = stoi(msgLen);

	for (int i = 0; i < clientMessage.totalLength; i++, bufferInd++)
	{
		if (bufferInd >= bytesRead)
		{
			break;
		}

		clientMessage.message += buffer[bufferInd];
	}

	if ((int)clientMessage.message.size() == clientMessage.totalLength)
	{
		clientMessage.fine = true;

		/* copy tail if MSG is filled */
		for (int i = bufferInd; i < bytesRead; i++)
		{
			clientTail.message += buffer[i];
		}
	}
	else
	{
		while ((int)clientMessage.message.size() < clientMessage.totalLength)
		{
			memset(buffer, 0, inputBufferSize);
			bufferInd = 0;

			bytesRead = recv(clientSocket, buffer, inputBufferSize, 0);

			if (bytesRead == 0)
			{
				throw(runtime_error("disconnect"));
			}

			for (int i = 0; i < bytesRead; i++)
			{
				if ((int)clientMessage.message.size() == clientMessage.totalLength)
				{
					bufferInd = i;
					break;
				}

				clientMessage.message += buffer[i];
			}

			/* copy tail if MSG is filled */
			if ((int)clientMessage.message.size() == clientMessage.totalLength)
			{
				clientMessage.fine = true;
				bufferInd = bytesRead;

				for (int i = bufferInd; i < bytesRead; i++)
				{
					clientTail.message += buffer[i];
				}
			}	
		}
	}
	
	log(clientMessage.message, client.info);

	unique_lock < mutex > clientsLk(clientsMutex);

	auto it = clients.find(client.id);

	if (it == clients.end())
	{
		return;
	}

	if (!client.messages.empty() && !client.messages[client.messages.size() - 1].fine)
	{
		it->second.messages.pop_back();
	}

	it->second.messages.push_back(clientMessage);
	it->second.messages.push_back(clientTail);
			
	it->second.inProcess = false;
}

void Server::checkClient(Client client)
{
	string message = "";

	try
	{
		getMessage(client);
	}
	catch (exception &ex) 
	{
		markDisconnect(client);
	}
}

void Server::receiveMessages()
{
	vector < thread > clientThreads;

	unique_lock < mutex > clientsLk(clientsMutex);

	for (auto &clientIt : clients)
	{
		if (FD_ISSET(clientIt.second.sock, &readfds))
		{
			clientIt.second.inProcess = true;
			clientThreads.push_back(thread(&Server::checkClient, this, clientIt.second));
		}
	}

	clientsLk.unlock();

	for (size_t i = 0; i < clientThreads.size(); i++)
	{
		clientThreads[i].detach();
	}
}

void Server::checkActivity(int timeToWait)
{
	FD_ZERO(&readfds);
	FD_SET(masterSocket, &readfds);

	int maxSocket = masterSocket;

	unique_lock < mutex > clientsLk(clientsMutex);

	for (auto &clientIt : clients)
	{
		if (!clientIt.second.inProcess && clientIt.second.type == ClientType::NORMAL)
		{
			FD_SET(clientIt.second.sock, &readfds);
			maxSocket = max(maxSocket, clientIt.second.sock);
		}
	}

	clientsLk.unlock();

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
		receiveMessages();
	}
}

void Server::sendMessage(const Client &client, const string &message)
{	
	if (message == "" || client.type != ClientType::NORMAL)
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
			//TODO check send

			send(client.sock, tmp.data(), tmp.length(), 0);
			tmp = "";
		}
	}
}

void Server::disconnect(const Client &client)
{
	auto it = clients.find(client.id);

	if (it != clients.end())
	{
		close(client.sock);
		clients.erase(it);

		log("Client disconnected", client.info);
	}
}

void Server::approve(const Client &client)
{
	auto it = clients.find(client.id);

	if (it != clients.end())
	{
		it->second.type = ClientType::NORMAL;

		log("Client approved", client.info);
	}	
}

map < int, vector < string > > Server::getClientsMessages()
{
	map < int, vector < string > > res;

	unique_lock < mutex > clientsLk(clientsMutex);

	for (auto &clientIt : clients)
	{
		if (clientIt.second.type == ClientType::NORMAL)
		{
			vector < string > messages;

			for (auto &message : clientIt.second.messages)
			{
				if (message.fine)
				{
					messages.push_back(message.message);
					clientIt.second.messages.pop_front();
				}
			}
			
			if (!messages.empty())
			{
				res.insert({clientIt.first, messages});
			}
		}
	}

	return res;
}

Client Server::getClient(int id)
{
	unique_lock < mutex > clientsLk(clientsMutex);

	auto it = clients.find(id);

	if (it == clients.end())
	{
		throw(runtime_error("client not found"));
	}

	return it->second;
}

map < int, Client > Server::getClients()
{
	return clients;
}

map < int, Client > Server::getNewClients()
{
	map < int, Client > res;

	unique_lock < mutex > clientsLk(clientsMutex);

	for (auto &i : clients)
	{
		if (i.second.type == ClientType::NEW)
		{
			res.insert({i.first, i.second});
		}
	}

	return res;	
}

map < int, Client > Server::getOldClients()
{
	map < int, Client > res;

	unique_lock < mutex > clientsLk(clientsMutex);

	for (auto &i : clients)
	{
		if (i.second.type == ClientType::OLD)
		{
			res.insert({i.first, i.second});
		}
	}

	return res;	
}

void Server::updateClientParty(int id, int partyIndex)
{
	unique_lock < mutex > clientsLk(clientsMutex);

	auto it = clients.find(id);

	if (it == clients.end())
	{
		throw(runtime_error("client not found"));
	}

	it->second.setParty(partyIndex);
}

void Server::updateClientType(int id, ClientType type)
{
	unique_lock < mutex > clientsLk(clientsMutex);

	auto it = clients.find(id);

	if (it == clients.end())
	{
		throw(runtime_error("client not found"));
	}

	it->second.setType(ClientType::OLD);
}

void Server::approveClients(map < int, Client > news)
{
	unique_lock < mutex > clientsLk(clientsMutex);

	for (auto &i : news)
	{
		approve(i.second);
	}	
}

void Server::approveClient(Client client)
{
	unique_lock < mutex > clientsLk(clientsMutex);

	approve(client);
}

void Server::disconnectClients(map < int, Client > olds)
{
	unique_lock < mutex > clientsLk(clientsMutex);

	for (auto &i : olds)
	{
		disconnect(i.second);
	}
}

void Server::disconnectClient(Client client)
{
	unique_lock < mutex > clientsLk(clientsMutex);

	disconnect(client);
}

Server::~Server()
{
	for (auto &client : clients)
	{
		close(client.second.sock);
	}

	close(masterSocket);
}
