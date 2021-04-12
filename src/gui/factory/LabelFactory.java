package gui.factory;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class LabelFactory {

	public static JLabel createLabel(String text, Color color, Font font) {
		JLabel label = new JLabel();
		label.setText(text);
		label.setForeground(color);
		label.setFont(font);
		return label;
	}
}
