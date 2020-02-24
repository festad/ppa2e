package cz.fav.kiv.ppa2e.asg1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class Asg1ReadFilesFromPortalWithCompletelyRandomQeries {
	public static void main(String[] args) {
		
		int nrandoms = 10000;
		Path file = FileSystems.getDefault().getPath("res", "reading-from-portal.txt");
		try (BufferedWriter writer = Files.newBufferedWriter(
				file, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
			for (int i = 1; i <= 10; i++) {
				String filename = "seq" + i + ".txt";
				writer.write(""
						+ "-----------------------------------\n"
						+ "Processing file " + filename + "\n");
				int data[] = Asg1.arrayFromFile("res", filename);
				long start_isSorted = System.nanoTime();
				boolean is_sorted = Asg1.isSorted(data);
				long stop_isSorted = System.nanoTime();
				long isSortedEffort = stop_isSorted - start_isSorted;
				writer.write("Is it really sorted? " + is_sorted + "\n"
						+ "Time required: " + isSortedEffort + "\n");
				if (is_sorted) {
					writer.write("Number of elements: " + data.length + "\n");
					long linear_time_sum = 0;
					long subdivision_time_sum = 0;
					for (int j = 0; j < nrandoms; j++) {
						int random = new Random().nextInt();
						long start_linear = System.nanoTime();
						boolean linearLocation = Asg1.sequentialSearch(data, random);
						long stop_linear = System.nanoTime();
						long linear_effort = stop_linear - start_linear;
						writer.write("Linear search of #" + (j+1) + " random number " + random + " results in: " + linearLocation
								+ "\nTime effort: " + linear_effort + "\n");
						System.out.println("Linear search of #" + (j+1) + " random number " + random + " results in: " + linearLocation
								+ "\nTime effort: " + linear_effort + "\n");
						linear_time_sum += linear_effort;
						System.out.println("start interval_subdivision");
						long start_subdivision = System.nanoTime();
						boolean subdivisionLocation = Asg1.intervalSubdivision(data, random);
						long stop_subdivision = System.nanoTime();
						System.out.println("end interval subdivision");
						long subdivision_effort = stop_subdivision - start_subdivision;
						writer.write("Subdivision search of #" + (j+1) + " random number " + random + " results in: " + subdivisionLocation
								+ "\nTime effort: " + subdivision_effort + "\n");
						System.out.println("Subdivision search of #" + (j+1) + " random number " + random + " results in: " + subdivisionLocation
								+ "\nTime effort: " + subdivision_effort + "\n");
						subdivision_time_sum += subdivision_effort;
					}
					writer.write("Linear time for " + nrandoms + " random searches: " + linear_time_sum + "\n");
					writer.write("Subdivision time for " + nrandoms + " random searches: " + subdivision_time_sum + "\n");
					if (linear_time_sum >= subdivision_time_sum)
						writer.write("Linear loses: " + ((float) linear_time_sum) / ((float) subdivision_time_sum) + " times slower!\n");
					else
						writer.write("Subdivision loses: " + ((float) subdivision_time_sum) / ((float) linear_time_sum) + " times slower!\n");

				}
				System.out.println("End " + i);
			}
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
}
