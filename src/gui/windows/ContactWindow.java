package gui.windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import contact.Contact;
import gui.factory.*;
import resources.*;

public class ContactWindow extends JFrame implements ActionListener {

	private JPanel panel;
	private Contact contact = new Contact();

	private ImageIcon backgroundImage = new ImageIcon("Background Images/background.png");
	private JLabel backgroundLabel;

	private ImageIcon logo = new ImageIcon("logo/logo-scaled.png");
	private JLabel logoLabel;

	private String path = "buttonImages/Back Button";
	private String lang = TextResources.imageLang;
	private ImageIcon backImage = new ImageIcon(path + lang);
	private JButton backBtn;

	private JLabel contactLabel = new JLabel();
	private JLabel faqLabel = new JLabel();

	private ImageIcon phoneIcon = new ImageIcon("buttonImages/phone button.png");
	private JButton phoneBtn;

	private ImageIcon facebookIcon = new ImageIcon("buttonImages/facebook button.png");
	private JButton facebookBtn;

	private ImageIcon instagramIcon = new ImageIcon("buttonImages/instagram button.png");
	private JButton instagramBtn;

	private ImageIcon messageIcon = new ImageIcon("buttonImages/message button.png");
	private JLabel messageLabel;

	private JTextArea messageArea = new JTextArea();
	private JButton sendBtn;

	private ImageIcon phonecallIcon = new ImageIcon("Icons/phone-icon.png");

	private JLabel question1;
	private JTextArea answer1;
	private JLabel question2;
	private JTextArea answer2;

	private JButton seeMoreBtn;

	public ContactWindow() {
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}

	public void windowsConfiguration() {
		this.setTitle("Segaleo Contact Page");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public void initializePanelToFrame() {

		new TextResources().changeLanguage();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(375, 812));
		panel.setBackground(new Color(216, 223, 224));

		configureLogo();
		configureBackground();
		configureButtons();
		configureLabels();
		configureTextArea();
		addComponentsToPanel();
		addListeners();

		this.setContentPane(panel);
		this.pack();
	}

	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	public void addComponentsToPanel() {

		panel.add(backBtn);
		panel.add(logoLabel);
		panel.add(contactLabel);
		panel.add(phoneBtn);
		panel.add(facebookBtn);
		panel.add(instagramBtn);
		panel.add(messageLabel);
		panel.add(messageArea);
		panel.add(faqLabel);
		panel.add(sendBtn);
		panel.add(question1);
		panel.add(answer1);
		panel.add(question2);
		panel.add(answer2);
		panel.add(seeMoreBtn);

		panel.add(backgroundLabel);

	}

	//CONFIGURING ALL THE COMPONENTS ADDED TO THE PANEL BY CATEGORY 
	
	public void configureTextArea() {
		messageArea = TextAreaFactory.createTextArea(TextResources.msgArea, Color.WHITE,
				ColorResources.bgLoginWindow, FontFactory.poppins(12));
		messageArea.setBounds(76, 315, 193, 89);

		messageArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				messageArea.setText("");
			}
		});
	}

	public void configureButtons() {
		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(12, 40, 67, 21);

		phoneBtn = ButtonFactory.createButtonIcon(phoneIcon);
		phoneBtn.setBounds(20, 250, 61, 61);

		facebookBtn = ButtonFactory.createButtonIcon(facebookIcon);
		facebookBtn.setBounds(80, 250, 61, 61);

		instagramBtn = ButtonFactory.createButtonIcon(instagramIcon);
		instagramBtn.setBounds(140, 250, 61, 61);

		sendBtn = ButtonFactory.createButton(TextResources.sendbtn, FontFactory.poppins(14), ColorResources.sendColor,
				ColorResources.bgLoginWindow);
		sendBtn.setBounds(281, 379, 70, 23);

		seeMoreBtn = ButtonFactory.createButton(TextResources.seeMore, FontFactory.poppins(14),
				ColorResources.sendColor, ColorResources.bgLoginWindow);
		seeMoreBtn.setBounds(200, 750, 150, 27);
	}

	public void configureLabels() {
		contactLabel = LabelFactory.createLabelBG(TextResources.contactLabel, ColorResources.bgLoginWindow, Color.WHITE,
				FontFactory.poppins(16));
		contactLabel = LabelFactory.alignLabel(contactLabel, SwingConstants.CENTER, SwingConstants.CENTER);
		contactLabel.setBounds(20, 225, 118, 26);

		messageLabel = LabelFactory.createIconLabel(messageIcon);
		messageLabel.setBounds(20, 311, 61, 61);

		faqLabel = LabelFactory.createLabelBG("FAQ", ColorResources.bgLoginWindow, Color.WHITE,
				FontFactory.poppins(16));
		faqLabel = LabelFactory.alignLabel(faqLabel, SwingConstants.CENTER, SwingConstants.CENTER);
		faqLabel.setBounds(20, 425, 118, 26);

		//picking the 1st and 2nd questions and answers from the FAQ list to show
		question1 = LabelFactory.createLabel(contact.getFaqs().get(0).getQuestion(), Color.black,
				FontFactory.poppins(13));
		question1.setBounds(22, 476, 308, 20);

		answer1 = TextAreaFactory.createTextArea(contact.getFaqs().get(0).getAnswer(), Color.white,
				ColorResources.bgLoginWindow, FontFactory.poppins(13));
		answer1.setBounds(22, 493, 331, 100);
		answer1.setEditable(false);

		question2 = LabelFactory.createLabel(contact.getFaqs().get(1).getQuestion(), Color.black,
				FontFactory.poppins(12));
		question2.setBounds(22, 600, 308, 20);

		answer2 = TextAreaFactory.createTextArea(contact.getFaqs().get(1).getAnswer(), Color.white,
				ColorResources.bgLoginWindow, FontFactory.poppins(13));
		answer2.setBounds(22, 620, 331, 105);
		answer2.setEditable(false);
		answer2.setWrapStyleWord(true);
		answer2.setLineWrap(true);

	}

	public void configureLogo() {
		logoLabel = new JLabel(logo);
		logoLabel.setBounds(101, 50, 173, 173);
	}

	public void configureBackground() {
		backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0, 0, 375, 812);
	}

	public void addListeners() {
		backBtn.addActionListener(this);
		facebookBtn.addActionListener(this);
		instagramBtn.addActionListener(this);
		phoneBtn.addActionListener(this);
		sendBtn.addActionListener(this);
		seeMoreBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			this.dispose();
			new MainWindow();
		}
		if (e.getSource() == facebookBtn) {
			contact.getSocial().openURL("facebook");
		}
		if (e.getSource() == instagramBtn) {
			contact.getSocial().openURL("instagram");
		}
		//Opens custom dialog for phone call
		if (e.getSource() == phoneBtn) {
			Object[] options = { "Call" };
			int n = JOptionPane.showOptionDialog(null, "+30 6978265917", "Phone Call", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, phonecallIcon, 
					options, // the titles of buttons
					options[0]); // default button title
		}
		if(e.getSource() == seeMoreBtn) {
			this.dispose();
			new FaqWindow();
		}

	}

}
