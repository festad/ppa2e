package cz.fav.kiv.ppa2e.assignments.asg3;

public class Sine extends AbstractFunction {	
	
	public Sine() {
		super();
	}

	@Override
	public double valueAt(double p) {
		return Math.sin(p);
	}

}
