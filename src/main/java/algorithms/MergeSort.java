/*
 * WarpsAndHomes - Minecraft plugin
 * Copyright (C) 2024 AwayAllay
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package algorithms;

import util.SortArray;

/**Merge Sort:
 * Mergesort is a popular sorting algorithm ti sort data. This works by splitting the original data[] in smaller "subarrays".
 * This way the subarrays can be compared and merged in the correct order. A fast algorithm to use but (always o(nlog(n))) as its downside it
 * uses a lot of memory, creating all the "subarrays" (o(n)).*/
public class MergeSort implements SortAlgorithm{

    private final SortArray sortArray;
    private final int speed;
    private boolean isCancelled = false;

    public MergeSort(SortArray sortArray, int speed) {
        this.sortArray = sortArray;
        this.speed = speed;
    }

    @Override
    public void sort() {
        mergeSort(sortArray.getData(), 0, sortArray.getData().length - 1);
    }

    @Override
    public void cancel() {
        isCancelled = true;
    }

    private void mergeSort(int[] data, int start, int end){

        if (start >= end || isCancelled) return; //Check if the array is longer than 1 element

        int mid = start + (end - start) / 2; //Gets the index of the middle of the array

        mergeSort(data, start, mid); //call method for left half
        mergeSort(data, mid + 1, end); //class method for right half

        merge(data, start, mid, end); //merge the two arrays
        sortArray.setData(data); //paints the changes
        sortArray.sleep(speed);
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
}
