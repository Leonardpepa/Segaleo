package gui.windows;

import java.awt.*;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import org.w3c.dom.Text;

import contact.MessageSender;
import gui.factory.*;
import resources.ColorResources;
import resources.TextResources;
import roomCustomer.Customer;
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

		if (e.getSource() == remindMeBtn) {
			String password = null;
			MessageSender sender = new MessageSender();
			String roomInput = checkRoom();
			
			if (roomInput == null) {
				return;
			}
			
			String emailInput = checkEmail(roomInput);
			
			if (emailInput == null) {
				return;
			}
			/* Once both inputs have been confirmed, the email is sent */
			password = getUserPassword(roomInput);
			sender.sendEmail(emailInput, false, password);
			JOptionPane.showMessageDialog(null, TextResources.emailSent);
		}

		if (e.getSource() == backBtn) {
			this.dispose();
			new LoginWindow();
		}

		initializePanelToFrame();
	}

	/*
	 * User needs to enter their room number first. If the room number does not
	 * exist on the file, the user needs to add the room number again
	 */
	public String checkRoom() {
		String roomInput = "";
		try {
			do {
				roomInput = (String) JOptionPane.showInputDialog(null, TextResources.enterRoom, "Room Number",
						JOptionPane.INFORMATION_MESSAGE, roomIcon, null, "");
				if (roomInput == null) {
					return null;
				}
			} while (roomInput.equals("") || !findUserRoomNumber(roomInput));
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, TextResources.wrongInput);
			return null;
		}
		return roomInput;
	}

	/*
	 * User needs to enter their email address in order to receive the password.
	 * Since the room number has been confirmed already, we need to check if the
	 * input matches the email of the account on file
	 */

	public String checkEmail(String roomInput) {
		String emailInput = "";
		try {
			do {
				UIManager.put("OptionPane.informationIcon", emailIcon);
				emailInput = (String) JOptionPane.showInputDialog(null, TextResources.enterEmail, "Email",
						JOptionPane.INFORMATION_MESSAGE, emailIcon, null, "");
				if (emailInput == null) {
					return null;
				}
			} while (emailInput.equals("") || !emailInput.equals(getUserEmail(roomInput)));
			
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, TextResources.wrongInput);
			return null;
		}
		return emailInput;
	}

	public boolean findUserRoomNumber(String getRoom) {

		ArrayList<Room> rooms = RoomCustomerReader.getRoomsList();
		for (Room room : rooms) {
			if (Integer.parseInt(getRoom) == room.getNumber()) {
				return true;
			}
		}
		return false;
	}

	public String getUserPassword(String getRoom) {
		int roomIndex = getUserIndex(getRoom);
		String password = null;
		ArrayList<Room> rooms = RoomCustomerReader.getRoomsList();
		password = rooms.get(roomIndex).getPassword();

		return password;
	}

	public String getUserEmail(String getRoom) {
		String email = null;
		int roomIndex = getUserIndex(getRoom);

		ArrayList<Customer> customers = RoomCustomerReader.getCustomersList();
		email = customers.get(roomIndex).getEmail();
		return email;
	}

	public int getUserIndex(String getRoom) {
		int roomIndex;

		if (Integer.parseInt(getRoom) / 100 == 1) {
			roomIndex = (Integer.parseInt(getRoom) % 100) - 1;
		} else {
			roomIndex = (Integer.parseInt(getRoom) / 100) * 10 + (Integer.parseInt(getRoom) % 100) - 1;
		}

		return roomIndex;
	}
}
