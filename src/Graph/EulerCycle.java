package Graph;

import java.util.*;

/**
 * Euler Cycle, visit each edge (not vertex) only once.
 *  (Vertex could be visited multiple times)
 */
public class EulerCycle {

  /* Euler Cycle for Directed Graph */
  /*
    Condition:
      1. Every vertex has the same indegree and outdegree
      2. All vertices with non-zero degrees belong to a single strongly connected component
   */
  public static boolean hasEulerCycle_Directed(Map<Integer, Set<Integer>> graph) {

    return isSCC(graph) && hasSameDegree(graph);
  }

  private static boolean isSCC(Map<Integer, Set<Integer>> graph) {
    if (graph == null && graph.isEmpty()) {
      throw new IllegalArgumentException();
    }

    int[] visited = new int[graph.size()];
    dfs(0, visited, graph);
    for (int tmp : visited) {
      if (tmp == 0) {
        return false;
      }
    }

    Arrays.fill(visited, 0);
    dfs(0, visited, reversed(graph));
    for (int tmp : visited) {
      if (tmp == 0) {
        return false;
      }
    }

    return true;
  }

  private static void dfs(int v, int[] visited, Map<Integer, Set<Integer>> graph) {
    if (visited[v] == 1) {
      return;
    }

    visited[v] = 1;
    for (int adj : graph.get(v)) {
      dfs(adj, visited, graph);
    }
  }

  private static Map<Integer, Set<Integer>> reversed(Map<Integer, Set<Integer>> graph) {
    Map<Integer, Set<Integer>> reversedGraph = new HashMap<>();

    for (int v : graph.keySet()) {
      for (int adj : graph.get(v)) {
        reversedGraph.putIfAbsent(adj, new HashSet<>());
        reversedGraph.get(adj).add(v);
      }
    }

    return reversedGraph;
  }

  private static boolean hasSameDegree(Map<Integer, Set<Integer>> graph) {

    Map<Integer, Integer> indegree = new HashMap<>();
    Map<Integer, Integer> outdegree = new HashMap<>();

    for (int v : graph.keySet()) {
      for (int adj : graph.get(v)) {
        indegree.put(adj, indegree.getOrDefault(adj, 0) + 1);
        outdegree.put(v, outdegree.getOrDefault(v, 0) + 1);
      }
    }

    for (int v : indegree.keySet()) {
      if (!outdegree.containsKey(v) || indegree.get(v) != outdegree.get(v)) {
        return false;
      }
    }

    return true;
  }


  /* Euler Cycle for Undirected Graph */
  /*
    Condition:
      1. All vertices with non-zero degree belong to a single strongly connected component
      2. All vertices must have even number of degrees
   */
  public static boolean hasEulerCycle_Undirected(Map<Integer, Set<Integer>> graph) {
    return isSCCUn(graph) && isCycleDegree(graph);
  }

  /* Euler Path for Undirected Graph */
  /*
    Condition:
      1. All vertices with non-zero degree belong to a single strongly connected component
      2. Only 0 or 2 vertices can have odd number of degrees
   */
  public static boolean hasEulerPath_Undirected(Map<Integer, Set<Integer>> graph) {
    return isSCCUn(graph) && isPathDegree(graph);
  }

  private static boolean isSCCUn(Map<Integer, Set<Integer>> graph) {
    int[] visited = new int[graph.size()];
    dfsUn(0, visited, graph);
    for (int v : visited) {
      if (v == 0) {
        return false;
      }
    }
    return true;
  }

  private static void dfsUn(int v, int[] visited, Map<Integer, Set<Integer>> graph) {
    if (visited[v] == 1) {
      return;
    }
    visited[v] = 1;
    for (int adj : graph.get(v)) {
      dfsUn(adj, visited, graph);
    }
  }

  /* All even */
  private static boolean isCycleDegree(Map<Integer, Set<Integer>> graph) {
    int[] degree = new int[graph.size()];
    for (int v : graph.keySet()) {
      degree[v] += graph.get(v).size();
      for (int adj : graph.get(v)) {
        degree[adj]++;
      }
    }

    for (int v : degree) {
      if (v % 2 != 0) {
        return false;
      }
    }
    return true;
  }

  /* Only 0 or 2 odd */
  private static boolean isPathDegree(Map<Integer, Set<Integer>> graph) {
    int[] degree = new int[graph.size()];
    for (int v : graph.keySet()) {
      degree[v] += graph.get(v).size();
      for (int adj : graph.get(v)) {
        degree[adj]++;
      }
    }

    int oddCnt = 0;
    for (int v : degree) {
      if (v % 2 != 0) {
        oddCnt++;
      }
    }

    return oddCnt == 0 || oddCnt == 2;
  }

}
