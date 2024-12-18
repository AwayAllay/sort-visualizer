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
package util;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortArray extends JPanel {

    private final int barWidth;
    private final int stepHeight;
    private final int[] data;

    public SortArray(int dataSize) {
        this.data = new int[dataSize];
        stepHeight = (Visualizer.WINDOW_HEIGHT - 50) / dataSize;
        barWidth = Visualizer.WINDOW_WIDTH / dataSize;
        for (int i = 1; i < data.length + 1; i++) {
            data[i - 1] = i;
        }
    }

    public void randomize() {
        Random random = new Random();

        for (int i = 0; i < data.length; i++) {
            int index1 = random.nextInt(data.length);
            int index2 = random.nextInt(data.length);
            swap(index1, index2);
        }

    }

    public void swap(int index1, int index2){
        int value1 = data[index1];
        int value2 = data[index2];
        data[index1] = value2;
        data[index2] = value1;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.setColor(Color.WHITE);

        for (int i = 0; i < data.length; i++) {
            graphics.fillRect(barWidth * i, getHeight() - stepHeight * data[i], barWidth, stepHeight * data[i]);
        }
    }

    public int[] getData() {
        return data;
    }
}
