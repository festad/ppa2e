package cz.fav.kiv.ppa2e.assignments.asg9;

import java.util.Random;

public class Thumbnails {
	
	private static final int n = 100000;
	
	public static void main(String[] args) {
		HashTable table = new HashTable(100);
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
	}
	
	private static String RandomImageName() {
		Random r = new Random();
		int year = 2005 + r.nextInt(13);
		int month = 1+r.nextInt(12);
		int day = 1+r.nextInt(28);
		int img = 1+r.nextInt(9999);
		return String.format("c:\\fotky\\%d-%02d-%02d\\IMG_%04d.CR2", year, month, day, img);
	}
}
