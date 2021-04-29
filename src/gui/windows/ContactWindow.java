package gui.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.components.RoundedPanel;
import gui.factory.ButtonFactory;
import resources.TextResources;

public class ContactWindow extends JFrame implements ActionListener {

	private JPanel panel;
	
	private ImageIcon backgroundImage = new ImageIcon("Background Images/background.png");
	private JLabel backgroundLabel;
	
	private ImageIcon logo = new ImageIcon(
			new ImageIcon("logo/logo.png").getImage().getScaledInstance(140, 140, Image.SCALE_DEFAULT));
	private JLabel logoLabel;

	ImageIcon backImage = new ImageIcon("buttonImages/Back Button.png");
	JButton backBtn;

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
		panel.add(backgroundLabel);

	}

	public void configureButtons() {
		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(12, 40, 67, 21);

	}

	public void configureLogo() {
		logoLabel = new JLabel(logo);
		logoLabel.setBounds(101, 50, 173, 173);
	}
	
	public void configureBackground() {
		backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0,0,375, 812);
	}

	public void resizeLogo() {
		logoLabel.setIcon(logo);
	}

	public void addListeners() {
		backBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			this.dispose();
			new MainWindow();
		}

	}

}
