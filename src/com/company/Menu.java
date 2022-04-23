package com.company;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;

public class Menu extends JPanel {

    JButton booth;
    JButton overview;
    GraphicsPanel graphicsPanel;

    public Menu(Window window) {
        setPreferredSize(GraphicsPanel.screenSize);
        setLayout(null);
        setVisible(true);
        booth = new JButton("Booth");
        overview = new JButton("Overview");

        booth.addActionListener(e -> {
            graphicsPanel = new GraphicsPanel(window);
            window.getContentPane().removeAll();
            window.getContentPane().add(graphicsPanel);
            window.revalidate();
        });

        overview.addActionListener(e -> {
            Overview overview = new Overview(window);
            int Cols = (int) Math.sqrt(overview.getComponentCount() * 16.00 / 9.00);
            overview.setLayout(new GridLayout(0, Cols));
            overview.setBackground(new Color(254, 105, 0));

            overview.updateOverview();

            window.getContentPane().removeAll();
            window.getContentPane().add(overview);
            window.revalidate();
            window.repaint();
        });

        booth.setBounds(550, 300, 100, 100);
        overview.setBounds(650, 300, 100, 100);
        add(booth);
        add(overview);
    }
}
