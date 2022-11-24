import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Scanner;

public class Server {

    private int portNumber;
    //int socketNumber;
    static int clientID;
    //static Vector<ClientIdentifier> clist = new Vector<>();
    
    private Socket socket;
    private ServerSocket ss;
    List<User> users;
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

        this.ss = new ServerSocket(portNumber);
        this.socket = new Socket();
        
        this.portNumber = portNumber;
        while(true){ //start loop to accept incoming requests
            
            System.out.println( "Listening on "+ss );
            this.socket = ss.accept();
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            //outputStreams.put( socket, dout );
            User newUser = new User(dout, socket);
            users.add(newUser);

            //ClientIdentifier cid = new ClientIdentifier(socket, "Client ID: " + clientID, din, dout);
            //Thread t = new Thread(ClientIdentifier); 

            new ServerThread(this, this.socket);
        }
    }

    public List<User> getUsers(){
        return this.users;
    }
    
    void sendToAll(String message, String group, String sender) {
        // We synchronize on this because another thread might be
        // calling removeConnection() and this would screw us up
        // as we tried to walk through the list

        synchronized(this.getUsers()) {
            List<User> users = this.getUsers();
            // For each client ...
            for(User user: users) {
            // ... get the output stream ...
                String username = user.getUsername();
                if(username != sender){
                    DataOutputStream dout = user.getOutputStream();
                // ... and send the message
                    try {
                        switch(group) {
                        case "PUBLIC":
                            dout.writeUTF(message);
                            break;
                        case "ONE":
                            if (this.usersGroup1.contains(user)){
                                dout.writeUTF(message);
                            }
                            break;
                        case "TWO":
                            if (this.usersGroup2.contains(user)){
                                dout.writeUTF(message);
                            }
                            break;
                        case "THREE":
                            if (this.usersGroup3.contains(user)){
                                dout.writeUTF(message);
                            }
                            break;
                        case "FOUR":
                            if (this.usersGroup4.contains(user)){
                                dout.writeUTF(message);
                            }
                            break;
                        case "FIVE":
                            if (this.usersGroup5.contains(user)){
                                dout.writeUTF(message);
                            }
                            break;
                        default:
                            break;
                    }
                    } catch( IOException ie ) { System.out.println( ie ); }
                }
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