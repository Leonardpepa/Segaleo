package gui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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

public class CartWindow extends JFrame implements ActionListener, MouseListener {

	private JPanel backgroundPanel;
	
	private Reservation reservation;
	private Order order;
	private boolean isOrder;
	// header
	private JPanel header;
	private ImageIcon exitIcon = new ImageIcon("buttonImages/exit button.png");
	private JButton exitButton;
	private JLabel myCartLabel;
	private JButton backButton;

	// footer
	JPanel footer;
	private JTextField couponField;
	private JButton submitCouponButton;
	private JButton paymentMethods;
	private JLabel totalLabel;
	private JLabel priceLabel;
	private JPanel priceHolder;
	private JButton orderNowButton;
	private JButton reserveNowButton;

	// main
	private JPanel mainContent;
	private ImageIcon productImage;
	private JLabel productimgLabel;
	private JLabel titleLabel;
	private JLabel descLabel;
	private JLabel productPriceLabel;
	private ImageIcon plusIcon;
	private JLabel plusButtonLabel;
	private ImageIcon minusIcon;
	private JLabel minusButtonLabel;

	private JLabel quantinty;

	
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
		leftHelper  = new JPanel();
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
		exitButton.addActionListener(this);
		backButton.addActionListener(this);
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

		String path = "buttonImages/Back Button";
		String lang = TextResources.imageLang;
		ImageIcon backImage = new ImageIcon(path + lang);
		backButton = ButtonFactory.createButtonIcon(backImage);
		backButton.setBounds(280, 55, 63, 23);

		myCartLabel = LabelFactory.createLabel(TextResources.myCart, Color.BLACK, FontFactory.poppins(20));
		myCartLabel.setBounds(64, 40, 100, 50);

		header.add(exitButton);
		header.add(backButton);
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
			container.setLayout(new GridLayout(order.getProducts().size(), 1, 0, 8));

			for (Product product : order.getProducts()) {
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
		scrollBar.setPreferredSize(new Dimension(0,0));
		scrollPane.setVerticalScrollBar(scrollBar);
		return scrollPane;
	}

	public JPanel configureProductPanel(Product product) {
		JPanel panel = new JPanel();
		panel.setName(product.getName());
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(325, 120));
		panel.setBackground(Color.white);

		productImage = new ImageIcon(product.getPath());
		productimgLabel = LabelFactory.createIconLabel(productImage);
		productimgLabel.setBounds(10, 10, 100, 100);

		titleLabel = LabelFactory.createLabel(product.getName(), Color.BLACK, FontFactory.poppins(14));
		titleLabel.setBounds(120, 20, 200, 17);

		descLabel = LabelFactory.createLabel(product.getDescription(), Color.GRAY, FontFactory.poppins(12));
		descLabel.setBounds(120, 35, 200, 40);

		plusIcon = new ImageIcon("./buttonImages/plus.png");
		plusButtonLabel = LabelFactory.createIconLabel(plusIcon);
		plusButtonLabel.setIcon(plusIcon);
		plusButtonLabel.setBounds(290, 85, 24, 24);
		plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		plusButtonLabel.addMouseListener(this);
		plusButtonLabel.setName("plus");

		minusIcon = new ImageIcon("./buttonImages/minus.png");

		minusButtonLabel = LabelFactory.createIconLabel(minusIcon);
		minusButtonLabel.setBounds(230, 85, 24, 24);
		minusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		minusButtonLabel.addMouseListener(this);
		minusButtonLabel.setName("minus");

		quantinty = LabelFactory.createLabel(order.getProd().get(product) +"x", Color.BLACK, FontFactory.poppins(13));
		quantinty.setBounds(260, 85, 50, 20);

