package gui.windows;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import gui.components.*;
import gui.factory.*;
import login.Login;
import menu.Menu;
import order.Food;
import platformData.PlatformData;
import reservation.Activity;
import reservation.ActivityReader;
import resources.ColorResources;
import resources.TextResources;
import roomCustomer.RoomCustomerReader;

public class LoginWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;

	private ImageIcon languageImage = new ImageIcon("buttonImages/Language Button.png");
	private JButton languageBtn;

	private ImageIcon contactImage = new ImageIcon("buttonImages/Contact Button.png");
	private JButton contactBtn;

	private JLabel welcomeLabel;
	private JLabel loginLabel;

	private JTextField roomField;
	private JPasswordField passwordField;
	Border border = BorderFactory.createLineBorder(Color.white, 15);

	private JButton loginBtn;

	private ImageIcon myPassImage = new ImageIcon("buttonImages/MyPassImage.png");
	private ImageIcon myPassImageGR = new ImageIcon("buttonImages/MyPassImageGR.png");
	private JButton myPassBtn;

	private PopupPanel popupPanel;

	boolean isPopup = false;
	public static ArrayList<Food> deals;
	

	// constructor
	public LoginWindow() {
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
	@SuppressWarnings("static-access")
	public void initializePanelToFrame() {
		
		new TextResources().changeLanguage();
		new ColorResources();
		deals = new Menu().GetDeals();
		Activity.initialarray();
		new ActivityReader();
		
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
		this.getRootPane().setDefaultButton(loginBtn);

	}

	// makes the frame visible
	public void showWindow(JFrame frame, boolean show) {
		loginBtn.requestFocusInWindow();
		frame.setVisible(show);
	}

	// label settings
	public void configureLabels() {

		welcomeLabel = LabelFactory.createLabel(TextResources.welcome, Color.WHITE, FontFactory.poppins(24));
		welcomeLabel.setBounds(38, 224, 294, 70);

		loginLabel = LabelFactory.createLabel(TextResources.loginto, Color.WHITE, FontFactory.poppins(18));
		loginLabel.setBounds(38, 275, 300, 70);
	}

	// textfileds setings
	public void configureTextFields() {
		roomField = TextFieldFactory.createTextField(TextResources.roomField, Color.WHITE, Color.LIGHT_GRAY,
				FontFactory.poppins(12));
		roomField.setBounds(22, 365, 330, 52);
		roomField.setBorder(border);

		passwordField = TextFieldFactory.createPasswordTextField(TextResources.passField, Color.WHITE, Color.LIGHT_GRAY,
				FontFactory.poppins(12));
		passwordField.setEchoChar((char) 0);
		passwordField.setBounds(22, 433, 330, 52);
		passwordField.setBorder(border);
	}

	// buttons settings
	public void configureButtons() {
		languageBtn = ButtonFactory.createButtonIcon(languageImage);
		languageBtn.setBounds(249, 723, 61, 61);

		contactBtn = ButtonFactory.createButtonIcon(contactImage);
		contactBtn.setBounds(309, 723, 61, 61);

		loginBtn = ButtonFactory.createButton(TextResources.loginBtn, FontFactory.poppins(20),
				ColorResources.bgLoginWindow, Color.WHITE);

		loginBtn.setBounds(22, 503, 331, 52);

		if (TextResources.isEnglish) {
			myPassBtn = ButtonFactory.createButtonIcon(myPassImage);
			myPassBtn.setBounds(132, 574, 112, 19);
		} else {
			myPassBtn = ButtonFactory.createButtonIcon(myPassImageGR);
			myPassBtn.setBounds(113, 574, 150, 19);
		}

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

		panel.add(myPassBtn);
		togglePanel();
		panel.add(BackgroundFactory.addBackgroundDark());
	}

	// each button has its own listener
	public void addListeners() {
		languageBtn.addActionListener(new languageListener());
		loginBtn.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// reads and saves the all customers with their rooms

				if (PlatformData.checkFile()) {
					PlatformData.loadData();
				} else {
					new RoomCustomerReader();
				}
				// checks if the text field is empty and if the login data is correct
				if (Login.checkLogin(roomField.getText(), passwordField.getText())) {
					dispose();
					new MainWindow();
				}
			}
		});

		contactBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ContactWindow();
			}
		});
		popupPanel.greek.addActionListener(new languageListener());
		popupPanel.english.addActionListener(new languageListener());

		myPassBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ForgotPasswordWindow();
			}
		});

		roomField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				roomField.setText("");
				if (e.getSource() == passwordField) {
					passwordField.setText("");
					passwordField.setEchoChar('*');
				}

			}
		});

		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				passwordField.setText("");
				passwordField.setEchoChar('*');
			}

		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				PlatformData.saveData();
			    System.exit(0);
			}
		});

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

	class languageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == languageBtn) {
				isPopup = isPopup ? false : true;
				initializePanelToFrame();
			}

			if (e.getSource() == popupPanel.greek) {
				TextResources.isEnglish = false;
			}

			if (e.getSource() == popupPanel.english) {
				TextResources.isEnglish = true;
			}
			initializePanelToFrame();
		}

	}

}
