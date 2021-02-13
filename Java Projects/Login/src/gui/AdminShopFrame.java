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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controllers.AdminController;
import entities.Address;
import gui_support.RoundJTextField;
import utilities.InputUtility;


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

		search_riders_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\searchRidersButtonInactive.png");
		search_riders_activeIMG = new ImageIcon("src\\images\\SqlButtons\\searchRidersButtonActive.png");
		setTable_title(new ImageIcon("src\\images\\tableTitles\\shops.png"));

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
		getAttributes_panel().add(address_provinceCB);
		
		address_townCB.setPreferredSize(getShort_dim_of_textfield());
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
				if(!getTable().getSelectionModel().isSelectionEmpty()) {
					InputUtility input_util= new InputUtility();
					Address address;
					String closing_days;
					address = input_util.tokenizedStringToAddress(getTable().getModel().getValueAt(getTable().getSelectedRow(), 3).toString(), "(, )");
					try
					{
						closing_days = getTable().getModel().getValueAt(getTable().getSelectedRow(), 5).toString();
					}catch(Exception e)
					{
						closing_days = "";
					}
					emailTF.setText(getTable().getModel().getValueAt(getTable().getSelectedRow(), 0).toString());
					nameTF.setText(getTable().getModel().getValueAt(getTable().getSelectedRow(), 2).toString());
					address_nameTF.setText(address.getAddress());
					address_civic_numberTF.setText(address.getCivic_number());
					address_capTF.setText(address.getCap());
					working_hoursTF.setText(getTable().getModel().getValueAt(getTable().getSelectedRow(), 4).toString());
					closing_daysTF.setText(closing_days);
					passwordTF.setText(getTable().getModel().getValueAt(getTable().getSelectedRow(), 1).toString());
					home_phoneTF.setText(getTable().getModel().getValueAt(getTable().getSelectedRow(), 6).toString());

				}
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


	public void setNameTF(JTextField nameTF) {
		this.nameTF = nameTF;
	}


	public JTextField getAddress_nameTF() {
		return address_nameTF;
	}


	public void setAddress_nameTF(JTextField address_nameTF) {
		this.address_nameTF = address_nameTF;
	}


	public JTextField getAddress_civic_numberTF() {
		return address_civic_numberTF;
	}


	public void setAddress_civic_numberTF(JTextField address_civic_numberTF) {
		this.address_civic_numberTF = address_civic_numberTF;
	}


	public JTextField getAddress_capTF() {
		return address_capTF;
	}


	public void setAddress_capTF(JTextField address_capTF) {
		this.address_capTF = address_capTF;
	}




	public JTextField getWorking_hoursTF() {
		return working_hoursTF;
	}


	public void setWorking_hoursTF(JTextField working_hoursTF) {
		this.working_hoursTF = working_hoursTF;
	}


	public JTextField getPasswordTF() {
		return passwordTF;
	}


	public void setPasswordTF(JTextField passwordTF) {
		this.passwordTF = passwordTF;
	}


	public JTextField getClosing_daysTF() {
		return closing_daysTF;
	}


	public void setClosing_daysTF(JTextField closing_daysTF) {
		this.closing_daysTF = closing_daysTF;
	}


	public JTextField getEmailTF() {
		return emailTF;
	}


	public void setEmailTF(JTextField emailTF) {
		this.emailTF = emailTF;
	}
	
	public JTextField getHome_phoneTF() {
		return home_phoneTF;
	}


	public void setHome_phoneTF(JTextField cellphoneTF) {
		this.home_phoneTF = cellphoneTF;
	}


	public JComboBox<String> getAddress_townCB() {
		return address_townCB;
	}


	public void setAddress_townCB(JComboBox<String> address_townCB) {
		this.address_townCB = address_townCB;
	}


	public JComboBox<String> getAddress_provinceCB() {
		return address_provinceCB;
	}



	
}
