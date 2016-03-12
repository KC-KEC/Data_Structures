package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface of Binary Search class
 * <p>
 * public TreeNode get(T val)
 * public void put(T val)
 * public void delete(T val)
 * public void deleteMin()
 * public T floor(T val)
 * public T ceiling(T val)
 * public List<T> traverse()
 * <p>
 * Created by Kyle on 1/3/16.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

  private TreeNode root;

  public TreeNode get(T val) {
    if (root == null) {
      return null;
    }
    TreeNode curr = root;
    while (curr != null) {
      if (curr.val == val) {
        return curr;
      }
      else if (val.compareTo(curr.val) < 0) {
        curr = curr.left;
      }
      else {
        curr = curr.right;
      }
    }
    return null;
  }

  /* Iterative put() method */
  public void put(T val) {
    if (root == null) {
      root = new TreeNode(val);
      return;
    }
    TreeNode curr = root;
    TreeNode parent = root;
    boolean isLeft = false;
    while (curr != null) {
      if (val.compareTo(curr.val) < 0) {
        parent = curr;
        isLeft = true;
        curr = curr.left;
      }
      else if (val.compareTo(curr.val) > 0) {
        parent = curr;
        isLeft = false;
        curr = curr.right;
      }
      else {
        return;
      }
    }
    if (isLeft) {
      parent.left = new TreeNode(val);
    }
    else {
      parent.right = new TreeNode(val);
    }
  }

  /* iterative delete */
  public void delete(T val) {
    if (root == null) {
      return;
    }
    TreeNode curr = root;
    TreeNode parent = root;
    boolean isLeft = false;

    // find the node to delete
    while (curr != null) {
      if (val.compareTo(curr.val) < 0) {
        parent = curr;
        isLeft = true;
        curr = curr.left;
      }
      else if (val.compareTo(curr.val) > 0) {
        parent = curr;
        isLeft = false;
        curr = curr.right;
      }
      else {
        break;
      }
    }
    if (curr == null) {
      return;
    }

    if (curr.left == null && curr.right == null) {
      if (curr == root) {
        root = null;
      }
      else if (isLeft) {
        parent.left = null;
      }
      else {
        parent.right = null;
      }
    }
    else if (curr.left == null) {
      if (curr == root) {
        root = curr.right;
      }
      else if (isLeft) {
        parent.left = curr.right;
      }
      else {
        parent.right = curr.right;
      }
    }
    else if (curr.right == null) {
      if (curr == root) {
        root = curr.left;
      }
      else if (isLeft) {
        parent.left = curr.left;
      }
      else {
        parent.right = curr.left;
      }
    }
    else {
      TreeNode successor = getSuccessor(curr);
      successor.left = curr.left;
      if (curr == root) {
        root = successor;
      }
      else if (isLeft) {
        parent.left = successor;
      }
      else {
        parent.right = successor;
      }
    }
  }

  private TreeNode getSuccessor(TreeNode root) {
    if (root == null || root.right == null) {
      return null;
    }
    TreeNode parent = root.right;
    TreeNode curr = root.right;
    while (curr.left != null) {
      parent = curr;
      curr = curr.left;
    }
    if (parent != curr) {
      parent.left = curr.right;
      curr.right = root.right;
    }
    return curr;
  }

  /* delete minimum node */
  public void deleteMin() {
    TreeNode parent = root;
    TreeNode curr = root;
    while (curr.left != null) {
      parent = curr;
      curr = curr.left;
    }
    parent.left = curr.right;
  }

  /* find the floor */
  /* the greatest value that is smaller than the given key */
  public T floor(T val) {
    if (root == null) {
      return null;
    }

    TreeNode result = floor(root, val);
    return result == null ? null : result.val;
  }

  private TreeNode floor(TreeNode curr, T val) {
    if (curr == null) {
      return null;
    }
    if (val.compareTo(curr.val) < 0) {
      return floor(curr.left, val);
    }
    else {
      TreeNode result = floor(curr.right, val);
      if (result == null) {
        return curr;
      }
      return result;
    }
  }

  /* find the ceiling */
  /* the smallest value that is greater than the given key */
  public T ceiling(T val) {
    if (root == null) {
      return null;
    }

    TreeNode result = ceiling(root, val);
    return result == null ? null : result.val;
  }

  private TreeNode ceiling(TreeNode curr, T val) {
    if (curr == null) {
      return null;
    }

    if (val.compareTo(curr.val) < 0) {
      TreeNode result = ceiling(curr.left, val);
      if (result == null) {
        return curr;
      }
      return result;
    }
    else {
      return ceiling(curr.right, val);
    }
  }

  /* inorder traversal */
  public List<T> traverse() {
    List<T> result = new ArrayList<T>();
    if (root == null) {
      return result;
    }
    traverse(root, result);
    return result;
  }

  private void traverse(TreeNode root, List<T> result) {
    if (root == null) {
      return;
    }
    traverse(root.left, result);
    result.add(root.val);
    traverse(root.right, result);
  }

  class TreeNode {
    T val;
    TreeNode left;
    TreeNode right;

    public TreeNode(T val) {
      this.val = val;
    }
  }

}
