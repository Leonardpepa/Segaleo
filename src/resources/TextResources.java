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
		viewCart = isEnglish ? "View Cart" : "Δες το καλαθι";
		search = isEnglish ? "Search" : "Αναζητηση";
		deals = isEnglish ? "Deals of the day" : "Προσφορες της ημερας";
		main = isEnglish ? "Main" : "Κυριως";
		salads = isEnglish ? "Salads" : "Σαλατες";
		desserts = isEnglish ? "Desserts" : "Γλυκα";
		coffee = isEnglish ? "Coffes" : "Καφεδες";
		drinks = isEnglish ? "Drinks" : "Ποτα";
		appetizers = isEnglish ? "Appetizers" : "Ορεκτικα";
		breakfast = isEnglish ? "Breakfast" : "Πρωινο";
		explore = isEnglish ? "Explore by category" : "Εξερευνηστε απο τις κατηγοριες";
		endpointPath = isEnglish ? "English.txt" : "Greek.txt";
	}
	
}
