package com.company;

import com.mysql.cj.xdevapi.Client;

import javax.swing.JFrame;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Window extends JFrame {

    final String ip = "10.160.208.0";
    final int port = 3305;

    GraphicsPanel graphicsPanel;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public Window() {
        graphicsPanel = new GraphicsPanel(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
        getContentPane().add(graphicsPanel);
        pack();
    }

    public void startClientConn(String uid) {
        try {
            socket = new Socket(ip, port);
            System.out.println("Connected");

            dataInputStream = new DataInputStream(System.in);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dataOutputStream.writeUTF(uid);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Window();
    }
}
