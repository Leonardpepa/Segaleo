package roomCustomer;

import java.io.Serializable;

/*
 * This class represents a hotel room as an object.
 * It saves the room number and the password of the room.
 */

public class Room implements Serializable{


	private static final long serialVersionUID = 3358518379349032467L;
	private int number;
	private String password;
	private Customer customer = null;


	public Room(int number, String password) {
		this.number = number;
		this.password = password;
	}


	public int getNumber() {
		return number;
	}


	public String getPassword() {
		return password;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer){
		this.customer = customer;
	}




}
