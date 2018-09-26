#include <fstream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

const int kNmax = 100005;

class Task {
 public:
	void solve() {
		read_input();
		print_output(get_result());
	}

 private:
	int n;
	int m;
	int source;
	vector<int> adj[kNmax];

	void read_input() {
		ifstream fin("in");
		fin >> n >> m >> source;
		for (int i = 1, x, y; i <= m; i++) {
			fin >> x >> y;
			adj[x].push_back(y);
			adj[y].push_back(x);
		}
		fin.close();
	}

	vector<int> get_result() {
		vector<int> d(n + 1, -1);
    vector<bool> viz(n + 1, false);

    d[source] = 0;
    queue<int> myQueue;
    myQueue.push(source);
    viz[source] = true;

    while(!myQueue.empty()){
      int nodeCurent = myQueue.front();
      myQueue.pop();
      for( int i = 0; i < adj[nodeCurent].size(); i++ ){
        int nodeNext = adj[nodeCurent][i];
        if(viz[nodeNext] == false){
          myQueue.push(nodeNext);
          viz[nodeNext] = true;
          d[nodeNext] = d[nodeCurent] + 1;
        }
      }
    }

		/*
		TODO: Faceti un BFS care sa construiasca in d valorile d[i] = numarul
		minim de muchii de parcurs de la nodul source la nodul i.
		d[source] = 0
		d[x] = -1 daca nu exista drum de la source la x.
		*******
		ATENTIE: nodurile sunt indexate de la 1 la n.
		*******
		*/
		return d;
	}

	void print_output(vector<int> result) {
		ofstream fout("out");
		for (int i = 1; i <= n; i++) {
			fout << result[i] << (i == n ? '\n' : ' ');
		}
		fout.close();
	}
};

int main() {
	Task *task = new Task();
	task->solve();
	delete task;
	return 0;
}
