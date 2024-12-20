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

/**Selection sort is a sort algorithm that finds the min value in an array and swaps it to its correct place.
 * o(n²) in worst case, o(n) in best case. But because it goes through the array several times, this algorithm is pretty inefficient. */
public class SelectionSort implements SortAlgorithm{

    private final SortArray sortArray;
    private final int speed;

    public SelectionSort(SortArray sortArray, int speed) {
        this.sortArray = sortArray;
        this.speed = speed;
    }

    @Override
    public void sort() {

        int[] data = sortArray.getData();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {

                if (data[i] < data[j]){
                    sortArray.swap(i, j);
                    sortArray.repaint();
                    sortArray.sleep(speed);
                }
            }
        }
    }
}
