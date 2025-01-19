
package algorithms;

import util.SortArray;

public class HeapSort extends SortAlgorithm {

    @Override
    public void sort(SortArray sortArray) {
        heapSort(sortArray);
    }

    private void heapSort(SortArray sortArray) {

        int length = sortArray.getData().length;

        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(sortArray, length, i);
        }

        for (int i = length - 1; i > 0; i--) {
            if (isCancelled) return;
            sortArray.swap(0, i);
            sortArray.sleep(sortArray.getSpeed());
            sortArray.repaint();
            heapify(sortArray, i, 0);
        }
    }

    private void heapify(SortArray sortArray, int length, int i) {

        int largest = i; //largest as root
        int l = 2 * i + 1; // left index
        int r = 2 * i + 2; //right index
        int[] data = sortArray.getData();

        if (l < length && data[l] > data[largest]){
            largest = l;
        }

        if (r < length && data[r] > data[largest]){
            largest = r;
        }

        if (largest != i){
            if (isCancelled) return;
            sortArray.swap(i, largest);
            changes++;
            sortArray.sleep(sortArray.getSpeed());
            sortArray.repaint();
            heapify(sortArray, length, largest);
        }
    }

    @Override
    public String name() {
        return "Heapsort";
    }
}
