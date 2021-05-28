package order;

public class Coffee  extends Product{

	private int size = 0;
	private int sugar = 0;
	private boolean cold = true;
	
	public Coffee(String name, String description, double price, String path, int id) {
		super(name, description, price, path, id);
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public int getSugar() {
		return sugar;
	}

	public void setSugar(int sugar) {
		this.sugar = sugar;
	}

	public boolean isCold() {
		return cold;
	}

	public void setCold(boolean cold) {
		this.cold = cold;
	}
}

