package cz.fav.kiv.ppa2e.assignments.asg11;

class Link {
	int neighbour;
	Link next;
	
	public Link(int n, Link next) {
		neighbour = n;
		this.next = next;
	}
	
}