package Graph;

public class Main {

  public static void main(String[] args) {
    int[][] nums = {
        {0, 0, 0, 0, 1, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 0, 0},
        {0, 1, 0, 0, 0, 0},
        {0, 1, 0, 0, 0, 0},
        {1, 0, 1, 0, 0, 0}
    };
    Graph graph = new Graph(nums);

    System.out.println("DFS Traversal: " + graph.traverseDFS());
    System.out.println("BFS Traversal: " + graph.traverseBFS() + "\n");
    System.out.println("DFS topological sort: " + graph.topoDFS());
    System.out.println("BFS topological sort: " + graph.topoBFS() + "\n");
    System.out.println("DFS has cycle: " + graph.hasCycleDFS());
    System.out.println("BFS has cycle: " + graph.hasCycleBFS() + "\n");
  }
}
