#include <string>
#include <sstream>
#include <algorithm>
#include "dep.h"
using namespace std;

Chess_Board::Chess_Board()
{
	this->adj = Matrix<int>(size*size, size*size, 0);
	this->bat = Matrix<char>(size, size, ' ');
	this->piece.clear();
	this->piece.push_back('o');
	this->piece.push_back('x');
	this->piece.push_back('#');
	this->piece.push_back('@');

	for (int i = 2; i <= 8; ++i) {
		for (int j = 2; j <= 8; ++j) {
			if (j != 8) {
				add_edge(i, j, i, j + 1);
			}
			if (i != 8) {
				add_edge(i, j, i + 1, j);
			}
			
		}
	}

	add_edge(2, 2, 0, 2);
	add_edge(0, 2, 0, 4);
	add_edge(1, 4, 0, 4);
	add_edge(1, 4, 1, 5);
	add_edge(1, 5, 2, 5);
	add_edge(1, 6, 1, 5);
	add_edge(1, 6, 0, 6);
	add_edge(0, 6, 0, 8);
	add_edge(0, 8, 2, 8);
	

	add_edge(2, 10, 2, 8);
	add_edge(2, 10, 4, 10);
	add_edge(4, 9, 4, 10);
	add_edge(4, 9, 5, 9);
	add_edge(5, 8, 5, 9);
	add_edge(6, 9, 5, 9);
	add_edge(6, 9, 6, 10);
	add_edge(6, 10, 8, 10);
	add_edge(8, 10, 8, 8);
	

	add_edge(10, 8, 8, 8);
	add_edge(10, 6, 10, 8);
	add_edge(10, 6, 9, 6);
	add_edge(9, 5, 9, 6);
	add_edge(8, 5, 9, 5);
	add_edge(9, 5, 9, 4);
	add_edge(10, 4, 9, 4);
	add_edge(10, 2, 10, 4);
	add_edge(8, 2, 10, 2);
	

	add_edge(8, 0, 8, 2);
	add_edge(8, 0, 6, 0);
	add_edge(6, 0, 6, 1);
	add_edge(5, 1, 6, 1);
	add_edge(5, 1, 5, 2);
	add_edge(5, 1, 4, 1);
	add_edge(4, 0, 4, 1);
	add_edge(4, 0, 2, 0);
	add_edge(2, 0, 2, 2);
	

	int tmp[][2] = {
		{3,5},
		{5,3},
		{5,7},
		{7,5}
	};
	for (auto i : tmp) {
		add_edge(i[0], i[1], i[0] - 1, i[1] - 1);
		add_edge(i[0], i[1], i[0] - 1, i[1] + 1);
		add_edge(i[0], i[1], i[0] + 1, i[1] + 1);
		add_edge(i[0], i[1], i[0] + 1, i[1] - 1);
	}

	int cnt = 0;
	bat[0][2] = piece[cnt];
	bat[0][4] = piece[cnt];
	bat[1][4] = piece[cnt];
	bat[1][5] = piece[cnt];
	bat[1][6] = piece[cnt];
	bat[0][6] = piece[cnt];
	bat[0][8] = piece[cnt];
	++cnt;

	bat[2][10] = piece[cnt];
	bat[4][10] = piece[cnt];
	bat[4][9] = piece[cnt];
	bat[5][9] = piece[cnt];
	bat[6][9] = piece[cnt];
	bat[6][10] = piece[cnt];
	bat[8][10] = piece[cnt];
	++cnt;

	bat[10][8] = piece[cnt];
	bat[10][6] = piece[cnt];
	bat[9][6] = piece[cnt];
	bat[9][5] = piece[cnt];
	bat[9][4] = piece[cnt];
	bat[10][4] = piece[cnt];
	bat[10][2] = piece[cnt];
	++cnt;

	bat[8][0] = piece[cnt];
	bat[6][0] = piece[cnt];
	bat[6][1] = piece[cnt];
	bat[5][1] = piece[cnt];
	bat[4][1] = piece[cnt];
	bat[4][0] = piece[cnt];
	bat[2][0] = piece[cnt];

	bat[0][3] = hori_occ;
	bat[1][2] = vert_occ;
	bat[0][7] = hori_occ;
	bat[1][8] = vert_occ;
	bat[2][9] = hori_occ;
	bat[3][10] = vert_occ;
	bat[7][10] = vert_occ;
	bat[8][9] = hori_occ;
	bat[9][8] = vert_occ;
	bat[10][7] = hori_occ;
	bat[10][3] = hori_occ;
	bat[9][2] = vert_occ;
	bat[8][1] = hori_occ;
	bat[7][0] = vert_occ;
	bat[3][0] = vert_occ;
	bat[2][1] = hori_occ;

	// test
	/*
	for (int i = 0; i < size; ++i) {
		for (int j = 0; j < size; ++j) {
			bat[i][j] = piece[(11 * i + j) % 4];
		}
	}
	*/	
}


