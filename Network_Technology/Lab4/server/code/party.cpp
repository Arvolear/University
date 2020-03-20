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
	finish = false;
	howManyPlayers = 0;
	cross = zero = -1;

	field.resize(FIELD_SIZE);

	for (size_t i = 0; i < field.size(); i++)
	{
		field[i].resize(FIELD_SIZE, "?");
	}
}

string Party::getBeautifulField()
{
	/*
	
	X turn!
	
		1   2   3  
		
	1	X | O | X      <row> <col> -> your answer.
	   ---+---+---     "QUIT" -> quit the game.
	2	O | X | O      
	   ---+---+---
	3   O | X | X
	
	*/
	
	stringstream resStream;
	int offset = 7;

	resStream << "\n" << setw(offset);

	for (size_t i = 1; i <= field[0].size(); i++)
	{
		resStream << i << "   ";
	}

	resStream << "\n\n";

	for (size_t i = 0; i < field.size(); i++)
	{
		resStream << setw(offset - 3) << i + 1 << " ";

		for (size_t j = 0; j < field[i].size(); j++)
		{
			resStream << " " << field[i][j] << " ";

			if (j != field[i].size() - 1)
			{
				resStream << "|";
			}
		}

		if (i == 0)
		{
			resStream << "     " << "<row> <col> -> your answer.";
		}

		if (i == 1)
		{
			resStream << "     " << "\"QUIT\" -> quit the game.";	
		}

		if (i != field.size() - 1)
		{
			resStream << "\n" << setw(offset - 1);

			for (size_t j = 0; j < field[0].size() * 3 + field[0].size() - 1; j++)
			{
				if ((j + 1) % 4 == 0)
				{
					resStream << "+";
				}
				else
				{
					resStream << "-";
				}
			}
		}

		resStream << "\n";
	}

	resStream << "\n";

	return resStream.str();
}

void Party::nextTurn()
{
	if (current == cross)
	{
		current = zero;
		currentSymbol = "O";
	}
	else
	{
		current = cross;
		currentSymbol = "X";
	}
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

		outputMessages.insert({id, "\nYou are X, please wait for an opponent...\n"});
	}
	else if (howManyPlayers == 1)
	{
		zero = id;
		current = cross;
		currentSymbol = "X";

		outputMessages.insert({cross, "\n --= GAME START =--\n\nYou go first!\n" + getBeautifulField()});

		outputMessages.insert({zero, "\n --= GAME START =--\n\nYou are O, you go second!\n" + getBeautifulField()});
	}
	else
	{
		throw(runtime_error("2 players MAX"));
	}

	++howManyPlayers;
}

bool Party::isValid(const string &message, int &row, int &col)
{
	if (message == "")
	{
		return false;
	}

	stringstream validator;
	validator << message;

	string sRow, sCol;

	validator >> sRow;
	
	if (validator.peek() == EOF)
	{
		return false;
	}

	validator >> sCol;

	if (validator.peek() != EOF)
	{
		return false;
	}

	try
	{
		row = stoi(sRow);
		col = stoi(sCol);

		row--;
		col--;

		if (row < 0 || row >= (int)field.size() || 
		    col < 0 || col >= (int)field[0].size())
		{
			return false;
		}

		if (field[row][col] != "?")
		{
			return false;
		}
	}
	catch (exception &ex)
	{
		return false;
	}

	return true;
}

bool Party::isWin(int row, int col)
{
	vector < vector < int > > used(field.size(), vector < int >(field[0].size(), 0));

	queue < pair < int, int > > q;

	q.push({row, col});
	used[row][col] = 1;

	while (!q.empty())
	{
		pair < int, int > cur = q.front();
		q.pop();

		if (cur.first > 0)
		{
			if (!used[cur.first - 1][cur.second] && field[cur.first - 1][cur.second] == currentSymbol)
			{
				used[cur.first - 1][cur.second] = used[cur.first][cur.second] + 1;
				q.push({cur.first - 1, cur.second});
			}
		}
		
		if (cur.first < (int)field.size() - 1)
		{
			if (!used[cur.first + 1][cur.second] && field[cur.first + 1][cur.second] == currentSymbol)
			{
				used[cur.first + 1][cur.second] = used[cur.first][cur.second] + 1;
				q.push({cur.first + 1, cur.second});
			}
		}
		
		if (cur.second > 0)
		{
			if (!used[cur.first][cur.second - 1] && field[cur.first][cur.second - 1] == currentSymbol)
			{
				used[cur.first][cur.second - 1] = used[cur.first][cur.second] + 1;
				q.push({cur.first, cur.second - 1});
			}
		}
		
		if (cur.second < (int)field[0].size() - 1)
		{
			if (!used[cur.first][cur.second + 1] && field[cur.first][cur.second + 1] == currentSymbol)
			{
				used[cur.first][cur.second + 1] = used[cur.first][cur.second] + 1;
				q.push({cur.first, cur.second + 1});
			}
		}
	}

	for (size_t i = 0; i < used.size(); i++)
	{
		for (size_t j = 0; j < used[i].size(); j++)
		{
			if (used[i][j] == TO_WIN)
			{
				return true;
			}
		}
	}

	return false;
}

void Party::receiveResponse(int id, string message)
{
	if (howManyPlayers != 2)
	{
		outputMessages.insert({id, "\nWaiting for an opponent...\n"});
		return;
	}

	if (id != current)
	{
		outputMessages.insert({id, "\nNot your turn!\n"});
		return;
	}

	int row = -1, col = -1;

	if (!isValid(message, row, col))
	{ 
		outputMessages.insert({current, "\nInvalid answer, please try again\n\n"});	
		return;
	}

	int prev = current;

	field[row][col] = currentSymbol;

	string prevMessage = "";
	string currMessage = "";

	if (isWin(row, col))
	{
		prevMessage = getBeautifulField() + "You Won! Congratulations!\n";
		
		currMessage = getBeautifulField() + "You Lost, better luck next time!\n";

		finish = true;
	}
	else
	{
		prevMessage = "\nOpponent turn (" + currentSymbol + ")\n" + getBeautifulField() + "Wait...\n";

		currMessage = "\nYour turn (" + currentSymbol + ")\n" + getBeautifulField();
	}
	
	nextTurn();
		
	outputMessages.insert({prev, prevMessage});
	outputMessages.insert({current, currMessage});
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

bool Party::shouldFinish()
{
	return finish;
}

int Party::getCrossPlayer()
{
	return cross;
}

int Party::getZeroPlayer()
{
	return zero;
}

Party::~Party() {}
