// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

// class Switch_Frame implements ActionListener{
// 	static JFrame userNameEntryFrame;
// 	static JFrame bulletinBoardFrame;
// 	static JButton enter;

// 	private static JLabel directionsLabel = new JLabel("Enter a unique username, then click the 'click me' button'");
//     private static JLabel outputLabel = new JLabel();
//     private static JTextField userNameBox = new JTextField(25);
//     private static JButton userNameButton = new JButton("Enter");
// 	public static void main(String args[])
// 	{
// 		//Create username registration box
// 		userNameEntryFrame = new JFrame("User Registration");
// 		userNameEntryFrame.setSize(250,250);
// 		userNameEntryFrame.setLayout(null);
// 		userNameEntryFrame.setBackground(Color.white);
// 		//Create next and close buttons
// 		enter = new JButton("Enter"); 
// 		enter.setBounds(75,50,100,50);
// 		//Add the buttons to frame 1
// 		userNameEntryFrame.add(enter);
// 		//Create an object
// 		Switch_Frame obj = new Switch_Frame();
// 		//Associate ActionListener with the buttons
// 		enter.addActionListener(obj);
// 		//Display frame 1
// 		userNameEntryFrame.setVisible(true);

// 		setLayout(new FlowLayout());
//         getContentPane().add(directionsLabel);
//         getContentPane().add(userNameBox);
//         getContentPane().add(userNameButton);
//         getContentPane().add(outputLabel);

//         userNameButton.addActionListener(new ActionListener(){
//             public void actionPerformed(ActionEvent e){
//                 buttonClick(e); //records the username on click of button
//             }
//         });
// 	}
// 	//Function to perform actions related to button clicked
// 	public void actionPerformed(ActionEvent e)
// 	{
// 		String button = e.getActionCommand();
// 		if(button.equals("Enter"))
// 		{
// 			userNameEntryFrame.dispose();
// 			create_frame2();
// 		}
// 	}
// 	//function to create Frame 2
// 	public static void create_frame2()
// 	{
// 		//Create frame 2
// 		bulletinBoardFrame = new JFrame("Bulletin Board");
// 		bulletinBoardFrame.setSize(250,250);
// 		bulletinBoardFrame.setLayout(null);
// 		bulletinBoardFrame.setBackground(Color.white);
// 		//Create back button
// 		JButton back = new JButton("Back"); 
// 		back.setBounds(75,100,100,50);
// 		//Add the button to frame 2
// 		bulletinBoardFrame.add(back);
// 		//Create an object
// 		Switch_Frame obj=new Switch_Frame();
// 		//Associate ActionListener with the buttons
// 		back.addActionListener(obj);
// 		//Display frame 2
// 		bulletinBoardFrame.setVisible(true);
// 	}

// 	public static void buttonClick(ActionEvent e){
//         String userName = userNameBox.getText();
//         String outputMessage = "Welcome to the server: " + userName;
//         outputLabel.setText(outputMessage);

//         // this.dispose();
//         // EnglishFrame eng = new EnglishFrame();
//         // eng.setVisible(true);

//         //add username to a list once recorded.
//         //compare username to other names to see if it is unique
//     }
// }


