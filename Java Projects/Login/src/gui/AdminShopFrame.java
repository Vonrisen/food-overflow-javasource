package gui;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controllers.AdminController;
import entities.Address;
import gui_support.RoundJTextField;
import utilities.InputUtility;
import utilities.TableModelUtility;


@SuppressWarnings("serial")
public class AdminShopFrame extends ComplexFrame{

	private ImageIcon search_riders_inactiveIMG;
	private ImageIcon search_riders_activeIMG;
	private String[]columns = {"E-mail", "Password", "Nome", "Indirizzo", "Orario di chiusura", "Giorni di chiusura", "Telefono fisso"};
	private JButton	riders_searchJB;


	private JTextField nameTF;
	private JTextField emailTF;
	private JTextField home_phoneTF;
	private JTextField address_nameTF;
	private JTextField address_civic_numberTF;
	private JTextField address_capTF;
	private JTextField working_hoursTF;
	private JTextField passwordTF;
	private JTextField closing_daysTF;
	private AdminController admin_controller;
	
	private JComboBox<String> address_provinceCB;
	private JComboBox<String> address_townCB;


	//Create the application
	public AdminShopFrame(AdminController admin_controller) {
		initialize();
		frameSetup();
		events();
		this.admin_controller = admin_controller;
	}


	//Initialize variables
	private void initialize() {

		search_riders_inactiveIMG = new ImageIcon("src\\images\\buttons\\searchRidersButtonInactive.png");
		search_riders_activeIMG = new ImageIcon("src\\images\\buttons\\searchRidersButtonActive.png");
		setTable_title(new ImageIcon("src\\images\\others\\shops.png"));

		nameTF = new RoundJTextField(new Color(0x771007));
		emailTF = new RoundJTextField(new Color(0x771007));
		home_phoneTF = new RoundJTextField(new Color(0x771007));
		address_nameTF = new RoundJTextField(new Color(0x771007));
		address_civic_numberTF = new RoundJTextField(new Color(0x771007));
		address_capTF = new RoundJTextField(new Color(0x771007));
		working_hoursTF = new RoundJTextField(new Color(0x771007));
		passwordTF = new RoundJTextField(new Color(0x771007));
		closing_daysTF = new RoundJTextField(new Color(0x771007));

		riders_searchJB = new JButton();
		address_provinceCB = new JComboBox<String>();
		address_townCB = new JComboBox<String>();
		
		getTable().setModel(model = new DefaultTableModel(columns, 0));
	}


	//Frame Setup
	private void frameSetup() {

		//Sottopannelli di "center_panel"
		getTable_titleLB().setIcon(getTable_title());
		setTitle("Food Overflow - Admin Panel: Gestione ristoranti");
		
		getScroll_pane().setBorder(BorderFactory.createLineBorder(new Color(0x771007), 5));

		//Button setup
		getButtons_panel().add(getInsert_sqlJB());
		getButtons_panel().add(getUpdate_sqlJB());
		getButtons_panel().add(getDelete_sqlJB());
		getButtons_panel().add(getGo_backJB());


		//Textfields setup
		createTextField(nameTF, "Nome", getShort_dim_of_textfield());
		getAttributes_panel().add(nameTF);

		createTextField(emailTF, "E-mail", getShort_dim_of_textfield());
		getAttributes_panel().add(emailTF);
		
		createTextField(home_phoneTF, "Telefono fisso", getShort_dim_of_textfield());
		getAttributes_panel().add(home_phoneTF);

		createTextField(address_nameTF, "Nome dell'indirizzo", getShort_dim_of_textfield());
		getAttributes_panel().add(address_nameTF);

		createTextField(address_civic_numberTF, "Numero civico", getShort_dim_of_textfield());
		getAttributes_panel().add(address_civic_numberTF);

		createTextField(address_capTF, "CAP", getShort_dim_of_textfield());
		getAttributes_panel().add(address_capTF);

		address_provinceCB.setPreferredSize(getShort_dim_of_textfield());
		address_provinceCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		address_provinceCB.setFocusable(false);
		address_provinceCB.setBackground(Color.white);
		getAttributes_panel().add(address_provinceCB);
		
		address_townCB.setPreferredSize(getShort_dim_of_textfield());
		address_townCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		address_townCB.setFocusable(false);
		address_townCB.setBackground(Color.white);
		getAttributes_panel().add(address_townCB);
		
		createTextField(working_hoursTF, "Orario di apertura", getShort_dim_of_textfield());
		getAttributes_panel().add(working_hoursTF);

		createTextField(passwordTF, "Password", getShort_dim_of_textfield());
		getAttributes_panel().add(passwordTF);

		createTextField(closing_daysTF, "Giorni di chiusura", getLong_dim_of_textfield());
		getAttributes_panel().add(closing_daysTF);

		setupButton(riders_searchJB, search_riders_inactiveIMG, new Dimension(335,30));
		getAttributes_panel().add(riders_searchJB);
		
		

	}

