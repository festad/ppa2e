package cz.fav.kiv.ppa2e.assignments.asg5;

import java.util.Arrays;
import java.util.Random;

public class SortingTest {
    public static void main(String[] args) {
        ISortingAlgorithm algorithm = new InsertSort();

        if (testCorrectness(algorithm)) {
            testCounts(algorithm);
        }

    }

    private static void testCounts(ISortingAlgorithm algorithm) {
        int MIN_LENGTH = 100;
        int MAX_LENGTH = 100000;
        int TEST_COUNT = 100;
        for (int length = MIN_LENGTH;length<MAX_LENGTH;length*=2) {
            int minComp = Integer.MAX_VALUE;
            int maxComp = 0;
            for (int test = 0;test<TEST_COUNT;test++) {
                int[] data = generateData(length);
                algorithm.sort(data);
                if (algorithm.comparesInLastSort()>maxComp)
                    maxComp = algorithm.comparesInLastSort();
                if (algorithm.comparesInLastSort()<minComp)
                    minComp = algorithm.comparesInLastSort();
            }
            System.out.println("Length: " + length + ", Min:" + minComp + ", Max:" + maxComp);
        }
    }

    private static boolean testCorrectness(ISortingAlgorithm algorithm) {
        for (int i = 0; i<100; i++) {
            int[] data = generateData(100);
//            data = new int[] {1,4,5,3,3,2,7,6,8,8};
            int[] dataCopy = data.clone();
            for(int j = 0; j<data.length;j++) {
//                i should use j instead of i to compare values
                System.out.println(data[j] + " => " + dataCopy[j]);
                if (data[j] != dataCopy[j]) {
                    System.out.println("Algorithm failed before even starting, terminating.");
                    return false;
                }
            }
            algorithm.sort(data);
            Arrays.sort(dataCopy);
            System.out.println("------------");
            for (int j = 0; j < data.length; j++) {
                System.out.println(data[j] + " => " + dataCopy[j]);
            }
            for (int j = 0; j < data.length; j++) {
//                i should use j instead of i to compare values
                if (data[j] != dataCopy[j]) {
                    System.out.println("Algorithm failed, terminating.");
                    return false;
                }
            }
        }
        System.out.println("Algorithm passed test, continuing.");
        return true;
    }

    private static int[] generateData(int c) {
        int[] result = new int[c];
        Random rnd = new Random();
        for (int i = 0;i<c;i++)
            result[i] = rnd.nextInt(c);
        return result;
    }

}
