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
    private boolean CON_ALIVE; 

    public String user;

    // Process msg once recieved otherwise wait for msg
    private boolean busy;
    
    private ObjectInputStream in;
    private ObjectOutputStream out;


   ClientConnection(Socket con, int i) {
        this.mConnection = con;
        this.ID = i;
        
        // The user has not been authenticated
        this.valid = false;
        this.CON_ALIVE = true;
        try {
          // Note the ordering
          out = new ObjectOutputStream(mConnection.getOutputStream());
          in = new ObjectInputStream(mConnection.getInputStream());
       
        } catch (Exception g) {
          System.out.println("ERROR: " + g);
       
        }
     }


    public void run()  {
            while (CON_ALIVE) {
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
                    privateIM(nw); 
                    break;
                case USER_LIST:
                    userList(nw);
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
            killConnection();
            return null;
        }
    }

    private void authenticateClient(NetworkMessage nw) {
       if (nw.getData()[1].equals(Server.password)) {
           valid = true;
           user = nw.getData()[0];
           System.out.println(String.format("%s has been authenticated.",user));

            // Let everyone know about the new user 
            for (ClientConnection c : Server.connections) {
                if (c != this) 
                c.sendNetworkMsg(new NetworkMessage(NetworkMessage.NetworkAction.USER_SIGN_ON, new String[] {user}));
            }

       } else {
           valid = false;
           try {
               killConnection();
           } catch (Exception e) {
           }
       }
    }

    private void killConnection()  {
        CON_ALIVE = false;
        try { 
            Server.connections.remove(Server.connections.indexOf(this));
            mConnection.close(); 
            } catch (Exception e) {}
        System.out.println(user + " connection lost.");
    }

    private void globalIM(NetworkMessage nw) {
       for (ClientConnection c : Server.connections) {
           // Don't IM ourselves
           if (c != this) {
               c.sendNetworkMsg(nw);
           }
       }
    }

    private void privateIM(NetworkMessage nw) {
       for (ClientConnection c : Server.connections) {
           // Don't IM ourselves
           if (c.user.equals(nw.getData()[2])) {
               c.sendNetworkMsg(nw);
           }
       }
    }

    private void userList(NetworkMessage nw) {
        String[] list = new String[Server.connections.size()];
        int i = 0;
        for (ClientConnection c : Server.connections) {
               list[i] = c.user;
               i++;
           }
        sendNetworkMsg(new NetworkMessage(NetworkMessage.NetworkAction.USER_LIST,list)); 
    }


}
