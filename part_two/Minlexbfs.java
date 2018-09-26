import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Minlexbfs {
	static class Task {
		public static final String INPUT_FILE = "minlexbfs.in";
		public static final String OUTPUT_FILE = "minlexbfs.out";
    public static final int NMAX = 100005;

		int n;
		int m;
    int source = 999999;
		ArrayList<Integer> adj[] = new ArrayList[NMAX];
		private void readInput() {
			try {
          Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
          n = sc.nextInt();
			    m = sc.nextInt();
				for (int i = 1; i <= n; i++)
					adj[i] = new ArrayList<>();
				for (int i = 1; i <= m; i++) {
					int x, y;
					x = sc.nextInt();
					y = sc.nextInt();
          if(x < source) source = x;
          if(y < source ) source = y;
					adj[x].add(y);
					adj[y].add(x);
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int[] result) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE)));
				for (int i = 0; i < n; i++) {
					pw.printf("%d%c", result[i], i == n ? '\n' : ' ');
				}
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int[] getResult() {
			boolean[] visited = new boolean[n + 1];
      int[] result = new int[n + 1];
      int j = 0;
			LinkedList<Integer> q= new LinkedList<>();
			q.add(source);
			visited[source] = true;
			while(!q.isEmpty()){
				source = q.poll();
        result[j++] = source;
        Collections.sort(adj[source]);
				for(int i = 0; i < adj[source].size(); i++){
					int suc = adj[source].get(i);
					if (visited[suc] == false){
              q.add(suc);
              visited[suc] = true;
          }
				}
			}
			return result;
		}

		public void solve() {
			readInput();
			writeOutput(getResult());
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
