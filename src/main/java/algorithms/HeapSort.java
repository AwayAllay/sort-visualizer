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

public class HeapSort implements SortAlgorithm {

    private boolean isCancelled = false;
    private int swaps = 0;

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
            swaps++;
            sortArray.sleep(sortArray.getSpeed());
            sortArray.repaint();
            heapify(sortArray, length, largest);
        }
    }


    @Override
    public void cancel() {
        isCancelled = true;
    }

    @Override
    public void reset() {
        isCancelled = false;
        swaps = 0;
    }

    @Override
    public String name() {
        return "Heapsort";
    }

    @Override
    public int getSwaps() {
        return swaps;
    }
}
