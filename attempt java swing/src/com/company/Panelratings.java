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

    Buttonratings shape= new Buttonratings(30);
    JLayeredPane layer= new JLayeredPane();
    JLabel text=new JLabel();
    JLabel title=new JLabel();

    public Panelratings(){
        setLayout(null);
        setPreferredSize(new Dimension((int) screensize.getWidth(), (int) screensize.getHeight()));
        setVisible(true);
        try {
            backgroundImage = ImageIO.read(new File("billeder/placeholder-card.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        textfunc();
        makebuttons();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImage.getScaledInstance((int) screensize.getWidth(), (int) screensize.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);


    }

    public void textfunc(){
        layer.setVisible(true);
        layer.setBounds(0,0,250,200);
        add(layer);
        shape.setBounds(0,50,250,200);
        shape.color=new Color(253,208,23);
        layer.add(shape,0);

        text.setVisible(true);
        text.setText(String.valueOf(rating));
        text.setBounds(112,23,200,200);
        text.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        layer.add(text);
        layer.setLayer(text,2);

        title.setVisible(true);
        title.setText("Hellosandwich");
        title.setBounds( screensize.width/2-260,30,700,200 );
        title.setFont(new Font("Comic Sans MS", Font.PLAIN, 80));
        add(title);
    }

    public void makebuttons() {

        for (int i = 0; i <= 4; i++) {
            btn[i] = new Buttonratings(20);
            btn[i].setBounds(i*150+255,300,200,200);
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
            text.setText(String.valueOf(rating));
            repaint();

        });



    }
}