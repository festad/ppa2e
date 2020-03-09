package cz.fav.kiv.ppa2e.assignments.asg4;

import java.util.Arrays;
import java.util.Random;

public class RemoveDuplicates {
	
	/**
	 * Removes an item at index i from an array
	 * @param input array of data
	 * @param index index if the item to be removed
	 * @return new array with one item removed
	 */
	public static int[] removeItem(int[] data, int index){
		// resulting array will be smaller by 1 element
		int[] result = new int[data.length-1];
		// we copy all elements up to index i
		for (int i = 0;i<index;i++)
			result[i] = data[i];
		// skip i-th element, copy the rest
		for (int i = index + 1;i<data.length;i++)
			result[i-1] = data[i];
		return result;
	}
	
	/**
	 * Traverses the array and removes items using the removeItem mehtod
	 * @param data input array
	 * @return data with removed duplicates
	 */
	static int[] removeDuplicates1(int[] data){
		int[] result = data;
		for (int i = 0;i<result.length;i++){
			for (int j = i+1;j<result.length;j++){
				if (result[j] == result[i])
					result = removeItem(result, j);
			}
		}
		return result;
	}
	
	/**
	 * Traverses the array and removes all duplicates of a single element at the same time
	 * @param data input array
	 * @return data with duplicates removed
	 */
	static int[] removeDuplicates2(int[] data){
		int[] result = data;
		for(int i = 0;i<result.length;i++){
			// how many duplicates of result[i] are there?
			int count = 0; // number of duplicates
			for (int j = i+1;j<result.length;j++)
				if (result[j] == result[i])
					count++;
			// if there is at least one duplicate, we remove it/them
			if (count>0){
				// the result will be by count shorter
				int[] newResult = new int[result.length-count];
				// up to element i we simply copy the elements
				for (int k = 0;k<=i;k++)
					newResult[k] = result[k];
				int index = i + 1; // index in target array
//				added 1 to i
				for (int k = i+2;k<result.length;k++){
//				added 2 instead of 1 to i
					if (result[k]!=result[i]){
						// not a duplicate
						newResult[index] = result[k];
						index++;
					}
				}
				result = newResult;
			}
		}
		return result;
	}
	
	/**
	 * Uses an array of booleans that indicate, whether a number has been visited already or not
	 * @param data input array
	 * @return data with duplicates removed
	 */
	static int[] removeDuplicates3(int[] data){
		// first, find out how many unique numbers we have
		boolean[] encountered = new boolean[1000000];
		int count = 0; // count of unique numbers
		for (int i = 0;i<data.length;i++){
			if (!encountered[data[i]]){
				// newly found number
				encountered[data[i]] = true;
				count++;
			}
		}
		// in count we have the count of unique numbers
		// we use the array encountered once more in the same way
		encountered = new boolean[1000000];
		int[] result = new int[count];
		int index = 0;
		for (int i = 0;i<data.length;i++){
			if (!encountered[data[i]]){
				result[index] = data[i];
				encountered[data[i]] = true;
				index++;
			}
		}
		return result;
	}
	
	/**
	 * generates random numbers up to 100 000,
	 * thus simulating, that roughly 90% of numbers are "inactive"
	 * @param count number of requested numbers
	 * @return array of random numbers
	 */
	static int[] generateData(int count)
	{
		int[] result = new int[count];
		Random r = new Random();
		for (int i = 0;i<result.length;i++)
			result[i] = r.nextInt(100000);
		return result;
	}
	
	public static void main(String[] args)
	{
		int[] driver = new int[] {1,2,3,3,4,5,5};
		System.out.println(Arrays.toString(removeDuplicates2(driver)));
		System.out.println(Arrays.toString(removeDuplicates1(driver)));
		System.out.println(Arrays.toString(removeDuplicates3(driver)));

		
		int count = 30000;
		int[] data = generateData(count);
		
		int[] reducedData1 = removeDuplicates1(data);
		int[] reducedData2 = removeDuplicates2(data);	
		int[] reducedData3 = removeDuplicates3(data);
		System.out.println(Arrays.equals(reducedData1, reducedData2));
		System.out.println(Arrays.equals(reducedData2, reducedData3));
		System.out.println(Arrays.equals(reducedData1, reducedData3));
		System.out.println("All done.");
	}
}