Chess_Board::~Chess_Board()
{
}


int Chess_Board::pos_encode(int i, int j) {
	return 11 * i + j;
}

pair<int,int> Chess_Board::pos_decode(int pos) {
	return { pos / 11, pos % 11 };
}

bool Chess_Board::is_adj(int a, int b)
{
	return adj[a][b];
}

bool Chess_Board::player_piece_match(int player, int pos)
{
	return in_pos(pos) == piece[player];
}

int Chess_Board::drawboard() {
	vector<stringstream> ss(size * 2 + 5);
	int cnt = 0;
	ss[cnt] << "    ";
	for (int i = 0; i < size; ++i) {
		ss[cnt] << (char)('A' + i);
		ss[cnt] << "   ";
	}
	ss[cnt++] << endl;

	for (int i = 1; i < 2 * size; ++i) {
		if (i &1) {
			ss[i] << i/2;
			if (i/2 < 10)ss[i] << "   ";
			else ss[i] << "  ";
		}
		else {
			ss[i] << "    ";
		}
			
	}
	int vert_fac = 2;
	int hori_fac = 4;
	
	Matrix<char> tmp_board(size * vert_fac, size * hori_fac, ' ');
	for (int i = 0; i < size * size; ++i) {
		for (int j = 0; j < size * size; ++j) {
			if (is_adj(i, j)) {
				pair<int, int> cor1 = pos_decode(i);
				pair<int, int> cor2 = pos_decode(j);
				try {
					if (cor1.first == cor2.first) {
						for (int j = hori_fac*min(cor1.second, cor2.second)+1; j < hori_fac*max(cor1.second, cor2.second); ++j) {
							tmp_board[cor1.first*vert_fac][j] = '-';
						}
					}
					else if (cor1.second == cor2.second) {
						for (int i = vert_fac*min(cor1.first, cor2.first)+1; i < vert_fac*max(cor1.first, cor2.first); ++i) {
							tmp_board[i][cor1.second*hori_fac] = '|';
						}
					}
					else {
						if (abs(cor1.first - cor2.first) == 1 && abs(cor1.second - cor2.second) == 1) {
							pair<int, int> left = cor1, right = cor2;
							if (left.second > right.second)swap(left, right);
							if (left.first > right.first) {
								tmp_board[right.first * vert_fac + 1][left.second * hori_fac + 2] = '/';
							}
							else {
								tmp_board[left.first * vert_fac + 1][left.second * hori_fac + 2] = '\\';
							}
						}
						else {
							throw board_edge_exeption();
						}
						
					}
				}
				catch (const board_edge_exeption & e) {
					cout << e.what() << endl;
					cout << cor1.first << "," << cor1.second << " ";
					cout << cor2.first << "," << cor2.second << endl;
				}
			}
			
		}
	}

	

	for (int i = 0; i < size; ++i) {
		for (int j = 0; j < size; ++j) {
			if (is_piece(pos_encode(i, j))) {
				tmp_board[i * vert_fac][j * hori_fac] = in_pos(pos_encode(i, j));
			}
		}
	}

	/*
	cout << "<<<" << endl;
	cout << tmp_board << endl;
	cout << "<<<" << endl;
	*/

	for (int i = 0; i < vert_fac * size; ++i) {
		for (int j = 0; j < size * hori_fac; ++j) {
			ss[i+1] << tmp_board[i][j];
		}
	}

	for (int i = 1; i < 2 * size; ++i) {
		ss[i] << "    ";
		if (i & 1) {
			ss[i] << i / 2;
			if (i < 10)ss[i] << "   ";
			else ss[i] << "  ";
		}
		else {
			ss[i] << "    ";
		}

	}
	ss[size * 2 + 1] << "    ";
	for (int j = 0; j < size; ++j) {
		ss[size * 2 + 1] << (char)(j + 'A') << "   ";
	}

	//fresh stream
	for (int i = 0; i < ss.size(); ++i) {
		cout << ss[i].str() << endl;
	}


	return 0;
}


int Chess_Board::add_edge(int i1, int j1, int i2, int j2) {
	this->adj[pos_encode(i1, j1)][pos_encode(i2, j2)] = 
		this->adj[pos_encode(i2, j2)][pos_encode(i1, j1)] = 1;
	return 0;
}

bool Chess_Board::is_piece(int pos) {
	for (int i = 0; i < 4; ++i) {
		if (in_pos(pos) == piece[i])
			return true;
	}
	return false;
}

char& Chess_Board::in_pos(int pos) {
	pair<int, int> cor = pos_decode(pos);
	//cout << "[] checking " << pos << " " << bat[cor.first][cor.second] << endl;
	return bat[cor.first][cor.second];
}