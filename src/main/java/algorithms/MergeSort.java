
package algorithms;

import util.SortArray;

/**Merge Sort:
 * Mergesort is a popular sorting algorithm ti sort data. This works by splitting the original data[] in smaller "subarrays".
 * This way the subarrays can be compared and merged in the correct order. A fast algorithm to use but (always o(nlog(n))) as its downside it
 * uses a lot of memory, creating all the "subarrays" (o(n)).*/
public class MergeSort extends SortAlgorithm {

    @Override
    public void sort(SortArray sortArray) {
        mergeSort(sortArray.getData(), 0, sortArray.getData().length - 1, sortArray);
    }

    private void mergeSort(int[] data, int start, int end, SortArray sortArray){

        if (start >= end || isCancelled) return; //Check if the array is longer than 1 element

        int mid = start + (end - start) / 2; //Gets the index of the middle of the array

        mergeSort(data, start, mid, sortArray); //call method for left half
        mergeSort(data, mid + 1, end, sortArray); //class method for right half

        merge(data, start, mid, end); //merge the two arrays
        sortArray.setData(data); //paints the changes
        changes++;
        sortArray.sleep(sortArray.getSpeed());
        sortArray.repaint();
    }

    private void merge(int[] data, int start, int mid, int end) {

        int sizeArray1 = mid - start + 1;
        int sizeArray2 = end - mid;

        int[] l = new int[sizeArray1];
        int[] r = new int[sizeArray2];

        System.arraycopy(data, start, l, 0, sizeArray1); //copy values from the data[] into l (left side)
        System.arraycopy(data, mid + 1, r, 0, sizeArray2); //copy values from the data[] int r (right side)

        int i = 0, j = 0, k = start;
        while (i < sizeArray1 && j < sizeArray2) { //fills in the data from array l and r in the right order into data[]
            if (isCancelled) return;
            if (l[i] <= r[j]) {
                data[k++] = l[i++];
            } else {
                data[k++] = r[j++];
            }
        }

        while (i < sizeArray1) {
            if (isCancelled) return;
            data[k++] = l[i++];
        }

        while (j < sizeArray2) {
            if (isCancelled) return;
            data[k++] = r[j++];
        }
    }

    @Override
    public String name() {
        return "Mergesort";
    }
}
