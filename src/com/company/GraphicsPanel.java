package com.company;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;

public class GraphicsPanel extends JPanel implements SerialConnectionHandler.SerialListener {

    SerialConnectionHandler serialHandler;
    JButton button;
    Window window;

    public GraphicsPanel(Window window) {
        setPreferredSize(new Dimension(1920, 1080));

        this.window = window;

        serialHandler = new SerialConnectionHandler();

        button = new JButton("Click for serial");
        button.addActionListener(e -> {
            if (serialHandler.isConnected()) {
                System.out.println("Serial is connected");
                serialHandler.startSerial(this);
            }
        });
        add(button);
    }

    @Override
    public void onValueRead(String uid) {
        window.startClientConn(uid);
        button.setText(uid);
    }
}
