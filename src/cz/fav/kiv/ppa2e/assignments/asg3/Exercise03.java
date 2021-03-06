package cz.fav.kiv.ppa2e.assignments.asg3;

public class Exercise03 {
	
	private static final double DELTA = 0.01;
	
	public static void main(String[] args) {
		
		LinearFunction lf = new LinearFunction(1, 0);
		Integrator integrator = new Integrator();
		integrator.setDelta(DELTA);
		double integral = integrator.integrate(lf, 0, 10);
		System.out.println(integral);
		System.out.println("----------------------");
		integrator.setDelta(0.1);
		integral = integrator.integrate(lf, 0, 10);
		System.out.println(integral);
		System.out.println("----------------------");
		IFunction qp = new QuadraticPolynomial(1, 0, 0);
		double quadratic_integral = integrator.integrate(qp, 0, 5);
		System.out.println(quadratic_integral);
		System.out.println("----------------------");
//		System.out.println(qp.differentiate(0.5));
		System.out.println("----------------------");
//		System.out.println(lf.differentiate(0.5));
		System.out.println("----------------------");
		IFunction p = new GeneralPolynomial(new double[] {1,0,0});
//		System.out.println(p.differentiate(0.5));
		System.out.println("======================");
		IFunction myPolynomial = new GeneralPolynomial(new double[] {7,-5,3,-15});
		IFunction firstDerivative = new Derivative(myPolynomial);
		IFunction secondDerivative = new Derivative(firstDerivative);
		IFunction thirdDerivative = new Derivative(secondDerivative);
		for (double x = 0;x<10.1;x+=0.2) {
		  System.out.println(thirdDerivative.valueAt(x));
		  System.out.println("Analytic value: 42");
		  if (x >= 1.5) {
			  System.out.println(">=");
			  System.out.println(String.format("Pol(%3.3f) = %3.3f"
			  		+ "\nPol1(%3.3f) = %3.3f"
			  		+ "\nPol2(%3.3f = %3.3f\n", x, myPolynomial.valueAt(x), 
			  		x, firstDerivative.valueAt(x),
			  		x, secondDerivative.valueAt(x)));
		  }
	
		}
		System.out.println("======================");
		IFunction sin = new Sine();
		IFunction cos = new Derivative(sin);
		System.out.println("Cosine");
		for (double x = 0;x<Math.PI*2;x+=0.3) {
		  System.out.println(cos.valueAt(x));
		}
		System.out.println("======================");
		IFunction polynom = new GeneralPolynomial(new double[] {3,0,0});
		IFunction derivative = new Derivative(polynom);
		System.out.println(derivative.valueAt(1));

	}
}