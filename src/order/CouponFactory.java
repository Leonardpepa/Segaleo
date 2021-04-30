package order;

import java.util.Date;
import java.util.Random;


public class CouponFactory {
    private String email = "phil@michalopoulos.gr";
    Random rand = new Random();

    public Coupon GenerateCoupon() {
        String code = "";
        //Βαλαμε στατικο για τεστ αλλα θα παιρνει ορισμα τον χρηστη και θα παιρνουμε απο κει το μειλ
        for(int i=0; i<3; i++) {
            code += email.toCharArray()[rand.nextInt(email.length())];
        }
        int randomNumber = rand.nextInt(999000 + 1 - 100000) + 1000000;
        code += Integer.toString(randomNumber);
        System.out.println(code);

        return new Coupon(code, new Date());
    }

    public boolean isValid(Coupon coupon) {
        return false;
    }
}
