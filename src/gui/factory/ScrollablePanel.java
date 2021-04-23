package gui.factory;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.desktop.ScreenSleepListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
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
		return scrollPane;
	}
}
