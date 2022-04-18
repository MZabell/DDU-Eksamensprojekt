package com.company;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

public class EndPanel extends JPanel {

    private Image backgroundImage;
    private final JLabel label;

    public EndPanel() {
        setPreferredSize(GraphicsPanel.screenSize);
        setLayout(null);
        label = new JLabel("Thank you for rating!");
        label.setBounds(250, 70, 1000, 1000);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 80));
        add(label);
        try {
            backgroundImage = ImageIO.read(new File("lib/Images/placeholder-card.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(backgroundImage.getScaledInstance((int) GraphicsPanel.screenSize.getWidth(), (int) GraphicsPanel.screenSize.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
    }
}
