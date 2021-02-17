package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class ComplexFrame extends JFrame{
	
	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	private Dimension long_dim_of_textfield;
	private Dimension short_dim_of_textfield;
	private Dimension button_size;
	private Dimension west_east_size;
	private Dimension north_south_size;
	
	private ImageIcon table_title;
	private ImageIcon delete_inactiveIMG;
	private ImageIcon delete_activeIMG;
	private ImageIcon update_inactiveIMG;
	private ImageIcon update_activeIMG;
	private ImageIcon insert_inactiveIMG;
	private ImageIcon insert_activeIMG;
	private ImageIcon go_back_inactiveIMG;
	private ImageIcon go_back_activeIMG;
	
	private JPanel west_panel;
	private JPanel east_panel;
	private JPanel north_panel;
	private JPanel south_panel;
	private JPanel center_panel;
	private JPanel sql_panel;
	private JPanel attributes_panel;
	private JPanel buttons_panel;
	
	private JTable table;
	private JScrollPane scroll_pane;
	
	private JButton insert_sqlJB;
	private JButton update_sqlJB;
	private JButton delete_sqlJB;
	private JButton go_backJB;
	
	private JLabel table_titleLB;
	private JLabel label;
	
	protected DefaultTableModel model;
	
	private Color background_color = new Color(0xf3ecd7);
	
	
	//Costruttore
	public ComplexFrame() {
		
		initialize();
		frameSetup();
		events();
	}
	
	
	private void initialize() {
		
		update_inactiveIMG = new ImageIcon("src\\images\\buttons\\updateButtonInactive.png");
		update_activeIMG = new ImageIcon("src\\images\\buttons\\updateButtonActive.png");
		delete_inactiveIMG = new ImageIcon("src\\images\\buttons\\deleteButtonInactive.png");
		delete_activeIMG = new ImageIcon("src\\images\\buttons\\deleteButtonActive.png");
		insert_inactiveIMG = new ImageIcon("src\\images\\buttons\\insertButtonInactive.png");
		insert_activeIMG = new ImageIcon("src\\images\\buttons\\insertButtonActive.png");
		go_back_inactiveIMG = new ImageIcon("src\\images\\buttons\\goBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\buttons\\goBackActive.png");
		
		long_dim_of_textfield = new Dimension(335,25);
		short_dim_of_textfield = new Dimension(150,25);
		button_size = new Dimension(150,30);
		west_east_size = new Dimension(100,50);
		north_south_size = new Dimension(100,75);
		
		label= new JLabel();
		table_titleLB = new JLabel();
		
		west_panel = new JPanel();
		east_panel = new JPanel();
		north_panel = new JPanel();
		south_panel = new JPanel();
		center_panel = new JPanel();
		sql_panel = new JPanel();
		buttons_panel = new JPanel();
		attributes_panel = new JPanel();
		
		table = (new JTable() {
			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				super.changeSelection(rowIndex, columnIndex, true, false);
			}
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scroll_pane = new JScrollPane(table);
		
		go_backJB = new JButton();
		insert_sqlJB = new JButton();
		delete_sqlJB = new JButton();
		update_sqlJB = new JButton();
		
	}
	
	
	private void frameSetup() {
		
		//Layout setup
		setSize(1280,720);
		setMinimumSize(new Dimension(800,680));
		int central_width = screen_dim.width/2-getSize().width/2;
		int central_height = screen_dim.height/2-getSize().height/2;
		setLocation(central_width, central_height); //Setta il frame a centro monitor
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(background_color);
		
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
		table.getTableHeader().setReorderingAllowed(false);
		table.setPreferredScrollableViewportSize(new Dimension(500,50));
		table.setFillsViewportHeight(true);
		
		
		//Subpanels di center_panel
		attributes_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 35,10));
		createStandardPanel(attributes_panel, null, (new Dimension(100,500)));
		sql_panel.add(attributes_panel, BorderLayout.CENTER);
		
		sql_panel.setLayout(new BorderLayout());
		createStandardPanel(sql_panel, null, (new Dimension(400,50)));
		center_panel.add(sql_panel, BorderLayout.EAST);
		
		center_panel.add(scroll_pane, BorderLayout.CENTER);
		
		
		//Sotto pannelli di "sql_panel"
		attributes_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 35,10));
		createStandardPanel(attributes_panel, null, (new Dimension(100,500)));
		sql_panel.add(attributes_panel, BorderLayout.CENTER);
		
		buttons_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 35,10));
		createStandardPanel(buttons_panel, null, (new Dimension(100,100)));
		sql_panel.add(buttons_panel, BorderLayout.SOUTH);
		
		
		//Textfields setup
		table_titleLB.setSize(225,100);
		north_panel.add(table_titleLB);
		label.setPreferredSize(long_dim_of_textfield);
		
		
		//Buttons setup
		setupButton(insert_sqlJB, insert_inactiveIMG, button_size);
		setupButton(update_sqlJB, update_inactiveIMG, button_size);
		setupButton(delete_sqlJB, delete_inactiveIMG, button_size);
		setupButton(go_backJB, go_back_inactiveIMG, button_size);

	}

	private void events() {
		
		insert_sqlJB.addMouseListener(new MouseAdapter() {
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
			public void mouseEntered(MouseEvent e) {
				go_backJB.setIcon(go_back_activeIMG);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				go_backJB.setIcon(go_back_inactiveIMG);
			}
		});
	}
	
	
	protected void textFieldFocusGained(JTextField text_field, String string) {
		if (text_field.getText().equals(string)) {
			text_field.setText("");
			text_field.setHorizontalAlignment(JTextField.LEFT);
		}
	}
	
	protected void textFieldFocusLost(JTextField text_field, String string) {
		if (text_field.getText().equals("")) {
			text_field.setText(string);
			text_field.setHorizontalAlignment(JTextField.CENTER);
		}
	}
	
	protected void createTextField(JTextField text_field, String text, Dimension dimension) {
		text_field.setHorizontalAlignment(JTextField.CENTER);
		text_field.setText(text);
		text_field.setPreferredSize(dimension);
	}
	
	protected void setupButton(JButton button, ImageIcon image, Dimension dimension) {
		button.setIcon(image);
		button.setSize(dimension);
		button.setBorder(null);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
	}
	
	protected void createStandardPanel(JPanel panel, Color color, Dimension dimension) {
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

	public Dimension getScreen_dim() {
		return screen_dim;
	}

	public void setScreen_dim(Dimension screen_dim) {
		this.screen_dim = screen_dim;
	}

	public ImageIcon getDelete_inactiveIMG() {
		return delete_inactiveIMG;
	}

	public void setDelete_inactiveIMG(ImageIcon delete_inactiveIMG) {
		this.delete_inactiveIMG = delete_inactiveIMG;
	}

	public ImageIcon getDelete_activeIMG() {
		return delete_activeIMG;
	}

	public void setDelete_activeIMG(ImageIcon delete_activeIMG) {
		this.delete_activeIMG = delete_activeIMG;
	}

	public ImageIcon getInsert_inactiveIMG() {
		return insert_inactiveIMG;
	}

	public void setInsert_inactiveIMG(ImageIcon insert_inactiveIMG) {
		this.insert_inactiveIMG = insert_inactiveIMG;
	}

	public ImageIcon getInsert_activeIMG() {
		return insert_activeIMG;
	}

	public void setInsert_activeIMG(ImageIcon insert_activeIMG) {
		this.insert_activeIMG = insert_activeIMG;
	}

	public ImageIcon getGo_back_inactiveIMG() {
		return go_back_inactiveIMG;
	}

	public void setGo_back_inactiveIMG(ImageIcon go_back_inactiveIMG) {
		this.go_back_inactiveIMG = go_back_inactiveIMG;
	}

	public ImageIcon getGo_back_activeIMG() {
		return go_back_activeIMG;
	}

	public void setGo_back_activeIMG(ImageIcon go_back_activeIMG) {
		this.go_back_activeIMG = go_back_activeIMG;
	}

	public Dimension getLong_dim_of_textfield() {
		return long_dim_of_textfield;
	}

	public void setLong_dim_of_textfield(Dimension long_dim_of_textfield) {
		this.long_dim_of_textfield = long_dim_of_textfield;
	}

	public Dimension getShort_dim_of_textfield() {
		return short_dim_of_textfield;
	}

	public void setShort_dim_of_textfield(Dimension short_dim_of_textfield) {
		this.short_dim_of_textfield = short_dim_of_textfield;
	}

	public Dimension getButton_size() {
		return button_size;
	}

	public void setButton_size(Dimension button_size) {
		this.button_size = button_size;
	}

	public Dimension getWest_east_size() {
		return west_east_size;
	}

	public void setWest_east_size(Dimension west_east_size) {
		this.west_east_size = west_east_size;
	}

	public Dimension getNorth_south_size() {
		return north_south_size;
	}

	public void setNorth_south_size(Dimension north_south_size) {
		this.north_south_size = north_south_size;
	}

	public JPanel getWest_panel() {
		return west_panel;
	}

	public void setWest_panel(JPanel west_panel) {
		this.west_panel = west_panel;
	}

	public JPanel getEast_panel() {
		return east_panel;
	}

	public void setEast_panel(JPanel east_panel) {
		this.east_panel = east_panel;
	}

	public JPanel getNorth_panel() {
		return north_panel;
	}

	public void setNorth_panel(JPanel north_panel) {
		this.north_panel = north_panel;
	}

	public JPanel getSouth_panel() {
		return south_panel;
	}

	public void setSouth_panel(JPanel south_panel) {
		this.south_panel = south_panel;
	}

	public JPanel getCenter_panel() {
		return center_panel;
	}

	public void setCenter_panel(JPanel center_panel) {
		this.center_panel = center_panel;
	}

	public JPanel getSql_panel() {
		return sql_panel;
	}

	public void setSql_panel(JPanel sql_panel) {
		this.sql_panel = sql_panel;
	}

	public JPanel getButtons_panel() {
		return buttons_panel;
	}

	public void setButtons_panel(JPanel buttons_panel) {
		this.buttons_panel = buttons_panel;
	}

	public JScrollPane getScroll_pane() {
		return scroll_pane;
	}

	public void setScroll_pane(JScrollPane scroll_pane) {
		this.scroll_pane = scroll_pane;
	}

	public JButton getInsert_sqlJB() {
		return insert_sqlJB;
	}

	public void setInsert_sqlJB(JButton insert_sqlJB) {
		this.insert_sqlJB = insert_sqlJB;
	}

	public JButton getDelete_sqlJB() {
		return delete_sqlJB;
	}

	public void setDelete_sqlJB(JButton delete_sqlJB) {
		this.delete_sqlJB = delete_sqlJB;
	}

	public JButton getGo_backJB() {
		return go_backJB;
	}

	public void setGo_backJB(JButton go_backJB) {
		this.go_backJB = go_backJB;
	}

	public JLabel getTable_titleLB() {
		return table_titleLB;
	}

	public void setTable_titleLB(JLabel meals_table_titleLB) {
		this.table_titleLB = meals_table_titleLB;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public Color getBackground_color() {
		return background_color;
	}

	public void setBackground_color(Color background_color) {
		this.background_color = background_color;
	}

	public ImageIcon getUpdate_inactiveIMG() {
		return update_inactiveIMG;
	}

	public void setUpdate_inactiveIMG(ImageIcon update_inactiveIMG) {
		this.update_inactiveIMG = update_inactiveIMG;
	}

	public ImageIcon getUpdate_activeIMG() {
		return update_activeIMG;
	}

	public void setUpdate_activeIMG(ImageIcon update_activeIMG) {
		this.update_activeIMG = update_activeIMG;
	}

	public JPanel getAttributes_panel() {
		return attributes_panel;
	}

	public void setAttributes_panel(JPanel attributes_panel) {
		this.attributes_panel = attributes_panel;
	}

	public JButton getUpdate_sqlJB() {
		return update_sqlJB;
	}

	public void setUpdate_sqlJB(JButton update_sqlJB) {
		this.update_sqlJB = update_sqlJB;
	}

	public ImageIcon getTable_title() {
		return table_title;
	}

	public void setTable_title(ImageIcon table_title) {
		this.table_title = table_title;
	}
	
}
