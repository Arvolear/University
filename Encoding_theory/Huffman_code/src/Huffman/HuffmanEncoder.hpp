#pragma once

#include "Node.hpp"
#include "Util.hpp"

#include <iostream>
#include <queue>
#include <vector>
#include <unordered_map>
#include <fstream>

using namespace std;

class HuffmanEncoder
{
private:
	Util *util;

	priority_queue<Node *, vector<Node *>, Node> heap;
	unordered_map<string, Node *> codesTable;

	string inputFileName;

	void calculateCodes(Node *root, string ans)
	{
		if (root == nullptr)
		{
			return;
		}

		if (root->left == nullptr && root->right == nullptr)
		{
			root->code = ans;			
		}

		calculateCodes(root->left, ans + "0");
		calculateCodes(root->right, ans + "1");
	}

	void build()
	{
		for (auto &pair : codesTable)
		{
			Node *node = pair.second;
			heap.push(node);
		}

		while (heap.size() > 1)
		{
			Node *left = heap.top();
			heap.pop();

			Node *right = heap.top();
			heap.pop();

			Node *top = new Node(left->data + right->data, left->freq + right->freq);
			top->left = left;
			top->right = right;

			heap.push(top);
		}

		calculateCodes(heap.top(), "");
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
	HuffmanEncoder()
	{
		util = new Util();
	}

	void read(string fileName)
	{
		inputFileName = fileName;

		ifstream in(fileName);
		char symbol;

		while (in.get(symbol))
		{
			if (codesTable.find(string(1, symbol)) == codesTable.end())
			{
				codesTable[string(1, symbol)] = new Node(string(1, symbol), 0);
			}

			codesTable[string(1, symbol)]->freq += 1;
		}

		in.close();

		build();
	}

	void write(string fileName)
	{
		ofstream out(fileName, ios::binary);

		string res = "";
		string code = "";

		res += (char)codesTable.size();

		/* Creating table to decode */
		for (auto &pair : codesTable)
		{
			res += pair.first;

			code.assign(127 - pair.second->code.length(), '0'); // 128 bit length
			code += '1';
			code += pair.second->code;

			for (int i = 0; i < 16; i++)
			{				
				res += (char)util->binaryToDecimal(code.substr(0, 8));				
				code = code.substr(8);
			}

			code = "";
		}

		ifstream in(inputFileName);
		char symbol;

		while (in.get(symbol))
		{
			code += codesTable[string(1, symbol)]->code;

			while (code.length() > 8)
			{
				res += (char)util->binaryToDecimal(code.substr(0, 8));
				code = code.substr(8);
			}
		}

		int zerosAppended = 8 - code.length();

		if (code.length() < 8)
		{
			code.append(zerosAppended, '0');
		}

		res += (char)util->binaryToDecimal(code);
		res += (char)zerosAppended;

		code = "";

		out.write(res.data(), res.size());

		in.close();
		out.close();
	}

	~HuffmanEncoder()
	{
		delete util;

		cleanTree(heap.top());
	}
};
