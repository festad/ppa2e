package cz.fav.kiv.ppa2e.assignments.asg9;

public class SmartHashTable extends HashTable {

	
	
	public SmartHashTable(int capacity) {
		super(capacity);
	}

	int getHashCode(String s){
		return Math.abs(s.hashCode() % data.length);
	}
	
}
