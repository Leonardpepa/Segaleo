package resources;

public class TextResources {
	public static boolean  isEnglish = true;
	
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
	public static String rateActivity;
	public static String orderCompleteTitle;
	public static String orderCompleteMessage;
	

	public  void changeLanguage() {
		greek = isEnglish ? "Greek" : "Ελληνικά";
		english = isEnglish ? "English" : "Αγγλικά";
		roomService = isEnglish ? "ROOM SERVICE" : "ΠΑΡΑΓΓΕΛΙΑ";
		activities = isEnglish ? "ACTIVITIES" : "ΔΡΑΣΤΗΡΙΟΤΗΤΕΣ";
		welcome = isEnglish ? "Welcome to Segaleo" : "Καλώς ήρθατε στο Segaleo";
		loginto = isEnglish ?  "Login to your account" : "Συνδεθείτε στον λογαριασμό σας";
		roomField = isEnglish ?  "  Room Number" : "  Αριθμός Δωματίου";
		passField = isEnglish ? "  Password" : "  Κωδικός‚";
		loginBtn = isEnglish ? "LOGIN" : "Σύνδεση";
		languageLabel = isEnglish ? "Languages" : "Γλώσσες";
		viewCart = isEnglish ? "View Cart" : "Δες το καλάθι";
		search = isEnglish ? "Search" : "Αναζήτηση";
		deals = isEnglish ? "Deals of the day" : "Προσφορές της ημέρας";
		main = isEnglish ? "Main" : "Κυρίως";
		salads = isEnglish ? "Salads" : "Σαλάτες";
		desserts = isEnglish ? "Desserts" : "Γλυκά";
		coffee = isEnglish ? "Coffes" : "Καφέδες";
		drinks = isEnglish ? "Drinks" : "Ποτά";
		appetizers = isEnglish ? "Appetizers" : "Ορεκτικά";
		breakfast = isEnglish ? "Breakfast" : "Πρωινό";
		explore = isEnglish ? "Explore by category" : "Εξερευνήστε από τις κατηγορίες";
		endpointPath = isEnglish ? "English.txt" : "Greek.txt";
		reservationCompleteTitle = isEnglish ? "Reservation Completed" : "Η Κράτηση Ολοκληρώθηκε";
		reservationCompleteMessage = isEnglish ? "Your reservation has been accepted.We will be waiting for your arrival." : "Η κράτηση σας ολοκληρώθηκε με επιτυχία. Σας περιμένουμε.";
		rateActivity = isEnglish ? "Rate this Αctivity" : "Αξιολόγησε αυτή τη δραστηριότητα";
		orderCompleteTitle = isEnglish ? "Order Completed" : "Η Παραγγελία Ολοκληρώθηκε";
		orderCompleteMessage =  isEnglish ? "Your order is accepted and is going to be with you in around 45 minutes." : "Η παραγγελία σας ολοκληρώθηκε με επιτυχία και θα βρίσκεται στο δωματό σας σε περίπου 45 λεπτά.";
	}
	
}
