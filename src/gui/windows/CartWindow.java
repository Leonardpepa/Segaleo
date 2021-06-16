package gui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import gui.factory.TextFieldFactory;
import login.Login;
import menu.Menu;
import order.Coupon;
import order.CouponFactory;
import order.Order;
import order.Product;
import reservation.Activity;
import reservation.ActivityReader;
import reservation.Reservation;
import resources.ColorResources;
import resources.TextResources;

public class CartWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8702422650544044042L;
	/*
	 * This Class creates the cart window for both orders and reservations
	 * 
	 * It's separated in 3 categories to create the header, main content and the
	 * footer
	 * 
	 * The header and footer remain the same for both the reservation and the order,
	 * only the main content changes
	 */
	private JPanel backgroundPanel;
	private Reservation reservation;
	private Order order;
	private boolean isOrder;
	// header
	private JPanel header;
	private ImageIcon exitIcon = new ImageIcon("buttonImages/exit button.png");
	private JButton exitButton;
	private JLabel myCartLabel;

	// footer
	private JPanel footer;
	public static JTextField couponField;
	private JButton submitCouponButton;
	public static JButton paymentMethods;
	private JLabel totalLabel;
	private JLabel priceLabel;
	private JPanel priceHolder;
	private JButton orderNowButton;
	private JButton reserveNowButton;

	// main
	private JPanel mainContent;

	private JLabel quantinty;

	private int[][] a;

	private JPanel leftHelper;
	private JPanel rightHelper;

	public CartWindow(Order order) {
		this.order = order;
		this.isOrder = true;
		initilizePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}

	public CartWindow(Reservation reservation) {
		this.a = Activity.getA();
		this.reservation = reservation;
		this.isOrder = false;
		initilizePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}

	public void windowsConfiguration() {
		this.setTitle("Segaleo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public void initilizePanelToFrame() {
		backgroundPanel = new JPanel();
		backgroundPanel.setPreferredSize(new Dimension(375, 812));
		backgroundPanel.setLayout(new BorderLayout());

		configureHeader();
		configureFooter();
		configureMainContent();
		addListeners();
		leftHelper = new JPanel();
		leftHelper.setPreferredSize(new Dimension(24, mainContent.getWidth()));

		rightHelper = new JPanel();
		rightHelper.setPreferredSize(new Dimension(23, mainContent.getWidth()));

		backgroundPanel.add(BorderLayout.WEST, leftHelper);
		backgroundPanel.add(BorderLayout.EAST, rightHelper);
		backgroundPanel.add(BorderLayout.NORTH, header);
		backgroundPanel.add(BorderLayout.CENTER, mainContent);
		backgroundPanel.add(BorderLayout.SOUTH, footer);

		this.setContentPane(backgroundPanel);
		this.pack();

	}

	public void addListeners() {
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				if (isOrder)
					new MenuWindow(order);
				else {
					new ActivityWindow(reservation);
				}
			}
		});

		submitCouponButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (order.getTotalCost() < 4) {
					couponField.setBackground(new Color(232, 158, 158));
					couponField.setText(TextResources.invalidCouponTitle);
				} else {
					double discount = order.calcDiscount(couponField.getText());
					priceLabel.setText(discount + "€");
				}
			}
		});

		paymentMethods.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PaymentWindow();
			}
		});

		couponField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				couponField.setText("");
			}
		});

		orderNowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (order.getTotalCost() >= 20 && !paymentMethods.getText().equals(TextResources.payment)) {

					dispose();
					Coupon coupon = CouponFactory.GenerateCoupon(Login.loggedCustomer.getEmail());
					
					order.setPaymentMethod(paymentMethods.getText());
					order.setDate(new Date());
					Order.numberOfOrders += 1;
					order.setId(Order.numberOfOrders);
					order.setCoupon(coupon);
					if(paymentMethods.getText().equals(TextResources.roomBill)) {
						Login.loggedCustomer.setRoomBill(Login.loggedCustomer.getRoomBill() + order.getTotalCost());
					}
					Login.loggedCustomer.addCoupons(coupon);
					Login.loggedCustomer.addOrders(order);
					new CompleteWindow(coupon, true);

				} else if (order.getTotalCost() >= 10 && !paymentMethods.getText().equals(TextResources.payment)) {
					dispose();
					order.setPaymentMethod(paymentMethods.getText());
					order.setDate(new Date());
					Order.numberOfOrders += 1;
					order.setId(Order.numberOfOrders);
					if(paymentMethods.getText().equals(TextResources.roomBill)) {
						Login.loggedCustomer.setRoomBill(Login.loggedCustomer.getRoomBill() + order.getTotalCost());
					}
					Login.loggedCustomer.addOrders(order);
					new CompleteWindow(null, true);

				} else if (order.getTotalCost() < 10) {
					orderNowButton.setBackground(ColorResources.bgMainWindowBtn);
					orderNowButton.setText(TextResources.orderMin);

				} else {

					JOptionPane.showMessageDialog(null, TextResources.noPaymentSelected, TextResources.orderErrorTitle,
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		reserveNowButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!paymentMethods.getText().equals(TextResources.payment) && !reservation.getActivities().isEmpty()) {
					dispose();
					reservation.setPaymentMethod(paymentMethods.getText());
					reservation.setDate(new Date());
					Reservation.numberOfReservations += 1;
					reservation.setId(Reservation.numberOfReservations);
					if(paymentMethods.getText().equals(TextResources.roomBill)) {
						Login.loggedCustomer.setRoomBill(Login.loggedCustomer.getRoomBill() + order.getTotalCost());
					}
					Login.loggedCustomer.addReservation(reservation);
					new CompleteWindow(null, false);
				} else {
					JOptionPane.showMessageDialog(null, TextResources.noPaymentSelected,
							TextResources.reservationErrorTitle, JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
	}

	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	public void configureHeader() {
		header = new JPanel();
		header.setLayout(null);
		header.setBackground(new Color(244, 249, 250));
		header.setPreferredSize(new Dimension(375, 125));

		exitButton = ButtonFactory.createButtonIcon(exitIcon);
		exitButton.setBounds(30, 60, 13, 13);

		myCartLabel = LabelFactory.createLabel(TextResources.myCart, Color.BLACK, FontFactory.poppins(20));
		myCartLabel.setBounds(64, 40, 100, 50);

		header.add(exitButton);

		header.add(myCartLabel);
	}

	public void configureMainContent() {
		mainContent = new JPanel();
		mainContent.setLayout(new BorderLayout());
		mainContent.add(createVerticalScrollablePanel());
	}

	// creates a vertical scrollable panel
	public JScrollPane createVerticalScrollablePanel() {
		JPanel container = new JPanel();
		if (isOrder) {
			container.setLayout(new GridLayout(this.order.getProducts().size(), 1, 0, 8));

			for (Product product : this.order.getProducts()) {
				container.add(configureProductPanel(product));
			}
		} else {
			container.setLayout(new GridLayout(reservation.getActivities().size(), 1, 0, 8));

			for (Activity activity : reservation.getActivities()) {
				container.add(configureActivityPanel(activity));
			}
		}

		JScrollPane scrollPane = new JScrollPane(container);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);
		scrollBar.setUnitIncrement(16);
		scrollBar.setPreferredSize(new Dimension(0, 0));
		scrollPane.setVerticalScrollBar(scrollBar);
		return scrollPane;
	}

	public JPanel configureProductPanel(Product product) {
		JPanel panel = new JPanel();
		panel.setName(String.valueOf(product.getId()));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(325, 120));
		panel.setBackground(Color.white);

		ImageIcon productImage = new ImageIcon(product.getPath());
		JLabel productimgLabel = LabelFactory.createIconLabel(productImage);
		productimgLabel.setBounds(10, 10, 100, 100);

		JLabel titleLabel = LabelFactory.createLabel(product.getName(), Color.BLACK, FontFactory.poppins(14));
		titleLabel.setBounds(120, 20, 200, 17);

		JLabel descLabel = LabelFactory.createLabel(product.getDescription(), Color.GRAY, FontFactory.poppins(12));
		descLabel.setBounds(120, 35, 200, 40);

		ImageIcon plusIcon = new ImageIcon("./buttonImages/plus.png");
		JLabel plusButtonLabel = LabelFactory.createIconLabel(plusIcon);
		plusButtonLabel.setIcon(plusIcon);
		plusButtonLabel.setBounds(290, 85, 24, 24);
		plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		plusButtonLabel.addMouseListener(new productsListener());
		plusButtonLabel.setName("plus");

		ImageIcon minusIcon = new ImageIcon("./buttonImages/minus.png");

		JLabel minusButtonLabel = LabelFactory.createIconLabel(minusIcon);
		minusButtonLabel.setBounds(230, 85, 24, 24);
		minusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		minusButtonLabel.addMouseListener(new productsListener());
		minusButtonLabel.setName("minus");

		JLabel quantinty = LabelFactory.createLabel(order.getProd().get(product) + "x", Color.BLACK,
				FontFactory.poppins(13));
		quantinty.setBounds(260, 85, 50, 20);

		JLabel productPriceLabel = LabelFactory.createLabel(product.getPrice() + "€", Color.BLACK,
				FontFactory.poppins(13));
		productPriceLabel.setBounds(190, 85, 43, 19);

		panel.add(productimgLabel);
		panel.add(titleLabel);
		panel.add(descLabel);
		panel.add(plusButtonLabel);
		panel.add(quantinty);
		panel.add(minusButtonLabel);
		panel.add(productPriceLabel);
		return panel;
	}

	public JPanel configureActivityPanel(Activity activity) {
		JPanel panel = new JPanel();
		panel.setName(String.valueOf(activity.getId()));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(320, 220));
		panel.setBackground(Color.white);

		ImageIcon productImage = new ImageIcon(activity.getPath());
		JLabel productimgLabel = LabelFactory.createIconLabel(productImage);
		productimgLabel.setBounds(5, 5, 320, 170);

		JLabel titleLabel = LabelFactory.createLabel(activity.getName(), Color.BLACK, FontFactory.poppins(14));
		titleLabel.setBounds(5, 180, 150, 40);

		ImageIcon plusIcon = new ImageIcon("./buttonImages/plus.png");
		JLabel plusButtonLabel = LabelFactory.createIconLabel(plusIcon);
		plusButtonLabel.setIcon(plusIcon);
		plusButtonLabel.setBounds(290, 180, 24, 24);
		plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		plusButtonLabel.addMouseListener(new activitiesListener());
		plusButtonLabel.setName("plus");

		ImageIcon minusIcon = new ImageIcon("./buttonImages/minus.png");

		JLabel minusButtonLabel = LabelFactory.createIconLabel(minusIcon);
		minusButtonLabel.setBounds(230, 180, 24, 24);
		minusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		minusButtonLabel.addMouseListener(new activitiesListener());
		minusButtonLabel.setName("minus");

		quantinty = LabelFactory.createLabel(reservation.getAct().get(activity) + "x", Color.BLACK,
				FontFactory.poppins(13));
		quantinty.setBounds(260, 180, 50, 20);

		JLabel productPriceLabel = LabelFactory.createLabel(activity.getPrice() + "€", Color.BLACK,
				FontFactory.poppins(13));
		productPriceLabel.setBounds(155, 185, 43, 19);

		panel.add(productimgLabel);
		panel.add(productPriceLabel);
		panel.add(titleLabel);
		panel.add(plusButtonLabel);
		panel.add(quantinty);
		panel.add(minusButtonLabel);
		panel.add(productPriceLabel);

		return panel;
	}

	public void configureFooter() {
		
		reserveNowButton = new JButton();
		orderNowButton = new JButton();
		submitCouponButton = new JButton();
		couponField = new JTextField();
		
		footer = new JPanel();
		footer.setLayout(null);
		footer.setPreferredSize(new Dimension(375, 290));
		footer.setBackground(new Color(244, 249, 250));

		if (isOrder) {
			couponField = TextFieldFactory.createTextField(TextResources.couponCode, new Color(216, 223, 224),
					Color.BLACK, FontFactory.poppins(14));
			couponField.setBounds(24, 10, 195, 48);

			submitCouponButton = ButtonFactory.createButton(TextResources.submit, FontFactory.poppins(14),
					new Color(216, 223, 224), Color.BLACK);
			submitCouponButton.setBounds(231, 10, 121, 48);
			footer.add(couponField);
			footer.add(submitCouponButton);
		}

		paymentMethods = ButtonFactory.createButton(TextResources.payment, FontFactory.poppins(14), Color.LIGHT_GRAY,
				Color.BLACK);
		paymentMethods.setFont(paymentMethods.getFont().deriveFont(Font.BOLD, 14));
		paymentMethods.setBounds(24, 68, 328, 63);

		priceHolder = new JPanel();
		priceHolder.setLayout(null);
		priceHolder.setPreferredSize(new Dimension(328, 63));
		priceHolder.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.WHITE));
		priceHolder.setBackground(Color.WHITE);

		totalLabel = LabelFactory.createLabel(TextResources.total, Color.BLACK, FontFactory.poppins(14));
		if (!isOrder) {
			priceLabel = LabelFactory.createLabel(reservation.calcCost() + "€", Color.BLACK, FontFactory.poppins(14));
		} else {
			priceLabel = LabelFactory.createLabel(order.calcCost() + "€", Color.BLACK, FontFactory.poppins(14));
		}
		totalLabel.setBounds(23, 18, 100, 20);
		priceLabel.setBounds(264, 18, 50, 20);

		priceHolder.setBounds(24, 141, 328, 63);
