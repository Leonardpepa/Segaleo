package platformData;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import menu.Menu;
import order.Order;
import order.Product;
import reservation.Activity;
import reservation.ActivityReader;
import reservation.Reservation;
import roomCustomer.Customer;
import roomCustomer.Room;
import roomCustomer.RoomCustomerReader;

public class PlatformData {

	public static File data = new File("files/platformData/data.ser");

	// Saves date to the file
	public static void saveData() {

		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(data));

			outputStream.writeObject(RoomCustomerReader.customers);
			outputStream.writeObject(RoomCustomerReader.rooms);

			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Loads date from the file
	@SuppressWarnings("unchecked")
	public static void loadData() {

		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(data));

			while (true) {
				try {
					RoomCustomerReader.customers = (ArrayList<Customer>) inputStream.readObject();
					RoomCustomerReader.rooms = (ArrayList<Room>) inputStream.readObject();
					loadReviews(RoomCustomerReader.customers);
				} catch (EOFException e) {
					break;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//loads all the reviews from each customer to each product / activities
	public static void loadReviews(ArrayList<Customer> customers) {
		for(Customer customer: customers) {
			for(Order order: customer.getOrders()) {
				for(Product product: order.getProducts()) {
					Product foundProduct = Menu.findProduct(product.getName());
					if(order.getRating() != null && foundProduct != null) {
						foundProduct.addRating(order.getRating());
					}
				}
			}
			for(Reservation reservation: customer.getReservations()) {
				for(Activity activity: reservation.getActivities()) {
					Activity activityFound = ActivityReader.findActivity(activity.getName());
					if(reservation.getRating() != null && activityFound != null) {
						activityFound.addRating(reservation.getRating());
					}
				}
			}
		}
	}

	// Checks if the file is empty
	public static boolean checkFile() {
		return data.length() != 0;
	}
}