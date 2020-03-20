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

	for (auto &clientIt : newClients)
	{
		bool foundEmpty = false;

		for (auto &partyIt : parties)
		{
			/* party is found */
			if (!partyIt.second->isFull())
			{
				foundEmpty = true;

				partyIt.second->addPlayer(clientIt.first);
				server->updateClientParty(clientIt.first, partyIt.first);

				break;
			}
		}

		if (!foundEmpty)
		{
			Party *newParty = new Party();

			newParty->addPlayer(clientIt.first);
			server->updateClientParty(clientIt.first, newParty->getIndex());

			parties.insert({newParty->getIndex(), newParty});
		}
	}
		
	server->approveClients(newClients);
}

void Game::processMessages()
{
	map < int, string > clientMessages = server->getClientsMessages();

	/* update parties */
	for (auto &messagesIt : clientMessages)
	{
		if (messagesIt.second == "QUIT")
		{
			server->updateClientType(messagesIt.first, ClientType::OLD);
			continue;
		}
		
		Client client = server->getClient(messagesIt.first);
		
		auto partyIt = parties.find(client.getParty());

		if (partyIt != parties.end())
		{
			partyIt->second->receiveResponse(messagesIt.first, messagesIt.second);
		}
	}
}

void Game::processOldConnections()
{
	map < int, Client > oldClients = server->getOldClients();
	map < int, Client > toDisconnect;

	for (auto &clientIt : oldClients)
	{
		auto partyIt = parties.find(clientIt.second.getParty());

		toDisconnect.insert({clientIt.first, clientIt.second});

		if (partyIt != parties.end())
		{
			int otherID = partyIt->second->getOtherPlayer(clientIt.first);

			delete partyIt->second;
			parties.erase(partyIt);

			if (otherID != -1)
			{
				Client otherClient = server->getClient(otherID);
				toDisconnect.insert({otherID, otherClient});

				server->sendMessage(otherClient, "\nYour opponent left the game\n");
			}
		}
	}

	server->disconnectClients(toDisconnect);
}

void Game::processParties()
{
	/* update clients */
	for (auto partyIt = parties.begin(); partyIt != parties.end();)
	{
		map < int, string > messages = partyIt->second->getMessages();

		for (auto &messagesIt : messages)
		{
			server->sendMessage(server->getClient(messagesIt.first), messagesIt.second);
		}
		
		if (partyIt->second->shouldFinish())
		{
			Client cross = server->getClient(partyIt->second->getCrossPlayer());
			Client zero = server->getClient(partyIt->second->getZeroPlayer());

			delete partyIt->second;
			partyIt = parties.erase(partyIt);

			server->disconnectClient(cross);
			server->disconnectClient(zero);
		}
		else
		{
			++partyIt;
		}
	}
}

void Game::react()
{
	while (true)
	{
		this_thread::sleep_for(chrono::milliseconds(100));

		processNewConnections();
		processParties();
		processMessages();
		processParties();
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
