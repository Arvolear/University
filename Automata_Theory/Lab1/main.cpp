#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>

using namespace std;

string clean(const string &ex)
{
	string expr = "";

	for (size_t i = 0; i < ex.size(); i++)
	{
		if (ex[i] != ' ')
		{
			expr += ex[i];
		}
	}
	
	return expr;
}

string get(const string &expr, const string &what)
{
	string ans = "";

	size_t index = expr.find(what);

	if (index != string::npos)
	{
		if (what == "key")
		{
			int counter = 0;
			int beg = -1, end = -1;

			for (size_t i = index + what.size() + 1; i < expr.size(); i++)
			{
				if (counter == 2)
				{
					break;
				}

				if (expr[i] == '\'' || expr[i] == '`')
				{
					if (beg == -1)
					{
						beg = i + 1;
					}

					end = i - 1;
					counter++;
				}
			}

			ans = expr.substr(beg, end - beg + 1);
		}
		else
		{
			int counter = -1;
			int beg = -1, end = -1;

			for (size_t i = index; i < expr.size(); i++)
			{
				if (!counter)
				{
					break;
				}

				if (expr[i] == '{')
				{
					if (beg == -1)
					{
						beg = i;
					}

					if (counter == -1)
					{
						counter++;
					}

					counter++;
				}
				else if (expr[i] == '}')
				{
					end = i;
					counter--;
				}
			}
	
			if (beg == -1)
			{
				beg = index + what.size() + 2;
				end--;
			}

			ans = expr.substr(beg, end - beg + 1);
		}
	}

	return ans;
}

int priority(char sign)
{
	switch (sign)
	{
		case '*':
		{
			return 3;
		}
		case '.':
		{
			return 2;
		}
		case '|':
		{
			return 1;
		}
	}

	return 0;
}

string parce(string expr, char last)
{
	if (expr == "")
	{
		return "";	
	}
	
	string key = get(expr, "key");

	if (key == "atm")
	{
		return get(expr, "val");
	}

	if (key == "|")
	{
		string ans = parce(get(expr, "fst"), '|') + "|" + parce(get(expr, "snd"), '|');

		if (priority('|') <= priority(last))
		{
			ans = "(" + ans + ")";
		}

		return ans;
	}

	if (key == ".")
	{
		string ans = parce(get(expr, "fst"), '.') + "." + parce(get(expr, "snd"), '.');

		if (priority('.') <= priority(last))
		{
			ans = "(" + ans + ")";
		}

		return ans;
	}

	if (key == "*")
	{
		string ans = parce(get(expr, "val"), '*') + "*";

		if (priority('*') <= priority(last))
		{
			ans = "(" + ans + ")";
		}

		return ans;
	}
}

string parce(string expr)
{
	return parce(expr, '\0');
}

int main()
{
	string expr;

	ifstream in("input.txt");

	getline(in, expr);

	in.close();

	expr = clean(expr);

	cout << parce(expr) << endl;

	return 0;
}
