#include <iostream>
#include <iomanip>
#include <fstream>
#include <vector>
#include <cmath>

using namespace std;
const double eps = 1e-3;

class Task {
public:
	void solve() {
		read_input();
		print_output(compute_sqrt());
	}

private:
	double n;

	void read_input() {
		ifstream fin("in");
		fin >> n;
		fin.close();
	}

	double compute_sqrt() {
		/*
		TODO: Calculati sqrt(n) cu o precizie de 0.001
		Precizie de 10^-x = |rezultatul vostru - rezultatul corect| <= 10^-x
		*/

		int steps = 30; // limitez prin numarare de pasi

		double left = 0.0, right = n;
		if (n < 1) right = 1.0;

		while (steps--) {
			double middle = (left + right) * 0.5;
			double diff = n - middle * middle;

			if (fabs(diff) <= eps) return middle;

			if (diff > 0) {
				left = middle;
			} else {
				right = middle;
			}
		}

		return 0.0;
	}

	void print_output(double result) {
		ofstream fout("out");
		fout << setprecision(4) << fixed << result;
		fout.close();
	}
};

int main() {
	Task task;
	task.solve();
	return 0;
}
