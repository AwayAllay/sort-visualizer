
package algorithms;

import util.SortArray;

import java.util.Arrays;

public class RadixSort extends SortAlgorithm{
    @Override
    public void sort(SortArray sortArray) {

        int[] data = sortArray.getData();
        int max = getMaxValue(data);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            if (isCancelled) return;
            countingSort(sortArray, exp, data.length);
        }
    }

    private void countingSort(SortArray sortArray, int exp, int n) {
        int output[] = new int[n];
        int i;
        int count[] = new int[10];
        int[] data = sortArray.getData();
        Arrays.fill(count, 0);


        for (i = 0; i < n; i++) count[(data[i] / exp) % 10]++;

        for (i = 1; i < 10; i++) count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--) {
            output[count[(data[i] / exp) % 10] - 1] = data[i];
            count[(data[i] / exp) % 10]--;
        }

        for (i = 0; i < n; i++) {
            data[i] = output[i];
            changes++;
            sortArray.setData(data);
            sortArray.sleep(sortArray.getSpeed());
            sortArray.repaint();
        }
    }

    private int getMaxValue(int[] data){
        int max = data[0];
        for (int i = 0; i < data.length; i++) {
            if (data[i] > max) max = data[i];
        }
        return max;
    }
    @Override
    public String name() {
        return "Radixsort";
    }
}
