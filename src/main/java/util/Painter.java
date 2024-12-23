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

import algorithms.BubbleSort;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Painter {
    private final SortArray sortArray;
    private final Rectangle sorter;
    private final Rectangle sort;
    private final Rectangle bubbleSort;
    private final Rectangle insertionSort;
    private final Rectangle mergeSort;
    private final Rectangle selectionSort;
    private final Rectangle randomize;
    private final Rectangle bar;
    private final int stepHeight;
    private final int barWidth;
    private boolean showMenu = false;
    private final Visualizer visualizer;

    public Painter(SortArray sortArray, Visualizer visualizer) {
        this.sortArray = sortArray;
        this.visualizer = visualizer;
        addListener();

        stepHeight = (Visualizer.WINDOW_HEIGHT - 50) / sortArray.getData().length;
        barWidth = Visualizer.WINDOW_WIDTH / sortArray.getData().length;

        bar = new Rectangle(0, 0, Visualizer.WINDOW_WIDTH, 45);
        sorter = new Rectangle(10, bar.y + 4, bar.height - 8, bar.height - 8);
        randomize = new Rectangle(20 + sorter.width, bar.y + 4, bar.height - 8, bar.height - 8);
        sort = new Rectangle(70 + randomize.width, bar.y + 4, bar.height - 8, bar.height - 8);

        bubbleSort = new Rectangle(10, sorter.y + 37, 150, 60);
        insertionSort = new Rectangle(10, bubbleSort.y - 20, 50, 20);
        mergeSort = new Rectangle(10, insertionSort.y - 20, 50, 20);
        selectionSort = new Rectangle(10, mergeSort.y - 20, 50, 20);
    }

    private void addListener() {
        sortArray.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point pressed = e.getPoint();
                if (randomize.contains(pressed)) {
                    new Thread(() -> sortArray.randomize(5)).start();
                } else if (sort.contains(pressed)) {
                    new Thread(() -> visualizer.sort(1)).start();
                } else if (bubbleSort.contains(pressed)) {
                    sortArray.setAlgorithm(new BubbleSort(sortArray, 1));
                }
                sortArray.repaint();
            }
        });
        sortArray.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point mousePos = e.getPoint();
                if (showMenu && bubbleSort.contains(mousePos)) showMenu = true;
                else showMenu = sorter.contains(mousePos);
                sortArray.repaint();
            }
        });
    }

    public void paint(Graphics graphics) {

        sortArray.setBackground(Color.BLACK);

        boolean hasChanged = false;

        paintBar(graphics);
        paintGraph(hasChanged, graphics);
        if (showMenu) paintMenu(graphics);
    }

    private void paintMenu(Graphics graphics) {

        Graphics2D g2d = (Graphics2D) graphics;

        if (getImage("bubble.png") != null) {
            g2d.drawImage(getImage("bubble.png"), bubbleSort.x, bubbleSort.y, bubbleSort.width, bubbleSort.height, null);
        } else {
            g2d.setColor(Color.BLUE);
            g2d.fill(bubbleSort);
        }


    }

    private void paintGraph(boolean hasChanged, Graphics graphics) {

        int[] data = sortArray.getData();
        int[] lastModifiedData = sortArray.getLastModifiedData();

        for (int i = 0; i < data.length; i++) {

            if (data[i] != lastModifiedData[i]) {
                if (hasChanged) {
                    graphics.setColor(Color.GREEN);
                } else {
                    graphics.setColor(Color.RED);
                    hasChanged = true;
                }
            } else graphics.setColor(Color.WHITE);

            graphics.fillRect(barWidth * i, sortArray.getHeight() - stepHeight * data[i], barWidth, stepHeight * data[i]);
        }
    }

    private void paintBar(Graphics graphics) {

        Graphics2D g2d = (Graphics2D) graphics;

        g2d.setColor(Color.GRAY);
        g2d.fill(bar);

        if (getImage("sorter.png") != null) {
            g2d.drawImage(getImage("sorter.png"), sorter.x, sorter.y, sorter.width, sorter.height, null);
        } else {
            g2d.setColor(Color.BLUE);
            g2d.fill(sorter);
        }

        if (getImage("randomize.png") != null) {
            g2d.drawImage(getImage("randomize.png"), randomize.x, randomize.y, randomize.width, randomize.height, null);
        } else {
            g2d.setColor(Color.GREEN);
            g2d.fill(randomize);
        }

        if (getImage("sort.png") != null) {
            g2d.drawImage(getImage("sort.png"), sort.x, sort.y, sort.width, sort.height, null);
        } else {
            g2d.setColor(Color.GREEN);
            g2d.fill(sort);
        }
    }

    private BufferedImage getImage(String picture) {
        BufferedImage texture = null;
        try {
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/" + picture)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texture;
    }


}
