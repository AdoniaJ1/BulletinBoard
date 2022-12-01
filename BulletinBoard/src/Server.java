import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private ServerSocket ss;

    private Hashtable<Socket,DataOutputStream> outputStreams = new Hashtable();

    ArrayList<String> usernames;
    List<User> usersGroup1;
    List<User> usersGroup2;
    List<User> usersGroup3;
    List<User> usersGroup4;
    List<User> usersGroup5;

    List<Message> publicMessages;
    List<Message> messagesGroup1;
    List<Message> messagesGroup2;
    List<Message> messagesGroup3;
    List<Message> messagesGroup4;
    List<Message> messagesGroup5;

    // retrieveMessage(MessageID){
    //     return MessageContent
    // }

    // onConnectionDisplayLastTwoMessage(){
    //     return twoMessages
    // }

    public Server(int portNumber) throws IOException {
        usernames = new ArrayList<String>();
        this.ss = new ServerSocket(portNumber);
        while(true){ //start loop to accept incoming requests

            System.out.println( "Listening on "+ss );
            Socket socket = ss.accept();
            System.out.println( "accepting on " + ss );
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

            outputStreams.put( socket, dout );

            new ServerThread(this, socket);
        }
    }

    Enumeration getOutputStreams() {
        return outputStreams.elements();
    }

    void sendToAll(String message) {
        String[] splitMessage = message.split("@");
        String group = splitMessage[4];
        String sender = splitMessage[1];

        synchronized( outputStreams ) {
            // For each client ...
            for (Enumeration e = getOutputStreams(); e.hasMoreElements(); ) {
            // ... get the output stream ...
            DataOutputStream dout = (DataOutputStream)e.nextElement();
            // ... and send the message
            try {
            System.out.println( "Sending TO ALL : " + message );
            dout.writeUTF( message );

            } catch( IOException ie ) { System.out.println( ie ); }
            }
        }
    }

    // void addUserToServer(user){
    //     //display username to bulletin
    // }

    // void addUserToGroup(user, group){
    //     //display username to group bulletin
    // }

    // postRecieved(Message){
    //     //
    // }

    // removeUserFromGroup(){
    //     //tell all other users
    // }

    // requestForUsersInGroup(){

    // }

  }
