
package application.main;

import gui.windows.*;

/*
 * The program is developed with java 11
 */
public class Main {
	
	
	/*	The profile our professor can use to test the app is: 
	 * 	room number: 115
	 * 	password: Achat115
	 * 	name: Achat
	 * 	email: achat@uom.edu.gr (the email that is associate with the profile can be used to retrieve the password and email will be send at users email)
	 * 
	 * 	Or you can use any profile exist in the path files/roomCustomer/Room-Customer.txt
	 * 	the format is roomnumber#pasword#name#email (# represent the divider of the variables)
	 * 
	 * 
	 *  Main class starts the program calling the login window class so the user can login and use the app
	 * 	
	 * 	External jars are included in the folder external jars and the class path is configured to link them to the program so the user hopefully 
	 * 	doesn't have to do it manually (if a problem occures you can link them by going to build path -> configure build path -> add external jars)
	 * 
	 * 	External jars are mail.jar and activation.jar. Are used to send an email to the customer
	 * 	***Sometimes it doesn't send it and it throws an error ***
	 * 
	 * 	The program doesn't have a data base the data used for products, activities and customer is stored in .txt files 
	 * 	with the format: # represent the divider of the variables
	 * 	
	 * 	The state of the program is stored in a binary file (the state is the orders/reservations/coupons/ratings each customer have) 
	 * 	
	 * 	Use cases we didn't full fill are the payment with paypal and room bill  (you can use them to pay but in the background it doesnt do anything more than storing the payment method and the total room bill)
	 * 	choices for each product like adding sugar to the coffee etc
	 * 	message contact with the hotel (we show the user a message that we recieve his message but we cant reach him back)
	 * 
	 * 	Custom libraries have been developed from the team to help us make the GUI more beautiful and easy	
	 * 
	 */
	
	public static void main(String[] args) {
		new LoginWindow();
	}
}