	private void events() {

		getInsert_sqlJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.addShop(AdminShopFrame.this);
			}
		});

		getUpdate_sqlJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.updateShop(AdminShopFrame.this);
			}
		});

		getDelete_sqlJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.removeShop(AdminShopFrame.this);
			}
		});

		getTable().addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				TableModelUtility table_utility = new TableModelUtility();
				table_utility.fillFieldsFromJTable(AdminShopFrame.this);
			}
		});

		getGo_backJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.openAdminFrame(AdminShopFrame.this);
			}
		});

		riders_searchJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.openAdminRiderFrame(AdminShopFrame.this);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				riders_searchJB.setIcon(search_riders_activeIMG);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				riders_searchJB.setIcon(search_riders_inactiveIMG);
			}
		});


		nameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(nameTF, "Nome");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(nameTF, "Nome");
			}
		});

		emailTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(emailTF, "E-mail");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(emailTF, "E-mail");
			}
		});

		home_phoneTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(emailTF, "Telefono fisso");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(emailTF, "Telefono fisso");
			}
		});

		address_nameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(address_nameTF, "Nome dell'indirizzo");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(address_nameTF, "Nome dell'indirizzo");
			}
		});

		address_civic_numberTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(address_civic_numberTF, "Numero civico");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(address_civic_numberTF, "Numero civico");
			}
		});

		address_capTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(address_capTF, "CAP");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(address_capTF, "CAP");
			}
		});

		working_hoursTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(working_hoursTF, "Orario");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(working_hoursTF, "Orario");
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

		closing_daysTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(closing_daysTF, "Giorni di chiusura");
			}

			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(closing_daysTF, "Giorni di chiusura");
			}
		});
		
		addWindowListener(new WindowAdapter()
	     {
	         @Override
	         public void windowClosing(WindowEvent e)
	         {
	             admin_controller.releaseAllDaoResourcesAndDisposeFrame(AdminShopFrame.this);
	         }
	     });
		
		address_provinceCB.addItemListener(this::addressProvinceCBitemStateChanged);
	}

	public void addressProvinceCBitemStateChanged(ItemEvent e) {
		
	    if (e.getStateChange() == ItemEvent.SELECTED) {
	    	String selected_item = (String) e.getItem();
	    	if(!selected_item.equals("-------------------")||!selected_item.equals("Seleziona provincia"))
	    		admin_controller.updateAddressTownsCB(selected_item, AdminShopFrame.this);	
	    	if(selected_item.equals("Seleziona provincia"))
	    	{
	    		getAddress_townCB().removeAllItems();
				getAddress_townCB().addItem("Seleziona comune");
	    	}
	    }
		
	}

	public JTextField getNameTF() {
		return nameTF;
	}
	

	public JTextField getAddress_nameTF() {
		return address_nameTF;
	}

	public JTextField getAddress_civic_numberTF() {
		return address_civic_numberTF;
	}



	public JTextField getAddress_capTF() {
		return address_capTF;
	}

	public JTextField getWorking_hoursTF() {
		return working_hoursTF;
	}


	public JTextField getPasswordTF() {
		return passwordTF;
	}


	public JTextField getClosing_daysTF() {
		return closing_daysTF;
	}



	public JTextField getEmailTF() {
		return emailTF;
	}

	
	public JTextField getHome_phoneTF() {
		return home_phoneTF;
	}



	public JComboBox<String> getAddress_townCB() {
		return address_townCB;
	}


	public JComboBox<String> getAddress_provinceCB() {
		return address_provinceCB;
	}



	
}
