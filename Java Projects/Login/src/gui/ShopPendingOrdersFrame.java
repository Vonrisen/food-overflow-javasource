package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import controllers.ShopController;

@SuppressWarnings("serial")
public class ShopPendingOrdersFrame extends JFrame {

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	private Dimension button_size;
	private Dimension west_east_size;
	private Dimension north_south_size;

	private ImageIcon go_back_inactiveIMG;
	private ImageIcon go_back_activeIMG;
	private ImageIcon table_titleIMG;
	private ImageIcon add_rider_to_order_inactiveIMG;
	private ImageIcon add_rider_to_order_activeIMG;
	private ImageIcon select_orderIMG;

	private JLabel table_titleLB;
	private JLabel select_orderLB;

	private JPanel west_panel;
	private JPanel east_panel;
	private JPanel north_panel;
	private JPanel south_panel;
	private JPanel center_panel;
	private JPanel all_meal_panel;
	private JPanel buttons_panel;

	private JTable pending_orders_table;
	private JScrollPane scroll_pane;

	private JTable riders_table;
	private JScrollPane scroll_pane2;

	private JButton go_backJB;
	private JButton add_rider_to_orderJB;

	private Color background_color = new Color(0xf3ecd7);

	private DefaultTableModel pending_orders_model;
	private DefaultTableModel riders_model;
	private ShopController shop_controller;

	private String[] columns = { "ID", "Data", "Indirizzo", "Pagamento", "Note", "Cliente" };
	private String[] columns2 = { "CF", "Cellulare", "Veicolo", "Orario di lavoro", "N. consegne" };

	// Costruttore
	public ShopPendingOrdersFrame(ShopController shop_controller) {
		initialize();
		frameSetup();
		events();
		this.shop_controller = shop_controller;
	}

	private void initialize() {

		go_back_inactiveIMG = new ImageIcon("src\\images\\buttons\\goBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\buttons\\goBackActive.png");
		table_titleIMG = new ImageIcon("src\\images\\others\\pendingOrders.png");
		add_rider_to_order_inactiveIMG = new ImageIcon("src\\images\\buttons\\aggiungiRiderInactive.png");
		add_rider_to_order_activeIMG = new ImageIcon("src\\images\\buttons\\aggiungiRiderActive.png");
		select_orderIMG = new ImageIcon("src\\images\\others\\selezionaRider.png");

		button_size = new Dimension(150, 30);
		west_east_size = new Dimension(100, 50);
		north_south_size = new Dimension(100, 75);

		west_panel = new JPanel();
		east_panel = new JPanel();
		north_panel = new JPanel();
		south_panel = new JPanel();
		center_panel = new JPanel();
		all_meal_panel = new JPanel();
		buttons_panel = new JPanel();

		table_titleLB = new JLabel();
		select_orderLB = new JLabel();

		pending_orders_table = (new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				super.changeSelection(rowIndex, columnIndex, true, false);
			}
		});

		riders_table = (new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				super.changeSelection(rowIndex, columnIndex, true, false);
			}
		});

		scroll_pane = new JScrollPane(pending_orders_table);
		scroll_pane2 = new JScrollPane(riders_table);

		go_backJB = new JButton();
		add_rider_to_orderJB = new JButton();

	}

	private void frameSetup() {

		// Layout setup
		this.setSize(1280, 720);
		this.setTitle("Food Overflow - Shop Panel: Ordini in attesa");
		setIconImage(new ImageIcon("src\\images\\startup\\icon.png").getImage());
		this.setMinimumSize(new Dimension(640, 500));
		int central_width = screen_dim.width / 2 - this.getSize().width / 2;
		int central_height = screen_dim.height / 2 - this.getSize().height / 2;
		this.setLocation(central_width, central_height); // Setta il frame a centro monitor
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

		center_panel.setLayout(new BorderLayout(30, 0));
		center_panel.setBackground(null);
		this.getContentPane().add(center_panel, BorderLayout.CENTER);

		//

		createStandardPanel(all_meal_panel, null, new Dimension(500, 100));
		all_meal_panel.setLayout(new BorderLayout());
		center_panel.add(all_meal_panel, BorderLayout.EAST);

		createStandardPanel(buttons_panel, null, new Dimension(100, 100));
		all_meal_panel.add(buttons_panel, BorderLayout.CENTER);

		// Impostazione JTable
		pending_orders_table.setAutoCreateRowSorter(false);
		pending_orders_table.setRowSelectionAllowed(true);
		pending_orders_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		pending_orders_table.getTableHeader().setReorderingAllowed(false);
		pending_orders_table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		pending_orders_table.setFillsViewportHeight(true);
		pending_orders_table.setModel(pending_orders_model = new DefaultTableModel(columns, 0));

		riders_table.setAutoCreateRowSorter(false);
		riders_table.setRowSelectionAllowed(true);
		riders_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		riders_table.getTableHeader().setReorderingAllowed(false);
		riders_table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		riders_table.setFillsViewportHeight(true);
		riders_table.setModel(riders_model = new DefaultTableModel(columns2, 0));

		scroll_pane.setBorder(BorderFactory.createLineBorder(new Color(0x771007), 5));
		scroll_pane2.setBorder(BorderFactory.createLineBorder(new Color(0x771007), 5));

		// Sottopannelli di "center_panel"
		table_titleLB.setSize(500, 50);
		table_titleLB.setIcon(table_titleIMG);
		scroll_pane2.setPreferredSize(new Dimension(100, 400));
		center_panel.add(scroll_pane, BorderLayout.CENTER);
		north_panel.add(table_titleLB);
		all_meal_panel.add(scroll_pane2, BorderLayout.NORTH);

		// Buttons & Label setup

		select_orderLB.setIcon(select_orderIMG);
		select_orderLB.setPreferredSize(new Dimension(450, 25));
		buttons_panel.add(select_orderLB);

		buttons_panel.add(Box.createVerticalStrut(50));

		add_rider_to_orderJB.setIcon(add_rider_to_order_inactiveIMG);
		add_rider_to_orderJB.setSize(button_size);
		add_rider_to_orderJB.setBorder(null);
		add_rider_to_orderJB.setFocusable(false);
		add_rider_to_orderJB.setContentAreaFilled(false);
		buttons_panel.add(add_rider_to_orderJB);

		buttons_panel.add(Box.createHorizontalStrut(400));

		go_backJB.setIcon(go_back_inactiveIMG);
		go_backJB.setSize(button_size);
		go_backJB.setBorder(null);
		go_backJB.setFocusable(false);
		go_backJB.setContentAreaFilled(false);
		buttons_panel.add(go_backJB);

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

			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.openShopOrderManagementFrame(ShopPendingOrdersFrame.this);
			}
		});

		add_rider_to_orderJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				add_rider_to_orderJB.setIcon(add_rider_to_order_activeIMG);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				add_rider_to_orderJB.setIcon(add_rider_to_order_inactiveIMG);
			}

			public void mousePressed(MouseEvent e) {
				shop_controller.linkRiderToOrder(ShopPendingOrdersFrame.this);
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				shop_controller.releaseAllDaoResourcesAndDisposeFrame(ShopPendingOrdersFrame.this);
			}
		});

	}

	private void createStandardPanel(JPanel panel, Color color, Dimension dimension) {
		panel.setBackground(color);
		panel.setPreferredSize(dimension);
	}

	public JTable getRiders_table() {
		return riders_table;
	}

	public DefaultTableModel getRiders_model() {
		return riders_model;
	}

	public JTable getPending_orders_table() {
		return pending_orders_table;
	}

	public DefaultTableModel getPending_orders_model() {
		return pending_orders_model;
	}

}
