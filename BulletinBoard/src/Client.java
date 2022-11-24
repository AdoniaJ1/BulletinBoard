// $Id$
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

public class Client
{
    private Server server;
    private List<String> usernames;
    private Socket socket;
    // Getting the streams for the server
    private DataOutputStream dout;
    private DataInputStream din;

    // Constructor
    public Client(int port) {
        System.out.println( "inside client");
        JFrame frame = new JFrame();

        //   synchronized(this.server.getUsers()) {
        //     List<User> users = this.server.getUsers();
        //     // For each client ...
        //     for(User user: users) {
        //         usernames.add(user.getUsername());
        //     }
        // }

        try {
        // Initiate the connection
        System.out.println( "trying to connect "+socket );
        this.socket = new Socket(InetAddress.getLocalHost().getHostName(), 1990);
        // We got a connection! Tell the world
        System.out.println( "connected to "+socket );
        // Let's grab the streams and create DataInput/Output streams
        // from them
        din = new DataInputStream( socket.getInputStream() );
        dout = new DataOutputStream( socket.getOutputStream() );

        User user = new User(dout, this.socket);
        JPanel chatPanel = new ChatPanel("Public", user, Group.PUBLIC );
       // JPanel userNamePanel = new UserNamePanel(usernames, chatPanel);

        //frame.add(userNamePanel);
        //chatPanel.setVisible(false);
        frame.add(chatPanel);
        frame.setVisible(true);

        } catch( IOException ie ) { System.out.println( ie ); }

    }
    public static void main(String args[])
    {
        Client client = new Client(1990);
    }
}
