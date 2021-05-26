package reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class Activity 
{
	private String name;
	private int price;
	private String path;
	private int availability;
	private ArrayList<String> hour;
	private static int [][] a = new int[7][14];
	private static ArrayList<Activity> activities = new ActivityReader().getActivitiesList();
	
	private int selhour ;
	private int selday ;
	
	// set hour and day 
	public void setSelHour(int hour) {
		this.selhour = hour;
	}

	public void setSelDay(int day) {
		this.selday = day;
	}
	public void PRINT()
	{
		System.out.print(selhour + "and " + selday);
	}
	
	public Activity(String name,  int price, String path, int availability, ArrayList<String> hour){
		this.name = name;
		this.price = price;
		this.path = path;
		this.availability = availability;
		this.hour = hour;

	}

	//Getters
	public String getName() {
		return name;
	}
	
	public int getPrice() {
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
	

}