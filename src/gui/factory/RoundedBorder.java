package gui.factory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class RoundedBorder implements Border {
	/*
	 * This Class is used to create rounded borders for some of the inside panels of the GUI windows
	 */

	    private int radius;


	    public RoundedBorder(int radius) {
	        this.radius = radius;
	    }


	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }


	    public boolean isBorderOpaque() {
	        return true;
	    }


	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	    	g.setColor(Color.LIGHT_GRAY);
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}

