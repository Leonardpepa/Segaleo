package gui.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.components.MenuHeader;
import gui.components.*;
import menu.Menu;
import resources.ColorResources;

public class MenuWindow extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel containerPanel;
	private MenuHeader header;
	private MenuMainContent mainContent;
	private ViewCartPanel viewCart;
	private Menu menu;
	
	//constructor
		public MenuWindow() {
			initilizePanelToFrame();
			windowsConfiguration();
			showWindow(this,true);
		}
		private void initilizePanelToFrame() {
			menu = new Menu();
			containerPanel = new JPanel();
			containerPanel.setPreferredSize(new Dimension(375, 812));
			containerPanel.setLayout(new BorderLayout());
			
			header = new MenuHeader(this,menu.getAppetizers());
			mainContent = new MenuMainContent(this, menu);
			viewCart = new ViewCartPanel();
			
			containerPanel.add(BorderLayout.SOUTH, viewCart);
			containerPanel.add(BorderLayout.NORTH, header);
			containerPanel.add(BorderLayout.CENTER, mainContent);
			
			this.setContentPane(containerPanel);
			this.pack();
		}
		//settings for the frame
		public void windowsConfiguration() {
			this.setTitle("Segaleo");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
		}
		
		private void showWindow(JFrame frame, boolean show) {
			frame.setVisible(show);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}

}
