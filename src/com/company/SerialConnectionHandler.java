package com.company;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.util.Scanner;

public class SerialConnectionHandler {

    private static final String PORT_DESCRIPTION = "USB Serial Device (COM3)";
    String uid;
    Scanner in;
    private SerialPort serialPort;
    private Thread serialThread;
    private boolean isRunning = true;
    private boolean isConnected = false;

    public SerialConnectionHandler() {
        SerialPort[] ports = SerialPort.getCommPorts();

        for (SerialPort sp : ports) {
            System.out.println(sp.getPortDescription());
            Scanner pick = new Scanner(System.in);
            int choice = pick.nextInt();
            if (choice == 1)
                serialPort = sp;
        }

        if (serialPort == null) {
            System.out.println("Device not found");
        } else
            isConnected = true;
    }

    public String getUid() {
        return uid;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void stopSerial() {
        isRunning = false;
        uid = null;
        try {
            serialThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serialPort.closePort();
    }

    public void startSerial(SerialListener listener) {
        serialPort.openPort();
        try {
            serialPort.getInputStream().skip(serialPort.getInputStream().available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        isRunning = true;
        serialThread = new Thread(() -> {
            serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
            while (isRunning) {
                in = new Scanner(serialPort.getInputStream());
                if (in.hasNextLine()) {
                    uid = in.nextLine();
                    listener.onValueRead(uid);
                    stopSerial();
                }
            }
        });
        serialThread.start();
    }

    public interface SerialListener {
        void onValueRead(String uid);
    }
}

