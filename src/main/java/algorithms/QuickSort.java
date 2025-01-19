
package algorithms;

import util.SortArray;

public class QuickSort extends SortAlgorithm {
    private SortArray sortArray = null;

    @Override
    public void sort(SortArray sortArray) {
        this.sortArray = sortArray;
        quickSort(0, sortArray.getData().length - 1);
    }

    private void quickSort(int l, int r){

        int[] data = sortArray.getData();
        int i, j, x;
        i = l;
        j = r;
        x = data[(l + r) / 2];
        do {

            if (isCancelled) return;
            while (data[i] < x) {
                i++;
            }
            while (x < data[j]) {
                j--;
            }
            if (i <= j) {
                sortArray.swap(i, j);
                changes++;
                sortArray.sleep(sortArray.getSpeed());
                sortArray.repaint();
                i++;
                j--;
            }
            if (isCancelled) return;
        }
        while (i <= j);
        if (l < j) quickSort(l, j);
        if (i < r) quickSort(i, r);
    }

    @Override
    public String name() {
        return "Quicksort";
    }
}
