package gui.factory;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class TextAreaFactory {
	/**
	 * This Class is used to create text areas efficiently 
	 */

	public static JTextArea createTextArea(String text, Color backgroundColor, Color foregroundColor, Font font) {
		JTextArea area = new JTextArea(text);
		area.setBackground(backgroundColor);
		area.setForeground(foregroundColor);
		area.setFont(font);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		return area;
	}
	
	public static JPasswordField createPasswordTextArea(String text, Color backgroundColor, Color foregroundColor, Font font) {
		JPasswordField area = new JPasswordField(text);
		area.setBackground(backgroundColor);
		area.setForeground(foregroundColor);
		area.setFont(font);
		area.setEchoChar('*');
		return area;
	}
	
}
