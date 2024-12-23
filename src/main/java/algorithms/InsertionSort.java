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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**Insertionsort is really similar to Selectionsort with the difference that Insertionsort starts left and looks for
 * the next right value and places it in the correct order in the left side. THis makes it, again, good for smaller arrays,
 * but it gets very inefficient for larger ones (o(n²) worst-/ o(n) best- case).*/
public class InsertionSort implements SortAlgorithm{
    private boolean isCancelled = false;

    @Override
    public void sort(SortArray sortArray, int speed) {

        int[] data = sortArray.getData();
        int temp;

        for (int i = 1; i < data.length; i++) {

            if (isCancelled) return;

            temp = data[i];
            int j = i;

            while (j > 0 && data[j - 1] > temp) {
                if (isCancelled) return;
                data[j] = data[j - 1];
                sortArray.swap(j, j - 1);
                sortArray.sleep(speed);
                sortArray.repaint();
                j--;
            }

            data[j] = temp;
        }
    }

    @Override
    public void cancel() {
        isCancelled = true;
    }
    @Override
    public void reset() {
        isCancelled = false;
    }
    @Override
    public String name() {
        return "Insertionsort";
    }


}
