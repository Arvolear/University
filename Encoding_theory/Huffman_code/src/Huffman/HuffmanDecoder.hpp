#pragma once

#include "Node.hpp"
#include "Util.hpp"

#include <iostream>
#include <queue>
#include <vector>
#include <unordered_map>
#include <fstream>

using namespace std;

class HuffmanDecoder
{
private:
    Util *util;

    priority_queue<Node *, vector<Node *>, Node> heap;
    unordered_map<string, Node *> codesTable;

    string inputFileName;

    void build(const string &path, string data)
    {
        Node *root = heap.top();

        for (size_t i = 0; i < path.length(); i++)
        {
            if (path[i] == '0')
            {
                if (root->left == nullptr)
                {
                    root->left = new Node();
                }

                root = root->left;
            }
            else
            {
                if (root->right == nullptr)
                {
                    root->right = new Node();
                }

                root = root->right;
            }
        }

        root->code = path;
        root->data = data;
    }

    void cleanTree(Node *root)
    {
        if (root == nullptr)
        {
            return;
        }

        cleanTree(root->left);
        cleanTree(root->right);

        delete root;
    }

public:
    HuffmanDecoder()
    {
        util = new Util();
    }

    void read(string fileName)
    {
        inputFileName = fileName;

        ifstream in(fileName, ios::binary);

        char size;
        in.read(&size, 1);

        heap.push(new Node());

        for (int i = 0; i < (int)size; i++)
        {
            char data;
            string code = "";

            in.read(&data, 1);

            unsigned char haffCode[16];
            in.read((char*)haffCode, 16);

            for (int i = 0; i < 16; i++)
            {                
                code += util->decimalToBinary(haffCode[i]);             
            }

            int j = 0;
            while (code[j] == '0')
            {
                j++;
            }

            code = code.substr(j + 1);
            build(code, string(1, data));
        }

        in.close();
    }

    void write(string fileName)
    {
        ifstream in(inputFileName, ios::binary);
        ofstream out(fileName);

        char size;
        in.read(&size, 1);

        in.seekg(-1, ios::end);
        char zerosAppended;
        in.read(&zerosAppended, 1);

        in.seekg(1 + (1 + 16) * size, ios::beg); // text start

        vector<unsigned char> text;
        char symbol;

        while (in.read(&symbol, 1))
        {
            text.push_back(symbol);
        }

        Node *root = heap.top();

        for (int i = 0; i < (int)text.size() - 1; i++)
        {
            string path = util->decimalToBinary(text[i]);

            if (i == (int)text.size() - 2)
            {
                path = path.substr(0, 8 - zerosAppended);
            }

            for (size_t j = 0; j < path.size(); j++)
            {
                if (path[j] == '0')
                {
                    root = root->left;
                }
                else
                {
                    root = root->right;
                }

                if (root->left == nullptr && root->right == nullptr)
                {
                    out << root->data;
                    root = heap.top();
                }
            }
        }

        in.close();
        out.close();
    }

    ~HuffmanDecoder()
    {
        delete util;

        //cleanTree(heap.top());
    }
};
