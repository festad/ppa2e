package cz.fav.kiv.ppa2e.assignments.asg7;

import java.util.StringTokenizer;

class Dispatcher {
	
	private static final String path = "res";
	private static final String w_filename = "dispatching.txt";
	private static final String r_filename = "callCentrum.txt";
	
	private ObjectQueue callerQueue;
	private ObjectQueue operatorQueue;
	
	private int unfortunate_number_wait = 0;
	private String unfortunate_number = null;
	private String operator_answering_unfortunate_number = null;
	
	public Dispatcher() {
		this.callerQueue = new ObjectQueue();
		this.operatorQueue = new ObjectQueue();
	}
	
	public void call(int number, int time) {
		callerQueue.add(new IncomingCall(number, time));
	}
	
	public void freeOperator(String name, int time) {
		operatorQueue.add(new FreeOperator(name, time)); // operator name se time sekund od zacatku smeny prihlasil jako dostupny
	}
	
	public void dispatchCall() throws Exception {
		boolean procede = true;
		if (callerQueue.isEmpty()) {
			System.out.println("Queue of callers is empty");
			Utility.writeContent(path, w_filename, "Queue of callers is empty", "append");
			procede = false;
		}
		if (operatorQueue.isEmpty()) {
			System.out.println("Queue of operators is empty");
			Utility.writeContent(path, w_filename, "Queue of opeartors is empty", "append");
			procede = false;
		}
		if (procede) {
			assignCall(
					(IncomingCall)callerQueue.get(), 
					(FreeOperator)operatorQueue.get());
			callerQueue.removeFirst();
			operatorQueue.removeFirst();
		}
	}
	
	private void assignCall(IncomingCall call, FreeOperator operator) {
		System.out.println(operator.name + " is answering to the call from +420 " + call.callingNumber);
		Utility.writeContent(path, w_filename, operator.name + " is answering to the call from +420 " + call.callingNumber, "append");
		int wait = operator.time - call.time;
		System.out.println("The caller has waited for " + Math.max(0, wait) + " seconds.");
		Utility.writeContent(path, w_filename, "The caller has waited for " + Math.max(0, wait) + " seconds.", "append");
		if (wait >= unfortunate_number_wait) {
			unfortunate_number_wait = wait;
			unfortunate_number = String.valueOf(call.callingNumber);
			operator_answering_unfortunate_number = operator.name;
		}
	}
	
	private void emptyAll() throws Exception {
		while (!callerQueue.isEmpty()) {
			callerQueue.removeFirst();
		}
		while (!operatorQueue.isEmpty()) {
			operatorQueue.removeFirst();
		}
	}
	
	public void uploadQueuesFromFile() throws Exception {
		emptyAll();
		String content = Utility.readContent("res", r_filename);
		StringTokenizer tk = new StringTokenizer(content);
		while (tk.hasMoreTokens()) {
			switch (tk.nextToken()) {
			case "O":
				int otime = Integer.parseInt(tk.nextToken());
				String name = tk.nextToken();
				freeOperator(name, otime);
				break;
			case "C":
				int ctime = Integer.parseInt(tk.nextToken());
				int number = Integer.parseInt(tk.nextToken());
				call(number, ctime);
				break;
			default:
				System.out.println("Error during reading");
				break;
			}
			dispatchCall();
		}
		System.out.println("The unfortunate number of the day is +420 " 
		+ unfortunate_number + " who was answered by " 
				+ operator_answering_unfortunate_number + " after " 
		+ unfortunate_number_wait + " seconds!" );
	}
	
}