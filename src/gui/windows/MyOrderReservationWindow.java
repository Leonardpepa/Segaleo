package gui.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.components.RoundedPanel;
import gui.factory.BackgroundFactory;
import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import gui.factory.LogoFactory;
import login.Login;
import order.Coupon;
import resources.ColorResources;
import resources.TextResources;

public class MyOrderReservationWindow extends JFrame implements ActionListener {

	private JPanel panel;
	private JPanel insidePanel;

	private String path = "buttonImages/Back Button";
	private String lang = TextResources.imageLang;
	private ImageIcon backImage = new ImageIcon(path + lang);
	private JButton backBtn;

	// αν δεν υπάρχει παραγγελια τοτε θα εμφανιζει αναλογο μηνυμα
	private JLabel orderNumber;
	private JLabel orderPrice;
	private JLabel roomNum;
	private JLabel orderDate;
	private JLabel paymentMethod;
	private JButton rating;



	public MyOrderReservationWindow(boolean isOrder) {
		initializePanelToFrame(isOrder);
		windowsConfiguration();
		showWindow(this, true);
	}

	public void windowsConfiguration() {
		this.setTitle("Profile");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public void initializePanelToFrame(boolean isOrder) {

		new TextResources().changeLanguage();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(375, 812));
		panel.setBackground(new Color(216, 223, 224));

		configureButtons(isOrder);
		addComponentsToPanel(isOrder);
		addListeners();

		this.setContentPane(panel);
		this.pack();
	}

	public void initializeInsidePanel(boolean isOrder) {
		insidePanel = new RoundedPanel(50, new Color(177, 206, 209));
		insidePanel.setOpaque(false);
		insidePanel.setBorder(new EmptyBorder(50, 20, 380, 20));
		insidePanel.setLayout(null);
		insidePanel.setBounds(11, 250, 351, 200);
		configureButtons(isOrder);

	}

	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	public void addComponentsToPanel(boolean isOrder) {
		panel.add(LogoFactory.addLogoScaled());

		initializeInsidePanel(isOrder);
		addComponentsToInsidePanel();
		panel.add(insidePanel);
		panel.add(backBtn);
		panel.add(BackgroundFactory.addBackgroundLight());

	}

	public void addComponentsToInsidePanel() {
		orderNumber = LabelFactory.createLabel("#0", Color.WHITE, FontFactory.poppins(16));
		orderNumber.setBounds(10, 10, 30, 20);
		
		orderPrice  = LabelFactory.createLabel("35$", Color.WHITE, FontFactory.poppins(16));
		orderPrice.setBounds(50, 10, 30, 20);
		
		orderDate = LabelFactory.createLabel(TextResources.date + ": " + configureDate(new Date()), Color.WHITE, FontFactory.poppins(15));
		orderDate.setBounds(10, 50, 300, 20);
		
		roomNum = LabelFactory.createLabel(TextResources.roomField + ": 401", Color.WHITE, FontFactory.poppins(15));
		roomNum.setBounds(10, 80, 300, 20);
		
		paymentMethod = LabelFactory.createLabel(TextResources.payment + ": " + "card", Color.WHITE, FontFactory.poppins(15));
		paymentMethod.setBounds(10, 110, 300, 20);
		
		insidePanel.add(rating);
		insidePanel.add(paymentMethod);
		insidePanel.add(roomNum);
		insidePanel.add(orderPrice);
		insidePanel.add(orderDate);
		insidePanel.add(orderNumber);
	}

	public void configureButtons(boolean isOrder) {
		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(12, 40, 67, 45);
		
		rating = isOrder ? 
		ButtonFactory.createButton(TextResources.rate, FontFactory.poppins(15), ColorResources.bgLoginWindow, Color.white) : ButtonFactory.createButton(TextResources.rate, FontFactory.poppins(15), ColorResources.bgLoginWindow, Color.white);
		rating.setBounds(200, 170, 140, 22);
	}

	public void addListeners() {
		backBtn.addActionListener(this);
	}
	
	public String configureDate(Date adate) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = adate;
		String dateAsString = sdf.format(date);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(dateAsString));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DATE, 3); // number of days to add
		dateAsString = sdf.format(c.getTime());
		return dateAsString;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			this.dispose();
			new ProfileWindow();
		}

	}

}
