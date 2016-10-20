package Heap;

public class Node<T> implements Comparable<Node>{
  public char key;
  public int val;

  public Node(char key, int val) {
    this.key = key;
    this.val = val;
  }

  @Override
  public int compareTo(Node node) {
    return this.val - node.val;
  }
}