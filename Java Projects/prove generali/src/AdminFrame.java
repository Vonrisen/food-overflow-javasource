package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controllers.AdminController;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class AdminFrame extends JFrame{
	
	private class MyNullLayoutJPanel extends JPanel {

		public Dimension getPreferredSize() {
			int maxX = 0;
			int maxY = 0;
			Component[] components = this.getComponents();
			for(int i = 0; i < components.length; i++){
				Rectangle bounds = components[i].getBounds();
				maxX = Math.max(maxX, (int)bounds.getMaxX());
				maxY = Math.max(maxY, (int)bounds.getMaxY());
			}
			return new Dimension(maxX,maxY);
		}
	}
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
		ImageIcon maximize_button = new ImageIcon("src\\images\\FrameIcons\\maximizeFrameButton.png");
		ImageIcon minimize_button = new ImageIcon("src\\images\\FrameIcons\\minimizeFrameButton.png");
		ImageIcon close_button = new ImageIcon("src\\images\\FrameIcons\\closeFrameButton.png");
		ImageIcon iconifier_button = new ImageIcon("src\\images\\FrameIcons\\iconifierFrameButton.png");
		ImageIcon logoIcon = new ImageIcon("src\\images\\FrameIcons\\logoIcon.png");

		
		
		this.setSize(1600,900);
		int central_width = dim.width/2-this.getSize().width/2;
		int central_height = dim.height/2-this.getSize().height/2;
		this.setLocation(central_width, central_height); //Setta il frame a centro monitor
		this.setUndecorated(false);
		this.getContentPane().setLayout(new BorderLayout());
		
		MyNullLayoutJPanel home_panel = new MyNullLayoutJPanel();
		home_panel.setLayout(null);
		home_panel.setBackground(new Color(0xf3ecd7));
		this.getContentPane().add(home_panel, BorderLayout.SOUTH);
		
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

					
				} else if(AdminFrame.this.getSize().equals(dim)) {
					
					resizeFrameButton.setIcon(maximize_button);
					AdminFrame.this.setSize(1600,900);
					AdminFrame.this.setLocation(central_width, central_height);

					
				}
				
			}
		});
		
		iconifierFrameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				AdminFrame.this.setState(Frame.ICONIFIED);
				
			}
		});
		
		
		
		
		Cibo alimento1=new Cibo("Spaghetti e vongole",5.50,"Ingredienti:Spaghetti, vongole, pomodorini");
		Cibo alimento2=new Cibo("Cotoletta",8.50,"Ingredienti:Carne di vitello, uova, farina, burro");
		Cibo alimento3=new Cibo("Costine al forno",12.00,"Ingredienti:Costine di carne, rosmarino, pepe nero, aglio");
		Cibo alimento4=new Cibo("Insalata di polpo e patate",9.00,"Ingredienti:Polpo, Patate, prezzemolo, olio, succo di limone");
		Cibo alimento5=new Cibo("CocaCola",2.00,"Ingredienti:Acqua, Anidride carbonica, zucchero, colorante, acido fosforico, aromi naturali. caffeina");
		Cibo alimento6=new Cibo("Bottiglina d'acqua",1.00);
		Cibo alimento7=new Cibo("Patatine Fritte p",2.00,"Porzione piccola. Ingredienti:Patate, sale, olio");
		Cibo alimento8=new Cibo("Pizza margherita",4.00);
		Cibo alimento9=new Cibo("Gelato alla vaniglia",2.00);
		Cibo alimento10=new Cibo("Pasticceria mignon",6.00,"Ingredienti:vari");
		
		
		ArrayList<Cibo> arrayCibo= new ArrayList<Cibo>();
		arrayCibo.add(alimento1);
		arrayCibo.add(alimento2);
		arrayCibo.add(alimento3);
		arrayCibo.add(alimento4);
		arrayCibo.add(alimento5);
		arrayCibo.add(alimento6);
		arrayCibo.add(alimento7);
		arrayCibo.add(alimento8);
		arrayCibo.add(alimento9);
		arrayCibo.add(alimento10);
		
		ArrayList<JButton> arrayButton = new ArrayList<JButton>();
		for(Cibo c:arrayCibo) {
			JButton ciboButton = new JButton();
			ciboButton.setName(c.getNome());
			arrayButton.add(ciboButton);
		}
		JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
		
		for(int i=0; i<arrayButton.size()/2; i++) {
			arrayButton.set(i, arrayButton.get(i)).setPreferredSize(new Dimension(200,200));
			ImageIcon imm = new ImageIcon("src\\"+arrayButton.get(i).getName()+".png");
			Image image= imm.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			imm.setImage(image);
			arrayButton.get(i).setIcon(imm);
			if(imm.getIconHeight()<1) {
				arrayButton.get(i).setBackground(Color.gray);
				arrayButton.get(i).setText(arrayButton.get(i).getName());
			}
			panel1.add(arrayButton.get(i));
			panel2.add(arrayButton.get(i));
			arrayButton.get(i).setVisible(true);
		}
		JScrollPane scrollPane1 = new JScrollPane(panel1);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        
        
       
		JScrollPane scrollPane2 = new JScrollPane(panel2);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        
        for(int i=arrayButton.size()/2; i<arrayButton.size(); i++) {
			arrayButton.set(i, arrayButton.get(i)).setPreferredSize(new Dimension(200,200));
			ImageIcon imm = new ImageIcon("src\\"+arrayButton.get(i).getName()+".png");
			Image image= imm.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			imm.setImage(image);
			arrayButton.get(i).setIcon(imm);
			if(imm.getIconHeight()<1) {
				arrayButton.get(i).setBackground(Color.gray);
				arrayButton.get(i).setText(arrayButton.get(i).getName());
			}
			panel3.add(arrayButton.get(i));
			panel4.add(arrayButton.get(i));
			arrayButton.get(i).setVisible(true);
		}
		JScrollPane scrollPane3 = new JScrollPane(panel3);
        scrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        
        
        
		JScrollPane scrollPane4 = new JScrollPane(panel4);
        scrollPane4.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        
        for(int i=arrayButton.size()/2; i<arrayButton.size(); i++) {
			arrayButton.set(i, arrayButton.get(i)).setPreferredSize(new Dimension(200,200));
			ImageIcon imm = new ImageIcon("src\\"+arrayButton.get(i).getName()+".png");
			Image image= imm.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			imm.setImage(image);
			arrayButton.get(i).setIcon(imm);
			if(imm.getIconHeight()<1) {
				arrayButton.get(i).setBackground(Color.gray);
				arrayButton.get(i).setText(arrayButton.get(i).getName());
			}
			panel5.add(arrayButton.get(i));
			panel6.add(arrayButton.get(i));
			arrayButton.get(i).setVisible(true);
		}
		JScrollPane scrollPane5 = new JScrollPane(panel5);
        scrollPane5.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane5.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        
        
        
        
		JScrollPane scrollPane6 = new JScrollPane(panel6);
        scrollPane6.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane6.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        
        
        
        
        
        
        JTextField jt1 = new JTextField("Primo");
        JTextField jt2 = new JTextField("Secondo");
        JTextField jt3 = new JTextField("Pizza");
        JTextField jt4 = new JTextField("Fast Food");
        JTextField jt5 = new JTextField("Bibite");
        JTextField jt6 = new JTextField("Dolci");
        
        jt1.setBackground(new Color(0xf3ecd7));
        jt2.setBackground(new Color(0xf3ecd7));
        jt3.setBackground(new Color(0xf3ecd7));
        jt4.setBackground(new Color(0xf3ecd7));
        jt5.setBackground(new Color(0xf3ecd7));
        jt6.setBackground(new Color(0xf3ecd7));
        
