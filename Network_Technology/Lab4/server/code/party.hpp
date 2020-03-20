#pragma once

#include "server.hpp"

#include <vector>

class Party
{
	private:
		static int globalPartyIndex;
		int MAX_PLAYERS = 2;
	
		/* this party index */
		int index;
		
		/* client ids */
		int cross;
		int zero;

		/* client id whose turn is now */
		int current;

		int howManyPlayers;

		vector < vector < char > > field;

		// TODO deque
		map < int, string > outputMessages;

		void init();

		string getBeautifulField();
		void nextTurn();
	
	public:
		Party();

		bool isFull();
		void addPlayer(int id);

		void receiveResponse(int id, string message);
		map < int, string > getMessages();

		int getOtherPlayer(int id);
		int getIndex();

		~Party();
};
