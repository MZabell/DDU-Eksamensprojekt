package dk.htx.ddu.server;

import javax.swing.JFrame;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends JFrame {

    public static String IP;
    final int SQL_PORT = 3306;
    final int SERVER_PORT = 3305;
    final String USERNAME = "user1";
    final String PASSWORD = "mz160902";

    GraphicsPanel graphicsPanel;
    Thread clientThread;

    Socket socket;
    ServerSocket serverSocket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    SQLConnectionHandler sqlConnectionHandler;
    String timeMillis;

    public Server() {
        sqlConnectionHandler = new SQLConnectionHandler(IP, SQL_PORT, USERNAME, PASSWORD);
        graphicsPanel = new GraphicsPanel();

        setUndecorated(true);
        getContentPane().add(graphicsPanel);
        pack();

        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started");
            while (true) {
                System.out.println("Waiting for client ...");
                socket = serverSocket.accept();
                System.out.println("Client accepted");

                clientThread = new Thread(() -> {
                    try {
                        dataInputStream = new DataInputStream(socket.getInputStream());
                        dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        String uid;
                        String rating;
                        String place;
                        String averageRating;
                        String marker;

                        marker = dataInputStream.readUTF();

                        switch (marker) {
                            case "SendData":
                                uid = dataInputStream.readUTF();
                                rating = dataInputStream.readUTF();
                                place = dataInputStream.readUTF();
                                System.out.println(uid);
                                System.out.println(rating);

                                timeMillis = "" + System.currentTimeMillis();
                                System.out.println(timeMillis);
                                sqlConnectionHandler.startSQLConn(uid, Integer.parseInt(rating), place, timeMillis);
                                break;

                            case "RequestData":
                                place = dataInputStream.readUTF();
                                averageRating = String.valueOf(sqlConnectionHandler.startSQLConnUpdate(place));
                                dataOutputStream.writeUTF(averageRating);
                                break;

                            case "UpdateOverview":
                                ArrayList<ArrayList> arrayList = sqlConnectionHandler.startSQLConnOverview();
                                ArrayList<String> places = arrayList.get(0);
                                ArrayList<Double> averageRatings = arrayList.get(1);
                                int i = 0;
                                for (String s : places) {
                                    dataOutputStream.writeUTF(s);
                                    dataOutputStream.writeUTF("" + averageRatings.get(i));
                                    i++;
                                }
                                break;
                        }
                        System.out.println("Closing connection");
                        dataInputStream.close();
                        dataOutputStream.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IP = args[0];
        new Server();
    }
}