//        jt1.setEditable(false);
//        jt2.setEditable(false);
//        jt3.setEditable(false);
//        jt4.setEditable(false);
//        jt5.setEditable(false);
//        jt6.setEditable(false);
        
        jt1.setFocusable(false);
        jt2.setFocusable(false);
        jt3.setFocusable(false);
        jt4.setFocusable(false);
        jt5.setFocusable(false);
        jt6.setFocusable(false);
        
        jt1.setBorder(null);
        jt2.setBorder(null);
        jt3.setBorder(null);
        jt4.setBorder(null);
        jt5.setBorder(null);
        jt6.setBorder(null);
        
        
        jt1.setBounds(100, 20, 100, 20);
        scrollPane1.setBounds(20, 50, 450, 250);
        jt2.setBounds(100, 320, 100, 20);
        scrollPane2.setBounds(20, 350, 450, 250);
        jt3.setBounds(100, 620, 100, 20);
        scrollPane3.setBounds(20, 650, 450, 250);
        jt4.setBounds(100, 920, 100, 20);
        scrollPane4.setBounds(20, 950, 450, 250);
        jt5.setBounds(100, 1220, 100, 20);
        scrollPane5.setBounds(20, 1250, 450, 250);
        jt6.setBounds(100, 1520, 100, 20);
        scrollPane6.setBounds(20, 1550, 1450, 250);
        
        home_panel.add(jt1);
        home_panel.add(scrollPane1);
        home_panel.add(jt2);
        home_panel.add(scrollPane2);
        home_panel.add(jt3);
        home_panel.add(scrollPane3);
        home_panel.add(jt4);
        home_panel.add(scrollPane4);
        home_panel.add(jt5);
        home_panel.add(scrollPane5);
        home_panel.add(jt6);
        home_panel.add(scrollPane6);
        
        
        
        panel1.setBackground(new Color(0xf3ecd7));
        panel2.setBackground(new Color(0xf3ecd7));
        panel3.setBackground(new Color(0xf3ecd7));
        panel4.setBackground(new Color(0xf3ecd7));
        panel5.setBackground(new Color(0xf3ecd7));
        panel6.setBackground(new Color(0xf3ecd7));
        scrollPane1.setBorder(null);
        scrollPane2.setBorder(null);
        scrollPane3.setBorder(null);
        scrollPane4.setBorder(null);
        scrollPane5.setBorder(null);
        scrollPane6.setBorder(null);
        
        JScrollPane scrollPane = new JScrollPane(home_panel);
        scrollPane.setBackground(new Color(0xf3ecd7));
        
        
        
        
        
        
        
        
		this.setSize(400,400);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		this.setVisible(true);
		this.getContentPane().add(scrollPane);

		
		
		
		
		
