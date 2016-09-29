package Heap;

import java.util.HashMap;
import java.util.Map;

public class HashHeap {
  private int[] heap;
  public int size = 10;
  private int cnt;
  private int n;
  private boolean isMinHeap = true;
  private Map<Integer, Node> map;

  /**
   * Default constructor, size = 10, min heap
   */
  public HashHeap() {
    this.heap = new int[size];
    map = new HashMap<>();
  }
  public HashHeap(int n) {
    this.size = n;
    this.heap = new int[size];
    map = new HashMap<>();
  }
  public HashHeap(boolean isMinHeap) {
    this.isMinHeap = isMinHeap;
    this.heap = new int[size];
    map = new HashMap<>();
  }
  public HashHeap(int n, boolean isMinHeap) {
    this.isMinHeap = isMinHeap;
    this.size = n;
    this.heap = new int[size];
    map = new HashMap<>();
  }

  /**
   * Offer a number.
   *
   * @param val Integer
   */
  public void offer(Integer val) {
    cnt++;
    if (map.containsKey(val)) {
      map.get(val).cnt++;
      return;
    }

    if (n > size / 2) {
      resize(size * 2);
    }
    heap[n] = val;
    map.put(val, new Node(n, 1));
    bubbleUp(n);
    n++;
  }

  /**
   * Poll a number.
   *
   * @return Integer
   */
  public Integer poll() {
    if (n == 0) {
      return null;
    }
    cnt--;
    Integer res = heap[0];
    if (map.get(res).cnt > 1) {
      map.get(res).cnt--;
    }
    else {
      swap(0, n - 1);
      n--;
      bubbleDown(0);
      if (n < size / 4) {
        resize(size / 2);
      }
      map.remove(res);
    }


    return res;
  }

  /**
   * Size of the heap.
   *
   * @return Integer
   */
  public int size() {
    return this.cnt;
  }

  /**
   * Is the heap empty.
   *
   * @return Boolean
   */
  public boolean isEmpty() {
    return n == 0;
  }

  /**
   * Remove a number.
   *
   * @param val Integer
   */
  public void remove(int val) {
    if (map.containsKey(val)) {
      cnt--;
      if (map.get(val).cnt > 1) {
        map.get(val).cnt--;
      }
      else {
        int idx = map.get(val).idx;
        swap(idx, n - 1);
        n--;
        bubbleDown(idx);
        if (n < size / 4) {
          resize(size / 2);
        }
        map.remove(val);
      }
    }
  }

  private void bubbleUp(int idx) {
    int parentIdx = (idx - 1) / 2;
    if (isMinHeap && parentIdx >= 0 && heap[parentIdx] > heap[idx]) {
      swap(parentIdx, idx);
      bubbleUp(parentIdx);
    }
    else if (!isMinHeap && parentIdx >= 0 && heap[parentIdx] < heap[idx]) {
      swap(parentIdx, idx);
      bubbleUp(parentIdx);
    }
  }

  private void bubbleDown(int idx) {
    int leftIdx = idx * 2 + 1;
    int rightIdx = idx * 2 + 2;
    if (isMinHeap) {
      int minVal = Integer.MAX_VALUE;
      int minIdx = -1;
      if (leftIdx < n && heap[leftIdx] < minVal) {
        minVal = heap[leftIdx];
        minIdx = leftIdx;
      }
      if (rightIdx < n && heap[rightIdx] < minVal) {
        minVal = heap[rightIdx];
        minIdx = rightIdx;
      }

      if (minVal < heap[idx]) {
        swap(idx, minIdx);
        bubbleDown(minIdx);
      }
    }
    else {
      int maxValue = Integer.MIN_VALUE;
      int maxIdx = -1;
      if (leftIdx < n && heap[leftIdx] > maxValue) {
        maxValue = heap[leftIdx];
        maxIdx = leftIdx;
      }
      if (rightIdx < n && heap[rightIdx] > maxValue) {
        maxValue = heap[rightIdx];
        maxIdx = rightIdx;
      }

      if (maxValue > heap[idx]) {
        swap(idx, maxIdx);
        bubbleDown(maxIdx);
      }
    }
  }

  private void resize(int newSize) {
    int[] newHeap = new int[newSize];
    for (int i = 0; i < n; i++) {
      newHeap[i] = heap[i];
    }
    heap = newHeap;
    size = newSize;
  }

  private void swap(int i, int j) {
    int val1 = heap[i];
    int val2 = heap[j];
    int tmpIdx = map.get(val1).idx;
    map.get(val1).idx = map.get(val2).idx;
    map.get(val2).idx = tmpIdx;
    heap[i] = val2;
    heap[j] = val1;
  }

  private class Node {
    int idx;
    int cnt;

    Node(int idx, int cnt) {
      this.idx = idx;
      this.cnt = cnt;
    }
  }

  // test
  public static void main(String[] args) {
    HashHeap hsHeap = new HashHeap();
    int[] nums = {3, 6, 2, 7, 1, 4, 3, 4, 5, 2};
    for (int num : nums) {
      hsHeap.offer(num);
    }

    System.out.println(hsHeap.size()); // 10
    System.out.println(hsHeap.size);// 20
    System.out.println(hsHeap.n); // 7

    hsHeap.remove(4);
    hsHeap.remove(6);
    hsHeap.remove(7);
    System.out.println(hsHeap.poll()); // 1

    System.out.println(hsHeap.size()); // 6
    System.out.println(hsHeap.size); // 10
    System.out.println(hsHeap.n); // 4

    while (!hsHeap.isEmpty()) {
      System.out.print(hsHeap.poll() + ", ");
    }
    System.out.println();
  }
}
