package server;

import java.net.*;
import java.io.*;
import java.util.*;  //import the goods
import javax.swing.*;
import java.awt.*;

import common.*;

public class Server {
    private static int PORT = 1295;
    private static int CHATSERV_ID = 0;
    public static String password = "ilove3A04";
    public static serverGUI sGUI = new serverGUI();
    public static InetAddress localaddr;
    public static InetAddress localaddr2;
    public static String ipAddress = null;
    
    
    public static ArrayList<ClientConnection> connections;
    private static ServerSocket s;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
        connections = new ArrayList<ClientConnection>();
        sGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sGUI.setVisible(true);
        sGUI.setResizable(false);
            }
        });

    }

    public static void getIP(){
     try {
         localaddr = InetAddress.getLocalHost();
         ipAddress = localaddr.toString();
     }
     catch (Exception e) {
            System.out.println("ERROR: " + e);
     }
    }


    public static void startServer(){
        try {
            s = new ServerSocket(PORT);
            System.out.println("Server Initialized");
            getIP();
            sGUI.lblip.setText(ipAddress);
            System.out.println(ipAddress);
            // We listen for incoming connections
            while (true) {
                Socket connection = s.accept(); // All connections are accepted
                System.out.println(String.format("Client connected AT:%s || IP:%s", new java.util.Date().toString(), connection.getInetAddress()));

                clientConnect(connection);
                
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);

        }
            
    }

    // Process the new connection
    private static void clientConnect(Socket con) {
        ClientConnection c = new ClientConnection(con, CHATSERV_ID);
        Thread thread = new Thread((Runnable) c);
        thread.start();
      
        connections.add(c);
    }
}
