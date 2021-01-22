package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.AdminController;
import gui_support.RoundJTextField;

public class AdminCustomerFrame extends JFrame {

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private ImageIcon delete_inactiveIMG;
	private ImageIcon delete_activeIMG;
	private ImageIcon update_inactiveIMG;
	private ImageIcon update_activeIMG;
	private ImageIcon go_back_inactiveIMG;
	private ImageIcon go_back_activeIMG;
	private ImageIcon customers_table_title;
	
	private Dimension long_dim_of_textfield;
	
	private Dimension button_size;
	private Dimension west_east_size;
	private Dimension north_south_size;
	
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
	
	private JButton update_sqlJB;
	private JButton delete_sqlJB;
	private JButton go_backJB;
	
	private JLabel customers_table_titleLB;
	
	private JTextField emailTF;
	private JTextField passwordTF;
	
	DefaultTableModel model;
	
	private Color background_color = new Color(0xf3ecd7);
	AdminController admin_controller;
	public AdminCustomerFrame(AdminController admin_controller) {
		
		initialize();
		frameSetup();
		events();
		this.admin_controller=admin_controller;
	}

	//Initialize variables
	private void initialize() {
		
		
		delete_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonInactive.png");
		delete_activeIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonActive.png");
		update_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\updateButtonInactive.png");
		update_activeIMG = new ImageIcon("src\\images\\SqlButtons\\updateButtonActive.png");
		go_back_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\goBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\SqlButtons\\goBackActive.png");
		customers_table_title = new ImageIcon("src\\images\\tableTitles\\customers.png");
		
		long_dim_of_textfield = new Dimension(335,25);
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
		
		customers_table_titleLB = new JLabel();
		emailTF = new RoundJTextField(new Color(0x771007));
		passwordTF = new RoundJTextField(new Color(0x771007));
		
		go_backJB = new JButton();
		update_sqlJB = new JButton();
		delete_sqlJB = new JButton();
		
	}
	
	//Setup layout of the frame
	private void frameSetup() {
		
		this.setTitle("Admin Panel: Customers");
		this.setSize(1280,720);
		this.setMinimumSize(new Dimension(800,500));
		String[] columns = {"CF", "Name", "Surname", "Birth date", "Birth place", "Address", "Gender", "Cellphone", "Email", "Password"};
	    table.setFocusable(false);
	    table.setRowSelectionAllowed(true);
		table.setModel(model = new DefaultTableModel(columns, 0) {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
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
		
		//Subpanels di center_panel
		
		sql_panel.setLayout(new BorderLayout());
		createStandardPanel(sql_panel, null, (new Dimension(400,50)));
		center_panel.add(sql_panel, BorderLayout.EAST);
		
		table.setPreferredScrollableViewportSize(new Dimension(500,50));
		table.setFillsViewportHeight(true);
		
		center_panel.add(scroll_pane, BorderLayout.CENTER);
		
		customers_table_titleLB.setIcon(customers_table_title);
		customers_table_titleLB.setSize(225,100);
		north_panel.add(customers_table_titleLB);
		
		//Subpanels di sql_panel
		
		attributes_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 35,50));
		createStandardPanel(attributes_panel, null, (new Dimension(100,500)));
		sql_panel.add(attributes_panel, BorderLayout.CENTER);
		
		createStandardPanel(buttons_panel, null, (new Dimension(100,100)));
		sql_panel.add(buttons_panel, BorderLayout.SOUTH);
		
		//Setup TextFields
		
		createTextField(emailTF, "E-Mail", long_dim_of_textfield);
		attributes_panel.add(emailTF);
		
		createTextField(passwordTF, "Password", long_dim_of_textfield);
		attributes_panel.add(passwordTF);
		
		//Setup Buttons
		
		update_sqlJB.setIcon(update_inactiveIMG);
		setupButton(update_sqlJB, update_inactiveIMG, button_size);
		buttons_panel.add(update_sqlJB);
		
		delete_sqlJB.setIcon(delete_inactiveIMG);
		setupButton(delete_sqlJB, delete_inactiveIMG, button_size);
		buttons_panel.add(delete_sqlJB);
		
		setupButton(go_backJB, go_back_inactiveIMG, button_size);
		buttons_panel.add(go_backJB);
			
	}
	
	//All events about frame
	private void events() {
		
		//MouseListeners
		
		update_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//UPDATE

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
				
				//DELETE
			
					admin_controller.removeCustomer(AdminCustomerFrame.this);
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
		
		go_backJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				//Apre AdminCustomerFrame
				AdminController admin_controller = new AdminController();
				admin_controller.openAdminFrame(AdminCustomerFrame.this);
			
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
		
		//FocusListeners
		
		emailTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(emailTF, emailTF.getText());

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(emailTF, "E-Mail");
				
			}
		});
		
		passwordTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(passwordTF, passwordTF.getText());

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(passwordTF, "Password");

			}
		});
		
		
	}
	
	//Methods
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

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	
	

}
