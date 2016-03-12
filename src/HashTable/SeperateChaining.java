package HashTable;

import LinkedList.LinkedList;
import LinkedList.ListNode;

/**
 * Hash table works best when the size is no more than two-thirds full of the
 * hash table. The size of hash table should be a prime number. Expand the hash
 * table should rehash!
 * 
 * @author Ke Chen
 *
 */
public class SeperateChaining implements HashTable {
  private LinkedList[] hashArray;
  private int size;

  public SeperateChaining() {
    this.hashArray = new LinkedList[2];
    this.size = 0;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public void add(int val) {
    if (size == arraySize() / 2) {
      rehash(2 * arraySize());
    }
    int index = hashFunc(val, arraySize());
    if (hashArray[index] == null) {
      hashArray[index] = new LinkedList();
    }
    hashArray[index].add(val);
    size++;
  }

  @Override
  public boolean remove(int val) {
    int index = hashFunc(val, arraySize());
    if (hashArray[index] == null) {
      return false;
    }
    if (hashArray[index].remove(val)) {
      size--;
      if (size > 0 && size == arraySize() / 4) {
        rehash(arraySize() / 2);
      }
      return true;
    }
    return false;
  }

  @Override
  public boolean contains(int val) {
    int index = hashFunc(val, arraySize());
    if (hashArray[index] == null) {
      return false;
    }
    ListNode curr = hashArray[index].head;
    while (curr != null) {
      if (curr.val == val) {
        return true;
      }
      curr = curr.next;
    }
    return false;
  }

  private int arraySize() {
    return this.hashArray.length;
  }

  private void rehash(int newSize) {
    newSize = nextPrime(newSize);
    LinkedList[] tmp = new LinkedList[newSize];
    for (int i = 0; i < arraySize(); i++) {
      if (hashArray[i] != null) {
        ListNode curr = hashArray[i].head;
        while (curr != null) {
          int newIndex = hashFunc(curr.val, newSize);
          if (tmp[newIndex] == null) {
            tmp[newIndex] = new LinkedList();
          }
          tmp[newIndex].add(curr.val);
          curr = curr.next;
        }
      }
    }
    hashArray = tmp;
  }

  private int hashFunc(int val, int size) {
    return val % size;
  }

  private int nextPrime(int n) {
    if (n % 2 == 0) {
      n++;
    }
    while (!isPrime(n)) {
      n += 2;
    }
    return n;
  }

  private boolean isPrime(int n) {
    if (n % 2 == 0) {
      return false;
    }
    for (int i = 3; i * i <= n; i += 2) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

}
