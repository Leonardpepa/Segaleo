package reservation;

import java.util.ArrayList;
import java.util.HashMap;

public class Reservation 
{

	private double totalCost;
	private ArrayList<Activity> activities;
	public HashMap<Activity,Integer> act;

	public Reservation () 
	{
		activities = new ArrayList<Activity>();
		act = new HashMap<>();
		initializeHashMap();
	}
	
	public void addActivity(Activity activity) {
		if(!activities.contains(activity)) {
			activities.add(activity);
		}
		act.put(activity, act.get(activity)+1);
	}
	
	public void clearReservation()
	{
		activities = new ArrayList<>();
		initializeHashMap();
	}
	
	public void removeActivity(Activity activity) {
		if(act.get(activity) ==1) {
			activities.remove(activity);
			act.put(activity, act.get(activity) -1 );
		}
		else {
			act.put(activity, act.get(activity) - 1 );
		}
	}
	
	public double calcCost() 
	{	
		totalCost = 0;
		
		for(Activity activity : activities) {
			totalCost += act.get(activity) * activity.getPrice();
		}

		return totalCost;
	}
	
	public double getTotalCost() {
		return totalCost;
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}
	
	public void initializeHashMap() {
		for(Activity a: new ActivityReader().getActivitiesList()) {
			act.put(a, 0);
		}
	}

	

}
