import javax.swing.JFrame;

//announce when user enters and leaves the chat
//assign username to user
//when a user enters the group see last 2 messages


//private chat
//Once a user is connected to the server, the server
// provides a list of 5 groups. The user can then select the desired group by id or by name. A user can join multiple
// groups at the same time. 

public class Main {

	public static void main(String[] args) throws Exception{
		//int port = Integer.parseInt(args[0]); //getting port number from command line
		int port = 1990;
		Server server = new Server(port); //creating new server based on port number
		//Multiple_Chat_Server_Chat chat = new Multiple_Chat_Server_Chat();
		new Client(1990, server);
		//chat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}