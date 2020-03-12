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
		int inputBufferSize, outputBufferSize;

		int sock;
		sockaddr_in servAddr;

		void displayMessage(const string &msg);
		string getMessage();

	public:
		Client(string serverIP, int serverPort, int inputufferSize = 1000, int outputBufferSize = 1000);

		void sendMessage(const string &message);
		void receiveMessage();

		~Client();
};
