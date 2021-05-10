package gui.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

import gui.factory.BackgroundFactory;
import gui.factory.LogoFactory;
import resources.TextResources;

public class MyOrdersWindow extends JFrame{

	private JPanel panel;
	private ImageIcon backgroundImage = new ImageIcon("Background Images/background.png");
	private JLabel backgroundLabel;
	
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
		panel.add(LogoFactory.addLogoScaled());
		
		panel.add(BackgroundFactory.addBackgroundLight());

	}
	
	
	
	
	
}
