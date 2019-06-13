#pragma once
#include <random>
#include "message.h"
#include "Chess_Board.h"


using namespace std;

class Game {
protected:
	int INF = 1e7;
	Msg msg;
	std::uniform_int_distribution<int> distribution;
	std::default_random_engine generator;
	bool exit_flag = false;
	void init();
	

public:
	Chess_Board board;
	int game_mode;
	int single_gamer_loop(int player);
	int game_loop();
	enum mode { zero_human, one_human, four_human };
	enum move { eat, normal, error };
	enum role { computer, human };
	int player_role[4];
	const int player_cnt = 4;
	int cur_player;
	int piece_owner(char ch);
	int get_game_mode();
	int read_cmd_input();
	pair<int, int> random_move();
	int move_piece(int from, int to);
	int move_piece(pair<int, int> move);
	void log(string s);
	void set_exit_flag(bool bv);
	Game();
	~Game();
};