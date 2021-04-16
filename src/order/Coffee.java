package order;

public class Coffee  extends Product{

	private String type;
	
	public Coffee(String name, String description, double price, String path, String type) {
		super(name, description, price, path);
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

}

