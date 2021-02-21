package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.LoginController;
import gui_support.RoundJPasswordField;
import gui_support.RoundJTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame{

	private JFrame frame;
	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel topPanel;
	private JLabel logoLabel;
	private JPanel loginPanel;
	private ImageIcon logoImage;
	private JButton adminButton;
	private JButton shopButton;
	private JButton homeButton;
	private JButton customerButton;
	private JButton loginButton;
	private JButton registerButton;
	
	private JLabel usernameImage;
	private JLabel passwordImage;
	
	private ImageIcon usernameIcon;
	private ImageIcon passwordIcon;
	
	private ImageIcon homeButtonActive;
	private ImageIcon adminButtonActive;
	private ImageIcon shopButtonActive;
	private ImageIcon adminButtonInactive;
	private ImageIcon shopButtonInactive;
	private ImageIcon homeButtonInactive;
	private ImageIcon customerButtonInactive;
	private ImageIcon customerButtonActive;
	private ImageIcon loginButtonActive;
	private ImageIcon loginButtonInactive;
	private ImageIcon registerButtonActive;
	private ImageIcon registerButtonInactive;
	private ImageIcon adminLogoImage;
	private ImageIcon shopLogoImage;
	private ImageIcon customerLogoImage;

	private JTextField usernameTF;
	private JPasswordField passwordTF;
	private LoginController login_controller;
	public LoginFrame(LoginController login_controller) {
		
		initialize();
		setupFrame();
		events();
		this.login_controller = login_controller;
		
	}

	private void initialize() {
		
		logoImage = new ImageIcon("src\\images\\startup\\Logo.png");
		adminButtonActive = new ImageIcon("src\\images\\startup\\adminButtonActive.png");
		adminButtonInactive = new ImageIcon("src\\images\\startup\\adminButtonInactive.png");
		homeButtonActive = new ImageIcon("src\\images\\startup\\homeButtonActive.png");
		homeButtonInactive = new ImageIcon("src\\images\\startup\\homeButtonInactive.png");
		shopButtonActive = new ImageIcon("src\\images\\startup\\shopButtonActive.png");
		shopButtonInactive = new ImageIcon("src\\images\\startup\\shopButtonInactive.png");
		customerButtonActive = new ImageIcon("src\\images\\startup\\customerButtonActive.png");
		customerButtonInactive = new ImageIcon("src\\images\\startup\\customerButtonInactive.png");
		usernameIcon = new ImageIcon("src\\images\\startup/usernameIcon.png");
		passwordIcon = new ImageIcon("src\\images\\startup\\passwordIcon.png");
		loginButtonActive = new ImageIcon("src\\images\\startup\\loginButtonActive.png");
		loginButtonInactive = new ImageIcon("src\\images\\startup\\loginButtonInactive.png");
		adminLogoImage = new ImageIcon("src\\images\\startup\\adminLogo.png");
		shopLogoImage = new ImageIcon("src\\images\\startup\\shopLogo.png");
		customerLogoImage = new ImageIcon("src\\images\\startup\\customerLogo.png");
		registerButtonActive = new ImageIcon("src\\images\\startup\\registerButtonActive.png");
		registerButtonInactive = new ImageIcon("src\\images\\startup\\registerButtonInactive.png");
		
		topPanel = new JPanel();
		loginPanel = new JPanel();
		logoLabel = new JLabel();
		adminButton = new JButton();
		shopButton = new JButton();
		customerButton = new JButton();
		homeButton = new JButton();
		loginButton = new JButton();
		registerButton = new JButton();
		usernameImage = new JLabel();
		passwordImage = new JLabel();
		usernameTF = new RoundJTextField(new Color(0x771007));
		passwordTF = new RoundJPasswordField(new Color(0x771007));
		
	}
	
	public void setupFrame() {
		
		//Layout setup
		
		this.setResizable(false);
		setIconImage(new ImageIcon("src\\images\\startup\\icon.png").getImage());
		this.setSize(600, 800);
		this.setTitle("Food Overflow");
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
		
		//Textfields setup

		usernameImage.setIcon(usernameIcon);
		usernameImage.setBounds(141, 46, 25, 25);
		usernameImage.setVisible(false);
		loginPanel.add(usernameImage);

		passwordImage.setIcon(passwordIcon);
		passwordImage.setBounds(141, 91, 25, 25);
		passwordImage.setVisible(false);
		loginPanel.add(passwordImage);
		
		usernameTF.setText("Inserisci ID o E-mail");
		usernameTF.setBounds(177, 45, 250, 30);
		usernameTF.setVisible(false);
		loginPanel.add(usernameTF);

		passwordTF.setText("Inserisci password");
		passwordTF.setEchoChar((char) 0);
		passwordTF.setBounds(177, 90, 250, 30);
		passwordTF.setVisible(false);
		loginPanel.add(passwordTF);
		
		//Buttons & Label setup
		
		logoLabel.setIcon(logoImage);
		logoLabel.setHorizontalAlignment(JLabel.CENTER);
		topPanel.add(logoLabel);
		
		setupButton(adminButton, adminButtonInactive);
		adminButton.setBounds(322, 230, 150, 150);
		loginPanel.add(adminButton);

		setupButton(shopButton, shopButtonInactive);
		shopButton.setBounds(112, 230, 150, 150);
		loginPanel.add(shopButton);
		
		setupButton(customerButton, customerButtonInactive);
		customerButton.setBounds(112, 20, 360, 180);
		loginPanel.add(customerButton);
		
		setupButton(homeButton, homeButtonInactive);
		homeButton.setBounds(267, 280, 50, 50);
		homeButton.setVisible(false);
		loginPanel.add(homeButton);
		
		setupButton(registerButton, registerButtonInactive);
		registerButton.setBounds(294, 150, 150, 30);
		registerButton.setVisible(false);
		loginPanel.add(registerButton);
		
		setupButton(loginButton, loginButtonInactive);
		loginButton.setBounds(134, 150, 150, 30);
		loginButton.setVisible(false);
		loginPanel.add(loginButton);
		
	}
	
	public void events() {
		
		customerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				loginButton.setBounds(134, 150, 150, 30);
				
				homeButton.setVisible(true);
				shopButton.setVisible(false);
				usernameTF.setVisible(true);
				customerButton.setVisible(false);
				passwordTF.setVisible(true);
				usernameImage.setVisible(true);
				passwordImage.setVisible(true);
				loginButton.setVisible(true);
				registerButton.setVisible(true);
				adminButton.setVisible(false);
				logoLabel.setIcon(customerLogoImage);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent cursore_su_customerButton) {

				customerButton.setIcon(customerButtonActive);

			}

			@Override
			public void mouseExited(MouseEvent cursore_fuori_customerButton) {

				customerButton.setIcon(customerButtonInactive);

			}
			
		});
		
		adminButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent click_su_adminButton) {
				
				loginButton.setBounds(215, 150, 150, 30);
				
				homeButton.setVisible(true);
				shopButton.setVisible(false);
				usernameTF.setVisible(true);
				customerButton.setVisible(false);
				passwordTF.setVisible(true);
				usernameImage.setVisible(true);
				passwordImage.setVisible(true);
				loginButton.setVisible(true);
				adminButton.setVisible(false);
				logoLabel.setIcon(adminLogoImage);
				
			}

			@Override
			public void mouseEntered(MouseEvent cursore_su_adminButton) {

				adminButton.setIcon(adminButtonActive);

			}

			@Override
			public void mouseExited(MouseEvent cursore_fuori_adminButton) {

				adminButton.setIcon(adminButtonInactive);

			}
		});

		shopButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent click_su_shopButton) {
				
				loginButton.setBounds(215, 150, 150, 30);

				homeButton.setVisible(true);
				adminButton.setVisible(false);
				shopButton.setVisible(false);
				customerButton.setVisible(false);
				usernameTF.setVisible(true);
				passwordTF.setVisible(true);
				usernameImage.setVisible(true);
				passwordImage.setVisible(true);
				loginButton.setVisible(true);
				logoLabel.setIcon(shopLogoImage);
			}

			@Override
			public void mouseEntered(MouseEvent cursore_su_shopButton) {

				shopButton.setIcon(shopButtonActive);

			}

			@Override
			public void mouseExited(MouseEvent cursore_fuori_shopButton) {

				shopButton.setIcon(shopButtonInactive);

			}
		});

		homeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent click_su_shopButton) {
				
				homeButton.setVisible(false);
				usernameTF.setVisible(false);
				passwordTF.setVisible(false);
				customerButton.setVisible(true);
				loginButton.setVisible(false);
				registerButton.setVisible(false);
				usernameImage.setVisible(false);
				passwordImage.setVisible(false);
				shopButton.setVisible(true);
				adminButton.setVisible(true);
				usernameTF.setText("Inserisci ID o E-mail");
				passwordTF.setText("Inserisci password");
				passwordTF.setEchoChar((char) 0);
				logoLabel.setIcon(logoImage);
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

		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent cursore_su_loginButton) {

				loginButton.setIcon(loginButtonActive);

			}

			@Override
			public void mouseExited(MouseEvent cursore_fuori_loginButton) {

				loginButton.setIcon(loginButtonInactive);

			}
		});
		
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent cursore_su_loginButton) {

				registerButton.setIcon(registerButtonActive);

			}
			public void mousePressed(MouseEvent cursore_su_loginButton) {
				
				login_controller.openRegisterFrame(LoginFrame.this);

			}

			@Override
			public void mouseExited(MouseEvent cursore_fuori_loginButton) {

				registerButton.setIcon(registerButtonInactive);

			}
		});

		loginButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent validazione_credenziali_admin) {
				
				if((usernameTF.getText().equals("Inserisci ID o E-mail"))||(passwordTF.getText().equals("Inserisci password"))) {
					
					JOptionPane.showMessageDialog(null, "Uno o più campi di testo sono vuoti","Errore",JOptionPane.INFORMATION_MESSAGE);
					
				} else login_controller.accessAuthentication(LoginFrame.this);
				

				
			}
		});
		
		addWindowListener(new WindowAdapter()
	     {
	         @Override
	         public void windowClosing(WindowEvent e)
	         {
	             login_controller.releaseAllDaoResourcesAndDisposeFrame(LoginFrame.this);
	         }
	     });
		
		usernameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				if (usernameTF.getText().equals("Inserisci ID o E-mail"))
					usernameTF.setText("");

			}

			@Override
			public void focusLost(FocusEvent e) {

				if (usernameTF.getText().equals(""))

					usernameTF.setText("Inserisci ID o E-mail");
			}
		});

		passwordTF.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {

				if (passwordTF.getText().equals("Inserisci password")) {

					passwordTF.setEchoChar('•');
					passwordTF.setText("");
				}
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {

				if (passwordTF.getText().equals("")) {

					passwordTF.setText("Inserisci password");
					passwordTF.setEchoChar((char) 0);
				}
			}
		});
		
		passwordTF.addKeyListener(new KeyAdapter() {
			@Override
			
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	
			    	LoginFrame.this.getRootPane().setDefaultButton(loginButton);
			        
			    }
				
			}
		});
		
	}
		
	
	private void setupButton(JButton button, ImageIcon image) {
		
		button.setIcon(image);
		button.setBorder(null);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		
	}

	
	public JLabel getLogoLabel() {
		return logoLabel;
	}

	public ImageIcon getAdminLogoImage() {
		return adminLogoImage;
	}

	public ImageIcon getShopLogoImage() {
		return shopLogoImage;
	}


	public JTextField getUsernameTF() {
		return usernameTF;
	}

	public JPasswordField getPasswordTF() {
		return passwordTF;
	}

}
