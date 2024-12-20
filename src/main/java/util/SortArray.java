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
    private int[] data;
    private int[] lastModifiedData;

    public SortArray(int dataSize) {
        super.setPreferredSize(new Dimension(Visualizer.WINDOW_WIDTH, Visualizer.WINDOW_HEIGHT));
        this.data = new int[dataSize];
        this.lastModifiedData = data.clone();
        stepHeight = (Visualizer.WINDOW_HEIGHT - 50) / dataSize;
        barWidth = Visualizer.WINDOW_WIDTH / dataSize;
        for (int i = 1; i < data.length + 1; i++) {
            data[i - 1] = i;
        }
    }

    public void randomize(int speed) {
        Random random = new Random();

        for (int i = 0; i < data.length; i++) {
            int index1 = random.nextInt(data.length);
            int index2 = random.nextInt(data.length);
            swap(index1, index2);
            sleep(speed);
            repaint();
        }

    }

    public void swap(int index1, int index2){
        int value1 = data[index1];
        int value2 = data[index2];
        data[index1] = value2;
        data[index2] = value1;
    }

    public void sleep(int timeInMillis){
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getIndex(int value){

        int toReturn = 0;

        for (int i = 0; i < data.length; i++) {

            if (data[i] == value){
                toReturn = i;
                break;
            }

        }
        return toReturn;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        super.setBackground(Color.BLACK);

        boolean hasChanged = false;
        for (int i = 0; i < data.length; i++) {

            if (data[i] != lastModifiedData[i]) {
                if (hasChanged){
                    graphics.setColor(Color.GREEN);
                }
                else {
                    graphics.setColor(Color.RED);
                    hasChanged = true;
                }
            }
            else graphics.setColor(Color.WHITE);

            graphics.fillRect(barWidth * i, getHeight() - stepHeight * data[i], barWidth, stepHeight * data[i]);
        }

        lastModifiedData = data.clone();
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }
}
