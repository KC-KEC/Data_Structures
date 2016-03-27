package SegmentTree;

/**
 * Segment Tree Array Implementation
 * <p>
 * Range Sum Query
 */
public class SegmentTreeArray {

  // length of nums array
  private int n;

  // size of segment tree
  private int size;

  // segment tree array representation
  private int[] segTree;

  private int[] nums;

  public SegmentTreeArray(int[] nums) {
    this.n = nums.length;
    this.nums = nums;

    // the number of actual nodes in the segment tree.
    // Not the same as the size of the segment tree, the size is always the power of 2!
    int nodes = 2 * n - 1;

    // compute the height and size of the segment tree
    int h = 0;
    int tmp = 1;
    while (tmp <= nodes) {
      h++;
      tmp <<= 1;
    }

    this.size = (int) Math.pow(2, h) - 1;
    this.segTree = new int[size];
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
    segTree[index] = left + right;
    return segTree[index];
  }

  public void update(int i, int val) {
    int diff = val - nums[i];
    update(i, diff, 0, n - 1, 0);
    nums[i] = val;
  }

  private void update(int i, int diff, int l, int r, int index) {
    if (l > r) {
      return;
    }
    if (i < l || i > r) {
      return;
    }

    if (l == r) {
      segTree[index] += diff;
      return;
    }

    int mid = (l + r) / 2;
    update(i, diff, l, mid, index * 2 + 1);
    update(i, diff, mid + 1, r, index * 2 + 2);
    segTree[index] = segTree[index * 2 + 1] + segTree[index * 2 + 2];
  }

  public void updateRange(int i, int j, int diff) {
    updateRange(i, j, 0, n - 1, diff, 0);
    for (int x = i; x <= j; x++) {
      nums[x] += diff;
    }
  }

  private void updateRange(int i, int j, int l, int r, int diff, int index) {
    if (l > r) {
      return;
    }
    if (i > r || j < l) {
      return;
    }

    if (l == r) {
      segTree[index] += diff;
      return;
    }

    int mid = (l + r) / 2;
    updateRange(i, j, l, mid, diff, index * 2 + 1);
    updateRange(i, j, mid + 1, r, diff, index * 2 + 2);
    segTree[index] = segTree[index * 2 + 1] + segTree[index * 2 + 2];
  }

  public int sumRange(int i, int j) {
    return getSum(i, j, 0, n - 1, 0);
  }

  private int getSum(int i, int j, int l, int r, int index) {
    // total overlap
    if (i <= l && j >= r) {
      return segTree[index];
    }
    // no overlap
    if (j < l || i > r) {
      return 0;
    }

    // partial overlap
    int mid = (l + r) / 2;
    int left = getSum(i, j, l, mid, index * 2 + 1);
    int right = getSum(i, j, mid + 1, r, index * 2 + 2);
    return left + right;
  }

  public static void main(String[] args) {
    int[] nums = {0, 3, 4, 2, 1, 6, -1};
    SegmentTreeArray tree = new SegmentTreeArray(nums);
    assert 9 == tree.sumRange(0, 3);
    assert 16 == tree.sumRange(1, 5);
    assert 15 == tree.sumRange(1, 6);
    tree.update(2, 1);
    assert 6 == tree.sumRange(1, 3);
    tree.updateRange(3, 5, -2);
    assert 3 == tree.sumRange(5, 6);
    assert 4 == tree.sumRange(0, 3);
  }
}
