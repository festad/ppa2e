package cz.fav.kiv.ppa2e.assignments.asg3;

public class GeneralPolynomial extends AbstractFunction implements IFunction 
{
	private double coefficients[];	
	
	public GeneralPolynomial(double[] attributes) 
	{
		super();
		this.coefficients = attributes;
	}

	@Override
	public double valueAt(double p) 
	{
		double value = 0;
		for (int i = 1; i < coefficients.length; i++) 
		{
			value += Math.pow(p, i) * coefficients[i];
		}
		value += coefficients[0];
		return value;
	}
}
