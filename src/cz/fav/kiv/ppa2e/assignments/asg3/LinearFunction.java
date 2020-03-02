package cz.fav.kiv.ppa2e.assignments.asg3;

class LinearFunction extends AbstractFunction implements IFunction{
	
	private double k, q;
	
	private double getValue(double p) {
		return(k*p+q); // here there was x
	}
	
	public LinearFunction(double k, double q) {
		this.k = k;
		this.q = q;
	}

	@Override // here the absent method derived from interface, to be implemented
	public double valueAt(double p) {
		return getValue(p);
	}
	
	@Override
	public double differentiate(double p) {
		return k;
	}
}
