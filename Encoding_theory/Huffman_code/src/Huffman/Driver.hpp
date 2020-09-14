#include "HuffmanEncoder.hpp"
#include "HuffmanDecoder.hpp"

#include <cstring>

class Driver
{
private:
    void showHelp()
    {
        cout << "Welcome to the archiver, based on Huffman code\n"
             << "./huffman [-h -e -d] [size] <input file> <output file>\n"
             << "\t -h or --help - shows this message\n"
             << "\t -e or --encode size <input file> <output file> - archive the file\n"
             << "\t -d or --decode <input file> <output file> - unarchive the file\n"
             << "\nExamples:\n"
             << "\t ./huffman -e 3 ./test.txt\n"
             << "\t ./huffman -d ./test.arv\n";
    }

    void encode(const string &inFile, const string &outFile, const string &sizeKey)
    {
        HuffmanEncoder encoder(stoi(sizeKey));

        encoder.read(inFile);
        encoder.write(outFile);
    }

    void decode(const string &inFile, const string &outFile)
    {
        HuffmanDecoder decoder;

        decoder.read(inFile);
        decoder.write(outFile);
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
            string inFile;
            string outFile;
            string sizeKey;

            switch (argc)
            {
                case 2:
                {
                    cout << "Error: No size provided" << endl;
                }
                case 3:
                {
                    cout << "Error: No input file provided" << endl;
                    return;
                }
                case 4:
                {
                    sizeKey = argv[2];

                    if (sizeKey.find_first_not_of("0123456789") != std::string::npos || sizeKey.find("0") == 0)
                    {
                        cout << "Error: wrong size specified" << endl;
                        return;
                    }

                    inFile = argv[3];
                    outFile = inFile.substr(0, inFile.find_last_of(".")) + ".arv";

                    break;
                }
                case 5:
                {
                    sizeKey = argv[2];

                    if (sizeKey.find_first_not_of("0123456789") != std::string::npos || sizeKey.find("0") == 0)
                    {
                        cout << "Error: wrong size specified" << endl;
                        return;
                    }

                    inFile = argv[3];
                    outFile = argv[4];

                    break;
                }
                default:
                {
                    cout << "Error: too many arguments" << endl;
                    return;
                }     
            }

            encode(inFile, outFile, sizeKey);
        }
        else if (key == "-d" || key == "--decode")
        {
            string inFile;
            string outFile;            

            switch (argc)
            {
                case 2:
                {
                    cout << "Error: No input file provided" << endl;
                    return;
                }
                case 3:
                {
                    inFile = argv[2];
                    outFile = inFile.substr(0, inFile.find_last_of(".")) + ".txt";

                    break;
                }
                case 4:
                {
                    inFile = argv[2];
                    outFile = argv[3];

                    break;
                }
                default:
                {
                    cout << "Error: too many arguments" << endl;
                    return;
                }
            }

            decode(inFile, outFile);
        }
        else
        {
            cout << "Error: wrong key specified" << endl;
        }
    }
};