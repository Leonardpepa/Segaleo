package gui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.components.RoundedPanel;
import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import gui.factory.LogoFactory;
import login.Login;
import order.Order;
import order.Product;
import platformData.PlatformData;
import reservation.Activity;
import reservation.Reservation;
import resources.ColorResources;
import resources.TextResources;
import roomCustomer.Customer;

@SuppressWarnings("serial")
public class MyOrderReservationWindow extends JFrame {

	private JPanel panel;
	private JPanel mainContent;

	private String path = "buttonImages/Back Button";
	private String lang = TextResources.imageLang;
	private ImageIcon backImage = new ImageIcon(path + lang);
	private JButton backBtn;

	private boolean isOrder;
	private Customer customer = Login.loggedCustomer;


	private int amount = 0;

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
		addComponentsToPanel(isOrder);
		this.setContentPane(panel);
		this.pack();
	}

	public void initializeMainContent() {
		mainContent = new JPanel();
		mainContent.setBackground(ColorResources.paymentBtn);
		mainContent.setOpaque(false);

		mainContent.setLayout(new FlowLayout());
		mainContent.setBounds(0, 250, 375, 550);
		JScrollPane pane = createVerticalScrollablePanel();
		String labelName = isOrder ? TextResources.noOrder : TextResources.noReservation;

		if (amount == 0) {
			mainContent.setLayout(null);
			JLabel label = LabelFactory.createLabel(labelName, ColorResources.bgLoginWindow, FontFactory.poppins(16));
			label.setBounds(25, 15, 200, 22);
			mainContent.add(label);
			mainContent.setBounds(0, 250, 375, 50);
		} else if (amount > 2) {
			mainContent.setLayout(new BorderLayout());
		}
		mainContent.add(pane);
	}

	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	public void addComponentsToPanel(boolean isOrder) {
		panel.add(LogoFactory.addLogoScaled());
		initializeMainContent();
		panel.add(mainContent);
		panel.add(backBtn);
		panel.setBackground(ColorResources.paymentBtn);

	}

	public JPanel displayPanel(Order order, Reservation reservation) {

		JPanel insidePanel = new RoundedPanel(50, new Color(177, 206, 209));
		insidePanel.setOpaque(false);
		insidePanel.setBorder(new EmptyBorder(50, 20, 380, 20));
		insidePanel.setLayout(null);
		insidePanel.setPreferredSize(new Dimension(350, 200));

		JLabel number = isOrder ? LabelFactory.createLabel("#" + order.getId(), Color.WHITE, FontFactory.poppins(16))
				: LabelFactory.createLabel("#" + reservation.getId(), Color.WHITE, FontFactory.poppins(16));
		number.setBounds(10, 10, 30, 20);

		JLabel price = isOrder
				? LabelFactory.createLabel(order.getTotalCost() + "€", Color.WHITE, FontFactory.poppins(16))
				: LabelFactory.createLabel(reservation.getTotalCost() + "€", Color.WHITE, FontFactory.poppins(16));
		price.setBounds(50, 10, 60, 20);

		JLabel date = isOrder
				? LabelFactory.createLabel(TextResources.date + ": " + configureDate(order.getDate()), Color.WHITE,
						FontFactory.poppins(13))
				: LabelFactory.createLabel(TextResources.date + ": " + configureDate(reservation.getDate()),
						Color.WHITE, FontFactory.poppins(13));
		date.setBounds(10, 50, 300, 20);

		JLabel roomNum = LabelFactory.createLabel(TextResources.roomField + ": " + customer.getRoom().getNumber(),
				Color.WHITE, FontFactory.poppins(13));
		roomNum.setBounds(10, 80, 300, 20);

		JLabel paymentMethod = isOrder
				? LabelFactory.createLabel(TextResources.payment + ": " + order.getPaymentMethod(), Color.WHITE,
						FontFactory.poppins(13))
				: LabelFactory.createLabel(TextResources.payment + ": " + reservation.getPaymentMethod(), Color.WHITE,
						FontFactory.poppins(13));
		paymentMethod.setBounds(10, 110, 300, 20);

		int y = 10; // y for set bounds
		int limit = 80;
		if (isOrder) {
			for (Product p : order.getProducts()) {
				JLabel product = LabelFactory.createLabel(order.getProd().get(p) + "x " + p.getName(), Color.WHITE,
						FontFactory.poppins(13));
				product.setBounds(210, y, 300, 20);
				insidePanel.add(product);
				y += 20;
				if (y >= limit) {
					JLabel andMore = LabelFactory.createLabel("and more", Color.white, FontFactory.poppins(13));
					andMore.setBounds(210, y, 300, 20);
					insidePanel.add(andMore);
					break; // the customer will be able to view the complete order when rating it
				}
			}
		} else {
			for (Activity activity : reservation.getActivities()) {
				JLabel product = LabelFactory.createLabel(
						reservation.getAct().get(activity) + "x " + activity.getName(), Color.WHITE,
						FontFactory.poppins(13));
				product.setBounds(210, y, 300, 20);
				insidePanel.add(product);
				y += 20;
				if (y >= limit) {
					JLabel andMore = LabelFactory.createLabel("and more", Color.white, FontFactory.poppins(13));
					andMore.setBounds(210, y, 300, 20);
					insidePanel.add(andMore);
					break; // the customer will be able to view the complete reservation when rating it
				}
			}
		}
		JButton cancel = ButtonFactory.createButton(TextResources.cancelBtn, FontFactory.poppins(15),
				ColorResources.appetizer, Color.WHITE);
		JButton rating = ButtonFactory.createButton(TextResources.rate, FontFactory.poppins(15),
				ColorResources.bgLoginWindow, Color.white);
		rating.setBounds(200, 170, 140, 22);
		
		if (isOrder) {
			cancel.setName(String.valueOf(order.getId()));
			cancel.addActionListener(new CancelOrderListener());

			rating.setName(String.valueOf(order.getId()));
			rating.addActionListener(new RateOrderListener());
		} else {
			cancel.setName(String.valueOf(reservation.getId()));
			cancel.addActionListener(new CancelReservationListener());

			rating.setName(String.valueOf(reservation.getId()));
			rating.addActionListener(new RateReservationListener());
		}
		
		cancel.setBounds(20, 170, 90, 22);



		insidePanel.add(cancel);
		insidePanel.add(rating);
		insidePanel.add(paymentMethod);
		insidePanel.add(roomNum);
		insidePanel.add(price);
		insidePanel.add(date);
		insidePanel.add(number);

		return insidePanel;
	}

	public void configureButtons(boolean isOrder) {
		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(12, 40, 67, 45);
	}

	public void addListeners() {
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ProfileWindow();
			}
		});
	}

	// creates a vertical scrollable panel
	public JScrollPane createVerticalScrollablePanel() {
		JPanel container = new JPanel();
		container.setBackground(ColorResources.paymentBtn);
		if (isOrder) {
			container.setLayout(new GridLayout(customer.getOrders().size(), 1, 10, 8));
			for (Order order : customer.getOrders()) {
				container.add(displayPanel(order, null));
			}
		} else {
			container.setLayout(new GridLayout(customer.getReservations().size(), 1, 10, 8));
			for (Reservation reservation : customer.getReservations()) {
				container.add(displayPanel(null, reservation));
			}
		}

		amount = container.getComponentCount();
		JScrollPane scrollPane = new JScrollPane(container);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBackground(ColorResources.paymentBtn);
		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);
		scrollBar.setUnitIncrement(16);
		scrollBar.setPreferredSize(new Dimension(0, 0));
		scrollPane.setVerticalScrollBar(scrollBar);
		return scrollPane;
	}

	public String configureDate(Date adate) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = adate;
		String dateAsString = sdf.format(date);
		return dateAsString;

	}

	class CancelOrderListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Order orderClicked = getOrder(Integer.parseInt(((JButton) e.getSource()).getName()));
			Date now = new Date();
			long milliseconds = now.getTime() - orderClicked.getDate().getTime();
			long timePassed = (milliseconds / 1000) / 60;
			if (timePassed < 10) {
				Login.loggedCustomer.removeOrder(orderClicked);
				Login.loggedCustomer.removeCoupon(orderClicked.getCoupon());
				PlatformData.saveData();
				JOptionPane.showMessageDialog(null, TextResources.orderHasBeenCanceled, TextResources.orderErrorTitle,
						JOptionPane.INFORMATION_MESSAGE);
				initializePanelToFrame(true);
			} else {
				JOptionPane.showMessageDialog(null, TextResources.timeLimitOrder, TextResources.orderErrorTitle,
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	class CancelReservationListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Reservation reservationClicked = getReservation(Integer.parseInt(((JButton) e.getSource()).getName()));
			Login.loggedCustomer.removeReservation(reservationClicked);
			JOptionPane.showMessageDialog(null, TextResources.resHasBeenCanceled, "Segaleo", JOptionPane.INFORMATION_MESSAGE);
			PlatformData.saveData();
			initializePanelToFrame(true);
		}
		
	}

	class RateOrderListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Order orderClicked = getOrder(Integer.parseInt(((JButton) e.getSource()).getName()));
			new RateOrderReservation(orderClicked);
		}
	}

	class RateReservationListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Reservation reservationClicked = getReservation(Integer.parseInt(((JButton) e.getSource()).getName()));
			new RateOrderReservation(reservationClicked);
		}
	}

	public Order getOrder(int id) {
		for (Order order : customer.getOrders()) {
			if (order.getId() == id) {
				return order;
			}
		}
		return null;
	}

	public Reservation getReservation(int id) {
		for (Reservation reservation : customer.getReservations()) {
			if (reservation.getId() == id) {
				return reservation;
			}
		}
		return null;
	}

}
