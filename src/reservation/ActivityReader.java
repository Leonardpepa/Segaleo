package reservation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import resources.TextResources;


public class ActivityReader {

	private static ArrayList<Activity> activitiesList;
	
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
		int id = 0;
		
		BufferedReader inputReader = null;
		FileReader reader = null;
		
		ArrayList<String> hour = new ArrayList<String>();
		Activity a;
		//File format: the # is used to separate the question from the answer
		File activityFile = new File(pathName + language);
		try {
			reader = new FileReader(activityFile);
			inputReader = new BufferedReader(reader);
			
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

	public static ArrayList<Activity> getActivitiesList() {
		return activitiesList;
	}

	public void setActivitiesList(ArrayList<Activity> activitiesList) {
		ActivityReader.activitiesList = activitiesList;
	}
	
	public static Activity findActivity(String name) {
		for(Activity act: activitiesList) {
			if(act.getName().equalsIgnoreCase(name)) {
				return act;
			}
		}
		return null;
	}

}
