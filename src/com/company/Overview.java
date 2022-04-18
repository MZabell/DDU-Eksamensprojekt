package com.company;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Overview extends JPanel {
    public int rator;
    Image image;

    public int getBodnNr() {
        return BodnNr;
    }

    private int BodnNr;


    public JLabel getT1() {
        return t1;
    }

    public JLabel getT2() {
        return t2;
    }

    private JLabel t1;
    private JLabel t2;

    public Overview(int BodNr, double Rating, String Navn){
        this.BodnNr = BodNr;

        rator = (int) (Rating*100);

        setLayout(null);
        setVisible(true);

        t1 = new JLabel(BodNr + " - " + Navn);
        t2 = new JLabel("   Rating: " + Rating);
        JLabel te = new JLabel("");

        t1.setForeground(Color.white);
        t2.setForeground(Color.white);

        setLayout(new GridLayout(0,1));
        add(t1);
        t1.setVisible(true);
        t1.setHorizontalAlignment(JLabel.CENTER);
        t1.setFont(new Font(Font.SANS_SERIF, Font.BOLD,15));

        add(te);

        add(t2);
        t2.setVisible(true);
        t2.setHorizontalAlignment(JLabel.LEFT);
        t2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,12));

        try {
            image = ImageIO.read(new File("lib/Images/starboy.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override public void paintComponent(Graphics g){
        int StarY = getHeight()*2/5;
        int width, height;

        if(getHeight()/getWidth()>=150/728){
            width = getWidth()*9/10;
            height = width*150/728;
        } else {
            height = getHeight()/5;
            width = height*728/150;
        }

        g.setColor(new Color(197, 78, 0));
        g.drawRect(0,0,getWidth()-3,getHeight()-3);

        g.setColor(new Color(79, 79, 79));
        g.fillRect(getWidth()/20,StarY,width,height);

        g.setColor(new Color(255,200,0));
        g.fillRect(getWidth()/20,StarY,width*rator/500,height);
        g.drawImage(image.getScaledInstance(width,height,Image.SCALE_REPLICATE),getWidth()/20,StarY,null);
    }
}
