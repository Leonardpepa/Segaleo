package platformData;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import menu.Menu;
import order.Order;
import order.Product;
import reservation.Activity;
import reservation.ActivityReader;
import reservation.Reservation;
import roomCustomer.Customer;
import roomCustomer.Room;
import roomCustomer.RoomCustomerReader;

/*
 * 	this class is used to store the state of the app in a binary file
 * 	also to read from it
 */
public class PlatformData {

	public static File data = new File("files/platformData/data.ser");

	// Saves date to the file
	public static void saveData() {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(data));
			//storing users that use the app and each user has associations with classes like order, reservation etc  
			outputStream.writeObject(RoomCustomerReader.customers);
			//storing the rooms of the hotel
			outputStream.writeObject(RoomCustomerReader.rooms);
			//storing the array that is used to check for availabilities for each activity
			outputStream.writeObject(Activity.getA());
			//storing the total number of orders and reservations so we can use it for ids
			outputStream.writeObject(Order.numberOfOrders);
			outputStream.writeObject(Reservation.numberOfReservations);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
					Activity.setA(((int[][]) inputStream.readObject()));
					checkArrayAvailabilities(RoomCustomerReader.customers);
					Order.numberOfOrders = (Integer) inputStream.readObject();
					Reservation.numberOfReservations = (Integer) inputStream.readObject();
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
	//so we can see the state of the ratings
	public static void loadReviews(ArrayList<Customer> customers) {
		for(Customer customer: customers) {
			for(Order order: customer.getOrders()) {
				for(Product product: order.getProducts()) {
					Product foundProduct = Menu.findProduct(product.getId());
					if(order.getRating() != null && foundProduct != null) {
						foundProduct.addRating(order.getRating());
					}
				}
			}
			for(Reservation reservation: customer.getReservations()) {
				for(Activity activity: reservation.getActivities()) {
					Activity activityFound = ActivityReader.findActivity(activity.getId());
					if(reservation.getRating() != null && activityFound != null) {
						activityFound.addRating(reservation.getRating());
					}
				}
			}
		}
	}
	
	//if an activity's time has passed it resets the fields of the array is needs
	public static void checkArrayAvailabilities(ArrayList<Customer> customers) {
		Date today = new Date();
		for(Customer customer: customers) {
			for(Reservation reservation: customer.getReservations()) {
				for(Activity activity: reservation.getActivities()) {
					if(today.getTime() > activity.getSelDate().getTime()) {
						Activity.getA()[activity.getSelday()][activity.getSelhour() + activity.getColumn()] += activity.getSelpeople(); 
					}
				}
			}
		}
	}
	
	//reset an activity in the array if the reservation that contained this activity has been canceled
	public static void resetActivitiesFromArray(Reservation reservation) {
		for(Activity activity: reservation.getActivities()) {
			Activity.getA()[activity.getSelday()][activity.getSelhour() + activity.getColumn()] += activity.getSelpeople(); 
		}
	}

	// Checks if the file is empty
	public static boolean checkFile() {
		return data.length() != 0;
	}
}
