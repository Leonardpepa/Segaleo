package gui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import gui.factory.TextFieldFactory;
import resources.ColorResources;
import resources.TextResources;

public class CartWindow extends JFrame{
	
	private JPanel backgroundPanel;
	
	//header
	private JPanel header;
	private ImageIcon exitIcon = new ImageIcon("buttonImages/exit button.png");
	private JButton exitButton;
	private JLabel myCartLabel;
	
	//footer
	JPanel footer;
	private JTextField couponField;
	private JButton submitCouponButton;
	private JButton paymentMethods;
	private JLabel totalLabel;
	private JLabel priceLabel;
	private JPanel priceHolder;
	private JButton orderNowButton;
	
	
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
		configureFooter();
		
		backgroundPanel.add(BorderLayout.NORTH, header);
		backgroundPanel.add(BorderLayout.SOUTH, footer);
		
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
	
	
	public void configureFooter(){
		footer = new JPanel();
		footer.setLayout(null);
		footer.setPreferredSize(new Dimension(375, 290));
		footer.setBackground(new Color(244, 249, 250));
		
		couponField = TextFieldFactory.createTextField(TextResources.couponCode, new Color(216, 223, 224), Color.LIGHT_GRAY, FontFactory.poppins(14));
		couponField.setBounds(24,10, 195, 48);
		
		submitCouponButton = ButtonFactory.createButton(TextResources.submit, FontFactory.poppins(14), new Color(216, 223, 224), Color.BLACK);
		submitCouponButton.setBounds(231, 10, 121, 48);
		
		paymentMethods = ButtonFactory.createButton(TextResources.payment, FontFactory.poppins(14), Color.LIGHT_GRAY, Color.BLACK);
		paymentMethods.setBounds(24, 68, 328, 63);
		
		priceHolder = new JPanel();
		priceHolder.setLayout(null);
		priceHolder.setPreferredSize(new Dimension(328, 63));
		priceHolder.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.WHITE));
		priceHolder.setBackground(Color.WHITE);
		
		totalLabel = LabelFactory.createLabel(TextResources.total, Color.BLACK, FontFactory.poppins(14));
		priceLabel = LabelFactory.createLabel("17.00", Color.BLACK, FontFactory.poppins(14));
		totalLabel.setBounds(23, 18, 100, 20);
		priceLabel.setBounds(264,18, 50, 20);
		
		priceHolder.setBounds(24, 141, 328, 63);
		
		orderNowButton = ButtonFactory.createButton(TextResources.orderNow, FontFactory.poppins(15), ColorResources.frMainWindowBtn, Color.WHITE);
		orderNowButton.setBounds(24, 214, 328, 41);
		priceHolder.add(totalLabel);
		priceHolder.add(priceLabel);
		
		footer.add(couponField);
		footer.add(submitCouponButton);
		footer.add(paymentMethods);
		footer.add(priceHolder);
		footer.add(orderNowButton);
	}
	
	

}
