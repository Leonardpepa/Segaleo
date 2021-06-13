package reservation;

import java.io.Serializable;
import java.util.ArrayList;

import rating.Rating;


public class Activity implements Serializable {
	
	private static final long serialVersionUID = -1720481625116348505L;
	
	private String name;
	private double price;
	private String path;
	private int availability;
	private int id;
	private ArrayList<String> hour;
	private ArrayList<Rating> ratings;

	private static int [][] a = new int[7][14];
	private static ArrayList<Activity> activities = new ActivityReader().getActivitiesList();

	private int selhour =0;
	private int selday =0;
	private int column =0;
	private int selpeople =0;
	
	
	public Activity(String name,  int price, String path, int availability, ArrayList<String> hour, int id){
		this.name = name;
		this.price = price;
		this.path = path;
		this.availability = availability;
		this.hour = hour;
		this.id = id;
		this.ratings = new ArrayList<Rating>();

	}


	// set-get selected hour-day-people-column
	public void setSelHour(int hour) {
		this.selhour = hour;
	}

	public void setSelDay(int day) {
		this.selday = day;
	}

	public int getSelhour() {
		return selhour;
	}

	public int getSelday() {
		return selday;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getSelpeople() {
		return selpeople;
	}

	public void setSelpeople(int selpeople) {
		this.selpeople = selpeople;
	}

	public static int[][] getA() {
		return a;
	}

	//Getters
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getPath() {
		return path;
	}

	public int getAvailability() {
		return availability;
	}

	public ArrayList<String> getHour() {
		return hour;
	}

	public int getId() {
		return id;
	}
	
	//array for avaliabilities
	public static void initialarray() {
		for(int i= 0; i<7; i++) {
			int j=0;
			for(Activity activity:activities) {
				a[i][j]= activity.getAvailability();
				a[i][++j] = activity.getAvailability();
				j++;
			}
		}
	}

	public boolean checkLimit() {
		if(this.getSelpeople() <= a[this.getSelday()][this.getSelhour() + this.getColumn()]){
			a[this.getSelday()][this.getSelhour() + this.getColumn()] -= this.getSelpeople();
			return true;
		}
		else {
			return false;
		}
	}
	public boolean plusCheck() {
		if(a[this.getSelday()][this.getSelhour() + this.getColumn()]>= 1) {
			a[this.getSelday()][this.getSelhour() + this.getColumn()] -=1;
			return true;
		}
		else {
			return false;
		}
	}

	// for hashmap
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;  
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (id != other.getId()) {
			return false;        	
		}
		return true;
	}

	public void addRating(Rating rating) {
		ratings.add(rating);
	}

	public double calcAvRating() {
		double sum = 0;
		for(Rating rating: ratings) {
			sum += rating.getNumOfStars();
		}
		return ratings.isEmpty() ? 0 :sum/ratings.size();
	}

}