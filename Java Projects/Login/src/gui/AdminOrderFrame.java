package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.AdminController;
import gui_support.RoundJTextField;

public class AdminOrderFrame extends ComplexFrame {
	
	private ImageIcon search_inactiveIMG;
	private ImageIcon search_activeIMG;

	private JTextField price_minTF;
	private JTextField price_maxTF;
	private String[] dish_array_strings = {"Seleziona categoria", "-------------------", "Primo piatto", "Carne", "Pesce", "Pizza","Panino", "Fritto", "Dolce", "Bevande analcoliche", "Bevande alcoliche" };
	private String[] vehicleStrings = {"Seleziona mezzo del rider", "-------------------", "Bicicletta", "Motoveicolo", "Autoveicolo"};
	private String[] columns = {"ID","Data","Orario di consegna","Indirizzo","Stato","Pagamento","Note","Cliente","Rider"};
	
	private JComboBox<String> categoryCB;
	private JComboBox<String> vehicleCB;
	private JComboBox<String> addressCB;
	
	private JButton searchJB;
	
	private AdminController admin_controller;
	
	public AdminOrderFrame(AdminController admin_controller) {
		initialize();
		frameSetup();
		events();
		this.admin_controller = admin_controller;
	}

	//Initialize variables
	private void initialize() {

		search_inactiveIMG = new ImageIcon("src\\images\\buttons\\complexSearchInactive.png");
		search_activeIMG = new ImageIcon("src\\images\\buttons\\complexSearchActive.png");
		setTable_title(new ImageIcon("src\\images\\others\\orders.png"));
		

		price_minTF = new RoundJTextField(new Color(0x771007));
		price_maxTF = new RoundJTextField(new Color(0x771007));

		categoryCB = new JComboBox<String>(dish_array_strings);
		vehicleCB = new JComboBox<String>(vehicleStrings);
		addressCB = new JComboBox<String>();
		searchJB = new JButton();
		
		getTable().setModel(model = new DefaultTableModel(columns, 0));
	}


	//Frame Setup
	private void frameSetup() {

		//Sottopannelli di "center_panel"
		getTable_titleLB().setPreferredSize(new Dimension(500,50));
		getTable_titleLB().setIcon(getTable_title());
		setTitle("Food Overflow - Admin Panel: Ricerca complessa ordini");

		getScroll_pane().setBorder(BorderFactory.createLineBorder(new Color(0x771007), 5));
		
		//Textfields setup
		
		categoryCB.setPreferredSize(getShort_dim_of_textfield());
		categoryCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		categoryCB.setFocusable(false);
		categoryCB.setBackground(Color.white);
		getAttributes_panel().add(categoryCB);
		
		vehicleCB.setPreferredSize(getShort_dim_of_textfield());
		vehicleCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		vehicleCB.setFocusable(false);
		vehicleCB.setBackground(Color.white);
		getAttributes_panel().add(vehicleCB);
		
		createTextField(price_minTF, "Prezzo minimo", getShort_dim_of_textfield());
		getAttributes_panel().add(price_minTF);

		createTextField(price_maxTF, "Prezzo massimo", getShort_dim_of_textfield());
		getAttributes_panel().add(price_maxTF);
		
		addressCB.setPreferredSize(getLong_dim_of_textfield());
		addressCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		addressCB.setFocusable(false);
		addressCB.setBackground(Color.white);
		getAttributes_panel().add(addressCB);

		setupButton(searchJB, search_inactiveIMG, getButton_size());
		getButtons_panel().add(searchJB);
		
		getButtons_panel().add(getGo_backJB());
		

	}
	
	private void events() {
		
		getGo_backJB().addMouseListener(new MouseAdapter() {
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
				
				//Ricerca complessa
				
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

	public void setAddressCB(JComboBox<String> addressCB) {
		this.addressCB = addressCB;
	}

}
