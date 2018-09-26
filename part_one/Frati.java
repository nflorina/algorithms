import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * obiectele de tip Tuple retin alegerile din jocuri
 */
class Tuple {
	int jocuri;
	int benzi;
	public Tuple(int jocuri, int benzi) {
		this.jocuri = jocuri;
		this.benzi = benzi;
	}
	public void setBenzi(int benzi) {
		this.benzi = benzi;
	}

	public void setJocuri(int jocuri) {
		this.jocuri = jocuri;
	}
	@Override
	public String toString() {
		return "(" + this.jocuri + " " + this.benzi + ")";
	}
}

/*
 * comparatorul asociat alegerilor lui Jon;
 * scopul este maximizarea nr de jocuri
 */
class CompGlobalJon implements Comparator<Tuple> {
	@Override
	public int compare(Tuple a, Tuple b) {
		int suma1 = a.jocuri + a.benzi;
		int suma2 = b.jocuri + b.benzi;
		if (suma1 > suma2) {
			return -1;
		}
		if (suma1 == suma2) {
			if (a.jocuri > b.jocuri) {
				return -1;
			}
			if (a.jocuri == b.jocuri) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}
}
/*
 * comparatorul asociat alegerilor lui Sam;
 * scopul este maximizarea nr de setBenzi
 */
class CompGlobalSam implements Comparator<Tuple> {
	@Override
	public int compare(Tuple a, Tuple b) {
		int suma1 = a.jocuri + a.benzi;
		int suma2 = b.jocuri + b.benzi;
		if (suma1 > suma2) {
			return -1;
		}
		if (suma1 == suma2) {
			if (a.benzi > b.benzi) {
				return -1;
			}
			if (a.benzi == b.benzi) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}
}
public class Frati {
	public static final String INPUT_FILE = "frati.in";
	public static final String OUTPUT_FILE = "frati.out";
	int n;
	LinkedList<Tuple> concurs = new LinkedList<>();
	/*
	 * se citesc din fisier numarul de alegeri viitoare;
	 * urmatoarele vor forma tupluri de tip joc-setBenzi
	 */
	public void readInput() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
			n = Integer.parseInt(reader.readLine());
			String line = reader.readLine();
			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, " ");
				Tuple t = new Tuple(0, 0);
				t.setJocuri(Integer.parseInt(st.nextToken()));
				t.setBenzi(Integer.parseInt(st.nextToken()));
				concurs.add(t);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * se scriu in fisier valorile campurilor unui obiect tuplu
	 * care contine sumele finale asociate castigului fiecarui jucator
	 */
	public void writeOutput(Tuple result) {
		try {
			PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
			pw.printf("%d %d\n", result.jocuri, result.benzi);
			pw.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * citesce si scrie in fisier solutia finala
	 */
	public void solve() {
		readInput();
		writeOutput(choose());
	}
	/*
	 * metoda va sorta concursurile in functie de cele doua cazuri:
	 * primul jucator doreste maximizarea numarului de jocuri, al
	 * doilea maximizarea nr de benzi;
	 * fiecare va face o mutare - prima din lista lui de favorite -
	 * si aceasta va fi eliminata din ambele liste ale jucatorilor
	 */
	public Tuple choose() {
		int chJon = 0, chSam = 0;
		concurs.sort(new CompGlobalJon());
		LinkedList<Tuple> clonaJon = new LinkedList<>(concurs);

		concurs.sort(new CompGlobalSam());
		LinkedList<Tuple> clonaSam = new LinkedList<>(concurs);

		boolean step = true;
		Tuple a;
		/*
		* fiind eliminari realizate pentru ambele liste de favorite,
		* ne putem raporta la oricare din acestea pentru marcare final
		*/
		while (!clonaJon.isEmpty()) {
			if (step == true) {
				a = clonaJon.poll();
				chJon += a.jocuri;
				clonaSam.remove(a);
				step = false;
			} else {
				a = clonaSam.poll();
				chSam += a.benzi;
				clonaJon.remove(a);
				step = true;
			}
		}
		System.out.println(chJon + " " + " " + chSam);
		return new Tuple(chJon, chSam);
	}
	public static void main(String[] args) {
		new Frati().solve();
	}
}
