package gui.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import resources.ColorResources;
import resources.TextResources;

public class CompleteReservationWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel insidePanel;
	private JLabel completeTitle;
	private JLabel message;
	private JLabel orderComplLabel;
	private JButton rateActivity;
	
	ImageIcon orderComplImage = new ImageIcon("buttonImages/order-complete.png");
	
	ImageIcon exitImage = new ImageIcon("buttonImages/exit button.png");
	JButton exitBtn;
	
	public CompleteReservationWindow() {
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}
	
public void initializePanelToFrame() {
		
		new TextResources().changeLanguage();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(375, 812));
		panel.setBackground(new Color(216, 223, 224));
		
		
		configureButtons();
		configurateLabels();
		addComponentsToPanel();
		//addListeners();
		
		this.setContentPane(panel);
		this.pack();
	}

public void windowsConfiguration() {
	this.setTitle("Hotel PDA Sample");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setLocationRelativeTo(null);
}

public void showWindow(JFrame frame, boolean show) {
	frame.setVisible(show);
}

public void addComponentsToPanel() {
	panel.add(exitBtn);
	panel.add(completeTitle);
	
	//initialize insidePanel and add it to panel
	insidePanel = new JPanel();
	insidePanel.setPreferredSize(new Dimension(375, 700));
	insidePanel.setBackground(new Color(255, 255, 255));
	insidePanel.setBounds(0, 100, 375, 762);
	insidePanel.setLayout(null);
	
	panel.add(insidePanel);
	
	//add components to insidePanel
	insidePanel.add(orderComplLabel);
	insidePanel.add(message);
	insidePanel.add(rateActivity);

	
}

public void configureButtons() {
	exitBtn = ButtonFactory.createButtonIcon(exitImage);
	exitBtn.setBounds(25, 50, 15, 15);
	
	rateActivity = ButtonFactory.createButton(TextResources.rateActivity, FontFactory.robboto(20), ColorResources.bgMainWindowBtn, ColorResources.frMainWindowBtn);
	rateActivity.setBounds(70, 480, 230, 48);
		
}
public void configurateLabels() {
	completeTitle = new JLabel("Reservation Completed");
	completeTitle.setFont(new Font("Sans-Serif", Font.PLAIN, 23));
	completeTitle.setBounds(60,40,300,35);
	
	orderComplLabel = new JLabel(orderComplImage);
	orderComplLabel.setBounds(50, 100, 280, 280);
	
	message = new JLabel(String.format("<html><body style=\"text-align: center;\">%s</body></html>","Your reservation has been accepted.We will be waiting for your arrival."));
	message.setFont(FontFactory.avenir(15));
	message.setBounds(40,400,300,40);
	
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}

	
}
