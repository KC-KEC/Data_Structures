package Graph;

import java.util.*;

/**
 * HamiltonianCycle, visit each vertex only once.
 *
 * Backtracking Problem
 */
public class HamiltonianCycle {

  public static List<Integer> hamiltonCycle(Map<Integer, Set<Integer>> graph) {
    List<Integer> result = new ArrayList<>();
    int[] visited = new int[graph.size()];
    result.add(0);
    visited[0] = 1;
    backtracking(graph, visited, 0, result);
    result.add(0);
    return result;
  }

  private static boolean backtracking(Map<Integer, Set<Integer>> graph, int[] visited, int v,
                                   List<Integer> result) {
    if (result.size() == graph.size()) {
      if (graph.get(result.get(result.size() - 1)).contains(result.get(0))) {
        return true;
      }
      return false;
    }

    for (int adj : graph.get(v)) {
      if (visited[adj] == 0) {
        result.add(adj);
        visited[adj] = 1;
        if (backtracking(graph, visited, adj, result)) {
          return true;
        }
        visited[adj] = 0;
        result.remove(result.size() - 1);
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int[][] edges = {
        {0, 1},
        {0, 3},
        {1, 2},
        {1, 3},
        {1, 4},
        {2, 4},
        {3, 4}
    };

    int[][] edges2 = {
        {0, 1},
        {0, 3},
        {1, 2},
        {1, 3},
        {1, 4},
        {2, 4}
    };

    Map<Integer, Set<Integer>> graph = new HashMap<>();
    for (int[] edge : edges2) {
      graph.putIfAbsent(edge[0], new HashSet<>());
      graph.putIfAbsent(edge[1], new HashSet<>());

      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }

    System.out.println(hamiltonCycle(graph));
  }
}
