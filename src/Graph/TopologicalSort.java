package Graph;

import java.util.*;

public class TopologicalSort {

  /**
   * DFS topological sort
   */
  public static List<Integer> topoDFS(Map<Integer, Set<Integer>> graph) {
    List<Integer> result = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    for (int vertex : graph.keySet()) {
      if (!visited.contains(vertex)) {
        dfs(graph, vertex, visited, result);
      }
    }
    return result;
  }

  private static void dfs(Map<Integer, Set<Integer>> graph, int vertex, Set<Integer> visited,
                   List<Integer> result) {
    visited.add(vertex);
    for (int adj : graph.get(vertex)) {
      if (!visited.contains(adj)) {
        dfs(graph, adj, visited, result);
      }
    }
    result.add(0, vertex);
  }

  /**
   * BFS topological sort
   */
  public List<Integer> topoBFS(Map<Integer, Set<Integer>> graph) {
    List<Integer> result = new LinkedList<>();
    Queue<Integer> q = new LinkedList<>();
    Map<Integer, Integer> indegree = new HashMap<>();

    // get the indegree of each vertex
    for (int vertex : graph.keySet()) {
      if (!indegree.containsKey(vertex)) {
        indegree.put(vertex, 0);
      }
      for (int adj : graph.get(vertex)) {
        if (!indegree.containsKey(adj)) {
          indegree.put(adj, 1);
        }
        else {
          indegree.put(adj, indegree.get(adj) + 1);
        }
      }
    }

    // put the vertex with 0 indegree to the queue
    for (int vertex : graph.keySet()) {
      if (indegree.get(vertex) == 0) {
        q.offer(vertex);
      }
    }

    // topological sort
    while (!q.isEmpty()) {
      int curr = q.poll();
      result.add(curr);

      for (int adj : graph.get(curr)) {
        indegree.put(adj, indegree.get(adj) - 1);
        if (indegree.get(adj) == 0) {
          q.offer(adj);
        }
      }
    }
    return result;
  }
}
