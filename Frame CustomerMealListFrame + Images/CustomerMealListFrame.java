package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.table.DefaultTableModel;

import controllers.AdminController;
import gui_support.RoundJTextField;
import gui_support.VerticalFlowLayout;

public class CustomerMealListFrame extends JFrame {

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	private Dimension long_dim_of_textfield;
	private Dimension short_dim_of_textfield;
	private ImageIcon backgroundIMG;
	private ImageIcon profile_inactiveIMG;
	private ImageIcon profile_activeIMG;
	private ImageIcon home_inactiveIMG;
	private ImageIcon home_activeIMG;
	private ImageIcon logout_inactiveIMG;
	private ImageIcon logout_activeIMG;
	private ImageIcon cart_button_inactiveIMG;
	private ImageIcon cart_button_activeIMG;
	private ImageIcon filtriIMG;
	private ImageIcon add_to_cart_inactiveIMG;
	private ImageIcon add_to_cart_activeIMG;
	private ImageIcon back_button_inactiveIMG;
	private ImageIcon back_button_activeIMG;
	
	private Dimension west_east_size;
	private Dimension north_south_size;
	
	private String[] columns = {"CF", "Nome", "Cognome", "Data di nascita", "Luogo di nascita", "Indirizzo", "Sesso", "Cellulare", "Email", "Password"};
	private String[] allergens_array_strings = {"Cereali e derivati", "Crostacei", "Uova", "Pesce", "Arachidi", "Soia", "Latte e derivati", 
			"Frutta a guscio", "Sedano", "Senape", "Sesamo", "An. solforosa e solfiti", "Lupini", "Molluschi"};
	private String[] dish_array_strings = {"Primo piatto", "Carne", "Pesce", "Pizza","Panino", "Fritto", "Dolce", "Bevande analcoliche", "Bevande alcoliche" };
	
	private JCheckBox[] allergens = new JCheckBox[14];
	private JComboBox<Object> dishCB;
	
	private JPanel west_panel;
	private JPanel east_panel;
	private JPanel north_panel;
	private JPanel south_panel;
	private JPanel center_panel;
	private JPanel sql_panel;
	private JPanel sql_panel2;
	private JPanel title_panel;
	private JPanel filters_panel;
	private JPanel buttons_panel2;
	private JTable table;
	private JPanel attributes_panel;
	private JPanel buttons_panel;
	private JScrollPane scroll_pane;
	private JScrollPane scroll_pane2;
	private JPanel allergens_panel;
	private JPanel allergens_panel2;
	
	private JPanel filters_subpanel;
	private JPanel filters_subpanel2;
	
	private JButton profileJB;
	private JButton homeJB;
	private JButton cartJB;
	private JButton logoutJB;
	private JButton backJB;
	
	private JLabel background;
	private JLabel filtriLB;
	private JLabel allergensLB;
	private JButton add_to_cartJB;
	DefaultTableModel model;
	
	private JTextField price_minTF;
	private JTextField price_maxTF;
	private JTextField meal_nameTF;
	private JTextField quantityTF;
	
