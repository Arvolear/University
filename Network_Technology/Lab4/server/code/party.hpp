#pragma once

#include "server.hpp"

#include <vector>
#include <queue>
#include <iomanip>
#include <sstream>

class Party
{
	private:
		static int globalPartyIndex;
		int MAX_PLAYERS = 2;
		int FIELD_SIZE = 3;
		int TO_WIN = 3;
	
		/* this party index */
		int index;
		
		/* client ids */
		int cross;
		int zero;

		/* client id whose turn is now */
		int current;
		string currentSymbol;

		int howManyPlayers;

		bool finish;

		vector < vector < string > > field;

		// TODO deque
		map < int, string > outputMessages;

		void init();

		string getBeautifulField();
		void nextTurn();

		void add(queue < pair < int, int > > &q, vector < vector < int > > &used, const pair < int, int > &cur, const pair < int, int > &delta);
		bool isWin(int row, int col);
		bool isDraw(int row, int col);
		bool isValid(const string &message, int &row, int &col);
	
	public:
		Party();

		bool isFull();
		void addPlayer(int id);

		void receiveResponse(int id, string message);
		map < int, string > getMessages();

		int getOtherPlayer(int id);

		int getCrossPlayer();
		int getZeroPlayer();

		int getIndex();

		bool shouldFinish();	

		~Party();
};
