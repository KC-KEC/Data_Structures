package Sorting;
import Heap.Heap;

public class HeapSort {
  public static void sort(int[] array) {
    if (array == null || array.length == 0) {
      return;
    }
    Heap maxHeap = new Heap();
    for (int num : array) {
      maxHeap.insert(num);
    }

    for (int i = array.length - 1; i >= 0; i--) {
      array[i] = maxHeap.remove();
    }
  }
}
