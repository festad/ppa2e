package cz.fav.kiv.ppa2e.assignments.asg6;

public class StackArrayFixedIncrement extends StackArray {

	private static final int FIXED_INCREMENT = 10;
	
	@Override
	protected void expandArray() {
		String[] newData = new String[data.length + FIXED_INCREMENT];
		  for (int i = 0; i < data.length; i++) 
			newData[i] = data[i];
		  data = newData;
	}

}
