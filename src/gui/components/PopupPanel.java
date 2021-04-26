package gui.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import resources.ColorResources;
import resources.TextResources;

public class PopupPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JLabel languageLabel;
	public JButton greek;
	public JButton english;
	
	public JPanel configurePopupWindow() {
		
		this.setBounds(146, 620, 144,109);
		this.setBackground(ColorResources.bgPopup);
		
		
		languageLabel = LabelFactory.createLabel(TextResources.languageLabel, ColorResources.frMainWindowBtn, FontFactory.poppins(18));
		languageLabel.setPreferredSize(new Dimension(120, 35));

		
		greek = ButtonFactory.createButton(TextResources.greek, FontFactory.poppins(18),ColorResources.bgPopup ,ColorResources.bgMainWindowBtn);
		english = ButtonFactory.createButton(TextResources.english, FontFactory.poppins(18), ColorResources.bgPopup, ColorResources.bgMainWindowBtn);
		greek.setPreferredSize(new Dimension(100, 25));
		english.setPreferredSize(new Dimension(100, 25));
		
		

		
		this.add(languageLabel);
		this.add(greek);
		this.add(english);
		return this;
	}
	
}