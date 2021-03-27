#include <thread>
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
	
using namespace std;

bool flag = true;

vector<int> vec1({1, 2, 3, 4});
vector<int> vec2({2, 3, 4, 5});

int res = 0;

void scal(int from, int to)
{
	for (int i = from; i <= to; i++)
	{
		res += vec1[i] * vec2[i];
	}
}


int main()  
{   
	cout << "How many threads?" << endl;
	int n;

	cin >> n;

	int chunk = vec1.size() / n;

	int currentIndex = 0;

	vector <thread> threads;

	for (int i = 0; i < n; i++)
	{
		threads.push_back(thread(scal, currentIndex, i == n - 1 ? vec1.size() - 1 : currentIndex + chunk));

		currentIndex += chunk + 1;
	}

	for (int i = 0; i < n; i++)
	{
		threads[i].join();
	}

	cout << res << endl;
}
