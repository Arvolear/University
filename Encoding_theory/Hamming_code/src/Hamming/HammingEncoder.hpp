#pragma once

#include "Util.hpp"

#include <iostream>
#include <vector>
#include <list>
#include <algorithm>

using namespace std;

class HammingEncoder
{
private:
	Util util;

	list<int> inputBytes;

	vector<list<int>> hammingBytes;
	vector<int> scalarValues;

	int numberOfControlBytes;

	void insertControlBytes()
	{
		int powerOfTwo = 1;
		numberOfControlBytes = 0;

		int i = 0;
		for (auto it = hammingBytes[0].begin(); it != hammingBytes[0].end(); i++)
		{
			if (i == powerOfTwo - 1)
			{
				hammingBytes[0].insert(it, 0);
				powerOfTwo *= 2;
				numberOfControlBytes++;
			}
			else
			{
				++it;
			}
		}
	}

	void expandTable()
	{
		for (int i = 0; i < numberOfControlBytes; i++)
		{
			list<int> newList;
			hammingBytes.push_back(newList);
		}
	}

	void writeTable()
	{
		for (size_t i = 0; i < hammingBytes[0].size(); i++)
		{
			string binaryRep = util.toBinary(i + 1);

			for (size_t k = 1; k < hammingBytes.size(); k++)
			{
				if ((k - 1) < binaryRep.size())
				{
					hammingBytes[k].push_back(int(binaryRep[k - 1] - '0'));
				}
				else
				{
					hammingBytes[k].push_back(0);
				}
			}
		}
	}

	void calcScalarValues()
	{
		for (size_t i = 1; i < hammingBytes.size(); i++)
		{
			auto inIter = hammingBytes[0].begin();
			int ans = 0;

			for (auto val : hammingBytes[i])
			{
				ans += (*inIter) * val;
				inIter++;
			}

			ans %= 2;

			scalarValues.push_back(ans);
		}
	}

	void changeControlBytes()
	{
		int powerOfTwo = 1;

		int i = 0;
		int j = 0;
		for (auto &val : hammingBytes[0])
		{
			if (i == powerOfTwo - 1)
			{
				val = scalarValues[j];
				powerOfTwo *= 2;
				j++;
			}

			i++;
		}
	}

	void outputEncoded()
	{
		cout << endl
			 << "Original message:" << endl;

		for (auto var : inputBytes)
		{
			cout << var;
		}

		cout << endl
			 << endl;

		cout << endl
			 << "Encoded message:" << endl;

		for (auto var : hammingBytes[0])
		{
			cout << var;
		}

		cout << endl
			 << endl;
	}

public:
	void encode(const string &inBytes)
	{
		for (size_t i = 0; i < inBytes.size(); i++)
		{
			inputBytes.push_back(int(inBytes[i] - '0'));
		}

		hammingBytes.push_back(inputBytes);

		insertControlBytes();
		expandTable();
		writeTable();
		calcScalarValues();
		changeControlBytes();
		outputEncoded();
	}
};
