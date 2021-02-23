package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controllers.CustomerController;
import controllers.LoginController;
import gui_support.RoundJTextField;

@SuppressWarnings("serial")
public class CustomerCartFrame extends JFrame {

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	private ImageIcon backgroundIMG;
	private ImageIcon profile_inactiveIMG;
	private ImageIcon profile_activeIMG;
	private ImageIcon home_inactiveIMG;
	private ImageIcon home_activeIMG;
	private ImageIcon logout_inactiveIMG;
	private ImageIcon logout_activeIMG;
	private ImageIcon delete_activeIMG;
	private ImageIcon delete_inactiveIMG;
	private ImageIcon update_inactiveIMG;
	private ImageIcon update_activeIMG;
	private ImageIcon deleteall_inactiveIMG;
	private ImageIcon deleteall_activeIMG;
	private ImageIcon order_inactiveIMG;
	private ImageIcon order_activeIMG;
	private ImageIcon back_button_inactiveIMG;
	private ImageIcon back_button_activeIMG;
	private ImageIcon cart_logoIMG;

	private Dimension west_east_size;
	private Dimension north_south_size;

	private String[] columns = { "Nome", "Categoria", "Prezzo", "Ingredienti", "Allergeni", "Quantita'" };

	private JPanel west_panel;
	private JPanel east_panel;
	private JPanel north_panel;
	private JPanel south_panel;
	private JPanel center_panel;
	private JPanel sql_panel;
	private JPanel sql_subpanel;
	private JPanel sql_subpanel2;
	private JTable table;
	private JPanel attributes_panel;
	private JPanel buttons_panel;
	private JScrollPane scroll_pane;

	private JPanel cart_panel;
	private JPanel cart_west_panel;
	private JPanel cart_east_panel;
	private JPanel cart_north_panel;
	private JPanel cart_south_panel;
	private JPanel left_sql_subpanel;
	private JPanel right_sql_subpanel;

	private JButton profileJB;
	private JButton homeJB;
	private JButton logoutJB;
	private JButton updateJB;
	private JButton deleteJB;
	private JButton deleteallJB;
	private JButton orderJB;
	private JButton backJB;

	private JTextField quantityTF;
	private JLabel quantityLB;
	private JLabel cart_logoLB;
	private JLabel background;
	
	private DefaultTableModel model;
	
	private CustomerController customer_controller;
	private LoginController login_controller;

	public CustomerCartFrame(CustomerController customer_controller, LoginController login_controller) {

		initialize();
		frameSetup();
		events();
		this.customer_controller = customer_controller;
		this.login_controller = login_controller;
	}

	// Initialize variables
	private void initialize() {

		backgroundIMG = new ImageIcon("src\\images\\customer\\WALLPAPER.PNG");
		profile_inactiveIMG = new ImageIcon("src\\images\\customer\\profileButtonInactive2.PNG");
		profile_activeIMG = new ImageIcon("src\\images\\customer\\profileButtonActive2.PNG");
		home_inactiveIMG = new ImageIcon("src\\images\\customer\\homeButtonInactive.PNG");
		home_activeIMG = new ImageIcon("src\\images\\customer\\homeButtonActive.PNG");
		logout_inactiveIMG = new ImageIcon("src\\images\\customer\\logoutButtonInactive.PNG");
		logout_activeIMG = new ImageIcon("src\\images\\customer\\logoutButtonActive.PNG");
		delete_activeIMG = new ImageIcon("src\\images\\customer\\cancellaButtonActive.PNG");
		delete_inactiveIMG = new ImageIcon("src\\images\\customer\\cancellaButtonInactive.PNG");
		update_activeIMG = new ImageIcon("src\\images\\customer\\aggiornaButtonActive.PNG");
		update_inactiveIMG = new ImageIcon("src\\images\\customer\\aggiornaButtonInactive.PNG");
		deleteall_activeIMG = new ImageIcon("src\\images\\customer\\cancellatuttoButtonActive.PNG");
		deleteall_inactiveIMG = new ImageIcon("src\\images\\customer\\cancellatuttoButtonInactive.PNG");
		order_inactiveIMG = new ImageIcon("src\\images\\customer\\procediButtonInactive.PNG");
		order_activeIMG = new ImageIcon("src\\images\\customer\\procediButtonActive.PNG");
		back_button_inactiveIMG = new ImageIcon("src\\images\\customer\\indietroButtonInactive.PNG");
		back_button_activeIMG = new ImageIcon("src\\images\\customer\\indietroButtonActive.PNG");
		cart_logoIMG = new ImageIcon("src\\images\\customer\\cartLogo.PNG");

		west_east_size = new Dimension(100, 80);
		north_south_size = new Dimension(100, 50);

		west_panel = new JPanel();
		east_panel = new JPanel();
		north_panel = new JPanel();
		south_panel = new JPanel();
		center_panel = new JPanel();
		sql_panel = new JPanel();
		sql_subpanel = new JPanel();
		sql_subpanel2 = new JPanel();
		attributes_panel = new JPanel();
		buttons_panel = new JPanel();

		cart_panel = new JPanel();
		cart_west_panel = new JPanel();
		cart_east_panel = new JPanel();
		cart_north_panel = new JPanel();
		cart_south_panel = new JPanel();
		left_sql_subpanel = new JPanel();
		right_sql_subpanel = new JPanel();
		quantityLB = new JLabel("Quantita': ");

		quantityTF = new RoundJTextField(new Color(0x771007));

		background = new JLabel();
		cart_logoLB = new JLabel();

		table = (new JTable() {

			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				super.changeSelection(rowIndex, columnIndex, true, false);
			}

		});
		scroll_pane = new JScrollPane(table);

