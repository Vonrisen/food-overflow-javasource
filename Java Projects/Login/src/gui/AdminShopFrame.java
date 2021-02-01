package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.AdminController;
import entities.Address;
import gui_support.RoundJTextField;
import utilities.InputUtility;


public class AdminShopFrame extends JFrame{

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private ImageIcon delete_inactiveIMG;
	private ImageIcon delete_activeIMG;
	private ImageIcon insert_inactiveIMG;
	private ImageIcon insert_activeIMG;
	private ImageIcon update_inactiveIMG;
	private ImageIcon update_activeIMG;
	private ImageIcon go_back_inactiveIMG;
	private ImageIcon go_back_activeIMG;
	private ImageIcon search_riders_inactiveIMG;
	private ImageIcon search_riders_activeIMG;
	private ImageIcon shops_table_title;
	
	private Dimension long_dim_of_textfield;
	private Dimension short_dim_of_textfield;
	
	private Dimension button_size;
	private Dimension west_east_size;
	private Dimension north_south_size;
	
	private String[]columns = {"E-mail", "Password", "Nome", "Indirizzo", "Orario", "Giorni di chiusura"};
	
	private JPanel west_panel;
	private JPanel east_panel;
	private JPanel north_panel;
	private JPanel south_panel;
	private JPanel center_panel;
	private JPanel sql_panel;
	private JTable table;

	private JPanel attributes_panel;
	private JPanel buttons_panel;
	private JScrollPane scroll_pane;
	
	private JButton insert_sqlJB;
	private JButton update_sqlJB;
	private JButton delete_sqlJB;
	private JButton go_backJB;
	private JButton	riders_searchJB;
	
	private JLabel shops_table_titleLB;
	
	private JTextField nameTF;
	private JTextField emailTF;
	private JTextField address_nameTF;
	private JTextField address_civic_numberTF;
	private JTextField address_capTF;
	private JTextField address_cityTF;
	private JTextField address_provinceTF;
	private JTextField working_hoursTF;
	private JTextField passwordTF;
	private JTextField closing_daysTF;
	
	private DefaultTableModel model; 
	private AdminController admin_controller;
	private Color background_color = new Color(0xf3ecd7);
	

	//Create the application
	public AdminShopFrame(AdminController admin_controller) {
		
		initialize();
		frameSetup();
		events();
		this.admin_controller = admin_controller;
	}
	

	//Initialize variables
	    private void initialize() {
	    
		delete_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonInactive.png");
		delete_activeIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonActive.png");
		insert_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\insertButtonInactive.png");
		insert_activeIMG = new ImageIcon("src\\images\\SqlButtons\\insertButtonActive.png");
		update_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\updateButtonInactive.png");
		update_activeIMG = new ImageIcon("src\\images\\SqlButtons\\updateButtonActive.png");
		go_back_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\goBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\SqlButtons\\goBackActive.png");
		search_riders_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\searchRidersButtonInactive.png");
		search_riders_activeIMG = new ImageIcon("src\\images\\SqlButtons\\searchRidersButtonActive.png");
		shops_table_title = new ImageIcon("src\\images\\tableTitles\\shops.png");
		
		long_dim_of_textfield = new Dimension(335,25);
		short_dim_of_textfield = new Dimension(150,25);
		button_size = new Dimension(150,30);
		west_east_size = new Dimension(100,50);
		north_south_size = new Dimension(100,75);
		
		west_panel = new JPanel();
		east_panel = new JPanel();
		north_panel = new JPanel();
		south_panel = new JPanel();
		center_panel = new JPanel();
		sql_panel = new JPanel();
		attributes_panel = new JPanel();
		buttons_panel = new JPanel();
		table = new JTable();
		scroll_pane = new JScrollPane(table);
		
		shops_table_titleLB = new JLabel();
		nameTF = new RoundJTextField(new Color(0x771007));
		emailTF = new RoundJTextField(new Color(0x771007));
		address_nameTF = new RoundJTextField(new Color(0x771007));
		address_civic_numberTF = new RoundJTextField(new Color(0x771007));
		address_capTF = new RoundJTextField(new Color(0x771007));
		address_cityTF = new RoundJTextField(new Color(0x771007));
		address_provinceTF = new RoundJTextField(new Color(0x771007));
		working_hoursTF = new RoundJTextField(new Color(0x771007));
		passwordTF = new RoundJTextField(new Color(0x771007));
		closing_daysTF = new RoundJTextField(new Color(0x771007));
		
		go_backJB = new JButton();
		insert_sqlJB = new JButton();
		update_sqlJB = new JButton();
		delete_sqlJB = new JButton();
		riders_searchJB = new JButton();
	
	}
	
