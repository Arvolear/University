#include "HuffmanEncoder.hpp"
#include "HuffmanDecoder.hpp"

#include <cstring>

class Driver
{
private:
    void showHelp()
    {
        cout << "Welcome to the archiver, based on Huffman code\n" << 
                "./huffman [-h -e -d] <path to input file> <path to output file>\n" <<
                "\t -h or --help - shows this message\n" <<
                "\t -e or --encode <input file> <output file> - archive the file\n" << 
                "\t -d or --decode <input file> <output file> - unarchive the file\n";
    }

    void encode(const string &inFile, const string &outFile)
    {
        HuffmanEncoder encoder;

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
        else if (key == "-e" || key == "--encode" || key == "-d" || key == "--decode")
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

                    if (key == "-e" || key == "--encode")
                    { 
                        outFile = inFile.substr(0, inFile.find_last_of(".")) + ".arv";
                    }
                    else
                    {
                        outFile = inFile.substr(0, inFile.find_last_of(".")) + ".txt";
                    }                    

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

            if (key == "-e" || key == "--encode")
            {
                encode(inFile, outFile);
            }
            else
            {
                decode(inFile, outFile);
            }
        }
        else
        {
            cout << "Error: wrong key specified" << endl;
        }
    }
};