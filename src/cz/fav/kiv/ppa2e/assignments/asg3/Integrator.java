package cz.fav.kiv.ppa2e.assignments.asg3;

class Integrator {
	double delta;
	
	double integrate(IFunction f, double a, double b) {
		double result = 0;		
		double p = a;
		double v = f.valueAt(p);
		while(p + delta < b) {
			// rectangles of width delta
			result += delta * v;
			p += delta;
			v = f.valueAt(p);
		}
		// now for the last rectangle, which will be narrower than the others
		result += (p - b) * f.valueAt(p - b); // better use value at p-b, not p
		return result;
	}
	
	void setDelta(double d)
	{
		delta = d; //this was a bad setter with tautology
	}
	
}
