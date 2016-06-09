package Graph;

import java.util.*;

public class Traversal {


  /**
   * DFS traversal
   */
  public static List<Integer> traverseDFS(Map<Integer, Set<Integer>> graph) {
    List<Integer> result = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();
    for (int vertex : graph.keySet()) {
      if (!visited.contains(vertex)) {
        dfsTraversal(graph, vertex, visited, result);
      }
    }
    return result;
  }

  private static void dfsTraversal(Map<Integer, Set<Integer>> graph, int vertex, Set<Integer>
      visited,
                            List<Integer> result) {
    visited.add(vertex);
    result.add(vertex);
    for (int adj : graph.get(vertex)) {
      if (!visited.contains(adj)) {
        dfsTraversal(graph, adj, visited, result);
      }
    }
  }

  /**
   * BFS traversal
   */
  public static List<Integer> traverseBFS(Map<Integer, Set<Integer>> graph) {
    List<Integer> result = new ArrayList<>();
    Queue<Integer> q = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();

    for (int vertex : graph.keySet()) {
      if (!visited.contains(vertex)) {
        q.offer(vertex);
        while (!q.isEmpty()) {
          int curr = q.poll();
          result.add(curr);
          visited.add(curr);
          for (int adj : graph.get(curr)) {
            if (!visited.contains(adj)) {
              q.offer(adj);
            }
          }
        }
      }
    }
    return result;
  }
}
