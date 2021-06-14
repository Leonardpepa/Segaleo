package gui.windows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;

import contact.Contact;
import contact.FAQ;
import gui.factory.BackgroundFactory;
import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import gui.factory.LogoFactory;
import gui.factory.TextAreaFactory;
import platformData.PlatformData;
import resources.ColorResources;
import resources.TextResources;

public class FaqWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6937638902732120929L;
	private JPanel panel;
	private Contact contact = new Contact();

	private String path = "buttonImages/Back Button";
	private String lang = TextResources.imageLang;
	private ImageIcon backImage = new ImageIcon(path + lang);
	private JButton backBtn;

	private JLabel faqLabel = new JLabel();

	private ArrayList<FAQ> faqs;
	private JLabel questions = new JLabel();
	private JTextArea answers = new JTextArea();

	public FaqWindow() {
		initializePanelToFrame();
		windowsConfiguration();
		showWindow(this, true);
	}

	public void windowsConfiguration() {
		this.setTitle("Segaleo FAQ Page");
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
		configureLabels();
		configureFaq();
		addComponentsToPanel();
		addListeners();

		this.setContentPane(panel);
		this.pack();
	}

	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	public void addComponentsToPanel() {

		panel.add(backBtn);
		panel.add(LogoFactory.addLogoScaled());
		panel.add(faqLabel);

		panel.add(BackgroundFactory.addBackgroundLight());

	}

	// CONFIGURING ALL THE COMPONENTS ADDED TO THE PANEL BY CATEGORY

	public void configureFaq() {

		faqs = contact.getFaqs();
		// y and z are used to change the horizontal position of each question and
		// answer
		int y = 250;
		int z = 270;
		for (FAQ faq : faqs) {
			questions = LabelFactory.createLabel(faq.getQuestion(), Color.black, FontFactory.poppins(12));
			questions.setBounds(22, y, 340, 20);
			panel.add(questions);
			y += 115;

			answers = TextAreaFactory.createTextArea(faq.getAnswer().toString(), Color.white,
					ColorResources.bgLoginWindow, FontFactory.poppins(13));
			answers.setBounds(22, z, 331, 80);
			answers.setEditable(false);
			answers.setWrapStyleWord(true);
			answers.setLineWrap(true);
			panel.add(answers);
			z += 115;
		}

	}

	public void configureButtons() {
		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(12, 40, 67, 45);
	}

	public void configureLabels() {
		faqLabel = LabelFactory.createLabelBG("FAQ", ColorResources.bgLoginWindow, Color.WHITE,
				FontFactory.poppins(16));
		faqLabel = LabelFactory.alignLabel(faqLabel, SwingConstants.CENTER, SwingConstants.CENTER);
		faqLabel.setBounds(20, 225, 118, 26);
	}

	public void addListeners() {
		backBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ContactWindow();				
			}
		});
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				PlatformData.saveData();
			    System.exit(0);
			}
		});

	}

}
