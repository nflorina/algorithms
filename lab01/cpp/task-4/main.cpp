#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

class Task {
public:
	void solve() {
		read_input();
		print_output(fast_pow(base, exponent, mod));
	}

private:
	int base, exponent, mod;

	void read_input() {
		ifstream fin("in");
		fin >> base >> exponent >> mod;
		fin.close();
	}

	int fast_pow(int base, int exponent, int mod) {
		// TODO: Calculati (base ^ exponent) % mod in O(log exponent)
		
		// base^0 = 1
		if (!exponent)  {
			return 1;
		}

		int aux = 1;
		while (exponent != 1) {
			if (exponent % 2 == 0) {   // par
 				base = (1LL * base * base) % mod;
				exponent /= 2; 
			} else {                   // impar
				aux = (1LL * aux * base) % mod;
				exponent--;
			}
		}

		return (1LL * aux * base) % mod;
	}

	void print_output(int result) {
		ofstream fout("out");
		fout << result;
		fout.close();
	}
};

int main() {
	Task task;
	task.solve();
	return 0;
}
