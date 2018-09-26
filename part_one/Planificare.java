import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;


/*
 * obiectele de tip FinalResult vor fi un tuplu care
 * marcheaza costul total de lipsuri si numarul de concursuri
 */
class FinalResult {
	long L;
	int N;
	public FinalResult(long L, int N) {
		this.L = L;
		this.N = N;
	}
}

public class Planificare {
	public static final String INPUT_FILE = "planificare.in";
	public static final String OUTPUT_FILE = "planificare.out";
	int p, d, b;
	int[] v;
	long[][] dp;
	int[][] count;

	public void readInput() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
			String first = reader.readLine();
			StringTokenizer f = new StringTokenizer(first, " ");
			p = Integer.parseInt(f.nextToken());
			d = Integer.parseInt(f.nextToken());
			b = Integer.parseInt(f.nextToken());
			v = new int[p + 1];
			dp = new long[p + 1][p + 1];
			count = new int[p + 1][p + 1];
			String line = reader.readLine();
			/*
			 * la citire se realizeaza popularea cu valori initiale pentru matrice;
			 * dp[][] are pe diagonala pierderile initiale (raportate la durata unui
			 * concurs si perioada din vectorul intial, ca si cum ar fi
			 * singure intr-un concurs ) si in partea superiora valori maxime;
			 * count[][] are pe diagonala principala 1 pentru ca incepem cu un singur
			 * concurs
			 */
			for (int i = 1; i <= p; i++) {
				v[i] = Integer.parseInt(line);
				dp[i][i] = (long) Math.pow(d - v[i], 3);
				for (int j = i + 1; j <= p; j++) {
					dp[i][j] = Long.MAX_VALUE;
				}
				count[i][i] = 1;
				line = reader.readLine();
			}
			dp[p][p] = 0;
			reader.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void writeOutput(FinalResult result) {
		try {
			PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
			pw.printf("%d %d\n", result.L, result.N);
			pw.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	* algoritmul presupune formularea ca pe o problema de tip subinterval [i,j];
	* utilizez formatul PODM
	* gasirea optimului pentru [i,j] presupune gasirea combinatiilor de 2
	* subintervale care ar fi putut genera solutie pentru probleme [i,j].
	* aleg partea care minimizeaza lipsa pentru [i,j]
	*/
	public FinalResult getSolution() {
		for (int len = 2; len <= p; ++len) {
			for (int i = 1; i + len - 1 <= p; ++i) {
				int j = i + len - 1;
				long suma = 0;
				for (int m = i; m <= j; m++) {
					suma += v[m];
				}
				long total = suma + (j - i) * b;
				if (total <= d) {
					count[i][j] = 1;
					if (j == p) {
						dp[i][j] = 0;
					} else {
						dp[i][j] = (long) Math.pow(d - total, 3);
					}
				} else {
					for (int k = i; k < j; k++) {
						if (dp[i][j] > dp[i][k] + dp[k + 1][j]) {
							dp[i][j] = dp[i][k] + dp[k + 1][j];
							count[i][j] = count[i][k] + count[k + 1][j];
						}
					}
				}
			}
		}
		System.out.println(dp[1][p] + " " + count[1][p]);
		return new FinalResult(dp[1][p], count[1][p]);
	}
	public void solve() {
		readInput();
		writeOutput(getSolution());
	}
	public static void main(String[] args) {
		new Planificare().solve();
	}
}
