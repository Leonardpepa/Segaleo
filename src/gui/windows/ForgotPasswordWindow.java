package gui.windows;
import java.awt.*;

import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;

import gui.factory.*;
import resources.ColorResources;
import resources.TextResources;


public class ForgotPasswordWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	
	private String lang = TextResources.imageLang;
	
	private String path = "buttonImages/Back Button";
	private ImageIcon backImage = new ImageIcon(path + lang);
	private JButton backBtn;
	
	private String path2 = "buttonImages/RemindMe";
	private ImageIcon remindMeImage = new ImageIcon(path2 + lang);
	private JButton remindMeBtn;
	
	private ImageIcon backgroundImage = new ImageIcon("Background Images/loginBackground.png");
	private JLabel backgroundLabel;
	
	private JLabel forgotPassword;
	private JLabel txtPass;
	private JLabel txtSendEmail;

	
	//constructor
	public ForgotPasswordWindow() {
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this,true);
	}
	
	//settings for the frame
	public void windowsConfiguration() {
		this.setTitle("Segaleo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	//set up the panel in the frame
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

	//makes the frame visible
	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}
	
	//label settings
	public void configureLabels() {
		backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0,0,375, 812);
		
		
		forgotPassword = LabelFactory.createLabel(TextResources.forgotPassword, Color.WHITE, FontFactory.poppins(30));
		forgotPassword.setBounds(41, 224, 241, 70);

		
		txtPass = LabelFactory.createLabel(TextResources.txtPass, Color.WHITE, FontFactory.poppins(16));
		txtPass.setBounds(41, 283, 304, 74);
		txtPass.setFont(FontFactory.boldavenir(20));
		
		txtSendEmail = LabelFactory.createLabel(TextResources.txtSendEmail, Color.WHITE, FontFactory.poppins(16));
		txtSendEmail.setBounds(41, 320, 304, 74);
		txtSendEmail.setFont(FontFactory.boldavenir(20));
	}
	
	//buttons settings
	public void configureButtons(){
		remindMeBtn = ButtonFactory.createButtonIcon(remindMeImage);
		remindMeBtn.setBounds(22, 400, 331, 52);
		remindMeBtn.setPressedIcon(remindMeImage);
		
		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(12, 50, 64, 45);
	}
	
	//add all the components
	public void addComponentsToPanel() {
		panel.add(backBtn);
		panel.add(forgotPassword);
		panel.add(txtPass);
		panel.add(txtSendEmail);
		panel.add(remindMeBtn);
		
		panel.add(backgroundLabel);
	}
	
	//add listeners
	public void addListeners() {
		remindMeBtn.addActionListener(this);
		backBtn.addActionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == remindMeBtn) {
			JOptionPane.showMessageDialog(panel,"We send it!");
		}
		if(e.getSource() == backBtn) {
			this.dispose();
			new LoginWindow();
		}
		
		initializePanelToFrame();
	}

}
