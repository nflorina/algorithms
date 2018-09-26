import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	static class Task {
		public final static String INPUT_FILE = "in";
		public final static String OUTPUT_FILE = "out";

		int n, S;
		int[] v;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				n = sc.nextInt();
				S = sc.nextInt();
				v = new int[n + 1];
				for (int i = 1; i <= n; i++) {
					v[i] = sc.nextInt();
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int getResult() {
			// TODO: Aflati numarul minim de monede ce poate fi folosit pentru a
			// obtine suma S. Tipurile monedelor sunt stocate in vectorul v, de
			// dimensiune n.
                        int[] dp = new int[S + 1];
                        dp[0] = 0;
                        for(int i = 1; i <= S; i++){
                            dp[i] = Integer.MAX_VALUE;
                        }

                        for(int i = 1; i <= S; i++){
                            for(int j = 0; j < v.length; j++){
                                if(v[j] <= i){
                                    if(dp[i - v[j]] != Integer.MAX_VALUE && dp[i - v[j]] + 1 < dp[i])
                                        dp[i] = dp[i - v[j]] + 1;
                                }
                            }
                        }
			return dp[S] != Integer.MAX_VALUE ? dp[S] : -1;
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
