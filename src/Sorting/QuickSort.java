package Sorting;

public class QuickSort {
  public static <T extends Comparable<T>> void sort(T[] array) {
    if (array.length == 0) {
      return;
    }

    int n = array.length;
    sort(array, 0, n - 1);
  }

  private static <T extends Comparable<? super T>> void sort(T[] array, int l, int r) {
    if (l >= r) {
      return;
    }

    /*
       When r - l < threshold, insertion sort could be used to do optimization.
     */

    int mid = partition(array, l, r);
    if (l < mid - 1) sort(array, l, mid - 1);
    if (mid + 1 < r) sort(array, mid + 1, r);
  }

  private static <T extends Comparable<? super T>> int partition(T[] array, int l, int r) {
    T pivot = array[r];
    int left = l;
    int right = r - 1;
    while (left <= right) {
      while (left <= right && array[left].compareTo(pivot) < 0) left++;
      while (right >= left && array[right].compareTo(pivot) >= 0) right--;
      if (left <= right) {
        swap(array, left++, right--);
      }
    }
    swap(array, left, r);
    return left;
  }

  private static <T extends Comparable<? super T>> void swap(T[] array, int i, int j) {
    T tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}
