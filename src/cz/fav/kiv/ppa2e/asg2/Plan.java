package cz.fav.kiv.ppa2e.asg2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.IllegalFormatException;
import java.util.IllegalFormatWidthException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collector;
import java.util.stream.Collectors;



public class Plan {
	
	private static final int MIN_MATH = 3;
	private static final int MIN_COMPUTER = 2;
	private static final int LINES_FOR_EVENT = 5;
	
	private Set<PlanEvent> plan = new HashSet<PlanEvent>();
	
	public Plan(Set<PlanEvent> plan) {
		super();
		this.plan = plan;
	}

	public boolean isConflict () {
		boolean is_conflict = false;
		for (PlanEvent planEvent : plan) 
			for (PlanEvent planEvent2 : plan) 
				if (!planEvent.equals(planEvent2) && planEvent.isInConflict(planEvent2))
					is_conflict = true;
		return is_conflict;
	}

	public Set<PlanEvent> whichAreConflicted () {
		for (PlanEvent planEvent : plan) 
			for (PlanEvent planEvent2 : plan) 
				if (planEvent.isInConflict(planEvent2)) {
					planEvent.setConflicted(true);
					planEvent2.setConflicted(true);
				}
		return plan.stream()
				.filter(PlanEvent::isConflicted)
				.collect(Collectors.toSet());
	}
	
	public boolean isOk () {
		boolean mathok = false;
		boolean computerok = false;
		if (anyDuplicate(plan.stream()
				.filter(event -> event.getSubject().equals(Subject.math))
				.mapToInt(PlanEvent::getDayOfWeek)
				.toArray()) && 
				plan.stream()
				.filter(event -> event.getSubject().equals(Subject.math))
				.count() >= MIN_MATH)
			mathok = true;
		if (anyDuplicate(plan.stream()
				.filter(event -> event.getSubject().equals(Subject.computers))
				.mapToInt(PlanEvent::getDayOfWeek)
				.toArray()) && 
				plan.stream()
				.filter(event -> event.getSubject().equals(Subject.computers))
				.count() >= MIN_COMPUTER)
			computerok = true;
			
		return mathok && computerok;
	}
	
	public static boolean sequentialSearch(int [] data, int x) {
		 for (int i = 0; i < data.length; i++) 
			if (x == data[i])
				return true;
		 return false;
	 }
	
	public static boolean anyDuplicate (int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (sequentialSearch(array, i))
				return true;
		}
		return false;
	}
	
	public static Set<PlanEvent> readEventsFromFile (String stringpath, String filename) throws IllegalFormatException {
		Set<PlanEvent> events = new HashSet<PlanEvent>();
		Path path = FileSystems.getDefault().getPath(stringpath, filename);
		try (InputStream in = Files.newInputStream(path);
				 BufferedReader reader =
						 new BufferedReader(new InputStreamReader(in))) {
				String line = null;
				StringBuilder builder = new StringBuilder();
				int counter = 0;
				while ((line = reader.readLine()) != null) {
					builder.append(line.concat("\n"));
					counter++;
				}
				String content = builder.toString();
				StringTokenizer tokenizer = new StringTokenizer(content, "\n");
				if (counter % LINES_FOR_EVENT != 0)
					throw new IllegalFormatWidthException(counter);
				for (int i = 0; i < (counter / LINES_FOR_EVENT); i++) {
					String name = tokenizer.nextToken();
					String subject = tokenizer.nextToken();
					int day = Integer.parseInt(tokenizer.nextToken());
					int start = Integer.parseInt(tokenizer.nextToken());
					int end = Integer.parseInt(tokenizer.nextToken());
					events.add(new PlanEvent(name, start, end, day, Subject.valueOf(subject)));
				}
//				System.out.println(counter);
//				System.out.println(events);
				return events;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return events;
	}
	
	public static Set<Set<PlanEvent>> allPossibleSetsOfFive (Set<PlanEvent> events) {
		Set<Set<PlanEvent>> set = new HashSet<Set<PlanEvent>>();
		for (PlanEvent planEvent1 : events) {
			for (PlanEvent planEvent2 : events) {
				for (PlanEvent planEvent3 : events) {
					for (PlanEvent planEvent4 : events) {
						for (PlanEvent planEvent5 : events) {
							Set<PlanEvent> fiveevents = new HashSet<PlanEvent>();
							fiveevents.add(planEvent1);
							fiveevents.add(planEvent2);
							fiveevents.add(planEvent3);
							fiveevents.add(planEvent3);
							fiveevents.add(planEvent4);
							fiveevents.add(planEvent5);
							if (fiveevents.size() == 5)
								set.add(fiveevents);
						}
					}
				}
			}
		}
		return set;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		plan.forEach(event -> {
			builder.append("\n"+event.toString()+"\n------------------------");
		});
		return builder.toString();
	}

	public static void main(String[] args) {
		Set<PlanEvent> events = readEventsFromFile("res", "ssc.txt");
		Set<Plan> plans = allPossibleSetsOfFive(events).stream()
				.map(lot -> new Plan(lot))
				.collect(Collectors.toSet());
//		System.out.println(plans.stream().collect(Collectors.toList()).get(1));
		System.out.println("In total there are " + plans.size() + " plans of five events each");
		System.out.println(plans.stream().filter(Plan::isConflict).count() + " of them present conflict");
		System.out.println(plans.stream().filter(p -> (p.isConflict() == false)).count() + " of them do not overlap");
		System.out.println(plans.stream().filter(p -> p.isOk()).count() + " of them are ok");
		System.out.println(plans.stream().filter(p -> !p.isOk()).count() + " are not");
		Set<Plan> okPlans = plans.stream().filter(p -> !p.isConflict() && p.isOk()).collect(Collectors.toSet());
		StringBuilder builder = new StringBuilder();
		okPlans.forEach(p -> {
			builder.append("\n" + p.toString() + "\n=============================");
		});
		System.out.println(okPlans.size() + " are ok and without overlaps, and they are >>>\n" + builder.toString());
	}
	
}
