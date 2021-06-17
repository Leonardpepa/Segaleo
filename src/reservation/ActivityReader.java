package reservation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import resources.TextResources;

/*	this class is responsible for reading the activities from the files
 * 	and provides the program with array lists that contain the activities
 * 	also provides useful methods to find an activity and returns it
 */
public class ActivityReader {

	private static ArrayList<Activity> activitiesList;
	private int id = 0;
	
	//Initializing the list
	public ActivityReader() {
		activitiesList = new ArrayList<>();
		readActivity("files/activities/Activity",  TextResources.endpointPath);
	}
	
	private void readActivity(String pathName, String language) {
		String line;
		String name;
		int price;
		String path;
		int availability;
		String hour1;
		String hour2;
		
		BufferedReader inputReader = null;
		FileReader reader = null;
		
		ArrayList<String> hour = new ArrayList<String>();
		Activity a;
		//File format: the # is used to separate the question from the answer
		File activityFile = new File(pathName + language);
		try {
			reader = new FileReader(activityFile);
			inputReader = new BufferedReader(reader);
			
			/*
			 * The reader reads each line, separates name, price, path,availability and 2 hours for each activity and add them
			 * into the list
			 */
			line = inputReader.readLine();
			while(line != null) {
				name = line.split("#")[0];
				price = Integer.parseInt(line.split("#")[1]);
				path = line.split("#")[2];
				availability = Integer.parseInt(line.split("#")[3]);
				hour1 = line.split("#")[4];
				hour2 = line.split("#")[5];
				
				hour.add(hour1);
				hour.add(hour2);
				
				a=new Activity(name,price,path,availability,hour,id++);
				activitiesList.add(a);
				line = inputReader.readLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				inputReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	//getters and setters
	public static ArrayList<Activity> getActivitiesList() {
		return activitiesList;
	}
	public void setActivitiesList(ArrayList<Activity> activitiesList) {
		ActivityReader.activitiesList = activitiesList;
	}
	
	//search for an activity by id
	public static Activity findActivity(int id) {
		for(Activity act: activitiesList) {
			if(act.getId() == id) {
				return act;
			}
		}
		return null;
	}

}
