#pragma once

#include "Util.hpp"

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class CRCEncoder
{
private:
	vector<string> splitByBuffer(const string &inBytes, int buffer)
	{
		vector<string> res;
		string tmp = "";

		for (size_t i = 0; i < inBytes.length(); i++)
		{
			tmp += inBytes[i];

			if ((i != 0 && i % buffer == 0) || i == inBytes.length() - 1)
			{
				res.push_back(tmp);
				tmp = "";
			}
		}

		return res;
	}

	void addZeros(vector<string> &added, const string &polynom)
	{
		for (size_t i = 0; i < added.size(); i++)
		{
			added[i].append(polynom.length() - 1, '0');
		}
	}

	vector<string> xorPackets(vector<string> &added, const string &polynom)
	{
		vector<string> reminders;

		for (size_t i = 0; i < added.size(); i++)
		{
			string reminder = Util::stringXorReminder(added[i], polynom);

			for (int j = (int)reminder.length() - 1; j >= 0; j--)
			{
				added[i][(added[i].length() - 1) - (reminder.length() - 1 - j)] = reminder[j];
			}

			reminders.push_back(reminder);
		}

		return reminders;
	}

	void printPackets(const string &inBytes, const vector<string> &packets, const vector<string> &reminders, const string &polynom)
	{
		cout << endl
			 << "Original message: " << inBytes << endl;

		cout << endl
			 << "Polynom: " << polynom << endl
			 << endl;

		cout << endl
			 << "Encoded message by packets: " << endl;

		for (size_t i = 0; i < packets.size(); i++)
		{
			cout << "Packet " << i + 1 << ": " << packets[i] << "  "
				 << "Reminder: " << reminders[i] << endl;
		}

		cout << endl;
	}

public:
	void encode(const string &inBytes, const string &polynom, int buffer)
	{
		vector<string> splitted = splitByBuffer(inBytes, buffer);
		addZeros(splitted, polynom);
		vector<string> reminders = xorPackets(splitted, polynom);

		printPackets(inBytes, splitted, reminders, polynom);
	}
};
