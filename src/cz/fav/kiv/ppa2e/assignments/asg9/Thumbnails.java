package cz.fav.kiv.ppa2e.assignments.asg9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class Thumbnails {
	
	private static final int n = 100_000;
	
	public static void main(String[] args) {
		
		HashTable table = new SmartHashTable(100);
//		table.add("c:\\img3451.jpg", 0);
//		table.add("c:\\img7657.jpg", 1);
//		table.add("c:\\img0987.jpg", 2);
		for (int i = 0; i < n; i++) {
			table.add(RandomImageName(), i);
		}
		System.out.println(table);
//		System.out.println(table.get("c:\\img3451.jpg"));
//		System.out.println(table.get("c:\\img7657.jpg"));
		System.out.println(table.ncollisions);
		System.out.println();
		System.out.println(table.printLinks());
		
		System.out.println("=====================");
		
		String[] keys = readKeys(countLines());
		
		int[] capacities = new int[] {1000, 1009, 30030, 100000, 100003};
		
		for (int i = 0; i < capacities.length; i++) {
			
			System.out.println("C = " + capacities[i]);
			
			HashTable silly = new SillyHashTable(capacities[i]);
			HashTable smart = new SmartHashTable(capacities[i]);
			
			for (int j = 0; j < capacities[i]; j++) {
				silly.add(keys[j], 0);
				smart.add(keys[j], 0);
			}
			
			Random random = new Random();
			long start = System.nanoTime();
			int count_silly = 0;
			while (System.nanoTime() - start < 2_000_000_000) {
				String key = keys[random.nextInt(n)];
				if (!silly.isThere(key)) 
					silly.add(key, 0);
				count_silly++;
			}
			System.out.println("Silly hashtable -> " + count_silly + " queries");
			long start2 = System.nanoTime();
			int count_smart = 0;
			while (System.nanoTime() - start2 < 2_000_000_000) {
				String key = keys[random.nextInt(n)];
				if (!smart.isThere(key)) 
					smart.add(key, 0);
				count_smart++;
			}
			System.out.println("Smart hashtable -> " + count_smart + " queries");
			System.out.println("----------------");
			
		}
		
		
		
	}
	
	private static String RandomImageName() {
		Random r = new Random();
		int year = 2005 + r.nextInt(13);
		int month = 1+r.nextInt(12);
		int day = 1+r.nextInt(28);
		int img = 1+r.nextInt(9999);
		return String.format("c:\\fotky\\%d-%02d-%02d\\IMG_%04d.CR2", year, month, day, img);
	}
	
	private static int countLines() {
		Path file = FileSystems.getDefault().getPath("res", "ImageNames.txt");
		int n = 0;
		try (InputStream in = Files.newInputStream(file);
			    BufferedReader reader =
			      new BufferedReader(new InputStreamReader(in))) {
				String line = null;
			    while ((line = reader.readLine()) != null) {
			    	n++;
			    }
			} catch (IOException x) {
			    System.err.println(x);
			}
		return n;
	}
	
	private static String[] readKeys(int nkeys) {
		Path file = FileSystems.getDefault().getPath("res", "ImageNames.txt");
		String[] keys = new String[nkeys];
		try (InputStream in = Files.newInputStream(file);
			    BufferedReader reader =
			      new BufferedReader(new InputStreamReader(in))) {
			    for (int i = 0; i < nkeys; i++) {
			    	keys[i] = reader.readLine();
			    }
			} catch (IOException x) {
			    System.err.println(x);
			}
		return keys;
	}
}
