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

public class BubbleSort {

    public void sort(SortArray sortArray, int speed){

        int[] data = sortArray.getData();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {

                if (data[j] > data[i]){
                    sortArray.swap(i, j);
                    sortArray.sleep(speed);
                    sortArray.repaint();
                }
            }
        }

    }

}
