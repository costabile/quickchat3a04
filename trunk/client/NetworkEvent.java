package client;

public class NetworkEvent {
    
    public static void msgRecieved(String msg) {
        System.out.println("");
        System.out.println(msg);
    }

    public static void pmsgRecieved(String from, String msg) {
        System.out.println("");
        System.out.println(from + ":" + msg);
    }

    public static void signOn(String user) {
        System.out.println("");
        System.out.println("connected:" + user);
    }

    public static void authenticated(boolean auth) {
        System.out.println("");
        if (auth) {
        System.out.println("login success");
        } else {
        System.out.println("login fail, check password");
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
