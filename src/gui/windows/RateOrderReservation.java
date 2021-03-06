package gui.windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


import gui.components.RoundedPanel;
import gui.factory.BackgroundFactory;
import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import gui.factory.LogoFactory;
import gui.factory.TextAreaFactory;
import order.Order;
import order.Product;
import platformData.PlatformData;
import rating.Rating;
import reservation.Activity;
import reservation.Reservation;
import resources.ColorResources;
import resources.TextResources;

public class RateOrderReservation extends JFrame{

	/**
	 * this class is used to rate an order
	 */
	private static final long serialVersionUID = 4758537304753221958L;
	private JPanel panel;
	private JPanel insidePanel;
	private JLabel ratingLabel;
	private JTextArea commentsArea;
	private Order order;
	private Reservation reservation;
	private boolean isOrder;
	private int numberOfStars = 0;
	private JLabel name;

	private ImageIcon backImage = new ImageIcon("buttonImages/Back Button" + TextResources.imageLang);
	private JButton backBtn;
	private ImageIcon estarImage = new ImageIcon("buttonImages/emptystar.png");
	private ImageIcon starImage = new ImageIcon("buttonImages/rating icon.png");
	private List<JButton> stars = new ArrayList<JButton>(5);
	
	private JButton submitButton;

	public RateOrderReservation(Reservation reservation) {
		this.reservation = reservation;
		isOrder = false;
		initializePanelToFrame(isOrder);
		windowsConfiguration();
		showWindow(this, true);
	}

	public RateOrderReservation(Order order) {
		this.order = order;
		isOrder = true;
		initializePanelToFrame(isOrder);
		windowsConfiguration();
		showWindow(this, true);
	}

	public void windowsConfiguration() {
		this.setTitle("Segaleo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public void initializePanelToFrame(boolean isOrer) {

		new TextResources().changeLanguage();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(375, 812));
		panel.setBackground(new Color(216, 223, 224));

		configureButtons();
		configureLabels();
		addComponentsToPanel(isOrder);
		addListeners();

		this.setContentPane(panel);
		this.pack();
	}

	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	public void addComponentsToPanel(boolean isOrder) {
		panel.add(backBtn);
		panel.add(LogoFactory.addLogoScaled());
		panel.add(ratingLabel);
		initializeInsidePanel();
		addComponentsToInsidePanel(isOrder);
		panel.add(insidePanel);
		panel.add(BackgroundFactory.addBackgroundLight());

	}

	public void initializeInsidePanel() {
		insidePanel = new RoundedPanel(50, new Color(177, 206, 209));
		insidePanel.setOpaque(false);
		insidePanel.setBorder(new EmptyBorder(50, 20, 380, 20));
		insidePanel.setLayout(null);
		insidePanel.setBounds(11, 290, 351, 500);
		configureTextArea();

	}

	public void addComponentsToInsidePanel(boolean isOrder) {
		if (isOrder) {
			name = new JLabel(TextResources.order + " #" + order.getId());
		} else {
			name = new JLabel(TextResources.reservation + " #" + reservation.getId());
		}
		configureOrderReservation();
		name.setBounds(15, 5, 300, 20);
		insidePanel.add(name);
		insidePanel.add(commentsArea);
		
		for(JButton star: stars) {
			insidePanel.add(star);
		}
		insidePanel.add(submitButton);
	}

	public void configureOrderReservation() {
		int y = 30; // y for set bounds
		int k = 30;
		int limit = 300;
		if (isOrder) {
			for (Product p : order.getProducts()) {
				JLabel product = LabelFactory.createLabel(order.getProd().get(p) + "x " + p.getName(), Color.WHITE,
						FontFactory.poppins(13));
				product.setBounds(10, y, 180, 20);
				insidePanel.add(product);
				y += 20;
				if (y >= limit) {
					product.setBounds(200, k, 180, 20);
					insidePanel.add(product);
					k += 20;
				}
			}
		} else {
			for (Activity activity : reservation.getActivities()) {
				JLabel product = LabelFactory.createLabel(
						reservation.getAct().get(activity) + "x " + activity.getName(), Color.WHITE,
						FontFactory.poppins(13));
				product.setBounds(10, y, 180, 20);
				insidePanel.add(product);
				y += 20;
				if (y >= limit) {
					product.setBounds(200, k, 180, 20);
					insidePanel.add(product);
					k += 20;
				}
			}
		}
	}

	public void configureTextArea() {

		commentsArea = TextAreaFactory.createTextArea(TextResources.ratingComment, new Color(177, 206, 209),
				ColorResources.bgLoginWindow, FontFactory.poppins(12));
		commentsArea.setBounds(10, 300, 330, 95);
		commentsArea.setBorder(new RoundBorder(30));
		commentsArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				commentsArea.setText("");
			}
		});

	}

	public void configureButtons() {
		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(12, 40, 67, 45);
		
		int x = 190;
		for(int i=0; i<5; i++) {
			stars.add(ButtonFactory.createButtonIcon(estarImage));
			stars.get(i).setBounds(x, 420, 25, 25);
			stars.get(i).setName(String.valueOf(i));
			x += 30;
		}
		submitButton = ButtonFactory.createButton(TextResources.submit, FontFactory.poppins(15),
				ColorResources.frMainWindowBtn, Color.WHITE);
		submitButton.setBounds(120, 455, 100, 40);

	}

	public void configureLabels() {
		if (isOrder) {
			ratingLabel = LabelFactory.createLabelBG(TextResources.ratingLabelOrder, ColorResources.bgLoginWindow,
					Color.WHITE, FontFactory.poppins(16));
		} else {
			ratingLabel = LabelFactory.createLabelBG(TextResources.ratingLabelReservation, ColorResources.bgLoginWindow,
					Color.WHITE, FontFactory.poppins(16));
		}
		ratingLabel = LabelFactory.alignLabel(ratingLabel, SwingConstants.CENTER, SwingConstants.CENTER);
		ratingLabel.setBounds(20, 225, 118, 38);
	}

	public void addListeners() {
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainWindow();
			}
		});
		
		stars.forEach(star -> {
			star.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int index = Integer.parseInt(star.getName());
					if(star.getIcon() == estarImage) {
						for(int i=0; i<=index; i++) {
							stars.get(i).setIcon(starImage);
							numberOfStars = index + 1;
							repaint();
							revalidate();
						}
					}
					else {
						for(int i=4; i>index; i--) {
							stars.get(i).setIcon(estarImage);
							numberOfStars = index + 1;
							repaint();
							revalidate();
						}
					}
				}
			});
		});
	

		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String comment = commentsArea.getText();
				Rating rate = new Rating(numberOfStars, comment);

				if (isOrder == true) {
					order.setRating(rate);
					for (Product product : order.getProducts()) {
						product.addRating(rate);
					}
				} else {
					reservation.setRating(rate);
					for (Activity activity : reservation.getActivities()) {
						activity.addRating(rate);
					}
				}

				PlatformData.saveData();
				PlatformData.loadData();
				dispose();
				if (isOrder == true) {
					JOptionPane.showMessageDialog(null, TextResources.rateOrder, "Segaleo",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, TextResources.rateReservation, "Segaleo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}


	public class RoundBorder implements Border {

		private int radius;

		public RoundBorder(int radius) {
			this.radius = radius;
		}

		public int getRadius() {
			return radius;
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, getRadius(), getRadius()));
			g2d.dispose();
		}

		@Override
		public Insets getBorderInsets(Component c) {
			int value = getRadius() / 2;
			return new Insets(value, value, value, value);
		}

		@Override
		public boolean isBorderOpaque() {
			return false;
		}

	}

}