	//Setup layout of the frame
	
	//Frame Setup
	private void frameSetup() {
		
		this.setTitle("[Admin Panel] Gestione ristoranti");
		this.setSize(1280,720);
		this.setMinimumSize(new Dimension(800,680));
		int central_width = screen_dim.width/2-this.getSize().width/2;
		int central_height = screen_dim.height/2-this.getSize().height/2;
		this.setLocation(central_width, central_height); //Setta il frame a centro monitor
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().setBackground(background_color);
		
		
		createStandardPanel(west_panel, null, west_east_size);
		this.getContentPane().add(west_panel, BorderLayout.WEST);
		
		createStandardPanel(east_panel, null, west_east_size);
		this.getContentPane().add(east_panel, BorderLayout.EAST);
		
		createStandardPanel(north_panel, null, north_south_size);
		this.getContentPane().add(north_panel, BorderLayout.NORTH);
		
		createStandardPanel(south_panel, null, north_south_size);
		this.getContentPane().add(south_panel, BorderLayout.SOUTH);
		
		center_panel.setLayout(new BorderLayout());
		center_panel.setBackground(null);
		this.getContentPane().add(center_panel, BorderLayout.CENTER);
		
		//Impostazione JTable
		
	    table.setAutoCreateRowSorter(true);
	    table.setRowSelectionAllowed(true);
	    table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model = new DefaultTableModel(columns, 0) {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
		table.getTableHeader().setReorderingAllowed(false);
		table.setPreferredScrollableViewportSize(new Dimension(500,50));
		table.setFillsViewportHeight(true);
		
		//Sottopannelli di "center_panel"
		
		sql_panel.setLayout(new BorderLayout());
		createStandardPanel(sql_panel, null, (new Dimension(400,50)));
		center_panel.add(sql_panel, BorderLayout.EAST);
		
		table.setPreferredScrollableViewportSize(new Dimension(500,50));
		table.setFillsViewportHeight(true);
		
		center_panel.add(scroll_pane, BorderLayout.CENTER);
		
		shops_table_titleLB.setIcon(shops_table_title);
		shops_table_titleLB.setSize(225,100);
		north_panel.add(shops_table_titleLB);
		
		//Sottopannelli di "sql_panel"
		
		attributes_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 35,10));
		createStandardPanel(attributes_panel, null, (new Dimension(100,500)));
		sql_panel.add(attributes_panel, BorderLayout.CENTER);
		
		createStandardPanel(buttons_panel, null, (new Dimension(100,100)));
		sql_panel.add(buttons_panel, BorderLayout.SOUTH);
		
		//Textfields setup
		
		createTextField(nameTF, "Nome", short_dim_of_textfield);
		attributes_panel.add(nameTF);
		
		createTextField(emailTF, "E-mail", short_dim_of_textfield);
		attributes_panel.add(emailTF);
		
		createTextField(address_nameTF, "Nome dell'indirizzo", long_dim_of_textfield);
		attributes_panel.add(address_nameTF);
		
		createTextField(address_civic_numberTF, "Numero civico", short_dim_of_textfield);
		attributes_panel.add(address_civic_numberTF);
		
		createTextField(address_capTF, "CAP", short_dim_of_textfield);
		attributes_panel.add(address_capTF);
		
		createTextField(address_cityTF, "Comune", short_dim_of_textfield);
		attributes_panel.add(address_cityTF);
		
		createTextField(address_provinceTF, "Provincia", short_dim_of_textfield);
		attributes_panel.add(address_provinceTF);
		
		createTextField(working_hoursTF, "Orario", short_dim_of_textfield);
		attributes_panel.add(working_hoursTF);
		
		createTextField(passwordTF, "Password", short_dim_of_textfield);
		attributes_panel.add(passwordTF);
		
		createTextField(closing_daysTF, "Giorni di chiusura", long_dim_of_textfield);
		attributes_panel.add(closing_daysTF);
		
		setupButton(riders_searchJB, search_riders_inactiveIMG, new Dimension(335,30));
		attributes_panel.add(riders_searchJB);
		
		//Buttons setup
		
		setupButton(insert_sqlJB, insert_inactiveIMG, button_size);
		buttons_panel.add(insert_sqlJB);
		
