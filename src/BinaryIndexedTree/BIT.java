package BinaryIndexedTree;

import java.util.Arrays;

/**
 * https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
 *
 */
public class BIT {

  static int[] nums;
  static int[] tree;
  static int n;

  /* ~O(nlogn) time to build tree */
  public static void buildTree(int[] nums) {
    n = nums.length;
    BIT.nums = new int[n + 1];
    int[] sums = new int[n + 1];
    tree = new int[n + 1];

    for (int i = 1; i <= n; i++) {
      BIT.nums[i] = nums[i - 1];
    }

    for (int i = 1; i <= n; i++) {
      sums[i] = sums[i - 1] + BIT.nums[i];
    }

    for (int i = 1; i <= n; i++) {
      int r = 1;
      int idx = i;

      // find the right most bit
      while ((idx & 1) != 1) {
        idx >>= 1;
        r <<= 1;
      }

      int from = i - r + 1;
      tree[i] = sums[i] - sums[from - 1];
    }
  }


  /* Return cumulative sum of [1, idx], idx is 1-based */
  /* ~O(logn) for query */

  /*
      Of Course we can get cumulative sum from sums[] array with O(1) time,
      but when we need update, time complexity for updating sums[] array will be O(n)
      So, sums[] array is only useful to build the fenwick tree in the beginning.
      After that, all query or update operations are only manipulating the Fenwick tree!
   */
  public static int cumulativeSum(int idx) {
    int sum = 0;
    while (idx > 0) {
      sum += tree[idx];
      idx -= (idx & -idx);
    }
    return sum;
  }

  /* update nums[idx] by val, idx is 1-based */
  /* ~O(logn) for update */
  public static void update(int idx, int val) {
    nums[idx] += val;
    while (idx <= n) {
      tree[idx] += val;
      idx += (idx & -idx);
    }
  }

  /* Return nums[idx], idx is 1-based */
  /* If we keep updating nums[] array in update() method, we can do this in O(1) time by simply
     return nums[idx]
     But if we did not update nums[] array, we still can do this in two ways:
        1. cumulativeSum(idx) - cumulativeSum(idx - 1) ~O(2 * logn) time
        2. readSingle(idx) ~O(c * logn) where c is less than 1
            For odd idx, O(1) time
                Because for odd number, the last bit is always 1,
                And idx - 1 always remove the last bit.
                The while loop will not be entered.
            For even idx, O(c * logn) time
  */
  public static int readSingle(int idx) {
    int sum = tree[idx];
    if (idx > 0) {
      int z = idx - (idx & -idx);
      idx--;
      while (idx != z) {
        sum -= tree[idx];
        idx -= (idx & -idx);
      }
    }
    return sum;
  }

  /* Scale each nums[] by factor c */
  /*
    nums[idx] / c = nums[idx] - (c - 1)nums[idx] / c
  */
  public static void scale_v1(int c) {
    for (int i = 1; i <= n; i++) {
      int num = readSingle(i);
      update(i, num - (c - 1) * num / c);
    }
  }

  /* Scale each nums[] by factor c */
  /*
    Scale tree[] directly
  */
  public static void scale_v2(int c) {
    for (int i = 1; i <= n; i++) {
      tree[i] /= c;
    }
  }

  /* Find index with given cumulative sum */
  /*
    Binary Search!
   */
  public static int find(int cumuSum) {
    int bitMask = (n & -n);
    int idx = 0;

    while (bitMask > 0 && idx < n) {
      int tIdx = idx + bitMask;
      if (tree[tIdx] == cumuSum) {
        return tIdx;
      }
      else if (tree[tIdx] < cumuSum) {
        idx = tIdx;
        cumuSum -= tree[tIdx];
      }
      bitMask >>= 1;
    }

    return cumuSum == 0? idx : -1;
  }

  public static void main(String[] args) {
    int[] nums = {1, 0, 2, 1, 1, 3, 0, 4, 2, 5, 2, 2, 3, 1, 0, 2};
    buildTree(nums);

    System.out.println(Arrays.toString(tree));
  }
}
