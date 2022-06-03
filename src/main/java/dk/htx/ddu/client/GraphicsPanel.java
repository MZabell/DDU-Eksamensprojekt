package dk.htx.ddu.client;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class GraphicsPanel extends JPanel implements SerialConnectionHandler.SerialListener {

    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final ImageIcon gif;
    SerialConnectionHandler serialHandler;
    Window window;
    JDialog dialog;
    Buttonratings[] btn = new Buttonratings[5];
    Buttonratings shape = new Buttonratings(30);
    JLayeredPane layer = new JLayeredPane();
    JLabel text = new JLabel();
    JLabel title = new JLabel();
    private int rating;
    private Image backgroundImage;

    public GraphicsPanel(Window window) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        gif = new ImageIcon(Objects.requireNonNull(classLoader.getResource("images/gif.gif")));
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(classLoader.getResource("images/placeholder-card.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setPreferredSize(screenSize);
        setLayout(null);
        setVisible(true);

        this.window = window;

        serialHandler = new SerialConnectionHandler();

        textFunction();
        makeButtons();
        text.setText(String.valueOf((double) Math.round(window.requestRatingUpdate() * 10) / 10));
        Timer timer = new Timer(1000 * 10, e -> {
            text.setText(String.valueOf((double) Math.round(window.requestRatingUpdate() * 10) / 10));
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(backgroundImage.getScaledInstance((int) screenSize.getWidth(), (int) screenSize.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
    }

    @Override
    public void onValueRead(String uid) {
        window.startClientConn(uid, rating);
        dialog.setVisible(false);
        window.getContentPane().removeAll();
        window.getContentPane().add(new EndPanel());
        window.revalidate();
        Timer timer = new Timer(5000, e -> {
            window.getContentPane().removeAll();
            window.getContentPane().add(window.menu.graphicsPanel);
            for (Buttonratings b : btn) {
                b.color = Color.gray;
            }
            window.revalidate();
            window.repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void makeButtons() {
        int center = (int) (5.24 * 20 * 2.5 + (2 * (175 - 5.24 * 20)));
        for (int i = 0; i <= 4; i++) {
            btn[i] = new Buttonratings(20);
            btn[i].setBounds(i * 150 + (screenSize.width / 2) - center, screenSize.height / 2, 200, 200);
            add(btn[i]);
            buttonFunction(i);

        }

    }

    public void buttonFunction(int i) {
        btn[i].addActionListener(e -> {
            if (serialHandler.isConnected()) {
                System.out.println("Serial is connected");
                serialHandler.startSerial(this);
            }
            dialog = new JDialog();
            dialog.setUndecorated(true);
            dialog.add(new JLabel(gif));
            dialog.setLocation((int) screenSize.getWidth() / 2 - gif.getIconWidth() / 2, (int) screenSize.getHeight() / 2 - gif.getIconHeight() / 2);
            dialog.setAlwaysOnTop(true);
            dialog.pack();
            dialog.setVisible(true);
            rating = i + 1;
            for (int j = 0; j <= i; j++) {
                btn[j].color = new Color(253, 208, 23);
            }
            for (int k = btn.length - 1; k >= rating; k--) {
                btn[k].color = Color.gray;
            }
            repaint();

        });
    }

    public void textFunction() {
        layer.setVisible(true);
        layer.setBounds(0, 0, 250, 200);
        add(layer);
        shape.setBounds(0, 50, 250, 200);
        shape.color = new Color(253, 208, 23);
        layer.add(shape, 0);

        text.setVisible(true);
        text.setBounds(112, 23, 200, 200);
        text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        layer.add(text);
        layer.setLayer(text, 2);

        title.setVisible(true);
        title.setText("Hellosandwich");
        title.setBounds(screenSize.width / 2 - 260, 30, 700, 200);
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 80));
        add(title);
    }
}
