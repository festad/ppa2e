package cz.fav.kiv.ppa2e.asg2;

import java.util.HashSet;
import java.util.Set;

/**
 * Consultation subjects
 * @author Libor Vasa
 */
enum Subject {math, computers}

/**
 * An offer of a consultation
 * @author Libor Vasa
 */
public class PlanEvent {
	/** tutor name */
	private final String tutor;
	/** start time (10 = 10:00 etc.) */
	private final int start;
	/** end time (10 = 10:00 etc.) */
	private final int end;
	/** day of week (0 = Monday, 1 = Tuesday ets.) */
	private final int dayOfWeek; 
	/** Subject of the offered consultation */
	private final Subject subject;
	
	private boolean conflicted = false;
	/**
	 * Creates an offer of a consultation
	 */
	public PlanEvent(String tutor, int start, int end, int dayOfWeek, Subject subject) throws IllegalArgumentException {
		super();
		this.tutor = tutor;
		this.start = start;
		this.end = end;
		this.dayOfWeek = dayOfWeek;
		this.subject = subject;
		if (start >= end)
			throw new IllegalArgumentException("Must start before end");
	}
	
	
	
	public String getTutor() {
		return tutor;
	}



	public int getStart() {
		return start;
	}



	public int getEnd() {
		return end;
	}



	public int getDayOfWeek() {
		return dayOfWeek;
	}



	public Subject getSubject() {
		return subject;
	}

	

	public boolean isConflicted() {
		return conflicted;
	}



	public void setConflicted(boolean conflicted) {
		this.conflicted = conflicted;
	}



	/**
	 * Returns true when an overlap with another consultation is detected, otherwise returns false
	 * @param other event (consultation offer) which may overlap this event.
	 * @return true, if this event overlaps with the other event, otherwise returns false
	 */
	public boolean isInConflict(PlanEvent other) {
		if (dayOfWeek != other.dayOfWeek) {
			return false;
		}
//		if (end >= other.start) {
//			return false;
//		}
//		if (other.end >= this.start) {
//			return false;
//		}
//		return true;
		
		if ((start >= other.end) || 
				end <= other.start)	
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return tutor + "\n" + subject + "\n" + dayOfWeek + "\n" + start + "\n" + end + "\n" + subject;
	}



	public static void main(String[] args) {
		PlanEvent event1 = new PlanEvent("František Vonásek", 10, 13, 1, Subject.math);
		PlanEvent event2 = new PlanEvent("Čeněk Landsmann", 9, 12, 1, Subject.computers);
		PlanEvent event3 = new PlanEvent("Hubert Zámožný", 11, 14, 1, Subject.math);
		PlanEvent event4 = new PlanEvent("Dobromila Musilová-Wébrová", 9, 14, 1, Subject.computers);
		PlanEvent event5 = new PlanEvent("Sisoj Psoič Rispoloženskyj", 11, 12, 1, Subject.math);
		PlanEvent event6 = new PlanEvent("Billy Blaze", 8, 10, 1, Subject.computers);
		PlanEvent event7 = new PlanEvent("Flynn Taggart", 13, 15, 1, Subject.math);
		System.out.println(event1.isInConflict(event2));			
		System.out.println(event1.isInConflict(event3));
		System.out.println(event1.isInConflict(event4));
		System.out.println(event1.isInConflict(event5));
		System.out.println(event1.isInConflict(event6));
		System.out.println(event1.isInConflict(event7));
		System.out.println(new Plan(new HashSet<PlanEvent>(Set.of(event1, event6))).isConflict());
	}
}