package roomCustomer;

public class Customer{
	
	private String name;
	private String email;
	private Room room;
	
	
	public Customer(String name, String email, Room room) {
		this.name = name;
		this.email = email;
		this.room = room;
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
	
	
}