package gui.windows;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import gui.components.RoundedPanel;
import gui.factory.ButtonFactory;
import resources.TextResources;

public class ProfileWindow extends JFrame implements ActionListener{

	
	private JPanel panel;
	private ImageIcon logo = new ImageIcon("logo/logo.png");
	private JLabel logoLabel;
	private JPanel insidePanel;
	
	
	ImageIcon backImage = new ImageIcon("buttonImages/Back Button.png");
	JButton backBtn;
	ImageIcon orderImage = new ImageIcon("buttonImages/Order Button.png");
	JButton orderBtn;
	ImageIcon reservationImage = new ImageIcon("buttonImages/Reservation Button.png");
	JButton reservationBtn;
	ImageIcon couponImage = new ImageIcon("buttonImages/Coupon Button.png");
	JButton couponBtn;
	
	
	public ProfileWindow() {
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}
	
	public void windowsConfiguration() {
		this.setTitle("Hotel PDA Sample");
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
		configureButtons();
		addComponentsToPanel();
		//addListeners();
		
		this.setContentPane(panel);
		this.pack();
	}
	
	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}
	
	public void addComponentsToPanel() {
		panel.add(backBtn);
		panel.add(logoLabel);
		
		//initial insidePanel and add it to panel
		insidePanel = new RoundedPanel(50,new Color(255, 255, 255));
		insidePanel.setOpaque(false);
		insidePanel.setBorder(new EmptyBorder(50, 20, 380, 20));
		insidePanel.setLayout(new BoxLayout(insidePanel, BoxLayout.Y_AXIS));
		insidePanel.setBounds(11, 280, 351, 550);
		
		
		//add components to insidePanel
		insidePanel.add(orderBtn);
		insidePanel.add(Box.createVerticalStrut(20));
		insidePanel.add(new JSeparator());
		insidePanel.add(Box.createVerticalStrut(20));
		insidePanel.add(reservationBtn);
		insidePanel.add(Box.createVerticalStrut(20));
		insidePanel.add(new JSeparator());
		insidePanel.add(Box.createVerticalStrut(20));
		insidePanel.add(couponBtn);
		
		
		panel.add(insidePanel);
		
	}
	
	public void configureButtons() {
		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(12, 40, 67, 21);
		
		orderBtn = ButtonFactory.createButtonIcon(orderImage);
		reservationBtn = ButtonFactory.createButtonIcon(reservationImage);
		couponBtn = ButtonFactory.createButtonIcon(couponImage);
	
	}
	public void configureLogo() {
		logoLabel = new JLabel(logo);
		logoLabel.setBounds(101, 50, 173, 173);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
