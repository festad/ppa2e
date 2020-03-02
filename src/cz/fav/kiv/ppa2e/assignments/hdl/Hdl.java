package cz.fav.kiv.ppa2e.assignments.hdl;

public class Hdl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int array[] = new int[] {1,2,3};
		mutate_array(array);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	public static void mutate_array(int array[]) {
		array[1] = -1;
	}
	
}
