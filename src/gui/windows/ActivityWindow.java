package gui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import calendar.CalendarWindow;
import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import reservation.Activity;
import reservation.ActivityReader;
import reservation.Reservation;
import resources.ColorResources;
import resources.TextResources;

public class ActivityWindow extends JFrame {

	private static final long serialVersionUID = -3218493844069922114L;
	private JPanel backgroundPanel;
	private JLabel activityLabel;
	private String path = "buttonImages/Back Button";
	private String lang = TextResources.imageLang;
	private ImageIcon backImage = new ImageIcon(path + lang);
	private JButton backBtn;

	private int[][] a;

	private Reservation reservation;

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

	private Activity thisactivity = null;

	public ActivityWindow(Reservation reservation) {
		this.a = Activity.getA();
		this.reservation = reservation;
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
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (reservation.getActivities().size() > 0) {
					int selectedOption = JOptionPane.showConfirmDialog(null, TextResources.cancelReservation,
							TextResources.cancelReservationTitle, JOptionPane.YES_NO_OPTION);
					if (selectedOption == 0) {
						for (Activity act : reservation.getActivities()) {
							a[act.getSelday()][act.getSelhour() + act.getColumn()] += act.getSelpeople();
						}
						reservation.clearReservation();
						dispose();
						new MainWindow();
					}
				} else {
					dispose();
					new MainWindow();
				}
			}
		});

		cartPanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				new CartWindow(reservation);
			}
		});
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

		priceLabel = LabelFactory.createLabel(reservation.calcCost() + "€", ColorResources.frPopup,
				FontFactory.poppins(14));
		priceLabel.setBounds(301, cartPanel.getHeight() / 2 - 15, 49, 20);

		viewCart = LabelFactory.createLabel(TextResources.viewCart, ColorResources.frPopup, FontFactory.poppins(14));
		viewCart.setBounds(67, cartPanel.getHeight() / 2 - 15, 200, 20);

		bagLabel = LabelFactory.createIconLabel(bagIcon);
		bagLabel.setText(reservation.getActivities().size() + "");
		bagLabel.setBounds(18, cartPanel.getHeight() / 2 - 25, 50, 40);
		bagLabel.setForeground(Color.RED);

		cartPanel.add(bagLabel);
		cartPanel.add(viewCart);
		cartPanel.add(priceLabel);
	}

	public JPanel configureActivityPanel(Activity activity) {
		JPanel panel = new JPanel();
		panel.setName(String.valueOf(activity.getId()));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(320, 220));
		panel.setBackground(Color.white);

		activityImage = new ImageIcon(activity.getPath());
		activityimgLabel = LabelFactory.createIconLabel(activityImage);
		activityimgLabel.setBounds(20, 5, 310, 170);

		titleLabel = LabelFactory.createLabel(activity.getName(), Color.BLACK, FontFactory.poppins(14));
		titleLabel.setBounds(80, 180, 250, 24);
		
		ImageIcon startIcon =  new ImageIcon("./Icons/star-rating.png");
		JLabel starLabel = LabelFactory.createIconLabel(startIcon);
		starLabel.setBounds(20, 180, 50, 24);
		starLabel.setText(activity.calcAvRating()+"");

		plusIcon = new ImageIcon("./buttonImages/plus.png");
		plusButtonLabel = LabelFactory.createIconLabel(plusIcon);
		plusButtonLabel.setIcon(plusIcon);
		plusButtonLabel.setBounds(305, 180, 24, 24);
		plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		plusButtonLabel.addMouseListener(new plusButtonListener());

		priceLabel = LabelFactory.createLabelBG(activity.getPrice() + "€", ColorResources.bgLoginWindow,
				ColorResources.bgMainWindowBtn, FontFactory.poppins(14));
		priceLabel.setBounds(260, 180, 40, 24);

		panel.add(activityimgLabel);
		panel.add(titleLabel);
		panel.add(plusButtonLabel);
		panel.add(priceLabel);
		panel.add(starLabel);

		return panel;
	}

	// creates a vertical scrollable panel
	public JScrollPane createVerticalScrollablePanel() {
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(ActivityReader.getActivitiesList().size(), 1, 0, 15));

		for (Activity activity : ActivityReader.getActivitiesList()) {
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

	class plusButtonListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {

			JLabel plusLabel = (JLabel) e.getSource();
			JPanel parent = (JPanel) plusLabel.getParent();
			String activityId = parent.getName();
			// search for the activity with activityName
			thisactivity = ActivityReader.findActivity(Integer.parseInt(activityId));
			dispose();
			new CalendarWindow(ActivityReader.getActivitiesList(), thisactivity, reservation);

		}
	}

}
