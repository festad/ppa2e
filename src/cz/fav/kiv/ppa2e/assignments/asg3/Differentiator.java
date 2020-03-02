package cz.fav.kiv.ppa2e.assignments.asg3;

public class Differentiator {
	
	private double h = IFunction.H;

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}
	
	public double differentiate(IFunction f, double p) {
		return (f.valueAt(p + h) - f.valueAt(p)) / h;
	}
	
}
