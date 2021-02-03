package gui;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controllers.ShopController;
import entities.Address;
import gui_support.ComplexFrame;
import gui_support.RoundJTextField;
import utilities.InputUtility;


public class ShopRiderFrame extends ComplexFrame{

	private String[] columns = {"CF", "Nome", "Cognome", "Data di nascita", "Luogo di nascita", "Indirizzo", "Sesso", "Cellulare", "Veicolo", "Orario di lavoro"};
	private String[] vehicleStrings = {"Bicicletta", "Motoveicolo", "Autoveicolo"};
	private String[] genderStrings = {"Maschio", "Femmina"};

	private JTextField nameTF;
	private JTextField surnameTF;
	private JTextField birth_dateTF;
	private JTextField birth_placeTF;
	private JTextField address_nameTF;
	private JTextField address_civic_numberTF;
	private JTextField address_capTF;
	private JTextField address_cityTF;
	private JTextField address_provinceTF;
	private JTextField working_hoursTF;
	private JTextField cellphoneTF;

	private JComboBox<String> vehicleCB;
	private JComboBox<String> genderCB;
	private ShopController shop_controller;


	//Costruttore
	public ShopRiderFrame(ShopController shop_controller) {
		initialize();
		setupFrame();
		events();
		this.shop_controller = shop_controller;
	}


	private void initialize() {

		setTable_title(new ImageIcon("src\\images\\tableTitles\\riders.png"));
		nameTF = new RoundJTextField(new Color(0x771007));
		surnameTF = new RoundJTextField(new Color(0x771007));
		birth_dateTF = new RoundJTextField(new Color(0x771007));
		birth_placeTF = new RoundJTextField(new Color(0x771007));
		address_nameTF = new RoundJTextField(new Color(0x771007));
		address_civic_numberTF = new RoundJTextField(new Color(0x771007));
		address_capTF = new RoundJTextField(new Color(0x771007));
		address_cityTF = new RoundJTextField(new Color(0x771007));
		address_provinceTF = new RoundJTextField(new Color(0x771007));
		working_hoursTF = new RoundJTextField(new Color(0x771007));
		cellphoneTF = new RoundJTextField(new Color(0x771007));
		vehicleCB = new JComboBox<String>(vehicleStrings);
		genderCB = new JComboBox<String>(genderStrings);
		getTable().setModel(model = new DefaultTableModel(columns, 0));
	}

	private void setupFrame() {

		//Layout setup
		this.setTitle("[Shop Panel] Gestione riders");


		//Textfields setup
		createTextField(nameTF, "Nome", getShort_dim_of_textfield());
		getAttributes_panel().add(nameTF);

		createTextField(surnameTF, "Cognome", getShort_dim_of_textfield());
		getAttributes_panel().add(surnameTF);

		createTextField(birth_dateTF, "Data di n. (dd/mm/yy)", getShort_dim_of_textfield());
		getAttributes_panel().add(birth_dateTF);

		createTextField(birth_placeTF, "Luogo di nascita", getShort_dim_of_textfield());
		getAttributes_panel().add(birth_placeTF);

		createTextField(address_nameTF, "Nome dell'indirizzo", getLong_dim_of_textfield());
		getAttributes_panel().add(address_nameTF);

		createTextField(address_civic_numberTF, "Numero civico", getShort_dim_of_textfield());
		getAttributes_panel().add(address_civic_numberTF);

		createTextField(address_capTF, "CAP", getShort_dim_of_textfield());
		getAttributes_panel().add(address_capTF);

		createTextField(address_cityTF, "Comune", getShort_dim_of_textfield());
		getAttributes_panel().add(address_cityTF);

		createTextField(address_provinceTF, "Provincia", getShort_dim_of_textfield());
		getAttributes_panel().add(address_provinceTF);

		createTextField(cellphoneTF, "Cellulare", getShort_dim_of_textfield());
		getAttributes_panel().add(cellphoneTF);

		genderCB.setPreferredSize(getShort_dim_of_textfield());
		getAttributes_panel().add(genderCB);

		vehicleCB.setPreferredSize(getShort_dim_of_textfield());
		getAttributes_panel().add(vehicleCB);

		createTextField(working_hoursTF, "Orario di lavoro", getShort_dim_of_textfield());
		getAttributes_panel().add(working_hoursTF);


		//Buttons & Label setup
		getTable_titleLB().setIcon(getTable_title());
		getButtons_panel().add(getInsert_sqlJB());
		getButtons_panel().add(getUpdate_sqlJB());
		getButtons_panel().add(getDelete_sqlJB());
		getButtons_panel().add(getGo_backJB());

	}


