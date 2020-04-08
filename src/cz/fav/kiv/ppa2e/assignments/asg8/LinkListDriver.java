package cz.fav.kiv.ppa2e.assignments.asg8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.sound.midi.Soundbank;

public class LinkListDriver {

	public static void main(String[] args) throws Exception {
		
//		LinkList myList = new LinkList();
//		myList.insert('a');
//		myList.insert('k');
//		myList.insert('v');
//		myList.next();
//		myList.insert('o');
//		myList.next();
//		myList.insert('d');
//			
//		printList(myList);
//		
//		
//		myList.moveToFirst();
//		myList.insert('l');
//		myList.next();
//	    myList.remove();
//	    myList.next();
//		myList.remove();
//		myList.remove();
//		myList.remove();
//		myList.insert('e');
//	    myList.insert('v');
//	    
//	    printList(myList);
	    
	    MyIterator it = new MyIterator(new LinkList());
	    
	    it.insert('a');
		it.insert('k');
		it.insert('v');
		it.next();
		it.insert('o');
		it.next();
		it.insert('d');
			
		System.out.println("List: "+it.printList());
				
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
	    
		System.out.println("List: "+it.printList());
		
		
		LinkList l = new LinkList();
		MyIterator i = new MyIterator(l); 
		
		readCharByChar("res", "input.txt", i);
		
		System.out.println("Finished reading");
		
		instructionByLine("res", "instructions.txt", i);
		
		i.printListToFile("res", "output.txt");
		
		System.out.println("Finished writing");
		
		
	}
	    
	
	
//	static void printList(LinkList l) throws Exception {
//		Link current = l.current;
//		l.moveToFirst();
//		if (l.hasNext()) {
//			System.out.print(l.get());
//			l.next();
//		}
//		while (l.hasNext()) {
//			System.out.print(" -> "+l.get());
//			l.next();
//		}
//		System.out.println();
//		l.current = current;
//	}
	
	public static void readCharByChar(String path, String filename, MyIterator it) throws Exception {
		Path file = FileSystems.getDefault().getPath(path, filename);
		try (InputStream in = Files.newInputStream(file);
			    BufferedReader reader =
			      new BufferedReader(new InputStreamReader(in))) {
			    String line = null;
			    while ((line = reader.readLine()) != null) {
			    	for (int i = 0; i < line.length(); i++){
			    	    char c = line.charAt(i);  
			    	    it.insert(c);
			    	    it.next();
			    	}
			    }
			} catch (IOException x) {
			    System.err.println(x);
			}
	}
	
	public static void instructionByLine(String path, String filename, MyIterator it) throws Exception {
		Path file = FileSystems.getDefault().getPath(path, filename);
		try (InputStream in = Files.newInputStream(file);
			    BufferedReader reader =
			      new BufferedReader(new InputStreamReader(in))) {
			    String line = null;
			    while ((line = reader.readLine()) != null) {
			    	    char c = line.charAt(0);
			    	    switch (c) {
						case 'N':
							it.next();
							break;
						case 'B':
							it.moveToFirst();
							break;
						case 'R':
							it.remove();
							break;
						case 'I':
							it.insert(line.charAt(2));
//							System.out.println("inserted "+c);
							break;
						default:
							throw new IllegalArgumentException("Unexpected value: " + c);
						}
			    	}
			} catch (IOException x) {
			    System.err.println(x);
			}
	}
	
	
}
