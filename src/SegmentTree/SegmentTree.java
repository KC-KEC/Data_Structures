package SegmentTree;

class SegTreeNode {
  int val;

  // range of this node
  int start;
  int end;
  SegTreeNode left;
  SegTreeNode right;

  public SegTreeNode(int val, int start, int end) {
    this.val = val;
    this.start = start;
    this.end = end;
  }
}

/**
 * Segment Tree implementation of Range Sum Query
 *
 * 1. total overlap => return curr.val
 * 2. partial overlap => go to left and right
 * 3. no overlap => 0
 */
public class SegmentTree {

  private SegTreeNode root;
  private int[] nums;

  /**
   * Construct the segment tree
   *
   * @param nums Input array
   */
  public SegmentTree(int[] nums) {
    this.nums = nums;
    this.root = constructTree(nums, 0, nums.length - 1);
  }

  private SegTreeNode constructTree(int[] nums, int l, int r) {
    if (l > r) {
      return null;
    }
    if (l == r) {
      return new SegTreeNode(nums[l], l, l);
    }

    int mid = (l + r) / 2;
    SegTreeNode left = constructTree(nums, l, mid);
    SegTreeNode right = constructTree(nums, mid + 1, r);
    SegTreeNode curr = new SegTreeNode(left.val + right.val, l, r);
    curr.left = left;
    curr.right = right;
    return curr;
  }

  /**
   * Update the value.
   *
   * @param i   index to update
   * @param val new value
   */
  public void update(int i, int val) {
    int diff = val - nums[i];
    update(root, diff, i);
    nums[i] = val;
  }

  private void update(SegTreeNode curr, int diff, int i) {
    if (curr == null) {
      return;
    }
    if (i < curr.start || i > curr.end) {
      return;
    }
    curr.val += diff;
    update(curr.left, diff, i);
    update(curr.right, diff, i);
  }

  /**
   * Query for sum.
   *
   * @param i starting index
   * @param j ending index
   * @return sum of range
   */
  public int sumRange(int i, int j) {
    return getSum(i, j, root);
  }

  private int getSum(int i, int j, SegTreeNode curr) {
    if (curr == null) {
      return 0;
    }

    // total overlap
    if (i <= curr.start && j >= curr.end) {
      return curr.val;
    }
    // no overlap
    if (i > curr.end || j < curr.start) {
      return 0;
    }
    //partial overlap
    return getSum(i, j, curr.left) + getSum(i, j, curr.right);
  }

  public static void main(String[] args) {
    int[] nums = {1, 3, 5};
    SegmentTree tree = new SegmentTree(nums);
    System.out.println(tree.sumRange(0, 2));
    tree.update(1, 2);
    System.out.println(tree.sumRange(0, 2));

  }
}
