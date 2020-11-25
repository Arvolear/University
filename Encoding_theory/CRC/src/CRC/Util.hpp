#pragma once

#include <string>
#include <deque>

using std::deque;
using std::string;

class Util
{
private:
    static deque<char> doXor(const deque<char> &what, const string &with)
    {
        deque<char> res;

        for (size_t i = 0; i < what.size(); i++)
        {
            res.push_back(char((int(what[i] - '0') ^ int(with[i] - '0')) + '0'));
        }

        return res;
    }

    static void removeFrontZeros(deque<char> &what)
    {
        while (!what.empty() && what[0] == '0')
        {
            what.pop_front();
        }
    }

public:
    static string stringXorReminder(const string &what, const string &with)
    {
        deque<char> tmpStr;
        int curInd = 0;

        while (true)
        {                    
            while (curInd < (int)what.size() && tmpStr.size() < with.size())
            {
                tmpStr.push_back(what[curInd]);
                curInd++;
            }

            if (curInd == (int)what.size() && tmpStr.size() < with.size())
            {
                string res = "";

                Util::removeFrontZeros(tmpStr);
                
                for (size_t i = 0; i < tmpStr.size(); i++)
                {
                    res += tmpStr[i];
                }                

                return res;
            }

            tmpStr = Util::doXor(tmpStr, with);
            Util::removeFrontZeros(tmpStr);
        }
    }
};