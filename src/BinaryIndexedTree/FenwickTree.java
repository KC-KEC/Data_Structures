package BinaryIndexedTree;

/**
 * Binary Indexed Tree, also called Fenwick tree.
 * <p>
 * Useful to find prefix sum or range sum from a given array.
 * <p>
 * O(n) space
 * O(logn) time for update and query
 * O(nlogn) time to build tree.
 * <p>
 * Advantage over segment tree: Less space and easy to implement. Segment Tree cost O(4n) space
 * in worst case to represent the tree.
 * <p>
 * Formula in Fenwick Tree:
 * GET PARENT:
 * 1. 2's complement
 * 2. AND with original number
 * 3. Subtract from original number
 * <p>
 * GET NEXT:
 * 1. 2's complement
 * 2. AND with original number
 * 3. Add to original number
 * <p>
 * Reference:
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/FenwickTree.java
 * https://www.youtube.com/watch?v=CWDQJGaN1gY
 */
public class FenwickTree {

  private int n;
  private int[] nums;
  private int[] fenwickTree;

  public FenwickTree(int[] nums) {
    this.nums = nums;
    this.n = nums.length;
    this.fenwickTree = new int[n + 1];

    constructTree(nums);
  }

  private void constructTree(int[] nums) {
    for (int i = 1; i <= n; i++) {
      update(i - 1, 2 * nums[i - 1]);
    }
  }

  public void update(int i, int val) {
    int diff = val - nums[i];
    int index = i + 1;
    while (index <= n) {
      fenwickTree[index] += diff;
      index = getNext(index);
    }
  }

  public int getSum(int i) {
    int result = 0;
    int index = i + 1;
    while (index > 0) {
      result += fenwickTree[index];
      index = getParent(index);
    }
    return result;
  }

  public int rangeSum(int i, int j) {
    if (i == 0) {
      return getSum(j);
    }
    return getSum(j) - getSum(i - 1);
  }

  private int getParent(int index) {
    return index - (-index & index);
  }

  private int getNext(int index) {
    return index + (-index & index);
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 4, 5, 6, 7};
    FenwickTree ft = new FenwickTree(nums);
    assert 1 == ft.getSum(0);
    assert 3 == ft.getSum(1);
    assert 6 == ft.getSum(2);
    assert 10 == ft.getSum(3);
    assert 15 == ft.getSum(4);
    assert 21 == ft.getSum(5);
    assert 28 == ft.getSum(6);
    assert 15 == ft.rangeSum(3, 5);
  }
}
