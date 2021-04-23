package gui.factory;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import order.Product;

public class HorizontalScrollablePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel container = new JPanel();
	
	public JScrollPane fill(ArrayList<Product> deals) {
		
		container.setLayout(new GridLayout(1,deals.size(),8,0));
		
		for(Product deal: deals) {
			container.add(new DealsCard().createPanel(deal));
		}
		
		JScrollPane scrollPane = new JScrollPane(container);
		scrollPane.setBorder(new EmptyBorder(0,0,0,0));
		scrollPane.getHorizontalScrollBar().setUnitIncrement(15);
		
		return scrollPane;
	}
}
