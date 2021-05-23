
package login;

import java.util.ArrayList;

import roomCustomer.Customer;
import roomCustomer.Room;
import roomCustomer.RoomCustomerReader;

public class Login {

	// This class logs in and out an user

	public static Customer loggedCustomer = null;

	public static boolean checkLogin(String numberString, String password) {
		ArrayList<Room> rooms = RoomCustomerReader.getRoomsList();
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

	public static void logout() {
		loggedCustomer = null;
	}
}