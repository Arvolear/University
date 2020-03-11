#pragma once

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>

#include <iostream>
#include <string>
#include <algorithm>
#include <vector>

using namespace std;

class Server
{
	private:
		int bufferSize;

		int masterSocket;
		sockaddr_in servAddr;

		fd_set readfds;
		timeval timeout;

		vector < int > clients;
		vector < sockaddr_in > clientsInfo; 

		string getMessage(int clientSocket, int id);
		void displayMessage(const string &msg, int id);
		void sendMessage(int id, const string &message);

		void disconnect(int id);
		
		void checkNewConnections();
		void answer();

	public:
		Server(int port, int bufferSize = 100);

		void checkActivity(int timeToWait = 200);

		~Server();
};
