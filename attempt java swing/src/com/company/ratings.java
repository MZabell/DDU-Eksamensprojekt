package com.company;

import javax.swing.*;
import java.awt.*;


public class ratings extends JFrame{
    public ratings() {

        setVisible(true);
        Panelratings panel=new Panelratings();
        getContentPane().add(panel);
        pack();
        setDefaultCloseOperation(ratings.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
	new ratings();
    }
}
