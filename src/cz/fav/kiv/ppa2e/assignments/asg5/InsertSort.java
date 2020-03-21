package cz.fav.kiv.ppa2e.assignments.asg5;

public class InsertSort extends ASortingAlgorithm {


    public void sort(int[] data) {
        compares = 0;
        for (int i = 1; i<data.length; i++) {
//            modifications on these assignments
            int j = i;
            int v = data[j];
            while(j > 0  && (greaterThan(data, j - 1, v))) {
//                had to change i to j
//                data[j+1] = data[j];
                int tmp = data[j - 1];
                data[j - 1] = data[j];
                data[j] = tmp;
                j--;
            }
//            data[j+1] = v;         int v = data[i];
        }
    }

}
