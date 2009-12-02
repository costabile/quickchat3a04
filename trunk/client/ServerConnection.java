package client;

import java.net.*; // contains the basics for netowrk operations
import java.io.*; // contains the basics for io

import common.NetworkMessage;
// SocketClient class is a simnple example of a TCP/IP Socket Client from
// http://edn.embarcadero.com/article/31995

public class ServerConnection implements Runnable {
    private Socket mConnection;
     
    private boolean CON_ALIVE;
    private boolean SENDING;
    private boolean AUTHENTICATED;

   
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

        CON_ALIVE = mConnection.isConnected();
    }
    killConnection();
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
            return null;
        }
    }

    private void processMsg(NetworkMessage nw) {
        switch (nw.getAction()) {
            case LOGIN:
                break;
            case IM:
                System.out.println("");
                System.out.println(nw.getData()[0] + ":" + nw.getData()[1]);
                break;
            case PIM:
                System.out.println("");
                System.out.println(nw.getData()[0] + ":" + nw.getData()[1]);
                break;
            case AUTHENTICATED:
                //HACK
                //System.out.println("authenticated: " + nw.getData()[0]);
                if (nw.getData()[0].equals("true")) {
                    AUTHENTICATED = true;
                } else {
                    AUTHENTICATED = false;
                }
            default: break;
        }
    }
    
    private void killConnection() {
        try {
        mConnection.close();
        System.out.println("connection lost.");
        } catch (Exception e) {
        }
    }

}



