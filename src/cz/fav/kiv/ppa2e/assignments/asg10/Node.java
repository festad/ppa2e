package cz.fav.kiv.ppa2e.assignments.asg10;

/**
 * Node of a Binary Search Tree (BST) with website names
 * @author Libor Vasa
 */
class Node {
	/** Key - website name */
	public String key;
	/** Left child */   
	public Node left;
	/** Right child */ 
	public Node right;
  
	/**
	 * Creates a new node of the BST
	 * @param key - website name
	 */
	public Node(String key) {
		this.key = key;
	}	
	
	public String toString() {
		return key;
	}
}