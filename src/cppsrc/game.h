#pragma once
#include <random>
#include "message.h"
#include "Chess_Board.h"


using namespace std;

class Game {
protected:
	int INF = 1e7;
	int game_mode;
	enum { zero_human, one_human, four_human };
	enum { computer, human };
	int cur_player;
	Msg msg;
	std::uniform_int_distribution<int> distribution;
	std::default_random_engine generator;
	int piece_role(char ch);
	int get_game_mode();
	int read_cmd_input();
	pair<int, int> random_move();
	int move_piece(int from, int to);

public:
	Chess_Board board;
	int single_gamer_loop(int player);
	int game_loop();
	
	int player_role[4];
	const int player_cnt = 4;
	Game();
	~Game();
};