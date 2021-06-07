package gui.windows;

import java.awt.*;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import contact.MessageSender;
import gui.factory.*;
import resources.ColorResources;
import resources.TextResources;
import roomCustomer.Room;
import roomCustomer.RoomCustomerReader;

public class ForgotPasswordWindow extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;

	private String path = "buttonImages/Back Button";
	private String lang = TextResources.imageLang;
	private ImageIcon backImage = new ImageIcon(path + lang);
	JButton backBtn;

	private String path2 = "buttonImages/RemindMe";
	private ImageIcon remindMeImage = new ImageIcon(path2 + lang);
	private JButton remindMeBtn;

	private ImageIcon backgroundImage = new ImageIcon("Background Images/loginBackground.png");
	private JLabel backgroundLabel;

	private JLabel forgotPassword;
	private JLabel txtPass;
	private JLabel txtSendEmail;

	private ImageIcon emailIcon = new ImageIcon("Icons/email-optionpane.png");
	private ImageIcon roomIcon = new ImageIcon("Icons/door.png");

	// constructor
	public ForgotPasswordWindow() {
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}

	// settings for the frame
	public void windowsConfiguration() {
		this.setTitle("Segaleo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	// set up the panel in the frame
	public void initializePanelToFrame() {
		new ColorResources();
		new TextResources().changeLanguage();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(375, 812));

		configureLabels();
		configureButtons();
		addListeners();
		addComponentsToPanel();

		this.setContentPane(panel);
		this.pack();
	}

	// makes the frame visible
	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	// label settings
	public void configureLabels() {
		backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0, 0, 375, 812);

		forgotPassword = LabelFactory.createLabel(TextResources.forgotPassword, Color.WHITE, FontFactory.poppins(30));
		forgotPassword.setBounds(41, 224, 241, 39);

		txtPass = LabelFactory.createLabel(TextResources.txtPass, Color.WHITE, FontFactory.poppins(16));
		txtPass.setBounds(41, 263, 304, 74);

		txtSendEmail = LabelFactory.createLabel(TextResources.txtSendEmail, Color.WHITE, FontFactory.poppins(16));
		txtSendEmail.setBounds(41, 300, 304, 74);
	}

	// buttons settings
	public void configureButtons() {

		remindMeBtn = ButtonFactory.createButtonIcon(remindMeImage);
		remindMeBtn.setBounds(22, 410, 331, 70);
		remindMeBtn.setPressedIcon(remindMeImage);

		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(12, 50, 64, 45);

		forgotPassword = LabelFactory.createLabel(TextResources.forgotPassword, Color.WHITE, FontFactory.poppins(30));
		forgotPassword.setBounds(41, 224, 241, 70);

		txtPass = LabelFactory.createLabel(TextResources.txtPass, Color.WHITE, FontFactory.poppins(16));
		txtPass.setBounds(41, 283, 304, 74);
		txtPass.setFont(FontFactory.boldavenir(20));

		txtSendEmail = LabelFactory.createLabel(TextResources.txtSendEmail, Color.WHITE, FontFactory.poppins(16));
		txtSendEmail.setBounds(41, 320, 304, 74);
		txtSendEmail.setFont(FontFactory.boldavenir(20));
	}

	// add all the components
	public void addComponentsToPanel() {
		panel.add(backBtn);
		panel.add(forgotPassword);
		panel.add(txtPass);
		panel.add(txtSendEmail);
		panel.add(remindMeBtn);

		panel.add(backgroundLabel);
	}

	// add listeners
	public void addListeners() {
		remindMeBtn.addActionListener(this);
		backBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String getEmail;
		String getRoom;
		String password = null;
		MessageSender sender = new MessageSender();

		if (e.getSource() == remindMeBtn) {
			// user needs to enter their email
			UIManager.put("OptionPane.informationIcon", emailIcon);
			getEmail = (String) JOptionPane.showInputDialog(null, "Enter your email", "Email",
					JOptionPane.INFORMATION_MESSAGE, emailIcon, null, "");

			// if they don't enter the email and click on Ok, ask for it again
			if (getEmail != (null) && getEmail.equals("")) {
				UIManager.put("OptionPane.informationIcon", emailIcon);
				JOptionPane.showMessageDialog(null, "Enter your email");
				getEmail = (String) JOptionPane.showInputDialog(null, "Enter your email", "Email",
						JOptionPane.INFORMATION_MESSAGE, emailIcon, null, "");
			}

			// if they enter their email, ask for their room number
			if (getEmail != null) {
				getRoom = (String) JOptionPane.showInputDialog(null, "Enter your room number:", "Room Number",
						JOptionPane.INFORMATION_MESSAGE, roomIcon, null, "");
				// if they don't enter their room number and click on Ok, ask for it again
				if (getRoom != null && getRoom.equals("")) {
					UIManager.put("OptionPane.informationIcon", roomIcon);
					JOptionPane.showMessageDialog(null, "Enter your room number");
					getRoom = (String) JOptionPane.showInputDialog(null, "Enter your room number:", "Room Number",
							JOptionPane.INFORMATION_MESSAGE, roomIcon, null, "");
				}
				else {
					// if they enter both successfully search for their password and send an email
					password = getUserPassword(getRoom);
					sender.sendEmail(getEmail, false, password);
				}
			// if they click on Cancel on both dialogs don't do anything
			if (getEmail == null || getRoom == null) {
				
			} 
			}
		}

		if (e.getSource() == backBtn) {
			this.dispose();
			new LoginWindow();
		}

		initializePanelToFrame();
	}

	public String getUserPassword(String getRoom) {
		int roomIndex;
		String password = null;
		try {
			@SuppressWarnings("static-access")
			ArrayList<Room> rooms = new RoomCustomerReader().getRoomsList();
			
			if (Integer.parseInt(getRoom) / 100 == 1)
				roomIndex = (Integer.parseInt(getRoom) % 100) - 1;
			else
				roomIndex = (Integer.parseInt(getRoom) / 100) * 10 + (Integer.parseInt(getRoom) % 100) - 1;
			
			password = rooms.get(roomIndex).getPassword();			
		}
		catch(NumberFormatException ex) {
			
		}
		return password;
	}
}
