package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class Buttonratings extends JButton {

    Path2D path = new Path2D.Double();
    Color color=Color.gray;

    public Buttonratings(int SCALE) {
        double[] pointx = {0 , 2 * SCALE, 2.62 * SCALE, 3.24 * SCALE, 5.24 * SCALE, 3.62 * SCALE, 4.24 * SCALE, 2.62 * SCALE, SCALE, 1.62 * SCALE};
        double[] pointy = {0, 0, 1.91 * SCALE, 0, 0, -1.18 * SCALE, -3.08 * SCALE, -1.9 * SCALE, -3.08 * SCALE, -1.18 * SCALE};

        setPreferredSize(new Dimension(8*SCALE,6*SCALE));
        for (int i = 0; i < pointx.length; i++) {
            pointx[i] = pointx[i] + 2*SCALE;
            pointy[i] = pointy[i] + 3.2*SCALE;
        }
        setContentAreaFilled(false);
        setVisible(true);
        path.moveTo(pointx[0], pointy[0]);
        for (int i = 1; i < 10; i++) {
            path.lineTo(pointx[i], pointy[i]);
        }
        path.closePath();
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHints(rh);
        graphics2D.fill(path);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.draw(path);
        setBorderPainted(true);
        graphics2d.setColor(Color.black);
    }


}