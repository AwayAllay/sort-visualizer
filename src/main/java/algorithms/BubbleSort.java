
package algorithms;

import util.SortArray;

/**Bubblesort is a pretty simple algorithm to sort data. It compares one value with the next one swaps the values if the next one is larger.
 * It is a pretty slow algorithm (average; o(n²)) but is nice to look at and uses very little memory(o(1)).*/

public class BubbleSort extends SortAlgorithm {

    @Override
    public void sort(SortArray sortArray){

        int[] data = sortArray.getData();

        for(int i = 1; i < data.length; i++) {

            for(int j = 0; j < data.length - i; j++) {

                if (isCancelled) return;

                if (data[j] > data[j + 1]) {
                    sortArray.swap(j, j + 1);
                    changes++;
                    sortArray.sleep(sortArray.getSpeed());
                    sortArray.repaint();
                }

            }
        }

    }
    @Override
    public String name() {
        return "Bubblesort";
    }
}
