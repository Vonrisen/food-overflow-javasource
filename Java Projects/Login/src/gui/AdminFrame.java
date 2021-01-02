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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class AdminFrame {

	public JFrame frame;
	private int mouseX=0;
	private int mouseY=0;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_1;
	
	/**
	 * Create the application.
	 */
	public AdminFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
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
		ImageIcon orders_buttonS = new ImageIcon("src\\images\\AdminFrame\\ordersButtonSmall.png");
		ImageIcon orders_buttonB = new ImageIcon("src\\images\\AdminFrame\\ordersButtonBig.png");
		ImageIcon meal_buttonS = new ImageIcon("src\\images\\AdminFrame\\mealButtonSmall.png");
		ImageIcon meal_buttonB = new ImageIcon("src\\images\\AdminFrame\\mealButtonBig.png");
		ImageIcon shops_table_title = new ImageIcon("src\\images\\tableTitles\\shopsTableTitle.png");
		ImageIcon delete_button_inactive = new ImageIcon("src\\images\\SqlButtons\\deleteButtonInactive.png");
		ImageIcon delete_button_active = new ImageIcon("src\\images\\SqlButtons\\deleteButtonActive.png");
		ImageIcon insert_button_inactive = new ImageIcon("src\\images\\SqlButtons\\insertButtonInactive.png");
		ImageIcon insert_button_active = new ImageIcon("src\\images\\SqlButtons\\insertButtonActive.png");
		ImageIcon update_button_inactive = new ImageIcon("src\\images\\SqlButtons\\updateButtonInactive.png");
		ImageIcon update_button_active = new ImageIcon("src\\images\\SqlButtons\\updateButtonActive.png");
		
		frame = new JFrame();
		frame.setSize(1600,900);
		int central_width = dim.width/2-frame.getSize().width/2;
		int central_height = dim.height/2-frame.getSize().height/2;
		frame.setLocation(central_width, central_height); //Setta il frame a centro monitor
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel home_panel = new JPanel();
		home_panel.setLayout(gbl);
		home_panel.setBackground(new Color(0xf3ecd7));
//		frame.getContentPane().add(home_panel, BorderLayout.CENTER);
		
		
		JPanel shop_panel = new JPanel();
		shop_panel.setLayout(null);
		shop_panel.setBackground(new Color(0xf3ecd7));
		frame.getContentPane().add(shop_panel, BorderLayout.CENTER);
		
		
		//*********CONTENUTO SHOP PANEL**********************
		JScrollPane scrollPane = new JScrollPane();
		shop_panel.add(scrollPane);
		scrollPane.setBounds(92, 137, 850, 600);
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		table.setFocusable(false);
		table.setEnabled(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setBackground(Color.black);
		table.getTableHeader().setForeground(Color.yellow);
		table.setBackground(Color.blue);
		table.setFont(new Font("", 1, 10));
		table.setForeground(Color.white);
		
		JButton insert_sql_button = new JButton();
		insert_sql_button.setIcon(insert_button_inactive);
		insert_sql_button.setBounds(984, 669, 150,30);
		insert_sql_button.setBorder(null);
		insert_sql_button.setFocusable(false);
		insert_sql_button.setContentAreaFilled(false);
		shop_panel.add(insert_sql_button);
		
		JButton update_sql_button = new JButton();
		update_sql_button.setIcon(update_button_inactive);
		update_sql_button.setBounds(1200, 669, 150,30);
		update_sql_button.setBorder(null);
		update_sql_button.setFocusable(false);
		update_sql_button.setContentAreaFilled(false);
		shop_panel.add(update_sql_button);
		
		JButton delete_sql_button = new JButton();
		delete_sql_button.setIcon(delete_button_inactive);
		delete_sql_button.setBounds(1400, 669, 150,30);
		delete_sql_button.setBorder(null);
		delete_sql_button.setFocusable(false);
		delete_sql_button.setContentAreaFilled(false);
		shop_panel.add(delete_sql_button);
		
		JLabel shops_tableT = new JLabel();
		shops_tableT.setIcon(shops_table_title);
		shops_tableT.setBounds(92, 26, 225,100);
		shop_panel.add(shops_tableT);
//		
//		JLabel shops_tableT = new JLabel();
//		shops_tableT.setIcon(shops_table_title);
//		shops_tableT.setBounds(20, 57, 225,100);
//		shop_panel.add(shops_tableT);
//		
//		JLabel shops_tableT = new JLabel();
//		shops_tableT.setIcon(shops_table_title);
//		shops_tableT.setBounds(20, 57, 225,100);
//		shop_panel.add(shops_tableT);
//		
		
		//*********CONTENUTO SHOP PANEL**********************
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
		topPanel.setPreferredSize(new Dimension(100,35));
		topPanel.setBackground(new Color(0x771007));
		topPanel.setVisible(true);
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);	
		
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
				
				frame.setLocation (frame.getX()+e.getX()-mouseX,frame.getY()+e.getY()-mouseY);
				
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
				
				if(frame.getSize().equals(new Dimension(1600,900))) {
					
					resizeFrameButton.setIcon(minimize_button);
					frame.setLocation(central_width, central_height);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					customerButton.setIcon(customer_buttonB);
					shopButton.setIcon(shop_buttonB);
					mealButton.setIcon(meal_buttonB);
					riderButton.setIcon(rider_buttonB);
					
				} else if(frame.getSize().equals(dim)) {
					
					resizeFrameButton.setIcon(maximize_button);
					frame.setSize(1600,900);
					frame.setLocation(central_width, central_height);
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
				
				frame.setState(Frame.ICONIFIED);
				
			}
		});
		
		shopButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				frame.getContentPane().remove(home_panel);
				frame.getContentPane().add(shop_panel, BorderLayout.CENTER);
				frame.getContentPane().validate();
				frame.getContentPane().repaint();
				
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
	
	private void Shop(JPanel shop_panel) {
		
		JScrollPane scrollPane = new JScrollPane();
		shop_panel.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBounds(100, 100, 850, 600);
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		table.setFocusable(false);
		table.setEnabled(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setBackground(Color.black);
		table.getTableHeader().setForeground(Color.yellow);
		table.setBackground(Color.blue);
		table.setFont(new Font("", 1, 10));
		table.setForeground(Color.white);
		
	}
}
