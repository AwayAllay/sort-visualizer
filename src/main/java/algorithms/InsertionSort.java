
package algorithms;

import util.SortArray;

/**Insertionsort is really similar to Selectionsort with the difference that Insertionsort starts left and looks for
 * the next right value and places it in the correct order in the left side. THis makes it, again, good for smaller arrays,
 * but it gets very inefficient for larger ones (o(n²) worst-/ o(n) best- case).*/
public class InsertionSort extends SortAlgorithm {

    @Override
    public void sort(SortArray sortArray) {

        int[] data = sortArray.getData();
        int temp;

        for (int i = 1; i < data.length; i++) {

            if (isCancelled) return;

            temp = data[i];
            int j = i;

            while (j > 0 && data[j - 1] > temp) {
                if (isCancelled) return;
                data[j] = data[j - 1];
                sortArray.swap(j, j - 1);
                changes++;
                sortArray.sleep(sortArray.getSpeed());
                sortArray.repaint();
                j--;
            }

            data[j] = temp;
        }
    }

    @Override
    public String name() {
        return "Insertionsort";
    }
}
