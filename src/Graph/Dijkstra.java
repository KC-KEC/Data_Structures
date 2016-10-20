package Graph;

import Heap.HeapHashTable;
import Heap.Node;

import java.util.*;

/**
 * Dijkstra Algorithm
 *
 * Find shortest path on weighted graph, either directed or undirected
 *
 * O(ElogV) time
 * O(E + V) space
 */
public class Dijkstra {

  /**
   * Input a map as graph, print out the shortest distance
   *
   * @param graph
   */
  public static List<Character> shortestPath(Map<Character, Map<Character, Integer>> graph, char
      start, char end) {
    List<Character> res = new LinkedList<>();
    if (graph == null || graph.isEmpty()) {
      return res;
    }

    HeapHashTable minHeap = new HeapHashTable();

    Map<Character, Integer> distance = new HashMap<>();

    Map<Character, Character> path = new HashMap<>();

    for (char v : graph.keySet()) {
      minHeap.put(v, Integer.MAX_VALUE);
    }

    minHeap.updateKey(start, 0);
    distance.put(start, 0);
    path.put(start, start);
    while (!minHeap.isEmpty()) {
      Node currNode = minHeap.poll();
      distance.put(currNode.key, currNode.val);

      for (char adj : graph.get(currNode.key).keySet()) {
        if (minHeap.containsKey(adj)) {
          int dis = distance.get(currNode.key) + graph.get(currNode.key).get(adj);
          if (dis < minHeap.get(adj)) {
            minHeap.updateKey(adj, dis);
            path.put(adj, currNode.key);
          }
        }
      }
    }

    res.add(0, end);
    while (end != path.get(end)) {
      end = path.get(end);
      res.add(0, end);
    }

    return res;
  }

  public static void main(String[] args) {

    Map<Character, Map<Character, Integer>> graph = new HashMap<>();
    Map<Character, Map<Character, Integer>> graph2 = new HashMap<>();

    for (char v = 'a'; v <= 'g'; v++) {
      graph.put(v, new HashMap<>());
      graph2.put(v, new HashMap<>());
    }

    graph.get('a').put('b', 5);
    graph.get('a').put('e', 9);
    graph.get('a').put('f', 3);
    graph.get('b').put('c', 2);
    graph.get('c').put('e', 3);
    graph.get('f').put('g', 2);
    graph.get('g').put('e', 2);

    graph2.get('a').put('b', 2);
    graph2.get('a').put('e', 9);
    graph2.get('a').put('f', 3);
    graph2.get('b').put('c', 2);
    graph2.get('c').put('e', 4);
    graph2.get('f').put('g', 2);
    graph2.get('g').put('e', 2);

    List<Character> shortestPath = Dijkstra.shortestPath(graph, 'a', 'e');
    List<Character> shortestPath2 = Dijkstra.shortestPath(graph2, 'a', 'e');

    System.out.println(shortestPath);
    System.out.println(shortestPath2);

  }
}




