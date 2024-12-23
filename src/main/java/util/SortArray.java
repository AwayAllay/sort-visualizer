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

import algorithms.SortAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortArray extends JPanel {
    private int[] data;
    private int[] lastModifiedData;
    private SortAlgorithm algorithm = null;
    private final Painter painter;
    private boolean isRandomizing = false;
    private int speed = 1;

    public SortArray(int dataSize, Visualizer visualizer) {
        super.setPreferredSize(new Dimension(Visualizer.WINDOW_WIDTH, Visualizer.WINDOW_HEIGHT));

        this.data = new int[dataSize];
        this.lastModifiedData = data.clone();
        painter = new Painter(this, visualizer);

        for (int i = 1; i < data.length + 1; i++) {
            data[i - 1] = i;
        }
    }

    public void randomize() {
        isRandomizing = true;
        Random random = new Random();

        if (algorithm != null) {
            algorithm.cancel();
            sleep(100);
            algorithm.reset();
        }
        sleep(speed);

        for (int i = 0; i < data.length * 2; i++) {
            int index1 = random.nextInt(data.length);
            int index2 = random.nextInt(data.length);
            swap(index1, index2);
            sleep(speed);
            this.repaint();
        }

        isRandomizing = false;
    }

    public void swap(int index1, int index2) {
        int value1 = data[index1];
        int value2 = data[index2];
        data[index1] = value2;
        data[index2] = value1;
    }

    public void sleep(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sort(){
        if (!isRandomizing && algorithm != null) algorithm.sort(this, speed);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        painter.paint(graphics);
        lastModifiedData = data.clone();
    }

    public int[] getData() {
        return data;
    }

    public int[] getLastModifiedData() {
        return lastModifiedData;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public void setAlgorithm(SortAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public SortAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
