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
