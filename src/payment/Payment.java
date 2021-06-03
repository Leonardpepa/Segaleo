package payment;



import java.awt.Color;

import javax.swing.JOptionPane;

import gui.windows.CartWindow;
import gui.windows.CreditCardWindow;
import resources.ColorResources;
import resources.TextResources;

public class Payment  {
	
	public Payment() {
		
		
	}
	
	public void paypal()
	{
		try {
			String URL = "https://www.paypal.com/gr/signin";
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
			CartWindow.paymentMethods.setText(TextResources.paypal);
			CartWindow.paymentMethods.setBackground(ColorResources.paypal);
			CartWindow.paymentMethods.setForeground(Color.BLACK);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void card()
	{
		new CreditCardWindow();
	}
	
	public void roomBill()
	{
		CartWindow.paymentMethods.setText(TextResources.roomBill);
		CartWindow.paymentMethods.setBackground(ColorResources.roomBil);
		CartWindow.paymentMethods.setForeground(new Color(34,139,34));
	}

	

}
