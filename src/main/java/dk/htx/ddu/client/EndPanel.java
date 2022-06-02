package dk.htx.ddu.client;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class EndPanel extends JPanel {

    private Image backgroundImage;

    public EndPanel() {
        setPreferredSize(GraphicsPanel.screenSize);
        setLayout(null);
        JLabel label = new JLabel("Thank you for rating!");
        label.setBounds(480, 70, 1000, 1000);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 80));
        add(label);
        try {
            backgroundImage = ImageIO.read(ClassLoader.getSystemResource("images/placeholder-card.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(backgroundImage.getScaledInstance((int) GraphicsPanel.screenSize.getWidth(), (int) GraphicsPanel.screenSize.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
    }
}
