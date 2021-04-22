package gui.factory;

import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import resources.ColorResources;
import resources.TextResources;

public class ViewCartPanel extends JPanel{

	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ImageIcon bagIcon = new ImageIcon("buttonImages/Bag.png");
	JLabel bagLabel;
	JLabel viewCart;
	JLabel priceLabel;
	
	public ViewCartPanel() {
		this.setLayout(null);
		this.setBounds(0, 718, 375, 94);
		configureComponents();
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(bagLabel);
		this.add(viewCart);
		this.add(priceLabel);
	}
	
	private void configureComponents() {
		//TODO make the text of the price change dynamically
				priceLabel = LabelFactory.createLabel("28,00", ColorResources.frPopup, FontFactory.poppins(14));
				priceLabel.setBounds(301, this.getHeight()/2 - 15, 49, 20);
				
				viewCart = LabelFactory.createLabel(TextResources.viewCart, ColorResources.frPopup, FontFactory.poppins(14));
				viewCart.setBounds(67, this.getHeight()/2 - 15, 67, 20);
				
				bagLabel = LabelFactory.createIconLabel(bagIcon);
				bagLabel.setBounds(29, this.getHeight()/2 - 15, 25, 25);
	}
	
	
}
