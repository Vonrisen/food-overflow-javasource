package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import controllers.AdminController;
import gui_support.RoundJTextField;

public class ShopFrame extends JFrame{

	
	private int mouseX=0;
	private int mouseY=0;
	private JTable table;
	private JScrollPane scrollPane1;
	private JTextField nameTF;
	private JTextField addressTF;
	private JTextField working_hoursTF;
	private JTextField closing_daysTF;
	private JTextField passwordTF;
	/**
	 * Create the application.
	 */
	public ShopFrame() {
	
		initialize();
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		ImageIcon maximize_button = new ImageIcon("src\\images\\FrameIcons\\maximizeFrameButton.png");
		ImageIcon minimize_button = new ImageIcon("src\\images\\FrameIcons\\minimizeFrameButton.png");
		ImageIcon close_button = new ImageIcon("src\\images\\FrameIcons\\closeFrameButton.png");
		ImageIcon iconifier_button = new ImageIcon("src\\images\\FrameIcons\\iconifierFrameButton.png");
		ImageIcon logoIcon = new ImageIcon("src\\images\\FrameIcons\\logoIcon.png");
		ImageIcon shops_table_title = new ImageIcon("src\\images\\tableTitles\\shops.png");
		ImageIcon delete_button_inactive = new ImageIcon("src\\images\\SqlButtons\\deleteButtonInactive.png");
		ImageIcon delete_button_active = new ImageIcon("src\\images\\SqlButtons\\deleteButtonActive.png");
		ImageIcon insert_button_inactive = new ImageIcon("src\\images\\SqlButtons\\insertButtonInactive.png");
		ImageIcon insert_button_active = new ImageIcon("src\\images\\SqlButtons\\insertButtonActive.png");
		ImageIcon update_button_inactive = new ImageIcon("src\\images\\SqlButtons\\updateButtonInactive.png");
		ImageIcon update_button_active = new ImageIcon("src\\images\\SqlButtons\\updateButtonActive.png");
		ImageIcon go_back_inactive = new ImageIcon("src\\images\\AdminFrame\\turnBackInactive.png");
		ImageIcon go_back_active = new ImageIcon("src\\images\\AdminFrame\\turnBackActive.png");
		AdminController admin_controller = new AdminController();
		this.setSize(1600,900);
		int central_width = dim.width/2-this.getSize().width/2;
		int central_height = dim.height/2-this.getSize().height/2;
		this.setLocation(central_width, central_height); //Setta il frame a centro monitor
		this.setUndecorated(true);
		this.getContentPane().setLayout(new BorderLayout());
		
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
		
		JLabel logoFrameButton = new JLabel("<html><font face='Calibri' size='4' color=rgb(243,236,215)>FOOD OVERFLOW:</font> <font face='Calibri' size='4' color=rgb(244,153,105)>ADMIN PANEL (SHOP)</font></html>");
		logoFrameButton.setForeground(new Color(0xf3ecd7));;
		logoFrameButton.setIcon(logoIcon);
		topPanel.add(logoFrameButton);
		topPanel.add(Box.createHorizontalGlue());
		topPanel.add(iconifierFrameButton);
		topPanel.add(resizeFrameButton);
		topPanel.add(closeFrameButton);
		
		JPanel shop_panel = new JPanel();
		shop_panel.setLayout(null);
		shop_panel.setBackground(new Color(0xf3ecd7));
		this.getContentPane().add(shop_panel, BorderLayout.CENTER);
		
		
		//*********CONTENUTO SHOP PANEL**********************
		scrollPane1 = new JScrollPane();
		shop_panel.add(scrollPane1);
		scrollPane1.setBounds(90, 132, 850, 600);
	    table = new JTable();
	    
	    table.addMouseListener(new java.awt.event.MouseAdapter() {
	        @Override
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            int row = table.rowAtPoint(evt.getPoint());
	            int col = table.columnAtPoint(evt.getPoint());
	            String name; 
        		String address; 
        		String working_hours;
        		String closing_days;
        		String password;
        	    if (row >= 0 && col >= 0) {
        	    	name = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
        	    	address = table.getModel().getValueAt(table.getSelectedRow(), 2).toString();
        	    	password = table.getModel().getValueAt(table.getSelectedRow(), 5).toString();
        	    	try
        	    	{
        			working_hours = table.getModel().getValueAt(table.getSelectedRow(), 3).toString();
        	    	}catch(Exception e)
        	    	{
        	    		working_hours = "";
        	    	}
        	    	try
        	    	{
        			closing_days = table.getModel().getValueAt(table.getSelectedRow(), 4).toString();
        	    	}catch(Exception e)
        	    	{
        	    		closing_days = "";
        	    	}
        			nameTF.setText(name);
        			addressTF.setText(address);
        			working_hoursTF.setText(working_hours);
        			closing_daysTF.setText(closing_days);
        			passwordTF.setText(password);
        	    }
        	}
        	});
	    
		scrollPane1.setViewportView(table);
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
		insert_sql_button.setBounds(1000, 669, 150,30);
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
		shops_tableT.setBounds(90, 20, 225,100);
		shop_panel.add(shops_tableT);

		JLabel nameLB = new JLabel("Name");
		nameLB.setBounds(1000, 173, 46, 14);
		shop_panel.add(nameLB);
		
		JLabel addressLB = new JLabel("Address");
		addressLB.setBounds(1000, 229, 159, 14);
		shop_panel.add(addressLB);
		
		JLabel working_hoursLB = new JLabel("Working Hours");
		working_hoursLB.setBounds(1000, 298, 126, 14);
		shop_panel.add(working_hoursLB);
		
		JLabel closing_daysLB = new JLabel("Closing Days");
		closing_daysLB.setBounds(1000, 368, 142, 14);
		shop_panel.add(closing_daysLB);
		
