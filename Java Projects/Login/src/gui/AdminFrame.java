package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controllers.AdminController;
import controllers.LoginController;

@SuppressWarnings("serial")
public class AdminFrame extends JFrame {

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();

	private GridBagLayout gbl;
	private GridBagConstraints gcon;

	private JPanel center_panel;
	private JPanel south_panel;

	private ImageIcon customer_button_inactiveIMG;
	private ImageIcon shop_button_inactiveIMG;
	private ImageIcon orders_button_inactiveIMG;
	private ImageIcon meal_button_inactiveIMG;
	private ImageIcon disconnect_button_inactiveIMG;
	private ImageIcon customer_button_activeIMG;
	private ImageIcon shop_button_activeIMG;
	private ImageIcon orders_button_activeIMG;
	private ImageIcon meal_button_activeIMG;
	private ImageIcon disconnect_button_activeIMG;

	private JButton customerJB;
	private JButton shopJB;
	private JButton ordersJB;
	private JButton mealJB;
	private JButton disconnectJB;

	private Color background_color = new Color(0xf3ecd7);
	private AdminController admin_controller;
	private LoginController login_controller;

	// Costruttore
	public AdminFrame(AdminController admin_controller, LoginController login_controller) {
		initialize();
		frameSetup();
		events();
		this.admin_controller = admin_controller;
		this.login_controller = login_controller;
	}

	private void initialize() {

		customer_button_inactiveIMG = new ImageIcon("src\\images\\big_buttons\\customerButtonInactive.png");
		shop_button_inactiveIMG = new ImageIcon("src\\images\\big_buttons\\shopButtonInactive.png");
		orders_button_inactiveIMG = new ImageIcon("src\\images\\big_buttons\\searchOrdersButtonInactive.png");
		meal_button_inactiveIMG = new ImageIcon("src\\images\\big_buttons\\mealButtonInactive.png");
		disconnect_button_inactiveIMG = new ImageIcon("src\\images\\big_buttons\\disconnectButtonInactive.png");
		disconnect_button_activeIMG = new ImageIcon("src\\images\\big_buttons\\disconnectButtonActive.png");
		customer_button_activeIMG = new ImageIcon("src\\images\\big_buttons\\customerButtonActive.png");
		shop_button_activeIMG = new ImageIcon("src\\images\\big_buttons\\shopButtonActive.png");
		orders_button_activeIMG = new ImageIcon("src\\images\\big_buttons\\searchOrdersButtonActive.png");
		meal_button_activeIMG = new ImageIcon("src\\images\\big_buttons\\mealButtonActive.png");

		center_panel = new JPanel();
		south_panel = new JPanel();

		customerJB = new JButton();
		shopJB = new JButton();
		ordersJB = new JButton();
		mealJB = new JButton();
		disconnectJB = new JButton();

		gbl = new GridBagLayout();
		gcon = new GridBagConstraints();

	}

	private void frameSetup() {

		// Layout setup
		this.setTitle("Food Overflow - Admin Panel");
		this.setSize(1280, 720);
		this.setResizable(false);
		setIconImage(new ImageIcon("src\\images\\startup\\icon.png").getImage());
		int central_width = screen_dim.width / 2 - this.getSize().width / 2;
		int central_height = screen_dim.height / 2 - this.getSize().height / 2;
		this.setLocation(central_width, central_height); // Setta il frame a centro monitor
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().setBackground(background_color);

		center_panel.setLayout(gbl);
		center_panel.setBackground(null);
		this.getContentPane().add(center_panel, BorderLayout.CENTER);

		south_panel.setLayout(null);
		south_panel.setBackground(null);
		south_panel.setPreferredSize(new Dimension(100, 150));
		this.getContentPane().add(south_panel, BorderLayout.SOUTH);

		setupButton(customerJB, customer_button_inactiveIMG);
		setupButton(shopJB, shop_button_inactiveIMG);
		setupButton(ordersJB, orders_button_inactiveIMG);
		setupButton(mealJB, meal_button_inactiveIMG);
		setupButton(disconnectJB, disconnect_button_inactiveIMG);
		disconnectJB.setBounds(565, 20, 150, 100);
		south_panel.add(disconnectJB);

		// Gridbaglayout setup
		gcon.insets = new Insets(10, 50, 10, 50);
		gcon.weightx = 1;
		gcon.weighty = 1;
		gcon.fill = GridBagConstraints.BOTH;

		gcon.gridx = 0;
		gcon.gridy = 0;
		gcon.gridwidth = 1;
		gcon.gridheight = 1;

		gbl.setConstraints(customerJB, gcon);
		center_panel.add(customerJB);

		gcon.gridx = 2;

		gbl.setConstraints(shopJB, gcon);
		center_panel.add(shopJB);

		gcon.gridx = 0;
		gcon.gridy = 2;

		gbl.setConstraints(ordersJB, gcon);
		center_panel.add(ordersJB);

		gcon.gridx = 2;
		gcon.gridy = 2;

		gbl.setConstraints(mealJB, gcon);
		center_panel.add(mealJB);

	}

	private void events() {

		shopJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.openAdminShopFrame(AdminFrame.this);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				shopJB.setIcon(shop_button_activeIMG);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				shopJB.setIcon(shop_button_inactiveIMG);
			}
		});

		ordersJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				admin_controller.openAdminOrderFrame(AdminFrame.this);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				ordersJB.setIcon(orders_button_activeIMG);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ordersJB.setIcon(orders_button_inactiveIMG);
			}
		});

		customerJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.openAdminCustomerFrame(AdminFrame.this);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				customerJB.setIcon(customer_button_activeIMG);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				customerJB.setIcon(customer_button_inactiveIMG);
			}
		});

		mealJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.openAdminMealFrame(AdminFrame.this);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mealJB.setIcon(meal_button_activeIMG);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mealJB.setIcon(meal_button_inactiveIMG);
			}
		});

		disconnectJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				login_controller.openLoginFrame(AdminFrame.this);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				disconnectJB.setIcon(disconnect_button_activeIMG);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				disconnectJB.setIcon(disconnect_button_inactiveIMG);
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				admin_controller.releaseAllDaoResourcesAndDisposeFrame(AdminFrame.this);
			}
		});
	}

	private void setupButton(JButton button, ImageIcon image) {

		button.setIcon(image);
		button.setBorder(null);
		button.setFocusable(false);
		button.setContentAreaFilled(false);

	}

}
