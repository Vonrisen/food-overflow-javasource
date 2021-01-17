package gui;

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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import gui_support.RoundJRadioButton;
import gui_support.RoundJTextField;

public class AdminCustomerFrame {

	private JFrame frame;

	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private int mouseX=0;
	private int mouseY=0;
	
	ImageIcon maximize_buttonIMG;
	ImageIcon minimize_buttonIMG;
	ImageIcon close_buttonIMG;
	ImageIcon iconifier_buttonIMG;
	ImageIcon logoIMG;
	ImageIcon customers_table_titleIMG;
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
	
	public JTextField nameTF;
	private JTextField surnameTF;
	private JTextField birth_dateTF;
	private JTextField birth_placeTF;
	private JTextField addressTF;
	private JTextField emailTF;
	private JTextField passwordTF;
	private JTextField cellphoneTF;
	private ButtonGroup genderBG;
	
	JPanel top_panelJP;
	JPanel customer_panelJP;
	
	JLabel iconifierLB;
	JLabel resizeJB;
	JLabel close_frameJB;
	JLabel logoLB;
	JLabel customers_table_titleLB;
	JLabel nameLB;
	JLabel surnameLB;
	JLabel birth_dateLB;
	JLabel birth_placeLB;
	JLabel addressLB;
	JLabel genderLB;
	JLabel cellphoneLB;
	JLabel emailLB;
	JLabel passwordLB;
	JLabel gender_boxLB;
	
	JButton go_backJB;
	JButton insert_sqlJB;
	JButton update_sqlJB;
	JButton delete_sqlJB;
	
