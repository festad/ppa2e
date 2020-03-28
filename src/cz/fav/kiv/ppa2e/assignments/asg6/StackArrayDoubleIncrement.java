package cz.fav.kiv.ppa2e.assignments.asg6;

public class StackArrayDoubleIncrement extends StackArray{

	
	private static final int MULTIPLICATIVE_CONSTANT = 2;

	
	@Override
	protected void expandArray() {
		String[] newData = new String[data.length * MULTIPLICATIVE_CONSTANT];
		  for (int i = 0; i < data.length; i++) 
			newData[i] = data[i];
		  data = newData;
	}

	
}
