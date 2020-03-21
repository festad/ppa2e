package cz.fav.kiv.ppa2e.assignments.asg5;

public class MergeSort extends ASortingAlgorithm implements ISortingAlgorithm {
    @Override
    public void sort(int[] data) {
        compares = 0;
        sort(data, 0, data.length - 1);
    }

    public void only_sort(int[] data) {
        only_sort(data, 0, data.length - 1);
    }

    private void only_sort_merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int [n1];
        int[] R = new int [n2];
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int [n1];
        int[] R = new int [n2];
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
//            if (L[i] <= R[j])
            int v = R[j];
            if (lessThan(L, i, v)) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private void only_sort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l+r)/2;
            sort(arr, l, m);
            sort(arr , m+1, r);
            only_sort_merge(arr, l, m, r);
        }
    }

    private void sort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l+r)/2;
            sort(arr, l, m);
            sort(arr , m+1, r);
            merge(arr, l, m, r);
        }
    }
}
