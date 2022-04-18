package com.company;

import javax.swing.JPanel;
import java.awt.Dimension;

public class Booth extends JPanel {

    public double[] Rating;
    public String[] Navn;

    public Overview[] getC() {
        return c;
    }

    private Overview[] c;

    public Booth(){
        int BodAntal = 104;
        setVisible(true);
        setPreferredSize(GraphicsPanel.screenSize);

        c = new Overview[BodAntal];
        Rating = new double[BodAntal];
        Navn = new String[BodAntal];

        Rating[0] = 1.2; //eksempelratings
        Rating[1] = 0.5;
        Rating[2] = 3;
        Rating[3] = 4.4;
        Rating[4] = 1.3;

        Navn[0] = "Navneks1";
        Navn[1] = "Navneks2";
        Navn[2] = "Navneks3";

        for (int i=0; i<BodAntal;i++){
            c[i] = new Overview(i+1,Rating[i],Navn[i]);
            add(c[i]);
        }
    }
}
