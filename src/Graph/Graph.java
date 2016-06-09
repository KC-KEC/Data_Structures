package Graph;

import java.util.*;

public class Graph {

  public static Map<Integer, Set<Integer>> getDirectedGraph(int[][] nums) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      graph.put(i, new HashSet<>());
    }

    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums.length; j++) {
        if (nums[i][j] == 1) {
          graph.get(i).add(j);
        }
      }
    }

    return graph;
  }


  public static Map<Integer, Set<Integer>> getUndirectedGraph(int[][] nums) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      graph.put(i, new HashSet<>());
    }

    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums.length; j++) {
        if (nums[i][j] == 1) {
          graph.get(i).add(j);
          graph.get(j).add(i);
        }
      }
    }

    return graph;
  }

}
