#include "server.hpp"

#include <thread>
#include <chrono>

using namespace std;

int main()
{
	Server* serv = new Server(5040);

	while (true)
	{
		this_thread::sleep_for(chrono::milliseconds(100));

		serv->checkActivity();
	}

	delete serv;

	return 0;
}
