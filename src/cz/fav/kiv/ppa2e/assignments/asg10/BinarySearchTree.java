package cz.fav.kiv.ppa2e.assignments.asg10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Binary Search Tree with website names
 * @author Libor Vasa
 */
class BinarySearchTree {
	/** root of the BST */
	Node root;
	
	int nhits = 0;
	
	/**
	 * Adds an element with  given key - website name
	 * @param key website name
	 */
	void add(String key){
		if (root == null) {
			root = new Node(key);
		}
		else {
			addUnder(root, key);
		}		
	}

	/**
	 * Adds under a given node a new node with the given key
	 * @param n the node, under which a new node should be added
	 * @param key website name of the new node
	 */
	void addUnder(Node n, String key) {
		if (key.compareToIgnoreCase(n.key) < 0) {
			// node belongs to the left, is there room?
			if (n.left == null) {
				n.left = new Node(key);
			}      
			else { 
				addUnder(n.left, key);
			}
		}
		else {
			// node belongs to the right, je tam misto?
			if (n.right == null) {
				n.right = new Node(key);
			}
			else { 
				addUnder(n.right, key);
			}
		}
	}
	
	
	public boolean contains(String key) {
		Node n = root;
		while (n != null) {
			if (n.key.equals(key))
				return true;
			if (key.compareTo(n.key)<0)
				n = n.left;
			else n = n.right;
		}
		return false;
	}
	
	
	public boolean remove(String key) {
		if (!contains(key))
			return false;
		Node n = root; // node we want to remove
		Node pred = null; // ancestor of the node we want to remove
		while(!key.equals(n.key)) {
			pred = n;
			if (key.compareTo(n.key)<0)
				n = n.left;
			else n = n.right;
		}
		if ((n.left == null)||(n.right==null)) {
			Node replacement = n.left;
			if (n.right != null)
				replacement = n.right;
			if (pred == null)
				root = replacement;
			else {
				if (pred.left == n)
					pred.left = replacement;
				else 
					pred.right = replacement;
			}
		}
		else {
			Node leftMax = n.left;
			Node leftMaxPred = n;
			while (leftMax.right!=null) {
				leftMaxPred = leftMax;
				leftMax = leftMax.right;
			}
			n.key = leftMax.key;
			if (leftMax != n.left)
				leftMaxPred.right = leftMax.left;
			else
				n.left = leftMax.left;
		}
		return true;
	}
	
	
	public List<String> getSortedKeys(){
		List<String> result = new ArrayList<>();
		getSortedKeysR(root, result);
		return result;
	}
	
	public void getSortedKeysR(Node n, List<String> result){
		if(n!=null) {
			getSortedKeysR(n.left, result);
			result.add(n.key);
			getSortedKeysR(n.right, result);
		}
	}
	
	public String toString() {
		return getSortedKeys().toString();
	}
	
	public int printAllStartingWith(String prefix) {
		Node n = root;
		nhits = 0;
		int m = Integer.min(prefix.length(), n.key.length());
		if (n.key.startsWith(prefix)) {
			System.out.println(n.key);
			nhits += 1;
		}
		if (n.key.substring(0, m).equals(prefix.substring(0, m))) {
			return printAllStartingWithR(prefix, n.left) + 
					printAllStartingWithR(prefix, n.right) +
					1;			
		}
		else if (n.key.substring(0, m).compareTo(prefix.substring(0, m)) > 0) {
			return printAllStartingWithR(prefix, n.left) + 1;
		}
		else 
			return printAllStartingWithR(prefix, n.right) + 1;		
	}
	
	public int printAllStartingWithR(String prefix, Node n) {
		if (n == null)
			return 0;
		if (n.key.startsWith(prefix)) {
			System.out.println(n.key);
			nhits += 1;
		}
		int m = Integer.min(prefix.length(), n.key.length());
		if (n.key.substring(0, m).equals(prefix.substring(0, m))) {
			return 1 + printAllStartingWithR(prefix, n.left) + 
					printAllStartingWithR(prefix, n.right);			
		}
		else if (n.key.substring(0, m).compareTo(prefix.substring(0, m)) > 0) {
			return 1 + printAllStartingWithR(prefix, n.left);
		}
		else 
			return 1 + printAllStartingWithR(prefix, n.right);
	}
	
	
}