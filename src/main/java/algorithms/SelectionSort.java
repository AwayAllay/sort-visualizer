
package algorithms;

import util.SortArray;

/**Selection sort is a sort algorithm that finds the min value in an array and swaps it to its correct place.
 * o(n²) in worst case, o(n) in best case. But because it goes through the array several times, this algorithm is pretty inefficient. */
public class SelectionSort extends SortAlgorithm {

    @Override
    public void sort(SortArray sortArray) {

        int[] data = sortArray.getData();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {

                if (isCancelled) return;

                if (data[i] < data[j]){
                    sortArray.swap(i, j);
                    changes++;
                    sortArray.repaint();
                    sortArray.sleep(sortArray.getSpeed());
                }
            }
        }
    }

    @Override
    public String name() {
        return "Selectionsort";
    }
}
