package Heap;

public class HeapNode implements Comparable<HeapNode>{
  public char key;
  public int val;

  public HeapNode(char key, int val) {
    this.key = key;
    this.val = val;
  }

  @Override
  public int compareTo(HeapNode node) {
    return this.val - node.val;
  }
}