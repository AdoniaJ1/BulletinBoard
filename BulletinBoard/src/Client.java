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
        System.out.println( "trying to connect to socket" );
        this.socket = new Socket(InetAddress.getLocalHost().getHostName(), 1990);
        System.out.println( "connected to " + socket );

        din = new DataInputStream(socket.getInputStream() );
        dout = new DataOutputStream(socket.getOutputStream() );

        User user = new User(dout, this.socket);
        JPanel chatPanel = new ChatPanel("Public", user, Group.PUBLIC );

       // user.setChatPanel(chatPanel);

        frame.add(chatPanel);
        frame.setVisible(true);

        } catch( IOException ie ) { System.out.println( ie ); }

    }
    public static void main(String args[]) throws IOException
    {
        Client client = new Client(1990);
    }
}
