package cz.fav.kiv.ppa2e.assignments.asg11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ShortestPathSearch {

	public static void main(String[] args) throws Exception {
//		Graph g = new Graph();
//		g.initialize(20);
//		g.addEdge(0, 1);
//		g.addEdge(0, 5);
//		g.addEdge(1, 7);
//		g.addEdge(1, 2);
//		g.addEdge(2, 8);
//		g.addEdge(8, 2);
//		g.addEdge(4, 9);
//		System.out.println(g);
//		//System.out.println(g.shortestPath(15,19));
//		System.out.println(g.shortestPath(0, 8));
//		Graph g = initialize_map();
//		System.out.println(g);
//		System.out.println(g.shortestPath(0, 19));
//		System.out.println(g.farthest(4) + ": " + g.shortestPath(4, g.farthest(4)));
//		System.out.println(graphFromMatrix(handle_requests()));
		List<char[]> matrix = readMapFromFile();
		Graph g = graphFromMatrix(matrix);
		
		while (true) {
			try {
				System.out.print("Enter starting point: (-1 to exit)\n-> ");
				Scanner sc = new Scanner(System.in);
				int s = sc.nextInt();
				if (s == -1)
					break;
				int k = g.farthest(s);
				int distance = g.shortestPath(s, k);
				System.out.printf("%d -> %d = %d%n", s, k, distance);
				write_result(matrix, s, k);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static Graph initialize_map() {
		Graph g = new Graph();
		g.initialize(20);
		g.addEdge(0, 1);
		g.addEdge(0, 5);
		g.addEdge(1, 2);
		g.addEdge(1, 7);
		g.addEdge(2, 7);
		g.addEdge(2, 8);
		g.addEdge(4, 9);
//		-------
		g.addEdge(5, 10);
		g.addEdge(7, 8);
		g.addEdge(7, 12);
		g.addEdge(8, 9);
		g.addEdge(8, 12);
		g.addEdge(8, 13);
		g.addEdge(9, 13);
		g.addEdge(9, 14);
//		-------
		g.addEdge(10, 15);
		g.addEdge(12, 13);
		g.addEdge(12, 17);
		g.addEdge(12, 18);
		g.addEdge(13, 14);
		g.addEdge(13, 18);
		g.addEdge(13, 19);
		g.addEdge(14, 19);
//		-------
		g.addEdge(17, 18);
		g.addEdge(18, 19);
		return g;
		
	}
	
	private static List<char[]> readMapFromFile() throws Exception {
		Path file = FileSystems.getDefault().getPath("res", "map.txt");
//		int k = 1;
		try (InputStream in = Files.newInputStream(file);
			    BufferedReader reader =
			      new BufferedReader(new InputStreamReader(in))) {
			String line = null;
			ArrayList<char[]> array = new ArrayList<char[]>();
		    while ((line = reader.readLine()) != null) {
//		    	System.out.println("processing: " + k);
		    	char[] row = new char[line.length()];
		    	for (int i = 0; i < row.length; i++) {
					row[i] = line.charAt(i);
				}
		    	array.add(row);
//		    	k++;
		    }
		    return array;
		} catch (IOException x) {
			    System.err.println(x);
		}
		return null;
	}
	
	private static Graph graphFromMatrix(List<char[]> array) {
		Graph g = new Graph();
		g.initialize(array.size() * array.get(0).length);
		int index = 0;
		for (int i = 0; i < array.size() - 1; i++) {
			if (array.get(i)[0] == 'a') {
				if (array.get(i)[1] == 'a')
					g.addEdge(index, index + 1);
				if (array.get(i + 1)[0] == 'a')
					g.addEdge(index, index + array.get(i).length);
				if (i == 0 || i % 2 == 0) {
					if (array.get(i + 1)[1] == 'a')
						g.addEdge(index, index + array.get(i).length + 1);
				}
			}
			index += 1;
			for (int j = 1; j < array.get(i).length - 1; j++) {
//				if (array.get(i)[j] == 'u') {
//					index += 1;
//					continue;
//				}
				if (array.get(i)[j] == 'a') {
					if (array.get(i)[j + 1] == 'a')
						g.addEdge(index, index + 1);
					if (array.get(i + 1)[j] == 'a')
						g.addEdge(index, index + array.get(i).length);
					if (i == 0 || i % 2 == 0) {
						if (array.get(i + 1)[j + 1] == 'a') {
							g.addEdge(index, index + array.get(i).length + 1);
						}
					}
					else {
						if (array.get(i + 1)[j - 1] == 'a') {
							g.addEdge(index, index + array.get(i).length - 1);
						}
					}
				}
				index += 1;
			}
			if (array.get(i)[array.get(i).length - 1] == 'a') {
				if (array.get(i + 1)[array.get(i).length - 1] == 'a')
					g.addEdge(index, index + array.get(i).length);
				if (i % 2 == 1) {
					if (array.get(i + 1)[array.get(i).length - 1 - 1] == 'a')
						g.addEdge(index, index + array.get(i).length - 1);
				}
			}
			index += 1;
		}
		for (int i = 0; i < array.get(array.size() - 1).length - 1; i++) {
//			if (array.get(array.size() - 1)[i] == 'u') {
//				index += 1;
//				continue;
//			}
			if (array.get(array.size() - 1)[i] == 'a') {
				if (array.get(array.size() - 1)[i + 1] == 'a')
					g.addEdge(index, index + 1);
			}
			index += 1;
		}
		return g;
	}
	
	private static void write_result(List<char[]> array, int start, int farthest) {
		Path file = FileSystems.getDefault().getPath("res", "result.txt");
//		int k = 1;
		try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			int index = 0;
			for (int i = 0; i < array.size(); i++) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < array.get(i).length; j++) {
					if (index == start) {
						sb.append('s');
					} else if (index == farthest) {
						sb.append('k');
					} else {
						if (array.get(i)[j] == 'a')
							sb.append(' ');
						else if (array.get(i)[j] == 'u') {
							sb.append('x');
						}
//						sb.append(array.get(i)[j]);
					}
					index += 1;
				}
				writer.write(sb.toString() + "\n");
//				System.out.println(sb.toString());
			}
		} catch (IOException x) {
			    System.err.println(x);
		}
	}
}
