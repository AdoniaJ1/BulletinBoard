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
            System.out.println( "In while loop" );

            String message = din.readUTF();

            System.out.println( "Sending " + message );

            server.sendToAll(message);
        }

    } catch( EOFException ie ) {

    } catch( IOException ie ) {

        ie.printStackTrace();
    } finally {

       //NEEEDDD server.removeConnection( socket );
    }
}
}
