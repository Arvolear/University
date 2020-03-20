#pragma once

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>

#include <iostream>
#include <string>
#include <vector>
#include <deque>
#include <map>
#include <thread>
#include <mutex>

using namespace std;

class Server;

enum class ClientType
{
	NEW,
	NORMAL,
	OLD,
	NONE
};

class Client
{
	private:
		int id;
		int sock;

		sockaddr_in info;
		ClientType type;

		deque < string > messages;

		int partyIndex;

	public:
		friend Server;

		Client()
		{
			this->id = -1;
			this->sock = -1;
			this->type = ClientType::NONE;

			this->partyIndex = -1;
		}

		Client(int id, int sock, sockaddr_in info, ClientType type)
		{
			this->id = id;
			this->sock = sock;
			this->info = info;
			this->type = type;

			this->partyIndex = -1;
		}

		void setParty(int partyIndex)
		{
			this->partyIndex = partyIndex;
		}

		int getID() const
		{
			return id;
		}

		int getParty() const
		{
			return partyIndex;
		}
};

class Server
{
	private:
		static int clientID;

		int MAX_CLIENTS_QUEUE = 5;
		
		int inputBufferSize, outputBufferSize;

		int masterSocket;
		sockaddr_in servAddr;

		fd_set readfds;
		timeval timeout;

		map < int, Client > clients;

		mutex writeMutex;
		mutex clientsMutex;

		void log(const string &msg, sockaddr_in info);

		string getMessage(Client client);

		void markDisconnect(Client client);
	
		bool isCollision(int newSock);
		void checkNewConnections();
		void checkClient(Client client);
		void receiveMessages();
		
		void disconnect(const Client &client);
		void approve(const Client &client);

	public:
		Server(int port, int inputBufferSize = 100, int outputBufferSize = 100);

		void checkActivity(int timeToWait = 200);
		
		void sendMessage(const Client &client, const string &message);

		map < int, string > getClientsMessages();
		Client getClient(int id);
		map < int, Client > getClients();
		map < int, Client > getNewClients();
		map < int, Client > getOldClients();

		void updateClientParty(int id, int partyIndex);

		void approveClients(map < int, Client > news);
		void disconnectClients(map < int, Client > olds);

		void approveClient(Client client);
		void disconnectClient(Client client);

		~Server();
};
