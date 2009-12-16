package client;

import java.net.*; // contains the basics for netowrk operations
import java.io.*; // contains the basics for io

import common.NetworkMessage;

public class ServerConnection implements Runnable {
    private Socket mConnection;
     
    public boolean CON_ALIVE;
    private boolean SENDING;
    public boolean AUTHENTICATED;

   
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServerConnection (Socket con) {
        mConnection = con;
        CON_ALIVE = mConnection.isConnected();
    
    try {
      // Note the ordering
      out = new ObjectOutputStream(mConnection.getOutputStream());
      in = new ObjectInputStream(mConnection.getInputStream());
   
    } catch (Exception g) {
      System.out.println("ERROR: " + g);
   
    }
    }

    public void run() {
    // Runs until the connection is lost
    while (CON_ALIVE) {
        NetworkMessage nw = recieveNetworkMsg();
        if (nw != null) processMsg(nw);
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
            return (NetworkMessage)in.readObject();
        } catch (Exception e) {
            killConnection();
            return null;
        }
    }

    private void processMsg(NetworkMessage nw) {
        switch (nw.getAction()) {
            case IM:
                try {
                    java.awt.Color c = java.awt.Color.decode(nw.getData()[2]);
                    NetworkEvent.msgRecieved(c, nw.getData()[0],nw.getData()[1]);
                } catch (NumberFormatException e) {
                    NetworkEvent.msgRecieved(java.awt.Color.black, nw.getData()[0],nw.getData()[1]);
                } break;
            case PIM:
                NetworkEvent.pmsgRecieved(nw.getData()[0], nw.getData()[1]);
                break;
            case AUTHENTICATED:
                if (nw.getData()[0].equals("true")) {
                    AUTHENTICATED = true;
                } else {
                    AUTHENTICATED = false;
                }
                NetworkEvent.authenticated(AUTHENTICATED);
                break;
            case USER_SIGN_ON:
                NetworkEvent.signOn(nw.getData()[0]);
                break;
            case USER_LIST:
                NetworkEvent.userlist(nw.getData());
                break;
            case FILEREQUEST:
                NetworkEvent.fmsgRecieved(nw.getData()[0], nw.getData()[1]);
                System.out.println("File Transfer Request");
                break;
            default: break;
        }
    }
    
    private void killConnection() {
        CON_ALIVE = false;
        NetworkEvent.connectionlost();
        try { mConnection.close(); } catch (Exception e) {
        }
    }

}



