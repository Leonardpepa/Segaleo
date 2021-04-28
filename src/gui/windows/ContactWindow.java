package gui.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import contact.Contact;
import gui.components.RoundedPanel;
import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import resources.ColorResources;
import resources.TextResources;

public class ContactWindow extends JFrame implements ActionListener {

	private JPanel panel;
	private Contact contact = new Contact();
	
	private ImageIcon backgroundImage = new ImageIcon("Background Images/background.png");
	private JLabel backgroundLabel;
	
	private ImageIcon logo = new ImageIcon("logo/logo-scaled.png");
	private JLabel logoLabel;

	private ImageIcon backImage = new ImageIcon("buttonImages/Back Button.png");
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
	private JButton messageBtn;
	

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
		panel.add(messageBtn);
		panel.add(faqLabel);
		panel.add(backgroundLabel);
	

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
		
		messageBtn = ButtonFactory.createButtonIcon(messageIcon);
		messageBtn.setBounds(20, 311, 61, 61);
	}
	
	public void configureLabels() {
		contactLabel = LabelFactory.createLabelBG("CONTACT US", ColorResources.bgLoginWindow, Color.WHITE ,FontFactory.poppins(16));
		contactLabel = LabelFactory.alignLabel(contactLabel, SwingConstants.CENTER, SwingConstants.CENTER);
		contactLabel.setBounds(20, 225, 118, 26);
		
		faqLabel = LabelFactory.createLabelBG("FAQ", ColorResources.bgLoginWindow, Color.WHITE ,FontFactory.poppins(16));
		faqLabel = LabelFactory.alignLabel(faqLabel, SwingConstants.CENTER, SwingConstants.CENTER);
		faqLabel.setBounds(20, 425, 118, 26);
	}

	public void configureLogo() {
		logoLabel = new JLabel(logo);
		logoLabel.setBounds(101, 50, 173, 173);
	}
	
	public void configureBackground() {
		backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0,0,375, 812);
	}

	public void addListeners() {
		backBtn.addActionListener(this);
		facebookBtn.addActionListener(this);
		instagramBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			this.dispose();
			new MainWindow();
		}
		if(e.getSource() == facebookBtn) {
			contact.getSocial().openURL("facebook");
		}
		if(e.getSource() == instagramBtn) {
			contact.getSocial().openURL("instagram");
		}
		if(e.getSource() == phoneBtn) {
			//todo
		}

	}

}
