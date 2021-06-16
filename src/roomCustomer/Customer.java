package roomCustomer;

import java.io.Serializable;
import java.util.ArrayList;

import order.Coupon;
import order.Order;
import reservation.Reservation;

/*
 * This class represents a hotel customer as object.
 * It saves the name, the email, the room, the coupons, the orders and the reservations of an customer.
 */
 
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 4759859413823365345L;
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

	public void addCoupons(Coupon coupon) {
		coupons.add(coupon);
	}


	public void addOrders(Order order) {
		orders.add(order);
	}
	
	public void removeOrder(Order order) {
		this.orders.remove(order);
	}

	public void removeReservation(Reservation reservation) {
		this.reservations.remove(reservation);
	}


	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}
	
	public void removeCoupon(Coupon coupon) {
		this.coupons.remove(coupon);
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}
	
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}
	
	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	public void removeProduct() {

	}
}