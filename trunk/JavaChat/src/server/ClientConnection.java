package server;

import java.net.*;
import java.io.*;
import java.util.*;  //import the goods

import common.NetworkMessage;

public class ClientConnection implements Runnable {
    private Socket mConnection;
    private String TimeStamp;
    private int ID;
    private boolean valid; 

    private String user;

    // Process msg once recieved otherwise wait for msg
    private boolean busy;
    
    private ObjectInputStream in;
    private ObjectOutputStream out;


   ClientConnection(Socket con, int i) {
        this.mConnection = con;
        this.ID = i;
        
        // The user has not been authenticated
        this.valid = false;
        try {
          // Note the ordering
          out = new ObjectOutputStream(mConnection.getOutputStream());
          in = new ObjectInputStream(mConnection.getInputStream());
       
        } catch (Exception g) {
          System.out.println("ERROR: " + g);
       
        }
     }


    public void run()  {
            while (true) {
                NetworkMessage nw = recieveNetworkMsg();
                if (nw != null) processMsg(nw);
            }
    }

   private void processMsg(NetworkMessage nw) {
         switch (nw.getAction()) {
                case LOGIN:
                    authenticateClient(nw);
                    sendNetworkMsg(new NetworkMessage(NetworkMessage.NetworkAction.AUTHENTICATED,new String[] { "true" })); 
                    break;
                case IM:
                    globalIM(nw); 
                    break;
                case PIM:
                    break;
                default: break;
            }
   }

   public void sendNetworkMsg(NetworkMessage nw) {
       try {
            out.writeObject(nw); 
            out.flush(); 
       } catch (Exception e) {
       }
    }

    public NetworkMessage recieveNetworkMsg() {
        try {
            NetworkMessage nw = (NetworkMessage)in.readObject();
            return nw; 
        } catch (Exception e) {
            return null;
        }
    }

    private void authenticateClient(NetworkMessage nw) {
       if (nw.getData()[1].equals(Server.password)) {
           valid = true;
           user = (String)nw.getData()[0];
           System.out.println(String.format("%s has been authenticated.",user));
       } else {
           valid = false;
           try {
               killConnection();
           } catch (Exception e) {
           }
       }
    }

    private void killConnection() throws Exception {
        mConnection.close();
        System.out.println("connection lost.");
    }

    private void globalIM(NetworkMessage nw) {
       for (ClientConnection c : Server.connections) {
           // Don't IM ourselves
           if (c != this) {
               c.sendNetworkMsg(nw);
           }
       }
    }

}
