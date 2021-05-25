package payment;

import gui.windows.*;
import resources.TextResources;

//import gui.windows.CreditCardWindow;

public class Payment {
	
	public Payment() {
		
		
	}
	
	public void paypal()
	{
		new CartWindow(TextResources.paypal);
	}
	
	public void card()
	{
		new CreditCardWindow();
	}
	
	public void roomBill()
	{
		new CartWindow(TextResources.roomBill);
	}

}
