import java.io.*;
import java.net.Socket;

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

        while (true) {

            String message = din.readUTF();

            System.out.println( "Sending " + message );

            String[] splitMessage = message.split(":");
            String group = splitMessage[4];
            String sender = splitMessage[1];

            server.sendToAll(message, group, sender);
        }
    } catch( EOFException ie ) {

    } catch( IOException ie ) {

        ie.printStackTrace();
    } finally {

       //NEEEDDD server.removeConnection( socket );
    }
}
}
