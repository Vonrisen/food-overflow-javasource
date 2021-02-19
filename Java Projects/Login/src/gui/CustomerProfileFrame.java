package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.AdminController;
import controllers.CustomerController;
import controllers.LoginController;
import entities.Shop;
import gui_support.RoundJPasswordField;
import gui_support.RoundJTextField;

public class CustomerProfileFrame extends JFrame {

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	private Dimension long_dim_of_textfield;
	private ImageIcon backgroundIMG;
	private ImageIcon male_avatarIMG;
	private ImageIcon female_avatarIMG;
	private ImageIcon edit_address_activeIMG;
	private ImageIcon edit_address_inactiveIMG;
	private ImageIcon edit_auth_inactiveIMG;
	private ImageIcon edit_auth_activeIMG;
	private ImageIcon update_inactiveIMG;
	private ImageIcon update_activeIMG;
	private ImageIcon go_back_inactiveIMG;
	private ImageIcon go_back_activeIMG;
	private Dimension west_east_size;
	private Dimension short_dim_of_textfield;
	private Dimension north_south_size;
	
	private JLabel avatarLB;
	private JLabel background;
	
	private JButton edit_authJB;
	private JButton edit_addressJB;
	private JButton updateJB;
	private JButton go_backJB;
	
	private JTextField address_nameTF;
	private JTextField address_civic_numberTF;
	private JTextField address_capTF;
	private JComboBox<String> address_provinceCB;
	private JComboBox<String> address_townCB;
	
	private JTextField old_emailTF;
	private JTextField old_phoneTF;
	private JPasswordField old_passwordTF;
	private JTextField emailTF;
	private JPasswordField passwordTF;
	private JTextField phoneTF;
	CustomerController customer_controller;
	
	public CustomerProfileFrame(CustomerController customer_controller) {
		
		initialize();
		frameSetup();
		events();
		this.customer_controller = customer_controller;
	}

	//Initialize variables
	private void initialize() {
		
		background = new JLabel();
		
		male_avatarIMG = new ImageIcon("src\\images\\customer\\maleAvatar.PNG");
		female_avatarIMG = new ImageIcon("src\\images\\customer\\femaleAvatar.PNG");
		edit_auth_inactiveIMG = new ImageIcon("src\\images\\customer\\changeLoginInactive.PNG");
		edit_address_inactiveIMG = new ImageIcon("src\\images\\customer\\changeAddressInactive.PNG");
		edit_auth_activeIMG = new ImageIcon("src\\images\\customer\\changeLoginActive.PNG");
		edit_address_activeIMG = new ImageIcon("src\\images\\customer\\changeAddressActive.PNG");
		backgroundIMG = new ImageIcon("src\\images\\customer\\backgroundProfile.PNG");
		update_inactiveIMG = new ImageIcon("src\\images\\buttons\\updateButtonInactive.png");
		update_activeIMG = new ImageIcon("src\\images\\buttons\\updateButtonActive.png");
		go_back_inactiveIMG = new ImageIcon("src\\images\\buttons\\goBackInactive.png");
		go_back_activeIMG = new ImageIcon("src\\images\\buttons\\goBackActive.png");
		
		long_dim_of_textfield = new Dimension(335,25);
		short_dim_of_textfield = new Dimension(150,25);
		west_east_size = new Dimension(100,80);
		north_south_size = new Dimension(100,50);
		
		avatarLB = new JLabel();
		
		edit_authJB = new JButton();
		edit_addressJB = new JButton();
		updateJB = new JButton();
		go_backJB = new JButton();
		
		address_nameTF = new RoundJTextField(new Color(0x771007));
		address_civic_numberTF = new RoundJTextField(new Color(0x771007));
		address_capTF = new RoundJTextField(new Color(0x771007));
		address_provinceCB = new JComboBox<String>();
		address_townCB = new JComboBox<String>();
		old_emailTF = new RoundJTextField(new Color(0x771007));
		old_passwordTF = new RoundJPasswordField(new Color(0x771007));
		old_phoneTF = new RoundJTextField(new Color(0x771007));
		emailTF = new RoundJTextField(new Color(0x771007));
		passwordTF = new RoundJPasswordField(new Color(0x771007));
		phoneTF = new RoundJTextField(new Color(0x771007));
		
	}
	
