package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import controllers.AdminController;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.ShopDAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class AdminFrame extends JFrame{

	
	private int mouseX=0;
	private int mouseY=0;

	AdminController admin_controller = new AdminController();
	/**
	 * Create the application.
	 */
	public AdminFrame() {
		initialize();
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		GridBagLayout gbl = new GridBagLayout();
		ImageIcon maximize_button = new ImageIcon("src\\images\\FrameIcons\\maximizeFrameButton.png");
		ImageIcon minimize_button = new ImageIcon("src\\images\\FrameIcons\\minimizeFrameButton.png");
		ImageIcon close_button = new ImageIcon("src\\images\\FrameIcons\\closeFrameButton.png");
		ImageIcon iconifier_button = new ImageIcon("src\\images\\FrameIcons\\iconifierFrameButton.png");
		ImageIcon customer_buttonS = new ImageIcon("src\\images\\AdminFrame\\customerButtonSmall.png");
		ImageIcon customer_buttonB = new ImageIcon("src\\images\\AdminFrame\\customerButtonBig.png");
		ImageIcon shop_buttonS = new ImageIcon("src\\images\\AdminFrame\\shopButtonSmall.png");
		ImageIcon shop_buttonB = new ImageIcon("src\\images\\AdminFrame\\shopButtonBig.png");
		ImageIcon logoIcon = new ImageIcon("src\\images\\FrameIcons\\logoIcon.png");
		ImageIcon rider_buttonS = new ImageIcon("src\\images\\AdminFrame\\riderButtonSmall.png");
		ImageIcon rider_buttonB = new ImageIcon("src\\images\\AdminFrame\\riderButtonBig.png");
//		ImageIcon orders_buttonS = new ImageIcon("src\\images\\AdminFrame\\ordersButtonSmall.png");
//		ImageIcon orders_buttonB = new ImageIcon("src\\images\\AdminFrame\\ordersButtonBig.png");
		ImageIcon meal_buttonS = new ImageIcon("src\\images\\AdminFrame\\mealButtonSmall.png");
		ImageIcon meal_buttonB = new ImageIcon("src\\images\\AdminFrame\\mealButtonBig.png");
		
		
		this.setSize(1600,900);
		int central_width = dim.width/2-this.getSize().width/2;
		int central_height = dim.height/2-this.getSize().height/2;
		this.setLocation(central_width, central_height); //Setta il frame a centro monitor
		this.setUndecorated(true);
		this.getContentPane().setLayout(new BorderLayout());
		
		JPanel home_panel = new JPanel();
		home_panel.setLayout(gbl);
		home_panel.setBackground(new Color(0xf3ecd7));
		this.getContentPane().add(home_panel, BorderLayout.CENTER);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
		topPanel.setPreferredSize(new Dimension(100,35));
		topPanel.setBackground(new Color(0x771007));
		topPanel.setVisible(true);
		this.getContentPane().add(topPanel, BorderLayout.NORTH);	
		
		JLabel iconifierFrameButton = new JLabel();
		iconifierFrameButton.setIcon(iconifier_button);
		
		JLabel resizeFrameButton = new JLabel();
		resizeFrameButton.setIcon(maximize_button);
		
		JLabel closeFrameButton = new JLabel();
		closeFrameButton.setIcon(close_button);
		
		JLabel logoFrameButton = new JLabel("<html><font face='Calibri' size='4' color=rgb(243,236,215)>FOOD OVERFLOW:</font> <font face='Calibri' size='4' color=rgb(244,153,105)>ADMIN PANEL</font></html>");
		logoFrameButton.setForeground(new Color(0xf3ecd7));;
		logoFrameButton.setIcon(logoIcon);
		topPanel.add(logoFrameButton);
		topPanel.add(Box.createHorizontalGlue());
		topPanel.add(iconifierFrameButton);
		topPanel.add(resizeFrameButton);
		topPanel.add(closeFrameButton);
		
		JButton customerButton = new JButton();
		customerButton.setIcon(customer_buttonS);
		customerButton.setBorder(null);
		customerButton.setPreferredSize(new Dimension(600,300));
		customerButton.setFocusable(false);
		customerButton.setContentAreaFilled(false);
		
		JButton shopButton = new JButton();
		shopButton.setPreferredSize(new Dimension(600,300));
		shopButton.setIcon(shop_buttonS);
		shopButton.setBorder(null);
		shopButton.setFocusable(false);
		shopButton.setContentAreaFilled(false);
		
		JButton riderButton = new JButton();
		riderButton.setPreferredSize(new Dimension(600,300));
		riderButton.setIcon(rider_buttonS);
		riderButton.setBorder(null);
		riderButton.setFocusable(false);
		riderButton.setContentAreaFilled(false);
		JButton mealButton = new JButton();
		mealButton.setPreferredSize(new Dimension(600,300));
		mealButton.setIcon(meal_buttonS);
		mealButton.setBorder(null);
		mealButton.setFocusable(false);
		mealButton.setContentAreaFilled(false);
		
		topPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				AdminFrame.this.setLocation (AdminFrame.this.getX()+e.getX()-mouseX,AdminFrame.this.getY()+e.getY()-mouseY);
				
			}
		});
		topPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				mouseX = e.getX();
				mouseY = e.getY();
				
				}
		});
		
		closeFrameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.exit(0); // Da rivedere la chiusura del frame
				
			}
		});
		
		resizeFrameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(AdminFrame.this.getSize().equals(new Dimension(1600,900))) {
					
					resizeFrameButton.setIcon(minimize_button);
					AdminFrame.this.setLocation(central_width, central_height);
					AdminFrame.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
					customerButton.setIcon(customer_buttonB);
					shopButton.setIcon(shop_buttonB);
					mealButton.setIcon(meal_buttonB);
					riderButton.setIcon(rider_buttonB);
					
				} else if(AdminFrame.this.getSize().equals(dim)) {
					
					resizeFrameButton.setIcon(maximize_button);
					AdminFrame.this.setSize(1600,900);
					AdminFrame.this.setLocation(central_width, central_height);
					customerButton.setIcon(customer_buttonS);
					shopButton.setIcon(shop_buttonS);
					mealButton.setIcon(meal_buttonS);
					riderButton.setIcon(rider_buttonS);
					
				}
				
			}
		});
		
		iconifierFrameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				AdminFrame.this.setState(Frame.ICONIFIED);
				
			}
		});
		
		shopButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				AdminFrame.this.setVisible(false);
				admin_controller.openShopFrame();
			}
		});
		
		riderButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				AdminFrame.this.setVisible(false);
				admin_controller.openRiderFrame();
			}
		});
		GridBagConstraints gcon = new GridBagConstraints();
		gcon.insets = new Insets(50,100,50,100);

		gcon.weightx = 1;
		gcon.weighty = 1;
		
		gcon.fill = GridBagConstraints.BOTH;
		
		//1
		gcon.gridx = 0;
		gcon.gridy = 0;
		gcon.gridwidth = 1;
		gcon.gridheight = 1;
		
		gbl.setConstraints(customerButton, gcon);
		home_panel.add(customerButton);
		
		gcon.gridx = 2;
		
		gbl.setConstraints(shopButton, gcon);
		home_panel.add(shopButton);
		
		gcon.gridx = 0;
		gcon.gridy = 2;

		gbl.setConstraints(riderButton, gcon);
		home_panel.add(riderButton);
		
		gcon.gridx = 2;
		gcon.gridy = 2;
		
		gbl.setConstraints(mealButton, gcon);
		home_panel.add(mealButton);
		
	}

}

