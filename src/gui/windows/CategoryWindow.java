package gui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;

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

public class CategoryWindow extends JFrame {

	/*	
	 * 	this class holds the content of each product category, the cart that show the price and the header tha show the sorting and searching
	 */
	private static final long serialVersionUID = -4277445545184372578L;

	private JPanel backgroundPanel;

	private Order order;

	private Color categoryColor;
	private String categoryName;
	private List<Product> categoryProducts;

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
	
	private JPanel leftHelper;
	private JPanel rightHelper;

	public CategoryWindow(Color categoryColor, String categoryName, List<Product> products, Order order) {
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
		
		//helpers are for the layout nothing more
		leftHelper  = new JPanel();
		leftHelper.setPreferredSize(new Dimension(16, mainContent.getWidth()));
		
		rightHelper = new JPanel();
		rightHelper.setPreferredSize(new Dimension(19, mainContent.getWidth()));
		
		backgroundPanel.add(BorderLayout.WEST, leftHelper);
		backgroundPanel.add(BorderLayout.EAST, rightHelper);
		backgroundPanel.add(BorderLayout.NORTH, header);
		backgroundPanel.add(BorderLayout.CENTER, mainContent);
		backgroundPanel.add(BorderLayout.SOUTH, cartPanel);

		this.setContentPane(backgroundPanel);
		this.pack();
	}

	
	//refresh the page when the user search something or sorts the products
	//it removes the main panel that contains the content with the products
	//the calls the method to configure new content and adds it back to the background panel
	public void refreshMaincontent(List<Product> products) {
		backgroundPanel.remove(mainContent);
		mainContent = new JPanel();
		configureMainContent(products);
		backgroundPanel.add(BorderLayout.WEST, leftHelper);
		backgroundPanel.add(BorderLayout.EAST, rightHelper);
		backgroundPanel.add(BorderLayout.CENTER, mainContent);
		backgroundPanel.add(BorderLayout.SOUTH, cartPanel);
		revalidate();
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
	
	//each component has its  own listener
	public void addListeners() {
		backButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MenuWindow(order);
			}
		});
		
		cartPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new CartWindow(order);
			}
			
		});
		
		compobox.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				//checks the users input from the sort choices and calls the sorting methods that suits
				//in the end calls the method that refresh the page
				@SuppressWarnings("unchecked")
				JComboBox<String> choices = (JComboBox<String>) e.getSource();
				String selectedSortMethod = (String) choices.getSelectedItem();
				if(selectedSortMethod.equals(TextResources.byName)) {
					Collections.sort(categoryProducts, Sort.prodNameComparator);					
				}
				else if(selectedSortMethod.equals(TextResources.byPrice)) {
					Collections.sort(categoryProducts, Sort.AscProdPriceComparator);					
				}
				else if(selectedSortMethod.equals(TextResources.byPopularity)){
					Collections.sort(categoryProducts, Sort.ProdRatingComparator);					
				}else {
					return;
				}
				refreshMaincontent(categoryProducts);
			}
		});
		search.addKeyListener(new KeyAdapter() {
			
			//for each key the users type we call the search method and we refresh the page
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				List<Product> foundProducts = new Search().expoSearch(categoryProducts, search.getText());
				refreshMaincontent(foundProducts);
			}
		});
	}

	// all the content for the header panel
	public void configureHeader() {
		String[] sorts = { TextResources.sort, TextResources.byPrice, TextResources.byPopularity, TextResources.byName };
		headerText = categoryName;
		headerLabelColor = categoryColor;
		header = new JPanel();

		header.setPreferredSize(new Dimension(375, 200));
		header.setLayout(null);

		compobox = new JComboBox<>(sorts);
		compobox.setBounds(200, 119, 156, 20);

		String path = "buttonImages/Back Button";
		String lang = TextResources.imageLang;
		ImageIcon backImage = new ImageIcon(path + lang);
		backButton = ButtonFactory.createButtonIcon(backImage);
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
		bagLabel.setText(order.getQuantity()+ "");
		bagLabel.setBounds(18, cartPanel.getHeight() / 2 - 25, 50, 40);
		bagLabel.setForeground(Color.RED);

		cartPanel.add(bagLabel);
		cartPanel.add(viewCart);
		cartPanel.add(cartPriceLabel);
	}

	// main panel
	public void configureMainContent(List<Product> products) {
		mainContent = new JPanel();
		mainContent.setLayout(new BorderLayout());
		//adds the panel that can be scrollable if the content is to big
		mainContent.add(createVerticalScrollablePanel(products));
	}

	// layout display for the products
	public JPanel configureProductPanel(Product product) {
		Food foodProduct;

		JPanel panel = new JPanel();
		panel.setName(String.valueOf(product.getId()));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(325, 120));
		panel.setBackground(Color.white);

		ImageIcon productImage = new ImageIcon(product.getPath());
		JLabel productimgLabel = LabelFactory.createIconLabel(productImage);
		productimgLabel.setBounds(10, 10, 100, 100);

		JLabel titleLabel = LabelFactory.createLabel(product.getName(), Color.BLACK, FontFactory.poppins(14));
		titleLabel.setBounds(120, 10, 200, 20);

		JLabel descLabel = LabelFactory.createLabel(product.getDescription(), Color.GRAY, FontFactory.poppins(12));
		descLabel.setBounds(120, 30, 200, 50);
		
		ImageIcon startIcon =  new ImageIcon("./Icons/star-rating.png");
		JLabel starLabel = LabelFactory.createIconLabel(startIcon);
		starLabel.setBounds(280, 0, 50, 50);
		starLabel.setText(product.calcAvRating()+"");

		ImageIcon plusIcon = new ImageIcon("./buttonImages/plus.png");

		JLabel plusButtonLabel;
		plusButtonLabel = LabelFactory.createIconLabel(plusIcon);
		plusButtonLabel.setBounds(285, 85, 24, 24);
		plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		plusButtonLabel.addMouseListener(new plusButtonListener());

		JLabel priceLabel = LabelFactory.createLabel(product.getPrice() + "€", Color.BLACK, FontFactory.poppins(13));
		//check if the product is type food and if it has discount so we can show the correct price
		if (product instanceof Food) {
			foodProduct = (Food) product;
			if (foodProduct.isHasDiscount()) {
				priceLabel = new JLabel("<html><body><span style='text-decoration: line-through;'>"
						+ (foodProduct.getPrice() + foodProduct.getDiscount()) + "€</span></body></html>");
				JLabel newPrice = LabelFactory.createLabel(foodProduct.getPrice() + "€", Color.red,
						FontFactory.poppins(13));
				newPrice.setBounds(245, 65, 43, 19);
				panel.add(newPrice);
			}
		}

		priceLabel.setBounds(286, 65, 43, 19);

		panel.add(productimgLabel);
		panel.add(titleLabel);
		panel.add(descLabel);
		panel.add(plusButtonLabel);
		panel.add(priceLabel);
		panel.add(starLabel);
		return panel;
	}

	// creates a vertical scrollable panel
	public JScrollPane createVerticalScrollablePanel(List<Product> products) {
		JPanel container = new JPanel();
		
		//with the help of the grid layout we make a panel that has many rows and one column
		container.setLayout(new GridLayout(products.size(), 1, 0, 8));

		for (Product product : products) {
			container.add(configureProductPanel(product));
		}

		//the scroll pane makes the panel scrollable
		JScrollPane scrollPane = new JScrollPane(container);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		//the scroll bar used so we can hide it 
		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);
		scrollBar.setUnitIncrement(16);
		scrollBar.setPreferredSize(new Dimension(0,0));
		scrollPane.setVerticalScrollBar(scrollBar);
		return scrollPane;
	}
	
	//input is the product the user wants to add to the cart
	//also changes the state of the cart panel to show the quantity and the price
	class plusButtonListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel plusLabel = (JLabel) e.getSource();
			JPanel parent = (JPanel) plusLabel.getParent();
			String productId = parent.getName();
			Product clickedProduct = Menu.findProduct(Integer.parseInt(productId));
			order.addProduct(clickedProduct);
			cartPriceLabel.setText(String.valueOf(order.calcCost()) + "€");
			bagLabel.setText(order.getQuantity()+ "");
		}
	}

}
