package gui.factory;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BackgroundFactory extends JLabel {

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
