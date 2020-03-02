package cz.fav.kiv.ppa2e.asg1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Random;

public class Asg1 {

	/**
	 * The method decides whether the passed x value is present in the given ordered array
	 * @param data input array of values that are sorted from the smallest to the largest
	 * @param x value sought
	 * @return true if x is in the data, otherwise false
	 */
	public static boolean intervalSubdivision (int [] data, int x) {
		int left = 0; // left interval boundary
		int right = data.length - 1; // upper interval boundary
		while (left <= right) { 
			int mid = (left + right) / 2;
			if (data [mid] > x) 
				right = mid - 1;
			else if (data [mid] < x)
				left = mid + 1;
			else
				return true;
		}
		return false;
	}

	public static void main(String [] args) {
		int [] data = new int[] {1, 3, 5, 41, 48, 52, 63, 71};
		int x = 72;
		long start = System.nanoTime();
		boolean found = intervalSubdivision (data, x);
		long stop = System.nanoTime();
		System.out.println ("Interval subdivision finished in " + (stop - start) + "ns");
		System.out.println ("Number found:" + found);
		data = arrayFromFile("res", "numbers.txt");
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
		System.out.println(sequentialSearch(new int[] {1,2,3}, 5));
		arrayToFile("res", "written.txt", new int[] {1,2,3,4,5});
		
		data = generateRandomIntegerArrayWithoutSorting(100, 1000); 
		for (int j = 0; j < data.length; j++) {
			System.out.println(data[j]);
		}
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
	 
	 public static int[] generateRandomIntegerArrayWithoutSorting(int minimalLength, int maximalLength) {
		 if (minimalLength > maximalLength) 
				throw new IllegalArgumentException("minimal length > maximal length");
		 int length = new Random().nextInt((maximalLength - minimalLength) + 1) + minimalLength;
			int[] data = new int[length];
			int precedent = 0;
			for (int i = 0; i < data.length; i++) {
				data[i] = new Random().nextInt(10) + 1 + precedent;
				precedent = data[i];
			}
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
	
	public static void arrayToFile (String path, String filename, int[] data) {
		Path file = FileSystems.getDefault().getPath(path, filename);
		try (BufferedWriter writer = Files.newBufferedWriter(
				file, StandardOpenOption.CREATE)) {
			for (int i = 0; i < data.length; i++) {
				writer.write(data[i] + "\n");
			}
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	
	public static boolean isSorted (int[] data) {
		int precedent = data[0];
		for (int i = 1; i < data.length; i++) {
			if (data[i] >= precedent) {
				precedent = data[i];
				continue;
			}
			else
				return false;
		}
		return true;
	}
}
