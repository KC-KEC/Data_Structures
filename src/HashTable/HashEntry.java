package HashTable;
/**
 * 
 * Implement each entry in the hash table.
 * Each entry contains two fields: 
 * 	int val;
 * 	boolean isActive;
 * 
 * This class is mainly designed for deleting method.
 *
 */
public class HashEntry {
	private int val;
	private boolean isActive;
	
	public HashEntry(int val) {
		this(val, true);
	}
	public HashEntry(int val, boolean isActive) {
		this.val = val;
		this.isActive = isActive;
	}
	
	public int getVal() {
		return this.val;
	}
	
	public boolean isActive() {
		return this.isActive;
	}
}
