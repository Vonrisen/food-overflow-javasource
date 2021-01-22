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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controllers.AdminController;
import controllers.ShopController;
import gui_support.RoundJTextField;

public class ShopMealFrame extends JFrame{


	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private ImageIcon delete_inactiveIMG;
	private ImageIcon delete_activeIMG;
	private ImageIcon insert_inactiveIMG;
	private ImageIcon insert_activeIMG;
	private ImageIcon go_back_inactiveIMG;
	private ImageIcon go_back_activeIMG;
	private ImageIcon meals_table_title;
	private ImageIcon view_all_meals_inactiveIMG;
	private ImageIcon view_all_meals_activeIMG;
	
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
	private JPanel attributes_panel;
	private JPanel buttons_panel;
	private JScrollPane shop_meals_scroll_pane;
	private JScrollPane all_meals_scroll_pane;
	
	private JTable all_meals_table;
	private JTable shop_meals_table;
	
	private JButton view_all_mealsJB;
	private JButton insert_sqlJB;
	private JButton delete_sqlJB;
	private JButton go_backJB;
	
	private JLabel meals_table_titleLB;
	private JTextField mealTF;
	
	private Color background_color = new Color(0xf3ecd7);
	private ShopController shop_controller;

	/**
	 * Create the application.
	 */
	public ShopMealFrame(ShopController shop_controller) {
		initialize();
		setupFrame();
		events();
		this.shop_controller = shop_controller;
	}

	private void initialize() {
		
		
		delete_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonInactive.png");
		delete_activeIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonActive.png");
		go_back_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\goBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\SqlButtons\\goBackActive.png");
		meals_table_title = new ImageIcon("src\\images\\tableTitles\\shopMeals.png");
		insert_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\insertButtonInactive.png");
		insert_activeIMG = new ImageIcon("src\\images\\SqlButtons\\insertButtonActive.png");
		view_all_meals_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\viewAllMealsInactive.png");
		view_all_meals_activeIMG = new ImageIcon("src\\images\\SqlButtons\\viewAllMealsActive.png");
		
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
		
		meals_table_titleLB = new JLabel();
		mealTF = new RoundJTextField(new Color(0x771007));
		
		view_all_mealsJB = new JButton();
		go_backJB = new JButton();
		insert_sqlJB = new JButton();
		delete_sqlJB = new JButton();
		
		shop_meals_table = new JTable();
		shop_meals_scroll_pane = new JScrollPane(shop_meals_table);
		
		all_meals_table = new JTable();
		all_meals_scroll_pane = new JScrollPane(all_meals_table);
		
		
	}
	
	private void setupFrame() {
		
		this.setTitle("Shop Panel: Meals");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1280,720);
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
		
		shop_meals_table.setPreferredScrollableViewportSize(new Dimension(500,50));
		shop_meals_table.setFillsViewportHeight(true);
		
		all_meals_table.setPreferredScrollableViewportSize(new Dimension(700,500));
		all_meals_table.setFillsViewportHeight(true);
		
		center_panel.add(shop_meals_scroll_pane, BorderLayout.CENTER);
		
		meals_table_titleLB.setIcon(meals_table_title);
		meals_table_titleLB.setSize(300,100);
		north_panel.add(meals_table_titleLB);
		
		//Subpanels di sql_panel
		
		attributes_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 35,30));
		createStandardPanel(attributes_panel, null, (new Dimension(100,500)));
		sql_panel.add(attributes_panel, BorderLayout.CENTER);
		
		createStandardPanel(buttons_panel, null, (new Dimension(100,100)));
		sql_panel.add(buttons_panel, BorderLayout.SOUTH);
		
		//Setup TextFields
		
		createTextField(mealTF, "Inserisci meal(ID) nello shop", long_dim_of_textfield);
		attributes_panel.add(mealTF);
		
		setupButton(view_all_mealsJB, view_all_meals_inactiveIMG, new Dimension(335,30));
		attributes_panel.add(view_all_mealsJB);
		
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
		
		insert_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				//DELETE
				
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
				
				//DELETE
				
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
		
		mealTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(mealTF, "Inserisci meal(ID) nello shop");

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(mealTF, "Inserisci meal(ID) nello shop");

			}
		});
		
		view_all_mealsJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
//				JOptionPane.showConfirmDialog(null, all_meals_scroll_pane, "All Meals", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
				String[][] data = { { "bla", "bla" } };
				String[] cols = { "col1", "col2" };
//				new VHSInformationPanel(data, cols);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				view_all_mealsJB.setIcon(view_all_meals_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				view_all_mealsJB.setIcon(view_all_meals_inactiveIMG);
				
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

}
