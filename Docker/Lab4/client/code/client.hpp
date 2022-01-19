#pragma once

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>

#include <cstring>
#include <iostream>
#include <deque>
#include <vector>
#include <mutex>
#include <string>

using namespace std;

class Client
{
	private:
		struct Message
		{	
			bool fine;
			int totalLength;
			string message;

			Message()
			{
				this->fine = false;
				this->totalLength = -1;
				this->message = "";
			}
		};

		int inputBufferSize, outputBufferSize;

		int sock;
		sockaddr_in servAddr;

		deque < Message > messages;

		mutex serverMutex;

		void log(const string &msg);
		void getMessage();

	public:
		Client(string serverIP, int serverPort, int inputufferSize = 100, int outputBufferSize = 100);

		void sendMessage(const string &message);
		
		void checkActivity();

		vector < string > getServerMessages();

		~Client();
};
