
public class Main {

	public static void main(String[] args) throws Exception{
		//int port = Integer.parseInt(args[0]); //getting port number from command line
		int port = 1990;
		new Server(port); //creating new server based on port number
		//chat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
