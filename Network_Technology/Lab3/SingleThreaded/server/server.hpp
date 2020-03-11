#pragma once

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>

#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Server
{
	private:
		int bufferSize;

		int masterSocket;
		sockaddr_in servAddr;

		vector < int > clients;
		vector < sockaddr_in > clientsInfo; 

		string getMessage(int clientSocket, int id);
		void displayMessage(const string &msg, int id);
		void sendMessage(int id, const string &message);

		void disconnect(int id);

	public:
		Server(int port, int bufferSize = 100);

		void checkNewConnections();
		void answer();

		~Server();
};
