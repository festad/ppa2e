package cz.fav.kiv.ppa2e.assignments.asg3;

public class GeneralPolynomial extends AbstractFunction
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
		for (int i = 0; i < coefficients.length - 1; i++) 
		{
			value += Math.pow(
					p, (coefficients.length - 1 - i)
					) 
					* coefficients[i];
		}
		value += coefficients[coefficients.length - 1];
		return value;
	}
}
