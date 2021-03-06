package cz.fav.kiv.ppa2e.assignments.asg4;

import java.util.Arrays;
import java.util.Random;

public class RemoveDuplicates {
	
	public static final int MAX_V = 100_000;
	
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
				if (result[j] == result[i]) {
					result = removeItem(result, j);
					j--;
				}
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
				for (int k = i+1;k<result.length;k++){
//				added 2 instead of 1 to i 
//				-> update: add 1 to i instead of 2
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
		boolean[] encountered = new boolean[MAX_V];
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
		encountered = new boolean[MAX_V];
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
			result[i] = r.nextInt(MAX_V);
		return result;
	}
	
	public static int rem1_more_than_s(long nano_seconds, int count) 
	{
		do 
		{
			int[] data = generateData(count);
			long start_1 = System.nanoTime();
			removeDuplicates1(data);
			long stop_1 = System.nanoTime();
			System.out.println(String.format("Remove duplicates 1 with %d -> %d", count, stop_1 - start_1));
			if (stop_1 - start_1 >= nano_seconds)
				return count;
			count += 1_000;
		}
		while (true);
	}
	
	public static int rem2_more_than_s(long l, int count) 
	{
		do 
		{
			int[] data = generateData(count);
			long start_1 = System.nanoTime();
			removeDuplicates2(data);
			long stop_1 = System.nanoTime();
			System.out.println(String.format("Remove duplicates 2 with %d -> %d", count, stop_1 - start_1));
			if (stop_1 - start_1 >= l)
				return count;
			count += 1_000;
		}
		while (true);
	}
	
	public static int rem3_more_than_s(long nano_seconds, int count) 
	{
		do 
		{
			int[] data = generateData(count);
			long start_1 = System.nanoTime();
			removeDuplicates3(data);
			long stop_1 = System.nanoTime();
			System.out.println(String.format("Remove duplicates 3 with %d -> %d", count, stop_1 - start_1));
			if (stop_1 - start_1 >= nano_seconds)
				return count;
			count += 1_000;
		}
		while (true);
	}
	
	public static void main(String[] args)
	{
//		int[] driver = new int[] {4,4,4,7,1,4,5,3,7,2,2,2};
//		System.out.println(Arrays.toString(removeDuplicates2(driver)));
//		System.out.println(Arrays.toString(removeDuplicates1(driver)));
//		System.out.println(Arrays.toString(removeDuplicates3(driver)));
////
		int c = 1;
		int count = 200_000;
		int[] data = generateData(count);
		long start_1 = System.nanoTime();
		int[] reducedData1 = removeDuplicates1(data);
		long stop_1 = System.nanoTime();
		System.out.println(String.format("Remove duplicates @%d -> %d", c, stop_1 - start_1));
		c++;
		long start_2 = System.nanoTime();
		int[] reducedData2 = removeDuplicates2(data);	
		long stop_2 = System.nanoTime();
		System.out.println(String.format("Remove duplicates @%d -> %d", c, stop_2 - start_2));
		c++;
		long start_3 = System.nanoTime();
		int[] reducedData3 = removeDuplicates3(data);
		long stop_3 = System.nanoTime();
		System.out.println(String.format("Remove duplicates @%d -> %d", c, stop_3 - start_3));
		
		System.out.println(Arrays.equals(reducedData1, reducedData2));
		System.out.println(Arrays.equals(reducedData2, reducedData3));
		System.out.println(Arrays.equals(reducedData1, reducedData3));
//		
//		System.out.println(rem1_more_than_s(1_000_000_000, 40_000));
//		System.out.println(rem2_more_than_s(1_000_000_000, 50_000)); 
//		System.out.println(rem3_more_than_s(1_000_000_000, 700_000_000)); 
//		int upperbound = rem1_more_than_s(10_000_000_000L, 145_000);
//		System.out.println(upperbound);
//		System.out.println(); // 200_000
//		int lowerbound = 1000;
//		int step = (upperbound - lowerbound) / 10;
//		int count = lowerbound;
//		System.out.println(String.format(""
//				+ "%20s | %20s | %20s | %20s | %20s | %20s | %20s", 
//				      "n", "t1", "a1", "t2", "a2", "t3", "a3"));
//		do
//		{
//			int[] data = generateData(count);
//			
//			long start_1 = System.nanoTime();
//			removeDuplicates1(data);
//			long stop_1 = System.nanoTime();
//			double delta1 = (stop_1 - start_1);
//			
//			long start_2 = System.nanoTime();
//			removeDuplicates2(data);	
//			long stop_2 = System.nanoTime();
//			double delta2 = (stop_2 - start_2);
//			
//			long start_3 = System.nanoTime();
//			removeDuplicates3(data);
//			long stop_3 = System.nanoTime();
//			double delta3 = (stop_3 - start_3);
//						
//			System.out.println(String.format(""
//					+ "%20d | %20f | %20f | %20f | %20f | %20f | %20f", 
//					count, delta1, delta1/delta1, delta2, delta1/delta2, delta3, delta1/delta3));
//			count += step;
//		} 
//		while (count <= upperbound);
		
		System.out.println("All done.");
	}
}