package gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
	
	/*
	 * 	this class is a JPanel class that ovverides the paintComponent method so we can change the border as we like
	 */

	private static final long serialVersionUID = 5852875706215881488L;
	private Color backgroundColor;
	private int cornerRadius = 15;

	public RoundedPanel(int radius, Color bgColor) {
		super();
		cornerRadius = radius;
		backgroundColor = bgColor;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension arcs = new Dimension(cornerRadius, cornerRadius);
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setColor(Color.WHITE);
		// Draws the rounded panel with borders.
		if (backgroundColor != null) {
			graphics.setColor(backgroundColor);
		} else {
			graphics.setColor(getBackground());
		}
		graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
		graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint border
	}
}
