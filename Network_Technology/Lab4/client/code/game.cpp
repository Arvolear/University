#include "game.hpp"

Game::Game()
{
	client = new Client(IP, PORT);
}

void Game::check()
{
	while (true)
	{
		this_thread::sleep_for(chrono::milliseconds(100));

		try
		{
			client->checkActivity();

			string message = client->getServerMessage();

			if (message != "")
			{
				displayMessage(message);
			}
		}
		catch (exception &ex)
		{
			displayMessage("You were disconnected from the server");
			exit(0);
		}
	}
}

void Game::react()
{
	while (true)
	{
		this_thread::sleep_for(chrono::milliseconds(100));

		string ans = "";

		getline(cin, ans);

		client->sendMessage(ans);
	}
}

void Game::displayMessage(const string &message)
{
	cout << endl << message << endl;
}

void Game::play()
{
	thread checkThread(&Game::check, this);
	thread reactThread(&Game::react, this);

	checkThread.join();
	reactThread.join();
}

Game::~Game()
{
	delete client;
}
