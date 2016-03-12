package Heap;

/**
 * Heap O(logN)
 * 1. Heap is a complete binary tree
 * 2. Heap is weakly ordered that it can not be traversed in order
 * 3. Heap doesn't support searching
 * 4. Remove and insert the same node doesn't give back the original heap
 * 5. Heap is array-based:
 * 			parent 			= (x - 1) / 2
 * 			left child  = 2 * x + 1
 * 			right child = 2 * x + 2
 * 
 * 6. All of the operations cost O(logN) time
 * 7. O(1) time for getting the maximum or minimum key
 * 
 */
public class Heap {
	private int[] heapArray;
	private int size;
	
	public Heap() {
		heapArray = new int[2];
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	private void resize(int newSize) {
		int[] tmp = new int[newSize];
		for (int i = 0; i < size; i++) {
			tmp[i] = heapArray[i];
		}
		heapArray = tmp;
	}
	
	public void insert(int key) {
		if (size == heapArray.length) {
			resize(2 * size);
		}
		heapArray[size] = key;
		trickleUp(size);
		size++;
	}
	
	private void trickleUp(int index) {
		int parent = (index - 1) / 2;
		int bottom = heapArray[index];
		while (index > 0 && heapArray[parent] < bottom) {
			heapArray[index] = heapArray[parent];
			index = parent;
			parent = (parent - 1) / 2;
		}
		heapArray[index] = bottom;
	}
	
	public int remove() {
		int result = heapArray[0];
		heapArray[0] = heapArray[--size];
		trickleDown(0);
		if (size > 0 && size == heapArray.length / 4) {
			resize(heapArray.length / 2);
		}
		return result;
	}
	
	private void trickleDown(int index) {
		int top = heapArray[index];
		int largerChild;
		while (index < size / 2) {
			int l = 2 * index + 1;
			int r = 2 * index + 2;
			
			//if right child is larger
			if (r < size && heapArray[r] > heapArray[l]) {
				largerChild = r;
			}
			//if left one is larger
			else {
				largerChild = l;
			}
			
			//is this larger child even larger than the top?
			if (heapArray[largerChild] > top) {
				heapArray[index] = heapArray[largerChild];
				index = largerChild;
			}
			else {
				break;
			}
		}
		heapArray[index] = top;
	}
	
	public boolean keyChange(int index, int newValue) {
		if (index < 0 || index >= size) {
			return false;
		}
		int oldValue = heapArray[index];
		heapArray[index] = newValue;
		if (oldValue < newValue) {
			trickleUp(index);
		}
		else {
			trickleDown(index);
		}
		return true;
	}
	
}
