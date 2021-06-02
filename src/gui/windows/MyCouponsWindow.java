package gui.windows;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.components.RoundedPanel;
import gui.factory.*;
import login.Login;
import order.Coupon;
import resources.ColorResources;
import resources.TextResources;

public class MyCouponsWindow extends JFrame implements ActionListener {
	private JPanel panel;
	private JPanel insidePanel;

	private String path = "buttonImages/Back Button";
	private String lang = TextResources.imageLang;
	private ImageIcon backImage = new ImageIcon(path + lang);
	private JButton backBtn;

	private ArrayList<Coupon> coupons = Login.loggedCustomer.getCoupons();

	private JLabel couponCode;
	private JLabel validLabel;
	private JLabel date;

	public MyCouponsWindow() {
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}

	public void windowsConfiguration() {
		this.setTitle("My Coupons");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public void initializePanelToFrame() {

		new TextResources().changeLanguage();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(375, 812));
		panel.setBackground(new Color(216, 223, 224));

		configureButtons();
		addComponentsToPanel();
		addListeners();

		this.setContentPane(panel);
		this.pack();
	}

	public void initializeInsidePanel() {
		insidePanel = new RoundedPanel(50, Color.white);
		insidePanel.setOpaque(false);
		insidePanel.setBorder(new EmptyBorder(50, 20, 380, 20));
		insidePanel.setLayout(null);
		insidePanel.setBounds(11, 250, 351, 80);
		configureLabels();

	}

	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	public void addComponentsToPanel() {
		panel.add(LogoFactory.addLogoScaled());

		initializeInsidePanel();
		addComponentsToInsidePanel();
		panel.add(insidePanel);
		panel.add(backBtn);
		panel.add(BackgroundFactory.addBackgroundLight());

	}

	public void addComponentsToInsidePanel() {

		insidePanel.add(couponCode);

		if (!coupons.isEmpty()) {
			insidePanel.add(validLabel);
			insidePanel.add(date);
		}
	}

	public void configureButtons() {
		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(12, 40, 67, 45);
	}

	public void configureLabels() {

		if (!coupons.isEmpty()) {
			for (Coupon coupon : coupons) {
				couponCode = LabelFactory.createLabel(coupon.getCode(), ColorResources.bgLoginWindow,
						FontFactory.poppins(18));
				couponCode.setBounds(15, 30, 200, 20);
				validLabel = LabelFactory.createLabel(TextResources.valid, ColorResources.bgLoginWindow,
						FontFactory.poppins(14));
				validLabel.setBounds(240, 20, 100, 20);

				date = LabelFactory.createLabel(configureDate(coupon), ColorResources.bgLoginWindow,
						FontFactory.poppins(14));
				date.setBounds(240, 40, 100, 20);
			}
		}else {
			couponCode = LabelFactory.createLabel(TextResources.noCoupon, ColorResources.bgLoginWindow,
					FontFactory.poppins(16));
			couponCode.setBounds(20, 30, 200, 20);
		}
	}

	public String configureDate(Coupon coupon) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = coupon.getDate();
		String dateAsString = sdf.format(date);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(dateAsString));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DATE, 3); // number of days to add
		dateAsString = sdf.format(c.getTime());
		return dateAsString;

	}

	public void addListeners() {
		backBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			this.dispose();
			new ProfileWindow();
		}

	}
}
