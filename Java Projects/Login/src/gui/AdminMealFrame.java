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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.AdminController;
import gui_support.RoundJTextField;

public class AdminMealFrame extends JFrame{

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private ImageIcon delete_inactiveIMG;
	private ImageIcon delete_activeIMG;
	private ImageIcon insert_inactiveIMG;
	private ImageIcon insert_activeIMG;
	private ImageIcon go_back_inactiveIMG;
	private ImageIcon go_back_activeIMG;
	private ImageIcon meals_table_title;
	
	private Dimension long_dim_of_textfield;
	private Dimension short_dim_of_textfield;
	
	private String[] dish_array_strings = {"Primo piatto", "Carne", "Pesce", "Pizza","Panino", "Fritto", "Dolce", "Bevande analcoliche", "Bevande alcoliche" };
	
	private JComboBox<Object> dishJCB;
	
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
	private JPanel dish_panel;
	private JPanel allergens_panel;
	private JPanel allergens_panel2;
	private JScrollPane scroll_pane;
	
	private JButton insert_sqlJB;
	private JButton delete_sqlJB;
	private JButton go_backJB;
	
	private JLabel meals_table_titleLB;
	private JLabel label;
	private JLabel allergensLB;
	
	private String[] allergens_array_strings = {"Cereali e derivati", "Crostacei", "Uova", "Pesce", "Arachidi", "Soia", "Latte e derivati", 
										"Frutta a guscio", "Sedano", "Senape", "Sesamo", "An. solforosa e solfiti", "Lupini", "Molluschi"};
	
	private JCheckBox[] allergens = new JCheckBox[14];
	
	private JTextField nameTF;
	private JTextField priceTF;
	private JTextField ingredientsTF;
	
	DefaultTableModel model;
	AdminController admin_controller;
	private Color background_color = new Color(0xf3ecd7);

	/**
	 * Create the application.
	 */
	public AdminMealFrame(AdminController admin_controller) {
		
		initialize();
		frameSetup();
		events();
		this.admin_controller=admin_controller;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		delete_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonInactive.png");
		delete_activeIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonActive.png");
		insert_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\insertButtonInactive.png");
		insert_activeIMG = new ImageIcon("src\\images\\SqlButtons\\insertButtonActive.png");
		go_back_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\goBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\SqlButtons\\goBackActive.png");
		meals_table_title = new ImageIcon("src\\images\\tableTitles\\meals.png");
		
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
		dish_panel = new JPanel();
		allergens_panel = new JPanel();
		allergens_panel2 = new JPanel();
		
		table = new JTable();
		scroll_pane = new JScrollPane(table);
		
		meals_table_titleLB = new JLabel();
		label = new JLabel("Dish");
		allergensLB = new JLabel("Allergens");
		
		go_backJB = new JButton();
		insert_sqlJB = new JButton();
		delete_sqlJB = new JButton();
		
		dishJCB = new JComboBox<Object>(dish_array_strings);
		
		nameTF = new RoundJTextField(new Color(0x771007));
		priceTF = new RoundJTextField(new Color(0x771007));
		ingredientsTF = new RoundJTextField(new Color(0x771007));

	}
	
