package rating;

public class Rating {

	private int numOfStars;
	private String comments;
	
	public Rating(int numOfStars, String comments) {
		//it depends on what user is going to click
		this.numOfStars = numOfStars;
		this.comments = comments;
	}
	
	public void setStars(){
	  this.numOfStars = numOfStars;
	}
	
	public int getStars() {
		return numOfStars;
	}
	
	public void setComments() {
		this.comments = comments;
	}
	
	public String getComments() {
		return comments;
	}

}

