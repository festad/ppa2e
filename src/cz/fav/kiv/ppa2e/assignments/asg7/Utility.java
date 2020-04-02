package cz.fav.kiv.ppa2e.assignments.asg7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.StringTokenizer;

public class Utility {

	public static String readContent(String path, String filename) {
		Path file = FileSystems.getDefault().getPath(path, filename);
		StringBuilder sb = new StringBuilder();
		try (InputStream in = Files.newInputStream(file);
			    BufferedReader reader =
			      new BufferedReader(new InputStreamReader(in))) {
			    String line = null;
			    while ((line = reader.readLine()) != null) {
			    	sb.append(line + "\n");
			    }
			    return sb.toString();
			} catch (IOException x) {
			    System.err.println(x);
			}
		return null;
	}
	
	public static String[] readTokens(String content) {
		if (content == null)
			content = readContent("res", "callCentrum.txt");
		StringTokenizer tk = new StringTokenizer(content);
		while (tk.hasMoreTokens()) {
			System.out.println(tk.nextToken());
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		readTokens(readContent("res", "callCentrum.txt"));
		writeContent("res", "dispatch.txt", "czesc", "append");
	}
	
	public static void writeContent(String path, String filename, String content, String mode) {
		Path file = FileSystems.getDefault().getPath(path, filename);
		switch (mode) {
		case "append":
			try (BufferedWriter writer = Files.newBufferedWriter(
					file, StandardOpenOption.APPEND)) {
					writer.write(content + "\n");
			} catch (IOException x) {
			    System.err.format("IOException: %s%n", x);
			}
			break;
		case "create":
			try (BufferedWriter writer = Files.newBufferedWriter(
					file, StandardOpenOption.CREATE)) {
					writer.write(content + "\n");
			} catch (IOException x) {
			    System.err.format("IOException: %s%n", x);
			}
			break;
			default:
				System.out.println("create or append");
		}
	}
	
}
