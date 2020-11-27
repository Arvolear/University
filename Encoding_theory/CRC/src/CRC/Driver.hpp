#include "CRCEncoder.hpp"
#include "CRCDecoder.hpp"

#include <cstring>

class Driver
{
private:
    static const string POLYNOM;
    static const int BUFFER;

    void showHelp()
    {
        cout << "Welcome to the CRC-7 encoder/decoder!\n"
             << "./crc [-h -e -d] <input bytes>\n"
             << "\t -h or --help - shows this message\n"
             << "\t -e or --encode <input bytes> - encode the bytes\n"
             << "\t -d or --decode <input bytes> - decode the bytes\n"
             << "\nExamples:\n"
             << "\t ./crc -e 100101100\n"
             << "\t ./crc -d 1001011001010101\n";
    }

    void encode(const string &inBytes)
    {
        CRCEncoder encoder;

        encoder.encode(inBytes, POLYNOM, BUFFER);
    }

    void decode(const string &inBytes)
    {
        CRCDecoder decoder;

        decoder.decode(inBytes, POLYNOM, BUFFER);    
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