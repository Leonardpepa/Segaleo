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
		greek = isEnglish ? "Greek" : "Î•Î»Î»Î·Î½Î¹ÎºÎ¬";
		english = isEnglish ? "English" : "Î‘Î³Î³Î»Î¹ÎºÎ¬";
		roomService = isEnglish ? "ROOM SERVICE" : "Î Î‘Î¡Î‘Î“Î“Î•Î›Î™Î‘";
		activities = isEnglish ? "ACTIVITIES" : "Î”Î¡Î‘Î£Î¤Î—Î¡Î™ÎŸÎ¤Î—Î¤Î•Î£";
		welcome = isEnglish ? "Welcome to Segaleo" : "ÎšÎ±Î»ÏŽÏ‚ Î®Ï�Î¸Î±Ï„Îµ ÏƒÏ„Î¿ Segaleo";
		loginto = isEnglish ?  "Login to your account" : "Î£Ï…Î½Î´ÎµÎ¸ÎµÎ¯Ï„Îµ ÏƒÏ„Î¿Î½ Î»Î¿Î³Î±Ï�Î¹Î±ÏƒÎ¼ÏŒ ÏƒÎ±Ï‚";
		roomField = isEnglish ?  "  Room Number" : "  Î‘Ï�Î¹Î¸Î¼ÏŒÏ‚ Î´Ï‰Î¼Î±Ï„Î¯Î¿Ï…";
		passField = isEnglish ? "  Password" : "  ÎšÏ‰Î´Î¹ÎºÏŒÏ‚";
		loginBtn = isEnglish ? "LOGIN" : "Î•Î™Î£ÎŸÎ”ÎŸÎ£";
		languageLabel = isEnglish ? "Languages" : "Î“Î»ÏŽÏƒÏƒÎµÏ‚";
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
