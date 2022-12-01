import java.io.*;
import java.net.Socket;

import javax.swing.JFrame;

public class ServerThread extends Thread
{

    private Server server;
    private Socket socket;

    public ServerThread( Server server, Socket socket ) {

        this.server = server;
        this.socket = socket;

        start();
    }

public void run() {
    try {
        System.out.println( "In server thread ");

        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream() );

        while (true) {
            String message = din.readUTF();
            String[] commandAndContents = message.split("%%",2);
            String str = "";
            switch (commandAndContents[0]) {
                case "usernames":
                    System.out.println( "usernames requested" );
                    str = server.usernames.toString();
                    str = str.substring(1, str.length() - 1);
                    dout.writeUTF("%usernames%"+str);
                    break;
                case "newname":
                    System.out.println( "username \"" + commandAndContents[1]+"\" added");
                    server.usernames.add(commandAndContents[1]);
                    break;
                case "joinGroup":
                    String[] splitMessage = commandAndContents[1].split("%");
                    Group joinedGroup = Group.valueOf(splitMessage[0]);
                    for (Group group : Group.values()) {
                        if(joinedGroup == group){
                            server.groupListsMap.get(group).add(splitMessage[1]);
                        }
                    }
                    dout.writeUTF("newUser@"+splitMessage[0]+"@"+splitMessage[1]);

                    System.out.println(splitMessage[0]+splitMessage[1]);
                    break;
                default:
                    server.sendToAll(message);
                    break;
            }

        }

    } catch( EOFException ie ) {

    } catch( IOException ie ) {

        ie.printStackTrace();
    } finally {

       //NEEEDDD server.removeConnection( socket );
    }
}
}
