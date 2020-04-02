package cz.fav.kiv.ppa2e.assignments.asg7;

public class ObjectQueue implements IQueue {
	
	private ObjectLink first;
	private ObjectLink last;

	@Override
	public void add(Object ob) {
		ObjectLink nl = new ObjectLink();
		nl.data = ob;
		if (first == null) {
			first = nl;
			last = nl;
		}
		else {
			last.next = nl;
			last = nl;
		}
	}

	@Override
	public Object get() throws Exception {
		if (first != null)
			return first.data;
		else
			throw new Exception();
	}

	@Override
	public void removeFirst() throws Exception {
		if (first!=null)
			first = first.next;
		else throw new Exception("Remove call on empty queue. Probably error, continuing...");
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

}
