import java.util.ArrayList;

import javax.swing.*;
public class BulletinTabbedPane extends JTabbedPane{
    JPanel joinButtons;
    String username;
    ChatPanel publicBoard;
    ArrayList<ChatPanel> chatPanels;

    public JPanel getJoinButtons() {
        return this.joinButtons;
    }


    public void setJoinButtons(JPanel joinButtons) {
        this.joinButtons = joinButtons;
        this.add("Join Other Chat Rooms", joinButtons);
    }

    public BulletinTabbedPane() {

    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
        publicBoard.setUsername(username);
    }

    public void addChatPanel(ChatPanel chatPanel) {
        this.addTab(chatPanel.getGroupName().getText(), null, chatPanel,
                null);
        this.publicBoard=chatPanel;
    }

}
