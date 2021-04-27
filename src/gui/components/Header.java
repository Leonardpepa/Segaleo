package gui.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

import gui.factory.ButtonFactory;
import gui.windows.MenuWindow;

public class Header extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton backButton;
	private JLabel headerLabel;
	private JTextField search;
	private String[] sorts = {"Sort","Sort by price", "Sort by popularity", "Sort by name"};
	private JComboBox<String> compobox;
	private String headerText;
	private Color headerLabelColor;
	private JFrame frame;
	
	public Header(String productCategory, Color color, JFrame frame) {
		headerText = productCategory;
		headerLabelColor = color;
		this.frame = frame;
		
		this.setPreferredSize(new Dimension(375, 200));
		this.setLayout(null);
	
		configureComponents();
		addListeners();
		this.add(backButton);
		this.add(headerLabel);
		this.add(search);
		this.add(compobox);

	}
	
	public void configureComponents() {
		
		compobox = new JComboBox<>(sorts);
		compobox.setBounds(236, 119, 120, 20);
		
		backButton = ButtonFactory.createButtonIcon(new ImageIcon("./buttonImages/Back Button.png"));
		backButton.setBounds(16, 30, 63, 23);
		
		headerLabel = new JLabel(headerText, SwingConstants.CENTER);
		headerLabel.setPreferredSize(new Dimension(100, 100));
		headerLabel.setOpaque(true);
		headerLabel.setBackground(headerLabelColor);
		headerLabel.setBounds(16, 77,150,62);
		
		search = new JTextField("Search");
		search.setBounds(16, 160, 340, 30);
		
	}
	
	public void addListeners() {
		backButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		new MenuWindow();
	}
	
}
