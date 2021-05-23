package gui.factory;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import resources.TextResources;

public class LabelFactory {

	public static JLabel createLabel(String text, Color color, Font font) {
		JLabel label = new JLabel(String.format("<html><body style=\"text-align: left;\">%s</body></html>",text));
		label.setForeground(color);
		label.setFont(font);
		return label;
	}
	
	public static JLabel createIconLabel(ImageIcon icon) {
		JLabel label = new JLabel();
		label.setIcon(icon);
		return label;
	}
	
	public static JLabel createLabelBG(String text, Color backgroundColor, Color foregroundColor, Font font){
		JLabel label = new JLabel(String.format("<html><body style=\"text-align: center;\">%s</body></html>",text));
		label.setBackground(backgroundColor);
		label.setForeground(foregroundColor);
		label.setFont(font);
		label.setOpaque(true);
		return label;
	}
	
	public static JLabel alignLabel(JLabel label, int horizontal, int vertical) {
		label.setHorizontalAlignment(horizontal);
		label.setVerticalAlignment(vertical);
		return label;
	}
}
