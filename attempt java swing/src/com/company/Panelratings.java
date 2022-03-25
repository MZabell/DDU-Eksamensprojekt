package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Panelratings extends JPanel {

    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    int rating;
    Buttonratings[] btn = new Buttonratings[5];
    final private Image backgroundImage;

    public Panelratings() {
        setPreferredSize(new Dimension((int) screensize.getWidth(), (int) screensize.getHeight()));
        setVisible(true);
        setLayout(new GridBagLayout());
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\Bruger\\IdeaProjects\\attempt java swing\\billeder\\placeholder-card.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        makebuttons();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImage.getScaledInstance((int) screensize.getWidth(), (int) screensize.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);

    }

    public void makebuttons() {
        for (int i = 0; i <= 4; i++) {
            btn[i] = new Buttonratings();

            add(btn[i]);
            buttonfunction(i);

            }

        }


    public void buttonfunction(int i){
        btn[i].addActionListener(e -> {
            rating = i + 1;
            System.out.println(rating);
            for (int j = 0; j <= i; j++) {
                btn[j].color = new Color(253,208,23);
            }
            for (int k = btn.length - 1; k >= rating; k--) {
                btn[k].color = Color.gray;
            }
            repaint();

        });



    }
}