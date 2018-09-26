import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeSet;
import javafx.util.Pair;

public class Rtd {
	static class Task {
		public static final String INPUT_FILE = "rtd.in";
		public static final String OUTPUT_FILE = "rtd.out";
                int n, m, sx, sy, fx, fy, k;
                int[] costuri = new int[7];
                int[] blocat_x;
                int[] blocat_y;
                Nod source;

    class Nod implements Comparable<Nod> {
			public int cost;
			public Pair<Integer, Integer> node;
      int down, top, front, back, left, right;
      public Pair<Integer, Integer> parinte;
			public Nod(Pair<Integer, Integer> _nod, int _cost) {
				cost = _cost;
				node = _nod;
			}
      public Nod(Pair<Integer, Integer> _nod, Pair<Integer, Integer> _parinte, int _cost,
      int down, int top, int front, int back, int left, int right) {
				cost = _cost;
				node = _nod;
        parinte = _parinte;
        this.down = down;
        this.top = top;
        this.front = front;
        this.back = back;
        this.left = left;
        this.right = right;
			}
      public Nod(){
          this(new Pair(0,0), new Pair(0, 0), 0, 0, 0, 0, 0, 0, 0);
      }
      @Override
			public int compareTo(Nod other) {
          if (cost == other.cost)
              if( node.getKey() == other.node.getKey())
                  return Integer.compare(node.getValue(), other.node.getValue());
              else
                  return Integer.compare(node.getKey(), other.node.getKey());
          return Integer.compare(cost, other.cost);
			}
		}

               public Nod moveRight(Nod start){
                   int down = start.right;
                   int top = start.left;
                   int front = start.front;
                   int back = start.back;
                   int left = start.down;
                   int right = start.top;
                   return new Nod(new Pair(start.node.getKey(), start.node.getValue() + 1),
									  							start.node, start.cost + start.right,
                                  down, top, front, back, left, right);
               }

               public Nod moveLeft(Nod start){
                   int down = start.left;
                   int top = start.right;
                   int front = start.front;
                   int back = start.back;
                   int left = start.top;
                   int right = start.down;
                   return new Nod(new Pair(start.node.getKey(), start.node.getValue() - 1),
									 								start.node, start.cost + start.left,
                                  down, top, front, back, left, right);
               }

               public Nod moveBack(Nod start){
                   int down = start.back;
                   int top = start.front;
                   int front = start.down;
                   int back = start.top;
                   int left = start.left;
                   int right = start.right;
                   return new Nod(new Pair(start.node.getKey() - 1, start.node.getValue()),
									  							start.node, start.cost + start.back,
                                  down, top, front, back, left, right);
               }

               public Nod moveFront(Nod start){
                   int down = start.front;
                   int top = start.back;
                   int front = start.top;
                   int back = start.down;
                   int left = start.left;
                   int right = start.right;
                   return new Nod(new Pair(start.node.getKey() + 1, start.node.getValue()),
									 								start.node, start.cost + start.front,
                                  down, top, front, back, left, right);

               }


               public ArrayList<Nod> createNeigh(Nod start){
                   int sx = start.node.getKey();
                   int sy = start.node.getValue();
                   ArrayList<Nod> vecini = new ArrayList<>();
                    if( sx == 1 && sy == 1){
                       if(!((new Pair(sx, sy+1)).equals(start.parinte))) vecini.add(moveRight(start));
                       if(!((new Pair(sx+1, sy)).equals(start.parinte))) vecini.add(moveFront(start));
                   } else if(sx == n && sy == m){
                       if(!((new Pair(sx-1, sy)).equals(start.parinte))) vecini.add(moveBack(start));
                       if(!((new Pair(sx, sy-1)).equals(start.parinte))) vecini.add(moveLeft(start));
                   } else if(sx == 1 && sy == m){
                       if(!((new Pair(sx, sy-1)).equals(start.parinte))) vecini.add(moveLeft(start));
                       if(!((new Pair(sx+1, sy)).equals(start.parinte))) vecini.add(moveFront(start));
                   } else if( sx == n && sy == 1){
                       if(!((new Pair(sx-1, sy)).equals(start.parinte))) vecini.add(moveBack(start));
                       if(!((new Pair(sx, sy+1)).equals(start.parinte))) vecini.add(moveRight(start));
                   } else if(sx == 1 && sy > 1 && sy < m){
                       if(!((new Pair(sx, sy-1)).equals(start.parinte))) vecini.add(moveLeft(start));
                       if(!((new Pair(sx, sy+1)).equals(start.parinte))) vecini.add(moveRight(start));
                       if(!((new Pair(sx+1, sy)).equals(start.parinte))) vecini.add(moveFront(start));
                   } else if(sx == n && sy > 1 && sy < m){
                       if(!((new Pair(sx, sy-1)).equals(start.parinte))) vecini.add(moveLeft(start));
                       if(!((new Pair(sx, sy+1)).equals(start.parinte))) vecini.add(moveRight(start));
                       if(!((new Pair(sx-1, sy)).equals(start.parinte))) vecini.add(moveBack(start));
                   } else if(sy == 1 && sx > 1 && sx < n){
                       if(!((new Pair(sx-1, sy)).equals(start.parinte))) vecini.add(moveBack(start));
                       if(!((new Pair(sx+1, sy)).equals(start.parinte))) vecini.add(moveFront(start));
                       if(!((new Pair(sx, sy+1)).equals(start.parinte))) vecini.add(moveRight(start));
                   } else if(sy == m && sx > 1 && sx < n){
                       if(!((new Pair(sx-1, sy)).equals(start.parinte))) vecini.add(moveBack(start));
                       if(!((new Pair(sx+1, sy)).equals(start.parinte))) vecini.add(moveFront(start));
                       if(!((new Pair(sx, sy-1)).equals(start.parinte))) vecini.add(moveLeft(start));
                   } else {
                        if(!((new Pair(sx-1, sy)).equals(start.parinte))) vecini.add(moveBack(start));
                        if(!((new Pair(sx+1, sy)).equals(start.parinte))) vecini.add(moveFront(start));
                        if(!((new Pair(sx, sy-1)).equals(start.parinte))) vecini.add(moveLeft(start));
                        if(!((new Pair(sx, sy+1)).equals(start.parinte))) vecini.add(moveRight(start));
                   }
                   ArrayList<Nod> finalList = new ArrayList<>(vecini);

                    for(Nod n : vecini){
                       for(int i = 0; i < k; i++){
                           if(n.node.getKey() == blocat_x[i] && n.node.getValue() == blocat_y[i]){
                               finalList.remove(n);
                           }
                        }
                   }
                   return finalList;
               }


