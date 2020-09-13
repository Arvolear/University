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

	int blockSize, lastBlockSize;

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
	HuffmanEncoder(int blockSize)
	{
		util = new Util();

		this->blockSize = blockSize;
		this->lastBlockSize = blockSize;
	}

	void read(string fileName)
	{
		inputFileName = fileName;

		ifstream in(fileName);

		while (in.peek() != EOF)
		{
			string symbols = "";
			char symbol;

			int i = 0;
			while (i < blockSize && in.get(symbol))
			{
				symbols += string(1, symbol);
				i++;
			}

			lastBlockSize = symbols.length();

			if (codesTable.find(symbols) == codesTable.end())
			{
				codesTable[symbols] = new Node(symbols, 0);
			}

			codesTable[symbols]->freq += 1;
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
		res += (char)blockSize;
		res += (char)lastBlockSize;

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

		while (in.peek() != EOF)
		{
			string symbols = "";

			int i = 0;
			while (i < blockSize && in.get(symbol))
			{
				symbols += string(1, symbol);
				i++;
			}

			code += codesTable[symbols]->code;

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
