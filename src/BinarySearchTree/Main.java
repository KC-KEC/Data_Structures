package BinarySearchTree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {

  private static final int size = 7;

  public static void main(String[] args) {
    Random rand = new Random();
    BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
    Set<Integer> hs = new HashSet<>();

    /* create tree */
    int[] nums = new int[size];
    int i = 0;
    while (i < size) {
      int x = rand.nextInt(20);
      if (hs.contains(x)) {
        continue;
      }
      hs.add(x);
      nums[i] = x;
      bst.put(x);
      i++;
    }
    System.out.println("nums = " + Arrays.toString(nums));
    System.out.println("Tree = " + bst.traverse() + "\n");

    /* delete a random node */
    int index = rand.nextInt(size);
    System.out.println("key = " + nums[index]);
    bst.delete(nums[index]);
    System.out.println("After delete: " + bst.traverse() + "\n");

    /* delete the min node */
    bst.deleteMin();
    System.out.println("After delete min: " + bst.traverse() + "\n");

    /* find the floor of the random key */
    int x = rand.nextInt(20);
    System.out.println("key = " + x);
    System.out.println("Floor: " + bst.floor(x) + "\n");

    /* find the ceiling of the random key */
    x = rand.nextInt(20);
    System.out.println("key = " + x);
    System.out.println("Ceiling: " + bst.ceiling(x));
  }
}
