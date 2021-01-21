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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.AdminController;
import controllers.LoginController;

public class AdminFrame extends JFrame{

	
	Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	GridBagLayout gbl;
	GridBagConstraints gcon;
	
	JPanel center_panel;
	JPanel south_panel;
	
	ImageIcon customer_button_inactiveIMG;
	ImageIcon shop_button_inactiveIMG;
	ImageIcon orders_button_inactiveIMG;
	ImageIcon meal_button_inactiveIMG;
	ImageIcon disconnect_button_inactiveIMG;
	
	ImageIcon customer_button_activeIMG;
	ImageIcon shop_button_activeIMG;
	ImageIcon orders_button_activeIMG;
	ImageIcon meal_button_activeIMG;
	ImageIcon disconnect_button_activeIMG;
	
	JButton customerJB;
	JButton shopJB;
	JButton ordersJB;
	JButton mealJB;
	JButton disconnectJB;

	private Color background_color = new Color(0xf3ecd7);
	

	public AdminFrame() {
		initialize();
		frameSetup();
		events();
	}
	
	private void initialize() {
		
		customer_button_inactiveIMG = new ImageIcon("src\\images\\AdminFrame\\customerButtonInactive.png");
		shop_button_inactiveIMG = new ImageIcon("src\\images\\AdminFrame\\shopButtonInactive.png");
		orders_button_inactiveIMG = new ImageIcon("src\\images\\AdminFrame\\ordersButtonInactive.png");
		meal_button_inactiveIMG = new ImageIcon("src\\images\\AdminFrame\\mealButtonInactive.png");
		disconnect_button_inactiveIMG = new ImageIcon("src\\images\\AdminFrame\\disconnectButtonInactive.png");
		disconnect_button_activeIMG = new ImageIcon("src\\images\\AdminFrame\\disconnectButtonActive.png");
		customer_button_activeIMG = new ImageIcon("src\\images\\AdminFrame\\customerButtonActive.png");
		shop_button_activeIMG = new ImageIcon("src\\images\\AdminFrame\\shopButtonActive.png");
		orders_button_activeIMG = new ImageIcon("src\\images\\AdminFrame\\ordersButtonActive.png");
		meal_button_activeIMG = new ImageIcon("src\\images\\AdminFrame\\mealButtonActive.png");
		
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
		
		this.setTitle("Admin Panel");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280,720);
		this.setResizable(false);
		int central_width = screen_dim.width/2-this.getSize().width/2;
		int central_height = screen_dim.height/2-this.getSize().height/2;
		this.setLocation(central_width, central_height); //Setta il frame a centro monitor
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().setBackground(background_color);
		
		center_panel.setLayout(gbl);
		center_panel.setBackground(null);
		this.getContentPane().add(center_panel, BorderLayout.CENTER);
		
		south_panel.setLayout(null);
		south_panel.setBackground(null);
		south_panel.setPreferredSize(new Dimension(100,150));
		this.getContentPane().add(south_panel, BorderLayout.SOUTH);
		
		setupButton(customerJB, customer_button_inactiveIMG);

		setupButton(shopJB, shop_button_inactiveIMG);
		
		setupButton(ordersJB, orders_button_inactiveIMG);

		setupButton(mealJB, meal_button_inactiveIMG);
		
		setupButton(disconnectJB, disconnect_button_inactiveIMG);
		disconnectJB.setBounds(565,20,150,100);
		south_panel.add(disconnectJB);
		
		//Impostazione GridBagLayout per i JButton
		
		gcon.insets = new Insets(10,50,10,50);
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
				//Spostamento AdminShopFrame
				AdminFrame.this.dispose();
				AdminController admin_controller= new AdminController();
				admin_controller.openAdminShopFrame();
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
				//Spostamento AdminOrdersFrame
//				AdminFrame.this.dispose();
//				AdminController admin_controller= new AdminController();
//				admin_controller.openAdminOrdersFrame();
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
				//Spostamento AdminUserFrame
				AdminFrame.this.dispose();
				AdminController admin_controller= new AdminController();
				admin_controller.openAdminCustomerFrame();
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
				//Spostamento AdminMealFrame
				AdminFrame.this.dispose();
				AdminController admin_controller= new AdminController();
				admin_controller.openAdminMealFrame();
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
				//Spostamento LoginFrame
				AdminFrame.this.dispose();
				LoginController login_controller = new LoginController();
				login_controller.openLoginFrame();
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
		
	}
	
	private void setupButton(JButton button, ImageIcon image) {
		
		button.setIcon(image);
		button.setBorder(null);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		
	}
	
}
