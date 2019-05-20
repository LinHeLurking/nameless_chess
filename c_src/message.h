#pragma once
#include <iostream>
using namespace std;

class Msg {
private:
	int prpt_cnt = 0;
	int state = 0;
public:
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
	
};
