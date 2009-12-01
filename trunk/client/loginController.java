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
import common.NetworkMessage;

public class loginController {

    
    //String address;
    int port;
    static String password = "1234";
    static String username = "namee";

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 1292;

    public static loginWindow l = new loginWindow();
    private static Socket mConnection;

    public static void main(String args[]) {
    System.out.println("SocketClient initialized");
    
     l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     l.setVisible(true);

    }
    

    public void connect() {
        try {
         //address = l.txtAddress.getText();
         //port = Integer.parseInt(l.txtPort.getText());
         //password = l.txtPassword.getText();
         //username = l.txtName.getText();

         InetAddress address = InetAddress.getByName(HOST);
         mConnection = new Socket(address, PORT);

        ServerConnection s = new ServerConnection(mConnection);
        Thread thread = new Thread((Runnable) s);
        thread.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("name:");
        //String name = br.readLine();
        System.out.print(username + "\n");
        System.out.print("password:");
        //String pass = br.readLine();
        System.out.print(password + "\n");

        NetworkMessage lmsg = new NetworkMessage(NetworkMessage.NetworkAction.LOGIN,new String[] {username, password });
        s.sendNetworkMsg(lmsg);

        while(true) {
            System.out.print("you:");
            String msg = br.readLine();

            NetworkMessage gmsg = new NetworkMessage(NetworkMessage.NetworkAction.IM,new String[] {username, msg });
            s.sendNetworkMsg(gmsg);
        }

    } catch(Exception e) {
        System.out.print("Error");
    }
    }
}




      /*public void connect() {
          
          address = l.txtAddress.getText();
          port = Integer.parseInt(l.txtPort.getText());
          password = l.txtPassword.getText();
          username = l.txtName.getText();

      try {
         Socket skt = new Socket("localhost", 1292); //this will eventually be the host name and port specified by the user in the dialog box
         BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
         //ObjectInputStream oi = new ObjectInputStream(skt.getInputStream());
         
         System.out.print("Received string: '");

         while (!in.ready()) {}
         System.out.println(in.readLine()); // Read one line and output it

         System.out.print("'\n");
         in.close();
      }

      catch(Exception e) {
         System.out.print("Whoops! It didn't work!\n");
         System.out.print(port);
      } */
