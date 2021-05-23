package gui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import menu.Menu;
import order.Order;
import order.Product;
import resources.ColorResources;
import resources.TextResources;

public class CategoryWindow extends JFrame implements ActionListener, MouseListener {

	private JPanel backgroundPanel;

	private Order order;

	private Menu menu;
	private Color categoryColor;
	private String categoryName;
	private ArrayList<Product> products;

	// components for main panel
	private JPanel mainContent;
	private ImageIcon productImage;
	private JLabel productimgLabel;
	private JLabel titleLabel;
	private JLabel descLabel;
	private JLabel priceLabel;
	private ImageIcon plusIcon;

	// components for the header
	private JPanel header;
	private JButton backButton;
	private JLabel headerLabel;
	private JTextField search;
	private String[] sorts = { "Sort", "Sort by price", "Sort by popularity", "Sort by name" };
	private JComboBox<String> compobox;
	private String headerText;
	private Color headerLabelColor;

	// cart panel
	private JPanel cartPanel;
	private ImageIcon bagIcon;
	private JLabel bagLabel;
	private JLabel viewCart;
	private JLabel cartPriceLabel;

	public CategoryWindow(Color categoryColor, String categoryName, ArrayList<Product> products, Order order) {
		this.categoryColor = categoryColor;
		this.categoryName = categoryName;
		this.products = products;
		this.order = order;
		this.menu = new Menu();
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}

	private void initializePanelToFrame() {
		backgroundPanel = new JPanel();
		backgroundPanel.setPreferredSize(new Dimension(375, 812));
		backgroundPanel.setLayout(new BorderLayout());

		configureHeader();
		configureMainContent();
		configureCartPanel();
		addListeners();

		backgroundPanel.add(BorderLayout.SOUTH, cartPanel);
		backgroundPanel.add(BorderLayout.CENTER, mainContent);
		backgroundPanel.add(BorderLayout.NORTH, header);

		this.setContentPane(backgroundPanel);
		this.pack();
	}

	// settings for the frame
	public void windowsConfiguration() {
		this.setTitle("Segaleo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	private void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	public void addListeners() {
		backButton.addActionListener(this);
		cartPanel.addMouseListener(this);
	}

	// all the content for the header panel
	public void configureHeader() {
		headerText = categoryName;
		headerLabelColor = categoryColor;
		header = new JPanel();

		header.setPreferredSize(new Dimension(375, 200));
		header.setLayout(null);

		compobox = new JComboBox<>(sorts);
		compobox.setBounds(236, 119, 120, 20);

		backButton = ButtonFactory.createButtonIcon(new ImageIcon("./buttonImages/Back Button.png"));
		backButton.setBounds(16, 30, 63, 23);

		headerLabel = new JLabel(headerText, SwingConstants.CENTER);
		headerLabel.setPreferredSize(new Dimension(100, 100));
		headerLabel.setOpaque(true);
		headerLabel.setBackground(headerLabelColor);
		headerLabel.setBounds(16, 77, 150, 62);

		search = new JTextField("Search");
		search.setBounds(16, 160, 340, 30);

		header.add(backButton);
		header.add(headerLabel);
		header.add(search);
		header.add(compobox);
	}

	// all the content for the cart panel
	public void configureCartPanel() {
		cartPanel = new JPanel();

		cartPanel.setLayout(null);
		cartPanel.setPreferredSize(new Dimension(375, 94));
		cartPanel.setBounds(0, 718, 375, 94);
		cartPanel.setBackground(Color.WHITE);
		cartPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

		bagIcon = new ImageIcon("Icons/Bag.png");

		cartPriceLabel = LabelFactory.createLabel("28,00", ColorResources.frPopup, FontFactory.poppins(14));
		cartPriceLabel.setBounds(301, cartPanel.getHeight() / 2 - 15, 49, 20);

		viewCart = LabelFactory.createLabel(TextResources.viewCart, ColorResources.frPopup, FontFactory.poppins(14));
		viewCart.setBounds(67, cartPanel.getHeight() / 2 - 15, 200, 20);

		bagLabel = LabelFactory.createIconLabel(bagIcon);
		bagLabel.setBounds(29, cartPanel.getHeight() / 2 - 15, 25, 25);

		cartPanel.add(bagLabel);
		cartPanel.add(viewCart);
		cartPanel.add(cartPriceLabel);
	}

	// main panel
	public void configureMainContent() {
		mainContent = new JPanel();
		mainContent.setLayout(new BorderLayout());
		mainContent.add(createVerticalScrollablePanel());
	}

	// layout display for the products
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
		descLabel.setBounds(120, 35, 200, 17);

		plusIcon = new ImageIcon("./buttonImages/plus.png");

		JLabel plusButtonLabel;
		plusButtonLabel = LabelFactory.createIconLabel(plusIcon);
		plusButtonLabel.setBounds(285, 85, 24, 24);
		plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		plusButtonLabel.addMouseListener(this);

		priceLabel = LabelFactory.createLabel(product.getPrice() + "€", Color.BLACK, FontFactory.poppins(13));
		priceLabel.setBounds(286, 65, 43, 19);

		panel.add(productimgLabel);
		panel.add(titleLabel);
		panel.add(descLabel);
		panel.add(plusButtonLabel);
		panel.add(priceLabel);
		return panel;
	}

	// creates a vertical scrollable panel
	public JScrollPane createVerticalScrollablePanel() {
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(products.size(), 1, 0, 8));

		for (Product product : products) {
			container.add(configureProductPanel(product));
		}

		JScrollPane scrollPane = new JScrollPane(container);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);

		return scrollPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			this.dispose();
			new MenuWindow(order);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == cartPanel) {
			this.dispose();
			new CartWindow(order, true);
		}
		if (e.getSource() instanceof JLabel) {
			JLabel plusLabel = (JLabel) e.getSource();
			JPanel parent = (JPanel) plusLabel.getParent();
			String productName = parent.getName();
			Product clickedProduct = Menu.findProduct(productName);
			order.addProduct(clickedProduct);
		}

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
