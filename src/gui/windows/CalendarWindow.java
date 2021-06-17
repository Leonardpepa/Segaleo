package gui.windows;

import javax.swing.*;
import javax.swing.table.*;

import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import reservation.Activity;
import reservation.Reservation;
import resources.ColorResources;
import resources.TextResources;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/*	this class is responsible to show the frame where the user can select day, hour and people for an activity he wants to do,
 *  and checks if the inputs are valid.
 */
public class CalendarWindow extends JFrame {

	private static final long serialVersionUID = 3493826716906127814L;
	
	private JTable tblCalendar;
	private JPanel container;
	private DefaultTableModel mtblCalendar; // Table model
	private JScrollPane stblCalendar; // The scrollpane
	private JPanel pnlCalendar;
	@SuppressWarnings("unused")
	private int realYear, realMonth, realDay, currentYear, currentMonth;

	private JLabel dayLabel;
	private JLabel timeLabel;
	private JLabel peopleLabel;

	private List<Activity> activities;
	private Activity activity;
	private Reservation reservation;

	private String path = "buttonImages/Back Button";
	private String lang = TextResources.imageLang;
	private ImageIcon backImage = new ImageIcon(path + lang);
	private JButton backBtn;

	private JButton hour1Btn;
	private JButton hour2Btn;

	private ImageIcon plusIcon;
	private JLabel plusButtonLabel;
	private ImageIcon minusIcon;
	private JLabel minusButtonLabel;
	private JLabel quantinty;

	private JButton confirmBtn;
	private boolean flagHour = false; //checks if an hour is selected
	private boolean flagDay = false; // checks if a day is selected

	private int[][] a;
	
	private int selectedDay = -1;
	private int selectedWeek = -1;

	private Date selDate;

	public CalendarWindow(List<Activity> activities, Activity activity, Reservation reservation) {

		new TextResources().changeLanguage();
		new ColorResources();
		this.activities = activities;
		this.activity = activity;
		this.reservation = reservation;
		this.a = Activity.getA();

		configurateHourButtons(ColorResources.timeBtn, ColorResources.timeBtn);
		configureCalendar();
		initializePanel();
		windowsConfiguration();
		showWindow(this, true);

	}

