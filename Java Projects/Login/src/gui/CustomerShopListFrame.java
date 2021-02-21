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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.CustomerController;
import controllers.LoginController;

@SuppressWarnings("serial")
public class CustomerShopListFrame extends JFrame {

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	private ImageIcon backgroundIMG;
	private ImageIcon profile_inactiveIMG;
	private ImageIcon profile_activeIMG;
	private ImageIcon home_inactiveIMG;
	private ImageIcon home_activeIMG;
	private ImageIcon logout_inactiveIMG;
	private ImageIcon logout_activeIMG;

	private Dimension west_east_size;
	private Dimension north_south_size;

	private String[] columns = { "Nome", "Indirizzo", "Orario di apertura", "Giorni festivi", "Telefono fisso",
			"Email" };

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

	private JButton profileJB;
	private JButton homeJB;
	private JButton logoutJB;

	private JLabel background;
	private DefaultTableModel model;
	private CustomerController customer_controller;
	private LoginController login_controller;

	public CustomerShopListFrame(CustomerController customer_controller, LoginController login_controller) {

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

		west_east_size = new Dimension(100, 80);
		north_south_size = new Dimension(100, 50);

		west_panel = new JPanel();
		east_panel = new JPanel();
		north_panel = new JPanel();
		south_panel = new JPanel();
		center_panel = new JPanel();
		sql_panel = new JPanel();
		attributes_panel = new JPanel();
		buttons_panel = new JPanel();

		background = new JLabel();

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

	}

	private void frameSetup() {

		// Layout setup

		this.setTitle("Food Overflow - Lista dei ristoranti nella tua zona");
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
		scroll_pane.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(0x771007)));

		// Sottopannell di "center_panel"

		sql_panel.setLayout(new BorderLayout());
		createStandardPanel(sql_panel, null, (new Dimension(100, 80)));
		center_panel.add(sql_panel, BorderLayout.SOUTH);

		center_panel.add(scroll_pane, BorderLayout.CENTER);

		// Sottopannelli di "sql_panel"

		attributes_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 35, 15));
		createStandardPanel(attributes_panel, null, (new Dimension(100, 500)));
		sql_panel.add(attributes_panel, BorderLayout.CENTER);

		createStandardPanel(buttons_panel, null, (new Dimension(135, 100)));
		buttons_panel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 15));
		sql_panel.add(buttons_panel, BorderLayout.EAST);

		//

		setupButton(homeJB, home_inactiveIMG, new Dimension(50, 100));
		attributes_panel.add(homeJB);

		setupButton(profileJB, profile_inactiveIMG, new Dimension(50, 100));
		attributes_panel.add(profileJB);

		setupButton(logoutJB, logout_inactiveIMG, new Dimension(50, 100));
		buttons_panel.add(logoutJB);

	}

	private void events() {

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				background.setIcon(resize(backgroundIMG, CustomerShopListFrame.this.getWidth(),
						CustomerShopListFrame.this.getHeight()));
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

				customer_controller.openCustomerFrame(CustomerShopListFrame.this);
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

				login_controller.openLoginFrame(CustomerShopListFrame.this);
			}
		});

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				if (!table.getSelectionModel().isSelectionEmpty()) {
					customer_controller.openCustomerMealListFrame(CustomerShopListFrame.this,
							table.getModel().getValueAt(table.getSelectedRow(), 5).toString());
				}
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				customer_controller.releaseAllDaoResourcesAndDisposeFrame(CustomerShopListFrame.this);
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

}
