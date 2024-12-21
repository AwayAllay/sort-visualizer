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
import algorithms.InsertionSort;
import algorithms.MergeSort;
import algorithms.SelectionSort;

import javax.swing.*;
import java.awt.*;

public class Visualizer {
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = WINDOW_WIDTH * 9 / 16;
    private JFrame frame;
    SortArray sortArray;

    public Visualizer(int dataSize){
        sortArray = new SortArray(dataSize);
        setupFrame();
        sortArray.repaint();
    }

    private void setupFrame() {
        frame = new JFrame("Sorting visualizer");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        frame.add(sortArray);
        frame.pack();
        frame.setVisible(true);
    }

    public void bubbleSort(int speed){
        BubbleSort bubbleSort = new BubbleSort(sortArray, speed);
        sortArray.setAlgorithm(bubbleSort);
        bubbleSort.sort();
    }

    public void mergeSort(int speed){
        MergeSort mergeSort = new MergeSort(sortArray, speed);
        sortArray.setAlgorithm(mergeSort);
        mergeSort.sort();
    }

    public void selectionSort(int speed){
        SelectionSort selectionSort = new SelectionSort(sortArray, speed);
        sortArray.setAlgorithm(selectionSort);
        selectionSort.sort();
    }

    public void insertionSort(int speed){
        InsertionSort insertionSort = new InsertionSort(sortArray, speed);
        sortArray.setAlgorithm(insertionSort);
        insertionSort.sort();
    }

    public void randomize(int speed){
        sortArray.randomize(speed);
    }
}
