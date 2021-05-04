package roomCustomer;

public class Customer{
	
	private String name;
	private String email;
	private Room room = null;
	
	
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