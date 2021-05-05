package roomCustomer;

import java.util.ArrayList;

import order.Coupon;
import order.Order;

public class Customer{
	
	private String name;
	private String email;
	private Room room = null;
	private ArrayList<Coupon> coupons = new ArrayList<Coupon>();
	private ArrayList<Order> orders = new ArrayList<Order>();
	private ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	
	
	public Customer(String name, String email) {
		this.name = name;
		this.email = email;
	}

	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
}