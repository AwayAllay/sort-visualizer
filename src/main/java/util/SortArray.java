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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

public class SortArray extends JPanel {

    private final int barWidth;
    private final int stepHeight;
    private int[] data;
    private int[] lastModifiedData;
    private boolean showMenu = false;
    private final Rectangle sorter;
    private final Rectangle randomize;
    private final Rectangle bar;
    private SortAlgorithm algorithm = null;
    private final Visualizer visualizer;

    public SortArray(int dataSize, Visualizer visualizer) {
        super.setPreferredSize(new Dimension(Visualizer.WINDOW_WIDTH, Visualizer.WINDOW_HEIGHT));

        this.data = new int[dataSize];
        this.lastModifiedData = data.clone();
        this.visualizer = visualizer;

        stepHeight = (Visualizer.WINDOW_HEIGHT - 50) / dataSize;
        System.out.println(Visualizer.WINDOW_HEIGHT +"-" + 50 + "/" + dataSize + "=" + stepHeight);
        barWidth = Visualizer.WINDOW_WIDTH / dataSize;
        System.out.println(Visualizer.WINDOW_WIDTH +"/" + dataSize + "=" + barWidth);

        bar = new Rectangle(0,0, Visualizer.WINDOW_WIDTH, 45);
        sorter = new Rectangle(10, bar.y + 4, bar.height - 8, bar.height - 8);
        randomize= new Rectangle(20 + sorter.width, bar.y + 4, bar.height - 8, bar.height - 8);

        for (int i = 1; i < data.length + 1; i++) {
            data[i - 1] = i;
        }

        addListener();
    }

    public void randomize(int speed) {
        Random random = new Random();

        if (algorithm != null) algorithm.cancel();
        sleep(speed);

        for (int i = 0; i < data.length * 2; i++) {
            int index1 = random.nextInt(data.length);
            int index2 = random.nextInt(data.length);
            swap(index1, index2);
            sleep(speed);
            this.repaint();
        }

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

    public int getIndex(int value) {

        int toReturn = 0;

        for (int i = 0; i < data.length; i++) {

            if (data[i] == value) {
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

        paintBar(graphics);
        paintGraph(hasChanged, graphics);

        lastModifiedData = data.clone();
    }

    private void paintGraph(boolean hasChanged, Graphics graphics) {

        for (int i = 0; i < data.length; i++) {

            if (data[i] != lastModifiedData[i]) {
                if (hasChanged) {
                    graphics.setColor(Color.GREEN);
                } else {
                    graphics.setColor(Color.RED);
                    hasChanged = true;
                }
            } else graphics.setColor(Color.WHITE);

            graphics.fillRect(barWidth * i, getHeight() - stepHeight * data[i], barWidth, stepHeight * data[i]);
        }
    }

    private void addListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point pressed = e.getPoint();
                if (randomize.contains(pressed)){
                    new Thread(() -> randomize(5)).start();
                }
            }
        });
    }

    private void paintBar(Graphics graphics) {

        Graphics2D g2d = (Graphics2D) graphics;

        g2d.setColor(Color.GRAY);
        g2d.fill(bar);

        if (getImage("sorter.png") != null) {
            g2d.drawImage(getImage("sorter.png"), sorter.x, sorter.y, sorter.width, sorter.height, null);
        }
        else {
            g2d.setColor(Color.BLUE);
            g2d.fill(sorter);
        }

        if (getImage("randomize.png") != null) {
            g2d.drawImage(getImage("randomize.png"), randomize.x, randomize.y, randomize.width, randomize.height, null);
        }
        else {
            g2d.setColor(Color.GREEN);
            g2d.fill(randomize);
        }
    }

    private BufferedImage getImage(String picture){
        BufferedImage texture = null;
        try {
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/" + picture)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texture;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public void setAlgorithm(SortAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
}
