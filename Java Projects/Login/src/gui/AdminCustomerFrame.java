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


public class AdminCustomerFrame extends ComplexFrame {

	private String[] columns = {"CF", "Nome", "Cognome", "Data di nascita", "Luogo di nascita", "Indirizzo", "Sesso", "Cellulare", "Email", "Password"};
	private JTextField emailTF;
	private JTextField passwordTF;
	private AdminController admin_controller;


	//Costruttore
	public AdminCustomerFrame(AdminController admin_controller) {
		initialize();
		frameSetup();
		events();
		this.admin_controller=admin_controller;
	}


	//Initialize variables
	private void initialize() {
		setTable_title(new ImageIcon("src\\images\\tableTitles\\customers.png"));
		emailTF = new RoundJTextField(new Color(0x771007));
		passwordTF = new RoundJTextField(new Color(0x771007));
		getTable().setModel(model = new DefaultTableModel(columns, 0));
	}

	private void frameSetup() {

		//Layout setup
		this.setTitle("[Admin Panel] Gestione clienti");
		getTable_titleLB().setIcon(getTable_title());


		//Textfields setup
		createTextField(emailTF, "E-Mail", getLong_dim_of_textfield());
		getAttributes_panel().add(emailTF);

		createTextField(passwordTF, "Password", getLong_dim_of_textfield());
		getAttributes_panel().add(passwordTF);


		//Button setup
		getButtons_panel().add(getUpdate_sqlJB());
		getButtons_panel().add(getDelete_sqlJB());
		getButtons_panel().add(getGo_backJB());

	}


	private void events() {

//		getUpdate_sqlJB().addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//				admin_controller.updateCustomer(AdminCustomerFrame.this);
//			}
//		});
//
//		getDelete_sqlJB().addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//				admin_controller.removeCustomer(AdminCustomerFrame.this);
//			}
//		});

		getGo_backJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.openAdminFrame(AdminCustomerFrame.this);
			}
		});

		emailTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(emailTF, "E-Mail");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(emailTF, "E-Mail");
			}
		});

		passwordTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(passwordTF, "Password");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(passwordTF, "Password");
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

	public JTextField getEmailTF() {
		return emailTF;
	}

	public void setEmailTF(JTextField emailTF) {
		this.emailTF = emailTF;
	}

	public JTextField getPasswordTF() {
		return passwordTF;
	}

	public void setPasswordTF(JTextField passwordTF) {
		this.passwordTF = passwordTF;
	}

}