		productPriceLabel = LabelFactory.createLabel(product.getPrice() + "€", Color.BLACK, FontFactory.poppins(13));
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
		panel.setName(activity.getName());
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(320, 220));
		panel.setBackground(Color.white);

		productImage = new ImageIcon(activity.getPath());
		productimgLabel = LabelFactory.createIconLabel(productImage);
		productimgLabel.setBounds(5, 5, 320, 170);

		titleLabel = LabelFactory.createLabel(activity.getName(), Color.BLACK, FontFactory.poppins(14));
		titleLabel.setBounds(5, 180, 150, 40);


		plusIcon = new ImageIcon("./buttonImages/plus.png");
		plusButtonLabel = LabelFactory.createIconLabel(plusIcon);
		plusButtonLabel.setIcon(plusIcon);
		plusButtonLabel.setBounds(290, 180, 24, 24);
		plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		plusButtonLabel.addMouseListener(this);
		plusButtonLabel.setName("plus");

		minusIcon = new ImageIcon("./buttonImages/minus.png");

		minusButtonLabel = LabelFactory.createIconLabel(minusIcon);
		minusButtonLabel.setBounds(230, 180, 24, 24);
		minusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		minusButtonLabel.addMouseListener(this);
		minusButtonLabel.setName("minus");

		quantinty = LabelFactory.createLabel(reservation.getAct().get(activity) +"x", Color.BLACK, FontFactory.poppins(13));
		quantinty.setBounds(260, 180, 50, 20);
		
		productPriceLabel = LabelFactory.createLabel(activity.getPrice() + "€", Color.BLACK, FontFactory.poppins(13));
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
		footer = new JPanel();
		footer.setLayout(null);
		footer.setPreferredSize(new Dimension(375, 290));
		footer.setBackground(new Color(244, 249, 250));

		couponField = TextFieldFactory.createTextField(TextResources.couponCode, new Color(216, 223, 224), Color.BLACK,
				FontFactory.poppins(14));
		couponField.setBounds(24, 10, 195, 48);

		submitCouponButton = ButtonFactory.createButton(TextResources.submit, FontFactory.poppins(14),
				new Color(216, 223, 224), Color.BLACK);
		submitCouponButton.setBounds(231, 10, 121, 48);
		submitCouponButton.addActionListener(this);

		paymentMethods = ButtonFactory.createButton(TextResources.payment, FontFactory.poppins(14), Color.LIGHT_GRAY,
				Color.BLACK);
		paymentMethods.setBounds(24, 68, 328, 63);

		priceHolder = new JPanel();
		priceHolder.setLayout(null);
		priceHolder.setPreferredSize(new Dimension(328, 63));
		priceHolder.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.WHITE));
		priceHolder.setBackground(Color.WHITE);

		totalLabel = LabelFactory.createLabel(TextResources.total, Color.BLACK, FontFactory.poppins(14));
		if (!isOrder) {
			priceLabel = LabelFactory.createLabel(reservation.calcCost() + "€", Color.BLACK, FontFactory.poppins(14));
		}
		else {
			priceLabel = LabelFactory.createLabel(order.calcCost() + "€", Color.BLACK, FontFactory.poppins(14));
		}
		totalLabel.setBounds(23, 18, 100, 20);
		priceLabel.setBounds(264, 18, 50, 20);

		priceHolder.setBounds(24, 141, 328, 63);
//define submit button
		if(isOrder) {
			orderNowButton = ButtonFactory.createButton(TextResources.orderNow, FontFactory.poppins(15),
					ColorResources.frMainWindowBtn, Color.WHITE);
			orderNowButton.setBounds(24, 214, 328, 41);
			orderNowButton.addActionListener(this);
			footer.add(orderNowButton);
		}
		else {
			reserveNowButton = ButtonFactory.createButton(TextResources.reserveNow, FontFactory.poppins(15),
					ColorResources.frMainWindowBtn, Color.WHITE);
			reserveNowButton.setBounds(24, 214, 328, 41);
			reserveNowButton.addActionListener(this);
			footer.add(reserveNowButton);
		}
		
		priceHolder.add(totalLabel);
		priceHolder.add(priceLabel);

		footer.add(couponField);
		footer.add(submitCouponButton);
		footer.add(paymentMethods);
		footer.add(priceHolder);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == exitButton) {
			if(isOrder) {
				int selectedOption = JOptionPane.showConfirmDialog(null, TextResources.cancelOrder, TextResources.cancelOrderTitle,
						JOptionPane.YES_NO_OPTION);
				if (selectedOption == 0) {
					order.clearOrder();
					this.dispose();
					new MainWindow();
				}
			}
			else {
				int selectedOption = JOptionPane.showConfirmDialog(null, TextResources.cancelReservation, TextResources.cancelReservationTitle,
						JOptionPane.YES_NO_OPTION);
				if (selectedOption == 0) {
					reservation.clearReservation();
					this.dispose();
					new MainWindow();
				}
			}
			
		}
		if (e.getSource() == backButton) {
			this.dispose();
			if(isOrder) new MenuWindow(order);
			else {
				ActivityReader actReader = new ActivityReader();
				new ActivityWindow(actReader.getActivitiesList(),reservation);
			}
		}
		if (e.getSource() == submitCouponButton) {
			Coupon coupon = CouponFactory.GenerateCoupon();
			double discount = order.calcDiscount(coupon);
			priceLabel.setText(discount + "€");
		}
		if(e.getSource().equals( orderNowButton)) {
			if(order.getTotalCost() < 10) {
				JOptionPane.showMessageDialog(null, TextResources.orderError, TextResources.orderErrorTitle, JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, TextResources.orderSuccess, TextResources.orderSuccessTitle, JOptionPane.INFORMATION_MESSAGE);
				if(order.getTotalCost() > 20) {
					Coupon coupon = CouponFactory.GenerateCoupon();
					JOptionPane.showMessageDialog(null, "Thank you for the order.Here is a coupon with the code: " + coupon.getCode());
					Login.loggedCustomer.addCoupons(coupon);
				}
				Login.loggedCustomer.addOrders(order);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JLabel) {
			JLabel label = (JLabel) e.getSource();
			JPanel parent = (JPanel) label.getParent();
			String productName = parent.getName();
			if(isOrder) {
				Product clickedProduct = Menu.findProduct(productName);
				if (label.getName().equals("plus")) {
					order.addProduct(clickedProduct);
				} else if (label.getName().equals("minus")) {
					order.removeProduct(clickedProduct);
				}
			}
			else {
				Activity clickedActivity =null;
				for (Activity a : new ActivityReader().getActivitiesList()) {
					if (a.getName().equalsIgnoreCase(productName)) {
						clickedActivity = a;
					}
				}
				if (label.getName().equals("plus")) {
					reservation.addActivity(clickedActivity);
				} else if (label.getName().equals("minus")) {
					reservation.removeActivity(clickedActivity);
				}
			}
		
		}

		initilizePanelToFrame();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
