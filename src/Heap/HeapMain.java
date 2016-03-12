package Heap;

import java.util.Random;

public class HeapMain {
	public static void main(String[] args) {
		Heap myHeap = new Heap();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			int x = rand.nextInt(50);
			myHeap.insert(x);
			System.out.print(x + " ");
		}
		System.out.println();
		while (!myHeap.isEmpty()) {
			System.out.print(myHeap.remove() + " ");
		}
	}
}