	public void windowsConfiguration() {
		this.setTitle("Segaleo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public void addListeners() {
		hour1Btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String selHour = (activity.getHour().get(0)).substring(0, 4); // get the first 4 chars of the string. If
																			// str = "9:00-12:00" , str.subrstring(0,4) -> "9:00"
				if (isValidHour(selHour)) {
					activity.setSelHour(0);
					flagHour = true;
					configurateHourButtons(ColorResources.clickedTimeBtn, ColorResources.timeBtn);
					initializePanel();
				}
			}
		});
		
		hour2Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selHour = (activity.getHour().get(1)).substring(0, 4); // get the first 4 chars of the string

				if (isValidHour(selHour)) {
					activity.setSelHour(1);
					flagHour = true;
					configurateHourButtons(ColorResources.timeBtn, ColorResources.clickedTimeBtn);
					initializePanel();
				}
			}
		});
		
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainWindow();

			}
		});
		
		confirmBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if (flagHour && flagDay) {
					activity.setColumn(activities.indexOf(activity) * 2);
					// if no availability remove activity from reservation
					if (!activity.checkLimit()) {
						for (int i = 0; i < activity.getSelpeople(); i++) {
							reservation.removeActivity(activity);
						}
						JOptionPane.showMessageDialog(null,
								TextResources.noAvaliability
										+ a[activity.getSelday()][activity.getSelhour() + activity.getColumn()]
										+ TextResources.people);
						initializePanel();
					} 
					// there is space for SelPeople for this activity this day and hour
					else {
						dispose();
						String[] hrs;
						if (activity.getSelhour() == 0) {
							hrs = hour1Btn.getText().split(":", 2);
						} else {
							hrs = hour2Btn.getText().split(":", 2);
						}
						selDate.setHours(Integer.parseInt(hrs[0]));
						//set the selected day and hour to the activity
						activity.setSelDate(selDate);
						new ActivityWindow(reservation);
					}
				// day or hour or people not selected
				} else {
					if (!flagHour || !flagDay || activity.getSelpeople() <= 0) {
						JOptionPane.showMessageDialog(null, TextResources.noSelection);
					}
				}
			}
		});
	}

	public void showWindow(JFrame frame, boolean show) {
		frame.setVisible(show);
	}

	public void initializePanel() {

		// panel
		container = new JPanel();
		container.setPreferredSize(new Dimension(375, 812));
		container.setLayout(null);
		container.setBackground(new Color(216, 223, 224));
		container.setBounds(25, 130, 320, 350);

		configureLabels();
		configureButtons();

		// Add controls to panel
		container.add(pnlCalendar);
		pnlCalendar.add(stblCalendar);
		container.add(dayLabel);
		container.add(timeLabel);
		container.add(backBtn);
		container.add(hour1Btn);
		container.add(hour2Btn);
		container.add(peopleLabel);
		container.add(quantinty);
		container.add(minusButtonLabel);
		container.add(plusButtonLabel);
		container.add(confirmBtn);

		addListeners();
		this.setContentPane(container);
		this.pack();

	}

	@SuppressWarnings("serial")
	public void configureCalendar() {

		mtblCalendar = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		tblCalendar = new JTable(mtblCalendar);
		tblCalendar.addMouseListener(new jTableListener());
		stblCalendar = new JScrollPane(tblCalendar);
		pnlCalendar = new JPanel(null);
		pnlCalendar.setBackground(new Color(216, 223, 224));

		// Set bounds
		pnlCalendar.setBounds(25, 120, 320, 300);
		stblCalendar.setBounds(10, 50, 300, 250);

		// Get real month/year
		GregorianCalendar cal = new GregorianCalendar(); // Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
		realMonth = cal.get(GregorianCalendar.MONTH); // Get month
		realYear = cal.get(GregorianCalendar.YEAR); // Get year
		currentMonth = realMonth; // Match month and year
		currentYear = realYear;

		// Add headers
		String[] headers = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" }; // All headers
		String[] headersGR = { "ΚΥΡ", "ΔΕΥ", "ΤΡΙ", "ΤΕΤ", "ΠΕΜ", "ΠΑΡ", "ΣΑΒ" }; // All GR headers

		for (int i = 0; i < 7; i++) {
			if (TextResources.isEnglish) {
				mtblCalendar.addColumn(headers[i]);
			} else {
				mtblCalendar.addColumn(headersGR[i]);
			}
		}

		// No resize/reorder
		tblCalendar.getTableHeader().setResizingAllowed(false);
		tblCalendar.getTableHeader().setReorderingAllowed(false);

		// Single cell selection
		tblCalendar.setColumnSelectionAllowed(true);
		tblCalendar.setRowSelectionAllowed(true);
		tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set row/column count
		tblCalendar.setRowHeight(38);
		mtblCalendar.setColumnCount(7);
		mtblCalendar.setRowCount(6);

		// Refresh calendar
		refreshCalendar(realMonth, realYear); // Refresh calendar
	}

	public void configureLabels() {
		dayLabel = LabelFactory.createLabel(TextResources.chooseDay, Color.gray,
				FontFactory.poppins(22));
		dayLabel.setBounds(32, 90, 160, 32);
		timeLabel = LabelFactory.createLabel(TextResources.chooseTime, Color.gray,
				FontFactory.poppins(22));
		timeLabel.setBounds(32, 450, 160, 32);
		peopleLabel = LabelFactory.createLabel(TextResources.choosePeople, Color.gray,
				FontFactory.poppins(22));
		peopleLabel.setBounds(32, 620, 165, 32);
	}

	public void configurateHourButtons(Color c1, Color c2) {
		hour1Btn = ButtonFactory.createButton(activity.getHour().get(0), FontFactory.poppins(14),
				c1, Color.WHITE);
		hour1Btn.setBounds(111, 500, 154, 50);

		hour2Btn = ButtonFactory.createButton(activity.getHour().get(1), FontFactory.poppins(14),
				c2, Color.WHITE);
		hour2Btn.setBounds(111, 560, 154, 50);
	}

	public void configureButtons() {
		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(15, 25, 64, 45);
		backBtn.setPressedIcon(backImage);

		plusIcon = new ImageIcon("./buttonImages/plus.png");
		minusIcon = new ImageIcon("./buttonImages/minus.png");

		plusButtonLabel = LabelFactory.createIconLabel(plusIcon);
		plusButtonLabel.setIcon(plusIcon);
		plusButtonLabel.setBounds(210, 670, 24, 24);
		plusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		plusButtonLabel.addMouseListener(new PlusMinusListener());
		plusButtonLabel.setName("plus");

		minusButtonLabel = LabelFactory.createIconLabel(minusIcon);
		minusButtonLabel.setBounds(150, 670, 24, 24);
		minusButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		minusButtonLabel.addMouseListener(new PlusMinusListener());
		minusButtonLabel.setName("minus");

		quantinty = LabelFactory.createLabel(reservation.getAct().get(activity) + "x", Color.BLACK,
				FontFactory.poppins(13));
		quantinty.setBounds(183, 670, 50, 20);

		confirmBtn = ButtonFactory.createButton(TextResources.confirm, FontFactory.poppins(14),
				ColorResources.clickedTimeBtn, Color.WHITE);
		confirmBtn.setBounds(111, 700, 154, 50);
	}

	//Refresh new Calendar
	public void refreshCalendar(int month, int year) {
		int nod, som; // Number Of Days, Start Of Month

		// Clear table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				mtblCalendar.setValueAt(null, i, j);
			}
		}

		// Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);

		// Draw calendar
		for (int i = 1; i <= nod; i++) {
			int row = (int) ((i + som - 2) / 7);
			int column = (i + som - 2) % 7;
			mtblCalendar.setValueAt(i, row, column);
		}

		// Apply renderers
		tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
	}

	public int getSelectedDay() {
		return selectedDay;
	}

	//If the day that has been selected is the same with current day 
	//then check if the current hour does not surpass selected hour.
	public boolean isValidHour(String hour) {
		
		String[] hrs = hour.split(":", 2); // Split the string (e.g. "9:00" to hrs[0]="9" and hrs[1]="0") 
		                                   //hrs[0] = selected hour (where activity starts) 
		                                   // and hrs[1] = selected minutes (where activity starts)
		
		GregorianCalendar cal = new GregorianCalendar();
		int currentHour = cal.get(GregorianCalendar.HOUR_OF_DAY); 
		int currentMinutes = cal.get(GregorianCalendar.MINUTE);
		int selectedHour = Integer.parseInt(hrs[0]); //e.g. selectedHour = 9
		int selectedMinutes = Integer.parseInt(hrs[1]); //e.g. selectedMinutes = 0

		int selectedDay = getSelectedDay();
		int currentDay = cal.get(GregorianCalendar.DAY_OF_MONTH);

		if (selectedDay != currentDay && selectedDay != -1) 
			return true;

		if (currentHour > selectedHour) {
			JOptionPane.showMessageDialog(null, "You can't choose that hour! Select another hour or day.");
			return false;
		}

		if (currentHour == selectedHour) {
			if (currentMinutes >= selectedMinutes) {
				JOptionPane.showMessageDialog(null, "You can't choose that hour! Select another hour or day.");
				return false;
			}
		}

		return true;
	}

	//listener for +/- buttons
	class PlusMinusListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() instanceof JLabel) {
				JLabel label = (JLabel) e.getSource();
				if (label.getName().equals("plus")) {
					reservation.addActivity(activity);
				} else if (label.getName().equals("minus")) {
					if (activity.getSelpeople() != 0) {
						reservation.removeActivity(activity);
					}
				}
				activity.setSelpeople(reservation.getAct().get(activity));
			}

			initializePanel();
		}
	}

	// extra classes
	class tblCalendarRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 5787096922884342183L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
				int row, int column) {
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
			return this;
		}
	}

	class jTableListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			JTable table = (JTable) e.getSource();
			GregorianCalendar cal = new GregorianCalendar();

			try {
				selectedDay = (int) table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn());
				selectedWeek = table.getSelectedRow();
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(null, TextResources.invalidDay);
			}

			int currentDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
			int currentWeek = (cal.get(GregorianCalendar.WEEK_OF_MONTH)) - 1;
			
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
				currentWeek = cal.get(GregorianCalendar.WEEK_OF_MONTH);

			int actMonth = cal.get(GregorianCalendar.MONTH) + 1;
			int actYear = cal.get(GregorianCalendar.YEAR);


			if (selectedDay == -1 || selectedWeek == -1) {
				return;
			}

			if (selectedWeek != currentWeek || selectedDay < currentDay) {
				JOptionPane.showMessageDialog(null, TextResources.invalidDay);
			} else {
				String sDate1 = selectedDay + "/" + actMonth + "/" + actYear;
				try {
					selDate = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				flagDay = true;
				activity.setSelDay(table.getSelectedColumn());
			}

		}
	}

}