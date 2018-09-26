import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;
import javafx.util.Pair;

public class Revedges {
	static class Task {
		public static final String INPUT_FILE = "revedges.in";
		public static final String OUTPUT_FILE = "revedges.out";

		int n;
		int m;
    int q;

		int d[][];
		int a[][];

    int[] querySource;
    int[] queryDest;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
								     INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
        q = sc.nextInt();

        d = new int[n + 1][n + 1];
				a = new int[n + 1][n + 1];
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= n; j++) {
              a[i][j] = 99999;
					}
				}

				for(int i = 0; i < m; i++){
					  int first = sc.nextInt();
						int second = sc.nextInt();
						if(a[first][second] == 1)
							 a[first][second] = 0;
						else{
							 a[first][second] = 0;
							 a[second][first] = 1;
					}
		}
       querySource = new int[q];
       queryDest = new int[q];

      for(int j = 0; j < q; j++){
      	querySource[j] = sc.nextInt();
      	queryDest[j] = sc.nextInt();
      }
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int[] result) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(
								OUTPUT_FILE));
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < q; i++) {
					sb.append(result[i]).append('\n');
				}
				bw.write(sb.toString());
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		private void compute() {
			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= n; j++)
					d[i][j] = a[i][j];
			for (int k = 1; k <= n; k++) {
				for (int i = 1; i <= n; i++) {
					for (int j = 1; j <= n; j++) {
						if (i == j) continue;
						if (d[i][j] > d[i][k] + d[k][j])
							d[i][j] = d[i][k] + d[k][j];
					}
				}
			}
		}

      public int[] finalResult(){
        int[] result = new int[q];
        for(int i = 0; i < q; i++){
						int source = querySource[i];
						int dest = queryDest[i];
						if(source == dest)
							result[i] = 0;
						else
              result[i] = d[source][dest];
            }
        return result;
      }

		public void solve() {
			readInput();
      compute();
			writeOutput(finalResult());
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
