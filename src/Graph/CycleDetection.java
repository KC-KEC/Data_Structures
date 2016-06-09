package Graph;

import java.util.*;


public class CycleDetection {
  /**
   * DFS detect cycle
   */
  public boolean hasCycleDFS(Map<Integer, Set<Integer>> graph) {
    // 1 for done, -1 for processing, 0 for unvisited
    Map<Integer, Integer> isVisited = new HashMap<>();
    for (int vertex : graph.keySet()) {
      isVisited.put(vertex, 0);
    }

    for (int vertex : graph.keySet()) {
      if (isVisited.get(vertex) != 1) {
        if (dfsDetectCycle(graph, vertex, isVisited)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean dfsDetectCycle(Map<Integer, Set<Integer>> graph, int vertex, Map<Integer,
      Integer> isVisited) {
    if (isVisited.get(vertex) == 1) {
      return false;
    }
    if (isVisited.get(vertex) == -1) {
      return true;
    }
    isVisited.put(vertex, -1);
    for (int adj : graph.get(vertex)) {
      if (isVisited.get(adj) != 1) {
        if (dfsDetectCycle(graph, adj, isVisited)) {
          return true;
        }
      }
    }
    isVisited.put(vertex, 1);
    return false;
  }

  /**
   * BFS detect cycle
   */
  public boolean hasCycleBFS(Map<Integer, Set<Integer>> graph) {
    Map<Integer, Integer> indegree = new HashMap<>();
    Queue<Integer> q = new LinkedList<>();

    for (int vertex : graph.keySet()) {
      indegree.put(vertex, 0);
    }

    for (int vertex : graph.keySet()) {
      for (int adj : graph.get(vertex)) {
        indegree.put(adj, indegree.get(adj) + 1);
      }
    }

    for (int vertex : indegree.keySet()) {
      if (indegree.get(vertex) == 0) {
        q.offer(vertex);
      }
    }

    while (!q.isEmpty()) {
      int curr = q.poll();
      for (int adj : graph.get(curr)) {
        indegree.put(adj, indegree.get(adj) - 1);
        if (indegree.get(adj) == 0) {
          q.offer(adj);
        }
      }
    }

    for (int vertex : indegree.keySet()) {
      if (indegree.get(vertex) != 0) {
        return true;
      }
    }
    return false;
  }
}
