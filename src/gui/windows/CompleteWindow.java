package gui.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import login.Login;
import order.Coupon;
import platformData.PlatformData;
import resources.ColorResources;
import resources.TextResources;

public class CompleteWindow extends JFrame {

	/**
	 * This Class creates the GUI window for the completion of each order/reservation
	 * 
	 * The boolean isOrder is used to specify if the action is performed for an order or a reservation 
	 * and can differentiate the labels for each
	 * 
	 * The boolean needsCoupon is used for the time an order is above 20 euros so the window
	 * needs to generate and display a coupon
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel insidePanel;
	private JLabel completeTitle;
	private JLabel message;
	private JLabel couponLabel;
	private JLabel couponCode;
	private JLabel orderComplLabel;

	private ImageIcon orderComplImage = new ImageIcon("buttonImages/order-complete.png");

	private ImageIcon exitImage = new ImageIcon("buttonImages/exit button.png");
	private JButton exitBtn;

	public CompleteWindow(Coupon coupon, boolean isOrder) {
		initializePanelToFrame(coupon, isOrder);
		windowsConfiguration();
		showWindow(this, true);
	}

	public void initializePanelToFrame(Coupon coupon, boolean isOrder) {

		new TextResources().changeLanguage();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(375, 812));
		panel.setBackground(new Color(216, 223, 224));

		configureButtons();
		configurateLabels(coupon, isOrder);
		addComponentsToPanel(coupon, isOrder);
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

	public void addComponentsToPanel(Coupon coupon, boolean isOrder) {
		panel.add(exitBtn);
		panel.add(completeTitle);

		// initialize insidePanel and add it to panel
		insidePanel = new JPanel();
		insidePanel.setPreferredSize(new Dimension(375, 700));
		insidePanel.setBackground(new Color(255, 255, 255));
		insidePanel.setBounds(0, 100, 375, 762);
		insidePanel.setLayout(null);

		panel.add(insidePanel);

		// add components to insidePanel
		insidePanel.add(orderComplLabel);
		insidePanel.add(message);
		if (coupon != null) {
			insidePanel.add(couponLabel);
			insidePanel.add(couponCode);
		}

	}

	public void configurateLabels(Coupon coupon, boolean isOrder) {
		completeTitle = isOrder
				? LabelFactory.createLabel(TextResources.orderCompleteTitle, Color.black, FontFactory.sansSerif(18))
				: LabelFactory.createLabel(TextResources.reservationCompleteTitle, Color.black,
						FontFactory.sansSerif(18));
		completeTitle.setBounds(60, 40, 300, 35);

		orderComplLabel = new JLabel(orderComplImage);
		orderComplLabel.setBounds(50, 100, 280, 280);

		message = isOrder
				? LabelFactory.createLabel(TextResources.orderCompleteMessage, Color.black, FontFactory.sansSerif(16))
				: LabelFactory.createLabel(TextResources.reservationCompleteMessage, Color.black,
						FontFactory.sansSerif(16));
		message.setBounds(40, 400, 300, 80);

		if (coupon != null) {
			couponLabel = LabelFactory.createLabel(TextResources.orderCompleteCoupon, ColorResources.bgLoginWindow,
					FontFactory.poppins(14));
			couponCode = LabelFactory.createLabelBG(coupon.getCode(), ColorResources.bgLoginWindow,
					ColorResources.bgMainWindowBtn, FontFactory.poppins(18));
			couponCode = LabelFactory.alignLabel(couponCode, SwingConstants.CENTER, SwingConstants.CENTER);
			couponLabel.setBounds(50, 500, 300, 100);
			couponCode.setBounds(130, 590, 100, 50);

		}

	}

	public void configureButtons() {
		exitBtn = ButtonFactory.createButtonIcon(exitImage);
		exitBtn.setBounds(25, 50, 15, 15);

	}

	public void addListeners() {
		exitBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				PlatformData.saveData();
				dispose();
				new MainWindow();				
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			 
			@Override
			 
			public void windowClosing(WindowEvent e) {
				PlatformData.saveData();
			    System.exit(0);
			}
		});
	}

}
