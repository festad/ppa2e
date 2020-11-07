package cz.fav.kiv.ppa2e.assignments.asg10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class for autocompleting text based on history
 * @author Libor Vasa
 */
public class Autocomplete {
	public static void main(String[] args) throws Exception {
		BinarySearchTree bst = new BinarySearchTree();
		bst.add("http://portal.zcu.cz");
		bst.add("http://courseware.zcu.cz");
		bst.add("dto");
		bst.add("dtoo");
		bst.add("dtoc");
		bst.add("dtxo");
		bst.add("htto");
		bst.add("httz");
		bst.add("htt");
		bst.add("http");
		System.out.println(bst);
		System.out.println(bst.contains("http://courseware.zcu.cz"));
		System.out.println(bst.remove("http://courseware.zcu.cz"));
		System.out.println(bst.printAllStartingWith("htt") + " " + bst.nhits);
		
		handle_requests();
		
	}
	
	private static BinarySearchTree handle_requests() throws Exception {
		BinarySearchTree bst = new BinarySearchTree();
		Path file = FileSystems.getDefault().getPath("res", "requests.txt");
//		int k = 1;
		try (InputStream in = Files.newInputStream(file);
			    BufferedReader reader =
			      new BufferedReader(new InputStreamReader(in))) {
			String line = null;
		    while ((line = reader.readLine()) != null) {
//		    	System.out.println("processing: " + k);
		    	switch (line.charAt(0)) {
		    	
				case 'A':
					System.out.println("Adding:   " + line.substring(2));
					bst.add(line.substring(2));
					break;
					
				case 'R':
					System.out.println("Removing: " + line.substring(2));
					if(!bst.remove(line.substring(2))) {
						throw new Exception(
								"Issues during removal of " + line.substring(2));
					};
					break;
					
				case 'P':
					System.out.println("Querying: " + line.substring(2));
					System.out.println(
							String.format(""
									+ "---> %d/%d/%d"
									+ "\n==============", 
									bst.printAllStartingWith(
											line.substring(2)), 
									bst.getSortedKeys().size(),
									bst.nhits));
					break;
				default:
					break;
				}
//		    	k++;
		    }
		} catch (IOException x) {
			    System.err.println(x);
		}
		return bst;
	}
}

