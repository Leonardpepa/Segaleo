package creditCard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import gui.components.*;
import gui.factory.*;
import gui.windows.PaymentMethodsWindow;
import resources.*;

public class CreditCardGUI extends JFrame implements ActionListener {

	JPanel panel = new JPanel();

	//Header
	private ImageIcon exitImage = new ImageIcon("buttonImages/exit button.png");
	private JButton exitBtn;
	private JLabel titleLabel;
	
	//Main Section
	private ImageIcon cardImage = new ImageIcon("Icons/card image.png");
	private JLabel cardImageLabel;
	
	//Adding card info
	private JLabel nameLabel;
	private JLabel nameIconLabel;
	private ImageIcon nameIcon = new ImageIcon("Icons/card name icon.png");
	
	private JLabel cardNumLabel;
	private JLabel cardnumIconLabel;
	private ImageIcon cardnumIcon = new ImageIcon("Icons/credit card icon.png");
	
	private JLabel dateLabel;
	private JLabel dateIconLabel;
	private ImageIcon dateIcon = new ImageIcon("Icons/expire date.png");
	
	private JLabel codeLabel;
	private JLabel codeIconLabel;
	private ImageIcon codeIcon = new ImageIcon("Icons/ccv.png");
	
	private JTextField cardNum;
	private JTextField name;
	private JTextField code;
	private JButton savecardBtn;
	private String[] months = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	private String[] year = { "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" };

	private JComboBox monthsList = new JComboBox(months);
	private JComboBox yearList = new JComboBox(year);

	private CreditCard cc;

	public CreditCardGUI() {
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}

	public void windowsConfiguration() {
		this.setTitle("Add card");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public void initializePanelToFrame() {

		new TextResources().changeLanguage();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(375, 812));
		panel.setBackground(Color.white);

		configureButtons();
		configureLabels();
		configureTextField();
		configureComboBox();
		addComponentsToPanel();
		addListeners();

		this.setContentPane(panel);
		this.pack();
	}

	public void addComponentsToPanel() {
		panel.add(exitBtn);
		panel.add(titleLabel);
		panel.add(cardImageLabel);
		panel.add(nameLabel);
		panel.add(nameIconLabel);
		panel.add(name);
		panel.add(cardNumLabel);
		panel.add(cardnumIconLabel);
		panel.add(cardNum);
		panel.add(dateIconLabel);
		panel.add(dateLabel);
		panel.add(codeLabel);
		panel.add(codeIconLabel);
		panel.add(code);
		panel.add(monthsList);
		panel.add(yearList);
		panel.add(savecardBtn);
	}

	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	public void configureLabels() {
		titleLabel = LabelFactory.createLabel("Add New Card", Color.black, FontFactory.poppins(16));
		titleLabel.setBounds(66, 68, 158, 31);
		
		cardImageLabel = new JLabel(cardImage);
		cardImageLabel.setBounds(51, 179, 274, 154);
		
		nameLabel = LabelFactory.createLabel("Card Holder", ColorResources.cardLabels, FontFactory.poppins(12));
		nameLabel.setBounds(23, 424, 328, 16);
		nameIconLabel = new JLabel(nameIcon);
		nameIconLabel.setBounds(27, 460, 16, 20);
		
		cardNumLabel = LabelFactory.createLabel("Card Number", ColorResources.cardLabels, FontFactory.poppins(12));
		cardNumLabel.setBounds(23, 508, 328, 16);
		cardnumIconLabel = new JLabel(cardnumIcon);
		cardnumIconLabel.setBounds(24, 543, 24, 18);
		
		dateLabel = LabelFactory.createLabel("Expire Date", ColorResources.cardLabels, FontFactory.poppins(12));
		dateLabel.setBounds(23, 590, 66, 17);
		dateIconLabel = new JLabel(dateIcon);
		dateIconLabel.setBounds(27, 625, 18, 20);
		
		codeLabel = LabelFactory.createLabel("CCV", ColorResources.cardLabels, FontFactory.poppins(12));
		codeLabel.setBounds(200, 590, 27, 17);
		codeIconLabel = new JLabel(codeIcon);
		codeIconLabel.setBounds(203, 625, 17, 20);	
	}

	public void configureTextField() {
		name = TextFieldFactory.createTextField("Enter full name", Color.white, Color.black, FontFactory.poppins(14));
		name.setBounds(64, 459, 270, 23);
		name.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				name.setText("");
			}
		});
		
		cardNum = TextFieldFactory.createTextField("Enter card number", Color.white, Color.black, FontFactory.poppins(14));
		cardNum.setBounds(64, 542, 270, 23);
		cardNum.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cardNum.setText("");
			}
		});
		
		code = TextFieldFactory.createTextField("Enter CCV", Color.white, Color.black, FontFactory.poppins(14));
		code.setBounds(238, 625, 95, 23);
		code.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				code.setText("");
			}
		});


	}
	
	public void configureButtons() {
		exitBtn = ButtonFactory.createButtonIcon(exitImage);
		exitBtn.setBounds(24, 70, 24, 24);
		
		savecardBtn = ButtonFactory.createButton("Save Card", FontFactory.poppins(15), ColorResources.bgLoginWindow, Color.white);
		savecardBtn.setBounds(30, 700, 315, 48);
	}

	public void configureComboBox() {
		monthsList.setBounds(63, 625, 70, 23);
		yearList.setBounds(63, 650, 100, 23);
	}
	
	public void addListeners() {
		exitBtn.addActionListener(this);
		name.addActionListener(this);
		cardNum.addActionListener(this);
		code.addActionListener(this);
		monthsList.addActionListener(this);
		yearList.addActionListener(this);
		savecardBtn.addActionListener(this);		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		String cn = cardNum.getText();
		String n = name.getText();

		String m = (String) monthsList.getSelectedItem();
		int month = Integer.parseInt(m);

		String y = (String) yearList.getSelectedItem();
		int year = Integer.parseInt(y);

		String cod = code.getText();

		// create card object and check for validation
		cc = new CreditCard(cn, n, month, year, cod);
		if(e.getSource() == savecardBtn) {
			cc.checkValidation();
		}
		if(e.getSource() == exitBtn) {
			this.dispose();
			new PaymentMethodsWindow();
		}
		
	}

}
