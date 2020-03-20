#include "game.hpp"

Game::Game()
{
	server = new Server(PORT);
}

void Game::check()
{
	while (true)
	{
		this_thread::sleep_for(chrono::milliseconds(100));

		server->checkActivity();
	}	
}

void Game::processNewConnections()
{
	map < int, Client > newClients = server->getNewClients();

	for (auto &i : newClients)
	{
		bool foundEmpty = false;

		for (auto &j : parties)
		{
			if (!j.second->isFull())
			{
				foundEmpty = true;

				j.second->addPlayer(i.first);
				server->updateClientParty(i.first, j.first);

				break;
			}
		}

		if (!foundEmpty)
		{
			Party *party = new Party();

			party->addPlayer(i.first);
			server->updateClientParty(i.first, party->getIndex());

			parties.insert({party->getIndex(), party});
		}
	}
		
	server->approveClients(newClients);
}

void Game::processMessages()
{
	map < int, string > clientMessages = server->getClientsMessages();

	for (auto &i : clientMessages)
	{
		Client client = server->getClient(i.first);
		
		auto it = parties.find(client.getParty());

		if (it != parties.end())
		{
			it->second->receiveResponse(i.first, i.second);
		}
	}

	for (auto &i : parties)
	{
		map < int, string > messages = i.second->getMessages();

		for (auto &j : messages)
		{
			server->sendMessage(server->getClient(j.first), j.second);
		}
	}
}

void Game::processOldConnections()
{
	map < int, Client > oldClients = server->getOldClients();
	map < int, Client > toDisconnect;

	for (auto &i : oldClients)
	{
		auto it = parties.find(i.second.getParty());

		toDisconnect.insert({i.first, i.second});

		if (it != parties.end())
		{
			int other = it->second->getOtherPlayer(i.first);

			delete it->second;
			parties.erase(it);

			if (other != -1)
			{
				Client otherClient = server->getClient(other);
				toDisconnect.insert({other, otherClient});

				server->sendMessage(otherClient, "Your opponent left the game");
			}
		}
	}

	server->disconnectClients(toDisconnect);
}

void Game::react()
{
	while (true)
	{
		this_thread::sleep_for(chrono::milliseconds(100));

		processNewConnections();
		processMessages();
		processOldConnections();
	}	
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
	for (auto &i : parties)
	{
		delete i.second;
	}

	delete server;
}
