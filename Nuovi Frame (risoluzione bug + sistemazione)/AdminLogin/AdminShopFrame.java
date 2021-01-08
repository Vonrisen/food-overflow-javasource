package guisssss;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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

import gui_support.RoundJTextField;

public class AdminShopFrame {

	private JFrame frame;

	private int mouseX=0;
	private int mouseY=0;
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	ImageIcon maximize_buttonIMG;
	ImageIcon minimize_buttonIMG;
	ImageIcon close_buttonIMG;
	ImageIcon iconifier_buttonIMG;
	ImageIcon logoIMG;
	ImageIcon shops_table_title;
	ImageIcon delete_button_inactiveIMG;
	ImageIcon delete_button_activeIMG;
	ImageIcon insert_button_inactiveIMG;
	ImageIcon insert_button_activeIMG;
	ImageIcon update_button_inactiveIMG;
	ImageIcon update_button_activeIMG;
	ImageIcon go_back_inactiveIMG;
	ImageIcon go_back_activeIMG;
	
	private JTable tableJT;
	private JScrollPane scrollPaneJSP;
	
	private JTextField nameTF;
	private JTextField addressTF;
	private JTextField working_hoursTF;
	private JTextField closing_daysTF;
	private JTextField passwordTF;
	
	JPanel top_panelJP;
	JPanel shop_panelJP;
	
	JLabel iconifierJB;
	JLabel resizeJB;
	JLabel close_frameJB;
	JLabel logoLB;
	JLabel shops_table_titleLB;
	JLabel nameLB;
	JLabel addressLB;
	JLabel working_hoursLB;
	JLabel closing_daysLB;
	JLabel passwordLB;
	
	JButton insert_sqlJB;
	JButton update_sqlJB;
	JButton delete_sqlJB;
	JButton go_backJB;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminShopFrame window = new AdminShopFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminShopFrame() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		
		//Definizione variabili ImageIcon
		
		maximize_buttonIMG = new ImageIcon("src\\images\\FrameIcons\\maximizeFrameButton.png");
		minimize_buttonIMG = new ImageIcon("src\\images\\FrameIcons\\minimizeFrameButton.png");
		close_buttonIMG = new ImageIcon("src\\images\\FrameIcons\\closeFrameButton.png");
		iconifier_buttonIMG = new ImageIcon("src\\images\\FrameIcons\\iconifierFrameButton.png");
		logoIMG = new ImageIcon("src\\images\\FrameIcons\\logoIcon.png");
		shops_table_title = new ImageIcon("src\\images\\tableTitles\\shops.png");
		delete_button_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonInactive.png");
		delete_button_activeIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonActive.png");
		insert_button_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\insertButtonInactive.png");
		insert_button_activeIMG = new ImageIcon("src\\images\\SqlButtons\\insertButtonActive.png");
		update_button_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\updateButtonInactive.png");
		update_button_activeIMG = new ImageIcon("src\\images\\SqlButtons\\updateButtonActive.png");
		go_back_inactiveIMG = new ImageIcon("src\\images\\AdminFrame\\turnBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\AdminFrame\\turnBackActive.png");
		
		//Definizione variabili JPanel e JTable
		
		top_panelJP = new JPanel();
		shop_panelJP = new JPanel();
		
		scrollPaneJSP = new JScrollPane();
		tableJT = new JTable();
		
		//Definizione variabili JLabel e JTextField
		
		shops_table_titleLB = new JLabel();
		nameLB = new JLabel("Name");
		addressLB = new JLabel("Address");
		working_hoursLB = new JLabel("Working Hours");
		closing_daysLB = new JLabel("Closing Days");
		passwordLB = new JLabel("Password");
		logoLB = new JLabel("<html><font face='Calibri' size='4' color=rgb(243,236,215)>FOOD OVERFLOW:</font> <font face='Calibri' size='4' color=rgb(244,153,105)>ADMIN PANEL (SHOP)</font></html>");
		
		nameTF = new RoundJTextField(new Color(0x771007));
		addressTF = new RoundJTextField(new Color(0x771007));
		working_hoursTF = new RoundJTextField(new Color(0x771007));
		closing_daysTF = new RoundJTextField(new Color(0x771007));
		passwordTF = new RoundJTextField(new Color(0x771007));
		
		//Definizione variabili JButton
		
		insert_sqlJB = new JButton();
		update_sqlJB = new JButton();
		delete_sqlJB = new JButton();
		go_backJB = new JButton();
		iconifierJB = new JLabel();
		resizeJB = new JLabel();
		close_frameJB = new JLabel();
				
		//Settiamo il frame
		
		frame.setSize(1600,900);
		int central_width = dim.width/2-frame.getSize().width/2;
		int central_height = dim.height/2-frame.getSize().height/2;
		frame.setLocation(central_width, central_height); //Setta il frame a centro monitor
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(new BorderLayout());
		
