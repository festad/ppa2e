package cz.fav.kiv.ppa2e.assignments.asg3;

public class Exercise03 {
	
	private static final double DELTA = 0.01;
	
	public static void main(String[] args) {
		
		LinearFunction lf = new LinearFunction(0, 1);
		Integrator integrator = new Integrator();
		integrator.setDelta(DELTA);
		double integral = integrator.integrate(lf, 0, 10);
		System.out.println(integral);
		System.out.println("----------------------");
		QuadraticPolynomial qp = new QuadraticPolynomial(1, 0, 0);
		double quadratic_integral = integrator.integrate(qp, 0, 5);
		System.out.println(quadratic_integral);
		System.out.println("----------------------");
		System.out.println(qp.differentiate(0.5));
		System.out.println("----------------------");
		System.out.println(lf.differentiate(0.5));
		System.out.println("----------------------");
		GeneralPolynomial p = new GeneralPolynomial(new double[] {0,0,1});
		System.out.println(p.differentiate(0.5));
	}
}