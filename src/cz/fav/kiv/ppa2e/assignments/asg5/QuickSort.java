package cz.fav.kiv.ppa2e.assignments.asg5;

public class QuickSort extends ASortingAlgorithm {

    @Override
    public void sort(int[] data) {
        compares = 0;
        sort(data, 0, data.length - 1);
    }

    private int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (lessThan(arr, j, pivot)) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }

    private void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }
}
