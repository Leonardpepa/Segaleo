package gui.windows;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import gui.factory.*;
import payment.Payment;
import resources.TextResources;

public class PaymentWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JPanel panel;

	private String lang = TextResources.imageLang;

	private String path = "buttonImages/Card";
	private ImageIcon cardImage = new ImageIcon(path + lang);
	private JButton cardBtn;

	private String path2 = "buttonImages/RoomBill";
	private ImageIcon roomBillImage = new ImageIcon(path2 + lang);
	private JButton roomBillBtn;

	private ImageIcon paypalImage = new ImageIcon("buttonImages/Paypal.png");
	private JButton paypalBtn;

	private ImageIcon backgroundImage = new ImageIcon("Background Images/PaymentBackground.png");
	private JLabel backgroundLabel;

	private JLabel paymentLabel;

	// constructor
	public PaymentWindow() {
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}

	// settings for the frame
	public void windowsConfiguration() {
		this.setTitle("Hotel PDA Sample");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	// set up the panel in the frame
	public void initializePanelToFrame() {

		new TextResources().changeLanguage();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(375, 812));

		configureLabels();
		configureButtons();
		configureBackground();
		addListeners();
		addComponentsToPanel();

		panel.setBackground(Color.white);

		this.setContentPane(panel);
		this.pack();
	}

	// makes the frame visible
	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	// label settings
	public void configureLabels() {
		paymentLabel = LabelFactory.createLabel(TextResources.payment, Color.black, FontFactory.poppins(22));
		paymentLabel.setBounds(24, 73, 200, 31);

	}

	// buttons settings
	public void configureButtons() {

		cardBtn = ButtonFactory.createButtonIcon(cardImage);
		cardBtn.setPressedIcon(cardImage);

		cardBtn.setBounds(17, 154, 342, 133);

		paypalBtn = ButtonFactory.createButtonIcon(paypalImage);
		paypalBtn.setBounds(17, 309, 342, 133);
		paypalBtn.setPressedIcon(paypalImage);

		roomBillBtn = ButtonFactory.createButtonIcon(roomBillImage);
		roomBillBtn.setPressedIcon(roomBillImage);
		roomBillBtn.setBounds(17, 467, 342, 133);

	}

	public void configureBackground() {
		backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0, 0, 375, 125);
	}

	public void addListeners() {
		cardBtn.addActionListener(this);
		paypalBtn.addActionListener(this);
		roomBillBtn.addActionListener(this);

	}

	public void addComponentsToPanel() {
		panel.add(paymentLabel);
		panel.add(cardBtn);
		panel.add(paypalBtn);
		panel.add(roomBillBtn);

		panel.add(backgroundLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Payment payment = new Payment();

		if (e.getSource() == cardBtn) {
			this.dispose();
			payment.card();
		}

		if (e.getSource() == paypalBtn) {
			this.dispose();
			payment.paypal();

		}
		if (e.getSource() == roomBillBtn) {
			this.dispose();
			payment.roomBill();

		}

		initializePanelToFrame();
	}

}
