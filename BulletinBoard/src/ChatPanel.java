import javax.swing.JPanel;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;

public class ChatPanel extends JPanel implements Runnable{
    //private static JPanel chatPanel = new JPanel();

    private static JTextField subjectBox = new JTextField(25);
    private static JTextField contentBox = new JTextField(25);
    private JTextArea chatWindow = new JTextArea();
    private static JButton enterButton = new JButton("Enter");
    // private static JButton disconnectButton = new JButton("Disconnect");
    // private static JButton joinGroup1Button = new JButton("Join Group 1");
    // private static JButton joinroup2Button = new JButton("Join Group 2");
    // private static JButton joinGroup3Button = new JButton("Join Group 3");
    // private static JButton joinGroup4Button = new JButton("Join Group 4");
    // private static JButton joinGroup5Button = new JButton("Join Group 5");

   //setting the layuout for the

    private DataOutputStream dout;

    private Socket socket;
    private Server server;

    private int messageID;
    private Message messageEntered;
    private JLabel groupName;
    private User user;
    private Group group;
    private DataInputStream din;

    public ChatPanel(String groupName, User user, Group group) {

        this.user = user;
        this.group = group;
        //setLayout(getLayout());
        setGroupName(groupName);
        this.add(subjectBox);
        this.add(contentBox);
        this.add(enterButton);
        this.add(chatWindow);
        enterButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                enterButtonClick(e);
            }
        });
    }

     public void enterButtonClick(ActionEvent e){
        System.out.println( "enterbuttonClick!!!!!\n" );
        String subject = subjectBox.getText();
        String content = contentBox.getText();
        messageEntered = new Message(this.messageID, "hehe", LocalDateTime.now(), subject, content, this.group);
        this.chatWindow.append(messageEntered.toString()+"\n");

        processMessage(messageEntered);
        this.messageID++;
    }

    private void processMessage(Message message ) {
        try {
            System.out.println( "HI" );
        // Send it to the server
        this.user.getOutputStream().writeUTF(message.toProtocol());
        // Clear out text input field
        subjectBox.setText( "" );
        contentBox.setText( "" );
        } catch( IOException ie ) { System.out.println( ie ); }
    }

    public JLabel getGroupName() {
        return this.groupName;
    }

    public Group getGroup() {
        return this.group;
    }

    public JTextArea getChatWindow() {
        return this.chatWindow;
    }

    public void setGroupName(String groupName) {

        this.groupName = new JLabel(groupName);
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

    // public void disconnectButtonClick(ActionEvent e){
    //     this.server.removeConnection(user, group);
    // }

    // public void addUserToGroup(ActionEvent e){
    //     this.server.addUserToGroup(user, group);
    // }
}