	private void frameSetup() {
		
		this.setTitle("Admin Panel: Meals");
		this.setSize(1280,720);
		this.setMinimumSize(new Dimension(800,680));

		String[] columns = {"Name", "Category", "Price in €", "ingredients", "Allergens"};
		model = new DefaultTableModel(columns, 0);
		table.setModel(model);
		table.setAutoCreateRowSorter(true);
		
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
		
		//Subpanels di sql_panel
		
		attributes_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 35,10));
		createStandardPanel(attributes_panel, null, (new Dimension(100,500)));
		sql_panel.add(attributes_panel, BorderLayout.CENTER);
		
		buttons_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 35,10));
		createStandardPanel(buttons_panel, null, (new Dimension(100,100)));
		sql_panel.add(buttons_panel, BorderLayout.SOUTH);
		
		createStandardPanel(dish_panel, null, new Dimension(335,75));
		attributes_panel.add(dish_panel);
	
		//Setup TextFields
		
		meals_table_titleLB.setIcon(meals_table_title);
		meals_table_titleLB.setSize(225,100);
		north_panel.add(meals_table_titleLB);
		
		label.setPreferredSize(long_dim_of_textfield);
		dish_panel.add(label);
		
		dishJCB.setPreferredSize(long_dim_of_textfield);
		dish_panel.add(dishJCB);
		
		createTextField(nameTF, "Name", short_dim_of_textfield);
		attributes_panel.add(nameTF);
		
		createTextField(priceTF, "Price", short_dim_of_textfield);
		attributes_panel.add(priceTF);
		
		createTextField(ingredientsTF, "Ingredients", long_dim_of_textfield);
		attributes_panel.add(ingredientsTF);
		
		//Setup checkbox layout
		
		allergensLB.setHorizontalAlignment(JLabel.CENTER);
		allergensLB.setPreferredSize(long_dim_of_textfield);
		attributes_panel.add(allergensLB);
		
		allergens_panel.setLayout(new BoxLayout(allergens_panel, BoxLayout.Y_AXIS));
		createStandardPanel(allergens_panel, null, new Dimension(150,200));
		attributes_panel.add(allergens_panel);
		
		allergens_panel2.setLayout(new BoxLayout(allergens_panel2, BoxLayout.Y_AXIS));
		createStandardPanel(allergens_panel2, null, new Dimension(150,200));
		attributes_panel.add(allergens_panel2);
		
		allergensLoader(); 	//Setup checkboxes
		
		//Setup Buttons
		
		insert_sqlJB.setIcon(insert_inactiveIMG);
		setupButton(insert_sqlJB, insert_inactiveIMG, button_size);
		buttons_panel.add(insert_sqlJB);
		
		delete_sqlJB.setIcon(delete_inactiveIMG);
		setupButton(delete_sqlJB, delete_inactiveIMG, button_size);
		buttons_panel.add(delete_sqlJB);
		
		setupButton(go_backJB, go_back_inactiveIMG, button_size);
		buttons_panel.add(go_backJB);
		
	}
	
	private void events() {
		
		//MouseListeners
		
		insert_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				admin_controller.addMeal(AdminMealFrame.this);
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
		
		delete_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				admin_controller.removeMeal(AdminMealFrame.this);
				
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
				
				//Admin Frame
				AdminMealFrame.this.dispose();
				admin_controller.openAdminFrame(AdminMealFrame.this);
			
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
		
		nameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(nameTF, nameTF.getText());

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(nameTF, "Name");
				
			}
		});
		
		priceTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(priceTF, priceTF.getText());

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(priceTF, "Price");

			}
		});
		
		ingredientsTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(ingredientsTF, ingredientsTF.getText());

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(ingredientsTF, "Ingredients");

			}
		});
		

		 addWindowListener(new WindowAdapter()
	     {
	         @Override
	         public void windowClosing(WindowEvent e)
	         {
	             admin_controller.closeWindow(AdminMealFrame.this);
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

	public JTextField getPriceTF() {
		return priceTF;
	}

	public void setPriceTF(JTextField priceTF) {
		this.priceTF = priceTF;
	}

	public JTextField getIngredientsTF() {
		return ingredientsTF;
	}

	public void setIngredientsTF(JTextField ingredientsTF) {
		this.ingredientsTF = ingredientsTF;
	}

	public JComboBox<Object> getDishJCB() {
		return dishJCB;
	}

	public void setDishJCB(JComboBox<Object> dishJCB) {
		this.dishJCB = dishJCB;
	}

	public JCheckBox[] getAllergens() {
		return allergens;
	}

	public void setAllergens(JCheckBox[] allergens) {
		this.allergens = allergens;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	

	
}
