package resources;

public class TextResources {
	public static boolean isEnglish = true;

	public static String roomService;
	public static String activities;
	public static String welcome;
	public static String loginto;
	public static String roomField;
	public static String passField;
	public static String loginBtn;
	public static String languageLabel;
	public static String greek;
	public static String english;
	public static String viewCart;
	public static String search;
	public static String deals;
	public static String main;
	public static String salads;
	public static String desserts;
	public static String coffee;
	public static String drinks;
	public static String appetizers;
	public static String explore;
	public static String breakfast;
	public static String endpointPath;
	public static String reservationCompleteTitle;
	public static String reservationCompleteMessage;
	public static String rate;
	public static String rateActivity;
	public static String orderCompleteTitle;
	public static String orderCompleteMessage;
	public static String orderCompleteCoupon;
	public static String seeMore;
	public static String activityHeader;

	public static String myCart;
	public static String couponCode;
	public static String submit;
	public static String orderNow;
	public static String reserveNow;
	public static String total;

	public static String forgotPassword;
	public static String txtPass;
	public static String txtSendEmail;
	public static String payment;
	public static String msgArea;
	public static String sendbtn;
	public static String contactLabel;
	public static String imageLang;
	public static String addCard;
	public static String nameCard;
	public static String numCard;
	public static String expireCard;
	public static String nameCardField;
	public static String numCardField;
	public static String saveCard;

	public static String ratingLabel;
	public static String ratingComment;

	public static String invalidCouponTitle;
	public static String invalidCoupon;
	public static String noCoupon;
	public static String valid;
	
	public static String cancelOrderTitle;
	public static String cancelOrder;
	public static String cancelReservationTitle;
	public static String cancelReservation;

	public static String orderErrorTitle;
	public static String orderError;
	public static String orderSuccessTitle;
	public static String orderSuccess;

	public static String chooseDay;
	public static String chooseTime;
	public static String choosePeople;
	public static String confirm;
	public static String roomBill;
	public static String paypal;
	public static String card;
	
	public static String noPaymentSelected;

