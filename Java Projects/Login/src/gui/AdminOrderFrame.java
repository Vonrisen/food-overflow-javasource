package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.AdminController;
import gui_support.RoundJTextField;

@SuppressWarnings("serial")
public class AdminOrderFrame extends ComplexFrame {

	private ImageIcon search_inactiveIMG;
	private ImageIcon search_activeIMG;
	private ImageIcon search_all_orders_inactiveIMG;
	private ImageIcon search_all_orders_activeIMG;

	private JTextField price_minTF;
	private JTextField price_maxTF;
	private String[] dish_array_strings = { "Seleziona categoria", "-------------------", "Primo piatto", "Carne",
			"Pesce", "Pizza", "Panino", "Fritto", "Dolce", "Bevande analcoliche", "Bevande alcoliche" };
	private String[] vehicleStrings = { "Seleziona veicolo del rider", "-------------------", "Bicicletta",
			"Motoveicolo", "Autoveicolo" };
	private String[] columns = { "ID", "Data", "Orario di consegna", "Indirizzo", "Stato", "Pagamento", "Note",
			"Cliente", "Rider" };

	private JComboBox<String> categoryCB;
	private JComboBox<String> vehicleCB;
	private JComboBox<String> addressCB;

	private JButton searchJB;
	private JButton search_all_ordersJB;

	private AdminController admin_controller;

	public AdminOrderFrame(AdminController admin_controller) {
		initialize();
		frameSetup();
		events();
		this.admin_controller = admin_controller;
	}

	// Initialize variables
	private void initialize() {

		search_inactiveIMG = new ImageIcon("src\\images\\buttons\\complexSearchInactive.png");
		search_activeIMG = new ImageIcon("src\\images\\buttons\\complexSearchActive.png");
		search_all_orders_inactiveIMG = new ImageIcon("src\\images\\buttons\\searchAllOrdersInactive.png");
		search_all_orders_activeIMG = new ImageIcon("src\\images\\buttons\\searchAllOrdersActive.png");
		table_title = new ImageIcon("src\\images\\others\\orders.png");

		price_minTF = new RoundJTextField(new Color(0x771007));
		price_maxTF = new RoundJTextField(new Color(0x771007));

		categoryCB = new JComboBox<String>(dish_array_strings);
		vehicleCB = new JComboBox<String>(vehicleStrings);
		addressCB = new JComboBox<String>();
		searchJB = new JButton();
		search_all_ordersJB = new JButton();

		table.setModel(model = new DefaultTableModel(columns, 0));
	}

	// Frame Setup
	private void frameSetup() {

		// Sottopannelli di "center_panel"
		table_titleLB.setPreferredSize(new Dimension(500, 50));
		table_titleLB.setIcon(table_title);
		setTitle("Food Overflow - Admin Panel: Ricerca complessa ordini");

		scroll_pane.setBorder(BorderFactory.createLineBorder(new Color(0x771007), 5));

		// Textfields setup

		categoryCB.setPreferredSize(short_dim_of_textfield);
		categoryCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		categoryCB.setFocusable(false);
		categoryCB.setBackground(Color.white);
		attributes_panel.add(categoryCB);

		vehicleCB.setPreferredSize(short_dim_of_textfield);
		vehicleCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		vehicleCB.setFocusable(false);
		vehicleCB.setBackground(Color.white);
		attributes_panel.add(vehicleCB);

		createTextField(price_minTF, "Prezzo minimo", short_dim_of_textfield);
		attributes_panel.add(price_minTF);

		createTextField(price_maxTF, "Prezzo massimo", short_dim_of_textfield);
		attributes_panel.add(price_maxTF);

		addressCB.setPreferredSize(long_dim_of_textfield);
		addressCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		addressCB.setFocusable(false);
		addressCB.setBackground(Color.white);
		attributes_panel.add(addressCB);
		
		setupButton(search_all_ordersJB, search_all_orders_inactiveIMG, new Dimension(335, 30));
		attributes_panel.add(search_all_ordersJB);

		setupButton(searchJB, search_inactiveIMG, button_size);
		buttons_panel.add(searchJB);

		buttons_panel.add(go_backJB);

	}

	private void events() {

		go_backJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.openAdminFrame(AdminOrderFrame.this);
			}
		});

		searchJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				searchJB.setIcon(search_activeIMG);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				searchJB.setIcon(search_inactiveIMG);
			}

			@Override
			public void mousePressed(MouseEvent e) {

				admin_controller.doAdminComplexSearch(AdminOrderFrame.this);

			}

		});
		
		search_all_ordersJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				search_all_ordersJB.setIcon(search_all_orders_activeIMG);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				search_all_ordersJB.setIcon(search_all_orders_inactiveIMG);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.visualizeAllOrders(AdminOrderFrame.this);
				
			}

		});

		price_maxTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(price_maxTF, "Prezzo massimo");
			}

			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(price_maxTF, "Prezzo massimo");
			}
		});

		price_minTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(price_minTF, "Prezzo minimo");
			}

			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(price_minTF, "Prezzo minimo");
			}
		});

	}

	public JComboBox<String> getAddressCB() {
		return addressCB;
	}

	public JTextField getPrice_minTF() {
		return price_minTF;
	}

	public JTextField getPrice_maxTF() {
		return price_maxTF;
	}

	public JComboBox<String> getCategoryCB() {
		return categoryCB;
	}

	public JComboBox<String> getVehicleCB() {
		return vehicleCB;
	}

	public DefaultTableModel getModel() {
		return model;
	}

}
