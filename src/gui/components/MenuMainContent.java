package gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.factory.*;
import gui.windows.CategoryWindow;
import menu.Menu;
import resources.ColorResources;
import resources.TextResources;


public class MenuMainContent extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel explore;
	private JButton main;
	private JButton salads;
	private JButton dessert;
	private JButton appetizers;
	private JButton breakfast;
	private JButton drinks;
	private JButton coffee;
	private JFrame frame;
	private Menu menu;
	
	public MenuMainContent(JFrame frame, Menu menu) {
		this.frame = frame;
		this.menu = menu;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(360, 468));
	
		configureComponents();
		addListeners();
		this.add(explore);
		this.add(appetizers);
		this.add(main);
		this.add(salads);
		this.add(dessert);
		this.add(coffee);
		this.add(drinks);
		this.add(breakfast);
	}
	
	public void configureComponents() {
		
		explore = LabelFactory.createLabel(TextResources.explore, Color.BLACK, FontFactory.poppins(16));
		main = ButtonFactory.createButton(TextResources.main, FontFactory.poppins(14), ColorResources.main, Color.BLACK);
		salads = ButtonFactory.createButton(TextResources.salads, FontFactory.poppins(14), ColorResources.salads, Color.BLACK);;
		dessert = ButtonFactory.createButton(TextResources.desserts , FontFactory.poppins(14), ColorResources.desserts, Color.BLACK);;
		appetizers = ButtonFactory.createButton(TextResources.appetizers, FontFactory.poppins(14), ColorResources.appetizer, Color.BLACK);;
		breakfast = ButtonFactory.createButton(TextResources.breakfast, FontFactory.poppins(14), ColorResources.breakfast, Color.BLACK);;
		drinks = ButtonFactory.createButton(TextResources.drinks, FontFactory.poppins(14), ColorResources.drinks, Color.BLACK);;
		coffee = ButtonFactory.createButton(TextResources.coffee, FontFactory.poppins(14), ColorResources.coffee, Color.BLACK);;
		
		explore.setBounds(16, 30, 250, 30);	
		appetizers.setBounds(23, 85, 150, 62);
		main.setBounds(203,85,150,62);
		salads.setBounds(23, 177, 150,62);
		dessert.setBounds(203, 177, 150,62);
		coffee.setBounds(23, 269, 150,62);
		drinks.setBounds(203, 269, 150,62);
		breakfast.setBounds(118, 361, 150, 62);
	}
	
	public void addListeners() {
		appetizers.addActionListener(this);
		main.addActionListener(this);
		salads.addActionListener(this);
		dessert.addActionListener(this);
		coffee.addActionListener(this);
		drinks.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		JButton btn = (JButton) e.getSource();
		new CategoryWindow(btn.getBackground(), btn.getText(), menu.getProductList(btn.getText()));
	}

}
