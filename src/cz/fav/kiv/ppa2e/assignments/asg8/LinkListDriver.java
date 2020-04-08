package cz.fav.kiv.ppa2e.assignments.asg8;

public class LinkListDriver {

	public static void main(String[] args) throws Exception {
		LinkList myList = new LinkList();
		myList.insert('a');
		myList.insert('k');
		myList.insert('v');
		myList.next();
		myList.insert('o');
		myList.next();
		myList.insert('d');
			
		printList(myList);
		
//		System.out.println("printed success");
		
		myList.moveToFirst();
		myList.insert('l');
		myList.next();
	    myList.remove();
	    myList.next();
		myList.remove();
		myList.remove();
		myList.remove();
		myList.insert('e');
	    myList.insert('v');
	    
	    printList(myList);
	    
	    MyIterator it = new MyIterator(new LinkList());
	    it.moveToFirst();
	    it.insert('a');
		it.insert('k');
		it.insert('v');
		it.next();
		it.insert('o');
		it.next();
		it.insert('d');
			
		System.out.println(it.printList());
		
//		System.out.println("printed success");
		
		it.moveToFirst();
		it.insert('l');
		it.next();
	    it.remove();
	    it.next();
		it.remove();
		it.remove();
		it.remove();
		it.insert('e');
	    it.insert('v');
	    
	    System.out.println(it.printList());	    
	    
	}
	
	static void printList(LinkList l) throws Exception {
		Link current = l.current;
		l.moveToFirst();
		if (l.hasNext()) {
			System.out.print(l.get());
			l.next();
		}
		while (l.hasNext()) {
			System.out.print(" -> "+l.get());
			l.next();
		}
		System.out.println();
		l.current = current;
	}
	
}
