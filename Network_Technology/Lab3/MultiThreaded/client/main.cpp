#include "client.hpp"

#include <thread>
#include <chrono>

using namespace std;

int main()
{
	Client *cli = new Client("127.0.0.1", 5040);

	while (true)
	{
		this_thread::sleep_for(chrono::milliseconds(100));

		string msg;

		cout << "Enter your message please:" << endl;
		cin >> msg;

		cli->sendMessage(msg);
		cli->receiveMessage();
	}
	
	delete cli;

	return 0;
}
