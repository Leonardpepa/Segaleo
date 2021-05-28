package reservation;

import java.util.ArrayList;

public class Reservation 
{

	private double totalCost;
	private ArrayList<Activity> activities;

	public Reservation () 
	{
		activities = new ArrayList<Activity>();
	}
	
	public void addActivity(Activity activity) {
		activities.add(activity);
	}
	
	public void clearReservation()
	{
		activities = new ArrayList<>();
	}
	
	public void removeActivity(Activity activity) {
		activities.remove(activity);
	}
	
	public double calcCost() 
	{	
		totalCost = 0;
		
		for(Activity activity : activities) {
			totalCost += activity.getActivityCost();
		}

		return totalCost;
	}
	
	public double getTotalCost() {
		return totalCost;
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	

}
