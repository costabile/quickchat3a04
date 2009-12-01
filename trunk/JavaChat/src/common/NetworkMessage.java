package common;

import java.io.Serializable;

public class NetworkMessage implements Serializable {
    public enum NetworkAction { LOGIN, IM, PIM, AUTHENTICATED };

    private NetworkAction action;
    private Object[] data;


    public NetworkMessage(NetworkAction a, String[] d) {
        this.action = a;
        this.data = d;
    }

    public NetworkMessage(NetworkAction a, Boolean[] d) {
        this.action = a;
        this.data = d;
    }

    public Object[] getData() {
        return this.data;
    }

    public NetworkAction getAction() {
        return this.action;
    }
}

