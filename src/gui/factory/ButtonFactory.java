package gui.factory;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonFactory extends JButton{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JButton createButton(String text, Font font, Color backgroundColor, Color foregroundColor) {
		JButton button = new JButton();
		button.setText(text);
		button.setBackground(backgroundColor);
		button.setForeground(foregroundColor);
		button.setFont(font);
		button.setOpaque(true);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return  button;
	}
	
	public static JButton createButtonIcon(ImageIcon icon) {
		
		JButton button = new JButton();
		button.setIcon(icon);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setOpaque(false);
		button.setFocusPainted(true);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		return button;
	}
}
