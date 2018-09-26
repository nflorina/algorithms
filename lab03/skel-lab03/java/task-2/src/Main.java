import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	static class Result {
		int len;
		int[] subsequence;

		public Result() {
			len = 0;
			subsequence = null;
		}
	}

	static class Task {
		public final static String INPUT_FILE = "in";
		public final static String OUTPUT_FILE = "out";

		int n, m;
		int[] v;
		int[] w;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				n = sc.nextInt();
				m = sc.nextInt();

				v = new int[n + 1]; // Adaugare element fictiv - indexare de
									// la 1.
				for (int i = 1; i <= n; i++) {
					v[i] = sc.nextInt();
				}

				w = new int[m + 1]; // Adaugare element fictiv - indexare de
									// la 1.
				for (int i = 1; i <= m; i++) {
					w[i] = sc.nextInt();
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(Result result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d\n", result.len);
				if (result.subsequence != null) {
					for (int i = 0; i < result.len; i++) {
						pw.printf("%d ", result.subsequence[i]);
					}
				}
				pw.printf("\n");
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private Result getResult() {
			Result result = new Result();
                        /*result.len = getResult(v, w, v.length - 1, w.length - 1);
                        return result;*/
                        
                        int[][] dp = new int[v.length + 1][w.length + 1];
                        dp[0][0] = 0;
                        for(int i = 0; i < v.length; i++){
                            for(int j = 0; j < w.length; j++){
                                if(v[i] == w[j])
                                    dp[i + 1][j + 1] = dp[i][j] + 1;
                                else
                                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j+1]);
                            }
                            result.len = dp[v.length ][w.length ] - 1;
                        }
                                 
                        int[] res = new int[dp[v.length][w.length] + 1];
 
                        int a = 0;
                        int b = v.length;
                        int c = w.length;
                        
                        while( b != 0 && c != 0){
                            if(dp[b][c] == dp[b-1][c])
                                b--;
                            else if(dp[b][c] == dp[b][c-1])
                                c--;
                            else{
                                res[a] = v[b-1];
                                b--;
                                c--;
                                a++;
                            }
                        }
                               
                            for(int i = 0, j = res.length - 3; i < j; i++, j--){
                                int temp = res[i];
                                res[i] = res[j];
                                res[j] = temp;
                            }
                            
                            
                            result.subsequence = res;
                        
                        return result;      
		}
                
                /*private int getResult(int[] v, int[] w, int n, int m){
                    if(m == 0 || n == 0)
                        return 0;
                    if(v[n - 1] == w[m - 1])
                        return 1 + getResult(v, w, n - 1, m - 1);
                    else
                        return Math.max(getResult(v, w, n - 1, m), getResult(v, w, n, m - 1));
                    
                }*/
                
		public void solve() {
			readInput();
			writeOutput(getResult());
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
