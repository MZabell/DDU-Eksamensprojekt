package com.company;

import javax.swing.JFrame;

public class Window extends JFrame {

    GraphicsPanel graphicsPanel;

    public Window() {
        graphicsPanel = new GraphicsPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
        getContentPane().add(graphicsPanel);
        pack();
    }

    public static void main(String[] args) {
        new Window();
    }
}
