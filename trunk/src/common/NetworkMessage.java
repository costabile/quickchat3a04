package common;

import java.io.Serializable;

public class NetworkMessage implements Serializable {
    public enum NetworkAction { LOGIN, IM, PIM, AUTHENTICATED, USER_SIGN_ON, USER_LIST,FILESEND, FILEREQUEST, FILERECEIVE };

    private NetworkAction action;
    private String[] data;


    public NetworkMessage(NetworkAction a, String[] d) {
        this.action = a;
        this.data = d;
    }

    public String[] getData() {
        return this.data;
    }

    public NetworkAction getAction() {
        return this.action;
    }
}

