package cz.fav.kiv.ppa2e.assignments.asg7;

class CallerQueue {
	private Link first, last;
	
	public void add(IncomingCall call) {
		Link nl = new Link();
		nl.data = call;
		if (first == null) {
			first = nl;
			last = nl;
		}
		else {
			last.next = nl;
			last = nl;
		}
	}
	
	public IncomingCall get() throws Exception {
		if (first != null)
			return first.data;
		else
			throw new Exception();
	}
	
	public void removeFirst() throws Exception {
		if (first!=null)
			first = first.next;
		else throw new Exception("Remove call on empty queue. Probably error, continuing...");
	}	
}