               int getCost(ArrayList<Nod> d, Pair<Integer, Integer> y){
                   int cost = -1;
                   for(Nod nod : d){
                       if(nod.node.getKey() == y.getKey() && nod.node.getValue() == y.getValue())
                           cost = nod.cost;
                   }
                   return cost;
               }

               int getNodeIndex(ArrayList<Nod> vecini, Pair<Integer, Integer> pereche){
                   int result = 0, i = 0;
                   for(Nod n : vecini){
                       if(n.node.getKey() == pereche.getKey() && n.node.getValue() == pereche.getValue()){
                           result = i;
                           break;
                       }
                       i++;
                   }
                   return result;
               }

               Nod nodeToDelete(ArrayList<Nod> lista, Pair<Integer, Integer> pereche){
                   Nod res = new Nod();
                   for(Nod n : lista){
                       if(n.node.getKey() == pereche.getKey() && n.node.getValue() == pereche.getValue()){
                           res = n;
                       }
                   }
                   return res;
               }

 		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
                                            INPUT_FILE)));
			n = sc.nextInt();
		  m = sc.nextInt();
      sx = sc.nextInt();
      sy = sc.nextInt();
      fx = sc.nextInt();
      fy = sc.nextInt();
      k = sc.nextInt();
      for(int i = 1; i <= 6; i++){
          costuri[i] = sc.nextInt();
      }
      if(k != 0){
        blocat_x = new int[k];
        blocat_y = new int[k];
        for(int i = 0;  i < k; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            blocat_x[i] = a;
            blocat_y[i] = b;
        }
      }
      source = new Nod(new Pair(sx, sy), new Pair(sx, sy), costuri[1],
               costuri[1], costuri[6], costuri[5],
               costuri[2], costuri[4], costuri[3]);
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int result) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(
								OUTPUT_FILE));
				StringBuilder sb = new StringBuilder();
				sb.append(result).append('\n');
				bw.write(sb.toString());
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public static int INF = 0x3f3f3f3f;

		private ArrayList<Nod> getResult() {
    ArrayList<Nod> d = new ArrayList<>();
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= m; j++){
            if( i == sx && j == sy){
               d.add(source);
            } else {
              d.add(new Nod(new Pair(i,j),INF));
            }
			    }
        }
			TreeSet<Nod> q = new TreeSet<>();
			q.add(source);
			while (!q.isEmpty()) {
				Nod dnode = q.pollFirst();
        ArrayList<Nod> neigh = createNeigh(dnode);
				for (Nod e : neigh) {
					Pair<Integer, Integer> y = e.node;
					int c = e.cost;
					if (getCost(d, y) > getCost(d, dnode.node) + c - dnode.cost) {
						q.remove(nodeToDelete(d, y));
            Nod aux = new Nod(y,dnode.node, getCost(d, dnode.node) + c - dnode.cost,
					                    e.down, e.top, e.front, e.back, e.left, e.right);
            int replace = getNodeIndex(d, y);
            d.set(replace, aux);
						q.add(aux);
					}
				}
			}
        for(Nod n : d){
          if(n.cost == INF)
              n.cost = -1;
        }
      return d;
		}

		public void solve() {
			readInput();
      ArrayList<Nod> result = getResult();
      int res = -1;
      for( Nod a : result){
          if((a.node).equals(new Pair(fx, fy))){
            res = a.cost;
      }
    }
		 writeOutput(res);
		}
	}
	public static void main(String[] args) {
		new Task().solve();
	}
}