	JScrollBar vbar=new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 500);
	
	public CustomerMealListFrame() {
		
		initialize();
		frameSetup();
		events();
		
	}

	//Initialize variables
	private void initialize() {
		
		backgroundIMG = new ImageIcon("src\\images\\WALLPAPER.PNG");
		profile_inactiveIMG = new ImageIcon("src\\images\\profileButtonInactive2.PNG");
		profile_activeIMG = new ImageIcon("src\\images\\profileButtonActive2.PNG");
		cart_button_inactiveIMG = new ImageIcon("src\\images\\cartButtonInactive.PNG");
		cart_button_activeIMG = new ImageIcon("src\\images\\cartButtonActive.PNG");
		home_inactiveIMG = new ImageIcon("src\\images\\homeButtonInactive.PNG");
		home_activeIMG = new ImageIcon("src\\images\\homeButtonActive.PNG");
		logout_inactiveIMG = new ImageIcon("src\\images\\logoutButtonInactive.PNG");
		logout_activeIMG = new ImageIcon("src\\images\\logoutButtonActive.PNG");
		filtriIMG = new ImageIcon("src\\images\\filtri.PNG");
		add_to_cart_inactiveIMG = new ImageIcon("src\\images\\addToCartInactive.PNG");
		add_to_cart_activeIMG = new ImageIcon("src\\images\\addToCartActive.PNG");
		back_button_inactiveIMG = new ImageIcon("src\\images\\indietroButtonInactive.PNG");
		back_button_activeIMG = new ImageIcon("src\\images\\indietroButtonActive.PNG");
		
		
		
		long_dim_of_textfield = new Dimension(335,25);
		short_dim_of_textfield = new Dimension(150,25);
		west_east_size = new Dimension(100,80);
		north_south_size = new Dimension(100,50);
		
		west_panel = new JPanel();
		east_panel = new JPanel();
		north_panel = new JPanel();
		south_panel = new JPanel();
		center_panel = new JPanel();
		sql_panel = new JPanel();
		sql_panel2 = new JPanel();
		attributes_panel = new JPanel();
		buttons_panel = new JPanel();
		filters_subpanel = new JPanel();
		filters_subpanel2 = new JPanel();
		
		title_panel = new JPanel();
		filters_panel = new JPanel();
		buttons_panel2 = new JPanel();
		
		background = new JLabel();
		filtriLB = new JLabel();
		allergensLB = new JLabel("Seleziona le tue allergie alimentari:");
		add_to_cartJB = new JButton();
		
		table = (new JTable() {
			
			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				super.changeSelection(rowIndex, columnIndex, true, false);
			}
			
		});
		scroll_pane = new JScrollPane(table);
		
		profileJB = new JButton();
		homeJB = new JButton();
		cartJB = new JButton();
		logoutJB = new JButton();
		backJB = new JButton();
		allergens_panel = new JPanel();
		allergens_panel2 = new JPanel();
		dishCB = new JComboBox<Object>(dish_array_strings);
		
		price_minTF = new RoundJTextField(new Color(0x771007));
		price_maxTF = new RoundJTextField(new Color(0x771007));
		meal_nameTF = new RoundJTextField(new Color(0x771007));
		quantityTF = new RoundJTextField(new Color(0x771007));
		
	}
	
	private void frameSetup() {
		
		//Layout setup
		
		this.setTitle("Food Overflow - Lista degli alimenti");
		this.setSize(1280,720);
		background.setIcon(resize(backgroundIMG, this.getWidth(), this.getHeight()));
		this.setMinimumSize(new Dimension(800,650));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int central_width = screen_dim.width/2-this.getSize().width/2;
		int central_height = screen_dim.height/2-this.getSize().height/2;
		this.setLocation(central_width, central_height); //Setta il frame a centro monitor
		this.getContentPane().setLayout(new BorderLayout());
		this.setContentPane(background);
		this.getContentPane().setLayout(new BorderLayout());
		
		createStandardPanel(west_panel, null, west_east_size);
		west_panel.setOpaque(false);
		this.getContentPane().add(west_panel, BorderLayout.WEST);
		
		createStandardPanel(east_panel, null, west_east_size);
		east_panel.setOpaque(false);
		this.getContentPane().add(east_panel, BorderLayout.EAST);
		
		createStandardPanel(north_panel, null, north_south_size);
		north_panel.setOpaque(false);
		this.getContentPane().add(north_panel, BorderLayout.NORTH);
		
		createStandardPanel(south_panel, null, north_south_size);
		south_panel.setOpaque(false);
		this.getContentPane().add(south_panel, BorderLayout.SOUTH);
		
		center_panel.setLayout(new BorderLayout());
		center_panel.setBackground(new Color(0xf2eee1));
		center_panel.setBorder(BorderFactory.createLineBorder(new Color(0x771007), 8));
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
		scroll_pane.setBorder(BorderFactory.createEmptyBorder());
		
		
		//Sottopannell di "center_panel"
		
		sql_panel.setLayout(new BorderLayout());
		createStandardPanel(sql_panel, null, (new Dimension(100,80)));
		center_panel.add(sql_panel, BorderLayout.SOUTH);
		sql_panel.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, new Color(0x771007)));
		
		sql_panel2.setLayout(new BorderLayout());
		createStandardPanel(sql_panel2, null, (new Dimension(350,100)));
		center_panel.add(sql_panel2, BorderLayout.WEST);
		sql_panel2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, new Color(0x771007)));
		
		center_panel.add(scroll_pane, BorderLayout.CENTER);
		
		//Sottopannelli di "sql_panel"
		
		attributes_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 35,15));
		createStandardPanel(attributes_panel, null, (new Dimension(100,500)));
		sql_panel.add(attributes_panel, BorderLayout.CENTER);
		
		createStandardPanel(buttons_panel, null, (new Dimension(255,100)));
		buttons_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 15,15));
		sql_panel.add(buttons_panel, BorderLayout.EAST);
		
		//Sottopannelli di "sql_panel2"
		
		createStandardPanel(title_panel, null, new Dimension(100,70));
		sql_panel2.add(title_panel, BorderLayout.NORTH);
