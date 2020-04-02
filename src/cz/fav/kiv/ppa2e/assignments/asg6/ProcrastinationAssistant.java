package cz.fav.kiv.ppa2e.assignments.asg6;

import java.util.Random;


/**
 * Procrastination reducing assistant
 * @author Libor Vasa
 */
class ProcrastinationAssistant {
	
  public static void main(String[] args) throws Exception {
    IStringStack stack = new StackArrayDoubleIncrement();
    stack.add("Learn to play ukulele");
    stack.add(randomString());
    System.out.println(stack);
    testStack(new StackArrayFixedIncrement());
    testStack(new StackArrayDoubleIncrement());
	  cli();
  }

  /**
   * Generates and returns a random string 
   * @return returns a random string of random characters (5 to 24 characters)
   */
  public static String randomString() {
    StringBuilder sb = new StringBuilder();
    Random r = new Random();
    for (int i = 0; i < (5 + r.nextInt(20)); i++) {
      sb.append((char) (r.nextInt(24) + 65));
    }
    return(sb.toString());
  }
  
  private static void testStack(IStringStack stack) throws Exception {
	  int n_data = 100_000;
	  String[] array = new String[n_data];
	  
	  for (int i = 0; i < n_data; i++) {
		  array[i] = randomString();
	  }
	  
	  long start = System.nanoTime();
	  
	  for (int i = 0; i < n_data; i++) {
		  stack.add(array[i]);
	  }
	  
//	  System.out.println(stack);
	  
	  for (int i = n_data - 1; i >= 0; i--) {
		  if (stack.get() != array[i])
			  throw new Exception("Test failed");
		  stack.removeLast();
	  }
	  
	  long stop = System.nanoTime();
	  
	  System.out.println("Test completed succesfully with " 
	  + n_data + " data in " + (stop - start) + " nanoseconds");
  }
  
  private static String user_input(String message) {
	  java.util.Scanner sc = new java.util.Scanner(System.in);
	  System.out.print(message + "\n-> ");
	  return sc.nextLine();
  }
  
  private static int cli() throws Exception {
	  IStringStack stack = new StackArrayDoubleIncrement();
	  String main_task = user_input("What needs to be done?");
	  if (main_task != "" || main_task != null)
		  stack.add(main_task);
	  else 
		  throw new Exception();
	  while (true) {
		  if (stack.isEmpty()) {
			  System.out.println("The main task " + main_task + " has been finished.");
			  return 0;
		  }
		  System.out.println("Current task: " + stack.get());
		  String choice = user_input("What do we do? (F = Finished, S = Subdivide)");
		  switch (choice) {
		case "F":
		case "f":
			stack.removeLast();
			break;
		case "S":
		case "s": 
			System.out.println("Please enter the sub-tasks, finished by an empty string");
			IStringStack substack = new StackArrayDoubleIncrement();
			stack.removeLast();
			while (true) {
				String subtask = user_input("");
				if (subtask.isBlank() || subtask.isEmpty() || subtask == "" || subtask == null) 
					break;
				substack.add(subtask);
			}
			while (!substack.isEmpty()) {
				stack.add(substack.get());
				substack.removeLast();
			}
			break;
		default:
			System.err.println("Enter proper input");
			break;
		}
	  }
  }
  
}

