// $Id$
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.*;
public class Client implements Runnable
{
    private Socket socket;
    private DataOutputStream dout;
    private DataInputStream din;
    private BulletinTabbedPane tabbedPane;
    JFrame frame;

    // Constructor
    public Client(int port) {

        System.out.println( "inside client");
        this.frame = new JFrame();
        frame.setSize(700,700);

        try {
        // Initiate the connection
        System.out.println( "trying to connect to socket" );
        this.socket = new Socket(InetAddress.getLocalHost().getHostName(), 1990);
        System.out.println( "connected to " + socket );

        din = new DataInputStream(socket.getInputStream() );
        dout = new DataOutputStream(socket.getOutputStream() );

        ChatPanel chatPanel = new ChatPanel("PUBLIC", Group.PUBLIC,dout);
        this.tabbedPane = new BulletinTabbedPane();
        JoinGroupsPanel joinGroupsPanel = new JoinGroupsPanel(tabbedPane, dout);

        this.tabbedPane.setJoinButtons(joinGroupsPanel);
        this.tabbedPane.addChatPanel(chatPanel);

        this.tabbedPane.setVisible(false);

        UserNamePanel usernamePanel = new UserNamePanel(dout, din,tabbedPane);
        frame.add(usernamePanel);

        usernamePanel.setVisible(true);

        frame.setVisible(true);

       //Todo make it pretty GridBagConstraints gbc = new GridBagConstraints();

        frame.add(tabbedPane, BorderLayout.CENTER);

        new Thread( this ).start();
        } catch( IOException ie ) { System.out.println( ie ); }

    }

    public static void main(String args[]) throws IOException
    {
        Client client = new Client(1990);
    }

    @Override
    public void run() {
        try {
        while (true) {
            String message = din.readUTF();
            String[] splitMessage = message.split("@");
            String bulletinMessage = "";

            if(splitMessage[0].equals("newUserAlert")){
                int totalTabs = tabbedPane.getTabCount();
                for(int i = 1; i < totalTabs; i++)
                {
                    ChatPanel c = displayToChatPanels(i, splitMessage[1], null, tabbedPane,splitMessage[2]);
                    tabbedPane.setComponentAt(i, c);
                    tabbedPane.revalidate();
                }

            }else{
                bulletinMessage = splitMessage[0]+","+splitMessage[1]+","+splitMessage[2]+","+splitMessage[3];
                JToggleButton messageAsButton = new JToggleButton(bulletinMessage);
                messageAsButton.addItemListener(new ItemListener(){
                    public void itemStateChanged(ItemEvent itemEvent) {
                        int state = itemEvent.getStateChange();
                        if (state == ItemEvent.SELECTED){
                            try {
                                System.out.println(splitMessage[5]);
                                messageAsButton.setText(splitMessage[5]);
                            } catch(ArrayIndexOutOfBoundsException e) {
                                messageAsButton.setText("!empty post!");
                            }
                        }else{
                            messageAsButton.setText(splitMessage[0]+","+splitMessage[1]+","+splitMessage[2]+","+splitMessage[3]);
                        }
                    }
                });
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = GridBagConstraints.RELATIVE;
                String groupToSendString = splitMessage[4];

                int totalTabs = tabbedPane.getTabCount();
                for(int i = 1; i < totalTabs; i++)
                {
                    ChatPanel c = displayToChatPanels(i, groupToSendString, messageAsButton, tabbedPane, null);
                    tabbedPane.setComponentAt(i, c);
                    tabbedPane.revalidate();
                }
                }
    }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ChatPanel displayToChatPanels(Integer i, String group, JToggleButton button, BulletinTabbedPane tabbedPane, String username){
        ChatPanel c = (ChatPanel) tabbedPane.getComponentAt(i);
        String groupName = c.getGroupName().getText();
        GridBagLayout chatLay = (GridBagLayout) c.chats.getLayout();
        GridBagConstraints chatGbc = chatLay.getConstraints(c);
        chatGbc.gridx = 0;
        chatGbc.gridy = GridBagConstraints.RELATIVE;

        if(groupName.equals(group) && button!=null){
            System.out.println("adding message button");
            c.chats.add(button,chatGbc);
        }else if(groupName.equals(group)){
            JTextArea newUserMessage = new JTextArea();
            newUserMessage.setText("New User: \""+username+"\" has entered the chat!");
            newUserMessage.setEditable(false);
            c.chats.add(newUserMessage, chatGbc);
        }
        return c;
    }
}
