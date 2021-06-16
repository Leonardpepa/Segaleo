package gui.windows;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.components.PopupPanel;
import gui.factory.BackgroundFactory;
import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LogoFactory;
import login.Login;
import order.Order;
import platformData.PlatformData;
import reservation.Reservation;
import resources.ColorResources;
import resources.TextResources;

public class MainWindow extends JFrame {
	
	/*
	 * 	this class holds the content that the logged user sees first and 
	 * 	also instantiates the order and reservation the user will make
	 */
	
	private static final long serialVersionUID = 6013067933817440251L;
	
	private Order order = new Order();
	private Reservation reservation= new Reservation();
	
	private JPanel panel;

	private JButton servicesBtn;
	private JButton activitiesBtn;

	private ImageIcon profilImage = new ImageIcon("buttonImages/Profile Button.png");
	private JButton profileBtn;

	private ImageIcon logoutBtnImage = new ImageIcon("buttonImages/Logout Button.png");
	private JButton logoutBtn;

	private ImageIcon contactImage = new ImageIcon("buttonImages/Contact Button.png");
	private JButton contactBtn;

	private PopupPanel popupPanel;
	//if true the the togglePopupPanel will be added to the main panel
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
		//changes the language if needed
		new TextResources().changeLanguage();
		//initialize the colors
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
		togglePanel();
		panel.add(LogoFactory.addLogo());
		panel.add(BackgroundFactory.addBackgroundLight());
	}

	//settings for the buttons
	public void configureButtons() {

		servicesBtn = ButtonFactory.createButton(TextResources.roomService, FontFactory.arial(18),
				ColorResources.bgMainWindowBtn, ColorResources.frMainWindowBtn);
		servicesBtn.setBounds(91, 353, 193, 48);

		activitiesBtn = ButtonFactory.createButton(TextResources.activities, FontFactory.arial(18),
				ColorResources.bgMainWindowBtn, ColorResources.frMainWindowBtn);
		activitiesBtn.setBounds(91, 426, 193, 48);

		profileBtn = ButtonFactory.createButtonIcon(profilImage);
		profileBtn.setBounds(309, 49, 61, 61);

		logoutBtn = ButtonFactory.createButtonIcon(logoutBtnImage);
		logoutBtn.setBounds(10, 49, 61, 61);

		contactBtn = ButtonFactory.createButtonIcon(contactImage);
		contactBtn.setBounds(309, 723, 61, 61);

	}

	public void configurePopupWindow() {
		popupPanel = new PopupPanel();
		popupPanel.configurePopupWindow();
	}
	
	//adds or removes the pop up panel  form the container panel
	public void togglePanel() {
		if (isPopup) {
			panel.add(popupPanel);
		} else {
			panel.remove(popupPanel);
		}
	}

	//each button has its own listener
	public void addListeners() {
		
		//before the user logs out it saves the state
		logoutBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				PlatformData.saveData();
				Login.logout();
				dispose();
				new LoginWindow();
			}
		});
	
		//navigate to the profile
		profileBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ProfileWindow();
			}
		});
		
		//navigate to the menu
		servicesBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MenuWindow(order);
			}
			
		});
		//navigate to contact window
		contactBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ContactWindow();
			}
		});
		
		//navigate to activity window
		activitiesBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ActivityWindow(reservation);
			}
		});
		
		//if the app its closed it will save the state
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				PlatformData.saveData();
			    System.exit(0);
			}
		});
	}
	
}
