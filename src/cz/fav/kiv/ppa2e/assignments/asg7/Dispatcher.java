package cz.fav.kiv.ppa2e.assignments.asg7;

class Dispatcher {
	private ObjectQueue callerQueue;
	private ObjectQueue operatorQueue;
	
	public Dispatcher() {
		this.callerQueue = new ObjectQueue();
		this.operatorQueue = new ObjectQueue();
	}
	
	public void call(int number, int time) {
		IncomingCall call = new IncomingCall();
		call.callingNumber = number;
		call.time = time;
		callerQueue.add(call);
	}
	
	public void freeOperator(String name, int time) {
		operatorQueue.add(new FreeOperator(name, time)); // operator name se time sekund od zacatku smeny prihlasil jako dostupny
	}
	
	public void dispatchCall() throws Exception {
		boolean procede = true;
		if (callerQueue.isEmpty()) {
			System.out.println("Queue of callers is empty");
			procede = false;
		}
		if (operatorQueue.isEmpty()) {
			System.out.println("Queue of operators is empty");
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
		System.out.println("The caller has waited for " + Math.max(0, operator.time - call.time) + " seconds.");
	}
}