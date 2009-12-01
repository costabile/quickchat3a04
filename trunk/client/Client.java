package client;

import java.net.*; // contains the basics for netowrk operations
import java.io.*; // contains the basics for io

import common.NetworkMessage;
// SocketClient class is a simnple example of a TCP/IP Socket Client from
// http://edn.embarcadero.com/article/31995

public class Client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 1292;
        
    private static Socket mConnection;
    public static ServerConnection s;
     
    public static void main(String[] args) {
        System.out.println("SocketClient initialized");
    
    try {

        // LOGIN CODE
        InetAddress address = InetAddress.getByName(HOST);
        mConnection = new Socket(address, PORT);
      
        s = new ServerConnection(mConnection);
        Thread thread = new Thread((Runnable) s);
        thread.start();
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("name:");
        String name = br.readLine();
        System.out.print("pass:");
        String pass = br.readLine();
         
        NetworkMessage lmsg = new NetworkMessage(NetworkMessage.NetworkAction.LOGIN,new String[] {name, pass }); 
        s.sendNetworkMsg(lmsg);
        // END LOGIN

        // Main LOOP
        while(true) {

            /* SEND GLOBAL MSG 
            System.out.print("you:");
            String msg = br.readLine();

            NetworkMessage gmsg = new NetworkMessage(NetworkMessage.NetworkAction.IM,new String[] {name, msg }); 
            s.sendNetworkMsg(gmsg);
            END GLOBAL */

            // SEND PRIVATE MSG
            System.out.print("to:");
            String to = br.readLine();
            System.out.print("msg:");
            String msg = br.readLine();
 
            NetworkMessage pmsg = new NetworkMessage(NetworkMessage.NetworkAction.PIM,new String[] {name, msg, to }); 
            s.sendNetworkMsg(pmsg);
            // END PRIVATE MSG
       
            // GET USERLIST 
            NetworkMessage ulmsg = new NetworkMessage(NetworkMessage.NetworkAction.USER_LIST,new String[] {}); 
            s.sendNetworkMsg(ulmsg);

        }

    } catch(Exception e) {
    }
    }
}
            
