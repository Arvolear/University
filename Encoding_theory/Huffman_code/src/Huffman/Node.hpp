#pragma once

#include <string>

using std::string;

class Node
{
public:
    string data;
    int freq;

    string code;

    Node *left;
    Node *right;

    Node() : Node("", 0)
    {
    }

    Node(string data, int freq)
    {
        this->data = data;
        this->freq = freq;
        this->code = "";

        this->left = nullptr;
        this->right = nullptr;
    }

    bool operator()(Node *l, Node *r)
    {
        return l->freq > r->freq;
    }
};