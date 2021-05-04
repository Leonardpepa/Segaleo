
package login;

import java.util.ArrayList;

import roomCustomer.Room;
import roomCustomer.RoomCustomerReader;

public class Login{
	
	public static boolean checkLogin(String number, String password) {
		ArrayList<Room> rooms = RoomCustomerReader.getRoomsList();
		
		/* The index of the room we're looking for*/
		int roomIndex = Integer.parseInt(number)- 101;
		
		if(rooms.get(roomIndex).getPassword().equals(password))
			return true;
		else
			return false;
	}
}