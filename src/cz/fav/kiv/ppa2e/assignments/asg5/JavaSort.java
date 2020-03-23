package cz.fav.kiv.ppa2e.assignments.asg5;

import java.util.Arrays;

public class JavaSort extends ASortingAlgorithm implements ISortingAlgorithm {
    @Override
    public void sort(int[] data) {
        Arrays.sort(data);
    }

    @Override
    public void only_sort(int[] data) {
        Arrays.sort(data);
    }

    @Override
    public int comparesInLastSort() {
        return 0;
    }
}
