import javax.swing.JPanel;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;

public class ChatPanel extends JPanel{

    private JTextField subjectBox = new JTextField(20);
    private JTextArea contentBox = new JTextArea(5,20);
    private JTextArea chatWindow = new JTextArea(5,20);
    private JButton enterButton = new JButton("Enter");
    // private static JButton disconnectButton = new JButton("Disconnect");

    private int messageID;
    private Message messageEntered;
    private JLabel groupName;
    private Group group;
    private String sender;
    JPanel chats;

    public ChatPanel() {
    }
    public ChatPanel(String groupName, Group group, DataOutputStream dout) {

        this.chats = new JPanel();
        this.group = group;
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor= GridBagConstraints.WEST;
        add(new JLabel("Subject:"), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = GridBagConstraints.RELATIVE;
        gbc.gridy = 0;
        this.add(subjectBox, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(new JLabel("Content:"), gbc);

        gbc.gridx++;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JScrollPane(contentBox), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth=GridBagConstraints.REMAINDER;
        gbc.gridx = GridBagConstraints.RELATIVE;
        gbc.gridy = 0;
        this.add(enterButton,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(chats,gbc);

        setGroupName(groupName);
        enterButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    enterButtonClick(e, dout);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
    }

     public void enterButtonClick(ActionEvent e, DataOutputStream dout) throws IOException{
        System.out.println( "enterbuttonClick!!!!!\n" );
        String subject = subjectBox.getText();
        String content = contentBox.getText();
        messageEntered = new Message(this.messageID, this.getUsername(), LocalDateTime.now(), subject, content, this.group);

        processMessage(messageEntered, dout);
        this.messageID++;
    }

    private void processMessage(Message message, DataOutputStream dout ) throws IOException {

        System.out.println( "processing message: "+message.toProtocol() );

        // Send it to the server
        dout.writeUTF(message.toProtocol());
        // Clear out text input field
        subjectBox.setText( "" );
        contentBox.setText( "" );
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

    public String getUsername() {
        return this.sender;
    }

    public void setUsername(String username) {
        this.sender = username;
    }

    // public void disconnectButtonClick(ActionEvent e){
    //     this.server.removeConnection(user, group);
    // }

    // public void addUserToGroup(ActionEvent e){
    //     this.server.addUserToGroup(user, group);
    // }
}
