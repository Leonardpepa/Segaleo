package reservation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import resources.TextResources;


public class ActivityReader {

	private ArrayList<Activity> activitiesList;
	
	//Initializing the list
	public ActivityReader() {
		activitiesList = new ArrayList<>();
		readActivity("files/activities/Activity",  TextResources.endpointPath);
	}
	
	private void readActivity(String pathName, String language) {
		String line;
		String id;
		String name;
		int price;
		String path;
		int availability;
		String hour;
		Activity a;
		//File format: the # is used to separate the question from the answer
		File activityFile = new File(pathName + language);
		try {
			FileReader reader = new FileReader(activityFile);
			BufferedReader inputReader = new BufferedReader(reader);
			
			line = inputReader.readLine();
			while(line != null) {
				id = line.split("#")[0];
				name = line.split("#")[1];
				price = Integer.parseInt(line.split("#")[2]);
				path = line.split("#")[3];
				availability = Integer.parseInt(line.split("#")[4]);
				hour = line.split("#")[5];
				
				a=new Activity(id,name,price,path,availability,hour);
				activitiesList.add(a);
				line = inputReader.readLine();
			}
			inputReader.close();
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<Activity> getActivitiesList() {
		return activitiesList;
	}

	public void setActivitiesList(ArrayList<Activity> activitiesList) {
		this.activitiesList = activitiesList;
	}

}
