package Graph;

import java.util.Map;
import java.util.Set;

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

    Map<Integer, Set<Integer>> graph = Graph.getDirectedGraph(nums);

  }
}
