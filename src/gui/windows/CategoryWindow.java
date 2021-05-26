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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import SortingSearching.Search;
import SortingSearching.Sort;
import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import menu.Menu;
import order.Food;
import order.Order;
import order.Product;
import resources.ColorResources;
import resources.TextResources;

public class CategoryWindow extends JFrame implements ActionListener, MouseListener {

	private JPanel backgroundPanel;

	private Order order;

	private Color categoryColor;
	private String categoryName;
	private ArrayList<Product> categoryProducts;

	// components for main panel
	private JPanel mainContent;

	// components for the header
	private JPanel header;
	private JButton backButton;
	private JLabel headerLabel;
	private JTextField search;
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
		this.categoryProducts = products;
		this.order = order;
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}

	private void initializePanelToFrame() {
		backgroundPanel = new JPanel();
		backgroundPanel.setPreferredSize(new Dimension(375, 812));
		backgroundPanel.setLayout(new BorderLayout());

		configureHeader();
		configureMainContent(categoryProducts);
		configureCartPanel();
		addListeners();

		backgroundPanel.add(BorderLayout.NORTH, header);
		backgroundPanel.add(BorderLayout.CENTER, mainContent);
		backgroundPanel.add(BorderLayout.SOUTH, cartPanel);

		this.setContentPane(backgroundPanel);
		this.pack();
	}

	public void refreshMaincontent(ArrayList<Product> products, boolean isSearch) {

		backgroundPanel.remove(1);
		mainContent = new JPanel();
		configureMainContent(products);
		if(isSearch) {
			backgroundPanel.add(BorderLayout.SOUTH, cartPanel);
			backgroundPanel.add(BorderLayout.CENTER, mainContent);			
		}
		else {
			backgroundPanel.add(BorderLayout.NORTH, header);
			backgroundPanel.add(BorderLayout.CENTER, mainContent);			
			backgroundPanel.add(BorderLayout.SOUTH, cartPanel);
		}

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
		compobox.addActionListener(this);
		search.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Product> foundProducts = new Search().expoSearch(categoryProducts, search.getText());
				refreshMaincontent(foundProducts, true);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Product> foundProducts = new Search().expoSearch(categoryProducts, search.getText());
				refreshMaincontent(foundProducts, true);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Product> foundProducts = new Search().expoSearch(categoryProducts, search.getText());
				refreshMaincontent(foundProducts, true);
			}
		});
			
	}

	// all the content for the header panel
	public void configureHeader() {
		String[] sorts = { "Sort", "Sort by price", "Sort by popularity", "Sort by name" };
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

		cartPriceLabel = LabelFactory.createLabel(order.calcCost() + "€", ColorResources.frPopup,
				FontFactory.poppins(14));
		cartPriceLabel.setBounds(301, cartPanel.getHeight() / 2 - 15, 49, 20);

		viewCart = LabelFactory.createLabel(TextResources.viewCart, ColorResources.frPopup, FontFactory.poppins(14));
		viewCart.setBounds(67, cartPanel.getHeight() / 2 - 15, 200, 20);

		bagLabel = LabelFactory.createIconLabel(bagIcon);
		bagLabel.setText(order.getProducts().size() + "");
		bagLabel.setBounds(18, cartPanel.getHeight() / 2 - 25, 50, 40);
		bagLabel.setForeground(Color.RED);

		cartPanel.add(bagLabel);
		cartPanel.add(viewCart);
		cartPanel.add(cartPriceLabel);
	}

	// main panel
	public void configureMainContent(ArrayList<Product> products) {
		mainContent = new JPanel();
		mainContent.setLayout(new BorderLayout());
		mainContent.add(createVerticalScrollablePanel(products));
	}

	// layout display for the products
	public JPanel configureProductPanel(Product product) {
		Food foodProduct;

		JPanel panel = new JPanel();
		panel.setName(product.getName());
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

		JLabel plusButtonLabel;
		plusButtonLabel = LabelFactory.createIconLabel(plusIcon);
		plusButtonLabel.setBounds(285, 85, 24, 24);
		plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		plusButtonLabel.addMouseListener(this);

		JLabel priceLabel = LabelFactory.createLabel(product.getPrice() + "€", Color.BLACK, FontFactory.poppins(13));
		if (product instanceof Food) {
			foodProduct = (Food) product;
			if (foodProduct.isHasDiscount()) {
				priceLabel = new JLabel("<html><body><span style='text-decoration: line-through;'>"
						+ (foodProduct.getPrice() + foodProduct.getDiscount()) + "€</span></body></html>");
				JLabel newPrice = LabelFactory.createLabel(foodProduct.getPrice() + "€", Color.red,
						FontFactory.poppins(13));
				newPrice.setBounds(250, 65, 43, 19);
				panel.add(newPrice);
			}
		}

		priceLabel.setBounds(286, 65, 43, 19);

		panel.add(productimgLabel);
		panel.add(titleLabel);
		panel.add(descLabel);
		panel.add(plusButtonLabel);
		panel.add(priceLabel);
		return panel;
	}

	// creates a vertical scrollable panel
	public JScrollPane createVerticalScrollablePanel(ArrayList<Product> products) {
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(products.size(), 1, 0, 8));

		for (Product product : products) {
			container.add(configureProductPanel(product));
		}

		JScrollPane scrollPane = new JScrollPane(container);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);
		scrollBar.setUnitIncrement(16);
		scrollBar.setPreferredSize(new Dimension(0,0));
		scrollPane.setVerticalScrollBar(scrollBar);
		return scrollPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			this.dispose();
			new MenuWindow(order);
		}

		if (e.getSource() instanceof JComboBox) {
			@SuppressWarnings("unchecked")
			JComboBox<String> choices = (JComboBox<String>) e.getSource();
			String selectedSortMethod = (String) choices.getSelectedItem();
			switch (selectedSortMethod) {
			case "Sort by name":
				Collections.sort(categoryProducts, Sort.prodNameComparator);
				break;
			case "Sort by price":
				Collections.sort(categoryProducts, Sort.AscProdPriceComparator);
				break;
			case "Sort by popularity":
				Collections.sort(categoryProducts, Sort.AscProdPriceComparator);
				break;
			}
		}
		refreshMaincontent(categoryProducts, false);
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
			cartPriceLabel.setText(String.valueOf(order.calcCost()) + "€");
			bagLabel.setText(order.getProducts().size() + "");
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
