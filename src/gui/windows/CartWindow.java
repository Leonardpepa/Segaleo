package gui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import resources.TextResources;

public class CartWindow extends JFrame{
	
	private JPanel backgroundPanel;
	
	//header
	private JPanel header;
	private ImageIcon exitIcon = new ImageIcon("buttonImages/exit button.png");
	private JButton exitButton;
	private JLabel myCartLabel;
	
	//footer
	private JTextField couponField;
	private JButton submitCouponButton;
	private JButton paymentMethods;
	
	
	
	public CartWindow() {
		
			initilizePanelToFrame();
			windowsConfiguration();
			showWindow(this, true);
		
	}
	
	public void windowsConfiguration() {
		this.setTitle("Segaleo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	
	public void initilizePanelToFrame() {
		backgroundPanel = new JPanel();
		backgroundPanel.setPreferredSize(new Dimension(375, 812));
		backgroundPanel.setLayout(new BorderLayout());
		configureHeader();
		
		backgroundPanel.add(BorderLayout.NORTH, header);
		
		this.setContentPane(backgroundPanel);
		this.pack();
		
	}
	
	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}
	
	
	public void configureHeader() {
		header = new JPanel();
		header.setLayout(null);
		header.setBackground(new Color(244, 249, 250));
		header.setPreferredSize(new Dimension(375, 125));
		
		exitButton = ButtonFactory.createButtonIcon(exitIcon);
		exitButton.setBounds(30, 77, 13, 13);
		
		myCartLabel = LabelFactory.createLabel(TextResources.myCart, Color.BLACK, FontFactory.poppins(20));
		myCartLabel.setBounds(64, 67, 100, 30);
		
		header.add(exitButton);
		header.add(myCartLabel);
	}

}
