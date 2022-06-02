package dk.htx.ddu.client;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class Booth extends JPanel {
    private final int BodnNr;
    private final JTextArea t1;
    private final JTextArea t2;
    Image image;
    private int rator;


    public Booth(int BodNr, double Rating, String Navn) {
        this.BodnNr = BodNr;

        rator = (int) (Rating * 100);

        setLayout(null);
        setVisible(true);

        t1 = new JTextArea(BodNr + " - " + Navn);
        t2 = new JTextArea("   Rating: " + Rating);
        JLabel te = new JLabel("");

        t1.setEditable(false);
        t1.setBackground(new Color(0, 0, 0, 1));
        t1.setWrapStyleWord(true);
        t1.setLineWrap(true);
        t1.setBounds(5, 5, 85, 30);
        t1.setVisible(true);
        t1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        t1.setForeground(Color.white);

        add(t1);

        add(te);

        t2.setEditable(false);
        t2.setForeground(Color.white);
        t2.setBackground(new Color(0, 0, 0, 1));
        t2.setBounds(5, 100, 120, 20);
        t2.setVisible(true);
        t2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        add(t2);

        try {
            image = ImageIO.read(ClassLoader.getSystemResource("images/starboy.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setRator(int rator) {
        this.rator = rator;
    }

    public int getBodnNr() {
        return BodnNr;
    }

    public JTextArea getT1() {
        return t1;
    }

    public JTextArea getT2() {
        return t2;
    }

    @Override
    public void paintComponent(Graphics g) {
        int StarY = getHeight() * 2 / 5;
        int width, height;

        if (getHeight() / getWidth() >= 150 / 728) {
            width = getWidth() * 9 / 10;
            height = width * 150 / 728;
        } else {
            height = getHeight() / 5;
            width = height * 728 / 150;
        }

        g.setColor(new Color(197, 78, 0));
        g.drawRect(0, 0, getWidth() - 3, getHeight() - 3);

        g.setColor(new Color(79, 79, 79));
        g.fillRect(getWidth() / 20, StarY, width, height);

        g.setColor(new Color(255, 200, 0));
        g.fillRect(getWidth() / 20, StarY, width * rator / 500, height);
        g.drawImage(image.getScaledInstance(width, height, Image.SCALE_REPLICATE), getWidth() / 20, StarY, null);
    }

    public void autoScaleText(JLabel label) {
        Font labelFont = label.getFont();
        String labelText = label.getText();

        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = 80;

        double widthRatio = (double) componentWidth / (double) stringWidth;

        int newFontSize = (int) (labelFont.getSize() * widthRatio);
        int componentHeight = 50;

        int fontSizeToUse = Math.min(newFontSize, componentHeight);

        label.setFont(new Font(labelFont.getFontName(), Font.PLAIN, fontSizeToUse));
    }
}
