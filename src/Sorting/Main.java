package Sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
//    Scanner scan = new Scanner(System.in);
    Integer[] nums = new Integer[10];
//    Integer[] nums = {3, 2, 1, 1, 3, 2};
    Random rand = new Random();
    for (int i = 0; i < 10; i++) {
      nums[i] = rand.nextInt(10);
    }
    System.out.println(Arrays.toString(nums));
    System.out.println("\nAfter sorting: ");

    Integer[] tmp;

    tmp = Arrays.copyOf(nums, nums.length);
    BubbleSort.sort(tmp);
    System.out.println("Bubble sort: " + Arrays.toString(tmp));

    tmp = Arrays.copyOf(nums, nums.length);
    InsertionSort.sort(tmp);
    System.out.println("Insertion sort: " + Arrays.toString(tmp));

    tmp = Arrays.copyOf(nums, nums.length);
    SelectionSort.sort(tmp);
    System.out.println("Selection sort: " + Arrays.toString(tmp));

    tmp = Arrays.copyOf(nums, nums.length);
    /*
      Always Shuffle first before doing quicksort.
     */
    Shuffle.shuffle(tmp);
    QuickSort.sort(tmp);
    System.out.println("Quick sort: " + Arrays.toString(tmp));

    tmp = Arrays.copyOf(nums, nums.length);
    MedianOfThree.sort(tmp);
    System.out.println("Median of Three: " + Arrays.toString(tmp));

    tmp = Arrays.copyOf(nums, nums.length);
    ThreeWayQuickSort.sort(tmp);
    System.out.println("3-way quick sort: " + Arrays.toString(tmp));

    tmp = Arrays.copyOf(nums, nums.length);
    MergeSort.sort(tmp);
    System.out.println("Merge sort: " + Arrays.toString(tmp));

    tmp = Arrays.copyOf(nums, nums.length);
    MergeSort.sort(tmp);
    System.out.println("Iterative Merge sort: " + Arrays.toString(tmp));

    int[] tmp2 = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      tmp2[i] = nums[i];
    }
    HeapSort.sort(tmp2);
    System.out.println("Heap sort: " + Arrays.toString(tmp2));
  }
}
