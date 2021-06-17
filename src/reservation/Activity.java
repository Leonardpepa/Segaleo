package reservation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import rating.Rating;

/*	this class is responsible for creating activities, add ratings to them and 
 *  provides an array with availabilities for all the activities for one week, 
 *  it also provides methods to check if there is remaining space for people in an activity
 */
public class Activity implements Serializable {
	
	private static final long serialVersionUID = -1720481625116348505L;
	
	private String name;
	private double price;
	private String path;
	private int availability;
	private int id;
	private ArrayList<String> hour;
	private ArrayList<Rating> ratings;

	/*availability array for all the activities in the current week
	 * 7  lines, one for each day of the week, starts from sunday
	 * 14 columns, two for each activity because every one has 2 available hours
	 */
	private static int [][] a = new int[7][14]; 

	private int selhour = 0;
	private int selday = 0;
	private int column = 0; //the first column for each activity
	private int selpeople = 0;
	private Date selDate;
	
	//constructor
	public Activity(String name,  int price, String path, int availability, ArrayList<String> hour, int id){
		this.name = name;
		this.price = price;
		this.path = path;
		this.availability = availability;
		this.hour = hour;
		this.id = id;
		this.ratings = new ArrayList<Rating>();

	}


	// getters and setters for selected hour/day/column/people/date from calendar
	public int getSelhour() {
		return selhour;
	}
	public void setSelHour(int hour) {
		this.selhour = hour;
	}
	
	public int getSelday() {
		return selday;
	}
	public void setSelDay(int day) {
		this.selday = day;
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

	public Date getSelDate() {
		return selDate;
	}
	public void setSelDate(Date selDate) {
		this.selDate = selDate;
	}
	
	
	// fetters and setters for availability array a
	public static void setA(int[][] a) {
		Activity.a = a;
	}
	public static int[][] getA() {
		return a;
	}

	//Getters for the constructor's variables
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
	
	//availability array initialization
	public static void initialarray() {
		for(int i= 0; i<7; i++) {
			int j=0;
			for(Activity activity: ActivityReader.getActivitiesList()) {
				a[i][j]= activity.getAvailability();
				a[i][++j] = activity.getAvailability();
				j++;
			}
		}
	}

	// check if many people (SelPeople) can go to an activity
	public boolean checkLimit() {
		if(this.getSelpeople() <= a[this.getSelday()][this.getSelhour() + this.getColumn()]){
			a[this.getSelday()][this.getSelhour() + this.getColumn()] -= this.getSelpeople();
			return true;
		}
			return false;
	}
	
	//check if one more person (added to SelPeople) can go to an activity
	public boolean plusCheck() {
		if(a[this.getSelday()][this.getSelhour() + this.getColumn()]>= 1) {
			a[this.getSelday()][this.getSelhour() + this.getColumn()] -=1;
			return true;
		}
			return false;
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
	
	//add rating to the activity
	public void addRating(Rating rating) {
		ratings.add(rating);
	}
	
	//calculate average rating for the activity
	public double calcAvRating() {
		double sum = 0;
		for(Rating rating: ratings) {
			sum += rating.getNumOfStars();
		}
		return ratings.isEmpty() ? 0 :sum/ratings.size();
	}

}