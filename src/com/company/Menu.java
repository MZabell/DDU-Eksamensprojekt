package com.company;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Menu extends JPanel {

    JButton booth;
    JButton overview;

    public Menu(Window window) {
        setPreferredSize(GraphicsPanel.screenSize);
        setLayout(null);
        setVisible(true);
        booth = new JButton("Booth");
        overview = new JButton("Overview");

        booth.addActionListener(e -> {
            window.getContentPane().removeAll();
            window.getContentPane().add(window.graphicsPanel);
            window.revalidate();
        });

        overview.addActionListener(e -> {
            Booth booth = new Booth();
            int Cols = (int)Math.sqrt(booth.getComponentCount()*16/9);
            booth.setLayout(new GridLayout(0, Cols));
            booth.setBackground(new Color(254,105,0));

            ArrayList<ArrayList> arrayList = window.requestOverviewUpdate();
            ArrayList<String> places = arrayList.get(0);
            ArrayList<Double> averageRatings = arrayList.get(1);
            int i = 0;
            for (String s : places) {
                booth.Navn[i] = s;
                booth.Rating[i] = averageRatings.get(i);
                i++;
            }
            int k = 0;
            for (Overview o : booth.getC()) {
                o.getT1().setText(o.getBodnNr() + " - " + booth.Navn[k]);
                o.getT2().setText("   Rating: " + booth.Rating[k]);
                k++;
            }
            window.getContentPane().removeAll();
            window.getContentPane().add(booth);
            window.revalidate();
            window.repaint();
        });

        booth.setBounds(550, 300, 100, 100);
        overview.setBounds(650, 300, 100, 100);
        add(booth);
        add(overview);
    }
}
