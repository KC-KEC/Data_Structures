package Graph;

import java.util.*;

public class Graph {
  Map<Integer, Set<Integer>> graph;

  public Graph(int[][] nums) {
    this.graph = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      this.graph.put(i, new HashSet<>());
    }

    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums.length; j++) {
        if (nums[i][j] == 1) {
          graph.get(i).add(j);
        }
      }
    }
  }

  /* DFS traversal */
  public List<Integer> traverseDFS() {
    List<Integer> result = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();
    for (int vertex : graph.keySet()) {
      if (!visited.contains(vertex)) {
        dfsTraversal(vertex, visited, result);
      }
    }
    return result;
  }

  private void dfsTraversal(int vertex, Set<Integer> visited, List<Integer> result) {
    visited.add(vertex);
    result.add(vertex);
    for (int adj : graph.get(vertex)) {
      if (!visited.contains(adj)) {
        dfsTraversal(adj, visited, result);
      }
    }
  }

  /* BFS Traversal */
  public List<Integer> traverseBFS() {
    List<Integer> result = new ArrayList<>();
    Queue<Integer> q = new LinkedList<Integer>();
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

  /* DFS of topological sort */
  public List<Integer> topoDFS() {
    List<Integer> result = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    for (int vertex : this.graph.keySet()) {
      if (!visited.contains(vertex)) {
        dfs(vertex, visited, result);
      }
    }
    return result;
  }

  private void dfs(int vertex, Set<Integer> visited, List<Integer> result) {
    visited.add(vertex);
    for (int adj : graph.get(vertex)) {
      if (!visited.contains(adj)) {
        dfs(adj, visited, result);
      }
    }
    result.add(0, vertex);
  }

  /* BFS of topological sort */
  public List<Integer> topoBFS() {
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

  /* DFS detect if there is a cycle in the graph */
  public boolean hasCycleDFS() {
    // 1 for done, -1 for processing, 0 for unvisited
    Map<Integer, Integer> isVisited = new HashMap<>();
    for (int vertex : graph.keySet()) {
      isVisited.put(vertex, 0);
    }

    for (int vertex : graph.keySet()) {
      if (isVisited.get(vertex) != 1) {
        if (dfsDetectCycle(vertex, isVisited)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean dfsDetectCycle(int vertex, Map<Integer, Integer> isVisited) {
    if (isVisited.get(vertex) == 1) {
      return false;
    }
    if (isVisited.get(vertex) == -1) {
      return true;
    }
    isVisited.put(vertex, -1);
    for (int adj : graph.get(vertex)) {
      if (isVisited.get(adj) != 1) {
        if (dfsDetectCycle(adj, isVisited)) {
          return true;
        }
      }
    }
    isVisited.put(vertex, 1);
    return false;
  }

  public boolean hasCycleBFS() {
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
