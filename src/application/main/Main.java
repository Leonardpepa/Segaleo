
package application.main;

import gui.windows.*;

/*
 * The program is developed with Java 11
 */
public class Main {
	
	
	/*	
	 * The main class is used to call the LoginWindow class, so the user can enter his credentials and use the app
	 *	
	 *	The profile our professor can use to test the app is: 
	 * 	room number: 115
	 * 	password: Achat115
	 * 	name: Achat
	 * 	email: achat@uom.edu.gr 
	 * 
	 *  The email that is associated with the profile can be used to retrieve the password via email.
	 * 
	 * 	You can use or modify any profile that exists in the path files/roomCustomer/Room-Customer.txt
	 * 	the format is roomnumber#password#name#email (# represents the divider of the variables)
	 * 
	 * 
	 * 	General Info:
	 * 
	 * 	External jars are included in the external jars folder. The class path is configured to link them to the program so the user
	 * 	doesn't have to add them manually (if a problem occurs you can link them by going to build path > configure build path > add external jars)
	 * 
	 * 	The external jars used are mail.jar and activation.jar. These can help sending an email to the customer.
	 * 	***Sometimes  the email can't be sent due to server connection issues and it throws a server error ***
	 * 	SOS! You have to sign in in segaleo's email first (in your browser) before you try to click the Remind me button as a user/customer.
	 * 
	 * 	The project doesn't use a database to store or retrieve data.
	 *  All of the data used for products, activities, customers and more are stored in .txt files .
	 * 	
	 * 	The state of the program is stored in a binary file.
	 *  This way the user can still view his orders/reservations/coupons on his profile. 
	 *  The binary file is also used to store the rating of each product/reservation, so an average rating can be displayed.
	 *  
	 *  Custom libraries have been developed from the team to help us create the GUI windows faster and efficiently.	
	 * 	
	 * 
	 * 	SDS Use Cases we didn't fulfill:
	 * 
	 * 	Customizable options for each product like adding sugar to the coffee etc
	 * 	Message contact with the hotel (we send an email to the user that we received his message but we can't actually reach him back)
	 *  Rating the app. We realized that this function wouldn't have any impact on the overall use of the app.
	 * 
	 */
	
	public static void main(String[] args) {
		new LoginWindow();
	}
}
