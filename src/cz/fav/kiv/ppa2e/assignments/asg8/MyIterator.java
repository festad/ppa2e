package cz.fav.kiv.ppa2e.assignments.asg8;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

class MyIterator {
	
	Link current;
	LinkList list;

	public MyIterator(LinkList list){
		this.list = list;
		current = null;	
	}
	
	void insert(char letter) throws Exception {
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
//		System.out.println(printState());
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
	
	void moveToFirst() throws Exception {
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
//		System.out.println(printState());
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
	
	private String printState() throws Exception {
		
		Link currenLink = current;
		
		class InnerIterator {
			Link current;
			LinkList list;
			
			public InnerIterator(LinkList list){
				this.list = list;
				current = null;	
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
		}
		
		InnerIterator ii = new InnerIterator(list);
		ii.current = null;
		StringBuilder s = new StringBuilder();
		if (ii.hasNext()) {
			s.append(ii.get());
			ii.next();
		}
		while (ii.hasNext()) {
			s.append(" -> "+ii.get());
			ii.next();
		}
		current = currenLink;
		return s.toString();
	}
	
	void printListToFile(String path, String filename) throws Exception {
		Path file = FileSystems.getDefault().getPath(path, filename);
		try (BufferedWriter writer = Files.newBufferedWriter(
				file, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			Link c = current;
			moveToFirst();
			if (hasNext()) {
				writer.write(get());
				next();
			}
			while (hasNext()) {
				writer.write(get());
				next();
			}
			current = c;
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	
}