		//Impostiamo il layout

		top_panelJP.setLayout(new BoxLayout(top_panelJP, BoxLayout.LINE_AXIS));
		top_panelJP.setPreferredSize(new Dimension(100,35));
		top_panelJP.setBackground(new Color(0x771007));
		top_panelJP.setVisible(true);
		frame.add(top_panelJP, BorderLayout.NORTH);	
		
		iconifierJB.setIcon(iconifier_buttonIMG);
		resizeJB.setIcon(maximize_buttonIMG);
		close_frameJB.setIcon(close_buttonIMG);
		
		logoLB.setForeground(new Color(0xf3ecd7));;
		logoLB.setIcon(logoIMG);
		top_panelJP.add(logoLB);
		top_panelJP.add(Box.createHorizontalGlue());
		top_panelJP.add(iconifierJB);
		top_panelJP.add(resizeJB);
		top_panelJP.add(close_frameJB);
		
		shop_panelJP.setLayout(null);
		shop_panelJP.setBackground(new Color(0xf3ecd7));
		frame.add(shop_panelJP, BorderLayout.CENTER);
		
		shop_panelJP.add(scrollPaneJSP);
		scrollPaneJSP.setBounds(90, 132, 850, 600);
		scrollPaneJSP.setViewportView(tableJT);
		tableJT.setFocusable(false);
		tableJT.setEnabled(true);
		tableJT.getTableHeader().setReorderingAllowed(false);
		tableJT.getTableHeader().setBackground(Color.black);
		tableJT.getTableHeader().setForeground(Color.yellow);
		tableJT.setBackground(Color.blue);
		tableJT.setFont(new Font("", 1, 10));
		tableJT.setForeground(Color.white);
		
		//Definizione contenuto shop_panelJP

		insert_sqlJB.setIcon(insert_button_inactiveIMG);
		insert_sqlJB.setBounds(1000, 669, 150,30);
		insert_sqlJB.setBorder(null);
		insert_sqlJB.setFocusable(false);
		insert_sqlJB.setContentAreaFilled(false);
		shop_panelJP.add(insert_sqlJB);
		

		update_sqlJB.setIcon(update_button_inactiveIMG);
		update_sqlJB.setBounds(1200, 669, 150,30);
		update_sqlJB.setBorder(null);
		update_sqlJB.setFocusable(false);
		update_sqlJB.setContentAreaFilled(false);
		shop_panelJP.add(update_sqlJB);
		

		delete_sqlJB.setIcon(delete_button_inactiveIMG);
		delete_sqlJB.setBounds(1400, 669, 150,30);
		delete_sqlJB.setBorder(null);
		delete_sqlJB.setFocusable(false);
		delete_sqlJB.setContentAreaFilled(false);
		shop_panelJP.add(delete_sqlJB);
		
		shops_table_titleLB.setIcon(shops_table_title);
		shops_table_titleLB.setBounds(90, 20, 225,100);
		shop_panelJP.add(shops_table_titleLB);

		nameLB.setBounds(1000, 173, 46, 14);
		shop_panelJP.add(nameLB);
		
		addressLB.setBounds(1000, 229, 159, 14);
		shop_panelJP.add(addressLB);
		
		working_hoursLB.setBounds(1000, 298, 126, 14);
		shop_panelJP.add(working_hoursLB);
		
		closing_daysLB.setBounds(1000, 368, 142, 14);
		shop_panelJP.add(closing_daysLB);
		
		passwordLB.setBounds(1000, 429, 142, 14);
		shop_panelJP.add(passwordLB);
		
		nameTF.setBounds(1000, 198, 240, 25);
		shop_panelJP.add(nameTF);
		
		addressTF.setBounds(1000, 254, 240, 25);
		shop_panelJP.add(addressTF);
		
		working_hoursTF.setBounds(1000, 325, 240, 25);
		shop_panelJP.add(working_hoursTF);
		
		closing_daysTF.setBounds(1000, 393, 240, 25);
		shop_panelJP.add(closing_daysTF);
		
		passwordTF.setBounds(1000, 454, 240, 25);
		shop_panelJP.add(passwordTF);
	
		go_backJB.setBounds(90, 770, 50, 50);
		go_backJB.setIcon(go_back_inactiveIMG);
		go_backJB.setBorder(null);
		go_backJB.setFocusable(false);
		go_backJB.setContentAreaFilled(false);
		shop_panelJP.add(go_backJB);
		
		//Listeners
		