//		home_panel.setLayout(null);
//		home_panel.setBackground(new Color(0xf3ecd7));
//		this.getContentPane().add(home_panel, BorderLayout.SOUTH);
		
		
//		JLabel centralPanel = new JLabel("New label");
//		scrollPane.setColumnHeaderView(centralPanel);
//		
		
		
		
	
		
		
		
		
		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		scrollPane.setColumnHeaderView(menuBar);
		JMenu profilo = new JMenu("Profilo");
		JMenu carrello = new JMenu("Carrello");
		JMenu ordini = new JMenu("ordini");
		JMenu home = new JMenu("Home");
		JMenu cerca = new JMenu("Cerca");
		
		JTextField ricerca= new JTextField();
		

		ricerca.setPreferredSize(new Dimension(200,20));
		ricerca.setMaximumSize(new Dimension(200,200));
		
		

		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(home);
		menuBar.add(profilo);
		menuBar.add(carrello);
		menuBar.add(ordini);
		menuBar.add(ricerca);
		menuBar.add(cerca);
		
		
		
		ricerca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					System.out.println("Effettuo la ricerca");
		        }
			}
		});
		
		home.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("tasto home premuto");
			}
		});
		
		profilo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("tasto profilo premuto");
			}
		});
		
		carrello.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("tasto carrello premuto");
			}
		});
		
		ordini.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("tasto ordini premuto");
			}
		});
		
		cerca.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("tasto cerca premuto");
			}
		});
	
	}

}

