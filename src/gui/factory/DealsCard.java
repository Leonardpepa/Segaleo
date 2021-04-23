package gui.factory;

import java.awt.*;


import javax.swing.*;
import order.Product;

public class DealsCard extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon productImg;
	private JLabel productimgLabel;
	private JLabel titleLabel;
	private JLabel descLabel;
	private JLabel prevPrice;
	private ImageIcon plusIcon;
	private JLabel plusButtonLabel = new JLabel();
	private JLabel newPrice;
	private Product product;
	
	public  JPanel createPanel(Product product) {
		this.product = product;
		
		this.setLayout(null);
		this.setPreferredSize(new Dimension(250,100));
		this.setBackground(Color.white);
//		this.setBorder(new RoundedBorder(10));
		configureComponents();
		
		this.add(productimgLabel);
		this.add(titleLabel);
		this.add(descLabel);
		this.add(plusButtonLabel);
		this.add(prevPrice);
		this.add(newPrice);
		return this;
	}
	
	public void configureComponents() {
		ImageIcon productImg = new ImageIcon(product.getPath());
		productimgLabel = LabelFactory.createIconLabel(productImg);
		productimgLabel.setBounds(10, 10, 100, 100);
		
		
		titleLabel = LabelFactory.createLabel(product.getName(), Color.BLACK, FontFactory.poppins(14));
		titleLabel.setBounds(120,20,150,17);
		
		descLabel = LabelFactory.createLabel(product.getDescription(), Color.GRAY, FontFactory.poppins(12));
		descLabel.setBounds(120, 35, 150, 17);

		prevPrice = LabelFactory.createLabel(product.getPrice() + "$", Color.BLACK, FontFactory.lineThrough(14));
		prevPrice.setBounds(195, 60, 43, 19);
		
		plusIcon = new ImageIcon("./buttonImages/plus.png");
		plusButtonLabel.setIcon(plusIcon);
		plusButtonLabel.setBounds(200, 80, 24, 24);
		plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		newPrice = LabelFactory.createLabel((product.getPrice() - 3) + "$", Color.RED, FontFactory.poppins(14));
		newPrice.setBounds(120, 60, 43,19);
		
	}
		
	
}
