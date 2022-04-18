package com.company;

import javax.swing.JFrame;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Window extends JFrame {

    final String IP = "192.168.0.18";
    final int PORT = 3305;

    final String PLACE = "Hello Worlds Sandwiches";

    Menu menu;
    GraphicsPanel graphicsPanel;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public Window() {
        menu = new Menu(this);
        graphicsPanel = new GraphicsPanel(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
        getContentPane().add(menu);
        pack();
    }

    public void startClientConn(String uid, int rating) {
        try {
            socket = new Socket(IP, PORT);
            System.out.println("Connected");

            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dataOutputStream.writeUTF("SendData");
            dataOutputStream.writeUTF(uid);
            dataOutputStream.writeUTF(String.valueOf(rating));
            dataOutputStream.writeUTF(PLACE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double requestRatingUpdate() {
        double averageRating = 0.00;
        try {
            socket = new Socket(IP, PORT);

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dataOutputStream.writeUTF("RequestData");
            dataOutputStream.writeUTF(PLACE);
            while (true) {
                if (dataInputStream.available() > 0) {
                    averageRating = Double.parseDouble(dataInputStream.readUTF());
                    break;
                }
            }
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
        return averageRating;
    }

    public ArrayList<ArrayList> requestOverviewUpdate() {
        ArrayList<String> places = new ArrayList<>();
        ArrayList<Double> averageRatings = new ArrayList<>();
        try {
            socket = new Socket(IP, PORT);

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dataOutputStream.writeUTF("UpdateOverview");
            while (true) {
                if (dataInputStream.available() > 0) {
                    places.add(dataInputStream.readUTF());
                    averageRatings.add(Double.parseDouble(dataInputStream.readUTF()));
                    if (dataInputStream.available() == 0) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<ArrayList> arrayList = new ArrayList<>();
        arrayList.add(places);
        arrayList.add(averageRatings);
        return arrayList;
    }

    public static void main(String[] args) {
        new Window();
    }
}
