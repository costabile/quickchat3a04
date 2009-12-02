package client;

import java.io.*;
import java.net.*;
import javax.swing.*;
import client.*;
import common.*;
import java.awt.*;


public class NetworkEvent {
    
    public static void msgRecieved(String usr, String msg/*, int colour*/) {
//        System.out.println("");
//        System.out.println(msg);
          //int colour =
          String message = usr + ": " + msg;
          mainview.lobbyWrite(message);
    }

    public static void pmsgRecieved(String usr, String msg) {
//        System.out.println("");
//        System.out.println(from + ":" + msg);
        mainview.lobbyWrite("[Private]" + usr + ": " + msg);
    }

    public static void signOn(String user) {
        System.out.println("");
        mainview.lobbyWrite("-> " + user + " has connected.");
    }

    public static void authenticated(boolean auth) {
        System.out.println("");
        if (auth) {
        System.out.println("login success");
        } else {
            
        System.out.println("login fail, check password");
        JFrame frame = new JFrame("DialogDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,
        "The Login / Password combination you supplied is invalid.",
        "Login Failed",
        JOptionPane.ERROR_MESSAGE);

        }
    }

    public static void userlist(String[] list) {
        mainview.usrlistUpdate(list);
    }

    public static void connectionlost() {
        System.out.println("connection lost.");
    }
}
