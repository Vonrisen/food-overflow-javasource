package gui;

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

import controllers.LoginController;
import controllers.ShopController;

@SuppressWarnings("serial")
public class ShopFrame extends JFrame {

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();

	private ImageIcon orders_button_inactiveIMG;
	private ImageIcon meal_button_inactiveIMG;
	private ImageIcon orders_button_activeIMG;
	private ImageIcon meal_button_activeIMG;
	private ImageIcon riders_button_inactiveIMG;
	private ImageIcon riders_button_activeIMG;
	private ImageIcon disconnect_button_inactiveIMG;
	private ImageIcon disconnect_button_activeIMG;

	private JButton ridersJB;
	private JButton ordersJB;
	private JButton mealsJB;
	private JButton disconnectJB;

	private Color background_color = new Color(0xf3ecd7);
	private ShopController shop_controller;
	private LoginController login_controller;

	public ShopFrame(ShopController shop_controller, LoginController login_controller) {

		initialize();
		setupFrame();
		events();
		this.shop_controller = shop_controller;
		this.login_controller = login_controller;

	}

	private void initialize() {

		riders_button_inactiveIMG = new ImageIcon("src\\images\\big_buttons\\riderButtonInactive.png");
		riders_button_activeIMG = new ImageIcon("src\\images\\big_buttons\\riderButtonActive.png");
		orders_button_inactiveIMG = new ImageIcon("src\\images\\big_buttons\\ordersButtonInactive.png");
		meal_button_inactiveIMG = new ImageIcon("src\\images\\big_buttons\\menuButtonInactive.png");
		orders_button_activeIMG = new ImageIcon("src\\images\\big_buttons\\ordersButtonActive.png");
		meal_button_activeIMG = new ImageIcon("src\\images\\big_buttons\\menuButtonActive.png");
		disconnect_button_inactiveIMG = new ImageIcon("src\\images\\big_buttons\\disconnectButtonInactive.png");
		disconnect_button_activeIMG = new ImageIcon("src\\images\\big_buttons\\disconnectButtonActive.png");

		ridersJB = new JButton();
		ordersJB = new JButton();
		mealsJB = new JButton();
		disconnectJB = new JButton();

	}

	private void setupFrame() {

		// Layout setup

		this.setTitle("Food Overflow - Shop Panel");
		this.setSize(1280, 720);
		setIconImage(new ImageIcon("src\\images\\startup\\icon.png").getImage());
		this.setResizable(false);
		int central_width = screen_dim.width / 2 - this.getSize().width / 2;
		int central_height = screen_dim.height / 2 - this.getSize().height / 2;
		this.setLocation(central_width, central_height); // Setta il frame a centro monitor
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(background_color);

		setupButton(ridersJB, riders_button_inactiveIMG);
		setupButton(mealsJB, meal_button_inactiveIMG);
		setupButton(ordersJB, orders_button_inactiveIMG);

		ridersJB.setBounds(150, 100, 400, 200);
		this.getContentPane().add(ridersJB);
		mealsJB.setBounds(710, 100, 400, 200);
		this.getContentPane().add(mealsJB);
		ordersJB.setBounds(150, 380, 400, 200);
		this.getContentPane().add(ordersJB);

		setupButton(disconnectJB, disconnect_button_inactiveIMG);
		disconnectJB.setBounds(845, 430, 150, 100);
		this.getContentPane().add(disconnectJB);

	}

	private void events() {

		ridersJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				shop_controller.openShopRiderFrame(ShopFrame.this);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				ridersJB.setIcon(riders_button_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent e) {

				ridersJB.setIcon(riders_button_inactiveIMG);

			}
		});

		ordersJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.openShopOrderManagementFrame(ShopFrame.this);
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

		mealsJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				shop_controller.openShopMealFrame(ShopFrame.this);

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				mealsJB.setIcon(meal_button_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent e) {

				mealsJB.setIcon(meal_button_inactiveIMG);

			}
		});

		disconnectJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				shop_controller.releaseDaoResourcesWhenLoggingOut();
				login_controller.openLoginFrame(ShopFrame.this);

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
				shop_controller.releaseAllDaoResourcesAndDisposeFrame(ShopFrame.this);
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
