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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.AdminController;
import controllers.ShopController;
import gui_support.RoundJTextField;

public class ShopPendingOrdersFrame extends JFrame{

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private ImageIcon update_inactiveIMG;
	private ImageIcon update_activeIMG;
	private ImageIcon go_back_inactiveIMG;
	private ImageIcon go_back_activeIMG;
	private ImageIcon orders_table_title;
	private ImageIcon search_riders_inactiveIMG;
	private ImageIcon search_riders_activeIMG;
	
	private Dimension long_dim_of_textfield;
	
	private Dimension button_size;
	private Dimension west_east_size;
	private Dimension north_south_size;
	
	private String[] columns = {"ID","Data","Indirizzo","Pagamento","Note","Cliente"};
	
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
	private JButton go_backJB;
	private JButton	riders_searchJB;
	
	private JLabel orders_table_titleLB;
	private JTextField orderTF;
	private JTextField riderTF;
	
	private DefaultTableModel model;
	private ShopController shop_controller;
	private Color background_color = new Color(0xf3ecd7);
	
	
	public ShopPendingOrdersFrame(ShopController shop_controller) {
		
		initialize();
		frameSetup();
		events();
		this.shop_controller = shop_controller;
		
	}

	private void initialize() {
		
		update_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\updateButtonInactive.png");
		update_activeIMG = new ImageIcon("src\\images\\SqlButtons\\updateButtonActive.png");
		go_back_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\goBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\SqlButtons\\goBackActive.png");
		orders_table_title = new ImageIcon("src\\images\\tableTitles\\orders.png");
		search_riders_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\searchRidersButtonInactive.png");
		search_riders_activeIMG = new ImageIcon("src\\images\\SqlButtons\\searchRidersButtonActive.png");
		
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
		
		table = (new JTable() {
			
			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				super.changeSelection(rowIndex, columnIndex, true, false);
			}
			
		});
		scroll_pane = new JScrollPane(table);
		
		orders_table_titleLB = new JLabel();
		orderTF = new RoundJTextField(new Color(0x771007));
		riderTF = new RoundJTextField(new Color(0x771007));
		
		go_backJB = new JButton();
		update_sqlJB = new JButton();
		riders_searchJB = new JButton();
		
	}
	
	private void frameSetup() {
		
		//Layout setup
		
		this.setTitle("[Shop Panel] Ordini in attesa");
		this.setSize(1280,720);
		this.setMinimumSize(new Dimension(800,500));
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
		
		center_panel.add(scroll_pane, BorderLayout.CENTER);
		
		//Sottopannelli di "sql_panel"
		
		attributes_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 35,15));
		createStandardPanel(attributes_panel, null, (new Dimension(100,500)));
		sql_panel.add(attributes_panel, BorderLayout.CENTER);
		
		createStandardPanel(buttons_panel, null, (new Dimension(100,100)));
		sql_panel.add(buttons_panel, BorderLayout.SOUTH);
		
		//Textfields setup
		
		createTextField(orderTF, "Inserisci l'ID dell'ordine", long_dim_of_textfield);
		attributes_panel.add(orderTF);
		
		createTextField(riderTF, "Inserisci il CF del rider", long_dim_of_textfield);
		attributes_panel.add(riderTF);
		
		//Buttons & Label setup
		
		orders_table_titleLB.setIcon(orders_table_title);
		orders_table_titleLB.setSize(225,100);
		north_panel.add(orders_table_titleLB);
		
		setupButton(riders_searchJB, search_riders_inactiveIMG, new Dimension(335,30));
		attributes_panel.add(riders_searchJB);
		
		update_sqlJB.setIcon(update_inactiveIMG);
		setupButton(update_sqlJB, update_inactiveIMG, button_size);
		buttons_panel.add(update_sqlJB);
		
		setupButton(go_backJB, go_back_inactiveIMG, button_size);
		buttons_panel.add(go_backJB);
			
	}
	
	private void events() {
		
		update_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				shop_controller.updatePendingOrder(ShopPendingOrdersFrame.this);
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
		
		go_backJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				shop_controller.openShopOrderManagementFrame(ShopPendingOrdersFrame.this);
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
		
		orderTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(orderTF, "Inserisci l'ID dell'ordine");

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(orderTF, "Inserisci l'ID dell'ordine");
				
			}
		});
		
		riderTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(riderTF, "Inserisci il CF del rider");

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(riderTF, "Inserisci il CF del rider");
				
			}
		});
		
		
		riders_searchJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			shop_controller.openAdminRiderFrame();
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

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTextField getOrderTF() {
		return orderTF;
	}

	public void setOrderTF(JTextField orderTF) {
		this.orderTF = orderTF;
	}

	public JTextField getRiderTF() {
		return riderTF;
	}

	public void setRiderTF(JTextField riderTF) {
		this.riderTF = riderTF;
	}

}