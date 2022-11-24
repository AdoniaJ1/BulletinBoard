import java.io.*;
import java.net.Socket;

public class  User {
    private String username;
    private Socket socketNumber;
    private DataOutputStream outputStream;
    private boolean isConnected;
    
    public User(DataOutputStream outputStream, Socket socketNumber){//other arguments?
        //this.username = username;
        this.outputStream = outputStream;
        this.socketNumber = socketNumber;
        this.isConnected = true;
    }

    public User(String username){
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public Socket getSocketNumber() {
        return this.socketNumber;
    }

    public DataOutputStream getOutputStream() {
        return this.outputStream;
    }

    public boolean getIsConnected() {
        return this.isConnected;
    }

    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

}