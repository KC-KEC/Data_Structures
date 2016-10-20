package Heap;

import java.util.HashMap;
import java.util.Map;

public class HeapHashTable {
  private Node[] heap;
  private Map<Character, Integer> map;
  private Map<Character, Integer> idxMap;
  private int N;
  private int maxSize;
  private boolean isMinHeap = true;

  public HeapHashTable() {
    this.heap = new Node[10];
    this.maxSize = 10;
    this.map = new HashMap<>();
    this.idxMap = new HashMap<>();
  }
  public HeapHashTable(boolean isMinHeap) {
    this();
    this.isMinHeap = isMinHeap;
  }

  public void put(char key, int val) {
    if (map.containsKey(key)) {
      updateKey(key, val);
    }
    else {
      map.put(key, val);
      idxMap.put(key, N);
      heap[N] = new Node(key, val);
      bubbleUp(N);
      N++;
      if (N > maxSize / 2) {
        resize(maxSize * 2);
      }
    }
  }

  public Integer get(char key) {
    return map.get(key);
  }

  public Node remove(char key) {
    if (!map.containsKey(key)) {
      return null;
    }

    int idx = idxMap.get(key);
    Node res = heap[idx];
    swap(idx, N - 1);
    N--;
    bubbleDown(idx);
    map.remove(key);
    idxMap.remove(key);

    if (N < maxSize / 4) {
      resize(maxSize / 2);
    }

    return res;
  }

  public Node poll() {
    if (N == 0) {
      return null;
    }

    Node res = heap[0];
    swap(0, N - 1);
    N--;
    bubbleDown(0);
    map.remove(res.key);
    idxMap.remove(res.key);

    if (N < maxSize / 4) {
      resize(maxSize / 2);
    }
    return res;
  }

  public boolean containsKey(char key) {
    return map.containsKey(key);
  }

  public int size() {
    return N;
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public void updateKey(char key, int val) {
    if (!map.containsKey(key)) {
      return;
    }

    int idx = idxMap.get(key);
    heap[idx].val = val;
    if (isMinHeap) {
      if (val < map.get(key)) {
        bubbleUp(idxMap.get(key));
      }
      else if (val > map.get(key)) {
        bubbleDown(idxMap.get(key));
      }
    }
    else {
      if (val < map.get(key)) {
        bubbleDown(idxMap.get(key));
      }
      else if (val > map.get(key)) {
        bubbleUp(idxMap.get(key));
      }
    }
    map.put(key, val);
  }

  private void swap(int l, int r) {
    idxMap.put(heap[l].key, r);
    idxMap.put(heap[r].key, l);
    Node tmp = heap[l];
    heap[l] = heap[r];
    heap[r] = tmp;
  }

  private void bubbleUp(int idx) {
    if (idx < 0 || idx >= N) {
      return;
    }

    int parentIdx = (idx - 1) / 2;
    if (isMinHeap) {
      if (parentIdx >= 0 && heap[parentIdx].val > heap[idx].val) {
        swap(parentIdx, idx);
        bubbleUp(parentIdx);
      }
    }
    else {
      if (parentIdx >= 0 && heap[parentIdx].val < heap[idx].val) {
        swap(parentIdx, idx);
        bubbleUp(parentIdx);
      }
    }
  }

  private void bubbleDown(int idx) {
    if (idx < 0 || idx >= N) {
      return;
    }
    int leftIdx = idx * 2 + 1;
    int rightIdx = idx * 2 + 2;
    if (isMinHeap) {
      int maxIdx = idx;
      if (leftIdx < N && heap[leftIdx].val > heap[maxIdx].val) {
        maxIdx = leftIdx;
      }
      if (rightIdx < N && heap[rightIdx].val > heap[maxIdx].val) {
        maxIdx = rightIdx;
      }

      if (maxIdx != idx) {
        swap(idx, maxIdx);
        bubbleDown(maxIdx);
      }
    }
    else {
      int minIdx = idx;
      if (leftIdx < N && heap[leftIdx].val < heap[minIdx].val) {
        minIdx = leftIdx;
      }
      if (rightIdx < N && heap[rightIdx].val < heap[minIdx].val) {
        minIdx = rightIdx;
      }

      if (minIdx != idx) {
        swap(idx, minIdx);
        bubbleDown(minIdx);
      }
    }
  }

  private void resize(int newSize) {
    Node[] newHeap = new Node[newSize];
    System.arraycopy(heap, 0, newHeap, 0, N + 1);
    heap = newHeap;
  }

}