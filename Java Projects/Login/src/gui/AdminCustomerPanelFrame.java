package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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

public class AdminCustomerPanelFrame extends JFrame {
	
	Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	GridBagLayout gbl;
	GridBagConstraints gcon;
	
	ImageIcon customer_buttonIMG;
	ImageIcon orders_buttonIMG;
	private ImageIcon go_back_inactiveIMG;
	private ImageIcon go_back_activeIMG;
	
	Dimension button_size;
	
	JPanel south_panel;
	JPanel center_panel;
	
	JButton customerJB;
	JButton ordersJB;
	JButton go_backJB;

	private Color background_color = new Color(0xf3ecd7);

	public AdminCustomerPanelFrame() {
		initialize();
		frameSetup();
		events();
	}
	
	private void initialize() {
		
		customer_buttonIMG = new ImageIcon("src\\images\\AdminFrame\\customerButton.png");
		orders_buttonIMG = new ImageIcon("src\\images\\AdminFrame\\ordersButton.png");
		go_back_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\goBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\SqlButtons\\goBackActive.png");
		
		south_panel = new JPanel();
		center_panel = new JPanel();
		
		customerJB = new JButton();
		ordersJB = new JButton();
		go_backJB = new JButton();
		
		button_size = new Dimension(400,200);
		
		gbl = new GridBagLayout();
		gcon = new GridBagConstraints();
		
	}
	
	private void frameSetup() {
		
		this.setTitle("AdminPanel: Customer Panel");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280,720);
		this.setResizable(false);
		
		int central_width = screen_dim.width/2-this.getSize().width/2;
		int central_height = screen_dim.height/2-this.getSize().height/2;
		this.setLocation(central_width, central_height); //Setta il frame a centro monitor
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().setBackground(background_color);
		
		center_panel.setBackground(null);
		center_panel.setLayout(gbl);
		center_panel.setPreferredSize(new Dimension(100,100));
		this.getContentPane().add(center_panel, BorderLayout.CENTER);
		
		south_panel.setBackground(null);
		south_panel.setPreferredSize(new Dimension(100,50));
		this.getContentPane().add(south_panel, BorderLayout.SOUTH);
		
		// Setup Buttons
		
		setupButton(customerJB, customer_buttonIMG, button_size);
		
		setupButton(ordersJB, orders_buttonIMG, button_size);
		
		setupButton(go_backJB, go_back_inactiveIMG, new Dimension(150,30));
		south_panel.add(go_backJB);
		
		//Setup GridBagLayout
		
		gcon.insets = new Insets(215,115,215,115);
		gcon.weightx = 1;
		gcon.weighty = 1;
		gcon.fill = GridBagConstraints.BOTH;
		
		gcon.gridx = 0;
		gcon.gridy = 0;
		gcon.gridwidth = 1;
		gcon.gridheight = 1;
		
		gcon.gridx = 0;
		gcon.gridy = 1;

		gbl.setConstraints(customerJB, gcon);
		center_panel.add(customerJB);
		
		gcon.gridx = 2;
		gcon.gridy = 1;
		
		gbl.setConstraints(ordersJB, gcon);
		center_panel.add(ordersJB);
		
	}

	private void events() {
		
		customerJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				//Apro AdminCustomerFrame
				AdminCustomerPanelFrame.this.dispose();
				AdminController admin_controller = new AdminController();
				admin_controller.openAdminCustomerFrame();
			}
		});
		
		ordersJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				//Apro AdminOrdersFrame
				
			}
		});
		
		go_backJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				//Apre AdminFrame
				AdminCustomerPanelFrame.this.dispose();
				AdminController admin_controller = new AdminController();
				admin_controller.openAdminFrame();
			
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

	private void setupButton(JButton button, ImageIcon image, Dimension dimension) {
		
		button.setIcon(image);
		button.setSize(dimension);
		button.setBorder(null);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		
	}
	
}
