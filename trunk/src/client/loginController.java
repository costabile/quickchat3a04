/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew
 */
package client;

//import java.lang.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import common.*;

public class loginController {

    public boolean conStatus = false;

    public static Socket mConnection = null;
    public static ServerConnection mainConnect = null;

    private static Thread main;


    public void connect(String target, int port, String username, String password) {

        try {

        InetAddress address = InetAddress.getByName(target);
        mConnection = new Socket(target, port);

        mainConnect = new ServerConnection(mConnection);
        main = new Thread((Runnable) mainConnect);
        main.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        NetworkMessage lmsg = new NetworkMessage(NetworkMessage.NetworkAction.LOGIN,new String[] {username, password });
        mainConnect.sendNetworkMsg(lmsg);
        conStatus = true;
        }

        catch(Exception e) {
        System.out.print("Error \n");
        JFrame frame = new JFrame("DialogDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,
        "The server did not respond. Please check your settings.",
        "Login Status",
        JOptionPane.INFORMATION_MESSAGE);
        conStatus = false;
    }
    }
}
