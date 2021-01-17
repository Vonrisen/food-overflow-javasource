package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminFrame {

	private int mouseX=0;
	private int mouseY=0;
	private JFrame frame;
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	GridBagLayout gbl;
	
	ImageIcon maximize_buttonIMG;
	ImageIcon minimize_buttonIMG;
	ImageIcon close_buttonIMG;
	ImageIcon iconifier_buttonIMG;
	ImageIcon customer_button_smallIMG;
	ImageIcon customer_button_bigIMG;
	ImageIcon shop_button_smallIMG;
	ImageIcon shop_button_bigIMG;
	ImageIcon logoIMG;
	ImageIcon rider_button_smallIMG;
	ImageIcon rider_button_bigIMG;
	ImageIcon orders_button_smallIMG;
	ImageIcon orders_button_bigIMG;
	ImageIcon meal_button_smallIMG;
	ImageIcon meal_button_bigIMG;
	
	JPanel home_panelJP;
	JPanel top_panelJP;
	

	JLabel logoLB;
	
	JLabel iconifierFrameButton;
	JLabel resizeFrameButton;
	JLabel closeFrameButton;
	JButton customerJB;
	JButton shopJB;
	JButton riderJB;
	JButton mealJB;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame window = new AdminFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminFrame() {
		initialize();
	}

	
	public void initialize() {
		
		frame = new JFrame();
		
		// Definizione variabili
		
		gbl = new GridBagLayout();
		maximize_buttonIMG = new ImageIcon("src\\images\\FrameIcons\\maximizeFrameButton.png");
		minimize_buttonIMG = new ImageIcon("src\\images\\FrameIcons\\minimizeFrameButton.png");
		close_buttonIMG = new ImageIcon("src\\images\\FrameIcons\\closeFrameButton.png");
		iconifier_buttonIMG = new ImageIcon("src\\images\\FrameIcons\\iconifierFrameButton.png");
		customer_button_smallIMG = new ImageIcon("src\\images\\AdminFrame\\customerButtonSmall.png");
		customer_button_bigIMG = new ImageIcon("src\\images\\AdminFrame\\customerButtonBig.png");
		shop_button_smallIMG = new ImageIcon("src\\images\\AdminFrame\\shopButtonSmall.png");
		shop_button_bigIMG = new ImageIcon("src\\images\\AdminFrame\\shopButtonBig.png");
		logoIMG = new ImageIcon("src\\images\\FrameIcons\\logoIcon.png");
		rider_button_smallIMG = new ImageIcon("src\\images\\AdminFrame\\riderButtonSmall.png");
		rider_button_bigIMG = new ImageIcon("src\\images\\AdminFrame\\riderButtonBig.png");
		orders_button_smallIMG = new ImageIcon("src\\images\\AdminFrame\\ordersButtonSmall.png");
		orders_button_bigIMG = new ImageIcon("src\\images\\AdminFrame\\ordersButtonBig.png");
		meal_button_smallIMG = new ImageIcon("src\\images\\AdminFrame\\mealButtonSmall.png");
		meal_button_bigIMG = new ImageIcon("src\\images\\AdminFrame\\mealButtonBig.png");
		
		home_panelJP = new JPanel();
		top_panelJP = new JPanel();
		iconifierFrameButton = new JLabel();
		resizeFrameButton = new JLabel();
		closeFrameButton = new JLabel();
		logoLB = new JLabel("<html><font face='Calibri' size='4' color=rgb(243,236,215)>FOOD OVERFLOW:</font> <font face='Calibri' size='4' color=rgb(244,153,105)>ADMIN PANEL</font></html>");
		customerJB = new JButton();
		shopJB = new JButton();
		riderJB = new JButton();
		mealJB = new JButton();
		
		//Settiamo il frame
	
		frame.setSize(1600,900);
		int central_width = dim.width/2-frame.getSize().width/2;
		int central_height = dim.height/2-frame.getSize().height/2;
		frame.setLocation(central_width, central_height); //Setta il frame a centro monitor
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(new BorderLayout());
		
		//Impostiamo il layout
		
		home_panelJP.setLayout(gbl);
		home_panelJP.setBackground(new Color(0xf3ecd7));
		frame.getContentPane().add(home_panelJP, BorderLayout.CENTER);
		
		top_panelJP.setLayout(new BoxLayout(top_panelJP, BoxLayout.LINE_AXIS));
		top_panelJP.setPreferredSize(new Dimension(100,35));
		top_panelJP.setBackground(new Color(0x771007));
		top_panelJP.setVisible(true);
		frame.getContentPane().add(top_panelJP, BorderLayout.NORTH);	
		
		//Impostazione dei JButton del Frame
		
		iconifierFrameButton.setIcon(iconifier_buttonIMG);
		resizeFrameButton.setIcon(maximize_buttonIMG);
		closeFrameButton.setIcon(close_buttonIMG);

		logoLB.setForeground(new Color(0xf3ecd7));;
		logoLB.setIcon(logoIMG);
		
		top_panelJP.add(logoLB);
		top_panelJP.add(Box.createHorizontalGlue());
		top_panelJP.add(iconifierFrameButton);
		top_panelJP.add(resizeFrameButton);
		top_panelJP.add(closeFrameButton);
		
		//Impostazione dei JButton

		customerJB.setIcon(customer_button_smallIMG);
		customerJB.setBorder(null);
		customerJB.setPreferredSize(new Dimension(600,300));
		customerJB.setFocusable(false);
		customerJB.setContentAreaFilled(false);
		

		shopJB.setPreferredSize(new Dimension(600,300));
		shopJB.setIcon(shop_button_smallIMG);
		shopJB.setBorder(null);
		shopJB.setFocusable(false);
		shopJB.setContentAreaFilled(false);
		

		riderJB.setPreferredSize(new Dimension(600,300));
		riderJB.setIcon(rider_button_smallIMG);
		riderJB.setBorder(null);
		riderJB.setFocusable(false);
		riderJB.setContentAreaFilled(false);

		mealJB.setPreferredSize(new Dimension(600,300));
		mealJB.setIcon(meal_button_smallIMG);
		mealJB.setBorder(null);
		mealJB.setFocusable(false);
		mealJB.setContentAreaFilled(false);
		
		//Impostazione GridBagLayout per i JButton
		
		GridBagConstraints gcon = new GridBagConstraints();
		gcon.insets = new Insets(50,100,50,100);
		gcon.weightx = 1;
		gcon.weighty = 1;
		gcon.fill = GridBagConstraints.BOTH;
		
		gcon.gridx = 0;
		gcon.gridy = 0;
		gcon.gridwidth = 1;
		gcon.gridheight = 1;
		
		gbl.setConstraints(customerJB, gcon);
		home_panelJP.add(customerJB);
		
		gcon.gridx = 2;
		
		gbl.setConstraints(shopJB, gcon);
		home_panelJP.add(shopJB);
		
		gcon.gridx = 0;
		gcon.gridy = 2;

		gbl.setConstraints(riderJB, gcon);
		home_panelJP.add(riderJB);
		
		gcon.gridx = 2;
		gcon.gridy = 2;
		
		gbl.setConstraints(mealJB, gcon);
		home_panelJP.add(mealJB);
		
		//Listeners
		
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
					
					resizeFrameButton.setIcon(minimize_buttonIMG);
					frame.setLocation(central_width, central_height);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					customerJB.setIcon(customer_button_bigIMG);
					shopJB.setIcon(shop_button_bigIMG);
					mealJB.setIcon(meal_button_bigIMG);
					riderJB.setIcon(rider_button_bigIMG);
					
				} else if(frame.getSize().equals(dim)) {
					
					resizeFrameButton.setIcon(maximize_buttonIMG);
					frame.setSize(1600,900);
					frame.setLocation(central_width, central_height);
					customerJB.setIcon(customer_button_smallIMG);
					shopJB.setIcon(shop_button_smallIMG);
					mealJB.setIcon(meal_button_smallIMG);
					riderJB.setIcon(rider_button_smallIMG);
					
				}
				
			}
		});
		
		iconifierFrameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				frame.setState(Frame.ICONIFIED);
				
			}
		});
		
		
		shopJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Spostamento AdminShopFrame
			}
		});
		
		riderJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Spostamento AdminRiderFrame
			}
		});
		
		customerJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Spostamento AdminUserFrame
			}
		});
		
		mealJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Spostamento AdminMealFrame
			}
		});
		
	}

}
