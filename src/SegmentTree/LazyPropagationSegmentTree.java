package SegmentTree;

import java.util.Arrays;

/**
 * Segment Tree with Lazy Propagation
 * <p>
 * Range Minimum Query.
 */
public class LazyPropagationSegmentTree {

  // length of nums array
  private int n;

  // size of segment tree
  private int size;

  // segment tree array representation
  private int[] segTree;

  // lazy propagation array, initialize to 0
  private int[] lazy;

  private int[] nums;

  /**
   * Constructor, create the segment tree.
   *
   * @param nums Input array
   */
  public LazyPropagationSegmentTree(int[] nums) {
    this.nums = nums;
    this.n = nums.length;

    // the number of actual nodes in the segment tree
    int nodes = 2 * n - 1;

    int h = 0;
    int tmp = 1;
    while (tmp <= nodes) {
      h++;
      tmp <<= 1;
    }

    this.size = (int) Math.pow(2, h) - 1;
    this.segTree = new int[size];
    this.lazy = new int[size];

    constructTree(0, n - 1, 0);
  }

  private int constructTree(int l, int r, int index) {
    if (l > r) {
      return Integer.MAX_VALUE;
    }
    if (l == r) {
      segTree[index] = nums[l];
      return segTree[index];
    }

    int mid = (l + r) / 2;
    int left = constructTree(l, mid, index * 2 + 1);
    int right = constructTree(mid + 1, r, index * 2 + 2);
    segTree[index] = Math.min(left, right);
    return segTree[index];
  }

  /**
   * Update a specific value with lazy propagation
   *
   * @param i   index to update
   * @param val new value
   */
  public void update(int i, int val) {
    int diff = val - nums[i];
    update(i, diff, 0, n - 1, 0);
    nums[i] = val;
  }

  private void update(int i, int diff, int l, int r, int index) {
    if (l > r) {
      return;
    }

    // if there is lazy propagation
    if (lazy[index] != 0) {
      segTree[index] += lazy[index];
      if (l != r) {
        lazy[index * 2 + 1] += lazy[index];
        lazy[index * 2 + 2] += lazy[index];
      }
      lazy[index] = 0;
    }

    // no overlap
    if (i < l || i > r) {
      return;
    }

    // like total overlap
    if (l == r) {
      segTree[index] += diff;
      return;
    }

    // partial overlap
    int mid = (l + r) / 2;
    update(i, diff, l, mid, index * 2 + 1);
    update(i, diff, mid + 1, r, index * 2 + 2);
    segTree[index] = Math.min(segTree[index * 2 + 1], segTree[index * 2 + 2]);
  }

  /**
   * Update a range with lazy propagation
   *
   * @param i    range start
   * @param j    range end
   * @param diff delta with original value
   */
  public void updateRange(int i, int j, int diff) {
    updateRange(i, j, 0, n - 1, diff, 0);
  }

  private void updateRange(int i, int j, int l, int r, int diff, int index) {
    if (i > j) {
      return;
    }

    // if there is lazy propagation
    if (lazy[index] != 0) {
      segTree[index] += lazy[index];
      if (l != r) {
        lazy[index * 2 + 1] += lazy[index];
        lazy[index * 2 + 2] += lazy[index];
      }
      lazy[index] = 0;
    }

    // total overlap
    if (i <= l && j >= r) {
      segTree[index] += diff;
      if (l != r) {
        lazy[index * 2 + 1] += diff;
        lazy[index * 2 + 2] += diff;
      }
      return;
    }

    // no overlap
    if (i > r || j < l) {
      return;
    }

    // partial overlap
    /*
        No possibility of l == r here.
        Because if l == r, then either a total overlap or no overlap is satisfied.
     */
    int mid = (l + r) / 2;
    updateRange(i, j, l, mid, diff, index * 2 + 1);
    updateRange(i, j, mid + 1, r, diff, index * 2 + 2);
    segTree[index] = Math.min(segTree[index * 2 + 1], segTree[index * 2 + 2]);
  }

  /**
   * Range Minimun Query with Lazy Propagation
   *
   * @param i range start
   * @param j range end
   * @return Minimum value in the range
   */
  public int rangeMin(int i, int j) {
    return getMin(i, j, 0, n - 1, 0);
  }

  private int getMin(int i, int j, int l, int r, int index) {
    if (l > r) {
      return Integer.MAX_VALUE;
    }

    // if there is lazy propagation
    if (lazy[index] != 0) {
      segTree[index] += lazy[index];
      if (l != r) {
        lazy[index * 2 + 1] += lazy[index];
        lazy[index * 2 + 2] += lazy[index];
      }
      lazy[index] = 0;
    }

    // total overlap
    if (i <= l && j >= r) {
      return segTree[index];
    }

    // no overlap
    if (i > r || j < l) {
      return Integer.MAX_VALUE;
    }

    // partial overlap
    /*
        No possibility of l == r here.
        Because if l == r, then either a total overlap or no overlap is satisfied.
     */
    int mid = (l + r) / 2;
    int left = getMin(i, j, l, mid, index * 2 + 1);
    int right = getMin(i, j, mid + 1, r, index * 2 + 2);
    return Math.min(left, right);
  }

  public static void main(String[] args) {

    /*
        General examples verifying the correctness of segment tree
    */
    int[] nums = {0, 3, 4, 2, 1, 6, -1};
    LazyPropagationSegmentTree tree = new LazyPropagationSegmentTree(nums);
    assert 0 == tree.rangeMin(0, 3);
    assert 1 == tree.rangeMin(1, 5);
    assert -1 == tree.rangeMin(1, 6);
    tree.update(2, 1);
    assert 1 == tree.rangeMin(1, 3);
    tree.updateRange(3, 5, -2);
    assert -1 == tree.rangeMin(5, 6);
    assert 0 == tree.rangeMin(0, 3);

    /*
        Another example showing the advantage of lazy propagation
    */
    int nums1[] = {-1,2,4,1,7,1,3,2};
    LazyPropagationSegmentTree tree1 = new LazyPropagationSegmentTree(nums1);
    tree1.updateRange(0, 3, 1);
    tree1.update(0, 1);

    assert 2 == tree1.rangeMin(0, 3);

    // there are two updates not been performed
    // because they are not touched.
    System.out.println(Arrays.toString(tree1.lazy));

    assert 1 == tree1.rangeMin(3, 5);
    // now the last two updates has been touched
    // and lazy propagation is performed to update them
    System.out.println(Arrays.toString(tree1.lazy));
  }
}
