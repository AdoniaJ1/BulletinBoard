import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Component;


public class JoinGroupsPanel extends JPanel{

    private JToggleButton joinGroup1Button = new JToggleButton("Join Group 1");
    private JToggleButton joinGroup2Button = new JToggleButton("Join Group 2");
    private JToggleButton joinGroup3Button = new JToggleButton("Join Group 3");
    private static JToggleButton joinGroup4Button = new JToggleButton("Join Group 4");
    private static JToggleButton joinGroup5Button = new JToggleButton("Join Group 5");


    BulletinTabbedPane tabbedPane;

    public JoinGroupsPanel(BulletinTabbedPane tabbedPane, DataOutputStream dout){
        this.tabbedPane=tabbedPane;
        GridLayout layout = new GridLayout(0,2);
        this.setLayout(layout);

        joinGroup1Button.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                joinGroup(joinGroup1Button, e, dout, tabbedPane);
            }
        });

        joinGroup2Button.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                joinGroup(joinGroup2Button, e, dout, tabbedPane);
            }
        });

        joinGroup3Button.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                joinGroup(joinGroup3Button, e, dout, tabbedPane);
            }
        });

        joinGroup4Button.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                joinGroup(joinGroup4Button, e, dout, tabbedPane);
            }
        });

        joinGroup5Button.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                joinGroup(joinGroup5Button, e, dout, tabbedPane);
            }
        });

        this.add(joinGroup1Button);
        this.add(joinGroup2Button);
        this.add(joinGroup3Button);
        this.add(joinGroup4Button);
        this.add(joinGroup5Button);
    }

    private void joinGroup(JToggleButton button, ItemEvent e, DataOutputStream dout, BulletinTabbedPane tabbedPane) {
        String buttonLabel = ((JToggleButton) e.getSource()).getText();
        String username = tabbedPane.getUsername();
        String joinMessage = "joinGroup%%";
        String leaveMessage = "leaveGroup%%";
        ChatPanel chatPanel = new ChatPanel();
        int state = e.getStateChange();
        switch (buttonLabel) {
            case "Join Group 1":
                if(state==ItemEvent.SELECTED){
                    joinMessage = joinMessage + "ONE%";
                    chatPanel = new ChatPanel("ONE", Group.ONE,dout);
                    button.setText("Leave Group 1");
                    chatPanel.setUsername(username);
                }else{

                    leaveMessage = leaveMessage + "ONE%";
                    button.setText("Join Group 1");
                    System.out.println("in the else statement");
                }
                break;
            case "Join Group 2":
                if(state==ItemEvent.SELECTED){
                    joinMessage = joinMessage + "TWO%";
                    chatPanel = new ChatPanel("TWO", Group.TWO,dout);
                    button.setText("Leave Group 2");
                    chatPanel.setUsername(username);
                }else{
                    leaveMessage = leaveMessage + "TWO%";
                    button.setText("Join Group 2");
                }
                break;
            case "Join Group 3":
                if(state==ItemEvent.SELECTED){
                    joinMessage = joinMessage + "THREE%";
                    chatPanel = new ChatPanel("THREE", Group.THREE,dout);
                    button.setText("Leave Group 3");
                    chatPanel.setUsername(username);
                }else{
                    leaveMessage = leaveMessage + "THREE%";
                    button.setText("Join Group 3");
                }
                break;
            case "Join Group 4":
                if(state==ItemEvent.SELECTED){
                    joinMessage = joinMessage + "FOUR%";
                    chatPanel = new ChatPanel("FOUR", Group.FOUR,dout);
                    button.setText("Leave Group 4");
                    chatPanel.setUsername(username);
                }else{
                    leaveMessage = leaveMessage + "FOUR%";
                    button.setText("Join Group 4");
                }
                break;
            case "Join Group 5":
                if(state==ItemEvent.SELECTED){
                    joinMessage = joinMessage + "FIVE%";
                    chatPanel = new ChatPanel("FIVE", Group.FIVE,dout);
                    button.setText("Leave Group 5");
                    chatPanel.setUsername(username);
                }else{
                    leaveMessage = leaveMessage + "FIVE%";
                    button.setText("Join Group 5");
                }
                break;
        }
        try {
            System.out.println(joinMessage+tabbedPane.getUsername());
            dout.writeUTF(joinMessage+tabbedPane.getUsername());
            this.tabbedPane.addTab(chatPanel.getGroupName().getText(), null, chatPanel,
                null);
            this.tabbedPane.revalidate();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
