package cz.fav.kiv.ppa2e.assignments.asg5;

public abstract class ASortingAlgorithm implements ISortingAlgorithm{

    protected int compares = 0;

    protected boolean greaterThan(int[] data, int i, int v) {
        compares++;
        return data[i] > v;
    }

    protected boolean lessThan(int[] data, int i, int v) {
        compares++;
        return data[i] < v;
    }

    public abstract void sort(int[] data);

    public int comparesInLastSort() {
        return compares;
    }

}
