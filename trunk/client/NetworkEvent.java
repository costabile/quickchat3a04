package client;

import java.io.*;
import java.net.*;
import javax.swing.*;
import client.*;
import common.*;
import java.awt.*;


public class NetworkEvent {
    
    public static void msgRecieved(String usr, String msg) {
//        System.out.println("");
//        System.out.println(msg);
          mainview.lobbyWrite(usr + ": " + msg);
    }

    public static void pmsgRecieved(String usr, String msg, String tar) {
//        System.out.println("");
//        System.out.println(from + ":" + msg);
        mainview.lobbyWrite("[Private]" + usr + ": " + msg);
    }

    public static void signOn(String user) {
        System.out.println("");
        System.out.println( user + " connected, say Hello!" );
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
        System.out.println("");
        System.out.println("online:" + list[0]  + list[1]); 
        System.out.println("num online:" + Integer.toString(list.length)); 
        
    }

    public static void connectionlost() {
        System.out.println("connection lost.");
    }
}
