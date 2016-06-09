package Sorting;

/**
 * In-place. No other data structures are allowed !
 */
public class HeapSort {

  private static int N;

  public static <T extends Comparable<? super T>> void sort(T[] array) {
    if (array == null || array.length == 0) {
      throw new IllegalArgumentException();
    }

    N = array.length;

    // recover heap structure, but not sorted yet
    // build a maxheap
    for (int i = (N - 2) / 2; i >= 0; i--) {
      heapify(array, i);
    }

    // sort
    for (int i = N - 1; i >= 0; i--) {
      swap(array, 0, N - 1);
      N--;
      heapify(array, 0);
    }

  }

  private static <T extends Comparable<? super T>> void heapify(T[] array, int i) {
    int left = i * 2 + 1;
    int right = i * 2 + 2;
    int maxIdx = i;
    if (left < N && array[left].compareTo(array[maxIdx]) > 0) {
      maxIdx = left;
    }
    if (right < N && array[right].compareTo(array[maxIdx]) > 0) {
      maxIdx = right;
    }

    if (maxIdx != i) {
      swap(array, i, maxIdx);
      heapify(array, maxIdx);
    }
  }

  private static <T extends Comparable<? super T>> void swap(T[] array, int i, int j) {
    T tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}
