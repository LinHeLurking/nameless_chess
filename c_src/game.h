#pragma once
#include "message.h"
#include "Chess_Board.h"


using namespace std;

class Game {
protected:
	int move_piece(int from, int to);
	int INF = 1e7;
	Msg msg;
	int eat_state = 0;
	int piece_role(char ch);
public:
	Chess_Board board;
	int single_gamer_loop(int player);
	int game_loop();
	pair<int, int> read_pos_from_input();
	int piece_cnt[4] = { 7,7,7,7 };
	const int player_cnt = 4;
	Game();
	~Game();
};