		profileJB = new JButton();
		homeJB = new JButton();
		logoutJB = new JButton();
		updateJB = new JButton();
		deleteJB = new JButton();
		deleteallJB = new JButton();
		orderJB = new JButton();
		backJB = new JButton();

	}

	private void frameSetup() {

		// Layout setup

		this.setTitle("Food Overflow - Il tuo carrello");
		this.setSize(1280, 720);
		background.setIcon(resize(backgroundIMG, this.getWidth(), this.getHeight()));
		setIconImage(new ImageIcon("src\\images\\startup\\icon.png").getImage());
		this.setMinimumSize(new Dimension(800, 650));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int central_width = screen_dim.width / 2 - this.getSize().width / 2;
		int central_height = screen_dim.height / 2 - this.getSize().height / 2;
		this.setLocation(central_width, central_height); // Setta il frame a centro monitor
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

		// Impostazione JTable

		table.setAutoCreateRowSorter(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setModel(model = new DefaultTableModel(columns, 0) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		table.setFillsViewportHeight(true);
//		scroll_pane.setBorder(BorderFactory.createEmptyBorder());
		scroll_pane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0x771007)));

		// Sottopannell di "center_panel"

		createStandardPanel(cart_panel, null, new Dimension(10, 10));
		cart_panel.setLayout(new BorderLayout());
		center_panel.add(cart_panel, BorderLayout.CENTER);
		cart_panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0x771007)));

		createStandardPanel(cart_west_panel, null, west_east_size);
		cart_panel.add(cart_west_panel, BorderLayout.WEST);

		createStandardPanel(cart_east_panel, null, west_east_size);
		cart_east_panel.setOpaque(false);
		cart_panel.add(cart_east_panel, BorderLayout.EAST);

		createStandardPanel(cart_north_panel, null, new Dimension(100, 100));
		cart_north_panel.setOpaque(false);
		cart_panel.add(cart_north_panel, BorderLayout.NORTH);

		createStandardPanel(cart_south_panel, null, north_south_size);
		cart_south_panel.setOpaque(false);
		cart_panel.add(cart_south_panel, BorderLayout.SOUTH);

		sql_panel.setLayout(new BorderLayout());
		createStandardPanel(sql_panel, null, (new Dimension(100, 140)));
		center_panel.add(sql_panel, BorderLayout.SOUTH);

		cart_panel.add(scroll_pane, BorderLayout.CENTER);

		cart_logoLB.setPreferredSize(new Dimension(500, 100));
		cart_logoLB.setIcon(cart_logoIMG);

		cart_north_panel.add(cart_logoLB);

		// Sottopannelli di "sql_panel"

		createStandardPanel(sql_subpanel, null, new Dimension(100, 60));
		sql_subpanel.setLayout(new BorderLayout());
		sql_panel.add(sql_subpanel, BorderLayout.CENTER);
		createStandardPanel(sql_subpanel2, new Color(0xf49969), new Dimension(100, 80));
		sql_subpanel2.setLayout(new BorderLayout());
		sql_subpanel2.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, new Color(0x771007)));
		sql_panel.add(sql_subpanel2, BorderLayout.SOUTH);

		// Sottopannelli di "sql_subpanel"

		createStandardPanel(left_sql_subpanel, null, new Dimension(550, 60));
		left_sql_subpanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 15));
		sql_subpanel.add(left_sql_subpanel, BorderLayout.CENTER);

		createStandardPanel(right_sql_subpanel, null, new Dimension(330, 60));
		right_sql_subpanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 15));
		sql_subpanel.add(right_sql_subpanel, BorderLayout.EAST);

		// Sottopannelli di "sql_subpanel2"

		attributes_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 35, 15));
		createStandardPanel(attributes_panel, null, (new Dimension(100, 500)));
		sql_subpanel2.add(attributes_panel, BorderLayout.CENTER);

		createStandardPanel(buttons_panel, null, (new Dimension(230, 100)));
		buttons_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 15));
		sql_subpanel2.add(buttons_panel, BorderLayout.EAST);

		//

		left_sql_subpanel.add(Box.createRigidArea(new Dimension(15, 0)));

		quantityLB.setSize(new Dimension(100, 25));
		left_sql_subpanel.add(quantityLB);

		createTextField(quantityTF, "1", new Dimension(50, 25));
		left_sql_subpanel.add(quantityTF);

		left_sql_subpanel.add(Box.createRigidArea(new Dimension(10, 0)));

		setupButton(updateJB, update_inactiveIMG, new Dimension(200, 30));
		left_sql_subpanel.add(updateJB);

		left_sql_subpanel.add(Box.createRigidArea(new Dimension(10, 0)));

		setupButton(deleteJB, delete_inactiveIMG, new Dimension(200, 30));
		left_sql_subpanel.add(deleteJB);

		setupButton(deleteallJB, deleteall_inactiveIMG, new Dimension(200, 30));
		right_sql_subpanel.add(deleteallJB);

		right_sql_subpanel.add(Box.createRigidArea(new Dimension(15, 0)));

		setupButton(orderJB, order_inactiveIMG, new Dimension(100, 30));
		right_sql_subpanel.add(orderJB);

		setupButton(homeJB, home_inactiveIMG, new Dimension(50, 100));
		attributes_panel.add(homeJB);

		setupButton(profileJB, profile_inactiveIMG, new Dimension(50, 100));
		attributes_panel.add(profileJB);

		setupButton(backJB, back_button_inactiveIMG, new Dimension(100, 50));
		buttons_panel.add(backJB);

		buttons_panel.add(Box.createRigidArea(new Dimension(15, 0)));

		setupButton(logoutJB, logout_inactiveIMG, new Dimension(50, 100));
		buttons_panel.add(logoutJB);

	}

	private void events() {

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				background.setIcon(
						resize(backgroundIMG, CustomerCartFrame.this.getWidth(), CustomerCartFrame.this.getHeight()));
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

			public void mousePressed(MouseEvent e) {

				customer_controller.openCustomerProfileFrame();

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

			public void mousePressed(MouseEvent e) {

				customer_controller.openCustomerFrame(CustomerCartFrame.this);
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

			public void mousePressed(MouseEvent e) {

				customer_controller.openCustomerMealListFrame(CustomerCartFrame.this,
						customer_controller.getShop().getEmail());
			}

		});

		updateJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				updateJB.setIcon(update_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent e) {

				updateJB.setIcon(update_inactiveIMG);

			}

			public void mousePressed(MouseEvent e) {

				customer_controller.updateMealQuantity(CustomerCartFrame.this);
			}

		});

		deleteJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				deleteJB.setIcon(delete_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent e) {

				deleteJB.setIcon(delete_inactiveIMG);

			}

			public void mousePressed(MouseEvent e) {

				customer_controller.removeMealFromCart(CustomerCartFrame.this);
			}

		});

		deleteallJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				deleteallJB.setIcon(deleteall_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent e) {

				deleteallJB.setIcon(deleteall_inactiveIMG);

			}

			public void mousePressed(MouseEvent e) {

				customer_controller.removeEverythingFromCart(CustomerCartFrame.this);

			}

		});

		orderJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				orderJB.setIcon(order_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent e) {

				orderJB.setIcon(order_inactiveIMG);

			}

			public void mousePressed(MouseEvent e) {

				customer_controller.openCustomerCheckoutFrame(CustomerCartFrame.this);
				
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

			public void mousePressed(MouseEvent e) {

				login_controller.openLoginFrame(CustomerCartFrame.this);

			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				customer_controller.releaseAllDaoResourcesAndDisposeFrame(CustomerCartFrame.this);
			}
		});

	}

	private ImageIcon resize(ImageIcon im, int w, int h) {

		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		Graphics2D gd = (Graphics2D) bi.createGraphics();
		gd.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		gd.drawImage(im.getImage(), 0, 0, w, h, null);
		gd.dispose();
		return new ImageIcon(bi);
		
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

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTable getTable() {
		return table;
	}

	public JTextField getQuantityTF() {
		return quantityTF;
	}
}