//		title_panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x771007)));
		
		filters_panel.setBackground(null);
		filters_panel.setPreferredSize(new Dimension(100,500));
		filters_panel.setLayout(new FlowLayout());
		sql_panel2.add(filters_panel, BorderLayout.CENTER);
		
		createStandardPanel(buttons_panel2, null, new Dimension(100,100));
		buttons_panel2.setLayout(new BorderLayout(0,3));
		sql_panel2.add(buttons_panel2, BorderLayout.SOUTH);
		
		//Sottopannelli di filters_panel
		
		createStandardPanel(filters_subpanel,null, new Dimension(350,115));
		filters_subpanel.setLayout(new FlowLayout(FlowLayout.LEADING, 17,15));
		filters_panel.add(filters_subpanel);
		
		allergens_panel.setLayout(new BoxLayout(allergens_panel, BoxLayout.Y_AXIS));
		createStandardPanel(allergens_panel, null, new Dimension(150,200));
		filters_panel.add(allergens_panel);
		
		allergens_panel2.setLayout(new BoxLayout(allergens_panel2, BoxLayout.Y_AXIS));
		createStandardPanel(allergens_panel2, null, new Dimension(150,200));
		filters_panel.add(allergens_panel2);
		
		
		//
		
		filtriLB.setIcon(filtriIMG);
		filtriLB.setFocusable(true);
		filtriLB.setPreferredSize(new Dimension(250,100));
		title_panel.add(filtriLB);
		
		//
		

		
		dishCB.setPreferredSize(short_dim_of_textfield);
		dishCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		dishCB.setFocusable(false);
		dishCB.setBackground(Color.white);
		filters_subpanel.add(dishCB);

		
		createTextField(meal_nameTF, "Nome dell'alimento", short_dim_of_textfield);
		filters_subpanel.add(meal_nameTF);
		
		createTextField(price_minTF, "Prezzo minimo", short_dim_of_textfield);
		filters_subpanel.add(price_minTF);
		
		createTextField(price_maxTF, "Prezzo massimo", short_dim_of_textfield);
		filters_subpanel.add(price_maxTF);
		
		filters_subpanel.add(allergensLB);
		
		allergensLoader();
		
		//
		
		setupButton(homeJB,home_inactiveIMG,new Dimension(50,100));
		attributes_panel.add(homeJB);
		
		setupButton(profileJB,profile_inactiveIMG,new Dimension(50,100));
		attributes_panel.add(profileJB);
		
		setupButton(cartJB,cart_button_inactiveIMG, new Dimension(50,100));
		attributes_panel.add(cartJB);
		
		setupButton(backJB, back_button_inactiveIMG, new Dimension(100,50));
		buttons_panel.add(backJB);
		
		setupButton(logoutJB,logout_inactiveIMG,new Dimension(50,100));
		buttons_panel.add(logoutJB);
		
		setupButton(add_to_cartJB, add_to_cart_inactiveIMG, new Dimension(200,50));
		buttons_panel2.add(add_to_cartJB);
		
		JPanel quantity_panel;
		quantity_panel = new JPanel();
		createStandardPanel(quantity_panel, null, new Dimension(100,30));
		buttons_panel2.add(quantity_panel, BorderLayout.NORTH);

		
		JLabel quantityLB;
		quantityLB = new JLabel("Quantità: ");
		quantityLB.setSize(new Dimension(100,25));
		quantity_panel.add(quantityLB);
		
		createTextField(quantityTF, "1", new Dimension(100,25));
		quantity_panel.add(quantityTF);
		
			
	}
	
	private void events() {
		
		this.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
				background.setIcon(resize(backgroundIMG, CustomerMealListFrame.this.getWidth(),CustomerMealListFrame.this.getHeight()));
		    }
		});
		
		profileJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				profileJB.setIcon(profile_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				profileJB.setIcon(profile_inactiveIMG);
				
			}
		});
		
		homeJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				homeJB.setIcon(home_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				homeJB.setIcon(home_inactiveIMG);
				
			}
		});
		
		logoutJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				logoutJB.setIcon(logout_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				logoutJB.setIcon(logout_inactiveIMG);
				
			}
		});
		
		cartJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				cartJB.setIcon(cart_button_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				cartJB.setIcon(cart_button_inactiveIMG);
				
			}
		});
		
		add_to_cartJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				add_to_cartJB.setIcon(add_to_cart_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				add_to_cartJB.setIcon(add_to_cart_inactiveIMG);
				
			}
		});
		
		backJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				backJB.setIcon(back_button_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				backJB.setIcon(back_button_inactiveIMG);
				
			}
		});
		
		meal_nameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(meal_nameTF, "Nome dell'alimento");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(meal_nameTF, "Nome dell'alimento");
				
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
		
		quantityTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(quantityTF, "1");
				
			}
		});
		
	}
	
	private ImageIcon resize(ImageIcon im, int w, int h) {
		
		BufferedImage bi = new BufferedImage(w,h, BufferedImage.TRANSLUCENT);
		Graphics2D gd=(Graphics2D)bi.createGraphics();
		gd.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		gd.drawImage(im.getImage(), 0, 0, w,h,null);
		gd.dispose();
		
		return new ImageIcon(bi);
	}
	
	
	
	private void allergensLoader() {
		
		int i=0;
		for(String a: allergens_array_strings) {
			
			allergens[i] = new JCheckBox(a);
			allergens[i].setFocusable(false);
			allergens[i].setContentAreaFilled(false);
			
			if(i<=(allergens_array_strings.length/2)-1)  
				allergens_panel.add(allergens[i]);
			
			else	
				allergens_panel2.add(allergens[i]);

			i++;
			
		}
		
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
	
	

}