//define submit button
		if (isOrder) {
			orderNowButton = ButtonFactory.createButton(TextResources.orderNow, FontFactory.poppins(15),
					ColorResources.frMainWindowBtn, Color.WHITE);
			orderNowButton.setBounds(24, 214, 328, 41);
			footer.add(orderNowButton);
		} else {
			reserveNowButton = ButtonFactory.createButton(TextResources.reserveNow, FontFactory.poppins(15),
					ColorResources.frMainWindowBtn, Color.WHITE);
			reserveNowButton.setBounds(24, 214, 328, 41);
			footer.add(reserveNowButton);
		}

		priceHolder.add(totalLabel);
		priceHolder.add(priceLabel);

		footer.add(paymentMethods);
		footer.add(priceHolder);

	}

	class productsListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			JPanel parent = (JPanel) label.getParent();
			String productId = parent.getName();
			Product clickedProduct = Menu.findProduct(Integer.parseInt(productId));
	
			if (label.getName().equals("plus")) {
				order.addProduct(clickedProduct);
			} else if (label.getName().equals("minus")) {
				order.removeProduct(clickedProduct);
			}
			initilizePanelToFrame();
		}
	}
	
	class activitiesListener extends MouseAdapter {
		
		public void mouseClicked(MouseEvent e) {
			JLabel label = (JLabel) e.getSource();
			JPanel parent = (JPanel) label.getParent();
			String productId = parent.getName();
			Activity clickedActivity;
			
			clickedActivity = ActivityReader.findActivity(Integer.parseInt(productId));
			clickedActivity.setSelpeople(reservation.getAct().get(clickedActivity));
			
			if (label.getName().equals("plus")) {
				reservation.addActivity(clickedActivity);
				clickedActivity.setColumn(ActivityReader.getActivitiesList().indexOf(clickedActivity) * 2);
				if (!clickedActivity.plusCheck()) {
					reservation.removeActivity(clickedActivity);
				}
			} else if (label.getName().equals("minus")) {
				reservation.removeActivity(clickedActivity);
				clickedActivity.setColumn(ActivityReader.getActivitiesList().indexOf(clickedActivity) * 2);
				clickedActivity.setSelpeople(reservation.getAct().get(clickedActivity));
				a[clickedActivity.getSelday()][clickedActivity.getSelhour() + clickedActivity.getColumn()] += 1;
			}	
			initilizePanelToFrame();
		}
		
	}
}