	public void changeLanguage() {
		greek = isEnglish ? "Greek" : "Ελληνικά";
		english = isEnglish ? "English" : "Αγγλικά";
		roomService = isEnglish ? "ROOM SERVICE" : "ΠΑΡΑΓΓΕΛΙΑ";
		activities = isEnglish ? "ACTIVITIES" : "ΔΡΑΣΤΗΡΙΟΤΗΤΕΣ";
		welcome = isEnglish ? "Welcome to Segaleo" : "Καλώς ήρθατε στο Segaleo";
		loginto = isEnglish ? "Login to your account" : "Συνδεθείτε στον λογαριασμό σας";
		roomField = isEnglish ? "  Room Number" : "  Αριθμός Δωματίου";
		passField = isEnglish ? "  Password" : "  Κωδικός";
		loginBtn = isEnglish ? "LOGIN" : "Σύνδεση";
		languageLabel = isEnglish ? "Languages" : "Γλώσσες";
		viewCart = isEnglish ? "View Cart" : "Δες το καλάθι";
		search = isEnglish ? "Search" : "Αναζήτηση";
		deals = isEnglish ? "Deals of the day" : "Προσφορές της ημέρας";
		main = isEnglish ? "Main" : "Κυρίως";
		salads = isEnglish ? "Salads" : "Σαλάτες";
		desserts = isEnglish ? "Desserts" : "Γλυκά";
		coffee = isEnglish ? "Coffees" : "Καφέδες";
		drinks = isEnglish ? "Drinks" : "Ποτά";
		appetizers = isEnglish ? "Appetizers" : "Ορεκτικά";
		breakfast = isEnglish ? "Breakfast" : "Πρωινό";
		explore = isEnglish ? "Explore by category" : "Εξερευνήστε από τις κατηγορίες";
		endpointPath = isEnglish ? "English.txt" : "Greek.txt";
		reservationCompleteTitle = isEnglish ? "Reservation Completed" : "Η Κράτηση Ολοκληρώθηκε";
		reservationCompleteMessage = isEnglish
				? "Your reservation has been accepted. We will be waiting for your arrival."
						: "Η κράτηση σας ολοκληρώθηκε με επιτυχία. Σας περιμένουμε.";
		rate = isEnglish ? "Rating" : "Αξιολόγηση";
		rateActivity = isEnglish ? "Rate this Αctivity" : "Αξιολόγησε αυτή τη δραστηριότητα";
		orderCompleteTitle = isEnglish ? "Order Completed" : "Η Παραγγελία Ολοκληρώθηκε";
		orderCompleteMessage = isEnglish ? "Your order is accepted and is going to be with you in around 45 minutes."
				: "Η παραγγελία σας ολοκληρώθηκε με επιτυχία και θα βρίσκεται στο δωματό σας σε περίπου 45 λεπτά.";
		orderCompleteCoupon = isEnglish ? "Your coupon for your next purchase is: " : "Το κουπόνι σας για την επόμενη παραγγελία είναι: ";
		seeMore = isEnglish ? "See More" : "Δείτε Περισσότερα";
		activityHeader = isEnglish ? "Choose the activity that best suits your vacation"
				: "Διάλεξε την δραστηριότητα που ταιριάζει καλύτερα στις διακοπές σου";

		myCart = isEnglish ? "My Cart" : "Το καλάθι μου";
		couponCode = isEnglish ? "Coupon code" : "Κωδικός Κουπονιού";
		submit = isEnglish ? "Submit" : "Υποβολή";
		orderNow = isEnglish ? "Order Now" : "Παραγγείλτε Τώρα";
		reserveNow = isEnglish ? "Reserve Now" : "Κάντε Κράτηση Τώρα";
		total = isEnglish ? "Total" : "Συνολικά";

		payment = isEnglish ? "Payment Methods" : "Τρόποι Πληρωμής";
		forgotPassword = isEnglish ? "Forgot Password" : "Ξέχασα τον κωδικό";
		txtPass = isEnglish ? "Don't know your password?" : "Δεν ξέρεις τον κωδικό σου;";
		txtSendEmail = isEnglish ? "We can send you a reminder email!" : "Μπορούμε να σου στείλουμε ένα email!";
		msgArea = isEnglish ? "Write your message here" : "Γράψτε το μήνυμα σας εδώ";
		sendbtn = isEnglish ? "Send" : "Αποστολή";
		contactLabel = isEnglish ? "CONTACT US" : "ΕΠΙΚΟΙΝΩΝΙΑ";
		imageLang = isEnglish ? ".png" : "GR.png";
		addCard = isEnglish ? "Add New Card" : "Προσθέστε κάρτα";
		nameCard = isEnglish ? "Card Holder" : "Κάτοχος κάρτας";
		numCard = isEnglish ? "Card Number" : "Αριθμός κάρτας";
		expireCard = isEnglish ? "Expiration Date" : "Ημερομηνία λήξης";
		nameCardField = isEnglish ? "Enter full name" : "Εισάγετε ονοματεπώνυμο";
		numCardField = isEnglish ? "Enter card number" : "Εισάγετε αριθμό κάρτας";
		saveCard = isEnglish ? "Save Card" : "Αποθήκευση κάρτας";

		ratingLabel = isEnglish ? "Rate Activities" : "Αξιολόγησε τις δραστηριότητες";
		ratingComment = isEnglish ? "Leave your comment about this activity here..." : "Σχολίασε την εμπειρία σου για την συγκεκριμένη δραστηριότητα εδώ";

		invalidCouponTitle = isEnglish ? "Error" : "Σφάλμα";
		invalidCoupon = isEnglish ? "Invalid Coupon" : "Μη έγκυρο Κουπόνι";
		noCoupon = isEnglish ? "You have no coupons" : "Δεν έχεις κουπόνια";
		valid = isEnglish ? "Valid Until" : "Έγκυρο εως";

		cancelOrderTitle = isEnglish ? "Cancel Order" : "Ακύρωση Παραγγελίας";
		cancelOrder = isEnglish ? "Do you want to cancel the order?" : "Θέλετε να ακυρώσετε την παραγελλία σας;";
		cancelReservationTitle = isEnglish ? "Cancel Reservation" : "Ακύρωση Κράτησης";
		cancelReservation = isEnglish ? "Do you want to cancel the reservation?" : "Θέλετε να ακυρώσετε την κράτησή σας;";

		orderErrorTitle = isEnglish ? "Invalid Order" : "Μη Έγκυρη Παραγγελία";
		orderError = isEnglish ? "Minimum order price is 10€" : "Το ελάχιστο ποσό παραγγελίας είναι 10€";
		orderSuccessTitle = isEnglish ? "Success" : "Επιτυχία";
		orderSuccess = isEnglish ? "Your order is complete" : "Η παραγγελία σας ολοκληρώθηκε";

		chooseDay = isEnglish ? "Choose Day" : "Διάλεξε Μέρα";
		chooseTime = isEnglish ? "Choose Time" : "Διάλεξε Ώρα";
		choosePeople = isEnglish ? "Choose People" : "Διάλεξε Άτομα";
		confirm = isEnglish ? "Confirm" : "Επιβεβαίωση";
		
		roomBill = isEnglish ? "Room Bill" : "Χρέωση στο δωμάτιο";
		paypal = isEnglish ? "Paypal" : "Πληρωμή με πέιπαλ";
		card = isEnglish ? "Card" : "Πληρωμή με κάρτα";
		
		noPaymentSelected = isEnglish ? "You need to choose payment method for the order" : "Πρέπει να διαλέξετε τρόπο πληρώμης για την παραγγελία";
		
		
	}

}
