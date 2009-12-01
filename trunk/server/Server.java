package server;

import java.net.*;
import java.io.*;
import java.util.*;  //import the goods

import common.*;

public class Server {
    private static int PORT = 1292;
    private static int CHATSERV_ID = 0;
    public static String password = "1234";
    
    public static ArrayList<ClientConnection> connections;
    private static ServerSocket s;

    public static void main(String[] args) {
        connections = new ArrayList<ClientConnection>();

        try {
            s = new ServerSocket(PORT);
            System.out.println("Server Initialized");
           
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
