package gui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import order.Order;
import order.Product;
import reservation.Activity;
import resources.ColorResources;
import resources.TextResources;
import roomCustomer.Customer;

public class MyOrderReservationWindow extends JFrame implements ActionListener {

	private JPanel panel;
	private JPanel mainContent;

	private String path = "buttonImages/Back Button";
	private String lang = TextResources.imageLang;
	private ImageIcon backImage = new ImageIcon(path + lang);
	private JButton backBtn;

	// αν δεν υπάρχει παραγγελια τοτε θα εμφανιζει αναλογο μηνυμα
	private boolean isOrder;
	private Customer customer = Login.loggedCustomer;


	public MyOrderReservationWindow(boolean isOrder) {
		this.isOrder = isOrder;
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
		addListeners();
		addComponentsToPanel(true);
		this.setContentPane(panel);
		this.pack();
	}
	
	public void initializeMainContent() {
		mainContent = new RoundedPanel(50, Color.WHITE);
		mainContent.setBorder(new EmptyBorder(0, 0,0 ,0));
		mainContent.setOpaque(false);
		mainContent.setLayout(new BorderLayout());
		JScrollPane scroll = createVerticalScrollablePanel(); 
		mainContent.add(scroll);
		mainContent.setBounds(0, 250, 375, 400);
	}

	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	public void addComponentsToPanel(boolean isOrder) {
		panel.add(LogoFactory.addLogoScaled());
		initializeMainContent();
		panel.add(mainContent);
		panel.add(backBtn);
		panel.add(BackgroundFactory.addBackgroundLight());

	}
	
	public JPanel orderPanel(Order order, int index) {
		
		JPanel insidePanel = new RoundedPanel(50, new Color(177, 206, 209));
		insidePanel.setOpaque(false);
		insidePanel.setBorder(new EmptyBorder(50, 20, 380, 20));
		insidePanel.setLayout(null);
//		insidePanel.setBounds(11, 250, 351, 200);
		insidePanel.setPreferredSize(new Dimension(351, 200));
		
		JLabel orderNumber = LabelFactory.createLabel("#" + (index + 1), Color.WHITE, FontFactory.poppins(16));
		orderNumber.setBounds(10, 10, 30, 20);
		
		JLabel orderPrice  = LabelFactory.createLabel(order.getTotalCost()+ "€", Color.WHITE, FontFactory.poppins(16));
		orderPrice.setBounds(50, 10, 40, 20);
		
		JLabel orderDate = LabelFactory.createLabel(TextResources.date + ": " + configureDate(order.getDate()), Color.WHITE, FontFactory.poppins(15));
		orderDate.setBounds(10, 50, 300, 20);
		
		JLabel roomNum = LabelFactory.createLabel(TextResources.roomField + ": " + customer.getRoom().getNumber(), Color.WHITE, FontFactory.poppins(15));
		roomNum.setBounds(10, 80, 300, 20);
		
		JLabel paymentMethod = LabelFactory.createLabel(TextResources.payment + ": " + order.getPaymentMethod(), Color.WHITE, FontFactory.poppins(15));
		paymentMethod.setBounds(10, 110, 300, 20);
		int y = 50;
		for(Product p: order.getProducts()) {
			JLabel product = LabelFactory.createLabel(order.getProd().get(p) + "x " + p.getName(), Color.WHITE, FontFactory.poppins(15));
			product.setBounds(200, y, 300, 20);
			insidePanel.add(product);
			y += 20;
		}
		JButton rating = isOrder ? 
				ButtonFactory.createButton(TextResources.rate, FontFactory.poppins(15), ColorResources.bgLoginWindow, Color.white) : ButtonFactory.createButton(TextResources.rate, FontFactory.poppins(15), ColorResources.bgLoginWindow, Color.white);
				rating.setBounds(200, 170, 140, 22);
		insidePanel.add(rating);
		insidePanel.add(paymentMethod);
		insidePanel.add(roomNum);
		insidePanel.add(orderPrice);
		insidePanel.add(orderDate);
		insidePanel.add(orderNumber);
		return insidePanel;
	}
	public void configureButtons(boolean isOrder) {
		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(12, 40, 67, 45);
	}

	public void addListeners() {
		backBtn.addActionListener(this);
	}
	
	// creates a vertical scrollable panel
		public JScrollPane createVerticalScrollablePanel() {
			JPanel container = new JPanel();
			if (isOrder) {
					container.setLayout(new GridLayout(customer.getOrders().size(), 1, 10, 8));
					for (int i=0; i<customer.getOrders().size(); i++) {
						container.add(orderPanel(customer.getOrders().get(i), i));
					}
				}
			JScrollPane scrollPane = new JScrollPane(container);
			scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);
			scrollBar.setUnitIncrement(16);
			scrollBar.setPreferredSize(new Dimension(0,0));
			scrollPane.setVerticalScrollBar(scrollBar);
			return scrollPane;
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
