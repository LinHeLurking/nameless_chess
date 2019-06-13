#pragma once
#include <iostream>
#include <sstream>
using namespace std;

/*
	Use "msg<<any_string;" to print messages
	Treat msg just like std::cout
	A string begins with '*' would be delayed
	A string begins with '[' would be marked a counter label
*/

class Msg {
private:
	int prpt_cnt = 0;
	int state = 0;
	stringstream ss;
public:
	void reset() {
		state = 0;
		prpt_cnt = 0;
		ss.str() = "";
	}

	template <typename type>
	Msg& operator<<(type i) {
		cout << i;
		return *this;
	}

	
	Msg& operator<<(const char *s) {
		if (s[0] == '[') {
			state = 1;
			cout << s[0] << prpt_cnt++ << s + 1;
		}
		else if (s[0] == '*') {
			ss << s;
		}
		else {
			if (!state)cout << ">>>";
			cout << s;
		}
		return *this;
	}

	
	Msg& operator<<(std::ostream& (*f)(std::ostream&)) {
		cout << f;
		state = 0;
		return *this;
	}

	void refresh() {
		cout << ss.str();
		ss.str("");
	}
	
};
