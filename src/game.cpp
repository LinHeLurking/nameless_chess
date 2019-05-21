#include <chrono>
#include <thread>
#include "Chess_Board.h"
#include "game.h"

Game::Game() {
	this->board = Chess_Board();
	this->distribution = uniform_int_distribution<int>(0, board.size * board.size - 1);
}

Game::~Game() {

}

enum {eat, normal, error };
int Game::move_piece(int from, int to) {
	//msg << "[] trying to move piece from " << from << " to " << to << endl;
	//pair<int, int> cor = board.pos_decode(from);
	//msg << "[] in (" << cor.first << "," << cor.second << ") , occupy == " << board.in_pos(from) << endl;
	if (board.is_piece(from)) {
		auto test = board.is_adj(from, to);
		if (board.is_piece(to) && board.is_adj(from,to)) {
			board.piece_cnt[piece_role(board.in_pos(to))]--;
			char& src = board.in_pos(from);
			char __src = board.in_pos(from);
			char& aim = board.in_pos(to);
			aim = __src;
			src = ' ';
			msg << "* last player ate a piece\n";
			return eat;
		}
		else if(board.is_adj(from,to)) {
			swap(board.in_pos(from), board.in_pos(to));
			return normal;
		}
	}
	return error;
}

int Game::piece_role(char ch)
{
	for (int i = 0; i < player_cnt; ++i) {
		if (ch == board.piece[i])return i;
	}
	return -1;
}

int Game::get_game_mode()
{
	msg << "[] please input the game mode\n\t1. 4 human players\n\t2. 1 human player & 3 computer plyaers\n\t3. 4 computer players" << endl;
	string s;
	while (msg << "", cin >> s) {
		int tmp = 0;
		for (const auto& c : s) {
			tmp = tmp * 10 + c - '0';
		}
		if (tmp == 1) {
			for (int i = 0; i < player_cnt; ++i) {
				player_role[i] = human;
			}
			return four_human;
		}
		else if (tmp == 2) {
			// msg << "[] not finished yet, using game mode 1 instead" << endl;
			player_role[0] = human;
			for (int i = 1; i < player_cnt; ++i) {
				player_role[i] = computer;
			}
			return one_human;
		}
		else if (tmp == 3) {
			// msg << "[] not finished yet, using game mode 1 instead" << endl;
			for (int i = 0; i < player_cnt; ++i) {
				player_role[i] = computer;
			}
			return zero_human;
		}
		else {
			msg << "[] Not a valid game mode code" << endl;
		}
	}
	return 0;
}


int Game::single_gamer_loop(int player) {
	if (board.piece_cnt[player] == 0) {
		return 0;
	}
	int from = 0;
	board.drawbaord();
	// show delayed msseages
	msg.refresh();
	int ret;
	while (true) {
		msg << "[] round for player " << board.piece[player] << std::endl;
		msg << "[] Choose the piece you want to move" << std::endl;
		if (player_role[player] == human) {
			int from = read_cmd_input();
			// msg << "[] got input    " << cor.first << ", " << (char)(cor.second + 'A') << endl;
			if (from <= -INF)return -INF;
			if (!board.is_piece(from)) {
				msg << "[] Not a valid piece" << std::endl;
				continue;
			}
			else if (!board.player_piece_match(player, from)) {
				msg << "[] Not your piece! RUA!" << std::endl;
				continue;
			}

			msg << "[] Choose the destination" << std::endl;
			int to = read_cmd_input();
			if (to <= -INF) return -INF;
			int result = move_piece(from, to);
			if (result == normal) {
				ret = normal;
				break;
			}
			else if (result == eat) {
				ret = eat;
				break;
			}
			else {
				msg << "[] Not suitable positions!" << std::endl;
				ret = error;
				break;
			}
		}
		else {
			pair<int, int> mv=random_move();
			move_piece(mv.first, mv.second);
			ret = normal;
			break;
		}
		
	}
	if (player_role[cur_player] == computer) {
		std::this_thread::sleep_for(std::chrono::milliseconds(50));
	}
	return ret;
}

int Game::game_loop() {
	msg << "[] This is nameless chess, enjoy it!" << std::endl;
	msg << "[] input \"quit\" || \"exit\" to leave the game at any time" << std::endl << endl;
	game_mode = get_game_mode();
	cur_player = 0;
	while (true) {
		int result = single_gamer_loop(cur_player);
		if (result <= -INF)return -INF;
		else {
			int zero_cnt = 0;
			for (int i = 0; i < player_cnt; ++i) {
				if (!board.piece_cnt[i])++zero_cnt;
			}
			if (zero_cnt == 3) {
				msg << "[] only one player survives, game ends" << endl;
				break;
			}
		}
		cur_player = (cur_player + 1) % 4;
		system("clear");
	}
	return 0;
}


int Game::read_cmd_input()
{
	msg << "[] please input the cordinates:" << std::endl;
	int i = -1, j = -1;
	bool flag = false;
	string ss;
	while (msg << "", cin >> ss) {
		if (ss == "quit" || ss == "exit" || ss == "Quit" || ss == "Exit") {
			return -INF;
		}
		int sz = ss.size();
		if ((ss[0] >= 'A' && ss[0] <= 'Z') || (ss[0] >= 'a' && ss[0] <= 'z')) {
			if (ss[0] >= 'A' && ss[0] <= 'K') j = ss[0] - 'A';
			else if (ss[0] >= 'a' && ss[0] <= 'k') j = ss[0] - 'a';
			else {
				flag = true;
			}
			int tmp = 0;
			for (int k = 1; k < sz; ++k) {
				tmp = tmp * 10 + ss[k] - '0';
			}
			i = tmp >= 0 && tmp <= 11 ? tmp : i;
		}
		else if (ss[0] >= '0' && ss[0] <= '9') {
			int tmp = 0;
			for (int k = 0; ss[k] >= '0' && ss[k] <= '9'; ++k) {
				tmp = tmp * 10 + ss[k] - '0';
			}
			i = tmp >= 0 && tmp <= 11 ? tmp : i;

			if (ss[sz - 1] >= 'A' && ss[sz - 1] <= 'K') j = ss[sz - 1] - 'A';
			else if (ss[sz - 1] >= 'a' && ss[sz - 1] <= 'k') j = ss[sz - 1] - 'a';
			else {
				flag = true;
			}
		}
		else {
			flag = true;
		}

		msg << "[] got input:  " << i << "," << (char)(j + 'A') << endl;
		msg << "[] current i,j == " << i << ", " << j << "; occupy == " << board.in_pos(board.pos_encode(i, j)) << endl;
		if (i == -1 || j == -1) {
			flag = true;
		}

		if (flag) {
			msg << "[] Invalid input!" << std::endl;
			flag = false;
			ss.clear();
			continue;
		}

		break;
	}
	return board.pos_encode(i, j);
}

pair<int, int> Game::random_move()
{
	int i = -1, j = -1;
	while (i == -1) {
		int tmp = distribution(generator);
		if (board.in_pos(tmp) != board.piece[cur_player])continue;
		int tmp2 = distribution(generator);
		if (board.is_adj(tmp, tmp2) && board.in_pos(tmp2) != board.piece[cur_player]) {
			i = tmp, j = tmp2;
		}
	}
	return { i,j };
}
