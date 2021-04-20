package gui.factory;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
//		popupPanel = new JPanel();
		this.setBounds(146, 620, 144,109);
		this.setBackground(new Color(153, 190, 193));
		
		
		languageLabel = LabelFactory.createLabel(TextResources.languageLabel, new Color(32, 150, 171), FontFactory.poppins(18));
		languageLabel.setPreferredSize(new Dimension(120, 35));

		
		greek = ButtonFactory.createButton(TextResources.greek, FontFactory.poppins(18),new Color(153, 190, 193) ,new Color(205, 221, 223));
		english = ButtonFactory.createButton(TextResources.english, FontFactory.poppins(18), new Color(153, 190, 193), new Color(205, 221, 223));
		greek.setPreferredSize(new Dimension(100, 25));
		english.setPreferredSize(new Dimension(100, 25));
		
		

		
		this.add(languageLabel);
		this.add(greek);
		this.add(english);
		return this;
	}
	
}
