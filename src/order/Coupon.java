package order;

import java.io.Serializable;
import java.util.Date;

public class Coupon implements Serializable {
  
	private static final long serialVersionUID = -1867457439069702703L;
	
	private String code;
    private Date date;

    public Date getDate() {
		return date;
	} 

	public void setDate(Date date) {
		this.date = date;
	}

	public Coupon(String code, Date date) {
        this.code = code;
        this.date = date;
    }

    public String getCode() {
        return code;
    }
}
