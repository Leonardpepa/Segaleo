package roomCustomer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomCustomerReader {
	/*
	 * This class reads all the Hotel infos from a txt file.
	 * 
	 * Also saves the customes and the rooms that are read before.
	 */

	public static ArrayList<Customer> customers;
	public static ArrayList<Room> rooms;
	
	public RoomCustomerReader(){
		customers = new ArrayList<Customer>();
		rooms = new ArrayList<Room>();
		readFile();
	}
	
	private void readFile() {
		/*
		 * Reads the customers and the rooms of the hotel, from the txt file and
		 * saves them to the correct ArrayLists
		 */
		Scanner scanner = null;
		try {
			String customerName = null;
			String customerEmail = null;
			int roomNumber = 0;
			String roomPassword = null;
			
			
			File roomCustomerFile = new File("./files/roomCustomer/Room-Customer.txt");
			scanner = new Scanner(roomCustomerFile);
			
			while(scanner.hasNextLine())
			{
				String line = scanner.nextLine();


				roomNumber = Integer.parseInt(line.split("#")[0]);
				roomPassword = line.split("#")[1];
				
				
				/*Checks of the room is empty*/
				if(!roomPassword.equals("null"))
				{
					customerName = line.split("#")[2];
					customerEmail = line.split("#")[3];
				}
				
				
				customers.add(new Customer(customerName, customerEmail));
				rooms.add(new Room(roomNumber, roomPassword));
				
				AssociateRoomCustomer();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	private void AssociateRoomCustomer() {
		/*Associates the last added room to the last added customer
		 * and the opposite
		 */
		
		customers.get(customers.size()-1).setRoom(rooms.get(rooms.size()-1));
		rooms.get(customers.size()-1).setCustomer(customers.get(rooms.size()-1));
	}
	
	
	public static ArrayList<Customer> getCustomersList(){
		return customers;
	}
	
	
	public static ArrayList<Room> getRoomsList(){
		return rooms;
	}
	
}