	private void events() {

		getInsert_sqlJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.addRider(ShopRiderFrame.this);
			}
		});

		getUpdate_sqlJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.updateRider(ShopRiderFrame.this);
			}
		});


		getDelete_sqlJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.removeRider(ShopRiderFrame.this);
			}
		});

		getGo_backJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.openShopFrame(ShopRiderFrame.this);
			}
		});

		getTable().addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				InputUtility input_util= new InputUtility();
				Address address;
				if (!getTable().getSelectionModel().isSelectionEmpty()) {
					address = input_util.tokenizedAddressToString(getTable().getModel().getValueAt(getTable().getSelectedRow(), 5).toString(), "(, )");
					nameTF.setText(getTable().getModel().getValueAt(getTable().getSelectedRow(), 1).toString());
					surnameTF.setText(getTable().getModel().getValueAt(getTable().getSelectedRow(), 2).toString());
					birth_dateTF.setText(getTable().getModel().getValueAt(getTable().getSelectedRow(), 3).toString());
					birth_placeTF.setText(getTable().getModel().getValueAt(getTable().getSelectedRow(), 4).toString());
					address_nameTF.setText(address.getName());
					address_civic_numberTF.setText(address.getCivic_number());
					address_capTF.setText(address.getCap());
					address_cityTF.setText(address.getCity());
					address_provinceTF.setText(address.getProvince_abbrevation());
					cellphoneTF.setText(getTable().getModel().getValueAt(getTable().getSelectedRow(), 7).toString());
					working_hoursTF.setText(getTable().getModel().getValueAt(getTable().getSelectedRow(), 9).toString());
				}
			}
		});


		//FocusListeners

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

		surnameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(surnameTF, "Cognome");
			}

			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(surnameTF, "Cognome");
			}
		});

		birth_dateTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(birth_dateTF, "Data di n. (dd/mm/yy)");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(birth_dateTF, "Data di n. (dd/mm/yy)");
			}
		});

		birth_placeTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(birth_placeTF, "Luogo di nascita");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(birth_placeTF, "Luogo di nascita");
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

		address_cityTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(address_cityTF, "Comune");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(address_cityTF, "Comune");
			}
		});

		address_provinceTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(address_provinceTF, "Provincia");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(address_provinceTF, "Provincia");
			}
		});

		cellphoneTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(cellphoneTF, "Cellulare");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(cellphoneTF, "Cellulare");
			}
		});

		working_hoursTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(working_hoursTF, "Orario di lavoro");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(working_hoursTF, "Orario di lavoro");
			}
		});



	}


	public JTextField getNameTF() {
		return nameTF;
	}

	public void setNameTF(JTextField nameTF) {
		this.nameTF = nameTF;
	}

	public JTextField getSurnameTF() {
		return surnameTF;
	}

	public void setSurnameTF(JTextField surnameTF) {
		this.surnameTF = surnameTF;
	}

	public JTextField getBirth_dateTF() {
		return birth_dateTF;
	}

	public void setBirth_dateTF(JTextField birth_dateTF) {
		this.birth_dateTF = birth_dateTF;
	}

	public JTextField getBirth_placeTF() {
		return birth_placeTF;
	}

	public void setBirth_placeTF(JTextField birth_placeTF) {
		this.birth_placeTF = birth_placeTF;
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

	public JTextField getAddress_cityTF() {
		return address_cityTF;
	}

	public void setAddress_cityTF(JTextField address_cityTF) {
		this.address_cityTF = address_cityTF;
	}

	public JTextField getAddress_provinceTF() {
		return address_provinceTF;
	}

	public void setAddress_provinceTF(JTextField address_provinceTF) {
		this.address_provinceTF = address_provinceTF;
	}

	public JTextField getCellphoneTF() {
		return cellphoneTF;
	}

	public void setCellphoneTF(JTextField cellphoneTF) {
		this.cellphoneTF = cellphoneTF;
	}

	public JComboBox<String> getVehicleCB() {
		return vehicleCB;
	}

	public void setVehicleCB(JComboBox<String> vehicleCB) {
		this.vehicleCB = vehicleCB;
	}

	public JComboBox<String> getGenderCB() {
		return genderCB;
	}

	public void setGenderCB(JComboBox<String> genderCB) {
		this.genderCB = genderCB;
	}

	public JTextField getWorking_hoursTF() {
		return working_hoursTF;
	}

	public void setWorking_hoursTF(JTextField working_hoursTF) {
		this.working_hoursTF = working_hoursTF;
	}

}
