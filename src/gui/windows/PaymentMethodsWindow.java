package gui.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PaymentMethodsWindow extends JFrame  implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	
	private ImageIcon backgroundImage = new ImageIcon("Background Images/PaymentBackground.png");
	private JLabel backgroundLabel;
	
	private ImageIcon cardImage = new ImageIcon("buttonImages/Card.png");
	private ImageIcon cardImageGR = new ImageIcon("buttonImages/CardGR.png");
	private JButton cardBtn;
	
	private ImageIcon paypalImage = new ImageIcon("buttonImages/Paypal.png");
	private JButton paypalBtn;
	
	private ImageIcon roomBillImage = new ImageIcon("buttonImages/RoomBill.png");
	private ImageIcon roomBillImageGR = new ImageIcon("buttonImages/RoomBillGR.png");
	private JButton roomBillBtn;

	private JLabel paymentLabel;


	//constructor
	public PaymentMethodsWindow() {
//		initializePanelToFrame();
//		windowsConfiguration();
//		showWindow(this,true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
