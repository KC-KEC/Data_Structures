package Sorting;

/**
 * The Difference between Median of three quicksort and the normal quicksort
 * is the Pivot Picking rule.
 * <p>
 * While normal quicksort always pick the last element as the pivot, the
 * median of three quicksort pick the median of first, middle and last
 * element as the pivot.
 * <p>
 * Other things are all the same.
 * <p>
 * Created by Kyle on 12/26/15.
 */
public class MedianOfThree {
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

    int median = getMedian(array, l, r);
    swap(array, median, r);
    int mid = partition(array, l, r);
    sort(array, l, mid - 1);
    sort(array, mid + 1, r);
  }

  private static <T extends Comparable<? super T>> int getMedian(T[] array, int l, int r) {
    int mid = (l + r) / 2;
    if (array[l].compareTo(array[mid]) > 0) {
      swap(array, mid, l);
    }
    if (array[l].compareTo(array[r]) > 0) {
      swap(array, l, r);
    }
    if (array[mid].compareTo(array[r]) > 0) {
      swap(array, mid, r);
    }

    return mid;
  }

  private static <T extends Comparable<? super T>> void swap(T[] array, int i, int j) {
    T tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }

  private static <T extends Comparable<? super T>> int partition(T[] array, int l, int r) {
    T pivot = array[r];
    int left = l;
    int right = r - 1;
    while (left <= right) {
      while (left <= right && array[left].compareTo(pivot) < 0) left++;
      while (left <= right && array[right].compareTo(pivot) > 0) right--;
      if (left <= right) {
        swap(array, left++, right--);
      }
    }
    swap(array, left, r);
    return left;
  }
}
