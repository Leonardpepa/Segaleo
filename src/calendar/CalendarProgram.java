package calendar;
import javax.swing.*;
import javax.swing.table.*;

import gui.factory.ButtonFactory;
import gui.factory.FontFactory;
import gui.factory.LabelFactory;
import resources.TextResources;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalendarProgram extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static JTable tblCalendar;
	static JFrame frmMain;
	static Container container;
	static DefaultTableModel mtblCalendar; //Table model
	static JScrollPane stblCalendar; //The scrollpane
	static JPanel pnlCalendar;
	static int realYear, realMonth, realDay, currentYear, currentMonth;
	
	static JLabel dayLabel;
	static JLabel timeLabel;
	
	
	static String path = "buttonImages/Back Button";
	static String lang = TextResources.imageLang;
	static ImageIcon backImage = new ImageIcon(path + lang);
	static JButton backBtn;
	
	public static void main (String args[]) {
		new TextResources().changeLanguage();
		
		
		//Look and feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}


		//Prepare frame
		
		frmMain = new JFrame ("Hotel PDA"); //Create frame
		frmMain.setSize(new Dimension(375, 812)); //Set size to 400x400 pixels
		container = frmMain.getContentPane(); //Get content pane
		container.setLayout(null); //Apply null layout
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
		container.setBackground(new Color(216,223,224));
		
		dayLabel = LabelFactory.createLabel("Choose Day", Color.gray, FontFactory.poppins(22));
		dayLabel.setBounds(32,80,139,32);
		timeLabel = LabelFactory.createLabel("Choose Time", Color.gray, FontFactory.poppins(22));
		timeLabel.setBounds(32,300,139,32);
		
		backBtn = ButtonFactory.createButtonIcon(backImage);
		backBtn.setBounds(15, 25, 64, 45);
//		backBtn.setPressedIcon(backImage);
		
		
		backBtn.addActionListener(new ActionListener() { @Override
			public void actionPerformed(ActionEvent e) {

			if(e.getSource() == backBtn) {
				frmMain.dispose();
//				new LoginWindow();
			}

		}
			
		});
		
		//Create controls
		mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
		tblCalendar = new JTable(mtblCalendar);
		tblCalendar.addMouseListener(new jtableListener());
		stblCalendar = new JScrollPane(tblCalendar);
		pnlCalendar = new JPanel(null);
		pnlCalendar.setBackground(new Color(216,223,224));

		//Add controls to pane
		container.add(pnlCalendar);
		pnlCalendar.add(stblCalendar);
		container.add(dayLabel);
		container.add(timeLabel);
		container.add(backBtn);

		//Set bounds
		pnlCalendar.setBounds(25, 109, 320, 335);
		stblCalendar.setBounds(10, 50, 300, 250);


		//Get real month/year
		GregorianCalendar cal = new GregorianCalendar(); //Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
		realMonth = cal.get(GregorianCalendar.MONTH); //Get month
		realYear = cal.get(GregorianCalendar.YEAR); //Get year
		currentMonth = realMonth; //Match month and year
		currentYear = realYear;

		//Add headers
		String[] headers = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"}; //All headers
		String[] headersGR = {"ΚΥΡ", "ΔΕΥ", "ΤΡΙ", "ΤΕΤ", "ΠΕΜ", "ΠΑΡ", "ΣΑΒ"}; //All headers
		
		for (int i=0; i<7; i++){
			mtblCalendar.addColumn(headers[i]);
		}

//		tblCalendar.setBackground(new Color(216,223,224));
//		tblCalendar.getParent().setBackground(tblCalendar.getBackground()); //Set background


		//No resize/reorder
		tblCalendar.getTableHeader().setResizingAllowed(false);
		tblCalendar.getTableHeader().setReorderingAllowed(false);

		//Single cell selection
		tblCalendar.setColumnSelectionAllowed(true);
		tblCalendar.setRowSelectionAllowed(true);
		tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//Set row/column count
		tblCalendar.setRowHeight(38);
		mtblCalendar.setColumnCount(7);
		mtblCalendar.setRowCount(6);


		//Refresh calendar
		refreshCalendar (realMonth, realYear); //Refresh calendar
		
		//Make frame visible
				frmMain.setResizable(false);
				frmMain.setVisible(true);
	}
	
	
	public static void refreshCalendar(int month, int year){
		int nod, som; //Number Of Days, Start Of Month

		//Clear table
		for (int i=0; i<6; i++){
			for (int j=0; j<7; j++){
				mtblCalendar.setValueAt(null, i, j);
			}
		}

		//Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);

		//Draw calendar
		for (int i=1; i<=nod; i++){
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
			mtblCalendar.setValueAt(i, row, column);
		}

		//Apply renderers
		tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
	}

	static class tblCalendarRenderer extends DefaultTableCellRenderer{
		public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);

			setBackground(new Color(216,223,224));
			if (value != null){
				if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
					setBackground(new Color(220, 220, 255));
				}
			}
			setBorder(null);
			setForeground(new Color(112, 112, 112));
			return this;
		}
	}




	static class jtableListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			JTable table = (JTable) e.getSource();
			GregorianCalendar cal = new GregorianCalendar();
			int selectedDate = -1;
			int selectedWeek = -1;
			int selectedDay = -1;

			try {
				selectedDate = (int) table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn());
				selectedWeek = table.getSelectedRow();
				selectedDay = table.getSelectedColumn();
			}catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "wrong date");
			}



			int week = cal.get(GregorianCalendar.WEEK_OF_MONTH);
			int date = cal.get(GregorianCalendar.DAY_OF_MONTH);


			if(selectedDate == -1 || selectedWeek == -1) {
				return;
			}
			if(selectedWeek != week || selectedDate < date) {
				JOptionPane.showMessageDialog(null, "invalid week or day");
			}
			else {
				JOptionPane.showMessageDialog(null, "date: " + table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn()) + " day: " + table.getSelectedColumn());
			}



		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}