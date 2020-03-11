#pragma once

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>

#include <iostream>
#include <string>

using namespace std;

class Client
{
	private:
		int bufferSize;

		int sock;
		sockaddr_in servAddr;

		void displayMessage(const string &msg);
		string getMessage();

	public:
		Client(string serverIP, int serverPort, int bufferSize = 1000);

		void sendMessage(const string &message);
		void receiveMessage();

		~Client();
};
