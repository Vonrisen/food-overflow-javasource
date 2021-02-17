package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import controllers.AdminController;


public class AdminCustomerFrame extends StandardFrame {

	private String[] columns = {"CF", "Nome", "Cognome", "Data di nascita", "Luogo di nascita", "Indirizzo", "Sesso", "Cellulare", "Email", "Password"};
	
	private AdminController admin_controller;
	
	//Costruttore
	public AdminCustomerFrame(AdminController admin_controller) {
		frame();
		this.admin_controller = admin_controller;
	}
	
	
	private void frame() {
		
		setTitle("Food Overflow - AdminPanel: Visualizza clienti");

		setTable_title(new ImageIcon("src\\images\\others\\customers.png"));
		getTable_titleLB().setIcon(getTable_title());
		getTable().setModel(model = new DefaultTableModel(columns, 0));
		
		getTable().getColumnModel().getColumn(0).setPreferredWidth(100);
		getTable().getColumnModel().getColumn(6).setPreferredWidth(5);
		getScroll_pane().setBorder(BorderFactory.createLineBorder(new Color(0x771007), 5));
		
		getGo_backJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				admin_controller.openAdminFrame(AdminCustomerFrame.this);
			
			}
		});
		
		addWindowListener(new WindowAdapter()
	     {
	         @Override
	         public void windowClosing(WindowEvent e)
	         {
	             admin_controller.releaseAllDaoResourcesAndDisposeFrame(AdminCustomerFrame.this);
	         }
	     });
	
		
	}

}
