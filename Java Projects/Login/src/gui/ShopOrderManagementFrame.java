package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

public class ShopOrderManagementFrame extends JFrame{

	Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	ImageIcon delivering_orders_inactiveIMG;
	ImageIcon pending_orders_inactiveIMG;
	ImageIcon delivering_orders_activeIMG;
	ImageIcon pending_orders_activeIMG;
	ImageIcon view_orders_inactiveIMG;
	ImageIcon view_orders_activeIMG;
	ImageIcon go_back_inactiveIMG;
	ImageIcon go_back_activeIMG;
	
	JButton view_ordersJB;
	JButton delivering_ordersJB;
	JButton pending_ordersJB;
	JButton go_backJB;
	
	GridBagLayout gbl;
	GridBagConstraints gcon;
	
	private Color background_color = new Color(0xf3ecd7);
	private ShopController shop_controller;
	public ShopOrderManagementFrame(ShopController shop_controller) {
		
		initialize();
		setupFrame();
		events();
		this.shop_controller = shop_controller;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		view_orders_inactiveIMG = new ImageIcon("src\\images\\AdminFrame\\viewOrdersButtonInactive.png");
		view_orders_activeIMG = new ImageIcon("src\\images\\AdminFrame\\viewOrdersButtonActive.png");
		delivering_orders_inactiveIMG = new ImageIcon("src\\images\\AdminFrame\\deliveringOrdersButtonInactive.png");
		pending_orders_inactiveIMG = new ImageIcon("src\\images\\AdminFrame\\pendingOrdersButtonInactive.png");
		delivering_orders_activeIMG = new ImageIcon("src\\images\\AdminFrame\\deliveringOrdersButtonActive.png");
		pending_orders_activeIMG = new ImageIcon("src\\images\\AdminFrame\\pendingOrdersButtonActive.png");
		go_back_inactiveIMG = new ImageIcon("src\\images\\AdminFrame\\goBackButtonInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\AdminFrame\\goBackButtonActive.png");
		
		view_ordersJB = new JButton();
		delivering_ordersJB = new JButton();
		pending_ordersJB = new JButton();
		go_backJB = new JButton();
		
		gbl = new GridBagLayout();
		gcon = new GridBagConstraints();
		
	}
	
	private void setupFrame() {
		
		this.setTitle("Shop Panel: Order Management");
		this.setSize(1280,720);
		this.setResizable(false);
		int central_width = screen_dim.width/2-ShopOrderManagementFrame.this.getSize().width/2;
		int central_height = screen_dim.height/2-ShopOrderManagementFrame.this.getSize().height/2;
		this.setLocation(central_width, central_height); //Setta il frame a centro monitor
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(background_color);
		
		setupButton(view_ordersJB, view_orders_inactiveIMG);
		setupButton(pending_ordersJB, pending_orders_inactiveIMG);
		setupButton(delivering_ordersJB, delivering_orders_inactiveIMG);
		
		view_ordersJB.setBounds(150,100,400,200);
		this.getContentPane().add(view_ordersJB);
		pending_ordersJB.setBounds(710,100,400,200);
		this.getContentPane().add(pending_ordersJB);
		delivering_ordersJB.setBounds(150,380,400,200);
		this.getContentPane().add(delivering_ordersJB);
		
		setupButton(go_backJB, go_back_inactiveIMG);
		go_backJB.setBounds(845,430,150,100);
		this.getContentPane().add(go_backJB);
		
	}
	
	private void events() {
		
		view_ordersJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				shop_controller.openShopViewOrdersFrame(ShopOrderManagementFrame.this);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				view_ordersJB.setIcon(view_orders_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				view_ordersJB.setIcon(view_orders_inactiveIMG);
				
			}
		});
		
		delivering_ordersJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				delivering_ordersJB.setIcon(delivering_orders_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				delivering_ordersJB.setIcon(delivering_orders_inactiveIMG);
				
			}
		});
		
		pending_ordersJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

//				shop_controller.openShopMealFrame(ShopFrame.this);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				pending_ordersJB.setIcon(pending_orders_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				pending_ordersJB.setIcon(pending_orders_inactiveIMG);
				
			}
		});
		
		go_backJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
//				login_controller.openLoginFrame(ShopFrame.this);
				
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
		

		 addWindowListener(new WindowAdapter()
	     {
	         @Override
	         public void windowClosing(WindowEvent e)
	         {
	             shop_controller.closeWindow(ShopOrderManagementFrame.this);
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
