package cz.fav.kiv.ppa2e.assignments.asg11;

import java.util.ArrayList;

class Graph {
	
	Link[] edges;
	
	public void initialize(int vertexCount) {
		edges = new Link[vertexCount];
	}
	
	public void addEdge(int start, int end) {
		Link n = new Link(end, edges[start]);		
		edges[start] = n;
		n = new Link(start, edges[end]);
		edges[end] = n;
	}
	
	public ArrayList<Integer> neighbours(int v) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Link n = edges[v];
		while (n != null) {
			result.add(n.neighbour);
			n = n.next;
		}
		return result;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < edges.length; i++) {
			sb.append(i + " -> "+ neighbours(i).toString() + "\n");
		}
		return sb.toString();
	}
}