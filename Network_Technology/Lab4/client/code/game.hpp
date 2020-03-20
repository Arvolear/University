#pragma once

#include "client.hpp"

#include <thread>
#include <chrono>

class Game
{
	private:
		string IP = "127.0.0.1";
		int PORT = 5040;

		Client *client;

		void check();
		void react();

		void displayMessage(const string &message);

	public:
		Game();

		void play();

		~Game();
};
