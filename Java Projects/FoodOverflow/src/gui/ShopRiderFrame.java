package gui;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.ShopController;
import gui_support.RoundJTextField;
import utilities.TableModelUtility;

@SuppressWarnings("serial")
public class ShopRiderFrame extends ComplexFrame {

	private String[] columns = { "CF", "Nome", "Cognome", "Data di nascita", "Luogo di nascita", "Indirizzo", "Sesso",
			"Cellulare", "Veicolo", "Orario di lavoro" };
	private String[] vehicleStrings = { "Bicicletta", "Motoveicolo", "Autoveicolo" };
	private String[] genderStrings = { "Maschio", "Femmina" };

	private JTextField nameTF;
	private JTextField surnameTF;
	private JTextField birth_dateTF;
	private JTextField address_nameTF;
	private JTextField address_civic_numberTF;
	private JTextField address_capTF;
	private JTextField working_hoursTF;
	private JTextField cellphoneTF;

	private JComboBox<String> vehicleCB;
	private JComboBox<String> genderCB;
	private JComboBox<String> address_provinceCB;
	private JComboBox<String> address_townCB;
	private JComboBox<String> birth_townCB;
	private JComboBox<String> birth_nationCB;
	private JComboBox<String> birth_provinceCB;
	private ShopController shop_controller;

	// Costruttore
	public ShopRiderFrame(ShopController shop_controller) {
		initialize();
		setupFrame();
		events();
		this.shop_controller = shop_controller;
	}

	private void initialize() {

		table_title = new ImageIcon("src\\images\\others\\riders.png");
		nameTF = new RoundJTextField(new Color(0x771007));
		surnameTF = new RoundJTextField(new Color(0x771007));
		birth_dateTF = new RoundJTextField(new Color(0x771007));
		address_nameTF = new RoundJTextField(new Color(0x771007));
		address_civic_numberTF = new RoundJTextField(new Color(0x771007));
		address_capTF = new RoundJTextField(new Color(0x771007));
		working_hoursTF = new RoundJTextField(new Color(0x771007));
		cellphoneTF = new RoundJTextField(new Color(0x771007));
		vehicleCB = new JComboBox<String>(vehicleStrings);
		genderCB = new JComboBox<String>(genderStrings);
		table.setModel(model = new DefaultTableModel(columns, 0));

		address_provinceCB = new JComboBox<String>();
		address_townCB = new JComboBox<String>();
		birth_nationCB = new JComboBox<String>();
		birth_townCB = new JComboBox<String>();
		birth_provinceCB = new JComboBox<String>();

	}

	private void setupFrame() {

		// Layout setup
		this.setTitle("Food Overflow - Shop Panel: Gestione rider");

		scroll_pane.setBorder(BorderFactory.createLineBorder(new Color(0x771007), 5));

		// Textfields setup
		createTextField(nameTF, "Nome", short_dim_of_textfield);
		attributes_panel.add(nameTF);

		createTextField(surnameTF, "Cognome", short_dim_of_textfield);
		attributes_panel.add(surnameTF);

		createTextField(birth_dateTF, "Data di n. (dd/MM/yyyy)", short_dim_of_textfield);
		attributes_panel.add(birth_dateTF);

		birth_nationCB.setPreferredSize(short_dim_of_textfield);
		setupComboBox(birth_nationCB);
		attributes_panel.add(birth_nationCB);

		birth_provinceCB.setPreferredSize(short_dim_of_textfield);
		setupComboBox(birth_provinceCB);
		attributes_panel.add(birth_provinceCB);

		birth_townCB.setPreferredSize(short_dim_of_textfield);
		setupComboBox(birth_townCB);
		attributes_panel.add(birth_townCB);

		createTextField(address_nameTF, "Nome dell'indirizzo", long_dim_of_textfield);
		attributes_panel.add(address_nameTF);

		createTextField(address_civic_numberTF, "Numero civico", short_dim_of_textfield);
		attributes_panel.add(address_civic_numberTF);

		createTextField(address_capTF, "CAP", short_dim_of_textfield);
		attributes_panel.add(address_capTF);

		address_provinceCB.setPreferredSize(short_dim_of_textfield);
		setupComboBox(address_provinceCB);
		attributes_panel.add(address_provinceCB);

		address_townCB.setPreferredSize(short_dim_of_textfield);
		setupComboBox(address_townCB);
		attributes_panel.add(address_townCB);

		createTextField(cellphoneTF, "Cellulare", short_dim_of_textfield);
		attributes_panel.add(cellphoneTF);

		genderCB.setPreferredSize(short_dim_of_textfield);
		setupComboBox(genderCB);
		attributes_panel.add(genderCB);

		vehicleCB.setPreferredSize(short_dim_of_textfield);
		setupComboBox(vehicleCB);
		attributes_panel.add(vehicleCB);

		createTextField(working_hoursTF, "Orario di lavoro", short_dim_of_textfield);
		attributes_panel.add(working_hoursTF);

		// Buttons & Label setup
		table_titleLB.setIcon(table_title);
		buttons_panel.add(insert_sqlJB);
		buttons_panel.add(update_sqlJB);
		buttons_panel.add(delete_sqlJB);
		buttons_panel.add(go_backJB);

	}

