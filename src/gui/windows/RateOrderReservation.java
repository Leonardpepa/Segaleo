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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
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
import rating.Rating;
import reservation.Reservation;
import resources.ColorResources;
import resources.TextResources;

public class RateOrderReservation extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel insidePanel;
	private JLabel ratingLabel;
	private JTextArea commentsArea;
	private Order order;
	private Reservation reservation;
	private boolean isOrder;
	private int numberOfStars = 0;
	
	
	ImageIcon backImage = new ImageIcon("buttonImages/Back Button"+ TextResources.imageLang);
	JButton backBtn;
	
	ImageIcon estarImage = new ImageIcon("buttonImages/emptystar.png");
	JButton estarBtn1;
	JButton estarBtn2;
	JButton estarBtn3;
	JButton estarBtn4;
	JButton estarBtn5;

	ImageIcon starImage = new ImageIcon("buttonImages/rating icon.png");
	JButton starBtn1;
	JButton starBtn2;
	JButton starBtn3;
	JButton starBtn4;
	JButton starBtn5;

	JButton submitButton;
	
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
		this.setTitle("Hotel PDA Sample");
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
		panel.add(submitButton);

	}
	public void initializeInsidePanel() {
		insidePanel = new RoundedPanel(50, new Color(177, 206, 209));
		insidePanel.setOpaque(false);
		insidePanel.setBorder(new EmptyBorder(50, 20, 380, 20));
		insidePanel.setLayout(null);
		insidePanel.setBounds(11, 290, 351, 190);
		configureTextArea();
		
	}
	public void addComponentsToInsidePanel(boolean isOrder) {
		JLabel name;
		if(isOrder) {
			name = new JLabel("#" + order.getId() + " ORDER");
		}
		else {
			name = new JLabel("#" + reservation.getId() + " RESERVATION");
		}
	
		
		insidePanel.add(name);
		insidePanel.add(commentsArea);
		insidePanel.add(estarBtn1);
		insidePanel.add(estarBtn2);
		insidePanel.add(estarBtn3);
		insidePanel.add(estarBtn4);
		insidePanel.add(estarBtn5);
		//εδω θα μπουν και τα labels για την δραστηριότητα προς αξιολογηση
	}
	
	public void configureTextArea() {
		
		commentsArea = TextAreaFactory.createTextArea(TextResources.ratingComment, new Color(177, 206, 209), ColorResources.bgLoginWindow,
				FontFactory.poppins(12));
		commentsArea.setBounds(10, 30,330, 95);
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
		
		estarBtn1 = ButtonFactory.createButtonIcon(estarImage);
		estarBtn1.setBounds(190, 140, 25, 25);
		estarBtn2 = ButtonFactory.createButtonIcon(estarImage);
		estarBtn2.setBounds(220, 140, 25, 25);
		estarBtn3 = ButtonFactory.createButtonIcon(estarImage);
		estarBtn3.setBounds(250, 140, 25, 25);
		estarBtn4 = ButtonFactory.createButtonIcon(estarImage);
		estarBtn4.setBounds(280, 140, 25, 25);
		estarBtn5 = ButtonFactory.createButtonIcon(estarImage);
		estarBtn5.setBounds(310, 140, 25, 25);
		
		starBtn1 = ButtonFactory.createButtonIcon(starImage);
		starBtn1.setBounds(190, 140, 25, 25);
		starBtn2 = ButtonFactory.createButtonIcon(starImage);
		starBtn2.setBounds(220, 140, 25, 25);
		starBtn3 = ButtonFactory.createButtonIcon(starImage);
		starBtn3.setBounds(250, 140, 25, 25);
		starBtn4 = ButtonFactory.createButtonIcon(starImage);
		starBtn4.setBounds(280, 140, 25, 25);
		starBtn5 = ButtonFactory.createButtonIcon(starImage);
		starBtn5.setBounds(310, 140, 25, 25);

		submitButton = ButtonFactory.createButton("Submit", FontFactory.poppins(15), ColorResources.frMainWindowBtn, Color.WHITE);
		submitButton.setBounds(12, 500, 100, 50);

	}

	public void configureLabels() {
		ratingLabel = LabelFactory.createLabelBG(TextResources.ratingLabel, ColorResources.bgLoginWindow, Color.WHITE,
				FontFactory.poppins(16));
		ratingLabel = LabelFactory.alignLabel(ratingLabel, SwingConstants.CENTER, SwingConstants.CENTER);
		ratingLabel.setBounds(20, 225, 118, 38);
	}
	
	public void addListeners() {
		backBtn.addActionListener(this);
		
		estarBtn1.addActionListener(this);
		estarBtn2.addActionListener(this);
		estarBtn3.addActionListener(this);
		estarBtn4.addActionListener(this);
		estarBtn5.addActionListener(this);
		
		starBtn1.addActionListener(this);
		starBtn2.addActionListener(this);
		starBtn3.addActionListener(this);
		starBtn4.addActionListener(this);
		starBtn5.addActionListener(this);

		submitButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			this.dispose();
			new MainWindow();
		}
		if (e.getSource() == estarBtn1) {
			insidePanel.remove(estarBtn1);
			insidePanel.add(starBtn1);
			revalidate();
			repaint();
		}
		if (e.getSource() == estarBtn2) {
			insidePanel.remove(estarBtn1);
			insidePanel.remove(estarBtn2);
			insidePanel.add(starBtn1);
			insidePanel.add(starBtn2);
			revalidate();
			repaint();
		}
		if (e.getSource() == estarBtn3) {
			insidePanel.remove(estarBtn1);
			insidePanel.remove(estarBtn2);
			insidePanel.remove(estarBtn3);
			insidePanel.add(starBtn1);
			insidePanel.add(starBtn2);
			insidePanel.add(starBtn3);
			revalidate();
			repaint();
		}
		if (e.getSource() == estarBtn4) {
			insidePanel.remove(estarBtn1);
			insidePanel.remove(estarBtn2);
			insidePanel.remove(estarBtn3);
			insidePanel.remove(estarBtn4);
			insidePanel.add(starBtn1);
			insidePanel.add(starBtn2);
			insidePanel.add(starBtn3);
			insidePanel.add(starBtn4);
			revalidate();
			repaint();
		}
		if (e.getSource() == estarBtn5) {
			insidePanel.remove(estarBtn1);
			insidePanel.remove(estarBtn2);
			insidePanel.remove(estarBtn3);
			insidePanel.remove(estarBtn4);
			insidePanel.remove(estarBtn5);
			insidePanel.add(starBtn1);
			insidePanel.add(starBtn2);
			insidePanel.add(starBtn3);
			insidePanel.add(starBtn4);
			insidePanel.add(starBtn5);
			revalidate();
			repaint();
		}
		
		if (e.getSource() == starBtn1) {
			insidePanel.remove(starBtn1);
			insidePanel.remove(starBtn2);
			insidePanel.remove(starBtn3);
			insidePanel.remove(starBtn4);
			insidePanel.remove(starBtn5);
			insidePanel.add(estarBtn1);
			insidePanel.add(estarBtn2);
			insidePanel.add(estarBtn3);
			insidePanel.add(estarBtn4);
			insidePanel.add(estarBtn5);
			revalidate();
			repaint();
			numberOfStars = 1;
		}	
		if (e.getSource() == starBtn2) {
			insidePanel.remove(starBtn2);
			insidePanel.remove(starBtn3);
			insidePanel.remove(starBtn4);
			insidePanel.remove(starBtn5);
			insidePanel.add(estarBtn2);
			insidePanel.add(estarBtn3);
			insidePanel.add(estarBtn4);
			insidePanel.add(estarBtn5);
			revalidate();
			repaint();
			numberOfStars = 2;
		}
		if (e.getSource() == starBtn3) {
			insidePanel.remove(starBtn3);
			insidePanel.remove(starBtn4);
			insidePanel.remove(starBtn5);
			insidePanel.add(estarBtn3);
			insidePanel.add(estarBtn4);
			insidePanel.add(estarBtn5);
			revalidate();
			repaint();
			numberOfStars = 3;
		}
		if (e.getSource() == starBtn4) {
			insidePanel.remove(starBtn4);
			insidePanel.remove(starBtn5);
			insidePanel.add(estarBtn4);
			insidePanel.add(estarBtn5);
			revalidate();
			repaint();
			numberOfStars = 4;
		}
		if (e.getSource() == starBtn5) {
			insidePanel.remove(starBtn5);
			insidePanel.add(estarBtn5);
			revalidate();
			repaint();
			numberOfStars = 5;
		}

		if(e.getSource() == submitButton) {
			String comment = commentsArea.getText();
			System.out.println(numberOfStars);
			if(isOrder == true) {
				order.addRating(new Rating(numberOfStars, comment));
			}
			else {
				reservation.addRating(new Rating(numberOfStars, comment));
			}
		}
		
		
		
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

