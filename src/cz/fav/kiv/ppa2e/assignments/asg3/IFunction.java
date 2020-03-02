package cz.fav.kiv.ppa2e.assignments.asg3;

interface IFunction {
	
	public static final double DELTA = 0.000000001;
	public static final double H     = 0.1;
	public static final double E     = 0.001;

	
	double valueAt(double p);
	double differentiate(double p);
}
