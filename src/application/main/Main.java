package application.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.factory.MenuHeader;
import gui.factory.ViewCartPanel;
import gui.windows.LoginWindow;
import order.Food;
import order.Product;
import resources.TextResources;

public class Main {

	public static void main(String[] args) {
//		new LoginWindow();
		new TextResources().changeLanguage();
		ArrayList<Product> p = new ArrayList<>();
		p.add(new Food("Test", "this is a test", 9.00, "Food Menu/appetizers/salty cheesecake.jpeg"));
		p.add(new Food("Test", "this is a test", 9.00, "Food Menu/appetizers/salty cheesecake.jpeg"));
		p.add(new Food("Test", "this is a test", 9.00, "Food Menu/appetizers/salty cheesecake.jpeg"));
		
		
		JFrame frame = new JFrame();
		
		JPanel bgPanel = new JPanel();
		bgPanel.setPreferredSize(new Dimension(375, 812));
		bgPanel.setLayout(new BorderLayout());
		bgPanel.add(BorderLayout.SOUTH, new ViewCartPanel());
		bgPanel.add(BorderLayout.NORTH, new MenuHeader(p));
				
		
		frame.setContentPane(bgPanel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.pack();
	}
}
