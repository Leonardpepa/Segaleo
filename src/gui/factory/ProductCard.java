package gui.factory;

import java.awt.*;
import javax.swing.*;

public class ProductCard extends JPanel{
	
	ImageIcon productImage = new ImageIcon("src\\Mozzarella.jpg");
	JLabel productimgLabel = LabelFactory.createIconLabel(productImage);
	JLabel titleLabel = LabelFactory.createLabel("Mozzarella bufala ", Color.BLACK, FontFactory.poppins(14));
	JLabel descLabel = LabelFactory.createLabel("with basil and tomato", Color.GRAY, FontFactory.poppins(12));
	JLabel priceLabel = new JLabel("28,00$");
	ImageIcon plusIcon = new ImageIcon("src\\plus.png");
	JLabel plusButtonLabel = new JLabel();
	
	public  JPanel createPanel() {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(300,120));
		this.setBackground(Color.white);
		
		productimgLabel.setBounds(10, 10, 100, 100);
		
		titleLabel.setBounds(141,20,150,17);
		
		descLabel.setBounds(141, 35, 150, 17);
		
		plusButtonLabel.setIcon(plusIcon);
		plusButtonLabel.setBounds(295, 95, 24, 24);
		plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		priceLabel.setBounds(288, 80, 43, 19);
		
		this.add(productimgLabel);
		this.add(titleLabel);
		this.add(descLabel);
		this.add(plusButtonLabel);
		this.add(priceLabel);
		return this;
	}
	
	
	
}