	private void frameSetup() {
		
		//Layout setup
		
		this.setTitle("Food Overflow - Profilo");
		this.setSize(500,720);
		background.setIcon(resize(backgroundIMG, this.getWidth(), this.getHeight()));
		setIconImage(new ImageIcon("src\\images\\startup\\icon.png").getImage());
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int central_width = screen_dim.width/2-this.getSize().width/2;
		int central_height = screen_dim.height/2-this.getSize().height/2;
		this.setLocation(central_width, central_height); //Setta il frame a centro monitor
		this.getContentPane().setLayout(null);
		this.setContentPane(background);
		this.getContentPane().setLayout(null);
	
		
		//
		
		avatarLB.setBounds(118,20,500,280);
		avatarLB.setIcon(male_avatarIMG);
		this.getContentPane().add(avatarLB);
		
		
		//
		createTextField(address_nameTF, "Nome dell'indirizzo");
		address_nameTF.setBounds(83,325,150,25);
		this.getContentPane().add(address_nameTF);

		createTextField(address_civic_numberTF, "Numero civico");
		address_civic_numberTF.setBounds(253,325,150,25);
		this.getContentPane().add(address_civic_numberTF);

		this.getContentPane().add(address_provinceCB);
		address_provinceCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		address_provinceCB.setFocusable(false);
		address_provinceCB.setBackground(Color.white);
		address_provinceCB.setVisible(false);
		address_provinceCB.setBounds(83,375,150,25);
		
		this.getContentPane().add(address_townCB);
		address_townCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		address_townCB.setFocusable(false);
		address_townCB.setBackground(Color.white);
		address_townCB.setVisible(false);
		address_townCB.setBounds(253,375,150,25);
		
		createTextField(address_capTF, "CAP");
		address_capTF.setBounds(168,425,150,25);
		this.getContentPane().add(address_capTF);
		
		//
		
		createTextField(old_emailTF, "Vecchia e-mail");
		old_emailTF.setBounds(83,325,150,25);
		this.getContentPane().add(old_emailTF);

		createTextField(emailTF, "Nuova e-mail");
		emailTF.setBounds(253,325,150,25);
		this.getContentPane().add(emailTF);
		
		createTextField(old_passwordTF, "Vecchia password");
		old_passwordTF.setEchoChar((char) 0);
		old_passwordTF.setBounds(83,375,150,25);
		this.getContentPane().add(old_passwordTF);

		createTextField(passwordTF, "Nuova password");
		passwordTF.setEchoChar((char) 0);
		passwordTF.setBounds(253,375,150,25);
		this.getContentPane().add(passwordTF);
		
		createTextField(old_phoneTF, "Vecchio n. cellulare");
		old_phoneTF.setBounds(83,425,150,25);
		this.getContentPane().add(old_phoneTF);
		
		createTextField(phoneTF, "Nuovo n. cellulare");
		phoneTF.setBounds(253,425,150,25);
		this.getContentPane().add(phoneTF);
		
		//
		
		setupButton(edit_authJB,edit_auth_inactiveIMG);
		edit_authJB.setBounds(285,400,150,100);
		this.getContentPane().add(edit_authJB);

		
		setupButton(edit_addressJB,edit_address_inactiveIMG);
		edit_addressJB.setBounds(50,400,150,100);
		this.getContentPane().add(edit_addressJB);
		
		setupButton(updateJB,update_inactiveIMG);
		updateJB.setBounds(168,480,150,30);
		updateJB.setVisible(false);
		this.getContentPane().add(updateJB);
		
		setupButton(go_backJB,go_back_inactiveIMG);
		go_backJB.setBounds(168,625,150,30);
		go_backJB.setVisible(true);
		this.getContentPane().add(go_backJB);
		
			
	}
	
