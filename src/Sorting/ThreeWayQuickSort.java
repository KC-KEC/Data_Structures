package Sorting;

/**
 * Useful when there are a lot of duplcates.
 * The time complexity of normal quick sort degrade to QUADRATIC time if too many duplicates exist.
 * And shuffle doesn't help much when the array are all duplicates.
 * <p>
 * Created by Kyle on 12/27/15.
 */
public class ThreeWayQuickSort {
  public static <T extends Comparable<? super T>> void sort(T[] array) {
    if (array == null || array.length == 0) {
      return;
    }

    sort(array, 0, array.length - 1);
  }

  private static <T extends Comparable<? super T>> void sort(T[] array, int l, int r) {
    if (l >= r) {
      return;
    }

    T pivot = array[r];
    int i = l;
    int left = l;
    int right = r;
    while (i <= right) {
      if (array[i].compareTo(pivot) < 0) {
        swap(array, left++, i++);
      } else if (array[i].compareTo(pivot) > 0) {
        swap(array, right--, i);
      } else {
        i++;
      }
    }

    sort(array, l, left - 1);
    sort(array, right + 1, r);
  }

  private static <T extends Comparable<? super T>> void swap(T[] array, int i, int j) {
    T tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}
