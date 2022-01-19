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

void Party::add(queue < pair < int, int > > &q, vector < vector < int > > &used, const pair < int, int > &cur, const pair < int, int > &delta)
{
	if ((cur.first - delta.first >= 0 && cur.first - delta.first < (int)field.size()) && 
		(cur.second - delta.second >= 0 && cur.second - delta.second < (int)field[0].size()))
	{
		if (!used[cur.first - delta.first][cur.second - delta.second] && 
		    field[cur.first - delta.first][cur.second - delta.second] == currentSymbol)
		{
			used[cur.first - delta.first][cur.second - delta.second] = 1;
			q.push({cur.first - delta.first, cur.second - delta.second});
		}
	}
}

bool Party::isWin(int row, int col)
{
	for (int state = 0; state < 4; state++)
	{
		vector < vector < int > > used(field.size(), vector < int >(field[0].size(), 0));

		queue < pair < int, int > > q;

		q.push({row, col});
		used[row][col] = 1;

		while (!q.empty())
		{
			pair < int, int > cur = q.front();
			q.pop();

			// HORIZONTAL
			if (state == 0)
			{
				add(q, used, cur, {0, 1});
				add(q, used, cur, {0, -1});
			}

			// VERTICAL
			if (state == 1)
			{
				add(q, used, cur, {1, 0});
				add(q, used, cur, {-1, 0});	
			}

			// MAIN
			if (state == 2)
			{
				add(q, used, cur, {1, 1});
				add(q, used, cur, {-1, -1});		
			}
			
			// SUB
			if (state == 3)
			{
				add(q, used, cur, {-1, 1});
				add(q, used, cur, {1, -1});
			}
		}

		int total = 0;
	
		for (size_t i = 0; i < used.size(); i++)
		{
			for (size_t j = 0; j < used[i].size(); j++)
			{
				total += used[i][j];
			}
		}

		if (total >= TO_WIN)
		{
			return true;
		}
	}

	return false;
}

bool Party::isDraw(int row, int col)
{
	if (isWin(row, col))
	{
		return false;
	}

	bool draw = true;

	for (size_t i = 0; i < field.size(); i++)
	{
		for (size_t j = 0; j < field[i].size(); j++)
		{
			if (field[i][j] == "?")
			{
				draw = false;
				break;
			}
		}
	}

	return draw;
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

	field[row][col] = currentSymbol;

	string prevMessage = "";
	string currMessage = "";

	if (isWin(row, col))
	{
		prevMessage = getBeautifulField() + "You Lost, better luck next time!\n";
		currMessage = getBeautifulField() + "You Won! Congratulations!\n";	

		finish = true;
	}
	else if (isDraw(row, col))
	{
		prevMessage = getBeautifulField() + "It's a Draw!\n";
		currMessage = getBeautifulField() + "It's a Draw!\n";	

		finish = true;	
	}
	else
	{
		nextTurn();

		prevMessage = "\nOpponent turn (" + currentSymbol + ")\n" + getBeautifulField() + "Wait...\n";
		currMessage = "\nYour turn (" + currentSymbol + ")\n" + getBeautifulField();
	}

	outputMessages.insert({getOtherPlayer(current), prevMessage});
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
