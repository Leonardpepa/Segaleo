package gui.windows;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.components.RoundedPanel;
import gui.factory.BackgroundFactory;
import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import gui.factory.LogoFactory;
import login.Login;
import resources.ColorResources;
import resources.TextResources;
import roomCustomer.Customer;

/*
 * This class is used to create the Profile page.
 * 
 * There the user will find a list with 3 buttons
 * 1. My Orders
 * 2. My Reservations
 * 3. My Coupons
 */
public class ProfileWindow extends JFrame {

	
	private static final long serialVersionUID = -7754183720884619469L;

	private JPanel panel;

	private JPanel insidePanel;

	private ImageIcon contactImage = new ImageIcon("buttonImages/Contact Button.png");
	private JButton contactBtn;

	private ImageIcon backImage = new ImageIcon("buttonImages/Back Button" + TextResources.imageLang);
	private JButton backBtn;
	private ImageIcon orderImage = new ImageIcon("buttonImages/Order Button" + TextResources.imageLang);
	private JButton orderBtn;
	private ImageIcon reservationImage = new ImageIcon("buttonImages/Reservation Button" + TextResources.imageLang);
	private JButton reservationBtn;
	private ImageIcon couponImage = new ImageIcon("buttonImages/Coupon Button" + TextResources.imageLang);
	private JButton couponBtn;
	
	private Customer customer = Login.loggedCustomer;
	private JLabel welcomeLabel;
	private ImageIcon profile = new ImageIcon("Icons/profile.png");
	private JLabel profileLabel;

	public ProfileWindow() {
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
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(375, 812));
		panel.setBackground(new Color(216, 223, 224));

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
		panel.add(LogoFactory.addLogoScaled());
		panel.add(contactBtn);
		panel.add(profileLabel);
		panel.add(welcomeLabel);

		// initial insidePanel and add it to panel
		insidePanel = new RoundedPanel(50, new Color(255, 255, 255));
		insidePanel.setOpaque(false);
		insidePanel.setBorder(new EmptyBorder(50, 20, 380, 20));
		insidePanel.setLayout(new BoxLayout(insidePanel, BoxLayout.Y_AXIS));
		insidePanel.setBounds(11, 280, 351, 300);

		// add components to insidePanel
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
		panel.add(BackgroundFactory.addBackgroundLight());

	}

	public void configureButtons() {
		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(12, 45, 68, 45);

		orderBtn = ButtonFactory.createButtonIcon(orderImage);
		reservationBtn = ButtonFactory.createButtonIcon(reservationImage);
		couponBtn = ButtonFactory.createButtonIcon(couponImage);

		contactBtn = ButtonFactory.createButtonIcon(contactImage);
		contactBtn.setBounds(309, 723, 61, 61);

	}
	
	public void configureLabels() {
		profileLabel = LabelFactory.createIconLabel(profile);
		profileLabel.setBounds(20, 245, 24, 24);
		
		welcomeLabel = LabelFactory.createLabel(TextResources.welcomeProfile + customer.getName() + "!", ColorResources.bgLoginWindow, FontFactory.poppins(20));
		welcomeLabel.setBounds(52, 130, 300, 250);
	}

	public void addListeners() {
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainWindow();
			}
		});

		contactBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ContactWindow();
			}
		});

		orderBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MyOrderReservationWindow(true);
			}
		});

		reservationBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MyOrderReservationWindow(false);
			}
		});

		couponBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MyCouponsWindow();

			}
		});

	}

}
