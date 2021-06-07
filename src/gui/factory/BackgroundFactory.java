package gui.factory;



import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BackgroundFactory extends JLabel {
	
	/*
	 * This Class is used throughout the whole project to set a background picture for the panels
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 6059076940593950003L;
	static ImageIcon backgroundImage = new ImageIcon("Background Images/background.png");
	static JLabel backgroundLabel;

	static ImageIcon backgroundImageDark = new ImageIcon("Background Images/loginBackground.png");
	static JLabel backgroundLabelDark;

	public static JLabel addBackgroundLight() {
		backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0, 0, 375, 812);
		return backgroundLabel;
	}

	public static JLabel addBackgroundDark() {
		backgroundLabelDark = new JLabel(backgroundImageDark);
		backgroundLabelDark.setBounds(0, 0, 375, 812);
		return backgroundLabelDark;
	}

}
