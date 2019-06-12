#pragma once
#include <vector>
#include <iostream>

using namespace std;

template <typename type>
class Matrix {
	friend ostream& operator<<(ostream& os, const Matrix& mat) {
		for (int i = 0; i < mat.row; ++i) {
			for (int j = 0; j < mat.col; ++j) {
				cout << mat[i][j] << " ";
			}
			cout << endl;
		}
		return os;
	}
protected:
	vector<vector<type>> mat;
	
	
public:
	int row, col;
	vector<type>& operator[](int i) {
		return this->mat[i];
	}
	vector<type> operator[](int i) const {
		return this->mat[i];
	}

	Matrix() {}

	Matrix(int m,int n) {
		this->row = m;
		this->col = n;
		this->mat.resize(m);
		for (int i = 0; i < m; ++i) {
			this->mat[i].resize(n);
		}

	}
	
	Matrix(int m, int n, type x) {
		this->row = m;
		this->col = n;
		this->mat.resize(m);
		for (int i = 0; i < m; ++i) {
			this->mat[i].resize(n);
			for (auto& j : mat[i]) {
				j = x;
			}
		}
	}

};

