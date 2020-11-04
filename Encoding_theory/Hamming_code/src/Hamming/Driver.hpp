#include "HammingEncoder.hpp"
#include "HammingDecoder.hpp"

#include <cstring>

class Driver
{
private:
    void showHelp()
    {
        cout << "Welcome to the Hamming encoder/decoder!\n"
             << "./hamming [-h -e -d] <input bytes>\n"
             << "\t -h or --help - shows this message\n"
             << "\t -e or --encode <input bytes> - encode the bytes\n"
             << "\t -d or --decode <input bytes> - decode the bytes\n"
             << "\nExamples:\n"
             << "\t ./hamming -e 100101100\n"
             << "\t ./hamming -d 1011001001100\n";
    }

    void encode(const string &inBytes)
    {
        HammingEncoder encoder;

        encoder.encode(inBytes);
    }

    void decode(const string &inBytes)
    {
        HammingDecoder decoder;

        decoder.decode(inBytes);    
    }

public:
    void work(int argc, char **argv)
    {
        if (argc < 2)
        {
            cout << "Error: no arguments provided" << endl;
            return;
        }

        string key = argv[1];

        if (key == "-h" || key == "--help")
        {
            showHelp();
        }
        else if (key == "-e" || key == "--encode")
        {
            string inBytes;

            switch (argc)
            {
            case 2:
            {
                cout << "Error: No input bytes provided" << endl;
                return;
            }
            case 3:
            {
                inBytes = argv[2];

                if (inBytes.find_first_not_of("01") != std::string::npos)
                {
                    cout << "Error: wrong input bytes symbols" << endl;
                    return;
                }

                break;
            }
            default:
            {
                cout << "Error: too many arguments" << endl;
                return;
            }
            }

            encode(inBytes);
        }
        else if (key == "-d" || key == "--decode")
        {
            string inBytes;

            switch (argc)
            {
            case 2:
            {
                cout << "Error: No input bytes provided" << endl;
                return;
            }
            case 3:
            {
                inBytes = argv[2];

                if (inBytes.find_first_not_of("01") != std::string::npos)
                {
                    cout << "Error: wrong input bytes symbols" << endl;
                    return;
                }

                break;
            }
            default:
            {
                cout << "Error: too many arguments" << endl;
                return;
            }
            }

            decode(inBytes);
        }
        else
        {
            cout << "Error: wrong key specified" << endl;
        }
    }
};