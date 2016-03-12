package Sorting;

public class SelectionSort {
  public static <T extends Comparable<? super T>> void sort(T[] array) {
    if (array.length == 0) {
      return;
    }

    int n = array.length;
    for (int i = 0; i < n; i++) {
      T minValue = array[i];
      int minIndex = i;
      for (int j = i + 1; j < n; j++) {
        if (array[j].compareTo(minValue) < 0) {
          minValue = array[j];
          minIndex = j;
        }
      }
      swap(array, i, minIndex);
    }
  }

  private static <T extends Comparable<? super T>> void swap(T[] array, int i, int j) {
    T tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}
