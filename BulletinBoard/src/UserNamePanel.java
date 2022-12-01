import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class UserNamePanel extends JPanel{
    private static JLabel directionsLabel = new JLabel("Enter a unique username to join Bulletin Server, then click the Enter Button");
    private static JButton userNameEnterButton = new JButton("Enter");
    private static JTextField userNameBox = new JTextField(25);
    private List<String> usernames;
    private String username;
    DataOutputStream dout;

    public void enterButtonClick(ActionEvent e, BulletinTabbedPane tabbedPane){
        String username = userNameBox.getText();
        boolean hasUsername = this.usernames.contains(username);
        if (hasUsername){
            System.out.println( "username already in use" );
            JOptionPane.showMessageDialog(null,
                              "username already in use!",
                              "Warning",
                              JOptionPane.ERROR_MESSAGE);
        }else{
            System.out.println( "username is available" );
            this.setVisible(false);
            tabbedPane.setUsername(username);
            tabbedPane.setVisible(true);
            returnNewUsernameToServer(this.dout, username);
        }
    }

    public UserNamePanel(DataOutputStream dout, DataInputStream din, BulletinTabbedPane nextScreen) {
        this.dout = dout;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(directionsLabel);
        userNameEnterButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                enterButtonClick(e,nextScreen); //records the username on click of button
            }
        });

        this.add(userNameBox);
        this.add(userNameEnterButton);

        this.usernames = Arrays.asList(getUsernamesFromServer(dout, din));
    }

    public String[] getUsernamesFromServer(DataOutputStream dout, DataInputStream din){

        String message="";
        String command = "";
        while(!command.equals("%usernames%")){
        try {
            requestUsernamesFromServer(dout);
            message = din.readUTF();
            command = message.substring(0,11);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }}
        String listOfUsernames = message.substring(11,message.length());
        String [] splitMessage = listOfUsernames.split(", ");

        return splitMessage;
    }

    public void requestUsernamesFromServer(DataOutputStream dout){
        try {
            dout.writeUTF("usernames%%");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void returnNewUsernameToServer(DataOutputStream dout, String newName){
        try {
            dout.writeUTF("newname%%"+newName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