	JRadioButton gender_maleJRB;
	JRadioButton gender_femaleJRB;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminCustomerFrame window = new AdminCustomerFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminCustomerFrame() {
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
		customers_table_titleIMG = new ImageIcon("src\\images\\tableTitles\\customers.png");
		delete_button_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonInactive.png");
		delete_button_activeIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonActive.png");
		insert_button_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\insertButtonInactive.png");
		insert_button_activeIMG = new ImageIcon("src\\images\\SqlButtons\\insertButtonActive.png");
		update_button_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\updateButtonInactive.png");
		update_button_activeIMG = new ImageIcon("src\\images\\SqlButtons\\updateButtonActive.png");
		go_back_inactiveIMG = new ImageIcon("src\\images\\AdminFrame\\turnBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\AdminFrame\\turnBackActive.png");
		
		//Definizione variabili JLabel e JTextField
		

		logoLB = new JLabel("<html><font face='Calibri' size='4' color=rgb(243,236,215)>FOOD OVERFLOW:</font> <font face='Calibri' size='4' color=rgb(244,153,105)>ADMIN PANEL (CUSTOMER)</font></html>");
		customers_table_titleLB = new JLabel();
		nameLB = new JLabel("Name");
		surnameLB = new JLabel("Surname");
		birth_dateLB = new JLabel("Birth Date");
		birth_placeLB = new JLabel("Birth Place");
		addressLB = new JLabel("Address");
		genderLB = new JLabel("Gender");
		cellphoneLB = new JLabel("Cellphone");
		emailLB = new JLabel("E-mail");
		passwordLB = new JLabel("Password");
		
		nameTF = new RoundJTextField(new Color(0x771007));
		surnameTF = new RoundJTextField(new Color(0x771007));
		birth_dateTF = new RoundJTextField(new Color(0x771007));
		birth_placeTF = new RoundJTextField(new Color(0x771007));
		addressTF = new RoundJTextField(new Color(0x771007));
		cellphoneTF = new RoundJTextField(new Color(0x771007));
		emailTF = new RoundJTextField(new Color(0x771007));
		passwordTF = new RoundJTextField(new Color(0x771007));
		
		// Definizione variabili JPanel e JTable
		
		top_panelJP = new JPanel();
		customer_panelJP = new JPanel();
		scrollPaneJSP = new JScrollPane();
		tableJT = new JTable();
		
		//Definizione altre variabili
		
		go_backJB = new JButton();
		insert_sqlJB = new JButton();
		update_sqlJB = new JButton();
		delete_sqlJB = new JButton();
		iconifierLB = new JLabel();
		resizeJB = new JLabel();
		close_frameJB = new JLabel();
		
		genderBG = new ButtonGroup();
		gender_boxLB = new RoundJRadioButton(new Color(0x771007));
		gender_maleJRB = new JRadioButton();
		gender_femaleJRB = new JRadioButton();
		
		//Settiamo il Frame
		
		frame.setSize(1600,900);
		int central_width = dim.width/2-frame.getSize().width/2;
		int central_height = dim.height/2-frame.getSize().height/2;
		frame.setLocation(central_width, central_height); //Setta il frame a centro monitor
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(new BorderLayout());
		
		// Impostiamo il layout
		
		top_panelJP.setLayout(new BoxLayout(top_panelJP, BoxLayout.LINE_AXIS));
		top_panelJP.setPreferredSize(new Dimension(100,35));
		top_panelJP.setBackground(new Color(0x771007));
		top_panelJP.setVisible(true);
		frame.getContentPane().add(top_panelJP, BorderLayout.NORTH);	
		

		iconifierLB.setIcon(iconifier_buttonIMG);
		resizeJB.setIcon(maximize_buttonIMG);
		close_frameJB.setIcon(close_buttonIMG);
		
		logoLB.setForeground(new Color(0xf3ecd7));;
		logoLB.setIcon(logoIMG);
		top_panelJP.add(logoLB);
		top_panelJP.add(Box.createHorizontalGlue());
		top_panelJP.add(iconifierLB);
		top_panelJP.add(resizeJB);
		top_panelJP.add(close_frameJB);
	    
		customer_panelJP.setLayout(null);
		customer_panelJP.setBackground(new Color(0xf3ecd7));
		frame.getContentPane().add(customer_panelJP, BorderLayout.CENTER);
		
		customer_panelJP.add(scrollPaneJSP);
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
		
		
		//Definizione contenuto customer_panelJP

		customers_table_titleLB.setIcon(customers_table_titleIMG);
		customers_table_titleLB.setBounds(90, 20, 250,100);
		customer_panelJP.add(customers_table_titleLB);

		nameLB.setBounds(1000, 132, 100, 14);
		customer_panelJP.add(nameLB);
		
		surnameLB.setBounds(1200, 132, 100, 14);
		customer_panelJP.add(surnameLB);
		
		birth_dateLB.setBounds(1000, 188, 100, 14);
		customer_panelJP.add(birth_dateLB);
		
		birth_placeLB.setBounds(1200, 188, 100, 14);
		customer_panelJP.add(birth_placeLB);
		
		addressLB.setBounds(1000, 249, 100, 14);
		customer_panelJP.add(addressLB);
		
		genderLB.setBounds(1000, 310, 100, 14);
		customer_panelJP.add(genderLB);
		
		cellphoneLB.setBounds(1200, 310, 100, 14);
		customer_panelJP.add(cellphoneLB);
		
		emailLB.setBounds(1000, 465, 100, 14);
		customer_panelJP.add(emailLB);
		
		passwordLB.setBounds(1200, 465, 100, 14);
		customer_panelJP.add(passwordLB);
		
		nameTF.setBounds(1000, 152, 150, 25);
		customer_panelJP.add(nameTF);
		
		surnameTF.setBounds(1200, 152, 150, 25);
		customer_panelJP.add(surnameTF);
		
		birth_dateTF.setBounds(1000, 213, 150, 25);
		customer_panelJP.add(birth_dateTF);
		
		birth_placeTF.setBounds(1200, 213, 150, 25);
		customer_panelJP.add(birth_placeTF);
		
		addressTF.setBounds(1000, 274, 350, 25);
		customer_panelJP.add(addressTF);
		
		gender_maleJRB.setText("Maschio");
		gender_maleJRB.setBounds(1005, 335, 75, 25);
		gender_maleJRB.setBorder(new LineBorder(null,5));
		gender_maleJRB.setContentAreaFilled(false);
		gender_maleJRB.setFocusable(false);
		customer_panelJP.add(gender_maleJRB);
		genderBG.add(gender_maleJRB);

		gender_femaleJRB.setText("Femmina");
		gender_femaleJRB.setBounds(1080, 335, 80, 25);
		gender_femaleJRB.setBorder(new LineBorder(null,5));
		gender_femaleJRB.setContentAreaFilled(false);
		gender_femaleJRB.setFocusable(false);
		customer_panelJP.add(gender_femaleJRB);
		genderBG.add(gender_femaleJRB);
		
		gender_boxLB.setBounds(1000, 335, 165, 25);
		customer_panelJP.add(gender_boxLB);
		
		cellphoneTF.setBounds(1200, 335, 150, 25);
		customer_panelJP.add(cellphoneTF);
		
		emailTF.setBounds(1000, 490, 150, 25);
		customer_panelJP.add(emailTF);
	
		passwordTF.setBounds(1200, 490, 150, 25);
		customer_panelJP.add(passwordTF);
		
		insert_sqlJB.setIcon(insert_button_inactiveIMG);
		insert_sqlJB.setBounds(1000, 600, 150,30);
		insert_sqlJB.setBorder(null);
		insert_sqlJB.setFocusable(false);
		insert_sqlJB.setContentAreaFilled(false);
		customer_panelJP.add(insert_sqlJB);
		
		update_sqlJB.setIcon(update_button_inactiveIMG);
		update_sqlJB.setBounds(1200, 600, 150,30);
		update_sqlJB.setBorder(null);
		update_sqlJB.setFocusable(false);
		update_sqlJB.setContentAreaFilled(false);
		customer_panelJP.add(update_sqlJB);
		
		delete_sqlJB.setIcon(delete_button_inactiveIMG);
		delete_sqlJB.setBounds(1000, 660, 150,30);
		delete_sqlJB.setBorder(null);
		delete_sqlJB.setFocusable(false);
		delete_sqlJB.setContentAreaFilled(false);
		customer_panelJP.add(delete_sqlJB);
		
		go_backJB.setBounds(90, 770, 50, 50);
		go_backJB.setIcon(go_back_inactiveIMG);
		go_backJB.setBorder(null);
		go_backJB.setFocusable(false);
		go_backJB.setContentAreaFilled(false);
		customer_panelJP.add(go_backJB);
		
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
					customers_table_titleLB.setBounds(dim.width/2-600, 80, 250,100);
					go_backJB.setBounds(25, dim.height-100, 50, 50);
					
					nameLB.setBounds(dim.width/2-300, 50, 100, 14);
					nameTF.setBounds(dim.width/2-300, 70, 150, 25);
					surnameLB.setBounds(dim.width/2-100, 50, 100, 14);
					surnameTF.setBounds(dim.width/2-100, 70, 150, 25);
					birth_dateLB.setBounds(dim.width/2+100, 50, 100, 14);
					birth_dateTF.setBounds(dim.width/2+100, 70, 150, 25);
					birth_placeLB.setBounds(dim.width/2+100, 100, 100, 14);
					birth_placeTF.setBounds(dim.width/2+100, 120, 150, 25);
					addressLB.setBounds(dim.width/2-300, 100, 100, 14);
					addressTF.setBounds(dim.width/2-300, 120, 170, 25);
					genderLB.setBounds(dim.width/2-100, 100, 100, 14);
					gender_maleJRB.setBounds(dim.width/2-98, 120, 100, 25);
					gender_femaleJRB.setBounds(dim.width/2-28, 120, 100, 25);
					gender_boxLB.setBounds(dim.width/2-100, 120, 150, 25);
					cellphoneLB.setBounds(dim.width/2-300, 150, 100, 14);
					cellphoneTF.setBounds(dim.width/2-300, 170, 150, 25);
					emailLB.setBounds(dim.width/2-100, 150, 100, 14);
					emailTF.setBounds(dim.width/2-100, 170, 150, 25);
					passwordLB.setBounds(dim.width/2+100, 150, 100, 14);
					passwordTF.setBounds(dim.width/2+100, 170, 150, 25);
					
					insert_sqlJB.setBounds(dim.width/2+450, 67, 150,30);
					update_sqlJB.setBounds(dim.width/2+450, 117, 150,30);
					delete_sqlJB.setBounds(dim.width/2+450, 167, 150,30);
					
				} else if(frame.getSize().equals(dim)) {
					
					resizeJB.setIcon(maximize_buttonIMG);
					frame.setSize(1600,900);
					frame.setLocation(central_width, central_height);
					
					scrollPaneJSP.setBounds(90, 132, 850, 600);
					customers_table_titleLB.setBounds(90, 20, 250,100);
					go_backJB.setBounds(90, 770, 50, 50);
					
					nameLB.setBounds(1000, 132, 100, 14);
					surnameLB.setBounds(1200, 132, 100, 14);
					birth_dateLB.setBounds(1000, 188, 100, 14);
					birth_placeLB.setBounds(1200, 188, 100, 14);
					addressLB.setBounds(1000, 249, 100, 14);
					genderLB.setBounds(1000, 310, 100, 14);
					cellphoneLB.setBounds(1200, 310, 100, 14);
					nameTF.setBounds(1000, 152, 150, 25);
					surnameTF.setBounds(1200, 152, 150, 25);
					birth_dateTF.setBounds(1000, 213, 150, 25);
					birth_placeTF.setBounds(1200, 213, 150, 25);
					addressTF.setBounds(1000, 274, 350, 25);
					gender_maleJRB.setBounds(1005, 335, 75, 25);
					gender_femaleJRB.setBounds(1080, 335, 80, 25);
					gender_boxLB.setBounds(1000, 335, 165, 25);
					cellphoneTF.setBounds(1200, 335, 150, 25);
					emailLB.setBounds(1000, 465, 100, 14);
					passwordLB.setBounds(1200, 465, 100, 14);
					emailTF.setBounds(1000, 490, 150, 25);
					passwordTF.setBounds(1200, 490, 150, 25);
					
					insert_sqlJB.setBounds(1000, 600, 150,30);
					update_sqlJB.setBounds(1200, 600, 150,30);
					delete_sqlJB.setBounds(1000, 660, 150,30);
					
				}
				
			}
		});
		
		iconifierLB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				frame.setState(Frame.ICONIFIED);
				
			}
		});
		
	}

}
