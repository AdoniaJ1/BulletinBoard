import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class UserNamePanel extends JPanel{
    private static JLabel directionsLabel = new JLabel("Enter a unique username, then click the Enter Button");
    private static JButton userNameEnterButton = new JButton("Enter");
    private static JTextField userNameBox = new JTextField(25);
    private List<String> usernames;

    public void enterButtonClick(ActionEvent e, JPanel nextScreen){
        String userName = userNameBox.getText();
        boolean hasUsername = this.usernames.contains(userName);
        if (hasUsername){
            System.out.println( "username already in use" );
        }else{
            //connect to server
            this.usernames.add(userName);
            this.setVisible(false);
            nextScreen.setVisible(true);
        }
    }

    public UserNamePanel(List<String> usernames, JPanel nextScreen) {
        setUsernames(usernames);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(directionsLabel);
        userNameEnterButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                enterButtonClick(e, nextScreen); //records the username on click of button
            }
        });

        this.add(userNameBox);
        this.add(userNameEnterButton);
    }

    public List<String> getUsernames() {
        return this.usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

}