		JLabel passwordLB = new JLabel("Password");
		passwordLB.setBounds(1000, 429, 142, 14);
		shop_panel.add(passwordLB);
		
		nameTF = new RoundJTextField(new Color(0x771007));
		nameTF.setBounds(1000, 198, 240, 25);
		shop_panel.add(nameTF);
		
		addressTF = new RoundJTextField(new Color(0x771007));
		addressTF.setBounds(1000, 254, 240, 25);
		shop_panel.add(addressTF);
		
		working_hoursTF = new RoundJTextField(new Color(0x771007));
		working_hoursTF.setBounds(1000, 325, 240, 25);
		shop_panel.add(working_hoursTF);
		
		closing_daysTF = new RoundJTextField(new Color(0x771007));
		closing_daysTF.setBounds(1000, 393, 240, 25);
		shop_panel.add(closing_daysTF);
		
		passwordTF = new RoundJTextField(new Color(0x771007));
		passwordTF.setBounds(1000, 454, 240, 25);
		shop_panel.add(passwordTF);
	
		
		JButton go_back = new JButton();
		go_back.setBounds(90, 770, 50, 50);
		go_back.setIcon(go_back_inactive);
		go_back.setBorder(null);
		go_back.setFocusable(false);
		go_back.setContentAreaFilled(false);
		shop_panel.add(go_back);
		
		go_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ShopFrame.this.setVisible(false);
				admin_controller.openAdminFrame();
			
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				go_back.setIcon(go_back_active);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				go_back.setIcon(go_back_inactive);
				
			}
		});
		
		insert_sql_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.insert_refreshShopTable(ShopFrame.this);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				insert_sql_button.setIcon(insert_button_active);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				insert_sql_button.setIcon(insert_button_inactive);
				
			}
			
		});
		
		update_sql_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.update_refreshShopTable(ShopFrame.this);

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				update_sql_button.setIcon(update_button_active);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				update_sql_button.setIcon(update_button_inactive);
				
			}
		});
		
		delete_sql_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				admin_controller.delete_refreshShopTable(ShopFrame.this);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				delete_sql_button.setIcon(delete_button_active);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				delete_sql_button.setIcon(delete_button_inactive);
				
			}
		});
		
 
		topPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				ShopFrame.this.setLocation (ShopFrame.this.getX()+e.getX()-mouseX,ShopFrame.this.getY()+e.getY()-mouseY);
				
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
				
				if(ShopFrame.this.getSize().equals(new Dimension(1600,900))) {
					
					resizeFrameButton.setIcon(minimize_button);
					ShopFrame.this.setLocation(central_width, central_height);
					ShopFrame.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
					
					scrollPane1.setBounds(dim.width/2-600, 230, 1200, 700);
					shops_tableT.setBounds(dim.width/2-600, 80, 225,100);
					go_back.setBounds(25, dim.height-100, 50, 50);
					
					nameLB.setBounds(dim.width/2-300, 50, 46, 14);
					nameTF.setBounds(dim.width/2-300, 70, 240, 25);
					addressLB.setBounds(dim.width/2, 50, 50, 14);
					addressTF.setBounds(dim.width/2, 70, 240, 25);
					working_hoursLB.setBounds(dim.width/2-300, 100, 100, 14);
					working_hoursTF.setBounds(dim.width/2-300, 120, 240, 25);
					closing_daysLB.setBounds(dim.width/2, 100, 100, 14);
					closing_daysTF.setBounds(dim.width/2, 120, 240, 25);
					passwordLB.setBounds(dim.width/2-300, 150, 100, 14);
					passwordTF.setBounds(dim.width/2-300, 170, 240, 25);
					
					insert_sql_button.setBounds(dim.width/2+450, 67, 150,30);
					update_sql_button.setBounds(dim.width/2+450, 117, 150,30);
					delete_sql_button.setBounds(dim.width/2+450, 167, 150,30);
					
				} else if(ShopFrame.this.getSize().equals(dim)) {
					
					resizeFrameButton.setIcon(maximize_button);
					ShopFrame.this.setSize(1600,900);
					ShopFrame.this.setLocation(central_width, central_height);
					
					scrollPane1.setBounds(90, 132, 850, 600);
					shops_tableT.setBounds(90, 20, 225,100);
					go_back.setBounds(90, 770, 50, 50);
					
					nameLB.setBounds(1000, 173, 46, 14);
					nameTF.setBounds(1000, 198, 240, 25);
					addressLB.setBounds(1000, 229, 159, 14);
					addressTF.setBounds(1000, 254, 240, 25);
					working_hoursLB.setBounds(1000, 298, 126, 14);
					working_hoursTF.setBounds(1000, 325, 240, 25);
					closing_daysLB.setBounds(1000, 368, 142, 14);
					closing_daysTF.setBounds(1000, 393, 240, 25);
					passwordLB.setBounds(1000, 429, 142, 14);
					passwordTF.setBounds(1000, 454, 240, 25);
					
					insert_sql_button.setBounds(984, 669, 150,30);
					update_sql_button.setBounds(1200, 669, 150,30);
					delete_sql_button.setBounds(1400, 669, 150,30);
					
				}
				
			}
		});
		iconifierFrameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ShopFrame.this.setState(Frame.ICONIFIED);
				
			}
		});
	}
	
	public JTable getTable() {
		return table;
	}
	
	public JTextField getNameTF() {
		return nameTF;
	}

	public JTextField getAddressTF() {
		return addressTF;
	}

	public JTextField getWorking_hoursTF() {
		return working_hoursTF;
	}

	public JTextField getClosing_daysTF() {
		return closing_daysTF;
	}
	public JTextField getPasswordTF() {
		return passwordTF;
	}

}



