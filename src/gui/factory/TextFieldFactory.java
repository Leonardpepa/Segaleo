package gui.factory;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TextFieldFactory {

	public static JTextField createTextField(String text, Color backgroundColor, Color foregroundColor, Font font) {
		JTextField field = new JTextField(text);
		field.setBackground(backgroundColor);
		field.setForeground(foregroundColor);
		field.setFont(font);
		return field;
	}
	
	public static JPasswordField createPasswordTextField(String text, Color backgroundColor, Color foregroundColor, Font font) {
		JPasswordField field = new JPasswordField(text);
		field.setBackground(backgroundColor);
		field.setForeground(foregroundColor);
		field.setFont(font);
		return field;
	}

}
