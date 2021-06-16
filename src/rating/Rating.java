package rating;

import java.io.Serializable;

/*	
 * this class is used to hold a rating for a product
 * 
 */

public class Rating implements Serializable{
	
	private static final long serialVersionUID = -5374276975811567971L;
	
	private int numOfStars;
	private String comments;
	
	public Rating(int numOfStars, String comments) {
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

