package common;

import java.io.*;  

public class NetworkCom {


    public static void sendNetworkMsg(ObjectOutputStream out, NetworkMessage nw) {
       try {
            out.writeObject(nw); 
            out.flush(); 
       } catch (Exception e) {
       }
    }

    public static NetworkMessage recieveNetworkMsg(ObjectInputStream in) {
        try {
            NetworkMessage nw = (NetworkMessage)in.readObject();
            return nw; 
        } catch (Exception e) {
            return null;
        }
    }

}