	private void events() {

		
		edit_authJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				edit_authJB.setIcon(edit_auth_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				edit_authJB.setIcon(edit_auth_inactiveIMG);
				
			}
			public void mousePressed(MouseEvent e) {
				
				edit_authJB.setVisible(false);
				edit_addressJB.setVisible(false);
				
				old_emailTF.setVisible(true);
				emailTF.setVisible(true);
				old_passwordTF.setVisible(true);
				passwordTF.setVisible(true);
				old_phoneTF.setVisible(true);
				phoneTF.setVisible(true);
				updateJB.setVisible(true);
				go_backJB.setVisible(true);
			}
			
		});
		
		edit_addressJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				edit_addressJB.setIcon(edit_address_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				edit_addressJB.setIcon(edit_address_inactiveIMG);
				
			}
			public void mousePressed(MouseEvent e) {
				
				edit_addressJB.setVisible(false);
				edit_authJB.setVisible(false);
				
				address_nameTF.setVisible(true);
				address_civic_numberTF.setVisible(true);
				address_capTF.setVisible(true);
				address_provinceCB.setVisible(true);
				address_townCB.setVisible(true);
				updateJB.setVisible(true);
				go_backJB.setVisible(true);
			}
		});
		
		updateJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				updateJB.setIcon(update_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				updateJB.setIcon(update_inactiveIMG);
				
			}
			public void mousePressed(MouseEvent e) {
				
				if(address_nameTF.isVisible()) {
					
					//Modifico le cose relative all'address
					
				} else {
					
					//Modifico le cose relative all'auth
					
				}
				
			}
		});
		
		go_backJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				go_backJB.setIcon(go_back_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				go_backJB.setIcon(go_back_inactiveIMG);
				
			}
			public void mousePressed(MouseEvent e) {

				if(address_nameTF.isVisible()) {
					
					address_nameTF.setVisible(false);
					address_civic_numberTF.setVisible(false);
					address_capTF.setVisible(false);
					address_provinceCB.setVisible(false);
					address_townCB.setVisible(false);

					
				} else {
					
					old_emailTF.setVisible(false);
					emailTF.setVisible(false);
					old_passwordTF.setVisible(false);
					passwordTF.setVisible(false);
					old_phoneTF.setVisible(false);
					phoneTF.setVisible(false);
					
				}
				
				updateJB.setVisible(false);
				go_backJB.setVisible(false);
				
				edit_authJB.setVisible(true);
				edit_addressJB.setVisible(true);
				
			}
		});
		
		address_nameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(address_nameTF, "Nome dell'indirizzo");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(address_nameTF, "Nome dell'indirizzo");
				
			}
		});
		
		address_civic_numberTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(address_civic_numberTF, "Numero civico");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(address_civic_numberTF, "Numero civico");
				
			}
		});
		
		address_capTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(address_capTF, "CAP");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(address_capTF, "CAP");
				
			}
		});
		
		old_emailTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(old_emailTF, "Vecchia e-mail");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(old_emailTF, "Vecchia e-mail");
				
			}
		});
		
		emailTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(emailTF, "Nuova e-mail");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(emailTF, "Nuova e-mail");
				
			}
		});
		
		old_phoneTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(old_phoneTF, "Vecchio n. cellulare");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(old_phoneTF, "Vecchio n. cellulare");
				
			}
		});
		
		phoneTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(phoneTF, "Nuovo n. cellulare");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(phoneTF, "Nuovo n. cellulare");
				
			}
		});
		
		old_passwordTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				if (old_passwordTF.getText().equals("Vecchia password")) {

					old_passwordTF.setEchoChar('•');
					old_passwordTF.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {

				if (old_passwordTF.getText().equals("")) {

					old_passwordTF.setText("Vecchia password");
					old_passwordTF.setEchoChar((char) 0);
				}
			}
		});
		
		passwordTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				if (passwordTF.getText().equals("Nuova password")) {

					passwordTF.setEchoChar('•');
					passwordTF.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {

				if (passwordTF.getText().equals("")) {

					passwordTF.setText("Nuova password");
					passwordTF.setEchoChar((char) 0);
				}
			}
		});
		
	}
	
	private ImageIcon resize(ImageIcon im, int w, int h) {
		
		BufferedImage bi = new BufferedImage(w,h, BufferedImage.TRANSLUCENT);
		Graphics2D gd=(Graphics2D)bi.createGraphics();
		gd.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		gd.drawImage(im.getImage(), 0, 0, w,h,null);
		gd.dispose();
		
		return new ImageIcon(bi);
	}
	
	private void setupButton(JButton button, ImageIcon image) {
		
		button.setIcon(image);
		button.setBorder(null);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		
	}
	
	protected void textFieldFocusGained(JTextField text_field, String string) {
		if (text_field.getText().equals(string)) {
			text_field.setText("");
			text_field.setHorizontalAlignment(JTextField.LEFT);
		}
	}
	
	protected void textFieldFocusLost(JTextField text_field, String string) {
		if (text_field.getText().equals("")) {
			text_field.setText(string);
			text_field.setHorizontalAlignment(JTextField.CENTER);
		}
	}
	
	protected void createTextField(JTextField text_field, String text) {
		text_field.setHorizontalAlignment(JTextField.CENTER);
		text_field.setText(text);
		text_field.setVisible(false);
	}

	
	

}

