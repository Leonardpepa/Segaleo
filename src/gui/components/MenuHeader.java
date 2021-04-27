package gui.components;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import gui.factory.*;
import gui.windows.MainWindow;
import order.Product;
import resources.TextResources;

public class MenuHeader extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton backButton;
	private JTextField search;
	private JPanel container;
	private JLabel dealsOfTheDay;
	private ArrayList<Product> deals;
	private JFrame frame;
	
	public MenuHeader(JFrame frame, ArrayList<Product> deals) {
		this.frame = frame;
		this.deals = deals;
		this.setPreferredSize(new Dimension(375, 260));
		this.setLayout(null);

		configureComponents();

		container.setLayout(new BorderLayout());
		container.add(new ScrollablePanel().createHorizontal(deals));

		this.add(backButton);
		this.add(search);
		this.add(dealsOfTheDay);
		this.add(container);
	}

	public void configureComponents() {
		backButton = ButtonFactory.createButtonIcon(new ImageIcon("./buttonImages/Back Button.png"));
		backButton.setBounds(16, 30, 63, 23);
		backButton.addActionListener(this);

		search = TextFieldFactory.createTextField(TextResources.search, Color.white, Color.GRAY,
				FontFactory.poppins(14));
		search.setBounds(16, 70, 340, 30);

		container = new JPanel();
		container.setBounds(16, 140, 340, 200);

		dealsOfTheDay = LabelFactory.createLabel(TextResources.deals, Color.BLACK, FontFactory.poppins(16));
		dealsOfTheDay.setBounds(16, 110, 200, 30);
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public JTextField getSearch() {
		return search;
	}

	public void setSearch(JTextField search) {
		this.search = search;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			frame.dispose();
			new MainWindow();
		}
	}
	
	

}
