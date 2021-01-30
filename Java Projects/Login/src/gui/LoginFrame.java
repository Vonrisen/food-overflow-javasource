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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.LoginController;
import gui_support.RoundJPasswordField;
import gui_support.RoundJTextField;

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
	LoginController login_controller = new LoginController();
	public LoginFrame() {
		
		
		initialize();
		setupFrame();
		events();
	}

	private void initialize() {
		
		logoImage = new ImageIcon("src\\images\\LoginImages\\Logo.png");
		adminButtonActive = new ImageIcon("src\\images\\LoginImages\\adminButtonActive.png");
		adminButtonInactive = new ImageIcon("src\\images\\LoginImages\\adminButtonInactive.png");
		homeButtonActive = new ImageIcon("src\\images\\LoginImages\\homeButtonActive.png");
		homeButtonInactive = new ImageIcon("src\\images\\LoginImages\\homeButtonInactive.png");
		shopButtonActive = new ImageIcon("src\\images\\LoginImages\\shopButtonActive.png");
		shopButtonInactive = new ImageIcon("src\\images\\LoginImages\\shopButtonInactive.png");
		customerButtonActive = new ImageIcon("src\\images\\LoginImages\\customerButtonActive.png");
		customerButtonInactive = new ImageIcon("src\\images\\LoginImages\\customerButtonInactive.png");
		usernameIcon = new ImageIcon("src\\images\\LoginImages/usernameIcon.png");
		passwordIcon = new ImageIcon("src\\images\\LoginImages\\passwordIcon.png");
		loginButtonActive = new ImageIcon("src\\images\\LoginImages\\loginButtonActive.png");
		loginButtonInactive = new ImageIcon("src\\images\\LoginImages\\loginButtonInactive.png");
		adminLogoImage = new ImageIcon("src\\images\\LoginImages\\adminLogo.png");
		shopLogoImage = new ImageIcon("src\\images\\LoginImages\\shopLogo.png");
		customerLogoImage = new ImageIcon("src\\images\\LoginImages\\customerLogo.png");
		registerButtonActive = new ImageIcon("src\\images\\LoginImages\\registerButtonActive.png");
		registerButtonInactive = new ImageIcon("src\\images\\LoginImages\\registerButtonInactive.png");
		
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
		
		this.setResizable(false);
		this.setSize(600, 800);
		this.setTitle("Food Overflow");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		int central_width = screen_dim.width/2-this.getSize().width/2;
		int central_height = screen_dim.height/2-this.getSize().height/2;
		this.setLocation(central_width, central_height); //Setta il frame a centro monitor
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().setBackground(new Color(0xf3ecd7));
		
		// PANELS INITIALIZER

		topPanel.setBounds(0, 0, 600, 400);
		topPanel.setBackground(null);
		topPanel.setLayout(new BorderLayout());
		this.getContentPane().add(topPanel, BorderLayout.NORTH);


		loginPanel.setBounds(0, 401, 600, 512);
		loginPanel.setBackground(null);
		this.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);
		loginPanel.setBounds(0, 401, 584, 512);
		loginPanel.setBackground(null);
		this.getContentPane().add(loginPanel, BorderLayout.CENTER);
		
		// First Panel
		

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
		
		//Textfields
		
		usernameImage.setIcon(usernameIcon);
		usernameImage.setBounds(141, 46, 25, 25);
		usernameImage.setVisible(false);
		loginPanel.add(usernameImage);

		passwordImage.setIcon(passwordIcon);
		passwordImage.setBounds(141, 91, 25, 25);
		passwordImage.setVisible(false);
		loginPanel.add(passwordImage);
		
		// USERNAME TEXF FIELD INITIALIZER

		usernameTF.setText("Inserisci ID o E-mail");
		usernameTF.setBounds(177, 45, 250, 30);
		usernameTF.setVisible(false);
		loginPanel.add(usernameTF);

		// PASSWORD TEXT FIELD INITIALIZER

		passwordTF.setText("Inserisci password");
		passwordTF.setEchoChar((char) 0);
		passwordTF.setBounds(177, 90, 250, 30);
		passwordTF.setVisible(false);
		loginPanel.add(passwordTF);
		
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

			@Override
			public void mouseExited(MouseEvent cursore_fuori_loginButton) {

				registerButton.setIcon(registerButtonInactive);

			}
		});

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent validazione_credenziali_admin) {
				
				
				login_controller.accessAuthentication(LoginFrame.this);
				
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
			@Override
			public void focusGained(FocusEvent e) {

				if (passwordTF.getText().equals("Inserisci password")) {

					passwordTF.setEchoChar('•');
					passwordTF.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {

				if (passwordTF.getText().equals("")) {

					passwordTF.setText("Inserisci password");
					passwordTF.setEchoChar((char) 0);
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

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public Dimension getScreen_dim() {
		return screen_dim;
	}

	public void setScreen_dim(Dimension screen_dim) {
		this.screen_dim = screen_dim;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public JLabel getLogoLabel() {
		return logoLabel;
	}

	public void setLogoLabel(JLabel logoLabel) {
		this.logoLabel = logoLabel;
	}

	public JPanel getLoginPanel() {
		return loginPanel;
	}

	public void setLoginPanel(JPanel loginPanel) {
		this.loginPanel = loginPanel;
	}

	public ImageIcon getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(ImageIcon logoImage) {
		this.logoImage = logoImage;
	}

	public JButton getAdminButton() {
		return adminButton;
	}

	public void setAdminButton(JButton adminButton) {
		this.adminButton = adminButton;
	}

	public JButton getShopButton() {
		return shopButton;
	}

	public void setShopButton(JButton shopButton) {
		this.shopButton = shopButton;
	}

	public JButton getHomeButton() {
		return homeButton;
	}

	public void setHomeButton(JButton homeButton) {
		this.homeButton = homeButton;
	}

	public JButton getCustomerButton() {
		return customerButton;
	}

	public void setCustomerButton(JButton customerButton) {
		this.customerButton = customerButton;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public void setRegisterButton(JButton registerButton) {
		this.registerButton = registerButton;
	}

	public JLabel getUsernameImage() {
		return usernameImage;
	}

	public void setUsernameImage(JLabel usernameImage) {
		this.usernameImage = usernameImage;
	}

	public JLabel getPasswordImage() {
		return passwordImage;
	}

	public void setPasswordImage(JLabel passwordImage) {
		this.passwordImage = passwordImage;
	}

	public ImageIcon getUsernameIcon() {
		return usernameIcon;
	}

	public void setUsernameIcon(ImageIcon usernameIcon) {
		this.usernameIcon = usernameIcon;
	}

	public ImageIcon getPasswordIcon() {
		return passwordIcon;
	}

	public void setPasswordIcon(ImageIcon passwordIcon) {
		this.passwordIcon = passwordIcon;
	}

	public ImageIcon getHomeButtonActive() {
		return homeButtonActive;
	}

	public void setHomeButtonActive(ImageIcon homeButtonActive) {
		this.homeButtonActive = homeButtonActive;
	}

	public ImageIcon getAdminButtonActive() {
		return adminButtonActive;
	}

	public void setAdminButtonActive(ImageIcon adminButtonActive) {
		this.adminButtonActive = adminButtonActive;
	}

	public ImageIcon getShopButtonActive() {
		return shopButtonActive;
	}

	public void setShopButtonActive(ImageIcon shopButtonActive) {
		this.shopButtonActive = shopButtonActive;
	}

	public ImageIcon getAdminButtonInactive() {
		return adminButtonInactive;
	}

	public void setAdminButtonInactive(ImageIcon adminButtonInactive) {
		this.adminButtonInactive = adminButtonInactive;
	}

	public ImageIcon getShopButtonInactive() {
		return shopButtonInactive;
	}

	public void setShopButtonInactive(ImageIcon shopButtonInactive) {
		this.shopButtonInactive = shopButtonInactive;
	}

	public ImageIcon getHomeButtonInactive() {
		return homeButtonInactive;
	}

	public void setHomeButtonInactive(ImageIcon homeButtonInactive) {
		this.homeButtonInactive = homeButtonInactive;
	}

	public ImageIcon getCustomerButtonInactive() {
		return customerButtonInactive;
	}

	public void setCustomerButtonInactive(ImageIcon customerButtonInactive) {
		this.customerButtonInactive = customerButtonInactive;
	}

	public ImageIcon getCustomerButtonActive() {
		return customerButtonActive;
	}

	public void setCustomerButtonActive(ImageIcon customerButtonActive) {
		this.customerButtonActive = customerButtonActive;
	}

	public ImageIcon getLoginButtonActive() {
		return loginButtonActive;
	}

	public void setLoginButtonActive(ImageIcon loginButtonActive) {
		this.loginButtonActive = loginButtonActive;
	}

	public ImageIcon getLoginButtonInactive() {
		return loginButtonInactive;
	}

	public void setLoginButtonInactive(ImageIcon loginButtonInactive) {
		this.loginButtonInactive = loginButtonInactive;
	}

	public ImageIcon getRegisterButtonActive() {
		return registerButtonActive;
	}

	public void setRegisterButtonActive(ImageIcon registerButtonActive) {
		this.registerButtonActive = registerButtonActive;
	}

	public ImageIcon getRegisterButtonInactive() {
		return registerButtonInactive;
	}

	public void setRegisterButtonInactive(ImageIcon registerButtonInactive) {
		this.registerButtonInactive = registerButtonInactive;
	}

	public ImageIcon getAdminLogoImage() {
		return adminLogoImage;
	}

	public void setAdminLogoImage(ImageIcon adminLogoImage) {
		this.adminLogoImage = adminLogoImage;
	}

	public ImageIcon getShopLogoImage() {
		return shopLogoImage;
	}

	public void setShopLogoImage(ImageIcon shopLogoImage) {
		this.shopLogoImage = shopLogoImage;
	}

	public ImageIcon getCustomerLogoImage() {
		return customerLogoImage;
	}

	public void setCustomerLogoImage(ImageIcon customerLogoImage) {
		this.customerLogoImage = customerLogoImage;
	}

	public JTextField getUsernameTF() {
		return usernameTF;
	}

	public void setUsernameTF(RoundJTextField usernameTF) {
		this.usernameTF = usernameTF;
	}

	public JPasswordField getPasswordTF() {
		return passwordTF;
	}

	public void setPasswordTF(RoundJPasswordField passwordTF) {
		this.passwordTF = passwordTF;
	}

}
