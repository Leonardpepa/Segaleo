package gui.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.components.PopupPanel;
import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import gui.factory.TextFieldFactory;
import login.Login;
import resources.ColorResources;
import resources.TextResources;
import roomCustomer.RoomCustomerReader;

public class LoginWindow extends JFrame implements ActionListener, FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;

	private ImageIcon backgroundImage = new ImageIcon("Background Images/loginBackground.png");
	private JLabel backgroundLabel;

	private ImageIcon languageImage = new ImageIcon("buttonImages/Language Button.png");
	private JButton languageBtn;

	private ImageIcon contactImage = new ImageIcon("buttonImages/Contact Button.png");
	private JButton contactBtn;

	private JLabel welcomeLabel;
	private JLabel loginLabel;

	private JTextField roomField;
	private JTextField passwordField;

	private JButton loginBtn;

	private PopupPanel popupPanel;

	boolean isPopup = false;

	// constructor
	public LoginWindow() {
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);

	}

	// settings for the frame
	public void windowsConfiguration() {
		this.setTitle("Hotel PDA Sample");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	// set up the panel in the frame
	public void initializePanelToFrame() {

		new TextResources().changeLanguage();
		new ColorResources();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(375, 812));

		configureLabels();
		configureTextFields();
		configureButtons();
		configurePopupWindow();
		addListeners();
		addComponentsToPanel();

		this.setContentPane(panel);
		this.pack();
	}

	// makes the frame visible
	public void showWindow(JFrame frame, boolean show) {
		loginBtn.requestFocusInWindow();
		frame.setVisible(show);
	}

	// label settings
	public void configureLabels() {
		backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0, 0, 375, 812);

		welcomeLabel = LabelFactory.createLabel(TextResources.welcome, Color.WHITE, FontFactory.poppins(28));
		welcomeLabel.setBounds(42, 224, 294, 29);

		loginLabel = LabelFactory.createLabel(TextResources.loginto, Color.WHITE, FontFactory.poppins(22));
		loginLabel.setBounds(41, 263, 244, 31);
	}

	// textfileds setings
	public void configureTextFields() {
		roomField = TextFieldFactory.createTextField(TextResources.roomField, Color.WHITE, Color.LIGHT_GRAY,
				FontFactory.poppins(12));
		roomField.setBounds(22, 330, 331, 52);

		passwordField = TextFieldFactory.createTextField(TextResources.passField, Color.WHITE, Color.LIGHT_GRAY,
				FontFactory.poppins(12));
		passwordField.setBounds(22, 398, 331, 52);
	}

	// buttons settings
	public void configureButtons() {
		languageBtn = ButtonFactory.createButtonIcon(languageImage);
		languageBtn.setBounds(249, 723, 61, 61);

		contactBtn = ButtonFactory.createButtonIcon(contactImage);
		contactBtn.setBounds(309, 723, 61, 61);

		loginBtn = ButtonFactory.createButton(TextResources.loginBtn, FontFactory.poppins(20),
				ColorResources.bgLoginWindow, Color.WHITE);
		loginBtn.setBounds(22, 468, 331, 52);

	}

	// add all the components
	public void addComponentsToPanel() {
		panel.add(languageBtn);
		panel.add(contactBtn);
		panel.add(welcomeLabel);
		panel.add(loginLabel);
		panel.add(loginLabel);
		panel.add(roomField);
		panel.add(passwordField);
		panel.add(loginBtn);
		togglePanel();
		panel.add(backgroundLabel);
	}

	// add listeners
	public void addListeners() {
		languageBtn.addActionListener(this);
		loginBtn.addActionListener(this);
		contactBtn.addActionListener(this);
		popupPanel.greek.addActionListener(this);
		popupPanel.english.addActionListener(this);
		roomField.addFocusListener(this);
		passwordField.addFocusListener(this);
	}

	// add or remove the popup panel
	public void togglePanel() {
		if (isPopup) {
			panel.add(popupPanel);
		} else {
			panel.remove(popupPanel);
		}
	}

	// settings for the popup panel
	public void configurePopupWindow() {
		popupPanel = new PopupPanel();
		popupPanel.configurePopupWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == languageBtn) {
			isPopup = isPopup ? false : true;
		}

		if (e.getSource() == loginBtn) {
			// reads and saves the all customers with their rooms
			new RoomCustomerReader();

			// checks if the text field is empty and if the login data is correct
			if (!roomField.getText().equals("") && Login.checkLogin(roomField.getText(), passwordField.getText())) {
				this.dispose();
				new MainWindow();
			} else
				JOptionPane.showMessageDialog(popupPanel,
						"Wrong Password or Room.\n Please contact with the reception.", "Log In Error",
						JOptionPane.WARNING_MESSAGE);

		}
		
		if(e.getSource() == contactBtn) {
			this.dispose();
			new ContactWindow();
		}
		
		if(e.getSource() == popupPanel.greek) {
			TextResources.isEnglish = false; 


		if (e.getSource() == popupPanel.greek) {
			TextResources.isEnglish = false;

		}

		if (e.getSource() == popupPanel.english) {
			TextResources.isEnglish = true;
		}

		initializePanelToFrame();
	}
	}

	@Override
	public void focusGained(FocusEvent e) {

		if (e.getSource() == roomField)
			roomField.setText("");
		if (e.getSource() == passwordField)
			passwordField.setText("");

	}

	@Override
	public void focusLost(FocusEvent e) {
		// initializePanelToFrame();
	}

}
