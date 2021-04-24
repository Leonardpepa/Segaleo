package gui.factory;

import java.awt.Cursor;
import java.awt.Dimension;

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
	
	private ImageIcon bagIcon;
	private JLabel bagLabel;
	private JLabel viewCart;
	private JLabel priceLabel;
	
	public ViewCartPanel() {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(375,94));
		this.setBounds(0, 718, 375, 94);
		configureComponents();
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(bagLabel);
		this.add(viewCart);
		this.add(priceLabel);
	}
	
	private void configureComponents() {
		//TODO make the text of the price change dynamically
		bagIcon = new ImageIcon("buttonImages/Bag.png");
		
		priceLabel = LabelFactory.createLabel("28,00", ColorResources.frPopup, FontFactory.poppins(14));
		priceLabel.setBounds(301, this.getHeight()/2 - 15, 49, 20);
		
		viewCart = LabelFactory.createLabel(TextResources.viewCart, ColorResources.frPopup, FontFactory.poppins(14));
		viewCart.setBounds(67, this.getHeight()/2 - 15, 200, 20);
		
		bagLabel = LabelFactory.createIconLabel(bagIcon);
		bagLabel.setBounds(29, this.getHeight()/2 - 15, 25, 25);
		bagLabel.setText("0");
	}

	public JLabel getPriceLabel() {
		return priceLabel;
	}

	public void setPriceLabel(JLabel priceLabel) {
		this.priceLabel = priceLabel;
	}
	
	
	
}
