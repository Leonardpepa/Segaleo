package roomCustomer;

public class Room {
	
	private int number;
	private String password;
	private Customer customer;
	
	
	public Room(int number, String password, Customer customer) {
		this.number = number;
		this.password = password;
		this.customer = customer;
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
	
	
	
	
}
