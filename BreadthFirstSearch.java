import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
public class BreadthFirstSearch {

  public static class Graph {

    Map<Integer, Set<Integer>> g;

    public Graph(int size) {
      g = new HashMap<Integer, Set<Integer>>();
      for(int i = 1; i <= size; i++) {
        g.put(i, new HashSet<Integer>());
      }
    }

    public void addEdge(int a, int b) {
      g.get(a).add(b);
      g.get(b).add(a);
    }

    public int[] shortestReach(int start) {
      int[] dist = new int[g.size()+1];
      Arrays.fill(dist, -1);
      Set<Integer> visited=new HashSet<Integer>();
      Queue<Integer> nodesToVisit = new LinkedList<Integer>();
      nodesToVisit.add(start);

      dist[start] = 0;

      while( nodesToVisit.size() > 0) {

        Integer currnode = nodesToVisit.poll();
        visited.add(currnode);

        for( Integer i : g.get(currnode)) {
          if( !visited.contains(i)){
            nodesToVisit.add(i);
            visited.add(i);
            dist[i] = dist[currnode] + 1;
          }
        }
      }

      return dist;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int queries = scanner.nextInt();

    for (int query = 0;  query < queries; query++){
      //Creating the graph
      Integer edges = scanner.nextInt();
      Graph graph   = new Graph(edges);
      int m         = scanner.nextInt();

      // reading and setting edges
      for( int i = 0; i < m; i++) {
        int frst  = scanner.nextInt();
        int sec = scanner.nextInt();

        // adding each edge to the graph
        graph.addEdge(frst,sec);
      }

      // Find shortest reach from node start
      int start = scanner.nextInt();
      int[] dist = graph.shortestReach(start);

      for( int i = 1; i < dist.length ; i++){
        if( i != start && dist[i] > -1) {
          System.out.printf("%d ",dist[i] * 6);
        } else if( i != start && dist[i] == -1) {
          System.out.printf("%d ",dist[i]);
        }
      }

      System.out.printf("\n");
    }

    scanner.close();
  }
}
