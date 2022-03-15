package com.company;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;

public class GraphicsPanel extends JPanel implements SerialConnectionHandler.SerialListener {

    SerialConnectionHandler serialHandler;
    JButton button;
    SQLConnectionHandler sqlConn;

    public GraphicsPanel() {
        setPreferredSize(new Dimension(1920, 1080));

        serialHandler = new SerialConnectionHandler();
        sqlConn = new SQLConnectionHandler();

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
        System.out.println(uid);
        button.setText(uid);
        sqlConn.startSQLConn(uid);
    }
}
