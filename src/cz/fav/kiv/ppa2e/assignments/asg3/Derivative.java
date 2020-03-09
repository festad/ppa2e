package cz.fav.kiv.ppa2e.assignments.asg3;


public class Derivative extends AbstractFunction {

	
	private IFunction function;
	
	public Derivative(IFunction function) {
		this.function = function;
	}
	
	/**
	 * 
	 */
	@Override
	public double valueAt(double p) {
		return function.differentiate(p);
	}
}
