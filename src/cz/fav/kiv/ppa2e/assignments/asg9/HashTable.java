package cz.fav.kiv.ppa2e.assignments.asg9;

class HashTable {
	Entry[] data;
	int[] collisions;
	
	int ncollisions = 0;
	
	public HashTable(int capacity) {		
		data = new Entry[capacity];
		collisions = new int[capacity];
	}
	
	public void add(String key, int value) {
		Entry newEntry = new Entry(key, value);
		int index = getHashCode(key);
		if (data[index] != null) {
			ncollisions++;
			collisions[index]++;
		}
		newEntry.next = data[index];
		data[index] = newEntry;
	}
	
	public int get(String key) {
		int hash = getHashCode(key);
		Entry current = data[hash];
		while (current!=null) {
			if (current.fileName.equals(key))
				return (current.indexInDatabase);
//			System.err.println("collision");
			current = current.next;
		}
		return -1;
	}
	
	int getHashCode(String s){
		return Math.abs(s.hashCode() % data.length);
	}
	
	public String toString() {
		StringBuffer b = new StringBuffer();
		for (Entry entry : data) {
			if (entry != null)
				b.append(entry.toString() + "\n");
		}
		return b.toString();
	}
	
	public String printLinks() {
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			if (data[i] != null)
				b.append("#"+ i + " => " + collisions[i] + " links\n");
		}
		return b.toString();
	}
}