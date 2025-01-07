package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Visualizer {
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = WINDOW_WIDTH * 9 / 16;
    private final SortArray sortArray;
    private final JFrame frame;
    private int width, height;

    public Visualizer(){
        sortArray = new SortArray();
        frame = new JFrame("Sorting visualizer");
        setupFrame();
        updateWidthAndHeight();
        sortArray.repaint();
    }

    private void updateWidthAndHeight() {
        width = frame.getWidth();
        height = frame.getHeight();
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

        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
               updateWidthAndHeight();
            }
            @Override
            public void componentMoved(ComponentEvent e) {}
            @Override
            public void componentShown(ComponentEvent e) {}
            @Override
            public void componentHidden(ComponentEvent e) {}
        });
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
