package Sorting;

public class InsertionSort {
  public static <T extends Comparable<? super T>> void sort(T[] array) {
    if (array.length == 0) {
      return;
    }
    int n = array.length;
    for (int i = 0; i < n; i++) {
      T curr = array[i];
      int j = i - 1;
      while (j >= 0) {
        if (array[j].compareTo(curr) > 0) {
          array[j + 1] = array[j];
          j--;
        } else {
          break;
        }
      }
      array[j + 1] = curr;
    }
  }
}
