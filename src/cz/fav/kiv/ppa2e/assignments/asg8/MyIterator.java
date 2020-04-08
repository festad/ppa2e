package cz.fav.kiv.ppa2e.assignments.asg8;

class MyIterator {
	
	Link current;
	LinkList list;

	public MyIterator(LinkList list){
		this.list = list;
		current = null;	
	}
	
	void insert(char letter) {
		Link newLink = new Link();
		newLink.data = letter;
		if (current == null) {
			newLink.next = list.first;
			list.first = newLink;
		}
		else {
			newLink.next = current.next;
			current.next = newLink;
		}
	}
	
	void next() throws Exception {
		if (current == null){
			current = list.first;
		}
		else {
			current = current.next;
			if (current == null)
				throw new Exception();
		}
	}
	
	char get() throws Exception {
		if (list.first == null) {
			throw new Exception("First element is null");
		}
		if (current == null) {
			return list.first.data;
		}
		if (current.next == null) {
			throw new Exception("Next element is null");
		}
		return current.next.data;
	}
	
	void moveToFirst() {
		current = null;
	}

	boolean hasNext() {
		if (current == null) {
			if (list.first != null)
				return true;
			else return false;
		}
		if (current.next!=null)
			return true;
		else return false;
	}
	
	void remove() throws Exception {
		if (list.first == null) {
			throw new Exception();
		}
		if (current == null) {
			list.first = list.first.next;
		}
		else if (current.next == null) {
			throw new Exception();
		}
		else {
			current.next = current.next.next;
		}
	}
	
	String printList() throws Exception {
		Link c = current;
		moveToFirst();
		StringBuilder s = new StringBuilder();
		if (hasNext()) {
			s.append(get());
			next();
		}
		while (hasNext()) {
			s.append(" -> "+get());
			next();
		}
		current = c;
		return s.toString();
	}
	
}
