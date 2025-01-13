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

import algorithms.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Painter {
    private final SortArray sortArray;
    private final Rectangle sorter;
    private final Rectangle sort;
    private final Rectangle randomize;
    private final Rectangle bar;
    private int stepHeight;
    private int barWidth;
    private final JSlider speedSlider;
    private final JSlider dataSlider;
    private boolean showMenu = false;
    private final List<SortAlgorithm> algorithms = new ArrayList<>();
    private final Map<SortAlgorithm, Rectangle> fields = new HashMap<>();

    public Painter(SortArray sortArray) {
        this.sortArray = sortArray;
        addListener();
        addAlgorithms();

        bar = new Rectangle(0, 0, Visualizer.WINDOW_WIDTH, 45);
        sorter = new Rectangle(10, bar.y + 4, bar.height - 8, bar.height - 8);
        randomize = new Rectangle(20 + sorter.width, bar.y + 4, bar.height - 8, bar.height - 8);
        sort = new Rectangle(70 + randomize.width, bar.y + 4, bar.height - 8, bar.height - 8);

        speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        dataSlider = new JSlider(JSlider.HORIZONTAL, 2, 500, 100);
        setUpSliders();

        setFields();
        new Thread(sortArray::repaint).start();

    }

    private void setFields() {

        int width = 160;
        int height = 60;

        for (int i = 0; i < algorithms.size(); i++) {

            int column = i / 4;
            int row = i % 4;

            int x = 10 + width * column;
            int y = sorter.y + 37 + height * row;

            fields.put(algorithms.get(i), new Rectangle(x, y, width, height));
        }
    }

    private void addAlgorithms() {
        //TODO ADD new algorithms here
        algorithms.add(new BubbleSort());
        algorithms.add(new InsertionSort());
        algorithms.add(new MergeSort());
        algorithms.add(new SelectionSort());
        algorithms.add(new QuickSort());
        algorithms.add(new HeapSort());
        algorithms.add(new CountingSort());
    }

    private void setUpSliders() {
        speedSlider.setBounds(new Rectangle(700, bar.y + 4, 150, 20));
        speedSlider.addChangeListener(e -> {
            int speed = 100 - speedSlider.getValue();
            sortArray.setSpeed(speed);
            sortArray.repaint();
        });
        sortArray.add(speedSlider);

        dataSlider.setBounds(new Rectangle(700, bar.y, 150, 20));
        dataSlider.addChangeListener(e -> {

            int size = dataSlider.getValue();
            boolean b = false;
            while ((Visualizer.WINDOW_WIDTH % size) != 0) {
                if (size >= 3 && !b) {
                    size--;
                } else b = true;
                if (b && size < 500) {
                    size++;
                }
            }
            if (sortArray.getAlgorithm() != null) sortArray.getAlgorithm().cancel();
            sortArray.setRandomizeIsCancelled(true);
            sortArray.setDataSize(size);
            sortArray.repaint();
            sortArray.sleep(100);
            sortArray.setRandomizeIsCancelled(false);
            if (sortArray.getAlgorithm() != null) sortArray.getAlgorithm().reset();
        });
        sortArray.add(dataSlider);
    }

    private void addListener() {
        sortArray.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point pressed = e.getPoint();
                if (randomize.contains(pressed)) {
                    new Thread(sortArray::randomize).start();
                }
                else if (sort.contains(pressed)) {
                    new Thread(sortArray::sort).start();
                }
                else if (showMenu) {

                    for (SortAlgorithm value : fields.keySet()) {

                        if (fields.get(value).contains(pressed)) {
                            sortArray.setAlgorithm(value);
                            showMenu = false;
                        }
                    }

                }
                sortArray.repaint();
            }
        });
        sortArray.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point mousePos = e.getPoint();

                if (showMenu && containsHoverPoint(mousePos)) {

                    for (SortAlgorithm value : fields.keySet()) {

                        if (fields.get(value).contains(mousePos)) {
                            sortArray.setAlgorithm(value);
                            showMenu = true;
                        }
                    }
                }
                else showMenu = sorter.contains(mousePos);
                sortArray.repaint();
            }
        });
    }

    private boolean containsHoverPoint(Point p){
        for (SortAlgorithm value : fields.keySet()) {

            if (fields.get(value).contains(p)) {
               return true;
            }
        }
        return false;
    }

    public void paint(Graphics graphics) {

        sortArray.setBackground(Color.BLACK);

        boolean hasChanged = false;
        stepHeight = (Visualizer.WINDOW_HEIGHT - 50) / sortArray.getData().length;
        barWidth = Visualizer.WINDOW_WIDTH / sortArray.getData().length;

        paintBar(graphics);
        paintGraph(hasChanged, graphics);
        if (showMenu) paintMenu(graphics);
    }

    private void paintMenu(Graphics graphics) {

        Graphics2D g2d = (Graphics2D) graphics;

        algorithms.forEach(algorithm -> {

            Rectangle rectangle = fields.get(algorithm);

            if (getImage("background.png") != null) {
                g2d.drawImage(getImage("background.png"), rectangle.x, rectangle.y, rectangle.width, rectangle.height, null);
            }
            else {
                g2d.setColor(Color.ORANGE);
                g2d.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        });

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));

        algorithms.forEach(algorithm -> {

            Rectangle rectangle = fields.get(algorithm);
            String name = algorithm.name();
            FontMetrics fontMetrics = g2d.getFontMetrics();
            int width = fontMetrics.stringWidth(name);
            int height = fontMetrics.getHeight();

            int x = rectangle.x + (rectangle.width - width) / 2;
            int y = rectangle.y + (rectangle.height - height);
            g2d.drawString(name, x, y);

        });
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
        //Draw images
        if (getImage("sorter.png") != null && getImage("randomize.png") != null && getImage("sort.png") != null) {
            g2d.drawImage(getImage("sorter.png"), sorter.x, sorter.y, sorter.width, sorter.height, null);
            g2d.drawImage(getImage("randomize.png"), randomize.x, randomize.y, randomize.width, randomize.height, null);
            g2d.drawImage(getImage("sort.png"), sort.x, sort.y, sort.width, sort.height, null);
        } else {
            g2d.setColor(Color.BLUE);
            g2d.fill(sorter);
            g2d.fill(randomize);
            g2d.fill(sort);
        }

        //Draw Algorithm
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, sort.height));
        if (sortArray.getAlgorithm() == null) g2d.drawString("Algorithm: (select algorithm)", sort.x + sort.width + 10, sort.y + sort.height - 4);
        else g2d.drawString("Algorithm: " + sortArray.getAlgorithm().name(), sort.x + sort.width + 10, sort.y + sort.height - 4);

        //Draw speed
        g2d.setFont(new Font("Arial", Font.ITALIC, speedSlider.getHeight()));
        if (sortArray.getSpeed() == 0) g2d.drawString("Speed: " + sortArray.getSpeed(), speedSlider.getX() + speedSlider.getWidth() + 5, speedSlider.getY() + speedSlider.getHeight() - 2);
        else g2d.drawString("Speed: -" + sortArray.getSpeed(), speedSlider.getX() + speedSlider.getWidth() + 5, speedSlider.getY() + speedSlider.getHeight() - 2);

        //Draw size
        g2d.drawString("Size: " + sortArray.getDataSize(), dataSlider.getX() + dataSlider.getWidth() + 5, dataSlider.getY() + dataSlider.getHeight() - 2);
        speedSlider.setLocation(700, bar.y + 4);
        dataSlider.setLocation(700, bar.y + 25);

        if (sortArray.getAlgorithm() != null) {
            g2d.drawString("Swaps: " + sortArray.getAlgorithm().getSwaps(), sorter.x, sorter.y + 60);
        }
        else {
            g2d.drawString("Swaps: " + 0, sorter.x, sorter.y + 60);
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
