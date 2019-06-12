#pragma once
#include "matrix.h"
using namespace std;
class Chess_Board
{
private:
	string vert = "|   ";
	string hori = "---";
	string hori_nothing = "   ";
	string vert_nothing = "    ";
	string oblique_a = "| \\ ";
	string oblique_b = "| / ";
	char  vert_occ = '|';
	char hori_occ = '-';
	int add_edge(int i1, int j1, int i2, int j2);


protected:
	Matrix<int> adj;
	Matrix<char> bat;

	
	

public:
	Chess_Board();
	~Chess_Board();
	static const int size = 11;
	int drawboard();
	vector<char> piece;
	bool is_piece(int pos);
	char& in_pos(int pos);
	int pos_encode(int i, int j);
	pair<int, int> pos_decode(int pos);
	bool is_adj(int a, int b);
	bool player_piece_match(int player, int pos);
	int piece_cnt[4] = { 7,7,7,7 };
};

