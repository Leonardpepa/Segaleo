package reservation;

import java.util.ArrayList;

import rating.Rating;


public class Activity 
{
	private String name;
	private double price;
	private String path;
	private int availability;
	private int id;
	private ArrayList<String> hour;
	private ArrayList<Rating> ratings;

	private static int [][] a = new int[7][14];
	private static ArrayList<Activity> activities = new ActivityReader().getActivitiesList();
	
	private int selhour = 0 ;
	private int selday = 0;
	private int column = 0;
	private int selpeople = 0;


	

	// set-get hour and day 
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

	public void PRINT()
	{
		System.out.print(this.selhour + "and " + this.selday);

	}
	
	public Activity(String name,  int price, String path, int availability, ArrayList<String> hour, int id){
		this.name = name;
		this.price = price;
		this.path = path;
		this.availability = availability;
		this.hour = hour;
		this.id = id;
		this.ratings = new ArrayList<Rating>();

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

	// for avaliability array
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

	public static void printArray() {
	for(int i=0; i<=6; i++) {
		for(int j=0; j<=13; j++) {
			System.out.println(a[i][j]);
		}
	}
}
	public boolean checkLimit() {
	if(this.getSelpeople() <= a[this.getSelday()][this.getSelhour() + this.getColumn()]){
 		a[this.getSelday()][this.getSelhour() + this.getColumn()] -= this.getSelpeople();
 		System.out.println("YES!");
 		System.out.println("apomenoun : " +a[this.getSelday()][this.getSelhour() + this.getColumn()] );
 		return true;
	}
	else {
		System.out.println("sorry!");
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
				sum += rating.getStars();
			}
			return ratings.size() == 0? 0 :sum/ratings.size();
		}

}