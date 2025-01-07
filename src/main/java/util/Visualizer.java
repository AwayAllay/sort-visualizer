package util;

import javax.swing.*;
import java.awt.*;

public class Visualizer {
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = WINDOW_WIDTH * 9 / 16;
    private final SortArray sortArray;
    private final JFrame frame;

    public Visualizer(){
        sortArray = new SortArray();
        frame = new JFrame("Sorting visualizer");
        setupFrame();
        sortArray.repaint();
    }


    private void setupFrame() {
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        frame.add(sortArray);
        frame.pack();
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
