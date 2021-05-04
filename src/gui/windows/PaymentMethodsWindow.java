package gui.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import resources.TextResources;

public class PaymentMethodsWindow extends JFrame  implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	
	private ImageIcon backgroundImage = new ImageIcon("Background Images/PaymentBackground.png");
	private JLabel backgroundLabel;
	
	private ImageIcon cardImage = new ImageIcon("buttonImages/Card.png");
	private ImageIcon cardImageGR = new ImageIcon("buttonImages/CardGR.png");
	private JButton cardBtn;
	
	private ImageIcon paypalImage = new ImageIcon("buttonImages/Paypal.png");
	private JButton paypalBtn;
	
	private ImageIcon roomBillImage = new ImageIcon("buttonImages/RoomBill.png");
	private ImageIcon roomBillImageGR = new ImageIcon("buttonImages/RoomBillGR.png");
	private JButton roomBillBtn;

	private JLabel paymentLabel;


	//constructor
	public PaymentMethodsWindow() {
//		initializePanelToFrame();
//		windowsConfiguration();
//		showWindow(this,true);
	}

	//settings for the frame
	public void windowsConfiguration() {
		this.setTitle("Hotel PDA Sample");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	//set up the panel in the frame
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

	//makes the frame visible
	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	//label settings
	public void configureLabels() {
		paymentLabel = LabelFactory.createLabel(TextResources.payment, Color.black, FontFactory.poppins(22));
		paymentLabel.setBounds(24, 73, 200, 31);
	
	}

	//buttons settings
	public void configureButtons() {
		
		
		if (TextResources.isEnglish)
		{
			cardBtn = ButtonFactory.createButtonIcon(cardImage);
			cardBtn.setPressedIcon(cardImage);
		}
		else
		{
			cardBtn = ButtonFactory.createButtonIcon(cardImageGR); //na valw ta ellhnika 
			cardBtn.setPressedIcon(cardImageGR);
		}
		cardBtn.setBounds(17, 154, 342, 133);
		
		paypalBtn = ButtonFactory.createButtonIcon(paypalImage);
		paypalBtn.setBounds(17, 309 , 342, 133);
		paypalBtn.setPressedIcon(paypalImage);
		
		if (TextResources.isEnglish)
		{
			roomBillBtn = ButtonFactory.createButtonIcon(roomBillImage);
			roomBillBtn.setPressedIcon(roomBillImage);
		}
		else
		{
			roomBillBtn = ButtonFactory.createButtonIcon(roomBillImageGR);//na valw ta ellhnika
			roomBillBtn.setPressedIcon(roomBillImageGR);
		}
		roomBillBtn.setBounds(17, 467 , 342, 133);

	}
	
	public void configureBackground() {
		backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0,0,375, 125);
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

		if(e.getSource() == cardBtn) {
//			this.dispose();
//			new CardWindow();
		}
		
		if(e.getSource() == paypalBtn) {
//			this.dispose();
//			new PaypalWindow();
			
		}
		if(e.getSource() == roomBillBtn) {
//			this.dispose();
//			new RoomBillWindow();
			
		}
		initializePanelToFrame();
	}

}
