package gui.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import login.Login;
import order.Coupon;
import order.CouponFactory;
import resources.ColorResources;
import resources.TextResources;

public class CompleteOrderWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel insidePanel;
	private JLabel completeTitle;
	private JLabel message;
	private JLabel couponLabel;
	private JLabel couponCode;
	private JLabel orderComplLabel;

	ImageIcon orderComplImage = new ImageIcon("buttonImages/order-complete.png");

	ImageIcon exitImage = new ImageIcon("buttonImages/exit button.png");
	JButton exitBtn;

	public CompleteOrderWindow(boolean needsCoupon) {
		initializePanelToFrame(needsCoupon);
		windowsConfiguration();
		showWindow(this, true);
	}

	public void initializePanelToFrame(boolean needsCoupon) {

		new TextResources().changeLanguage();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(375, 812));
		panel.setBackground(new Color(216, 223, 224));

		configureButtons();
		configurateLabels(needsCoupon);
		addComponentsToPanel(needsCoupon);
		addListeners();

		this.setContentPane(panel);
		this.pack();
	}

	public void windowsConfiguration() {
		this.setTitle("Segaleo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}
	
	public void addComponentsToPanel(boolean needsCoupon) {
		panel.add(exitBtn);
		panel.add(completeTitle);
		
		//initialize insidePanel and add it to panel
		insidePanel = new JPanel();
		insidePanel.setPreferredSize(new Dimension(375, 700));
		insidePanel.setBackground(new Color(255, 255, 255));
		insidePanel.setBounds(0, 100, 375, 762);
		insidePanel.setLayout(null);
		
		panel.add(insidePanel);
		
		//add components to insidePanel
		insidePanel.add(orderComplLabel);
		insidePanel.add(message);
		if(needsCoupon) {
			insidePanel.add(couponLabel);
			insidePanel.add(couponCode);
		}

		
	}
	
	public void configurateLabels(boolean needsCoupon) {
		completeTitle = new JLabel(TextResources.orderCompleteTitle);
		completeTitle.setFont(new Font("Sans-Serif", Font.PLAIN, 23));
		completeTitle.setBounds(60,40,300,35);
		
		orderComplLabel = new JLabel(orderComplImage);
		orderComplLabel.setBounds(50, 100, 280, 280);
		
		message = new JLabel(String.format("<html><body style=\"text-align: center;\">%s</body></html>",TextResources.orderCompleteMessage));
		message.setFont(FontFactory.avenir(15));
		message.setBounds(40,400,300,40);
		
		if(needsCoupon) {
			Coupon coupon = CouponFactory.GenerateCoupon(Login.loggedCustomer.getEmail());
			couponLabel = LabelFactory.createLabel(TextResources.orderCompleteCoupon, ColorResources.bgLoginWindow, FontFactory.poppins(14));
			couponCode = LabelFactory.createLabelBG(coupon.getCode(), ColorResources.bgLoginWindow, ColorResources.bgMainWindowBtn, FontFactory.poppins(18));
			Login.loggedCustomer.addCoupons(coupon);
			couponLabel.setBounds(50, 500, 300, 100);
			couponCode.setBounds(150, 590, 80, 50);
			
		}
		
	}

	public void configureButtons() {
		exitBtn = ButtonFactory.createButtonIcon(exitImage);
		exitBtn.setBounds(25, 50, 15, 15);
		
	}
	
	public void addListeners() {
		exitBtn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == exitBtn) {
			this.dispose();
			new MainWindow();
		}
	}

}
