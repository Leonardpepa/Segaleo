package gui.windows;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import calendar.CalendarProgram;
import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import reservation.Activity;
import reservation.ActivityReader;
import resources.ColorResources;
import resources.TextResources;

public class ActivityWindow extends JFrame implements ActionListener, MouseListener {

	private JPanel backgroundPanel;
	private ArrayList<Activity> activities = new ActivityReader().getActivitiesList();
	private JLabel activityLabel;
	private String path = "buttonImages/Back Button";
	private String lang = TextResources.imageLang;
	private ImageIcon backImage = new ImageIcon(path + lang);
	private JButton backBtn;

	// components for main panel
	private JPanel mainContent;
	private ImageIcon activityImage;
	private JLabel activityimgLabel;
	private JLabel titleLabel;
	private JLabel priceLabel;
	private ImageIcon plusIcon;
	private JLabel plusButtonLabel;

	// cart panel
	private JPanel cartPanel;
	private ImageIcon bagIcon;
	private JLabel bagLabel;
	private JLabel viewCart;
	private JLabel cartPriceLabel;

	public ActivityWindow(ArrayList<Activity> activities) {
		this.activities = activities;
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}

	private void initializePanelToFrame() {
		backgroundPanel = new JPanel();
		backgroundPanel.setPreferredSize(new Dimension(375, 812));
		backgroundPanel.setLayout(null);
		backgroundPanel.setBackground(ColorResources.bgMainWindowBtn);
		configureHeader();
		configureMainContent();
		configureCartPanel();
		addListeners();

		backgroundPanel.add(backBtn);
		backgroundPanel.add(activityLabel);
		backgroundPanel.add(mainContent);
		backgroundPanel.add(cartPanel);
		this.setContentPane(backgroundPanel);
		this.pack();
	}

	public void configureHeader() {
		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(12, 40, 67, 41);

		activityLabel = new JLabel(String.format("<html><body style=\"text-align: left;\">%s</body></html>",
				TextResources.activityHeader));
		activityLabel.setFont(FontFactory.boldavenir(20));
		activityLabel.setBounds(20, 80, 270, 80);
		activityLabel.setForeground(ColorResources.bgLoginWindow);
	}

	// settings for the frame
	public void windowsConfiguration() {
		this.setTitle("Segaleo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	private void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	public void addListeners() {
		backBtn.addActionListener(this);

	}

	// main panel
	public void configureMainContent() {
		mainContent = new JPanel();
		mainContent.setBackground(ColorResources.bgMainWindowBtn);
		mainContent.setLayout(new BorderLayout());
		mainContent.add(createVerticalScrollablePanel());
		mainContent.setBounds(13, 160, 350, 558);
	}

	// all the content for the cart panel
	public void configureCartPanel() {
		cartPanel = new JPanel();

		cartPanel.setLayout(null);
		cartPanel.setPreferredSize(new Dimension(375, 94));
		cartPanel.setBounds(0, 718, 375, 94);
		cartPanel.setBackground(Color.WHITE);
		cartPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

		bagIcon = new ImageIcon("Icons/Bag.png");

		priceLabel = LabelFactory.createLabel("10" + "€", ColorResources.frPopup, FontFactory.poppins(14));
		priceLabel.setBounds(301, cartPanel.getHeight() / 2 - 15, 49, 20);

		viewCart = LabelFactory.createLabel(TextResources.viewCart, ColorResources.frPopup, FontFactory.poppins(14));
		viewCart.setBounds(67, cartPanel.getHeight() / 2 - 15, 200, 20);

		bagLabel = LabelFactory.createIconLabel(bagIcon);
		bagLabel.setBounds(29, cartPanel.getHeight() / 2 - 15, 25, 25);

		cartPanel.add(bagLabel);
		cartPanel.add(viewCart);
		cartPanel.add(priceLabel);
	}

	public JPanel configureActivityPanel(Activity activity) {
		JPanel panel = new JPanel();
		panel.setName(activity.getName());
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(320, 220));
		panel.setBackground(Color.white);

		activityImage = new ImageIcon(activity.getPath());
		activityimgLabel = LabelFactory.createIconLabel(activityImage);
		activityimgLabel.setBounds(20, 5, 310, 170);

		titleLabel = LabelFactory.createLabel(activity.getName(), Color.BLACK, FontFactory.poppins(14));
		titleLabel.setBounds(20, 180, 250, 24);

		plusIcon = new ImageIcon("./buttonImages/plus.png");
		plusButtonLabel = LabelFactory.createIconLabel(plusIcon);
		plusButtonLabel.setIcon(plusIcon);
		plusButtonLabel.setBounds(305, 180, 24, 24);
		plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		plusButtonLabel.addMouseListener(this);

		priceLabel = LabelFactory.createLabelBG(activity.getPrice() + "€", ColorResources.bgLoginWindow, ColorResources.bgMainWindowBtn, FontFactory.poppins(14));
		priceLabel.setBounds(270, 180, 30, 24);

		panel.add(activityimgLabel);
		panel.add(titleLabel);
		panel.add(plusButtonLabel);
		panel.add(priceLabel);

		return panel;
	}

	// creates a vertical scrollable panel
	public JScrollPane createVerticalScrollablePanel() {
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(activities.size(), 1, 0, 15));

		for (Activity activity : activities) {
			container.add(configureActivityPanel(activity));
		}

		JScrollPane scrollPane = new JScrollPane(container);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);
		scrollBar.setUnitIncrement(16);
		scrollBar.setPreferredSize(new Dimension(0, 0));
		scrollPane.setVerticalScrollBar(scrollBar);
		return scrollPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			this.dispose();
			new MainWindow();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JLabel) {
			Activity thisactivity = null;
			JLabel plusLabel = (JLabel) e.getSource();
			JPanel parent = (JPanel) plusLabel.getParent();
			String activityName = parent.getName();
			// search for the activity with activityName
			for (Activity a : activities) {
				if (a.getName().equalsIgnoreCase(activityName)) {
					thisactivity = a;
//					System.out.println(thisactivity.getName());
				}
			}
			new CalendarProgram(thisactivity);
			// column where activity starts
			int pos = (activities.indexOf(thisactivity)) * 2;
//			 System.out.println(pos);
			// να μπαίνει στο reservation αβτίστοιχα με το order
			// order.addProduct(clickedProduct);
			// cartPriceLabel.setText(String.valueOf(order.calcCost()) + "€");
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
