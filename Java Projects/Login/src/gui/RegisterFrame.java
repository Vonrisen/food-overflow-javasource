package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.CustomerController;
import controllers.LoginController;
import gui_support.RoundJPasswordField;
import gui_support.RoundJTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class RegisterFrame extends JFrame{

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel topPanel;
	private JLabel logoLabel;
	private JPanel loginPanel;
	private JButton homeButton;
	private JButton forwardButton;
	private JButton backButton;
	private JButton registerButton;
	
	private Dimension long_dim_of_textfield;
	private Dimension short_dim_of_textfield;
	
	private ImageIcon homeButtonActive;
	private ImageIcon homeButtonInactive;
	private ImageIcon register_logoIMG;
	private ImageIcon forward_button_active;
	private ImageIcon forward_button_inactive;
	private ImageIcon back_button_active;
	private ImageIcon back_button_inactive;
	private ImageIcon registerButtonActive;
	private ImageIcon registerButtonInactive;

	private JTextField nameTF;
	private JTextField surnameTF;
	private JTextField birth_dateTF;
	private JTextField address_nameTF;
	private JTextField address_civic_numberTF;
	private JTextField address_capTF;
	private JTextField cellphoneTF;
	
	private JComboBox<String> address_townCB;
	private JComboBox<String> address_provinceCB;   
	
	
	private JTextField emailTF;
	private JPasswordField passwordTF;
	private JPasswordField passwordTF1;
	
	private JComboBox<String> genderCB;
	private JComboBox<String> birth_nationCB;
	private JComboBox<String> birth_provinceCB;
	private JComboBox<String> birth_townCB;
	
	private String[] genderStrings = {"Maschio", "Femmina"};
	
	private JLabel birth_city;
	private JLabel birth_comune;
	private JLabel birth_comune_1;
	private JLabel birth_comune_1_1;
	
	private LoginController login_controller;

	public RegisterFrame(LoginController login_controller) {
		
		initialize();
		setupFrame();
		events();
		this.login_controller = login_controller;
	}

	private void initialize() {
		
		register_logoIMG = new ImageIcon("src\\images\\startup\\registerLogo.png");
		homeButtonActive = new ImageIcon("src\\images\\startup\\homeButtonActive.png");
		homeButtonInactive = new ImageIcon("src\\images\\startup\\homeButtonInactive.png");
		forward_button_inactive = new ImageIcon("src\\images\\startup\\avantiButtonInactive.png");
		forward_button_active = new ImageIcon("src\\images\\startup\\avantiButtonActive.png");
		back_button_inactive = new ImageIcon("src\\images\\startup\\indietroButtonInactive.png");
		back_button_active = new ImageIcon("src\\images\\startup\\indietroButtonActive.png");
		registerButtonActive = new ImageIcon("src\\images\\startup\\registerButtonActive.png");
		registerButtonInactive = new ImageIcon("src\\images\\startup\\registerButtonInactive.png");
		
		topPanel = new JPanel();
		loginPanel = new JPanel();
		logoLabel = new JLabel();
		homeButton = new JButton();
		forwardButton = new JButton();
		registerButton = new JButton();
		backButton = new JButton();
		
		birth_city = new JLabel("Inserisci lo stato di nascita: ");
		birth_townCB = new JComboBox();
		
		nameTF = new RoundJTextField(new Color(0x771007));
		surnameTF = new RoundJTextField(new Color(0x771007));
		birth_dateTF = new RoundJTextField(new Color(0x771007));
		address_nameTF = new RoundJTextField(new Color(0x771007));
		address_civic_numberTF = new RoundJTextField(new Color(0x771007));
		address_capTF = new RoundJTextField(new Color(0x771007));
		cellphoneTF = new RoundJTextField(new Color(0x771007));
		emailTF = new RoundJTextField(new Color(0x771007));
		passwordTF = new RoundJPasswordField(new Color(0x771007));
		passwordTF1 = new RoundJPasswordField(new Color(0x771007));
		
		address_townCB = new JComboBox();
		address_provinceCB = new JComboBox();
		
		genderCB = new JComboBox(genderStrings);
		birth_nationCB = new JComboBox();
		birth_provinceCB = new JComboBox();
		birth_comune_1 = new JLabel("Inserisci il comune di nascita: ");
		birth_comune_1_1 = new JLabel("Inserisci la data di nascita:");
		birth_comune = new JLabel("Inserisci la provincia di nascita: ");
		
		long_dim_of_textfield = new Dimension(335,25);
		short_dim_of_textfield = new Dimension(150,25);
		
	}
	
	public void setupFrame() {
		
		//Layout setup
		
		this.setResizable(false);
		this.setSize(600, 800);
		setIconImage(new ImageIcon("src\\images\\startup\\icon.png").getImage());
		this.setTitle("Food Overflow - Registrazione");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		int central_width = screen_dim.width/2-this.getSize().width/2;
		int central_height = screen_dim.height/2-this.getSize().height/2;
		this.setLocation(central_width, central_height); //Setta il frame a centro monitor
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().setBackground(new Color(0xf3ecd7));

		topPanel.setBounds(0, 0, 600, 400);
		topPanel.setBackground(null);
		topPanel.setLayout(new BorderLayout());
		this.getContentPane().add(topPanel, BorderLayout.NORTH);

		loginPanel.setLayout(null);
		loginPanel.setBounds(0, 401, 584, 512);
		loginPanel.setBackground(null);
		this.getContentPane().add(loginPanel, BorderLayout.CENTER);
		
		//1# Textfield setup

		createTextField(nameTF, "Nome...");
		nameTF.setBounds(120,25,150,25);
		loginPanel.add(nameTF);
		
		createTextField(surnameTF, "Cognome...");
		surnameTF.setBounds(314,25,150,25);
		loginPanel.add(surnameTF);
		
		createTextField(address_nameTF, "Indirizzo...");
		address_nameTF.setBounds(120,97,150,25);
		loginPanel.add(address_nameTF);
		
		createTextField(address_civic_numberTF, "Numero civico...");
		address_civic_numberTF.setBounds(314,97,150,25);
		loginPanel.add(address_civic_numberTF);
		
		createTextField(address_capTF, "CAP...");
		address_capTF.setBounds(120,133,150,25);
		loginPanel.add(address_capTF);
		
		address_townCB.setBounds(314,61,150,25);
		loginPanel.add(address_townCB);
		
		address_provinceCB.setBounds(120,61,150,25);
		loginPanel.add(address_provinceCB);
		
		genderCB.setBounds(314,133,150,25);
		loginPanel.add(genderCB);
	
//		//2# Textfield setup
		
		birth_comune.setBounds(120, 66, 246, 14);
		birth_comune.setVisible(false);
		loginPanel.add(birth_comune);
		
		birth_comune_1.setBounds(120, 102, 246, 14);
		birth_comune_1.setVisible(false);
		loginPanel.add(birth_comune_1);
		
		birth_city.setBounds(122, 30, 267, 14);
		birth_city.setVisible(false);
		loginPanel.add(birth_city);
		
		birth_nationCB.setBounds(305,25,150,25);
		birth_nationCB.setVisible(false);
		loginPanel.add(birth_nationCB);
		
		birth_provinceCB.setBounds(305,61,150,25);
		birth_provinceCB.setVisible(false);
		loginPanel.add(birth_provinceCB);
		
		birth_townCB.setBounds(305, 97, 150, 25);
		birth_townCB.setVisible(false);
		loginPanel.add(birth_townCB);
		
		createTextField(birth_dateTF, "Data di nascita...");
		birth_dateTF.setVisible(false);
		birth_dateTF.setBounds(305,133,150,25);
		loginPanel.add(birth_dateTF);
		
		birth_comune_1_1.setBounds(120, 138, 246, 14);
		birth_comune_1_1.setVisible(false);
		loginPanel.add(birth_comune_1_1);
		
		//3# Textfield setup
		
		createTextField(emailTF, "E-mail...");
		emailTF.setVisible(false);
		emailTF.setBounds(135,30,310,25);
		loginPanel.add(emailTF);
		
		createTextField(cellphoneTF, "Cellulare...");
		cellphoneTF.setVisible(false);
		cellphoneTF.setBounds(135,138,310,25);
		loginPanel.add(cellphoneTF);
		
		passwordTF.setText("Inserisci password...");
		passwordTF.setVisible(false);
		passwordTF.setHorizontalAlignment(JTextField.CENTER);
		passwordTF.setEchoChar((char) 0);
		passwordTF.setBounds(135, 66, 310, 25);
		loginPanel.add(passwordTF);
		
		passwordTF1.setText("Reinserisci la password...");
		passwordTF1.setVisible(false);
		passwordTF1.setHorizontalAlignment(JTextField.CENTER);
		passwordTF1.setEchoChar((char) 0);
		passwordTF1.setBounds(135, 102, 310, 25);
		loginPanel.add(passwordTF1);
		

		//Buttons & Label setup
		
		logoLabel.setIcon(register_logoIMG);
		logoLabel.setFocusable(true);
		logoLabel.setHorizontalAlignment(JLabel.CENTER);
		topPanel.add(logoLabel);
		
		setupButton(forwardButton, forward_button_inactive);
		forwardButton.setBounds(314,180,150,30);
		loginPanel.add(forwardButton);
		
		setupButton(backButton, back_button_inactive);
		backButton.setVisible(false);
		backButton.setBounds(120,180,150,30);
		loginPanel.add(backButton);
		
		setupButton(registerButton, registerButtonInactive);
		registerButton.setVisible(false);
		registerButton.setBounds(314,180,150,30);
		loginPanel.add(registerButton);
		
		setupButton(homeButton, homeButtonInactive);
		homeButton.setBounds(267, 280, 50, 50);
		loginPanel.add(homeButton);
		
	}
	
	public void events() {
		
		nameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(nameTF, "Nome...");
				
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(nameTF, "Nome...");
				
			}
		});
		
		surnameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(surnameTF, "Cognome...");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(surnameTF, "Cognome...");
				
			}
		});
		
		address_nameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(address_nameTF, "Indirizzo...");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(address_nameTF, "Indirizzo...");
				
			}
		});
		
		address_civic_numberTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(address_civic_numberTF, "Numero civico...");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(address_civic_numberTF, "Numero civico...");
				
			}
		});
		
		address_capTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(address_capTF, "CAP...");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(address_capTF, "CAP...");
				
			}
		});
		
		emailTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(emailTF, "E-mail...");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(emailTF, "E-mail...");
				
			}
		});
		
		cellphoneTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(cellphoneTF, "Cellulare...");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(cellphoneTF, "Cellulare...");
				
			}
		});
		
		birth_dateTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				textFieldFocusGained(birth_dateTF, "Data di nascita...");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				textFieldFocusLost(birth_dateTF, "Data di nascita...");
				
			}
		});
		
		forwardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				forwardButton.setIcon(forward_button_active);
				
				
			}
			@Override
			public void mouseExited(MouseEvent e) {

				forwardButton.setIcon(forward_button_inactive);
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(nameTF.isVisible()&&!birth_nationCB.isVisible()) {
					
					nameTF.setVisible(false);
					surnameTF.setVisible(false);
					address_nameTF.setVisible(false);
					address_civic_numberTF.setVisible(false);
					address_capTF.setVisible(false);
					address_townCB.setVisible(false);
					address_provinceCB.setVisible(false);
					genderCB.setVisible(false);
					
					birth_comune.setVisible(true);
					birth_comune_1.setVisible(true);
					birth_city.setVisible(true);
					birth_nationCB.setVisible(true);
					birth_provinceCB.setVisible(true);
					birth_townCB.setVisible(true);
					birth_dateTF.setVisible(true);
					birth_comune_1_1.setVisible(true);
					
					backButton.setVisible(true);
					
				}else if(birth_nationCB.isVisible()&&!emailTF.isVisible()) {
					
					birth_comune.setVisible(false);
					birth_comune_1.setVisible(false);
					birth_city.setVisible(false);
					birth_nationCB.setVisible(false);
					birth_provinceCB.setVisible(false);
					birth_townCB.setVisible(false);
					birth_dateTF.setVisible(false);
					birth_comune_1_1.setVisible(false);
					
					emailTF.setVisible(true);
					passwordTF.setVisible(true);
					passwordTF1.setVisible(true);
					cellphoneTF.setVisible(true);
					
					forwardButton.setVisible(false);
					registerButton.setVisible(true);
					
				
				}
				
			}
		});
		
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				backButton.setIcon(back_button_active);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				backButton.setIcon(back_button_inactive);
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(!nameTF.isVisible()&&birth_nationCB.isVisible()) {
					
					nameTF.setVisible(true);
					surnameTF.setVisible(true);
					address_nameTF.setVisible(true);
					address_civic_numberTF.setVisible(true);
					address_capTF.setVisible(true);
					address_townCB.setVisible(true);
					address_provinceCB.setVisible(true);
					genderCB.setVisible(true);
					
					birth_comune.setVisible(false);
					birth_comune_1.setVisible(false);
					birth_city.setVisible(false);
					birth_nationCB.setVisible(false);
					birth_provinceCB.setVisible(false);
					birth_townCB.setVisible(false);
					birth_dateTF.setVisible(false);
					birth_comune_1_1.setVisible(false);
					backButton.setVisible(false);
					
				} else if(emailTF.isVisible()&&!birth_nationCB.isVisible()) {
					
					birth_comune.setVisible(true);
					birth_comune_1.setVisible(true);
					birth_city.setVisible(true);
					birth_nationCB.setVisible(true);
					birth_provinceCB.setVisible(true);
					birth_townCB.setVisible(true);
					birth_dateTF.setVisible(true);
					birth_comune_1_1.setVisible(true);
					
					emailTF.setVisible(false);
					passwordTF.setVisible(false);
					passwordTF1.setVisible(false);
					cellphoneTF.setVisible(false);
					
					forwardButton.setVisible(true);
					registerButton.setVisible(false);
					
				}
				
			}
		});
		
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				registerButton.setIcon(registerButtonActive);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				registerButton.setIcon(registerButtonInactive);
				
			}
			@SuppressWarnings("deprecation")
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(!passwordTF.getText().equals(passwordTF1.getText())) {
					
					System.out.println("Le password inserite non coincidono...");
					
				}
				login_controller.registerCustomer(RegisterFrame.this);
				
			}
		});
		
		homeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent click_su_shopButton) {
				login_controller.openLoginFrame(RegisterFrame.this);
				
			}
		});

		homeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent cursore_su_homeButton) {
				
				homeButton.setIcon(homeButtonActive);

			}

			@Override
			public void mouseExited(MouseEvent cursore_fuori_homeButton) {

				homeButton.setIcon(homeButtonInactive);

			}
		});

		passwordTF.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {

				if (passwordTF.getText().equals("Inserisci password...")) {
					
					passwordTF.setHorizontalAlignment(JTextField.LEFT);
					passwordTF.setEchoChar('•');
					passwordTF.setText("");
				}
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {

				if (passwordTF.getText().equals("")) {
					
					passwordTF.setHorizontalAlignment(JTextField.CENTER);
					passwordTF.setText("Inserisci password...");
					passwordTF.setEchoChar((char) 0);
				}
			}
		});
		
		passwordTF1.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {

				if (passwordTF1.getText().equals("Reinserisci la password...")) {
					
					passwordTF1.setHorizontalAlignment(JTextField.LEFT);
					passwordTF1.setEchoChar('•');
					passwordTF1.setText("");
				}
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {

				if (passwordTF1.getText().equals("")) {
					
					passwordTF1.setHorizontalAlignment(JTextField.CENTER);
					passwordTF1.setText("Reinserisci la password...");
					passwordTF1.setEchoChar((char) 0);
				}
			}
		});
		
		addWindowListener(new WindowAdapter()
	     {
	         @Override
	         public void windowClosing(WindowEvent e)
	         {
	             login_controller.releaseAllDaoResourcesAndDisposeFrame(RegisterFrame.this);
	         }
	     });
		
		birth_provinceCB.addItemListener(this::provincesCBitemStateChanged);
		birth_nationCB.addItemListener(this::townsCBitemStateChanged);
		address_provinceCB.addItemListener(this::addressProvinceCBitemStateChanged);
		
	}

	public void provincesCBitemStateChanged(ItemEvent e) {
	    if (e.getStateChange() == ItemEvent.SELECTED) {
	        String selected_item = (String) e.getItem();
	        login_controller.updateTownsCB(selected_item, RegisterFrame.this);
	    }
	}
	public void addressProvinceCBitemStateChanged(ItemEvent e) {
		
	    if (e.getStateChange() == ItemEvent.SELECTED) {
	        String selected_item = (String) e.getItem();
	        if(!selected_item.equals("-------------------")||!selected_item.equals("Seleziona provincia di residenza"))
	        	 login_controller.updateAddressTownsCB(selected_item, RegisterFrame.this);	;
	    	if(selected_item.equals("Seleziona provincia di residenza"))
	    	{
	    		this.getAddress_townCB().removeAllItems();
	    		this.getAddress_townCB().addItem("Seleziona comune di residenza");
	    	}
	       
	    }
		
	}

	public void townsCBitemStateChanged(ItemEvent e) {
	    if (e.getStateChange() == ItemEvent.SELECTED) {
	    	if(birth_nationCB.getSelectedItem().equals("ITALIA")) {
	    		
	    		if(!nameTF.isVisible())
	    		{
	    		birth_provinceCB.setVisible(true);
	    		birth_townCB.setVisible(true);
	    		}
	    		
	    	} else {
	    		
	    		birth_provinceCB.setVisible(false);
	    		birth_townCB.setVisible(false);
	    		
	    	}
	    	
	    }
	}
	
	private void createTextField(JTextField text_field, String text) {

		text_field.setHorizontalAlignment(JTextField.CENTER);
		text_field.setText(text);

	}
	
	private void textFieldFocusGained(JTextField text_field, String string) {
		
		if (text_field.getText().equals(string)) {
			
			text_field.setText("");
			text_field.setHorizontalAlignment(JTextField.LEFT);
			
		}
		
	}
	
	private void textFieldFocusLost(JTextField text_field, String string) {
		
		if (text_field.getText().equals("")) {
			text_field.setText(string);
			text_field.setHorizontalAlignment(JTextField.CENTER);
		}
		
	}
	
	private void setupButton(JButton button, ImageIcon image) {
		
		button.setIcon(image);
		button.setBorder(null);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		
	}

	public JTextField getBirth_dateTF() {
		return birth_dateTF;
	}

	public JTextField getAddress_nameTF() {
		return address_nameTF;
	}

	public JTextField getAddress_civic_numberTF() {
		return address_civic_numberTF;
	}

	public JTextField getAddress_capTF() {
		return address_capTF;
	}

	public JTextField getCellphoneTF() {
		return cellphoneTF;
	}

	public JComboBox<String> getAddress_townCB() {
		return address_townCB;
	}

	public JComboBox<String> getAddress_provinceCB() {
		return address_provinceCB;
	}

	public JTextField getEmailTF() {
		return emailTF;
	}

	public JComboBox<String> getBirth_nationCB() {
		return birth_nationCB;
	}

	public JComboBox<String> getBirth_provinceCB() {
		return birth_provinceCB;
	}

	public JComboBox<String> getBirth_townCB() {
		return birth_townCB;
	}

	public JTextField getSurnameTF() {
		return surnameTF;
	}

	public JPasswordField getPasswordTF() {
		return passwordTF;
	}

	public JPasswordField getPasswordTF1() {
		return passwordTF1;
	}

	public JComboBox<String> getGenderCB() {
		return genderCB;
	}

	public JTextField getNameTF() {
		return nameTF;
	}

	
	
	

	
}
