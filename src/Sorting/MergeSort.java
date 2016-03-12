package Sorting;

import java.util.Arrays;

public class MergeSort {
  public static <T extends Comparable<? super T>> void sort(T[] array) {
    if (array == null || array.length == 0) {
      return;
    }

    sort(array, 0, array.length - 1);
  }

  private static <T extends Comparable<? super T>> void sort(T[] array, int l, int r) {
    if (l == r) {
      return;
    }

    int mid = (l + r) / 2;
    sort(array, l, mid);
    sort(array, mid + 1, r);
    merge(array, l, mid, r);
  }

  private static <T extends Comparable<? super T>> void merge(T[] array, int l, int mid, int r) {
    T[] tempArray = Arrays.copyOf(array, array.length);
    int k = r;
    int i = mid;
    int j = r;
    while (i >= l && j >= mid + 1) {
      if (array[i].compareTo(array[j]) > 0) tempArray[k--] = array[i--];
      else tempArray[k--] = array[j--];
    }
    while (i >= l) {
      tempArray[k--] = array[i--];
    }
    while (j >= mid + 1) {
      tempArray[k--] = array[j--];
    }

    System.arraycopy(tempArray, l, array, l, r - l + 1);
  }
}
