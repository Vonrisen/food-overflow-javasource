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
public class LoginFrame extends JFrame {

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel top_panel;
	private JLabel logoLB;
	private JPanel login_panel;
	private ImageIcon logoIMG;
	private JButton adminJB;
	private JButton shopJB;
	private JButton homeJB;
	private JButton customerJB;
	private JButton loginJB;
	private JButton registerJB;

	private JLabel usernameLB;
	private JLabel passwordLB;

	private ImageIcon usernameIMG;
	private ImageIcon passwordIMG;

	private ImageIcon home_button_activeIMG;
	private ImageIcon admin_button_activeIMG;
	private ImageIcon shop_button_activeIMG;
	private ImageIcon admin_button_inactiveIMG;
	private ImageIcon shop_button_inactiveIMG;
	private ImageIcon home_button_inactiveIMG;
	private ImageIcon customer_button_inactiveIMG;
	private ImageIcon customer_button_activeIMG;
	private ImageIcon login_button_activeIMG;
	private ImageIcon login_button_inactiveIMG;
	private ImageIcon register_button_activeIMG;
	private ImageIcon register_button_inactiveIMG;
	private ImageIcon admin_logoIMG;
	private ImageIcon shop_logoIMG;
	private ImageIcon customer_logoIMG;

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

		logoIMG = new ImageIcon("src\\images\\startup\\Logo.png");
		admin_button_activeIMG = new ImageIcon("src\\images\\startup\\adminButtonActive.png");
		admin_button_inactiveIMG = new ImageIcon("src\\images\\startup\\adminButtonInactive.png");
		home_button_activeIMG = new ImageIcon("src\\images\\startup\\homeButtonActive.png");
		home_button_inactiveIMG = new ImageIcon("src\\images\\startup\\homeButtonInactive.png");
		shop_button_activeIMG = new ImageIcon("src\\images\\startup\\shopButtonActive.png");
		shop_button_inactiveIMG = new ImageIcon("src\\images\\startup\\shopButtonInactive.png");
		customer_button_activeIMG = new ImageIcon("src\\images\\startup\\customerButtonActive.png");
		customer_button_inactiveIMG = new ImageIcon("src\\images\\startup\\customerButtonInactive.png");
		usernameIMG = new ImageIcon("src\\images\\startup/usernameIcon.png");
		passwordIMG = new ImageIcon("src\\images\\startup\\passwordIcon.png");
		login_button_activeIMG = new ImageIcon("src\\images\\startup\\loginButtonActive.png");
		login_button_inactiveIMG = new ImageIcon("src\\images\\startup\\loginButtonInactive.png");
		admin_logoIMG = new ImageIcon("src\\images\\startup\\adminLogo.png");
		shop_logoIMG = new ImageIcon("src\\images\\startup\\shopLogo.png");
		customer_logoIMG = new ImageIcon("src\\images\\startup\\customerLogo.png");
		register_button_activeIMG = new ImageIcon("src\\images\\startup\\registerButtonActive.png");
		register_button_inactiveIMG = new ImageIcon("src\\images\\startup\\registerButtonInactive.png");

