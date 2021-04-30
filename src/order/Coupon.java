package order;

import java.util.Date;

public class Coupon {
    private String code;
    private Date date;

    public Coupon(String code, Date date) {
        this.code = code;
        this.date = date;
    }

    public String getCode() {
        return code;
    }
}
