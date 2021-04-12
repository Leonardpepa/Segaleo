package gui.windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import gui.factory.*;
import resources.TextResources;
import resources.*;

public class MainWindow  extends JFrame implements ActionListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel; 
	private ImageIcon logo = new ImageIcon("logo/logo.png");
	private JLabel logoLabel;
	private JButton servicesBtn;
	private JButton activitiesBtn;
	
	ImageIcon profilImage = new ImageIcon("buttonImages/Profile Button.png");
	JButton profileBtn;
	
	ImageIcon logoutBtnImage = new ImageIcon("buttonImages/Logout Button.png");
	JButton logoutBtn;
	
	ImageIcon languageImage = new ImageIcon("buttonImages/Language Button.png");
	JButton languageBtn;
	
	ImageIcon contactImage = new ImageIcon("buttonImages/Contact Button.png");
	JButton contactBtn;
	
	PopupPanel popupPanel;
	boolean isPopup = false;
	
	public MainWindow() {
		intilizePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}
	
	
	public void windowsConfiguration() {
		this.setIconImage(logo.getImage());
		this.setTitle("Hotel PDA Sample");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	
	public void intilizePanelToFrame() {
		
		new TextResources().changeLanguage();
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(375, 812));
		panel.setBackground(new Color(216, 223, 224));
		
		configurePopupWindow();
		configureLogo();
		configureButtons();
		addComponentsToPanel();
		addListeners();
		
		this.setContentPane(panel);
		this.pack();
	}
	
	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	public void addComponentsToPanel() {
		panel.add(servicesBtn);
		panel.add(activitiesBtn);
		panel.add(logoutBtn);
		panel.add(profileBtn);
		panel.add(contactBtn);
		panel.add(languageBtn);
		togglePanel();
		panel.add(logoLabel);
	}
	
	public void configureButtons() {

		servicesBtn = ButtonFactory.createButton(TextResources.roomService, FontFactory.avenir(20), new Color(205, 221, 223), new Color(32, 150, 171));
		servicesBtn.setBounds(91, 353, 193, 48);
		
		activitiesBtn = ButtonFactory.createButton(TextResources.activities, FontFactory.avenir(20),  new Color(205, 221, 223), new Color(32, 150, 171));
		activitiesBtn.setBounds(91, 426, 193, 48);
		
		profileBtn = ButtonFactory.createButtonIcon(profilImage);
		profileBtn.setBounds(309, 49, 61, 61);

		logoutBtn = ButtonFactory.createButtonIcon(logoutBtnImage);
		logoutBtn.setBounds(10, 49, 61, 61);
		
		languageBtn = ButtonFactory.createButtonIcon(languageImage);
		languageBtn.setBounds(249, 723, 61, 61);
		
		contactBtn = ButtonFactory.createButtonIcon(contactImage);
		contactBtn.setBounds(309, 723, 61, 61);
		
	}
	
	public void configureLogo() {
		logoLabel = new JLabel(logo);
		logoLabel.setBounds(101, 118, 173, 173);
	}
	
	public void configurePopupWindow() {
		popupPanel =  new PopupPanel();
		popupPanel.configurePopupWindow();
	}
	
	public void togglePanel(){
		if(isPopup) {
			panel.add(popupPanel);
		}
		else {
			panel.remove(popupPanel);
		}
	}
	public void addListeners() {
		languageBtn.addActionListener(this);
		logoutBtn.addActionListener(this);
		popupPanel.greek.addActionListener(this);
		popupPanel.english.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logoutBtn) {
			this.dispose();
			new LoginWindow();			
		}
		
		if(e.getSource() == languageBtn) {
			isPopup = isPopup ? false : true;
		}
		
		if(e.getSource() == popupPanel.greek) {
			TextResources.isEnglish = false;
		}
		
		if(e.getSource() == popupPanel.english) {
			TextResources.isEnglish = true;
		}
		intilizePanelToFrame();
	}
}
