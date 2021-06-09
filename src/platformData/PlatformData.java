package platformData;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import roomCustomer.Customer;
import roomCustomer.Room;
import roomCustomer.RoomCustomerReader;

public class PlatformData {
	
	public static File data = new File("files/platformData/data.ser");
	
	//Saves date to the file
	public static void saveData() {
		
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(data));
			
			outputStream.writeObject(RoomCustomerReader.customers);
			outputStream.writeObject(RoomCustomerReader.rooms);
			
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Write problem");
		} 
	}
	
	//Loads date from the file
	public static void loadData(){
		
		
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(data));
			
			while(true){
				try {
					RoomCustomerReader.customers = (ArrayList<Customer>)inputStream.readObject();
					RoomCustomerReader.rooms = (ArrayList<Room>)inputStream.readObject();
				}
				catch(EOFException e) {
					break;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("No such a class into the file");
				}
			}
			
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Checks if the file is empty
	public static boolean checkFile() {
		
		if(data.length() != 0) {
			return true;}
		else
			return false;
	}
}
