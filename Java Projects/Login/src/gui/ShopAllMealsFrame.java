package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.table.DefaultTableModel;

import controllers.ShopController;

public class ShopAllMealsFrame extends JFrame {

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();

	private ImageIcon go_back_inactiveIMG;
	private ImageIcon go_back_activeIMG;
	private ImageIcon meals_table_title;
	
	private Dimension button_size;
	private Dimension west_east_size;
	private Dimension north_south_size;
	
	private JPanel west_panel;
	private JPanel east_panel;
	private JPanel north_panel;
	private JPanel south_panel;
	private JPanel center_panel;
	private JTable table;
	private JScrollPane scroll_pane;
	
	private JButton go_backJB;
	
	private JLabel meals_table_titleLB;
	
	private Color background_color = new Color(0xf3ecd7);
	
	DefaultTableModel model;
	
	ShopController shop_controller;

	public ShopAllMealsFrame(ShopController shop_controller) {
		initialize();
		frameSetup();
		events();
		this.shop_controller = shop_controller;
	}

	private void initialize() {
		
		go_back_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\goBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\SqlButtons\\goBackActive.png");
		meals_table_title = new ImageIcon("src\\images\\tableTitles\\meals.png");
		
		button_size = new Dimension(150,30);
		west_east_size = new Dimension(100,50);
		north_south_size = new Dimension(100,75);
		
		west_panel = new JPanel();
		east_panel = new JPanel();
		north_panel = new JPanel();
		south_panel = new JPanel();
		center_panel = new JPanel();
		
		table = new JTable();
		scroll_pane = new JScrollPane(table);
		
		meals_table_titleLB = new JLabel();
		
		go_backJB = new JButton();
		
	}
	
	private void frameSetup() {
		
		String[] columns = {"Name", "Category", "Price in €", "ingredients", "Allergens"};
	    model = new DefaultTableModel(columns, 0);
		table.setModel(model);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		this.setTitle("Shop Panel: All Meals");
		this.setSize(1280,720);
		this.setMinimumSize(new Dimension(640,500));
		
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
		
		table.setPreferredScrollableViewportSize(new Dimension(500,50));
		table.setFillsViewportHeight(true);
		
		center_panel.add(scroll_pane, BorderLayout.CENTER);
		
		meals_table_titleLB.setIcon(meals_table_title);
		meals_table_titleLB.setSize(225,100);
		north_panel.add(meals_table_titleLB);
		
		go_backJB.setIcon(go_back_inactiveIMG);
		go_backJB.setSize(button_size);
		go_backJB.setBorder(null);
		go_backJB.setFocusable(false);
		go_backJB.setContentAreaFilled(false);
		south_panel.add(go_backJB);
		
	}
	
	private void events() {
		
		go_backJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			
				ShopAllMealsFrame.this.dispose();
				
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
		
	}
	
	private void createStandardPanel(JPanel panel, Color color, Dimension dimension) {

		panel.setBackground(color);
		panel.setPreferredSize(dimension);
		
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

}
