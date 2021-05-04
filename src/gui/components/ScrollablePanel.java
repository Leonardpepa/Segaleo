package gui.components;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import order.Product;

public class ScrollablePanel extends JPanel{
	
	JPanel container = new JPanel();
	
	public JScrollPane createVertical(ArrayList<Product> products) {
		
		container.setLayout(new GridLayout(products.size(),1,0,8));
		
		for(Product product: products) {
			container.add(new ProductCard().createPanel(product));
		}
		
		JScrollPane scrollPane = new JScrollPane(container);
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		
		return scrollPane;
	}
	
	public JScrollPane createHorizontal(ArrayList<Product> products) {
			
		container.setLayout(new GridLayout(1,products.size(),8,0));
			
		for (Product product: products) {
				container.add(new DealsCard().createPanel(product));
			}
			
			JScrollPane scrollPane = new JScrollPane(container);
			scrollPane.setBorder(new EmptyBorder(0,0,0,0));
			scrollPane.getHorizontalScrollBar().setUnitIncrement(15);
			
			return scrollPane;
			}
	
}
