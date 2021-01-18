package gui;

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

import controllers.AdminController;

public class AdminFrame extends JFrame{

	
	Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	GridBagLayout gbl;
	GridBagConstraints gcon;
	
	ImageIcon customer_buttonIMG;
	ImageIcon shop_buttonIMG;
	ImageIcon rider_buttonIMG;
	ImageIcon orders_buttonIMG;
	ImageIcon meal_buttonIMG;
	
	JButton customerJB;
	JButton shopJB;
	JButton riderJB;
	JButton mealJB;

	private Color background_color = new Color(0xf3ecd7);
	

	public AdminFrame() {
		initialize();
		frameSetup();
		events();
	}
	
	private void initialize() {
		
		customer_buttonIMG = new ImageIcon("src\\images\\AdminFrame\\customerButton.png");
		shop_buttonIMG = new ImageIcon("src\\images\\AdminFrame\\shopButton.png");
		rider_buttonIMG = new ImageIcon("src\\images\\AdminFrame\\riderButton.png");
		orders_buttonIMG = new ImageIcon("src\\images\\AdminFrame\\ordersButton.png");
		meal_buttonIMG = new ImageIcon("src\\images\\AdminFrame\\mealButton.png");
		
		customerJB = new JButton();
		shopJB = new JButton();
		riderJB = new JButton();
		mealJB = new JButton();
		
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
		this.getContentPane().setLayout(gbl);
		this.getContentPane().setBackground(background_color);
		
		customerJB.setIcon(customer_buttonIMG);
		customerJB.setBorder(null);
		customerJB.setSize(760,400);
		customerJB.setPreferredSize(new Dimension(760,400));
		customerJB.setFocusable(false);
		customerJB.setContentAreaFilled(false);

		shopJB.setPreferredSize(new Dimension(760,400));
		shopJB.setIcon(shop_buttonIMG);
		shopJB.setBorder(null);
		shopJB.setFocusable(false);
		shopJB.setContentAreaFilled(false);
		
		riderJB.setPreferredSize(new Dimension(760,400));
		riderJB.setIcon(rider_buttonIMG);
		riderJB.setBorder(null);
		riderJB.setFocusable(false);
		riderJB.setContentAreaFilled(false);

		mealJB.setPreferredSize(new Dimension(760,400));
		mealJB.setIcon(meal_buttonIMG);
		mealJB.setBorder(null);
		mealJB.setFocusable(false);
		mealJB.setContentAreaFilled(false);
		
		//Impostazione GridBagLayout per i JButton
		
		gcon.insets = new Insets(0,50,0,50);
		gcon.weightx = 1;
		gcon.weighty = 1;
		gcon.fill = GridBagConstraints.BOTH;
		
		gcon.gridx = 0;
		gcon.gridy = 0;
		gcon.gridwidth = 1;
		gcon.gridheight = 1;
		
		gbl.setConstraints(customerJB, gcon);
		this.getContentPane().add(customerJB);
		
		gcon.gridx = 2;
		
		gbl.setConstraints(shopJB, gcon);
		this.getContentPane().add(shopJB);
		
		gcon.gridx = 0;
		gcon.gridy = 2;

		gbl.setConstraints(riderJB, gcon);
		this.getContentPane().add(riderJB);
		
		gcon.gridx = 2;
		gcon.gridy = 2;
		
		gbl.setConstraints(mealJB, gcon);
		this.getContentPane().add(mealJB);
		
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
		});
		
		riderJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Spostamento AdminRiderFrame
				AdminFrame.this.dispose();
				AdminController admin_controller= new AdminController();
				admin_controller.openAdminRiderFrame();
			}
		});
		
		customerJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Spostamento AdminUserFrame
				AdminFrame.this.dispose();
				AdminController admin_controller= new AdminController();
				admin_controller.openAdminCustomerPanelFrame();
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
		});
		
	}
}
