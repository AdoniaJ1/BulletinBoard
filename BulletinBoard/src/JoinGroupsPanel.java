import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinGroupsPanel extends JPanel{
    private static JButton joinGroup1Button = new JButton("Join Group 1");
    private static JButton joinGroup2Button = new JButton("Join Group 2");
    private static JButton joinGroup3Button = new JButton("Join Group 3");
    private static JButton joinGroup4Button = new JButton("Join Group 4");
    private static JButton joinGroup5Button = new JButton("Join Group 5");
    BulletinTabbedPane tabbedPane;

    public JoinGroupsPanel(BulletinTabbedPane tabbedPane, DataOutputStream dout){
        this.tabbedPane=tabbedPane;
        GridLayout layout = new GridLayout(0,2);
        this.setLayout(layout);

        joinGroup1Button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                joinGroup(e, dout, tabbedPane);
            }
        });

        joinGroup2Button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                joinGroup(e, dout, tabbedPane);
            }
        });

        joinGroup3Button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                joinGroup(e, dout, tabbedPane);
            }
        });

        joinGroup4Button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                joinGroup(e, dout, tabbedPane);
            }
        });

        joinGroup5Button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                joinGroup(e, dout, tabbedPane);
            }
        });

        this.add(joinGroup1Button);
        this.add(joinGroup2Button);
        this.add(joinGroup3Button);
        this.add(joinGroup4Button);
        this.add(joinGroup5Button);
    }

    private void joinGroup(ActionEvent e, DataOutputStream dout, BulletinTabbedPane tabbedPane) {
        String buttonLabel = ((JButton) e.getSource()).getText();
        String username = tabbedPane.getUsername();
        String joinMessage = "joinGroup%%";
        ChatPanel chatPanel = new ChatPanel();
        switch (buttonLabel) {
            case "Join Group 1":
                joinMessage = joinMessage + "ONE%";
                chatPanel = new ChatPanel("ONE", Group.ONE,dout);
                ((JButton) e.getSource()).setVisible(false);
                chatPanel.setUsername(username);
                break;
            case "Join Group 2":
                joinMessage = joinMessage + "TWO%";
                chatPanel = new ChatPanel("TWO", Group.TWO,dout);
                ((JButton) e.getSource()).setVisible(false);
                chatPanel.setUsername(username);
                break;
            case "Join Group 3":
                joinMessage = joinMessage + "THREE%";
                chatPanel = new ChatPanel("THREE", Group.THREE,dout);
                ((JButton) e.getSource()).setVisible(false);
                chatPanel.setUsername(username);
                break;
            case "Join Group 4":
                joinMessage = joinMessage + "FOUR%";
                chatPanel = new ChatPanel("FOUR", Group.FOUR,dout);
                ((JButton) e.getSource()).setVisible(false);
                chatPanel.setUsername(username);
                break;
            case "Join Group 5":
                joinMessage = joinMessage + "FIVE%";
                chatPanel = new ChatPanel("FIVE", Group.FIVE,dout);
                ((JButton) e.getSource()).setVisible(false);
                chatPanel.setUsername(username);
                break;
        }
        try {
            System.out.println(joinMessage+tabbedPane.getUsername());
            dout.writeUTF(joinMessage+tabbedPane.getUsername());
            this.tabbedPane.addTab(chatPanel.getGroupName().getText(), null, chatPanel,
                null);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
