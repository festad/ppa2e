package cz.fav.kiv.ppa2e.assignments.asg5;

public class SortingDriver {

    public static void main(String[] args) {
        ISortingAlgorithm ia = new InsertSort();
        int[] array = new int[] {1,4,5,3,3,2,7,6,8,8};
        ia.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    
}
