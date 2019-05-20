#include "Chess_Board.h"
#include "game.h"

Game::Game() {
	this->board = Chess_Board();
}

Game::~Game() {

}

int Game::move_piece(int from, int to) {
	if (board.is_piece(from)) {
		if (board.is_piece(to) && board.is_adj(from,to)) {
			char& src = board.in_pos(from);
			char __src = board.in_pos(from);
			char& aim = board.in_pos(to);
			aim = __src;
			src = ' ';
			return 0;
		}
		else if(board.is_adj(from,to)) {
			swap(board.in_pos(from), board.in_pos(to));
			return 0;
		}
	}
	return -1;
}

int Game::single_gamer_loop(int player) {
	int from = 0;
	board.drawbaord();
	while (true) {
		msg << "[] round for player " << board.piece[player] << std::endl;
		msg << "[] Choose the piece you want to move" << std::endl;
		pair<int, int> cor = read_pos_from_input();
		from = board.pos_encode(cor.first, cor.second);
		if (from <= -INF)return -INF;
		if (!board.is_piece(from)) {
			msg << "Not a valid piece" << std::endl;
			continue;
		}
		else if (!board.player_piece_match(player, from)) {
			msg << "Not your piece! RUA!" << std::endl;
			continue;
		}

		msg << "[] Choose the destination" << std::endl;
		int to = board.pos_encode((cor = read_pos_from_input()).first, cor.second);
		if (to <= -INF)return -INF;
		if (move_piece(from, to) == 0)break;
		else {
			msg << "Invalid input!" << std::endl;
		}
	}
	return 0;
}

int Game::game_loop() {
	msg << "[] This is nameless chess, enjoy it!" << std::endl;
	msg << "input \"quit\" || \"exit\" to leave the game at any time" << std::endl;
	int player = 0;
	while (true) {
		if(single_gamer_loop(player)<=-INF)return -INF;
		player = (player + 1) % 4;
		system("clear");
	}
	return 0;
}

pair<int, int>  Game::read_pos_from_input()
{
	msg << "please input the cordinates:" << std::endl;
	const int max_retry = 3;
	int retry_cnt = 0;
	int i = -1, j = -1;
	while (true) {
		string ss;
		while (retry_cnt++<max_retry && (i==-1||j==-1) && cin >> ss) {
			if (ss == "quit" || ss == "exit" || ss == "Quit" || ss == "Exit") {
				return { -INF,-INF };
			}
			int sz = ss.size();
			if (sz == 0 || sz > 3)continue;
			if (sz == 2) {
				if (ss[0] >= '0' && ss[0] <= '9' && ss[1] >= '0' && ss[1] <= '9') {
					i = (ss[0] - '0') * 10 + ss[1] - '0';
				}
				else if (ss[0] >= 'A' && ss[0] <= 'Z' && ss[1] >= '0' && ss[1] <= '9') {
					i = ss[1] - '0';
					j = ss[0] - 'A';
				}
				else if (ss[0] >= 'a' && ss[0] <= 'z' && ss[1] >= '0' && ss[1] <= '9') {
					i = ss[1] - '0';
					j = ss[0] - 'a';
				}
				else if (ss[1] >= 'a' && ss[1] <= 'z' && ss[0] >= '0' && ss[0] <= '9') {
					j = ss[1] - 'a';
					i = ss[0] - '0';
				}
				else if (ss[1] >= 'A' && ss[1] <= 'Z' && ss[0] >= '0' && ss[0] <= '9') {
					j = ss[1] - 'A';
					i = ss[0] - '0';
				}
			}
			else {
				if (ss[0] >= '0' && ss[0] <= '9') {
					i = ss[0] - '0';
				}
				else if (ss[0] >= 'A' && ss[0] <= 'Z') {
					j = ss[0] - 'A';
				}
				else if (ss[0] >= 'a' && ss[0] <= 'z') {
					j = ss[0] - 'a';
				}
			}
		}
		if (i == -1 || j == -1) {
			msg << "Invalid input!" << std::endl;
			continue;
		}
		break;
	}
	return { i,j };
}