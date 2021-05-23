package gui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import gui.factory.TextFieldFactory;
import gui.components.*;
import menu.Menu;
import order.Product;
import resources.ColorResources;
import resources.TextResources;

public class MenuWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// menu object for access to the products
	// TODO this have to be passed as argument
	private Menu menu;

	// contains all the panels for the whole gui window
	private JPanel backgroundPanel;

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

	// display product layout
	private JPanel productPanel;
	private ImageIcon productImg;
	private JLabel productimgLabel;
	private JLabel titleLabel;
	private JLabel descLabel;
	private JLabel prevPrice;
	private ImageIcon plusIcon;
	private JLabel plusButtonLabel;
	private JLabel newPrice;
	private Random rand = new Random();

	// header
	private JPanel header;
	private JButton backButton;
	private JTextField search;
	private JPanel container;
	private JLabel dealsOfTheDay;
	private ArrayList<Product> deals;

	// cart panel
	private JPanel cartPanel;
	private ImageIcon bagIcon;
	private JLabel bagLabel;
	private JLabel viewCart;
	private JLabel priceLabel;

	// constructor
	public MenuWindow() {
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}

	// put all the components to the background panel
	private void initializePanelToFrame() {
		menu = new Menu();
		backgroundPanel = new JPanel();
		backgroundPanel.setPreferredSize(new Dimension(375, 812));
		backgroundPanel.setLayout(new BorderLayout());

		configureMainConent();
		configureHeader();
		configureCartPanel();
		addListeners();
		backgroundPanel.add(BorderLayout.SOUTH, cartPanel);
		backgroundPanel.add(BorderLayout.NORTH, header);
		backgroundPanel.add(BorderLayout.CENTER, mainContent);

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
		backButton.addActionListener(this);
		main.addActionListener(this);
		appetizers.addActionListener(this);
		coffee.addActionListener(this);
		dessert.addActionListener(this);
		drinks.addActionListener(this);
		salads.addActionListener(this);
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

		explore.setBounds(16, 30, 250, 30);
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

	// all the content for the cart panel
	public void configureCartPanel() {
		cartPanel = new JPanel();

		cartPanel.setLayout(null);
		cartPanel.setPreferredSize(new Dimension(375, 94));
		cartPanel.setBounds(0, 718, 375, 94);
		cartPanel.setBackground(Color.WHITE);
		cartPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

		bagIcon = new ImageIcon("Icons/Bag.png");

		priceLabel = LabelFactory.createLabel("28,00", ColorResources.frPopup, FontFactory.poppins(14));
		priceLabel.setBounds(301, cartPanel.getHeight() / 2 - 15, 49, 20);

		viewCart = LabelFactory.createLabel(TextResources.viewCart, ColorResources.frPopup, FontFactory.poppins(14));
		viewCart.setBounds(67, cartPanel.getHeight() / 2 - 15, 200, 20);

		bagLabel = LabelFactory.createIconLabel(bagIcon);
		bagLabel.setBounds(29, cartPanel.getHeight() / 2 - 15, 25, 25);

		cartPanel.add(bagLabel);
		cartPanel.add(viewCart);
		cartPanel.add(priceLabel);
	}

	// all the content for the header panel
	public void configureHeader() {
		header = new JPanel();
		header.setPreferredSize(new Dimension(375, 260));
		header.setLayout(null);
		backButton = ButtonFactory.createButtonIcon(new ImageIcon("./buttonImages/Back Button.png"));
		backButton.setBounds(16, 30, 63, 23);

		search = TextFieldFactory.createTextField(TextResources.search, Color.white, Color.GRAY,
				FontFactory.poppins(14));
		search.setBounds(16, 70, 340, 30);

		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBounds(16, 140, 340, 200);
		// container contains the scrollable panel
		container.add(createHorizontalScrollablePanel(menu.getMain()));

		dealsOfTheDay = LabelFactory.createLabel(TextResources.deals, Color.BLACK, FontFactory.poppins(16));
		dealsOfTheDay.setBounds(16, 110, 200, 30);

		header.add(backButton);
		header.add(search);
		header.add(dealsOfTheDay);
		header.add(container);
	}

	// creates a horizontal scrollable panel
	public JScrollPane createHorizontalScrollablePanel(ArrayList<Product> products) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, products.size(), 8, 0));

		for (Product product : products) {
			panel.add(configureProductPanel(product));
		}

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.getHorizontalScrollBar().setUnitIncrement(15);
		return scrollPane;
	}

	// layout display for the products
	public JPanel configureProductPanel(Product product) {
		productPanel = new JPanel();

		productPanel.setLayout(null);
		productPanel.setPreferredSize(new Dimension(250, 100));
		productPanel.setBackground(Color.white);

		ImageIcon productImg = new ImageIcon(product.getPath());
		productimgLabel = LabelFactory.createIconLabel(productImg);
		productimgLabel.setBounds(10, 10, 100, 100);

		titleLabel = LabelFactory.createLabel(product.getName(), Color.BLACK, FontFactory.poppins(14));
		titleLabel.setBounds(120, 20, 150, 17);

		descLabel = LabelFactory.createLabel(product.getDescription(), Color.GRAY, FontFactory.poppins(12));
		descLabel.setBounds(120, 35, 150, 17);

		prevPrice = LabelFactory.createLabel(product.getPrice() + "€", Color.BLACK, FontFactory.lineThrough(14));
		prevPrice.setBounds(195, 60, 43, 19);

		plusIcon = new ImageIcon("./buttonImages/plus.png");
		plusButtonLabel = new JLabel();
		plusButtonLabel.setIcon(plusIcon);
		plusButtonLabel.setBounds(200, 80, 24, 24);
		plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

		int discount = rand.nextInt(3) + 1;
		newPrice = LabelFactory.createLabel((product.getPrice() - discount) + "€", Color.RED, FontFactory.poppins(14));
		newPrice.setBounds(120, 60, 43, 19);

		productPanel.add(productimgLabel);
		productPanel.add(titleLabel);
		productPanel.add(descLabel);
		productPanel.add(plusButtonLabel);
		productPanel.add(prevPrice);
		productPanel.add(newPrice);
		return productPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			this.dispose();
			new MainWindow();
		} 
		else {
			this.dispose();
			JButton btn = (JButton) e.getSource();
			new CategoryWindow(btn.getBackground(), btn.getText(), menu.getProductList(btn.getText()));
		}
	}

}
