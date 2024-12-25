package util;

import javax.swing.*;
import java.awt.*;

public class Visualizer {
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = WINDOW_WIDTH * 9 / 16;
    private final SortArray sortArray;

    public Visualizer(){
        sortArray = new SortArray();
        setupFrame();
        sortArray.repaint();
    }

    private void setupFrame() {

        JFrame frame = new JFrame("Sorting visualizer");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        frame.add(sortArray);
        frame.pack();
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
