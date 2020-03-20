#pragma once

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>

#include <iostream>
#include <deque>
#include <mutex>
#include <string>

using namespace std;

class Client
{
	private:
		int inputBufferSize, outputBufferSize;

		int sock;
		sockaddr_in servAddr;

		deque < string > messages;

		mutex serverMutex;

		void log(const string &msg);
		string getMessage();

	public:
		Client(string serverIP, int serverPort, int inputufferSize = 100, int outputBufferSize = 100);

		void sendMessage(const string &message);
		
		void checkActivity();

		string getServerMessage();

		~Client();
};
