package cz.fav.kiv.ppa2e.assignments.asg6;

/**
 * Implementation of a stack using an array
 * @author Libor Vasa
 */
public abstract class StackArray implements IStringStack {
	
	  /** Data in stack */
	  protected String[] data;
	  
	  /** Index where a new element can be put */
	  private int freeIndex;
	  
	  private static final int INITIAL_SIZE = 5;
	  	  
	  /**
	   * Creates an empty stack
	   */
	  public StackArray() {
	    data = new String[INITIAL_SIZE];
	    freeIndex = 0;
	  }
	  
	  public void add(String s) {
		  if (freeIndex == data.length)
			  expandArray();
	    data[freeIndex++] = s;
	  }
	  
	  protected abstract void expandArray();
	  
	  public String get() throws Exception {
		  if (freeIndex == 0)
			  throw new Exception();
		  return data[freeIndex - 1];
	  }
	
	  public void removeLast() throws Exception {
		  if (freeIndex == 0)
			  throw new Exception();
		  freeIndex--;
	  }  
	  
	  public boolean isEmpty() {
		  return freeIndex == 0;
	  }
	  
	  public String toString() {
		  int i = freeIndex - 1;
		  StringBuilder  sb = new StringBuilder();
		  while (i >= 0) {
			  sb.append(data[i] + "\n");
			  i--;
		  }
		  return sb.toString();
	  }
	  
}

