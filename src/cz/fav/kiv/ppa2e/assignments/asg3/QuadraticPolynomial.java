package cz.fav.kiv.ppa2e.assignments.asg3;

public class QuadraticPolynomial implements IFunction {

	private double a;
	private double b;
	private double c;
	
	
	
	public QuadraticPolynomial(double a, double b, double c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}



	public double getA() {
		return a;
	}



	public void setA(double a) {
		this.a = a;
	}



	public double getB() {
		return b;
	}



	public void setB(double b) {
		this.b = b;
	}



	public double getC() {
		return c;
	}



	public void setC(double c) {
		this.c = c;
	}


	

	@Override
	public double valueAt(double p) {
		return a*p*p + b*p + c;
	}
	
}