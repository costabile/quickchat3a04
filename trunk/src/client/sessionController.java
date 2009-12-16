/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import common.*;

/**
 *
 * @author Andrew
 */
public class sessionController {

    public static loginController l = new loginController();
    public static loginWindow lGUI = new loginWindow();
    public static mainGUI mainWindow = new mainGUI();

    public static String username = "";
    public static String password = "";
    public static String target = "";
    public static int port;
    private static Socket mConnection2 = null;
    public static ServerConnection mainConnect2 = null;
    private static clientSettings settings = new clientSettings();
    //private static Thread main;
    private final static String newline = "\n";
    public static final Color SYSTEM_MSG_COLOR = Color.red;
    private static Thread main;
    public static String users[] = null;
    public static String activeTab = null;


public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("SocketClient initialized");
                lGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                lGUI.setVisible(true);
                lGUI.setResizable(false);
                //startSession();
            }
        });
}


public static void startSession() {
    
        username = lGUI.txtName.getText();
        target = lGUI.txtAddress.getText();
        port = Integer.parseInt(lGUI.txtPort.getText());
        password = lGUI.txtPassword.getText();

        l.connect(target, port, username, password);
        while (l.conStatus != true){}
        //lGUI.dispose();
        System.out.print("you are here");
        JOptionPane.showMessageDialog(lGUI,"You have successfully logged in.", "Login Status", JOptionPane.INFORMATION_MESSAGE);
        lGUI.dispose();
        mConnection2 = l.mConnection;
        mainConnect2 = l.mainConnect;
        mainWindow.lblState.setText("Connected");
        mainWindow.lblState.setForeground(Color.green);
        mainWindow.setVisible(true);
        mainWindow.setResizable(false);
        consoleWrite("Connection to the Server Confirmed!");
        consoleWrite("Username: " + username);
        consoleWrite("Password: *****");
        consoleWrite("IP: " + target);
        consoleWrite("Port: " + port);
        consoleWrite("-> Joining Lobby...");
        lobbyWrite(SYSTEM_MSG_COLOR, username + " has joined the lobby." + newline);

        }


public static void lobbyWrite(Color c, String a) {      //does not implicitly add newline!
        String text = a;
        localFilter filter = new localFilter();
        mainWindow.appendToLobby(c, filter.filterMsg(text));
    }



public static void usrlistUpdate(String[] list) {
        mainWindow.jList1.setListData(list);
    }

protected static JComponent makeTextPanel(String text) {
        JPanel name = new JPanel(false);
        name.setLayout(new GridLayout(1, 1));
        return name;
    }

public static JComponent makeTab(String user){
    JScrollPane sp = new JScrollPane();
    JTextArea tab = new JTextArea();
    sp.setViewportView(tab);
    mainWindow.chatview.addTab(user, sp);
    sp.setName(user);
    return sp;
}

public static String getFocus(){
        Component sp = null;
        String user = null;
        sp = mainWindow.chatview.getSelectedComponent();
        user = sp.getName();
        activeTab = user;
        return user;
}
public static String[] openTabs(){
    Component sp = null;
    String tabs[] = null;
    String user = "";
    int c = 0;
    c = mainWindow.chatview.getTabCount();
    for (int i=0; i <= (c-1); i++){
        sp = mainWindow.chatview.getComponent(c);
        user = sp.getName();
        tabs[i] = user;
    }
    return tabs;
}
private static void consoleWrite(String a) {
    String text = a;
    mainWindow.consoleArea.append(text + newline);
}

public void sendMessage(){
    System.out.print("got it");
    getFocus();
    String txtmsg = mainWindow.txtEntry.getText();
    String c_str = (Integer.toString(settings.getFontColour().hashCode()));
    mainWindow.txtEntry.setText(null);
    if ((activeTab != "jScrollPane4") && (activeTab != "jScrollPane3")) {
        NetworkMessage pmsg = new NetworkMessage(NetworkMessage.NetworkAction.IM,new String[] {activeTab, txtmsg, c_str });
        mainConnect2.sendNetworkMsg(pmsg);
    }
    else {
        NetworkMessage gmsg = new NetworkMessage(NetworkMessage.NetworkAction.IM,new String[] {username, txtmsg, c_str });
        mainConnect2.sendNetworkMsg(gmsg);
    }


    consoleWrite("Message Sent!");
    lobbyWrite(Color.black, "You: ");
    lobbyWrite(settings.getFontColour(), txtmsg + newline);
    //lobbyWrite("Active tab is" + getFocus());
    mainWindow.txtEntry.setText(null);
}

public void sendFile(String user){
    JFileChooser chooser = new JFileChooser();
    chooser.showOpenDialog(null);
    File curFile = chooser.getSelectedFile();
    String fileName = curFile.getName();
    consoleWrite("File Selected, Destination :" + user);
    NetworkMessage fmsg = new NetworkMessage(NetworkMessage.NetworkAction.FILEREQUEST,new String[] {username, fileName, user});
    mainConnect2.sendNetworkMsg(fmsg);


}

public static void fileRequest(String usr, String filename){
    int n = JOptionPane.showConfirmDialog(
            mainWindow, ("Would you like to accept:" + newline + filename + newline + "From: " + usr) ,
            "File Transfer",
            JOptionPane.YES_NO_OPTION);
    if (n == JOptionPane.YES_OPTION) {
        consoleWrite("File Accepted!");
    } else if (n == JOptionPane.NO_OPTION) {
        consoleWrite("File Declined!");
    }

}

public void showSettings(){
    settingsWindow settingsWin = new settingsWindow();
    settingsWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    settingsWin.setVisible(true);
    settingsWin.setResizable(false);
}

public void saveTextFile(){
    System.out.print("save initiated");
        String chat = mainWindow.getChatSession();
    try
          {
             OutputStream out = new FileOutputStream  ( "Saved_ChatSession.txt"  ) ;
             String content = chat;
             for  ( int c = 0; c  <  content.length  (  ) ; ++c )
                 out.write  (  ( char )  content.charAt  ( c )  ) ;
             out.close  (  ) ;
          }
         catch  ( IOException ex )
          {
             System.out.println  ( "Couldn't save file." ) ;
          }

        catch(Exception e) {
        System.out.print("Error");
    }
}

public static void disconnect(){
        mainWindow.dispose();
        
        try{
            mConnection2.close();
            main.interrupt();
        }
        catch(Exception e) {
        System.out.print("Error");
    }
    }

}

