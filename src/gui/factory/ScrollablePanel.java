package gui.factory;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import order.Product;

public class ScrollablePanel extends JPanel{
	
	JPanel container = new JPanel();
	
	public JScrollPane fill(ArrayList<Product> products) {
		container.setLayout(new GridLayout(products.size(),1,0,8));
		for(Product product: products) {
			container.add(new ProductCard().createPanel(product));
		}
		JScrollPane scrollPane = new JScrollPane(container);
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		return scrollPane;
	}
}
