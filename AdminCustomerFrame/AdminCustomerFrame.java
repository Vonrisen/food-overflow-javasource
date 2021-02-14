package gui;

import java.awt.Color;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controllers.AdminController;
import gui_support.RoundJTextField;


public class AdminCustomerFrame extends StandardFrame {

	private String[] columns = {"CF", "Nome", "Cognome", "Data di nascita", "Luogo di nascita", "Indirizzo", "Sesso", "Cellulare", "Email", "Password"};
	
	private AdminController controller;
	
	//Costruttore
	public AdminCustomerFrame(AdminController controller) {
		frame();
		this.controller = controller;
	}
	
	
	private void frame() {
		
		setTitle("[Admin Panel] Lista clienti");

		setTable_title(new ImageIcon("src\\images\\tableTitles\\customers.png"));
		getTable_titleLB().setIcon(getTable_title());
		getTable().setModel(model = new DefaultTableModel(columns, 0));
		
		getGo_backJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				controller.openAdminFrame(AdminCustomerFrame.this);
			
			}
		});
	
		
	}

}
