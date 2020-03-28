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
	  int n_data = 10_000;
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
  
}

