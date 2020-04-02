package cz.fav.kiv.ppa2e.assignments.asg7;

public interface IQueue {
	
	void add(Object ob);
	Object get() throws Exception;
	void removeFirst() throws Exception;
	boolean isEmpty();
}
