package cz.fav.kiv.ppa2e.assignments.asg1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class Asg1LinearSearchVsSubdivision {
	public static void main(String[] args) {
		
		new Thread(() -> {
			while (true) {
				try {
					System.out.print(". ");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		Path file = FileSystems.getDefault().getPath("res", "random-test-result.txt");
		try (BufferedWriter writer = Files.newBufferedWriter(
				file, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
			int nsequences = 1;
			int minimal_length = 5000;
			int maximal_length = 5001;
			writer.write(""
					+ "Generating " + nsequences + " sequences and looking for a random number with two techniques\n"
					+ "----------------------------------------------------------------------------\n");
			for (int i = 0; i < nsequences; i++) {
				writer.write(""
						+ "Sequence number " + (i+1) + "\n");
				int data[] =  Asg1.generateRandomIntegerArray(minimal_length, maximal_length);
				for (int j = 0; j < data.length; j++) {
					writer.write(data[j] + "\n");
				}
				writer.write(""
						+ "--------\n");
				int random = new Random().nextInt();
				writer.write("Is the sequence really sorted? " + Asg1.isSorted(data) + "\n");
//				writer.write("Looking for number " + random + "\n"); this almost everytime generates false
				int random_position = new Random().nextInt(data.length); // this is supposed to always generate true
				int number_in_random_position = data[random_position];
				writer.write("Looking for number in " + "[" + random_position + "]: "+ number_in_random_position + "\n");
				random = number_in_random_position;
				long start_linear = System.nanoTime();
				boolean result_linear_search = Asg1.sequentialSearch(data, random);
				long stop_linear = System.nanoTime();
				long linear = stop_linear - start_linear;
				writer.write("Linear search result: " + result_linear_search + "\n");
				writer.write("Time effort: " + linear + "\n");
				long start_subdivision = System.nanoTime();
				boolean result_subdivision_search = Asg1.intervalSubdivision(data, random);
				long stop_subdivision = System.nanoTime();
				long subdivision = stop_subdivision - start_subdivision;
				writer.write("Interval subdivision search result: " + result_subdivision_search + "\n");
				writer.write("Time effort: " + subdivision + "\n");
				if ((subdivision >= linear))
					writer.write("Subdivision loses: " + ((float)subdivision) / ((float)linear) + " times slower!\n");
				else
					writer.write("Linear loses: " + ((float)linear) / ((float)subdivision) + " times slower!\n");
				writer.write("-----------------------------\n");
				System.out.println("End");
			}
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
}
