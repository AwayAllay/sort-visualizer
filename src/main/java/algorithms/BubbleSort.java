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

/**Bubblesort is a pretty simple algorithm to sort data. It compares one value with the next one swaps the values if the next one is larger.
 * It is a pretty slow algorithm (average; o(n²)) but is nice to look at and uses very little memory(o(1)).*/

public class BubbleSort implements SortAlgorithm{

    private final SortArray sortArray;
    private final int speed;
    private boolean isCancelled = false;

    public BubbleSort(SortArray sortArray, int speed) {
        this.sortArray = sortArray;
        this.speed = speed;
    }

    @Override
    public void sort(){

        int[] data = sortArray.getData();

        for(int i = 1; i < data.length; i++) {

            for(int j = 0; j < data.length - i; j++) {

                if (isCancelled) return;

                if (data[j] > data[j + 1]) {
                    sortArray.swap(j, j + 1);
                    sortArray.sleep(speed);
                    sortArray.repaint();
                }

            }
        }

    }

    @Override
    public void cancel() {
        isCancelled = true;
    }

    @Override
    public BufferedImage getImage() {
        BufferedImage texture = null;
        try {
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/bubble.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texture;
    }

    @Override
    public void reset() {
        isCancelled = false;
    }

}
