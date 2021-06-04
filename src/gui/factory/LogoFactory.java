package gui.factory;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LogoFactory {
	/*
	 * This Class is used to set a logo for the GUI windows either scaled or normal sized
	 */

	static ImageIcon logoscaled = new ImageIcon("logo/logo-scaled.png");
	static ImageIcon logo = new ImageIcon("logo/logo.png");
	static JLabel logoLabel;

	public static JLabel addLogoScaled() {
		logoLabel = new JLabel(logoscaled);
		logoLabel.setBounds(101, 50, 173, 173);
		return logoLabel;
	}

	public static JLabel addLogo() {
		logoLabel = new JLabel(logo);
		logoLabel.setBounds(101, 118, 173, 173);
		return logoLabel;
	}

}
