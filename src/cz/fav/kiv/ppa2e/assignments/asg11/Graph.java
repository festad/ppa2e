package cz.fav.kiv.ppa2e.assignments.asg11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
	
	public int shortestPath(int start, int end) {
		int[] result = new int[edges.length];
		for (int i = 0; i < edges.length; i++)
			result[i] = -1;
		result[start] = 0;
		int[] mark = new int[edges.length];
		mark[start] = 1;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		while(!q.isEmpty()) {
			int v = q.poll();
			ArrayList<Integer> nbs = neighbours(v);
			for(int i = 0; i < nbs.size(); i++) {
				int n = nbs.get(i);
				if (mark[n] == 0) {
					mark[n] = 1;
					q.add(n);
					result[n] = result[v] + 1;
					if (n == end)
						return result[end];
				}
			}
			mark[v] = 2;
		}
		return -1;
	}
	
	public int farthest(int start) {
		int[] result = new int[edges.length];
//		for (int i = 0; i < edges.length; i++)
//			result[i] = -1;
//		result[start] = 0;
		int[] mark = new int[edges.length];
		mark[start] = 1;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		while(!q.isEmpty()) {
			int v = q.poll();
			ArrayList<Integer> nbs = neighbours(v);
			for(int i = 0; i < nbs.size(); i++) {
				int n = nbs.get(i);
				if (mark[n] == 0) {
					mark[n] = 1;
					q.add(n);
//					result[n] = result[v] + 1;
				}
			}
			mark[v] = 2;
			if(q.isEmpty())
				return v;
		}
		return -1;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < edges.length; i++) {
			sb.append(i + " -> "+ neighbours(i).toString() + "\n");
		}
		return sb.toString();
	}
}