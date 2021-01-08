package guisssss;

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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import gui_support.RoundJTextField;

public class AdminMealFrame {

	private JFrame frame;

	private int mouseX=0;
	private int mouseY=0;
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	ImageIcon maximize_buttonIMG;
	ImageIcon minimize_buttonIMG;
	ImageIcon close_buttonIMG;
	ImageIcon iconifier_buttonIMG;
	ImageIcon logoIMG;
	ImageIcon meals_table_titleIMG;
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
	private JTextField priceTF;
	private JTextField descriptionTF;
	
	JPanel top_panelJP;
	JPanel meal_panelJP;
	
	JLabel iconifierJB;
	JLabel resizeJB;
	JLabel close_frameJB;
	JLabel logoLB;
	JLabel meals_table_titleLB;
	JLabel dishLB;
	JLabel dish_typeLB;
	JLabel nameLB;
	JLabel priceLB;
	JLabel descriptionLB;
	JLabel allergensLB;
	
	String[] dish_array_strings = { "Antipasto", "Primo Piatto", "Secondo Piatto", "Pizza", "Fast Food", "Bevanda" };
	String[] primi_array_strings = {"Pasta", "Zuppe e Minestre"};
	String[] secondi_array_strings = {"Carne", "Pesce e frutti di mare"};
	String[] drink_array_strings = {"Alcolica", "Analcolica"};
	String[] allergens_array_strings = {"Cereali e derivati", "Crostacei", "Uova", "Pesce", "Arachidi", "Soia", "Latte", 
				"Frutta a guscio", "Sedano", "Senape", "Sesamo", "Anidride solforosa e solfiti", "Lupini", "Molluschi"};
	
	DefaultComboBoxModel<String> primo_piatto;
	DefaultComboBoxModel<String> secondo_piatto;
	DefaultComboBoxModel<String> bevanda;
	JComboBox dishTF;
	JComboBox dish_typeTF;
	JButton go_backJB;
	
	JCheckBox allergen1;
	JCheckBox allergen2;
	JCheckBox allergen3;
	JCheckBox allergen4;
	JCheckBox allergen5;
	JCheckBox allergen6;
	JCheckBox allergen7;
	JCheckBox allergen8;
	JCheckBox allergen9;
	JCheckBox allergen10;
	JCheckBox allergen11;
	JCheckBox allergen12;
	JCheckBox allergen13;
	JCheckBox allergen14;
	
