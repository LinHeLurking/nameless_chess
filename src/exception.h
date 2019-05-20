#pragma once
#include <exception>
using namespace std;
class board_edge_exeption : public exception {
public:
	const char* what() const throw() {
		return "board edge initialization error";
	}
};