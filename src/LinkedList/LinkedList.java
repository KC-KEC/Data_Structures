package LinkedList;

public class LinkedList {
  public ListNode head;

  public void add(int val) {
    ListNode curr = head;
    if (curr == null) {
      curr = new ListNode(val);
      head = curr;
    } else {
      while (curr.next != null) {
        curr = curr.next;
      }
      curr.next = new ListNode(val);
    }
  }

  public boolean remove(int val) {
    if (head == null) {
      return false;
    }
    if (head != null && head.val == val) {
      head = head.next;
      return true;
    }
    ListNode prev = head;
    ListNode curr = head.next;
    while (curr != null) {
      if (curr.val == val) {
        prev.next = curr.next;
        curr.next = null;
        return true;
      }
      prev = curr;
      curr = curr.next;
    }
    return false;
  }

  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    ListNode curr = head;
    while (curr.next != null) {
      sb.append(curr.val).append("->");
    }
    sb.append(curr.val);
    return sb.toString();
  }
}
