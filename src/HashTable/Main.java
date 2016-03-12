package HashTable;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		SeperateChaining lp = new SeperateChaining();
		Random rand = new Random();
		for (int i = 0; i < 20; i++) {
			int x = rand.nextInt(20);
			lp.add(x);
			System.out.print(x + " ");
		}
		
		System.out.println();
		
		for (int i = 0; i < 10; i++) {
		  int x = rand.nextInt(20);
		  lp.remove(x);
		  System.out.print(x + " ");
		}
		
		System.out.println();
		System.out.println("size: " + lp.size());
		for (int i = 0; i < 10; i++) {
			int x = rand.nextInt(20);
			System.out.println(x + " : " + lp.contains(x));
		}
	}
}
