package Sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * Find the kth smallest in a given array.
 * <p>
 * Created by Kyle on 12/27/15.
 */
public class QuickSelect {
  public static <T extends Comparable<? super T>> T select(T[] array, int k) {
    if (array == null || array.length == 0) {
      return null;
    }

    int l = 0;
    int r = array.length - 1;
    while (l < r) {
      int mid = partition(array, l, r);
      if (mid == k - 1) {
        return array[mid];
      } else if (mid < k - 1) {
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }
    return array[k - 1];
  }

  private static <T extends Comparable<? super T>> int partition(T[] array, int l, int r) {
    T pivot = array[r];
    int left = l;
    int right = r - 1;
    while (left <= right) {
      while (left <= right && array[left].compareTo(pivot) < 0) left++;
      while (left <= right && array[right].compareTo(pivot) >= 0) right--;
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

  public static void main(String[] args) {
    Random rand = new Random();
    Integer[] array = new Integer[10];
    for (int i = 0; i < 10; i++) {
      array[i] = rand.nextInt(10);
    }
    int k = 9;
    Arrays.sort(array);
    System.out.println(Arrays.toString(array));
    System.out.println(select(array, k));
  }
}
