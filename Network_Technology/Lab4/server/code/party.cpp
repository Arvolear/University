#include "party.hpp"

int Party::globalPartyIndex = 0;

Party::Party()
{
	this->index = globalPartyIndex;

	init();

	++globalPartyIndex;
}

void Party::init()
{
	howManyPlayers = 0;
	cross = zero = -1;

	field.resize(3);

	for (size_t i = 0; i < field.size(); i++)
	{
		field[i].resize(3, '?');
	}
}

string Party::getBeautifulField()
{
	string res;

	res += '\n';

	for (size_t i = 0; i < field.size(); i++)
	{
		for (size_t j = 0; j < field[i].size(); j++)
		{
			res += field[i][j];
		}

		res += '\n';
	}

	return res;
}

void Party::nextTurn()
{
	current = cross == current ? zero : cross;
}

bool Party::isFull()
{
	return howManyPlayers == MAX_PLAYERS; 
}

void Party::addPlayer(int id)
{
	if (howManyPlayers == 0)
	{
		cross = id;
		
		outputMessages.insert({id, "You are X, wait for an opponent"});
	}
	else if (howManyPlayers == 1)
	{
		zero = id;
		current = cross;

		outputMessages.insert({cross, "Hey, X" + getBeautifulField()});
		outputMessages.insert({zero, "Hey, O" + getBeautifulField()});
	}
	else
	{
		throw(runtime_error("2 players MAX"));
	}

	++howManyPlayers;
}

void Party::receiveResponse(int id, string message)
{
	if (howManyPlayers != 2)
	{
		outputMessages.insert({id, "Waiting for an opponent"});
	}
	else if (id != current)
	{
		outputMessages.insert({id, "No worries, it is not your turn"});
	}
	else
	{
		// TODO validate message
		
		// if (!valid(message)
		// { 
		//		... 
		// }
		
		int row = atoi(&message[0]);
		int col = atoi(&message[2]);

		field[row][col] = current == cross ? 'X' : 'O';

		outputMessages.insert({current, "Your turn is over\n" + getBeautifulField() + "Wait..."});

		nextTurn();

		outputMessages.insert({current, "Hey!\n" + getBeautifulField()});	
	}
}
		
map < int, string > Party::getMessages()
{
	map < int, string > tmp = outputMessages;
	outputMessages.clear();

	return tmp;
}

int Party::getOtherPlayer(int id)
{
	if (id == cross)
	{
		return zero;
	}
	
	if (id == zero)
	{
		return cross;
	}

	throw(runtime_error("other not found"));
}

int Party::getIndex()
{
	return index;
}

Party::~Party() {}
