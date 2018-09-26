#include <bits/stdc++.h>
using namespace std;
	
int solve(int n, int k, vector< pair<int, int> > &v) {
	sort(v.begin(), v.end(), [](const pair<int, int> &a,  const pair<int, int> &b) {
		return a.first - a.second < b.first - b.second;
	});

	int cnt = 0;
	int sol = 0;

	for (auto &p : v) {
		if (cnt < k || p.first <= p.second) {
			++cnt;
			sol += p.first;
		} else {
			sol += p.second;
		}
	}

	return sol;
}
int main() {
	// freopen("in", "r", stdin);

	int n, k;
	vector< pair<int, int> > v;

	cin >> n >> k;
	v.resize(n);
	for (int i = 0; i < n; ++i) {
		cin >> v[i].first;
	}
	for (int i = 0; i < n; ++i) {
		cin >> v[i].second;
	}

	cout << solve(n, k, v) << "\n";


	return 0;
}