package cz.fav.kiv.ppa2e.assignments.asg9;

class Entry{
	public String fileName;	
	int indexInDatabase;
	public Entry next;
		
	public Entry (String fn, int index) {
		fileName = fn;
		indexInDatabase = index;
	}
	
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append(indexInDatabase + " -> " + fileName);
		if (next != null) {
			b.append("\n" + next.toString());
		}
		return b.toString();
	}
	
	public int getLinks() {
		return get_links(0);
	}
	
	private int get_links(int current_links) {
		if (next != null)
			return next.get_links(current_links++);
		else
			return current_links;
	}
	
}