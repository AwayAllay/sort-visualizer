
package algorithms;

import util.SortArray;

public class CountingSort extends SortAlgorithm {

    @Override
    public void sort(SortArray sortArray) {

        int[] data = sortArray.getData();
        int length = data.length;

        int max = 0;
        for (int i = 0; i < length; i++) {
            max = Math.max(max, data[i]);
        }

        int[] countArray = new int[max + 1];

        for (int i = 0; i < length; i++) {
            countArray[data[i]]++;
        }
        for (int i = 1; i <= max; i++) {
            countArray[i] += countArray[i - 1];
        }

        int[] temp = data.clone();
        for (int i = length - 1; i >= 0; i--) {
            if (isCancelled) return;

            int value = temp[i];
            int position = countArray[value] - 1;
            data[position] = value;
            countArray[value]--;
            changes++;

            sortArray.setData(data);
            sortArray.sleep(sortArray.getSpeed());
            sortArray.repaint();
        }

    }

    @Override
    public String name() {
        return "Countingsort";
    }
}
