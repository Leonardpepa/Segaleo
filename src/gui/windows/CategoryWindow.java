package gui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.components.Header;
import gui.components.MenuHeader;
import gui.components.MenuMainContent;
import gui.components.ScrollablePanel;
import gui.components.ViewCartPanel;
import menu.Menu;
import order.Product;

public class CategoryWindow extends JFrame{
	
	private JPanel containerPanel;
	private Header header;
	private JScrollPane mainContent;
	private ViewCartPanel viewCart;
	private Menu menu;
	private Color categoryColor;
	private String categoryName;
	private ArrayList<Product> products;
	
	
	public CategoryWindow(Color categoryColor, String categoryName, ArrayList<Product> products) {
		this.categoryColor = categoryColor;
		this.categoryName = categoryName;
		this.products = products;
		initilizePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}
	private void initilizePanelToFrame() {
		containerPanel = new JPanel();
		containerPanel.setPreferredSize(new Dimension(375, 812));
		containerPanel.setLayout(new BorderLayout());
		
		header = new Header(categoryName, categoryColor, this);
		mainContent = new ScrollablePanel().createVertical(products);
		viewCart = new ViewCartPanel();
		
		containerPanel.add(BorderLayout.SOUTH, viewCart);
		containerPanel.add(BorderLayout.CENTER, mainContent);
		containerPanel.add(BorderLayout.NORTH, header);
		
		this.setContentPane(containerPanel);
		this.pack();
	}
	//settings for the frame
	public void windowsConfiguration() {
		this.setTitle("Segaleo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	private void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}
	
	
	
}
