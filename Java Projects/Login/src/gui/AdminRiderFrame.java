package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class AdminRiderFrame {

	private JFrame frame;

	private int mouseX=0;
	private int mouseY=0;
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	ImageIcon maximize_buttonIMG;
	ImageIcon minimize_buttonIMG;
	ImageIcon close_buttonIMG;
	ImageIcon iconifier_buttonIMG;
	ImageIcon logoIMG;
	ImageIcon riders_table_titleIMG;
	ImageIcon delete_button_inactiveIMG;
	ImageIcon delete_button_activeIMG;
	ImageIcon insert_button_inactiveIMG;
	ImageIcon insert_button_activeIMG;
	ImageIcon update_button_inactiveIMG;
	ImageIcon update_button_activeIMG;
	ImageIcon go_back_inactiveIMG;
	ImageIcon go_back_activeIMG;
	
	JPanel top_panelJP;
	JPanel rider_panelJP;
	
	private JTable tableJT;
	private JScrollPane scrollPaneJSP;
	
	private JTextField nameTF;
	private JTextField surnameTF;
	private JTextField birth_dateTF;
	private JTextField birth_placeTF;
	private JTextField addressTF;
	private JTextField cellphoneTF;
	private JTextField working_timeTF;
	private JTextField shop_idTF;
	
	private ButtonGroup genderBG;
	JRadioButton gender_maleJRB;
	JRadioButton gender_femaleJRB;
	
	String[] vehicleStrings = {"Bicicletta", "Motoveicolo", "Autoveicolo"};
	private JComboBox<String> vehicleJCB;
	
	JLabel shops_table_titleLB;
	JLabel nameLB;
	JLabel surnameLB;
	JLabel birth_dateLB;
	JLabel birth_placeLB;
	JLabel addressLB;
	JLabel genderLB;
	JLabel cellphoneLB;
	JLabel vehicleLB;
	JLabel working_timeLB;
	JLabel shop_idLB;
	JLabel logoLB;
	JLabel gender_boxLB;
	
	JLabel iconifierJB;
	JLabel resizeJB;
	JLabel close_frameLB;
	
	JButton go_backJB;
	JButton insert_sqlJB;
	JButton update_sqlJB;
	JButton delete_sqlJB;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminRiderFrame window = new AdminRiderFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminRiderFrame() {
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
		riders_table_titleIMG = new ImageIcon("src\\images\\tableTitles\\riders.png");
		delete_button_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonInactive.png");
		delete_button_activeIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonActive.png");
		insert_button_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\insertButtonInactive.png");
		insert_button_activeIMG = new ImageIcon("src\\images\\SqlButtons\\insertButtonActive.png");
		update_button_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\updateButtonInactive.png");
		update_button_activeIMG = new ImageIcon("src\\images\\SqlButtons\\updateButtonActive.png");
		go_back_inactiveIMG = new ImageIcon("src\\images\\AdminFrame\\turnBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\AdminFrame\\turnBackActive.png");
		
		//Variabili JLabel
		
		shops_table_titleLB = new JLabel();
		nameLB = new JLabel("Name");
		surnameLB = new JLabel("Surname");
		birth_dateLB = new JLabel("Birth Date");
		birth_placeLB = new JLabel("Birth Place");
		addressLB = new JLabel("Address");
		genderLB = new JLabel("Gender");
		cellphoneLB = new JLabel("Cellphone");
		vehicleLB = new JLabel("Vehicle");
		working_timeLB = new JLabel("Working Time");
		shop_idLB = new JLabel("Shop ID");
		iconifierJB = new JLabel();
		resizeJB = new JLabel();
		close_frameLB = new JLabel();
		logoLB = new JLabel("<html><font face='Calibri' size='4' color=rgb(243,236,215)>FOOD OVERFLOW:</font> <font face='Calibri' size='4' color=rgb(244,153,105)>ADMIN PANEL (RIDER)</font></html>");
			
		//Variabili JRadioButton e JComboBox e JScrollPane
		
		gender_boxLB = new RoundJRadioButton(new Color(0x771007));
		gender_maleJRB = new JRadioButton();
		gender_femaleJRB = new JRadioButton();
		
		vehicleJCB = new JComboBox(vehicleStrings);
		scrollPaneJSP = new JScrollPane();
			
		//Variabili JButton
		go_backJB = new JButton();
		insert_sqlJB = new JButton();
		update_sqlJB = new JButton();
		delete_sqlJB = new JButton();
		
		//Variabili JPanel
		
		top_panelJP = new JPanel();
		rider_panelJP = new JPanel();
		
		//Variabili JTextFields e dei vari input
		
		nameTF = new RoundJTextField(new Color(0x771007));
		surnameTF = new RoundJTextField(new Color(0x771007));
		birth_dateTF = new RoundJTextField(new Color(0x771007));
		birth_placeTF = new RoundJTextField(new Color(0x771007));
		addressTF = new RoundJTextField(new Color(0x771007));
		genderBG = new ButtonGroup();
		
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
		frame.getContentPane().add(top_panelJP, BorderLayout.NORTH);	
		

		iconifierJB.setIcon(iconifier_buttonIMG);
		resizeJB.setIcon(maximize_buttonIMG);
		close_frameLB.setIcon(close_buttonIMG);
		
		logoLB.setForeground(new Color(0xf3ecd7));;
		logoLB.setIcon(logoIMG);
		top_panelJP.add(logoLB);
		top_panelJP.add(Box.createHorizontalGlue());
		top_panelJP.add(iconifierJB);
		top_panelJP.add(resizeJB);
		top_panelJP.add(close_frameLB);
		
		rider_panelJP.setLayout(null);
		rider_panelJP.setBackground(new Color(0xf3ecd7));
		frame.getContentPane().add(rider_panelJP, BorderLayout.CENTER);
		
		rider_panelJP.add(scrollPaneJSP);
		scrollPaneJSP.setBounds(90, 132, 850, 600);
	    tableJT = new JTable();
		scrollPaneJSP.setViewportView(tableJT);
		tableJT.setFocusable(false);
		tableJT.setEnabled(true);
		tableJT.getTableHeader().setReorderingAllowed(false);
		tableJT.getTableHeader().setBackground(Color.black);
		tableJT.getTableHeader().setForeground(Color.yellow);
		tableJT.setBackground(Color.blue);
		tableJT.setFont(new Font("", 1, 10));
		tableJT.setForeground(Color.white);
		
		shops_table_titleLB.setIcon(riders_table_titleIMG);
		shops_table_titleLB.setBounds(90, 20, 225,100);
		rider_panelJP.add(shops_table_titleLB);


		//Definizione JLabel
		
		nameLB.setBounds(1000, 132, 100, 14);
		rider_panelJP.add(nameLB);
		
		surnameLB.setBounds(1200, 132, 100, 14);
		rider_panelJP.add(surnameLB);
		
		birth_dateLB.setBounds(1000, 188, 100, 14);
		rider_panelJP.add(birth_dateLB);
		
		birth_placeLB.setBounds(1200, 188, 100, 14);
		rider_panelJP.add(birth_placeLB);
		
		addressLB.setBounds(1000, 249, 100, 14);
		rider_panelJP.add(addressLB);
		
		genderLB.setBounds(1000, 310, 100, 14);
		rider_panelJP.add(genderLB);
		
		cellphoneLB.setBounds(1200, 310, 100, 14);
		rider_panelJP.add(cellphoneLB);
		
		vehicleLB.setBounds(1000, 371, 100, 14);
		rider_panelJP.add(vehicleLB);
		
		working_timeLB.setBounds(1200, 371, 100, 14);
		rider_panelJP.add(working_timeLB);
		
		shop_idLB.setBounds(1200, 431, 46, 14);
		rider_panelJP.add(shop_idLB);
		
		//Definizione JTextField e dei vari input
		
		nameTF.setBounds(1000, 152, 150, 25);
		rider_panelJP.add(nameTF);
	
		surnameTF.setBounds(1200, 152, 150, 25);
		rider_panelJP.add(surnameTF);
		
		birth_dateTF.setBounds(1000, 213, 150, 25);
		rider_panelJP.add(birth_dateTF);
		
		birth_placeTF.setBounds(1200, 213, 150, 25);
		rider_panelJP.add(birth_placeTF);
		
		addressTF.setBounds(1000, 274, 350, 25);
		rider_panelJP.add(addressTF);
		
		shop_idTF = new RoundJTextField(new Color(0x771007));
		shop_idTF.setBounds(1200, 456, 150, 25);
		rider_panelJP.add(shop_idTF);
		
		cellphoneTF = new RoundJTextField(new Color(0x771007));
		cellphoneTF.setBounds(1200, 335, 150, 25);
		rider_panelJP.add(cellphoneTF);

		gender_maleJRB.setText("Maschio");
		gender_maleJRB.setActionCommand("M");
		gender_maleJRB.setSelected(true);
		gender_maleJRB.setBounds(1005, 335, 75, 25);
		gender_maleJRB.setBorder(new LineBorder(null,5));
		gender_maleJRB.setContentAreaFilled(false);
		gender_maleJRB.setFocusable(false);
		rider_panelJP.add(gender_maleJRB);
		
		gender_femaleJRB.setText("Femmina");
		gender_femaleJRB.setActionCommand("F");
		gender_femaleJRB.setBounds(1080, 335, 80, 25);
		gender_femaleJRB.setBorder(new LineBorder(null,5));
		gender_femaleJRB.setContentAreaFilled(false);
		gender_femaleJRB.setFocusable(false);
		rider_panelJP.add(gender_femaleJRB);
		
		genderBG.add(gender_maleJRB);
		genderBG.add(gender_femaleJRB);
		
		gender_boxLB.setBounds(1000, 335, 165, 25);
		rider_panelJP.add(gender_boxLB);
		
		vehicleJCB.setBounds(1000, 396, 150, 25);
		rider_panelJP.add(vehicleJCB);
		working_timeTF = new RoundJTextField(new Color(0x771007));
		working_timeTF.setBounds(1200, 396, 150, 25);
		rider_panelJP.add(working_timeTF);
		
		
		//Sistemazione JButtons
		
		insert_sqlJB.setIcon(insert_button_inactiveIMG);
		insert_sqlJB.setBounds(1000, 600, 150,30);
		insert_sqlJB.setBorder(null);
		insert_sqlJB.setFocusable(false);
		insert_sqlJB.setContentAreaFilled(false);
		rider_panelJP.add(insert_sqlJB);
		

		update_sqlJB.setIcon(update_button_inactiveIMG);
		update_sqlJB.setBounds(1200, 600, 150,30);
		update_sqlJB.setBorder(null);
		update_sqlJB.setFocusable(false);
		update_sqlJB.setContentAreaFilled(false);
		rider_panelJP.add(update_sqlJB);
		

		delete_sqlJB.setIcon(delete_button_inactiveIMG);
		delete_sqlJB.setBounds(1000, 660, 150,30);
		delete_sqlJB.setBorder(null);
		delete_sqlJB.setFocusable(false);
		delete_sqlJB.setContentAreaFilled(false);
		rider_panelJP.add(delete_sqlJB);

		go_backJB.setBounds(90, 770, 50, 50);
		go_backJB.setIcon(go_back_inactiveIMG);
		go_backJB.setBorder(null);
		go_backJB.setFocusable(false);
		go_backJB.setContentAreaFilled(false);
		rider_panelJP.add(go_backJB);

		
		//Listeners
		
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
				//Insert
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
				
				//Update
				
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
				
				//Delete
				
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
				
				frame.setLocation(frame.getX()+e.getX()-mouseX,frame.getY()+e.getY()-mouseY);
				
			}
		});
		top_panelJP.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				mouseX = e.getX();
				mouseY = e.getY();
				
				}
		});
		
		close_frameLB.addMouseListener(new MouseAdapter() {
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
					
					nameLB.setBounds(dim.width/2-300, 50, 100, 14);
					surnameLB.setBounds(dim.width/2-100, 50, 100, 14);
					birth_dateLB.setBounds(dim.width/2+100, 50, 100, 14);
					birth_placeLB.setBounds(dim.width/2+100, 100, 100, 14);
					addressLB.setBounds(dim.width/2-300, 100, 100, 14);
					genderLB.setBounds(dim.width/2-100, 100, 100, 14);
					gender_boxLB.setBounds(dim.width/2-100, 120, 150, 25);
					cellphoneLB.setBounds(dim.width/2-300, 150, 100, 14);
					vehicleLB.setBounds(dim.width/2-100, 150, 100, 14);
					working_timeLB.setBounds(dim.width/2+100, 150, 100, 14);
					shop_idLB.setBounds(dim.width/2+280, 50, 46, 14);
					
					nameTF.setBounds(dim.width/2-300, 70, 150, 25);
					surnameTF.setBounds(dim.width/2-100, 70, 150, 25);
					birth_dateTF.setBounds(dim.width/2+100, 70, 150, 25);
					birth_placeTF.setBounds(dim.width/2+100, 120, 150, 25);
					addressTF.setBounds(dim.width/2-300, 120, 170, 25);
					cellphoneTF.setBounds(dim.width/2-300, 170, 150, 25);
					working_timeTF.setBounds(dim.width/2+100, 170, 150, 25);
					shop_idTF.setBounds(dim.width/2+280, 70, 150, 25);

					gender_maleJRB.setBounds(dim.width/2-98, 120, 100, 25);
					gender_femaleJRB.setBounds(dim.width/2-28, 120, 100, 25);
					vehicleJCB.setBounds(dim.width/2-100, 170, 150, 25);
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
					
					nameLB.setBounds(1000, 132, 100, 14);
					surnameLB.setBounds(1200, 132, 100, 14);
					birth_dateLB.setBounds(1000, 188, 100, 14);
					birth_placeLB.setBounds(1200, 188, 100, 14);
					addressLB.setBounds(1000, 249, 100, 14);
					genderLB.setBounds(1000, 310, 100, 14);
					cellphoneLB.setBounds(1200, 310, 100, 14);
					vehicleLB.setBounds(1000, 371, 100, 14);
					working_timeLB.setBounds(1200, 371, 100, 14);
					gender_boxLB.setBounds(1000, 335, 165, 25);
					shop_idLB.setBounds(1200, 431, 46, 14);
					
				
					working_timeTF.setBounds(1200, 396, 150, 25);
					nameTF.setBounds(1000, 152, 150, 25);
					surnameTF.setBounds(1200, 152, 150, 25);
					birth_dateTF.setBounds(1000, 213, 150, 25);
					birth_placeTF.setBounds(1200, 213, 150, 25);
					addressTF.setBounds(1000, 274, 350, 25);
					cellphoneTF.setBounds(1200, 335, 150, 25);
					shop_idTF.setBounds(1200, 456, 150, 25);
					
					gender_maleJRB.setBounds(1005, 335, 75, 25);
					gender_femaleJRB.setBounds(1080, 335, 80, 25);
					vehicleJCB.setBounds(1000, 396, 150, 25);
					insert_sqlJB.setBounds(1000, 600, 150,30);
					update_sqlJB.setBounds(1200, 600, 150,30);
					delete_sqlJB.setBounds(1000, 660, 150,30);
					
				}
				
			}
		});
		
		iconifierJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				frame.setState(Frame.ICONIFIED);
				
			}
		});
		
		vehicleJCB.addActionListener(new ActionListener( ) {
			
			public void actionPerformed(ActionEvent arg0) {
				String value = vehicleJCB.getSelectedItem().toString();
				if(value.equals("Bicicletta"))
				{
					vehicleJCB.setActionCommand("Bicicletta");
				}
				else if(value.equals("Motoveicolo"))
					vehicleJCB.setActionCommand("Motoveicolo");
				else
					vehicleJCB.setActionCommand("Autoveicolo");
			
			}
		});
	}
	
}