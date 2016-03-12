package HashTable;

/**
 * Hash Table 1. Open Addressing 
 * 1.1 linear probing 
 * 1.2 quadratic probing 
 * 1.3 double hashing
 * 
 * 2. Separate Chaining
 * 
 * Hash table works best when they are not more than, or at the most two-thirds
 * full
 * 
 * The size of Hash table should be a prime number.
 * 
 * Expanding the array should rehash!
 */

public class LinearProbing implements HashTable {
  private HashEntry[] hashArray;
  private int size;
  private HashEntry nonItem;

  public LinearProbing() {
    this.size = 0;
    this.hashArray = new HashEntry[2];
    this.nonItem = new HashEntry(0, false);
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  private int arraySize() {
    return hashArray.length;
  }

  public void add(int val) {
    if (size == arraySize() / 2) {
      rehash(2 * arraySize());
    }
    int hashVal = hashFunc(val, arraySize());
    while (hashArray[hashVal] != null && hashArray[hashVal].isActive()) {
      if (hashArray[hashVal].getVal() == val) {
        return;
      }
      hashVal++;
      hashVal %= arraySize();
    }
    hashArray[hashVal] = new HashEntry(val);
    size++;
  }

  public boolean remove(int val) {
    int hashVal = hashFunc(val, arraySize());
    while (hashArray[hashVal] != null && hashArray[hashVal].isActive()) {
      if (hashArray[hashVal].getVal() == val) {
        hashArray[hashVal] = nonItem;
        size--;
        if (size > 0 && size == arraySize() / 4) {
          rehash(arraySize() / 2);
        }
        return true;
      }
      hashVal++;
      hashVal %= arraySize();
    }
    return false;
  }

  public boolean contains(int val) {
    int hashVal = hashFunc(val, arraySize());
    while (hashArray[hashVal] != null && hashArray[hashVal].isActive()) {
      if (hashArray[hashVal].getVal() == val) {
        return true;
      }
      hashVal++;
      hashVal %= arraySize();
    }
    return false;
  }

  // resize the array and rehashing
  private void rehash(int newSize) {
    newSize = nextPrime(newSize);
    HashEntry[] tmp = new HashEntry[newSize];
    for (int i = 0; i < arraySize(); i++) {
      if (hashArray[i] != null && hashArray[i].isActive()) {
        int index = hashFunc(hashArray[i].getVal(), newSize);
        tmp[index] = hashArray[i];
      }
    }
    hashArray = tmp;
  }

  // hashing
  private int hashFunc(int val, int size) {
    return val % size;
  }

  // find the next prime number as the new array size
  private int nextPrime(int n) {
    if (n % 2 == 0) {
      n++;
    }
    while (!isPrime(n)) {
      n += 2;
    }
    return n;
  }

  // check if the given number is a prime number
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