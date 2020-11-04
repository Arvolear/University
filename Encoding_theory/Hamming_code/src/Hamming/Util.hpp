#pragma once

#include <string>
#include <algorithm>

using std::string;
using std::reverse;

class Util
{
public:
    string toBinary(int num)
    {
        string res = "";

        while (num > 0)
        {
            res += char((num % 2) + '0');
            num /= 2;
        }
    
        return res;
    }

    int fromBinary(const string &bin)
    {
        int res = 0;

        for (int i = int(bin.size()) - 1; i >= 0; i--)
        {
            res *= 2;
            res += int(bin[i] - '0');
        }

        return res;
    }
};