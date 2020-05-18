package cz.fav.kiv.ppa2e.assignments.asg11;

public class ShortestPathSearch {

	public static void main(String[] args) {
		Graph g = new Graph();
		g.initialize(20);
		g.addEdge(0, 1);
		g.addEdge(0, 5);
		g.addEdge(1, 7);
		g.addEdge(1, 2);
		g.addEdge(2, 8);
		g.addEdge(4, 9);
		System.out.println(g);
		//System.out.println(g.shortestPath(15,19));
	}
}