	JButton insert_sqlJB;
	JButton update_sqlJB;
	JButton delete_sqlJB;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMealFrame window = new AdminMealFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminMealFrame() {
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
		meals_table_titleIMG = new ImageIcon("src\\images\\tableTitles\\meals.png");
		delete_button_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonInactive.png");
		delete_button_activeIMG = new ImageIcon("src\\images\\SqlButtons\\deleteButtonActive.png");
		insert_button_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\insertButtonInactive.png");
		insert_button_activeIMG = new ImageIcon("src\\images\\SqlButtons\\insertButtonActive.png");
		update_button_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\updateButtonInactive.png");
		update_button_activeIMG = new ImageIcon("src\\images\\SqlButtons\\updateButtonActive.png");
		go_back_inactiveIMG = new ImageIcon("src\\images\\AdminFrame\\turnBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\AdminFrame\\turnBackActive.png");
		
		//Definizione variabili JLabel e JTextField
		
		iconifierJB = new JLabel();
		resizeJB = new JLabel();
		close_frameJB = new JLabel();
		logoLB = new JLabel("<html><font face='Calibri' size='4' color=rgb(243,236,215)>FOOD OVERFLOW:</font> <font face='Calibri' size='4' color=rgb(244,153,105)>ADMIN PANEL (MEAL)</font></html>");
		meals_table_titleLB = new JLabel();
		dishLB = new JLabel("Dish");
		dish_typeLB = new JLabel("Type of dish");
		nameLB = new JLabel("Name");
		priceLB = new JLabel("Price");
		descriptionLB = new JLabel("Description");
		allergensLB = new JLabel("Allergens");
		
		nameTF = new RoundJTextField(new Color(0x771007));
		priceTF = new RoundJTextField(new Color(0x771007));
		descriptionTF = new RoundJTextField(new Color(0x771007));
		
		//Definizione variabili JPanel e JTable
		
		top_panelJP = new JPanel();
		meal_panelJP = new JPanel();
		
		scrollPaneJSP = new JScrollPane();
	    tableJT = new JTable();
	    
	    //Definizione variabili JComboBox e JButton

		primo_piatto = new DefaultComboBoxModel<>(primi_array_strings);
		secondo_piatto = new DefaultComboBoxModel<>(secondi_array_strings);
		bevanda = new DefaultComboBoxModel<>(drink_array_strings);
		dishTF = new JComboBox(dish_array_strings);
		dish_typeTF = new JComboBox();
		go_backJB = new JButton();
		insert_sqlJB = new JButton();
		update_sqlJB = new JButton();
		delete_sqlJB = new JButton();

		//Definizione variabili JCheckBox
		
		allergen1 = new JCheckBox(allergens_array_strings[0]);
		allergen2 = new JCheckBox(allergens_array_strings[1]);
		allergen3 = new JCheckBox(allergens_array_strings[2]);
		allergen4 = new JCheckBox(allergens_array_strings[3]);
		allergen5 = new JCheckBox(allergens_array_strings[4]);
		allergen6 = new JCheckBox(allergens_array_strings[5]);
		allergen7 = new JCheckBox(allergens_array_strings[6]);
		allergen8 = new JCheckBox(allergens_array_strings[7]);
		allergen9 = new JCheckBox(allergens_array_strings[8]);
		allergen10 = new JCheckBox(allergens_array_strings[9]);
		allergen11 = new JCheckBox(allergens_array_strings[10]);
		allergen12 = new JCheckBox(allergens_array_strings[11]);
		allergen13 = new JCheckBox(allergens_array_strings[12]);
		allergen14 = new JCheckBox(allergens_array_strings[13]);

		//Settiamo il Frame
		frame.setSize(1600,900);
		int central_width = dim.width/2-frame.getSize().width/2;
		int central_height = dim.height/2-frame.getSize().height/2;
		frame.setLocation(central_width, central_height); //Setta il frame a centro monitor
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(new BorderLayout());
		
		//Impostazione del layout
		
		top_panelJP.setLayout(new BoxLayout(top_panelJP, BoxLayout.LINE_AXIS));
		top_panelJP.setPreferredSize(new Dimension(100,35));
		top_panelJP.setBackground(new Color(0x771007));
		top_panelJP.setVisible(true);
		frame.getContentPane().add(top_panelJP, BorderLayout.NORTH);	
		

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
		
		meal_panelJP.setLayout(null);
		meal_panelJP.setBackground(new Color(0xf3ecd7));
		frame.getContentPane().add(meal_panelJP, BorderLayout.CENTER);
		

		meal_panelJP.add(scrollPaneJSP);
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
		
		//Definizione contenuto meal_panelJP
		
		meals_table_titleLB.setIcon(meals_table_titleIMG);
		meals_table_titleLB.setBounds(90, 20, 225,100);
		meal_panelJP.add(meals_table_titleLB);

		dishLB.setBounds(1000, 173, 46, 14);
		meal_panelJP.add(dishLB);
		
		dish_typeLB.setBounds(1000, 229, 200, 14);
		dish_typeLB.setVisible(false);
		meal_panelJP.add(dish_typeLB);
		
		allergensLB.setBounds(1000, 295, 150, 14);
		meal_panelJP.add(allergensLB);

		nameLB.setBounds(1310, 173, 126, 14);
		meal_panelJP.add(nameLB);
		
		priceLB.setBounds(1310, 229, 142, 14);
		meal_panelJP.add(priceLB);
		
		descriptionLB.setBounds(1310, 290, 142, 14);
		meal_panelJP.add(descriptionLB);
	
		dishTF.setBounds(1000, 198, 240, 25);
		meal_panelJP.add(dishTF);
		
		dish_typeTF.setBounds(1000, 254, 240, 25);
		dish_typeTF.setVisible(false);
		meal_panelJP.add(dish_typeTF);
		
		nameTF.setBounds(1310, 198, 240, 25);
		meal_panelJP.add(nameTF);
		
		priceTF.setBounds(1310, 254, 240, 25);
		meal_panelJP.add(priceTF);

		descriptionTF.setBounds(1310, 315, 240, 25);
		meal_panelJP.add(descriptionTF);
	
		go_backJB.setBounds(90, 770, 50, 50);
		go_backJB.setIcon(go_back_inactiveIMG);
		go_backJB.setBorder(null);
		go_backJB.setFocusable(false);
		go_backJB.setContentAreaFilled(false);
		meal_panelJP.add(go_backJB);
		
		allergen1.setBounds(1000, 316, 150, 23);
		allergen1.setFocusable(false);
		allergen1.setContentAreaFilled(false);
		meal_panelJP.add(allergen1);
		
		allergen2.setBounds(1000, 342, 88, 23);
		allergen2.setFocusable(false);
		allergen2.setContentAreaFilled(false);
		meal_panelJP.add(allergen2);
		
		allergen3.setBounds(1175, 342, 97, 23);
		allergen3.setFocusable(false);
		allergen3.setContentAreaFilled(false);
		meal_panelJP.add(allergen3);
		
		allergen4.setBounds(1175, 368, 97, 23);
		allergen4.setFocusable(false);
		allergen4.setContentAreaFilled(false);
		meal_panelJP.add(allergen4);
		
		allergen5.setBounds(1000, 394, 97, 23);
		allergen5.setFocusable(false);
		allergen5.setContentAreaFilled(false);
		meal_panelJP.add(allergen5);
		
		allergen6.setBounds(1175, 316, 65, 23);
		allergen6.setFocusable(false);
		allergen6.setContentAreaFilled(false);
		meal_panelJP.add(allergen6);
		
		allergen7.setBounds(1175, 420, 97, 23);
		allergen7.setFocusable(false);
		allergen7.setContentAreaFilled(false);
		meal_panelJP.add(allergen7);

		allergen8.setBounds(1000, 420, 97, 23);
		allergen8.setFocusable(false);
		allergen8.setContentAreaFilled(false);
		meal_panelJP.add(allergen8);
		
		allergen9.setBounds(1000, 472, 97, 23);
		allergen9.setFocusable(false);
		allergen9.setContentAreaFilled(false);
		meal_panelJP.add(allergen9);
		
		allergen10.setBounds(1175, 394, 97, 23);
		allergen10.setFocusable(false);
		allergen10.setContentAreaFilled(false);
		meal_panelJP.add(allergen10);

		allergen11.setBounds(1000, 446, 97, 23);
		allergen11.setFocusable(false);
		allergen11.setContentAreaFilled(false);
		meal_panelJP.add(allergen11);
		
		allergen12.setBounds(1000, 368, 173, 23);
		allergen12.setFocusable(false);
		allergen12.setContentAreaFilled(false);
		meal_panelJP.add(allergen12);
		
		allergen13.setBounds(1175, 446, 97, 23);
		allergen13.setFocusable(false);
		allergen13.setContentAreaFilled(false);
		meal_panelJP.add(allergen13);
		
		allergen14.setBounds(1175, 472, 97, 23);
		allergen14.setFocusable(false);
		allergen14.setContentAreaFilled(false);
		meal_panelJP.add(allergen14);
		
		insert_sqlJB.setIcon(insert_button_inactiveIMG);
		insert_sqlJB.setBounds(1000, 669, 150,30);
		insert_sqlJB.setBorder(null);
		insert_sqlJB.setFocusable(false);
		insert_sqlJB.setContentAreaFilled(false);
		meal_panelJP.add(insert_sqlJB);
		
		update_sqlJB.setIcon(update_button_inactiveIMG);
		update_sqlJB.setBounds(1200, 669, 150,30);
		update_sqlJB.setBorder(null);
		update_sqlJB.setFocusable(false);
		update_sqlJB.setContentAreaFilled(false);
		meal_panelJP.add(update_sqlJB);
		
		delete_sqlJB.setIcon(delete_button_inactiveIMG);
		delete_sqlJB.setBounds(1400, 669, 150,30);
		delete_sqlJB.setBorder(null);
		delete_sqlJB.setFocusable(false);
		delete_sqlJB.setContentAreaFilled(false);
		meal_panelJP.add(delete_sqlJB);
		
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
					meals_table_titleLB.setBounds(dim.width/2-600, 80, 225,100);
					go_backJB.setBounds(25, dim.height-100, 50, 50);
					
					dishLB.setBounds(dim.width/2-300, 50, 46, 14);
					dishTF.setBounds(dim.width/2-300, 70, 240, 25);
					dish_typeLB.setBounds(dim.width/2, 50, 200, 14);
					dish_typeTF.setBounds(dim.width/2, 70, 240, 25);
					nameLB.setBounds(dim.width/2-300, 100, 100, 14);
					nameTF.setBounds(dim.width/2-300, 120, 240, 25);
					priceLB.setBounds(dim.width/2, 100, 100, 14);
					priceTF.setBounds(dim.width/2, 120, 240, 25);
					descriptionLB.setBounds(dim.width/2-300, 150, 100, 14);
					descriptionTF.setBounds(dim.width/2-300, 170, 240, 25);
					
					allergensLB.setBounds(dim.width/2+300, 25, 150, 14);
					allergen1.setBounds(dim.width/2+300, 50, 150, 23);
					allergen2.setBounds(dim.width/2+430, 50, 88, 23);
					allergen3.setBounds(dim.width/2+300, 80, 97, 23);
					allergen4.setBounds(dim.width/2+430, 80, 97, 23);
					allergen5.setBounds(dim.width/2+300, 110, 97, 23);
					allergen6.setBounds(dim.width/2+430, 110, 65, 23);
					allergen7.setBounds(dim.width/2+300, 140, 97, 23);
					allergen8.setBounds(dim.width/2+430, 140, 97, 23);
					allergen9.setBounds(dim.width/2+300, 170, 97, 23);
					allergen10.setBounds(dim.width/2+430, 170, 97, 23);
					allergen11.setBounds(dim.width/2+300, 200, 97, 23);
					allergen12.setBounds(dim.width/2+430, 200, 173, 23);
					allergen13.setBounds(dim.width/2+300, 230, 97, 23);
					allergen14.setBounds(dim.width/2+430, 230, 97, 23);
					
					insert_sqlJB.setBounds(dim.width/2+650, 230, 150,30);
					update_sqlJB.setBounds(dim.width/2+650, 280, 150,30);
					delete_sqlJB.setBounds(dim.width/2+650, 330, 150,30);
					
				} else if(frame.getSize().equals(dim)) {
					
					resizeJB.setIcon(maximize_buttonIMG);
					frame.setSize(1600,900);
					frame.setLocation(central_width, central_height);
					
					scrollPaneJSP.setBounds(90, 132, 850, 600);
					meals_table_titleLB.setBounds(90, 20, 225,100);
					go_backJB.setBounds(90, 770, 50, 50);
					
					dishLB.setBounds(1000, 173, 46, 14);
					dish_typeLB.setBounds(1000, 229, 159, 14);
					nameLB.setBounds(1310, 173, 126, 14);
					priceLB.setBounds(1310, 229, 142, 14);
					descriptionLB.setBounds(1310, 290, 142, 14);
					dishTF.setBounds(1000, 198, 240, 25);
					dish_typeTF.setBounds(1000, 254, 240, 25);
					nameTF.setBounds(1310, 198, 240, 25);
					priceTF.setBounds(1310, 254, 240, 25);
					descriptionTF.setBounds(1310, 315, 240, 25);
					
					allergensLB.setBounds(1000, 295, 150, 14);
					allergen1.setBounds(1000, 316, 150, 23);
					allergen2.setBounds(1000, 342, 88, 23);
					allergen3.setBounds(1175, 342, 97, 23);
					allergen4.setBounds(1175, 368, 97, 23);
					allergen5.setBounds(1000, 394, 97, 23);
					allergen6.setBounds(1175, 316, 65, 23);
					allergen7.setBounds(1175, 420, 97, 23);
					allergen8.setBounds(1000, 420, 97, 23);
					allergen9.setBounds(1000, 472, 97, 23);
					allergen10.setBounds(1175, 394, 97, 23);
					allergen11.setBounds(1000, 446, 97, 23);
					allergen12.setBounds(1000, 368, 173, 23);
					allergen13.setBounds(1175, 446, 97, 23);
					allergen14.setBounds(1175, 472, 97, 23);
					
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
		
		dishTF.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				String value = dishTF.getSelectedItem().toString();
				
				if(value.equals(dish_array_strings[1])) {
					
					dish_typeTF.setModel(primo_piatto);
					dish_typeTF.setVisible(true);
					dish_typeLB.setVisible(true);
					
				} else if(value.equals(dish_array_strings[2])) {
					
					dish_typeTF.setModel(secondo_piatto);
					dish_typeTF.setVisible(true);
					dish_typeLB.setVisible(true);
					
				} else if(value.equals(dish_array_strings[5])) {
					
					dish_typeTF.setModel(bevanda);
					dish_typeTF.setVisible(true);
					dish_typeLB.setVisible(true);
					
				} else {
					
					dish_typeTF.setModel(new DefaultComboBoxModel());
					dish_typeTF.setVisible(false);
					dish_typeLB.setVisible(false);
					
				}
				
			}
			
		});
		
	}

}
