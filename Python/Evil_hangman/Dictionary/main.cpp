#include <fstream>
#include <iostream>
#include <string>
#include <algorithm>
#include <vector>
#include <cctype>

using namespace std;

int main(int argc, char** argv)
{
    if (argc > 3 || argc < 1)
    {
        cout << "Wrong arguments!\n";
        return 0;
    }

    int exactMatch = -1;

    if (argc == 3)
    {
        exactMatch = stoi(argv[2]);
    }

    vector < ofstream* > files;

    ifstream dic(argv[1]);

    if (!dic)
    {
        cout << "Error while opening the file!\n";
        return 0;
    }

    string word;

    while (dic >> word)
    {
        if (exactMatch == -1 || (exactMatch != -1 && (int)word.length() == exactMatch))
        {
            if (word.length() > files.size())
            {
                files.resize(word.length(), nullptr);
            }
            
            if (!files[word.length() - 1])
            {
                files[word.length() - 1] = new ofstream("length" + to_string(word.length()) + ".txt");
            }

            bool norm = true;
            for (size_t i = 0; i < word.length(); i++)
            {
                if ((!isalpha(word[i]) || isupper(word[i])) && word[i] != '-')
                {
                    norm = false;
                    break;
                }
            }

            if (!norm)
            {
                continue;
            }

            *(files[word.length() - 1]) << word << endl;
        }
    }
            
    for (size_t i = 0; i < files.size(); i++)
    {
        if (files[i])
        {
            files[i]->close();
        }
        
        delete files[i];
    }

    return 0;
}