	    tableJT.addMouseListener(new java.awt.event.MouseAdapter() {
	        @Override
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            int row = tableJT.rowAtPoint(evt.getPoint());
	            int col = tableJT.columnAtPoint(evt.getPoint());
	            String name; 
        		String address; 
        		String working_hours;
        		String closing_days;
        		String password;
        	    if (row >= 0 && col >= 0) {
        	    	name = tableJT.getModel().getValueAt(tableJT.getSelectedRow(), 1).toString();
        	    	address = tableJT.getModel().getValueAt(tableJT.getSelectedRow(), 2).toString();
        	    	password = tableJT.getModel().getValueAt(tableJT.getSelectedRow(), 5).toString();
        	    	try
        	    	{
        			working_hours = tableJT.getModel().getValueAt(tableJT.getSelectedRow(), 3).toString();
        	    	}catch(Exception e)
        	    	{
        	    		working_hours = "";
        	    	}
        	    	try
        	    	{
        			closing_days = tableJT.getModel().getValueAt(tableJT.getSelectedRow(), 4).toString();
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
	    
		go_backJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				//Apre AdminFrame
			
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
		
		insert_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//INSERT
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				insert_sqlJB.setIcon(insert_button_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				insert_sqlJB.setIcon(insert_button_inactiveIMG);
				
			}
			
		});
		
		update_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//UPDATE

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				update_sqlJB.setIcon(update_button_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				update_sqlJB.setIcon(update_button_inactiveIMG);
				
			}
		});
		
		delete_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				//DELETE
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				delete_sqlJB.setIcon(delete_button_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				delete_sqlJB.setIcon(delete_button_inactiveIMG);
				
			}
		});
		
 
		top_panelJP.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				frame.setLocation (frame.getX()+e.getX()-mouseX,frame.getY()+e.getY()-mouseY);
				
			}
		});
		top_panelJP.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				mouseX = e.getX();
				mouseY = e.getY();
				
				}
		});
		
		close_frameJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.exit(0); // Da rivedere la chiusura del frame
				
			}
		});
		
		resizeJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(frame.getSize().equals(new Dimension(1600,900))) {
					
					resizeJB.setIcon(minimize_buttonIMG);
					frame.setLocation(central_width, central_height);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					
					scrollPaneJSP.setBounds(dim.width/2-600, 230, 1200, 700);
					shops_table_titleLB.setBounds(dim.width/2-600, 80, 225,100);
					go_backJB.setBounds(25, dim.height-100, 50, 50);
					
					nameLB.setBounds(dim.width/2-300, 50, 46, 14);
					addressLB.setBounds(dim.width/2, 50, 50, 14);
					working_hoursLB.setBounds(dim.width/2-300, 100, 100, 14);
					closing_daysLB.setBounds(dim.width/2, 100, 100, 14);
					passwordLB.setBounds(dim.width/2-300, 150, 100, 14);
					
					nameTF.setBounds(dim.width/2-300, 70, 240, 25);
					addressTF.setBounds(dim.width/2, 70, 240, 25);
					working_hoursTF.setBounds(dim.width/2-300, 120, 240, 25);
					closing_daysTF.setBounds(dim.width/2, 120, 240, 25);
					passwordTF.setBounds(dim.width/2-300, 170, 240, 25);
					
					insert_sqlJB.setBounds(dim.width/2+450, 67, 150,30);
					update_sqlJB.setBounds(dim.width/2+450, 117, 150,30);
					delete_sqlJB.setBounds(dim.width/2+450, 167, 150,30);
					
				} else if(frame.getSize().equals(dim)) {
					
					resizeJB.setIcon(maximize_buttonIMG);
					frame.setSize(1600,900);
					frame.setLocation(central_width, central_height);
					
					scrollPaneJSP.setBounds(90, 132, 850, 600);
					shops_table_titleLB.setBounds(90, 20, 225,100);
					go_backJB.setBounds(90, 770, 50, 50);
					
					nameLB.setBounds(1000, 173, 46, 14);
					addressLB.setBounds(1000, 229, 159, 14);
					working_hoursLB.setBounds(1000, 298, 126, 14);
					closing_daysLB.setBounds(1000, 368, 142, 14);
					passwordLB.setBounds(1000, 429, 142, 14);
					
					nameTF.setBounds(1000, 198, 240, 25);
					addressTF.setBounds(1000, 254, 240, 25);
					working_hoursTF.setBounds(1000, 325, 240, 25);
					closing_daysTF.setBounds(1000, 393, 240, 25);
					passwordTF.setBounds(1000, 454, 240, 25);
					
					insert_sqlJB.setBounds(984, 669, 150,30);
					update_sqlJB.setBounds(1200, 669, 150,30);
					delete_sqlJB.setBounds(1400, 669, 150,30);
					
				}
				
			}
		});
		iconifierJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				frame.setState(Frame.ICONIFIED);
				
			}
		});
		
	}

}
