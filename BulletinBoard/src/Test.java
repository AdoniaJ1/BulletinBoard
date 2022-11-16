// import java.io.*;

// private void showAllUsers(String message){
//     displayToUser(message);
//     Iterator it = clientOutputStreams.iterator();
        
//     while (it.hasNext()) 
//     {
//         try 
//         {
//             PrintWriter writer = (PrintWriter) it.next();
//             writer.println(message);
//             writer.flush();
//         } 
//         catch (Exception ex) {}
//     } 
// }

// private void displayToUser(final String text){
//         SwingUtilities.invokeLater(
//             new Runnable(){
//                 public void run(){
//                     discussionBoard.append(text+"\n");
//                 }
//             }
//         );
// }

// public void startServer()
// {
//     new Thread(new ServerStart()).start();
// }

// public class Server implements Runnable {
//     @Override
//     public void run() {
//         clientOutputStreams = new ArrayList<PrintWriter>();
//         users = new ArrayList<String>(); 
            
//         try{
                
//             ServerSocket socket = new ServerSocket(1990);
                
//             while(true){
//                 Socket clientSocket = socket.accept();
//                 PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
//                 //clientOutputStreams.add(writer);
//                 // sendToAll("A NEW CLIENT IS CONNECTED");
//                 new Thread(new newClient(clientSocket, writer)).start();        
//             }
                
//         }catch(Exception e){}
//     }
// }

// public class PublicChatServer extends JFrame {

//     private JTextField boardPost;
//     private JTextArea textBox;
//     private JTextArea discussionBoard;
//     private JButton button = new JButton("SEND");
//     public ArrayList<PrintWriter> userMessages;
//     public ArrayList<User> users = new ArrayList<User>();

//     public PublicChat() {
//         super("Private Chat");
//         boardPost = new JTextField();
//         boardPost.setEditable(true);

//         boardPost.addActionListener(
//             new ActionListener() {
//                 public void actionPerformed(){
//                     sendToAll(event.getActionCommand());
//                     boardPost.setText("");
//                 }  
//             }
//         );
//         add(boardPost);
//         discussionBoard = new JTextArea();
//         discussionBoard.setEditable(false);
//         setVisible(true);
//     }

// }

