package gui.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

import resources.TextResources;

public class MyOrdersWindow extends JFrame{

	private JPanel panel;
	private ImageIcon backgroundImage = new ImageIcon("Background Images/background.png");
	private JLabel backgroundLabel;
	
	private ImageIcon logo = new ImageIcon("logo/logo-scaled.png");
	private JLabel logoLabel;
	
	private ImageIcon backImage = new ImageIcon("buttonImages/Back ButtonGR.png");
	private JButton backBtn;
	
	public MyOrdersWindow() {
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}

	public void windowsConfiguration() {
		this.setTitle("My Orders");
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
	//	configureButtons();
	//	configureLabels();
	//	configureTextArea();
		addComponentsToPanel();
	//	addListeners();

		this.setContentPane(panel);
		this.pack();
	}
	
	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}
	
	public void addComponentsToPanel() {
		//panel.add(backBtn);
		panel.add(logoLabel);
		
		panel.add(backgroundLabel);

	}
	
	public void configureLogo() {
		logoLabel = new JLabel(logo);
		logoLabel.setBounds(101, 50, 173, 173);
	}
	
	public void configureBackground() {
		backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0, 0, 375, 812);
	}
	
}
