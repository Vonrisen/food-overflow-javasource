package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
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
import javax.swing.table.DefaultTableModel;

import controllers.AdminController;

public class StandardFrame extends JFrame {
	
	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	private Dimension button_size;
	private Dimension west_east_size;
	private Dimension north_south_size;
	
	private ImageIcon go_back_inactiveIMG;
	private ImageIcon go_back_activeIMG;
	private ImageIcon table_title;
	
	private JLabel table_titleLB;
	
	private JPanel west_panel;
	private JPanel east_panel;
	private JPanel north_panel;
	private JPanel south_panel;
	private JPanel center_panel;
	
	private JTable table;
	private JScrollPane scroll_pane;
	
	private JButton go_backJB;
	
	private Color background_color = new Color(0xf3ecd7);
	
	protected DefaultTableModel model;
	
	
	//Costruttore
	public StandardFrame() {
		initialize();
		frameSetup();
		events();
	}

	
	private void initialize() {
		
		go_back_inactiveIMG = new ImageIcon("src\\images\\buttons\\goBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\buttons\\goBackActive.png");
		
		button_size = new Dimension(150,30);
		west_east_size = new Dimension(100,50);
		north_south_size = new Dimension(100,75);
		
		west_panel = new JPanel();
		east_panel = new JPanel();
		north_panel = new JPanel();
		south_panel = new JPanel();
		center_panel = new JPanel();
		
		table_titleLB = new JLabel();
		
		table = (new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				super.changeSelection(rowIndex, columnIndex, true, false);
			}
		});
		scroll_pane = new JScrollPane(table);
		
		go_backJB = new JButton();
		
	}
	
	
	private void frameSetup() {
		
		//Layout setup
		this.setSize(1280,720);
		this.setMinimumSize(new Dimension(640,500));
		setIconImage(new ImageIcon("src\\images\\startup\\icon.png").getImage());
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
		table.getTableHeader().setReorderingAllowed(false);
		table.setPreferredScrollableViewportSize(new Dimension(500,50));
		table.setFillsViewportHeight(true);
		
		
		//Sottopannelli di "center_panel"
		table_titleLB.setSize(225,100);
		center_panel.add(scroll_pane, BorderLayout.CENTER);
		north_panel.add(table_titleLB);
		
		
		//Buttons setup
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

	public JScrollPane getScroll_pane() {
		return scroll_pane;
	}

	public void setScroll_pane(JScrollPane scroll_pane) {
		this.scroll_pane = scroll_pane;
	}

	public JButton getGo_backJB() {
		return go_backJB;
	}

	public void setGo_backJB(JButton go_backJB) {
		this.go_backJB = go_backJB;
	}

	public Color getBackground_color() {
		return background_color;
	}

	public void setBackground_color(Color background_color) {
		this.background_color = background_color;
	}

	public ImageIcon getTable_title() {
		return table_title;
	}

	public void setTable_title(ImageIcon table_title) {
		this.table_title = table_title;
	}

	public JLabel getTable_titleLB() {
		return table_titleLB;
	}

	public void setTable_titleLB(JLabel table_titleLB) {
		this.table_titleLB = table_titleLB;
	}
	
}
