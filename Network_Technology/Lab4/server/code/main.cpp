#include "game.hpp"

using namespace std;

int main()
{
	Game* game = new Game();

	game->play();

	delete game;
	
	return 0;
}
