package dk.htx.ddu.client;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;

public class Overview extends JPanel {

    private final Booth[] c;
    public double[] Rating;
    public String[] Navn;
    Window window;

    public Overview(Window window) {
        this.window = window;

        Timer timer = new Timer(1000 * 10, e -> {
            updateOverview();
            repaint();
        });
        timer.start();

        int BodAntal = 104;
        setVisible(true);
        setPreferredSize(GraphicsPanel.screenSize);

        c = new Booth[BodAntal];
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

        for (int i = 0; i < BodAntal; i++) {
            c[i] = new Booth(i + 1, Rating[i], Navn[i]);
            add(c[i]);
        }
    }

    public void updateOverview() {
        ArrayList<ArrayList> arrayList = window.requestOverviewUpdate();
        ArrayList<String> places = arrayList.get(0);
        ArrayList<Double> averageRatings = arrayList.get(1);
        int i = 0;
        for (String s : places) {
            Navn[i] = s;
            Rating[i] = (double) Math.round(averageRatings.get(i) * 10) / 10;
            i++;
        }
        int k = 0;
        for (Booth o : c) {
            if (Navn[k] == null) {
                Navn[k] = "Eksempel";
            }
            o.getT1().setText(o.getBodnNr() + " - " + Navn[k]);
            o.getT2().setText("   Rating: " + Rating[k]);
            o.setRator((int) (Rating[k] * 100));
            o.repaint();
            k++;
        }
    }
}
