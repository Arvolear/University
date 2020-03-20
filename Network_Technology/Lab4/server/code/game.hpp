#pragma once

#include "server.hpp"
#include "party.hpp"

#include <map>
#include <thread>
#include <chrono>

using namespace std;

class Game
{
	private:
		int PORT = 5040;

		Server* server;

		map < int, Party* > parties;

		void check();
		void react();

		void processNewConnections();
		void processMessages();
		void processOldConnections();

	public:
		Game();
		
		void play();

		~Game();
};
