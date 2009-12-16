package client;

import java.io.*;
import java.net.*;
import javax.swing.*;
import client.*;
import common.*;
import java.awt.*;


public class NetworkEvent {
    public static String users[];
    public static boolean exists;
    private final static String newline = "\n";
    
    public static void msgRecieved(Color c, String usr, String msg) {
//        System.out.println("");
//        System.out.println(msg);
          sessionController.lobbyWrite(Color.black, usr + ": ");
          sessionController.lobbyWrite(c, msg + newline);
    }

    public static void pmsgRecieved(String usr, String msg) {
//        System.out.println("");
//        System.out.println(from + ":" + msg);
        users = sessionController.openTabs();
        int j = users.length;
        for (int i=0; i <= j-1; i++)
            if (users[i].equals(usr)){
                exists = true;
            }
            else
                exists = false;

       if (exists == false)
       {
           sessionController.makeTab(usr);
       }
       else
        sessionController.lobbyWrite(Color.GRAY, "[Private]" + usr + ": " + msg + newline);
    }

    public static void fmsgRecieved(String usr, String filename){
         sessionController.fileRequest(usr,filename);
    }

    public static void signOn(String user) {
        System.out.println("");
        sessionController.lobbyWrite(sessionController.SYSTEM_MSG_COLOR, user + " : connected" + newline);
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
        sessionController.usrlistUpdate(list);
        sessionController.users = list;
    }

    public static void connectionlost() {
        System.out.println("connection lost.");
    }
}
