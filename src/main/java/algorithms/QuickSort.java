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
