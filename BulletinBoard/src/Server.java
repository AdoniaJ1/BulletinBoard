import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private ServerSocket ss;
    private List<User> users;
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
    private DataInputStream din;

    // retrieveMessage(MessageID){
    //     return MessageContent
    // }

    // onConnectionDisplayLastTwoMessage(){
    //     return twoMessages
    // }

    public Server(int portNumber) throws IOException {
        //users = Collections.<User>emptyList();
        this.ss = new ServerSocket(portNumber);
        while(true){ //start loop to accept incoming requests

            System.out.println( "Listening on "+ss );
            Socket socket = ss.accept();
            System.out.println( "accepting on " + ss );
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

            din = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
            // User newUser = new User(dout, socket);
            // users.add(newUser);

            new ServerThread(this, socket);
        }
    }

    public List<User> getUsers(){
        return this.users;
    }

    void sendToAll(String message) {
        String[] splitMessage = message.split(":");
        String group = splitMessage[4];
        String sender = splitMessage[1];

        synchronized(this.getUsers()) {
            List<User> users = this.getUsers();

            for(User user: users) {

                user.getChatPanel().getChatWindow().append(message);

                // String username = user.getUsername();
                // if(username != sender)
                //{

                //}
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
