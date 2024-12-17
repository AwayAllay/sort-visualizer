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

import util.Data;
import util.Graphic;

public class BubbleSort {

    public void sort(int[] dataToSort){

        for (int i = 0; i < dataToSort.length - 1; i++) {

            int first = dataToSort[i];
            int second = dataToSort[i + 1];

            if (first >= second){
                dataToSort[i + 1] = first;
                dataToSort[i] = second;
            }

        }

        if (new Data().isSorted(dataToSort)){
            new Graphic(dataToSort).draw();
        }
        else {
            sort(dataToSort);
        }

    }


}
