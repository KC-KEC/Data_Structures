package Sorting;

import java.util.Random;

public class Shuffle {
  public static <T extends Comparable<? super T>> void shuffle(T[] array) {
    if (array == null || array.length == 0) {
      return;
    }
    int n = array.length;
    Random rand = new Random();
    for (int i = 0; i < n; i++) {
      int j = rand.nextInt(i + 1);
      swap(array, i, j);
    }
  }

  private static <T extends Comparable<? super T>> void swap(T[] array, int i, int j) {
    T tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}
