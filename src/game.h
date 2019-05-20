#pragma once
#include "message.h"
#include "Chess_Board.h"


using namespace std;

class Game {
protected:
	int move_piece(int from, int to);
	int INF = 1e7;
	Msg msg;
public:
	Chess_Board board;
	int single_gamer_loop(int player);
	int game_loop();
	pair<int, int> read_pos_from_input();
	Game();
	~Game();
};