#pragma once

#include <string>

using std::string;

class Util
{
public:
    int binaryToDecimal(const string &binary) const
    {
        int res = 0;

        for (size_t i = 0; i < binary.length(); i++)
        {
            res = res * 2 + (binary[i] - '0');
        }

        return res;
    }

    string decimalToBinary(int decimal) const
    {
        string binTmp = "";
        string res = "";

        while (decimal)
        {
            binTmp += decimal % 2 + '0';
            decimal /= 2;
        }

        res.append(8 - binTmp.length(), '0'); // make the whole byte

        for (int i = binTmp.length() - 1; i >= 0; i--)
        {
            res += binTmp[i];
        }        

        return res;
    }
};