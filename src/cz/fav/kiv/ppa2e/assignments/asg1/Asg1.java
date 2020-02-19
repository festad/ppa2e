package cz.fav.kiv.ppa2e.assignments.asg1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;

public class Asg1 {

	/**
	 * The method decides whether the passed x value is present in the given ordered array
	 * @param data input array of values that are sorted from the smallest to the largest
	 * @param x value sought
	 * @return true if x is in the data, otherwise false
	 */
	static boolean intervalSubdivision (int [] data, int x) {
		int left = 0; // left interval boundary
		int right = data.length - 1; // upper interval boundary
		int mid = (left + right) / 2; // index in the middle of the interval
		while (data [mid]!= x) { 
			if (left == right) 
				return (false);
			// Now reduce the interval
			if (data [mid]> x) 
				right = mid - 1;
			else 
				left = mid + 1;
			mid = (left + right) / 2;
//			beginning of my correction
			if (mid <= data.length - 1 
					&& mid >= 0 
					&& left <= data.length - 1
					&& right >= 0 
					)
				continue;
			else
				return false;
//			end of my correction
		}
		return true; //
	}

	public static void main(String [] args) {
		int [] data = new int[] {1, 2, 3, 100};
		int x = 120;
		long start = System.nanoTime();
		boolean found = intervalSubdivision (data, x);
		long stop = System.nanoTime();
		System.out.println ("Interval subdivision finished in" + (stop) + "ns");
		System.out.println ("Number found:" + found);
		data = arrayFromFile("files", "boh.txt");
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
		System.out.println(sequentialSearch(new int[] {1,2,3}, 5));
	}
	
	public static int[] generateRandomIntegerArray (int minimalLength, int maximalLength) {
		if (minimalLength > maximalLength) 
			throw new IllegalArgumentException("minimal length > maximal length");
		int length = new Random().nextInt((maximalLength - minimalLength) + 1) + minimalLength;
		int[] data = new int[length];
		for (int i = 0; i < data.length; i++) {
			boolean duplicate = false;
			int candidate = new Random().nextInt();
			for (int j = 0; j < i; j++) {
				if (candidate == data[j]) {
					duplicate = true;
					break;
				}
			}
			if (duplicate)
				i = i - 1;
			else
				data[i] = candidate;
		}
		Arrays.sort(data);
		return data;
	}
	
	 public static boolean sequentialSearch(int [] data, int x) {
		 for (int i = 0; i < data.length; i++) 
			if (x == data[i])
				return true;
		 return false;
	 }
	
	 public static int[] generateRandomIntegerArrayWithSequentialSearch (int minimalLength, int maximalLength) {
			if (minimalLength > maximalLength) 
				throw new IllegalArgumentException("minimal length > maximal length");
			int length = new Random().nextInt((maximalLength - minimalLength) + 1) + minimalLength;
			int[] data = new int[length];
			for (int i = 0; i < data.length; i++) {
				int candidate = new Random().nextInt();
				if (sequentialSearch(data, candidate))
					i = i - 1;
				else
					data[i] = candidate;
			}
			Arrays.sort(data);
			return data;
		}
	 
	public static int nLinesInFile (String path, String filename) {
		Path file = FileSystems.getDefault().getPath(path, filename);
		int count = 0;
		try (InputStream in = Files.newInputStream(file);
		    BufferedReader reader =
		      new BufferedReader(new InputStreamReader(in))) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	count++;
		    }
		    return count;
		} catch (IOException x) {
		    System.err.println(x);
		}
		return -1;
	}
	
	public static int[] arrayFromFile (String path, String filename) {
		int nlines = nLinesInFile(path, filename);
		int[] data = new int[nlines];
		int index = 0;
		Path file = FileSystems.getDefault().getPath(path, filename);
		try (InputStream in = Files.newInputStream(file);
		    BufferedReader reader =
		      new BufferedReader(new InputStreamReader(in))) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	data[index] = Integer.parseInt(line);
		    	index++;
		    }
		    return data;
		} catch (IOException x) {
		    System.err.println(x);
		}
		return data;
	}
}
