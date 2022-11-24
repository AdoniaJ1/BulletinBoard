// $Id$
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

public class Client implements Runnable
{
    private Server server;
    private List<String> usernames;
    private Socket socket;
    // Getting the streams for the server
    private DataOutputStream dout;
    private DataInputStream din;

    // Constructor
    public Client(int port, Server server ) {
        System.out.println( "inside client");
        JFrame frame = new JFrame();

          synchronized(this.server.getUsers()) {
            List<User> users = this.server.getUsers();
            // For each client ...
            for(User user: users) {
                usernames.add(user.getUsername());
            }
        }
       
        try {
        // Initiate the connection
        System.out.println( "trying to connect "+socket );
        this.socket = new Socket(InetAddress.getLocalHost().getHostName(), 195);
        // We got a connection! Tell the world
        System.out.println( "connected to "+socket );
        // Let's grab the streams and create DataInput/Output streams
        // from them
        din = new DataInputStream( socket.getInputStream() );
        dout = new DataOutputStream( socket.getOutputStream() );

        User user = new User(dout, this.socket);
        JPanel chatPanel = new ChatPanel("Public", user, Group.PUBLIC );
        JPanel userNamePanel = new UserNamePanel(usernames, chatPanel);

        frame.add(userNamePanel);
        chatPanel.setVisible(false);
        frame.add(chatPanel);
        frame.setVisible(true);
        // Start a background thread for receiving messages
        new Thread( this ).start();
        } catch( IOException ie ) { System.out.println( ie ); }
       
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

    // // Gets called when the user types something
    // private void processMessage(String message) {
    // try {
    // // Send it to the server
    // dout.writeUTF( message.toProtocol());
    // // Clear out text input field
    // subjectBox.setText( "" );
    // contentBox.setText( "" );
    // } catch( IOException ie ) { System.out.println( ie ); }
    // }
    // // Background thread runs this: show messages from other window
    // public void run() {
    // try {
    // // Receive messages one-by-one, forever
    // while (true) {
    // // Get the next message
    // String message = din.readUTF();
    // // Print it to our text window
    // chatWindow.append( message+"\n" );
    // }
    // } catch( IOException ie ) { System.out.println( ie ); }
    // }
}