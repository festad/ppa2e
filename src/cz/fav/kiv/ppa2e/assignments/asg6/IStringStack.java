package cz.fav.kiv.ppa2e.assignments.asg6;

/**
 * Stack for storing strings
 * @author Libor Vasa
 */
public interface IStringStack {
  /**
   * Adds a string to the stack
   * @param s a string that is to be added to the stack
   */
  void add(String s);
  
  /**
   * Returns a string at the top of the stack
   * @return the string at the top of the stack
 * @throws Exception 
   */
  String get() throws Exception;
  
  /**
   * Removes the last (top) element of the stack
 * @throws Exception 
   */
  void removeLast() throws Exception;
  
  boolean isEmpty();
  
}