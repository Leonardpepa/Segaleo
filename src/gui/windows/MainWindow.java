package gui.windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import reservation.ActivityReader;
import reservation.Reservation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.components.PopupPanel;
import gui.factory.BackgroundFactory;
import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LogoFactory;
import login.Login;
import order.Order;
import resources.ColorResources;
import resources.TextResources;

public class MainWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Order order = new Order();
	Reservation reservation= new Reservation();
	
	private JPanel panel;

	private JButton servicesBtn;
	private JButton activitiesBtn;

	private ImageIcon profilImage = new ImageIcon("buttonImages/Profile Button.png");
	private JButton profileBtn;

	private ImageIcon logoutBtnImage = new ImageIcon("buttonImages/Logout Button.png");
	private JButton logoutBtn;

	private ImageIcon languageImage = new ImageIcon("buttonImages/Language Button.png");
	private JButton languageBtn;

	private ImageIcon contactImage = new ImageIcon("buttonImages/Contact Button.png");
	private JButton contactBtn;

	private PopupPanel popupPanel;
	boolean isPopup = false;

	public MainWindow() {
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}

	public void windowsConfiguration() {
		this.setTitle("Segaleo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public void initializePanelToFrame() {

		new TextResources().changeLanguage();
		new ColorResources();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(375, 812));
		panel.setBackground(new Color(216, 223, 224));

		configurePopupWindow();

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
		panel.add(LogoFactory.addLogo());
		panel.add(BackgroundFactory.addBackgroundLight());
	}

	public void configureButtons() {

		servicesBtn = ButtonFactory.createButton(TextResources.roomService, FontFactory.avenir(18),
				ColorResources.bgMainWindowBtn, ColorResources.frMainWindowBtn);
		servicesBtn.setBounds(91, 353, 193, 48);

		activitiesBtn = ButtonFactory.createButton(TextResources.activities, FontFactory.avenir(18),
				ColorResources.bgMainWindowBtn, ColorResources.frMainWindowBtn);
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

	public void configurePopupWindow() {
		popupPanel = new PopupPanel();
		popupPanel.configurePopupWindow();
	}

	public void togglePanel() {
		if (isPopup) {
			panel.add(popupPanel);
		} else {
			panel.remove(popupPanel);
		}
	}

	public void addListeners() {
		languageBtn.addActionListener(this);
		logoutBtn.addActionListener(this);
		popupPanel.greek.addActionListener(this);
		popupPanel.english.addActionListener(this);
		profileBtn.addActionListener(this);
		servicesBtn.addActionListener(this);
		contactBtn.addActionListener(this);
		activitiesBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == languageBtn) {
			isPopup = isPopup ? false : true;
		}

		if (e.getSource() == popupPanel.greek) {
			TextResources.isEnglish = false;
		}

		if (e.getSource() == popupPanel.english) {
			TextResources.isEnglish = true;
		}

		if (e.getSource() == profileBtn) {
			this.dispose();
			new ProfileWindow();
		}
		if (e.getSource() == servicesBtn) {
			this.dispose();
			new MenuWindow(order);
		}
		if (e.getSource() == contactBtn) {
			this.dispose();
			new ContactWindow();
		}
		if (e.getSource() == activitiesBtn) {
			this.dispose();
			ActivityReader actReader = new ActivityReader();
			new ActivityWindow(actReader.getActivitiesList(),reservation);
		}
		if (e.getSource() == logoutBtn) {
			Login.logout();
			this.dispose();
			new LoginWindow();
		}

		initializePanelToFrame();
	}
}
