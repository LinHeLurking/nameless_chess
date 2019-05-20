#include "Chess_Board.h"
#include "game.h"

Game::Game() {
	this->board = Chess_Board();
}

Game::~Game() {

}

enum { game_end, eat, normal, error };
int Game::move_piece(int from, int to) {
	//msg << "[] trying to move piece from " << from << " to " << to << endl;
	//pair<int, int> cor = board.pos_decode(from);
	//msg << "[] in (" << cor.first << "," << cor.second << ") , occupy == " << board.in_pos(from) << endl;
	if (board.is_piece(from)) {
		auto test = board.is_adj(from, to);
		if (board.is_piece(to) && board.is_adj(from,to)) {
			piece_cnt[piece_role(board.in_pos(to))]--;
			char& src = board.in_pos(from);
			char __src = board.in_pos(from);
			char& aim = board.in_pos(to);
			aim = __src;
			src = ' ';
			return piece_cnt[piece_role(board.in_pos(to))]?eat:game_end;
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

int Game::single_gamer_loop(int player) {
	int from = 0;
	board.drawbaord();
	if (eat_state) {
		eat_state = 0;
		msg << "[] last player ate a piece" << endl;
	}
	while (true) {
		msg << "[] round for player " << board.piece[player] << std::endl;
		msg << "[] Choose the piece you want to move" << std::endl;
		pair<int, int> cor = read_pos_from_input();
		//msg << "[] got input    " << cor.first << ", " << (char)(cor.second + 'A') << endl;
		from = board.pos_encode(cor.first, cor.second);
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
		cor = read_pos_from_input();
		int to = board.pos_encode(cor.first, cor.second);
		if (to <= -INF) return -INF;
		int result = move_piece(from, to);
		if (result == normal) {
			return normal;
		}
		else if (result == eat) {
			eat_state = 1;
			return eat;
		}		
		else {
			msg << "[] Not suitable positions!" << std::endl;
			return error;
		}
	}
	return 0;
}

int Game::game_loop() {
	msg << "[] This is nameless chess, enjoy it!" << std::endl;
	msg << "[] input \"quit\" || \"exit\" to leave the game at any time" << std::endl << endl;
	int player = 0;
	while (true) {
		int result = single_gamer_loop(player);
		if (result <= -INF)return -INF;
		else if (result == game_end) {
			msg << "[] one of the players lost all his/her pieces, so the game terminates." << endl;
		}
		player = (player + 1) % 4;
		system("clear");
	}
	return 0;
}

pair<int, int>  Game::read_pos_from_input()
{
	msg << "[] please input the cordinates:" << std::endl;
	int i = -1, j = -1;
	bool flag = false;
	string ss;
	while (msg<<"",cin >> ss) {
		if (ss == "quit" || ss == "exit" || ss == "Quit" || ss == "Exit") {
			return { -INF,-INF };
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
	return { i,j };
}