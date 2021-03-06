 
package login;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import resources.TextResources;
import roomCustomer.Customer;
import roomCustomer.Room;
import roomCustomer.RoomCustomerReader;

public class Login {

	// This class logs in and out a user

	public static Customer loggedCustomer = null;

	public static boolean checkLogin(String numberString, String password) {
		ArrayList<Room> rooms = RoomCustomerReader.getRoomsList();

		try {
			int number = Integer.parseInt(numberString);

			/* The array index of the room we're looking for */
			int roomIndex;

			if (number / 100 == 1) {
				roomIndex = (number % 100) - 1;
			} else {
				roomIndex = (number / 100) * 10 + (number % 100) - 1;
			}
			// Checks if the login data is correct
			// If it is, saves the logged customer
			if (rooms.get(roomIndex).getPassword().equals(password)) {
				loggedCustomer = rooms.get(roomIndex).getCustomer();
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, TextResources.wrongNumberOrPass);
			return false;
		}
		if (loggedCustomer == null) {
			JOptionPane.showMessageDialog(null, TextResources.wrongNumberOrPass);

			int number = Integer.parseInt(numberString);

			/* The array index of the room we're looking for */
			int roomIndex;

			if (number / 100 == 1)
				roomIndex = (number % 100) - 1;
			else
				roomIndex = (number / 100) * 10 + (number % 100) - 1;

			// Checks if the login data is correct
			// If it is, saves the logged customer
			if (rooms.get(roomIndex).getPassword().equals(password)) {
				loggedCustomer = rooms.get(roomIndex).getCustomer();
				return true;
			} else

				return false;
		}
		return true;
	}

	//Logs out the user
	public static void logout() {
		loggedCustomer = null;
	}
}