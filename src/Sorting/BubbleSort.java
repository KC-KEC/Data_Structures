package Sorting;

public class BubbleSort {
  public static <T extends Comparable<? super T>> void sort(T[] array) {
    if (array.length == 0) {
      return;
    }
    int n = array.length;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (array[j].compareTo(array[i]) < 0) {
          swap(array, i, j);
        }
      }
    }
  }

  private static <T extends Comparable<? super T>> void swap(T[] array, int i, int j) {
    T tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}
