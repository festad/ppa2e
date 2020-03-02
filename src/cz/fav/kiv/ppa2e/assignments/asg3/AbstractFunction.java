package cz.fav.kiv.ppa2e.assignments.asg3;

public abstract class AbstractFunction implements IFunction {


	private double epsilon = IFunction.E;
	
	@Override
	public double differentiate(double p) {
		Differentiator d = new Differentiator();
		d.setH(IFunction.H);
		double previous_derivative = -1;
		double derivative = -1;
		double difference = -1;
		do {
//			System.out.println("h = " + d.getH());
			previous_derivative = d.differentiate(this, p);
//			System.out.println("previous derivative = " + previous_derivative);
			d.setH(d.getH()/2);
			derivative = d.differentiate(this, p);
			difference = Math.abs(derivative - previous_derivative);
//			System.out.println("difference: " + difference);
		} while (difference >= epsilon);
		return 	derivative;
	}

	public double getEpsilon() {
		return epsilon;
	}

	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}

	
}
