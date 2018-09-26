import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Numaratoare {

	public static final String INPUT_FILE = "numaratoare.in";
	public static final String OUTPUT_FILE = "numaratoare.out";
	Integer[] numbers;
	int s, n, i;
	LinkedList<LinkedList<Integer>> res = new LinkedList<>();

	public void readInput() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
			s = Integer.parseInt(reader.readLine());
			n = Integer.parseInt(reader.readLine());
			i = Integer.parseInt(reader.readLine());
			reader.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		numbers = new Integer[s];
		for (int j = 0; j < s; j++) {
			numbers[j] = j + 1;
		}
	}
    public void find(int sum, LinkedList<Integer> r, int i) {
		    if (sum < 0) {
			       return;
    }
		if (sum == 0 && n == r.size()) {
			LinkedList<Integer> aux = (LinkedList<Integer>) r.clone();
			Collections.reverse(aux);
			res.add(aux);
			return;
		}
		while (i < numbers.length && sum - numbers[i] >= 0) {
			r.add(numbers[i]);
			find(sum - numbers[i], r, i);
			i++;
			r.removeLast();
		}
	}
	public void getCombination(int sum) {
		LinkedList<Integer> r = new LinkedList<>();
		find(sum, r, 0);
	}

	public void writeOutput() throws FileNotFoundException {
		System.out.println(s + " " + n + " " + i);
		System.out.println(res.size());

		PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
		if (i >= res.size()) {
			pw.printf("-");
			pw.close();
		} else {
			for (LinkedList<Integer> x : res) {
				System.out.println(x);
			}
			LinkedList<Integer> r = res.get(i);
			pw.printf("%d=%d", s, r.get(0));
			r.remove(0);
			r.forEach((element) -> {
				pw.printf("+%d", element);
			});
			pw.close();
		}

	}

	public void solve() throws FileNotFoundException {
		readInput();
		getCombination(s);
		writeOutput();
	}

	public static void main(String[] args) throws FileNotFoundException {
		new Numaratoare().solve();
	}

}
