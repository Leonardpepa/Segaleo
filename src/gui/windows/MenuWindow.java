package gui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

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

import SortingSearching.Search;
import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import gui.factory.TextFieldFactory;
import menu.Menu;
import order.Food;
import order.Order;
import order.Product;
import resources.ColorResources;
import resources.TextResources;

public class MenuWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// contains all the panels for the whole gui window
	private JPanel backgroundPanel;

	// order
	private Order order;

	// main panel
	private JPanel mainContent;
	private JLabel explore;
	private JButton main;
	private JButton salads;
	private JButton dessert;
	private JButton appetizers;
	private JButton breakfast;
	private JButton drinks;
	private JButton coffee;

	// header
	private JPanel header;
	private JButton backButton;
	private JTextField search;
	private JPanel container;
	private JLabel dealsOfTheDay;

	// cart panel
	private JPanel cartPanel;
	private ImageIcon bagIcon;
	private JLabel bagLabel;
	private JLabel viewCart;
	private JLabel priceCartLabel;
	String input = "";
	
	private JPanel leftHelper;
	private JPanel rightHelper;

	int displayQuantity;
	
	// constructor
	public MenuWindow(Order order) {
		this.order = order;
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}

	// put all the components to the background panel
	private void initializePanelToFrame() {
		backgroundPanel = new JPanel();
		backgroundPanel.setPreferredSize(new Dimension(375, 812));
		backgroundPanel.setLayout(new BorderLayout());

		configureMainConent();
		configureHeader();
		configureCartPanel();
		addListeners();
		backgroundPanel.add(BorderLayout.SOUTH, cartPanel);
		backgroundPanel.add(BorderLayout.CENTER, mainContent);
		backgroundPanel.add(BorderLayout.NORTH, header);

		// set the content to the background panel that contains all the components
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
		
		main.addActionListener(new CategoryButtonListener());
		appetizers.addActionListener(new CategoryButtonListener());
		coffee.addActionListener(new CategoryButtonListener());
		dessert.addActionListener(new CategoryButtonListener());
		drinks.addActionListener(new CategoryButtonListener());
		salads.addActionListener(new CategoryButtonListener());
		breakfast.addActionListener(new CategoryButtonListener());
		
		backButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(displayQuantity > 0) {
					int selectedOption = JOptionPane.showConfirmDialog(null, TextResources.cancelOrder, TextResources.cancelOrderTitle,
							JOptionPane.YES_NO_OPTION);
					if (selectedOption == 0) {
						order.clearOrder();
						dispose();
						new MainWindow();
					}
				}
				else {
					dispose();
					new MainWindow();
				}
			}
		});
		
		cartPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new CartWindow(order);
			}
		});
		
		search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				List<Product> foundProducts = new Search().expoSearch(Menu.getAllProducts(),  search.getText());
				refreshMaincontent(foundProducts);				
			}
		});
	}

	// all the content of the main panel (CENTER panel)
	public void configureMainConent() {
		mainContent = new JPanel();
		mainContent.setPreferredSize(new Dimension(360, 468));
		mainContent.setLayout(null);

		explore = LabelFactory.createLabel(TextResources.explore, Color.BLACK, FontFactory.poppins(16));
		main = ButtonFactory.createButton(TextResources.main, FontFactory.poppins(14), ColorResources.main,
				Color.BLACK);
		salads = ButtonFactory.createButton(TextResources.salads, FontFactory.poppins(14), ColorResources.salads,
				Color.BLACK);
		;
		dessert = ButtonFactory.createButton(TextResources.desserts, FontFactory.poppins(14), ColorResources.desserts,
				Color.BLACK);
		;
		appetizers = ButtonFactory.createButton(TextResources.appetizers, FontFactory.poppins(14),
				ColorResources.appetizer, Color.BLACK);
		;
		breakfast = ButtonFactory.createButton(TextResources.breakfast, FontFactory.poppins(14),
				ColorResources.breakfast, Color.BLACK);
		;
		drinks = ButtonFactory.createButton(TextResources.drinks, FontFactory.poppins(14), ColorResources.drinks,
				Color.BLACK);
		;
		coffee = ButtonFactory.createButton(TextResources.coffee, FontFactory.poppins(14), ColorResources.coffee,
				Color.BLACK);
		;

		explore.setBounds(16, 30, 280, 40);
		appetizers.setBounds(23, 85, 150, 62);
		main.setBounds(203, 85, 150, 62);
		salads.setBounds(23, 177, 150, 62);
		dessert.setBounds(203, 177, 150, 62);
		coffee.setBounds(23, 269, 150, 62);
		drinks.setBounds(203, 269, 150, 62);
		breakfast.setBounds(118, 361, 150, 62);

		mainContent.add(explore);
		mainContent.add(appetizers);
		mainContent.add(main);
		mainContent.add(salads);
		mainContent.add(dessert);
		mainContent.add(coffee);
		mainContent.add(drinks);
		mainContent.add(breakfast);
	}

	public void refreshMaincontent(List<Product> products) {

		backgroundPanel.remove(mainContent);
		
		configureSearchContent(products);
		leftHelper  = new JPanel();
		leftHelper.setPreferredSize(new Dimension(16, mainContent.getWidth()));
		
		rightHelper = new JPanel();
		rightHelper.setPreferredSize(new Dimension(19, mainContent.getWidth()));
		
		backgroundPanel.add(BorderLayout.WEST, leftHelper);
		backgroundPanel.add(BorderLayout.EAST, rightHelper);
		backgroundPanel.add(BorderLayout.SOUTH, cartPanel);
		backgroundPanel.add(BorderLayout.CENTER, mainContent);
		revalidate();
		this.pack();
	}
	
	public void configureSearchContent(List<Product> products) {
		mainContent = new JPanel();
		mainContent.setLayout(new BorderLayout());
		mainContent.add(createVerticalScrollablePanel(products));
	}
	public JPanel configureProductPanelSearched(Product product) {
		Food foodProduct;
		
		JPanel panel = new JPanel();
		panel.setName(product.getName());
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(325, 120));
		panel.setBackground(Color.white);

		ImageIcon searchProductImage = new ImageIcon(product.getPath());
		JLabel searchProductimgLabel = LabelFactory.createIconLabel(searchProductImage);
		searchProductimgLabel.setBounds(10, 10, 100, 100);

		JLabel searchTitleLabel = LabelFactory.createLabel(product.getName(), Color.BLACK, FontFactory.poppins(14));
		searchTitleLabel.setBounds(120, 20, 200, 17);

		JLabel searchDescLabel = LabelFactory.createLabel(product.getDescription(), Color.GRAY, FontFactory.poppins(12));
		searchDescLabel.setBounds(120, 35, 200, 40);
		
		ImageIcon startIcon =  new ImageIcon("./Icons/star-rating.png");
		JLabel starLabel = LabelFactory.createIconLabel(startIcon);
		starLabel.setBounds(280, 0, 50, 50);
		starLabel.setText(product.calcAvRating()+"");
		
		ImageIcon searchPlusIcon = new ImageIcon("./buttonImages/plus.png");

		JLabel plusButtonLabel;
		plusButtonLabel = LabelFactory.createIconLabel(searchPlusIcon);
		plusButtonLabel.setBounds(285, 85, 24, 24);
		plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		plusButtonLabel.addMouseListener(new plusButtonListener());
		
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

		panel.add(searchProductimgLabel);
		panel.add(searchTitleLabel);
		panel.add(searchDescLabel);
		panel.add(plusButtonLabel);
		panel.add(priceLabel);
		panel.add(starLabel);
		return panel;
	}
	public JScrollPane createVerticalScrollablePanel(List<Product> products) {
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(products.size(), 1, 0, 8));

		for (Product product : products) {
			container.add(configureProductPanelSearched(product));
		}

		JScrollPane scrollPane = new JScrollPane(container);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);
		scrollBar.setUnitIncrement(16);
		scrollBar.setPreferredSize(new Dimension(0,0));
		scrollPane.setVerticalScrollBar(scrollBar);
		return scrollPane;
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

		priceCartLabel = LabelFactory.createLabel(order.calcCost() + "€", ColorResources.frPopup, FontFactory.poppins(14));
		priceCartLabel.setBounds(301, cartPanel.getHeight() / 2 - 15, 49, 20);

		viewCart = LabelFactory.createLabel(TextResources.viewCart, ColorResources.frPopup, FontFactory.poppins(14));
		viewCart.setBounds(67, cartPanel.getHeight() / 2 - 15, 200, 20);

		bagLabel = LabelFactory.createIconLabel(bagIcon);
		bagLabel.setText(order.getQuantity() + "");
		bagLabel.setBounds(18, cartPanel.getHeight() / 2 - 25, 50, 40);
		bagLabel.setForeground(Color.RED);

		cartPanel.add(bagLabel);
		cartPanel.add(viewCart);
		cartPanel.add(priceCartLabel);
	}

	// all the content for the header panel
	public void configureHeader() {
		header = new JPanel();
		header.setPreferredSize(new Dimension(375, 265));
		header.setLayout(null);
		String path = "buttonImages/Back Button";
		String lang = TextResources.imageLang;
		ImageIcon backImage = new ImageIcon(path + lang);
		backButton = ButtonFactory.createButtonIcon(backImage);
		backButton.setBounds(16, 30, 63, 26);

		search = TextFieldFactory.createTextField(TextResources.search, Color.white, Color.GRAY,
				FontFactory.poppins(14));
		search.setBounds(16, 70, 340, 30);

		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBounds(16, 140, 340, 200);
		// container contains the scrollable panel
		container.add(createHorizontalScrollablePanel(LoginWindow.deals));

		dealsOfTheDay = LabelFactory.createLabel(TextResources.deals, Color.BLACK, FontFactory.poppins(16));
		dealsOfTheDay.setBounds(16, 110, 200, 30);

		header.add(backButton);
		header.add(search);
		header.add(dealsOfTheDay);
		header.add(container);
	}

	// creates a horizontal scrollable panel
	public JScrollPane createHorizontalScrollablePanel(ArrayList<Food> products) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, products.size(), 8, 0));

		for (Food product : products) {
			panel.add(configureProductPanel(product));
		}

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		scrollBar.setUnitIncrement(16);
		scrollBar.setPreferredSize(new Dimension(0,0));
		scrollPane.setHorizontalScrollBar(scrollBar);
		return scrollPane;
	}

	// layout display for the products
	public JPanel configureProductPanel(Food product) {
		
		JPanel	productPanel = new JPanel();
		productPanel.setName(String.valueOf(product.getId()));
		productPanel.setLayout(null);
		productPanel.setPreferredSize(new Dimension(260, 50));
		productPanel.setBackground(Color.white);

		ImageIcon productImg = new ImageIcon(product.getPath());
		JLabel productimgLabel = LabelFactory.createIconLabel(productImg);
		productimgLabel.setBounds(10, 10, 100, 100);

		JLabel titleLabel = LabelFactory.createLabel(product.getName(), Color.BLACK, FontFactory.poppins(14));
		titleLabel.setBounds(120, 8, 160, 20);

		JLabel descLabel = LabelFactory.createLabel(product.getDescription(), Color.GRAY, FontFactory.poppins(12));
		descLabel.setBounds(120, 25, 150, 50);

		JLabel prevPrice = new JLabel("<html><body><span style='text-decoration: line-through;'>" + (product.getPrice() + product.getDiscount())+ "€</span></body></html>");
		prevPrice.setBounds(163, 85, 43, 19);

		ImageIcon plusIcon = new ImageIcon("./buttonImages/plus.png");
		JLabel plusButtonLabel;
		plusButtonLabel = new JLabel();
		plusButtonLabel.setIcon(plusIcon);
		plusButtonLabel.setBounds(200, 80, 24, 24);
		plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		plusButtonLabel.addMouseListener(new plusButtonListener());

		JLabel newPrice = LabelFactory.createLabel(product.getPrice() + "€", Color.RED, FontFactory.poppins(14));
		newPrice.setBounds(120, 85, 43, 19);

		productPanel.add(productimgLabel);
		productPanel.add(titleLabel);
		productPanel.add(descLabel);
		productPanel.add(plusButtonLabel);
		productPanel.add(prevPrice);
		productPanel.add(newPrice);
		return productPanel;
	}

	class CategoryButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			JButton btn = (JButton) e.getSource();
			new CategoryWindow(btn.getBackground(), btn.getText(), Menu.getProductList(btn.getText()), order);
		}
		
	}
	
	class plusButtonListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			
				JLabel plusLabel = (JLabel) e.getSource();
				JPanel parent = (JPanel) plusLabel.getParent();
				String productId = parent.getName();
				Product clickedProduct = Menu.findProduct(Integer.parseInt(productId));
				order.addProduct(clickedProduct);
				priceCartLabel.setText(String.valueOf(order.calcCost()) + "€");
				displayQuantity = 0;
				for(Product p: order.getProd().keySet()) {
					displayQuantity += order.getProd().get(p);
				}
				bagLabel.setText(displayQuantity + "");
		}
	}

}
