package roomCustomer;

import java.io.Serializable;

public class Room implements Serializable{
	
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
