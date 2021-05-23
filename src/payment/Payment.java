package payment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gui.windows.CreditCardWindow;

public class Payment  {
	
	public Payment() {
		
		
	}
	
	public void paypal()
	{
		try {
			String URL = "https://www.paypal.com/gr/signin";
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void card()
	{
		
	}
	
	public void roomBill()
	{
		
	}

	

}
