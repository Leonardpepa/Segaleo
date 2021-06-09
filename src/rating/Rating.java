package rating;

import java.io.Serializable;

public class Rating implements Serializable{

	private int numOfStars;
	private String comments;
	
	public Rating(int numOfStars, String comments) {
		//it depends on what user is going to click
		this.numOfStars = numOfStars;
		this.comments = comments;
	}

	public int getNumOfStars() {
		return numOfStars;
	}

	public void setNumOfStars(int numOfStars) {
		this.numOfStars = numOfStars;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}