		top_panel = new JPanel();
		login_panel = new JPanel();
		logoLB = new JLabel();
		adminJB = new JButton();
		shopJB = new JButton();
		customerJB = new JButton();
		homeJB = new JButton();
		loginJB = new JButton();
		registerJB = new JButton();
		usernameLB = new JLabel();
		passwordLB = new JLabel();
		usernameTF = new RoundJTextField(new Color(0x771007));
		passwordTF = new RoundJPasswordField(new Color(0x771007));

	}

	private void setupFrame() {

		// Layout setup

		this.setResizable(false);
		setIconImage(new ImageIcon("src\\images\\startup\\icon.png").getImage());
		this.setSize(600, 800);
		this.setTitle("Food Overflow");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		int central_width = screen_dim.width / 2 - this.getSize().width / 2;
		int central_height = screen_dim.height / 2 - this.getSize().height / 2;
		this.setLocation(central_width, central_height); // Setta il frame a centro monitor
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().setBackground(new Color(0xf3ecd7));

		top_panel.setBounds(0, 0, 600, 400);
		top_panel.setBackground(null);
		top_panel.setLayout(new BorderLayout());
		this.getContentPane().add(top_panel, BorderLayout.NORTH);

		login_panel.setLayout(null);
		login_panel.setBounds(0, 401, 584, 512);
		login_panel.setBackground(null);
		this.getContentPane().add(login_panel, BorderLayout.CENTER);

		// Textfields setup

		usernameLB.setIcon(usernameIMG);
		usernameLB.setBounds(141, 46, 25, 25);
		usernameLB.setVisible(false);
		login_panel.add(usernameLB);

		passwordLB.setIcon(passwordIMG);
		passwordLB.setBounds(141, 91, 25, 25);
		passwordLB.setVisible(false);
		login_panel.add(passwordLB);

		usernameTF.setText("Inserisci ID o E-mail");
		usernameTF.setBounds(177, 45, 250, 30);
		usernameTF.setVisible(false);
		login_panel.add(usernameTF);

		passwordTF.setText("Inserisci password");
		passwordTF.setEchoChar((char) 0);
		passwordTF.setBounds(177, 90, 250, 30);
		passwordTF.setVisible(false);
		login_panel.add(passwordTF);

		// Buttons & Label setup

		logoLB.setIcon(logoIMG);
		logoLB.setHorizontalAlignment(JLabel.CENTER);
		top_panel.add(logoLB);

		setupButton(adminJB, admin_button_inactiveIMG);
		adminJB.setBounds(322, 230, 150, 150);
		login_panel.add(adminJB);

		setupButton(shopJB, shop_button_inactiveIMG);
		shopJB.setBounds(112, 230, 150, 150);
		login_panel.add(shopJB);

		setupButton(customerJB, customer_button_inactiveIMG);
		customerJB.setBounds(112, 20, 360, 180);
		login_panel.add(customerJB);

		setupButton(homeJB, home_button_inactiveIMG);
		homeJB.setBounds(267, 280, 50, 50);
		homeJB.setVisible(false);
		login_panel.add(homeJB);

		setupButton(registerJB, register_button_inactiveIMG);
		registerJB.setBounds(294, 150, 150, 30);
		registerJB.setVisible(false);
		login_panel.add(registerJB);

		setupButton(loginJB, login_button_inactiveIMG);
		loginJB.setBounds(134, 150, 150, 30);
		loginJB.setVisible(false);
		login_panel.add(loginJB);

	}

	private void events() {

		customerJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				loginJB.setBounds(134, 150, 150, 30);

				homeJB.setVisible(true);
				shopJB.setVisible(false);
				usernameTF.setVisible(true);
				customerJB.setVisible(false);
				passwordTF.setVisible(true);
				usernameLB.setVisible(true);
				passwordLB.setVisible(true);
				loginJB.setVisible(true);
				registerJB.setVisible(true);
				adminJB.setVisible(false);
				logoLB.setIcon(customer_logoIMG);

			}

			@Override
			public void mouseEntered(MouseEvent cursore_su_customerButton) {

				customerJB.setIcon(customer_button_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent cursore_fuori_customerButton) {

				customerJB.setIcon(customer_button_inactiveIMG);

			}

		});

		adminJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent click_su_adminButton) {

				loginJB.setBounds(215, 150, 150, 30);

				homeJB.setVisible(true);
				shopJB.setVisible(false);
				usernameTF.setVisible(true);
				customerJB.setVisible(false);
				passwordTF.setVisible(true);
				usernameLB.setVisible(true);
				passwordLB.setVisible(true);
				loginJB.setVisible(true);
				adminJB.setVisible(false);
				logoLB.setIcon(admin_logoIMG);

			}

			@Override
			public void mouseEntered(MouseEvent cursore_su_adminButton) {

				adminJB.setIcon(admin_button_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent cursore_fuori_adminButton) {

				adminJB.setIcon(admin_button_inactiveIMG);

			}
		});

		shopJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent click_su_shopButton) {

				loginJB.setBounds(215, 150, 150, 30);

				homeJB.setVisible(true);
				adminJB.setVisible(false);
				shopJB.setVisible(false);
				customerJB.setVisible(false);
				usernameTF.setVisible(true);
				passwordTF.setVisible(true);
				usernameLB.setVisible(true);
				passwordLB.setVisible(true);
				loginJB.setVisible(true);
				logoLB.setIcon(shop_logoIMG);
			}

			@Override
			public void mouseEntered(MouseEvent cursore_su_shopButton) {

				shopJB.setIcon(shop_button_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent cursore_fuori_shopButton) {

				shopJB.setIcon(shop_button_inactiveIMG);

			}
		});

		homeJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent click_su_shopButton) {

				homeJB.setVisible(false);
				usernameTF.setVisible(false);
				passwordTF.setVisible(false);
				customerJB.setVisible(true);
				loginJB.setVisible(false);
				registerJB.setVisible(false);
				usernameLB.setVisible(false);
				passwordLB.setVisible(false);
				shopJB.setVisible(true);
				adminJB.setVisible(true);
				usernameTF.setText("Inserisci ID o E-mail");
				passwordTF.setText("Inserisci password");
				passwordTF.setEchoChar((char) 0);
				logoLB.setIcon(logoIMG);
			}
		});

		homeJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent cursore_su_homeButton) {

				homeJB.setIcon(home_button_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent cursore_fuori_homeButton) {

				homeJB.setIcon(home_button_inactiveIMG);

			}
		});

		loginJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent cursore_su_loginButton) {

				loginJB.setIcon(login_button_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent cursore_fuori_loginButton) {

				loginJB.setIcon(login_button_inactiveIMG);

			}
		});

		registerJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent cursore_su_loginButton) {

				registerJB.setIcon(register_button_activeIMG);

			}

			public void mousePressed(MouseEvent cursore_su_loginButton) {

				login_controller.openRegisterFrame(LoginFrame.this);

			}

			@Override
			public void mouseExited(MouseEvent cursore_fuori_loginButton) {

				registerJB.setIcon(register_button_inactiveIMG);

			}
		});

		loginJB.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent validazione_credenziali_admin) {
				
				if ((usernameTF.getText().equals("Inserisci ID o E-mail"))
						|| (passwordTF.getText().equals("Inserisci password"))) {
					JOptionPane.showMessageDialog(null, "Uno o più campi di testo sono vuoti", "Errore",
							JOptionPane.INFORMATION_MESSAGE);

				} else
					login_controller.accessAuthentication(LoginFrame.this);
				
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					LoginFrame.this.getRootPane().setDefaultButton(loginJB);

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
		return logoLB;
	}

	public ImageIcon getAdminLogoImage() {
		return admin_logoIMG;
	}

	public ImageIcon getShopLogoImage() {
		return shop_logoIMG;
	}

	public JTextField getUsernameTF() {
		return usernameTF;
	}

	public JPasswordField getPasswordTF() {
		return passwordTF;
	}

}
