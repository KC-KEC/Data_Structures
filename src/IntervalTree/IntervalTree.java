package IntervalTree;

/**
 * Interval Tree is a BST (balanced BST is better) to
 * maintain set of intervals so that all operations can be done in O(logn) time
 *
 * Every node of Interval Tree stores following information:
 *    interval: [low, high]
 *    max: Maximum high value in subtree rooted with this node.
 *
 * Algorithm to search node x into Interval Tree:
 *    (1) if x overlaps with root's interval, return root's interval
 *    (2) if left child is not null and left.max > x.low, recur for left
 *    (3) else recur for right
 *
 * The BST structure is maintained by lower value of every interval.
 *
 *
 * Created by Kyle on 6/16/16.
 */

public class IntervalTree {

  public static ITNode buildTree(Interval[] intervals) {
    if (intervals == null || intervals.length == 0) {
      return null;
    }

    ITNode root = new ITNode(intervals[0], intervals[0].high);
    for (int i = 1; i < intervals.length; i++) {
      insert(root, intervals[i]);
    }
    return root;
  }

  public static ITNode insert(ITNode root, Interval interval) {
    if (root == null) {
      return new ITNode(interval, interval.high);
    }

    if (interval.low < root.interval.low) {
      root.left = insert(root.left, interval);
    }
    else {
      root.right = insert(root.right, interval);
    }

    root.maxValue = Math.max(root.maxValue, interval.high);
    return root;
  }

  private static boolean isOverlap(Interval i1, Interval i2) {
    return i1.low <= i2.low && i1.high >= i2.high;
  }

  public static Interval intervalQuery(ITNode root, Interval interval) {
    if (root == null) {
      return null;
    }

    if (isOverlap(root.interval, interval)) {
      return root.interval;
    }
    else if (root.left != null && interval.low < root.left.maxValue) {
      return intervalQuery(root.left, interval);
    }
    else {
      return intervalQuery(root.right, interval);
    }
  }

  public static void inorder(ITNode root) {
    if (root == null) {
      return;
    }

    inorder(root.left);
    System.out.print(root.interval);
    inorder(root.right);
  }

  public static void main(String[] args) {
    Interval[] intervals = {
        new Interval(15, 20),
        new Interval(10, 30),
        new Interval(17, 19),
        new Interval(5, 20),
        new Interval(12, 15),
        new Interval(30, 40)
    };

    ITNode root = buildTree(intervals);
    inorder(root);
    System.out.println();
    System.out.println(intervalQuery(root, new Interval(6, 7)));
    System.out.println(intervalQuery(root, new Interval(17, 18)));

  }
}
