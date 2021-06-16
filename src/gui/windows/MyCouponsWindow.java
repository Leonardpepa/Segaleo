package gui.windows;

import java.awt.*;
import java.awt.event.*;
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
import roomCustomer.Customer;

public class MyCouponsWindow extends JFrame {
	/**
	 * This window displays all of the users coupons
	 * 
	 * It can be found when going to the Profile > My Coupons
	 */
	private static final long serialVersionUID = 1994427376744382511L;
	private JPanel panel;
	private JPanel insidePanel;

	private String path = "buttonImages/Back Button";
	private String lang = TextResources.imageLang;
	private ImageIcon backImage = new ImageIcon(path + lang);
	private JButton backBtn;

	private Customer customer = Login.loggedCustomer;

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
		insidePanel = new JPanel();
		insidePanel.setBackground(ColorResources.paymentBtn);
		insidePanel.setOpaque(false);
		insidePanel.setLayout(new FlowLayout());
		
			//if there's no coupon, display a message
			if (customer.getCoupons().size() == 0) {
				insidePanel.setLayout(null);
				JLabel label = LabelFactory.createLabel(TextResources.noCoupon, ColorResources.bgLoginWindow,
						FontFactory.poppins(16));
				label.setBounds(25, 15, 200, 22);
				insidePanel.add(label);
				insidePanel.setBounds(0, 250, 375, 50);
			//if there are more than 6 coupons create a scrollable panel and adjust accordingly
			}else if(customer.getCoupons().size() >= 6) {
				insidePanel.setLayout(new BorderLayout());
				JScrollPane scroll = createVerticalScrollablePanel();
				insidePanel.add(scroll);
				insidePanel.setBounds(0, 250, 375, 500);
			}
			//create a scrollable panel with no adjustments needed
			else {
				JScrollPane scroll = createVerticalScrollablePanel();
				insidePanel.add(scroll);
				insidePanel.setBounds(0, 250, 375, 500);
			}

	}

	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	public void addComponentsToPanel() {
		panel.add(LogoFactory.addLogoScaled());

		initializeInsidePanel();
		panel.add(insidePanel);
		panel.add(backBtn);
		panel.add(BackgroundFactory.addBackgroundLight());

	}

	public void configureButtons() {
		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(12, 40, 67, 45);
	}

	//configuring the date when the coupon is going to expire (after 3 days)
	public String configureDate(Coupon coupon) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = coupon.getDate(); //get the creation date of the coupon
		String dateAsString = sdf.format(date);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(dateAsString));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, 3); // add 3 days
		dateAsString = sdf.format(c.getTime()); //new date is the expiring date
		return dateAsString;
	}
	
	public JPanel displayPanel(Coupon coupon) {

		JPanel insidePanel = new RoundedPanel(50, new Color(177, 206, 209));
		insidePanel.setOpaque(false);
		insidePanel.setBorder(new EmptyBorder(50, 20, 380, 20));
		insidePanel.setLayout(null);
		insidePanel.setPreferredSize(new Dimension(350, 80));

		couponCode = LabelFactory.createLabel(coupon.getCode(), ColorResources.bgLoginWindow,
				FontFactory.poppins(18));
		couponCode.setBounds(15, 30, 200, 20);
		validLabel = LabelFactory.createLabel(TextResources.valid, ColorResources.bgLoginWindow,
				FontFactory.poppins(14));
		validLabel.setBounds(240, 20, 100, 20);

		date = LabelFactory.createLabel(configureDate(coupon), ColorResources.bgLoginWindow,
				FontFactory.poppins(14));
		date.setBounds(240, 40, 100, 20);
	
		insidePanel.add(couponCode);
		insidePanel.add(validLabel);
		insidePanel.add(date);
		return insidePanel;
	}

	//creating a scrollable panel to display all of the coupons
	public JScrollPane createVerticalScrollablePanel() {
		JPanel container = new JPanel();
		container.setBackground(ColorResources.paymentBtn);
			container.setLayout(new GridLayout(customer.getCoupons().size(), 1, 10, 8));
			for (int i = 0; i < customer.getCoupons().size(); i++) {
				container.add(displayPanel(customer.getCoupons().get(i)));
			}
		
		JScrollPane scrollPane = new JScrollPane(container);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBackground(ColorResources.paymentBtn);
		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);
		scrollBar.setUnitIncrement(16);
		scrollBar.setPreferredSize(new Dimension(0, 0));
		scrollPane.setVerticalScrollBar(scrollBar);
		return scrollPane;
	}

	public void addListeners() {
		backBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ProfileWindow();
			}
		});
	}
}
