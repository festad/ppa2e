package cz.fav.kiv.ppa2e.assignments.asg5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SortingTest {
    public static void main(String[] args) {
        List<ISortingAlgorithm> algorithms = new LinkedList<>();
        algorithms.add(new InsertSort());
        algorithms.add(new MergeSort());
        algorithms.add(new JavaSort());
        algorithms.add(new QuickSort());
        algorithms.forEach(a -> {
            if (testCorrectness(a))
                testCounts(a);
        });
    }

    private static void testCounts(ISortingAlgorithm algorithm) {
        int MIN_LENGTH = 100;
        int MAX_LENGTH = 10000;
        int TEST_COUNT = 100;
        for (int length = MIN_LENGTH;length<MAX_LENGTH;length*=2) {
            int[] data = null;
            int minComp = Integer.MAX_VALUE;
            int maxComp = 0;
            for (int test = 0;test<TEST_COUNT;test++) {
                data = generateData(length);
                algorithm.sort(data);
                if (algorithm.comparesInLastSort()>maxComp)
                    maxComp = algorithm.comparesInLastSort();
                if (algorithm.comparesInLastSort()<minComp)
                    minComp = algorithm.comparesInLastSort();
            }
            System.out.println("Sorting -> " + algorithm.getClass().getSimpleName().toUpperCase());
            System.out.println("Length: " + length + ", Min:" + minComp + ", Max:" + maxComp);
            algorithm.sort(data);
            System.out.println("------------------------------------------------------------\n" +
                    "And if the array is already sorted, how many comparisons?\n" +
                    "@ " + algorithm.comparesInLastSort());
            System.out.println("=============================================================");
            data = generateData(length);
            long start = System.nanoTime();
            algorithm.only_sort(data);
            long end = System.nanoTime();
            long delta = end - start;
            System.out.println("Pure sorting:                      " + delta + " nanoseconds");
            start = System.nanoTime();
            algorithm.only_sort(data);
            end = System.nanoTime();
            delta = end - start;
            System.out.println("Pure sorting already sorted array: " + delta + " nanoseconds");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }
    }

    private static boolean testCorrectness(ISortingAlgorithm algorithm) {
        for (int i = 0; i<100; i++) {
            int[] data = generateData(100);
            int[] only_sort_data = data.clone();
            int[] dataCopy = data.clone();
            for(int j = 0; j<data.length;j++) {
//                i should use j instead of i to compare values
                if (data[j] != dataCopy[j]) {
                    System.out.println("Algorithm failed before even starting, terminating.");
                    return false;
                }
            }
            algorithm.sort(data);
            algorithm.only_sort(only_sort_data);
            Arrays.sort(dataCopy);
            for (int j = 0; j < data.length; j++) {
//                i should use j instead of i to compare values
                if (data[j] != dataCopy[j] || data[j] != only_sort_data[j] || dataCopy[j] != only_sort_data[j]) {
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
        for (int i = 0; i<c; i++)
            result[i] = rnd.nextInt(c);
        return result;
    }
}
