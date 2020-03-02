package cz.fav.kiv.ppa2e.assignments.asg3;

interface IFunction {
	
	public static final double DELTA = 0.01;
	public static final double H = 0.01;
	public static final double E = 0.001;

	
	double valueAt(double p);
	double differentiate(double p);
}