	private void events() {

		insert_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.addRider(ShopRiderFrame.this);
			}
		});

		update_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.updateRider(ShopRiderFrame.this);
			}
		});

		delete_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.removeRider(ShopRiderFrame.this);
			}
		});

		go_backJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.openShopFrame(ShopRiderFrame.this);
			}
		});

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				TableModelUtility table_utility = new TableModelUtility();
				table_utility.fillFieldsFromJTable(ShopRiderFrame.this);
			}

		});

		// FocusListeners

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
				textFieldFocusGained(birth_dateTF, "Data di n. (dd/MM/yyyy)");
			}

			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(birth_dateTF, "Data di n. (dd/MM/yyyy)");
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

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				shop_controller.releaseAllDaoResourcesAndDisposeFrame(ShopRiderFrame.this);
			}
		});

		birth_provinceCB.addItemListener(this::birth_provinceCBitemStateChanged);
		birth_nationCB.addItemListener(this::birth_nationCBitemStateChanged);
		address_provinceCB.addItemListener(this::address_provinceCBitemStateChanged);

	}

	private void setupComboBox(JComboBox<String> cb) {

		cb.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		cb.setFocusable(false);
		cb.setBackground(Color.white);

	}

	private void birth_provinceCBitemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String selected_item = (String) e.getItem();
			if (!selected_item.equals("-------------------") || !selected_item.equals("Seleziona provincia di nascita"))
				shop_controller.updateBirth_townCB(selected_item, ShopRiderFrame.this);
			if (selected_item.equals("Seleziona provincia di nascita")) {
				this.birth_townCB.removeAllItems();
				this.birth_townCB.addItem("Seleziona comune di nascita");
			}

		}
	}

	private void address_provinceCBitemStateChanged(ItemEvent e) {

		if (e.getStateChange() == ItemEvent.SELECTED) {
			String selected_item = (String) e.getItem();
			if (!selected_item.equals("-------------------")
					|| !selected_item.equals("Seleziona provincia di residenza"))
				shop_controller.updateAddress_townCB(selected_item, ShopRiderFrame.this);
			if (selected_item.equals("Seleziona provincia di residenza")) {
				this.address_townCB.removeAllItems();
				this.address_townCB.addItem("Seleziona comune di residenza");
			}
		}

	}

	private void birth_nationCBitemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {

			if (birth_nationCB.getSelectedItem().equals("ITALIA")) {
				birth_provinceCB.setVisible(true);
				birth_townCB.setVisible(true);
			} else {

				birth_provinceCB.setVisible(false);
				birth_townCB.setVisible(false);

			}

		}
	}

	public JTextField getNameTF() {
		return nameTF;
	}

	public JTextField getSurnameTF() {
		return surnameTF;
	}

	public JTextField getBirth_dateTF() {
		return birth_dateTF;
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

	public JTextField getCellphoneTF() {
		return cellphoneTF;
	}

	public JComboBox<String> getVehicleCB() {
		return vehicleCB;
	}

	public JComboBox<String> getAddress_provinceCB() {
		return address_provinceCB;
	}

	public JComboBox<String> getAddress_townCB() {
		return address_townCB;
	}

	public JComboBox<String> getBirth_townCB() {
		return birth_townCB;
	}

	public JComboBox<String> getBirth_nationCB() {
		return birth_nationCB;
	}

	public JComboBox<String> getBirth_provinceCB() {
		return birth_provinceCB;
	}

	public JComboBox<String> getGenderCB() {
		return genderCB;
	}

}
