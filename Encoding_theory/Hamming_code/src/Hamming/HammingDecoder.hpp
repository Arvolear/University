#pragma once

#include "Util.hpp"

#include <iostream>
#include <vector>
#include <list>
#include <algorithm>

using namespace std;

class HammingDecoder
{
private:
    Util util;

    list<int> inputBytes;

    vector<list<int>> hammingBytes;
    vector<int> scalarValues;

    int numberOfControlBytes;

    void calcNumberOfControlBytes()
    {
        numberOfControlBytes = 1;

        while (numberOfControlBytes <= int(inputBytes.size()))
        {
            numberOfControlBytes *= 2;
        }

        numberOfControlBytes /= 2;
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

    void changeWrongByteAndOutput()
    {
        string potentialError = "";

        for (auto val : scalarValues)
        {
            potentialError += to_string(val);
        }

        int index = util.fromBinary(potentialError);

        cout << endl
             << "Encoded message:" << endl;

        for (auto val : hammingBytes[0])
        {
            cout << val;
        }
        cout << endl;

        if (index == 0)
        {
            cout << endl
                 << "The encoded message is correct!" << endl;
        }
        else
        {
            for (int i = 0; i < index - 1; i++)
            {
                cout << ' ';
            }
            cout << "^" << endl;

            cout << "An error in " + to_string(index) + " byte" << endl;
        }

        auto it = hammingBytes[0].begin();
        advance(it, index - 1);

        (*it) = ((*it) + 1) % 2;
    }

    void outputCorrect()
    {
        cout << endl
             << "Decoded message:" << endl;

        int powerOfTwo = 1;

        int i = 0;
        for (auto &val : hammingBytes[0])
        {
            if (i == powerOfTwo - 1)
            {
                powerOfTwo *= 2;
            }
            else
            {
                cout << val;
            }

            i++;
        }

        cout << endl
             << endl;
    }

public:
    void decode(const string &inBytes)
    {
        for (size_t i = 0; i < inBytes.size(); i++)
        {
            inputBytes.push_back(int(inBytes[i] - '0'));
        }

        hammingBytes.push_back(inputBytes);

        calcNumberOfControlBytes();
        expandTable();
        writeTable();
        calcScalarValues();
        changeWrongByteAndOutput();
        outputCorrect();
    }
};