		setupButton(update_sqlJB, update_inactiveIMG, button_size);
		buttons_panel.add(update_sqlJB);
		
		setupButton(delete_sqlJB, delete_inactiveIMG, button_size);
		buttons_panel.add(delete_sqlJB);
		
		setupButton(go_backJB, go_back_inactiveIMG, button_size);
		buttons_panel.add(go_backJB);
			
	}
	
	private void events() {
		
		insert_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
			admin_controller.addShop(AdminShopFrame.this);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				insert_sqlJB.setIcon(insert_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				insert_sqlJB.setIcon(insert_inactiveIMG);
				
			}
			
		});
		
		update_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.updateShop(AdminShopFrame.this);
		
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				update_sqlJB.setIcon(update_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				update_sqlJB.setIcon(update_inactiveIMG);
				
			}
		});
		
		
		delete_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.removeShop(AdminShopFrame.this);
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			delete_sqlJB.setIcon(delete_activeIMG);

			}
			@Override
			public void mouseExited(MouseEvent e) {

			delete_sqlJB.setIcon(delete_inactiveIMG);

			}
			});
		
		 table.addMouseListener(new java.awt.event.MouseAdapter() {
		        @Override
		        public void mouseClicked(java.awt.event.MouseEvent evt) {	           
		        	
                        if(!table.getSelectionModel().isSelectionEmpty()) {
			            InputUtility input_util= new InputUtility();
			            Address address;
		        		String closing_days;
	        	    	address = input_util.tokenizedAddressToString(table.getModel().getValueAt(table.getSelectedRow(), 3).toString(), "(, )");
	        	    	try
	        	    	{
	        			closing_days = table.getModel().getValueAt(table.getSelectedRow(), 5).toString();
	        	    	}catch(Exception e)
	        	    	{
	        	    		closing_days = "";
	        	    	}
	        	    	emailTF.setText(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
	        			nameTF.setText(table.getModel().getValueAt(table.getSelectedRow(), 2).toString());
	        			address_nameTF.setText(address.getName());
	        			address_civic_numberTF.setText(address.getCivic_number());
	        			address_capTF.setText(address.getCap());
	        			address_cityTF.setText(address.getCity());
	        			address_provinceTF.setText(address.getProvince_abbrevation());
	        			working_hoursTF.setText(table.getModel().getValueAt(table.getSelectedRow(), 4).toString());
	        			closing_daysTF.setText(closing_days);
	        			passwordTF.setText(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
	        			
		        	} 
	        	}
	        	});
		 
		go_backJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				admin_controller.openAdminFrame(AdminShopFrame.this);
			
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				go_backJB.setIcon(go_back_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				go_backJB.setIcon(go_back_inactiveIMG);
				
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
		

		 addWindowListener(new WindowAdapter()
	     {
	         @Override
	         public void windowClosing(WindowEvent e)
	         {
	             admin_controller.closeWindow(AdminShopFrame.this);
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
		
	}
	
	private void textFieldFocusGained(JTextField text_field, String string) {
		
		if (text_field.getText().equals(string)) {
			
			text_field.setText("");
			text_field.setHorizontalAlignment(JTextField.LEFT);
			
		}
		
	}
	
	private void textFieldFocusLost(JTextField text_field, String string) {
		
		if (text_field.getText().equals("")) {
			text_field.setText(string);
			text_field.setHorizontalAlignment(JTextField.CENTER);
		}
		
	}
	
	private void createTextField(JTextField text_field, String text, Dimension dimension) {

		text_field.setHorizontalAlignment(JTextField.CENTER);
		text_field.setText(text);
		text_field.setPreferredSize(dimension);

	}
	
	private void setupButton(JButton button, ImageIcon image, Dimension dimension) {
		
		button.setIcon(image);
		button.setSize(dimension);
		button.setBorder(null);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		
	}
	
	private void createStandardPanel(JPanel panel, Color color, Dimension dimension) {

		panel.setBackground(color);
		panel.setPreferredSize(dimension);

	}
	
	public JTable getTable() {
		return table;
	}


	public void setTable(JTable table) {
		this.table = table;
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


	public DefaultTableModel getModel() {
		return model;
	}


	public void setModel(DefaultTableModel model) {
		this.model = model;
	}


	public JTextField getEmailTF() {
		return emailTF;
	}


	public void setEmailTF(JTextField emailTF) {
		this.emailTF = emailTF;
	}
	
	
	
	
}
