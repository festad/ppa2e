package cz.fav.kiv.ppa2e.assignments.asg7;

public class CallDispatching {
	public static void main(String[] args) {
		Dispatcher d = new Dispatcher();
		try {
//			d.freeOperator("Tonda", 0);
//			d.dispatchCall();
//			d.freeOperator("Jarmila", 10);
//			d.dispatchCall();
//			d.call(608123456, 15);
//			d.dispatchCall();
//			d.call(723987654, 35);
//			d.dispatchCall();
//			d.call(602112233, 45);
//			d.dispatchCall();
//			d.freeOperator("Pepa", 62);
//			d.dispatchCall();
//			d.call(608987654, 124);
//			d.dispatchCall();
//			d.freeOperator("Tonda", 240);
//			d.dispatchCall();
			
			d.uploadQueuesFromFile();
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
