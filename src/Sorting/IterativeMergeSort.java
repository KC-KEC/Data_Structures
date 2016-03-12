package Sorting;

import java.util.Arrays;

public class IterativeMergeSort {
  public static <T extends Comparable<? super T>> void sort(T[] array) {
    if (array == null || array.length == 0) {
      return;
    }
    int n = array.length;
    for (int L = 2; L < n; L *= 2) {
      for (int i = 0; i + L <= n; i += L) {
        int j = i + L - 1;
        merge(array, i, (i + j) / 2, j);
      }
    }
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
