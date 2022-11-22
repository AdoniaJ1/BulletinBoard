import java.util.List;

import javax.swing.*;
import java.awt.event.*;

public class UserNamePanel extends JPanel{
    private static JLabel directionsLabel = new JLabel("Enter a unique username, then click the 'click me' button'");
    private static JButton userNameEnter = new JButton("Enter");
    private static JTextField userNameBox = new JTextField(25);
    private List<String> usernames;

    
    public void enterButtonClick(ActionEvent e){
        String userName = userNameBox.getText();
        boolean hasUsername = this.usernames.contains(userName);
        if (hasUsername){
            System.out.println( "username already in use" );
        }else{
            this.usernames.add(userName);
            this.setVisible(false);
        }

    }

    public UserNamePanel(List<String> usernames) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(directionsLabel);

        userNameEnter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                enterButtonClick(e); //records the username on click of button
            }
        });
        this.add(userNameBox);
        this.add(userNameEnter);
        setUsernames(usernames);
    }

    public List<String> getUsernames() {
        return this.usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }
    
}