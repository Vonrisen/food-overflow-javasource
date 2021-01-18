package gui;
import gui_support.RoundJTextField;


import gui_support.RoundJPasswordField;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import controllers.LoginController;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	JPanel topPanel;
	JLabel logoLabel;
	JPanel loginPanel;
	public RoundJTextField usernameTF;
	public RoundJPasswordField passwordTF;
	JLabel usernameImage;
	JLabel passwordImage;
	JButton loginButton;
	JButton adminButton;
	JButton shopButton;
	JButton homeButton;
	ImageIcon logoImage;
	ImageIcon homeButtonActive;
	ImageIcon adminButtonActive;
	ImageIcon shopButtonActive;
	ImageIcon usernameIcon;
	ImageIcon passwordIcon;
	ImageIcon adminButtonInactive;
	ImageIcon shopButtonInactive;
	ImageIcon homeButtonInactive;
	ImageIcon loginButtonActive;
	ImageIcon loginButtonInactive;
	ImageIcon adminLogoImage;
	ImageIcon shopLogoImage;
	Dimension dim;
	JTable table;
	JScrollPane scrollPane;
	ImageIcon close_button;
	ImageIcon iconifier_button;
	ImageIcon logoIcon;
	private int mouseX=0;
	private int mouseY=0;
	
	public LoginFrame()
	{
		initialize();
	}
	public void initialize() {

		// IMAGES INITIALIZER
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		logoImage = new ImageIcon("src\\images\\LoginImages\\Logo.png");
		adminButtonActive = new ImageIcon("src\\images\\LoginImages\\adminButtonActive.png");
		adminButtonInactive = new ImageIcon("src\\images\\LoginImages\\adminButtonInactive.png");
		homeButtonActive = new ImageIcon("src\\images\\LoginImages\\homeButtonActive.png");
		homeButtonInactive = new ImageIcon("src\\images\\LoginImages\\homeButtonInactive.png");
		shopButtonActive = new ImageIcon("src\\images\\LoginImages\\shopButtonActive.png");
		shopButtonInactive = new ImageIcon("src\\images\\LoginImages\\shopButtonInactive.png");
		loginButtonActive = new ImageIcon("src\\images\\LoginImages\\loginButtonActive.png");
		loginButtonInactive = new ImageIcon("src\\images\\LoginImages\\loginButtonInactive.png");
		usernameIcon = new ImageIcon("src\\images\\LoginImages/usernameIcon.png");
		passwordIcon = new ImageIcon("src\\images\\LoginImages\\passwordIcon.png");
		adminLogoImage = new ImageIcon("src\\images\\LoginImages\\adminLogo.png");
		shopLogoImage = new ImageIcon("src\\images\\LoginImages\\shopLogo.png");
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		close_button = new ImageIcon("src\\images\\FrameIcons\\closeFrameButton.png");
		iconifier_button = new ImageIcon("src\\images\\FrameIcons\\iconifierFrameButton.png");
		logoIcon = new ImageIcon("src\\images\\FrameIcons\\logoIcon.png");

		// FRAME INITIALIZER
		this.setResizable(false);
		this.setSize(600, 800);
		int central_width = dim.width/2-this.getSize().width/2;
		int central_height = dim.height/2-this.getSize().height/2;
		this.setLocation(central_width, central_height); //Setta il frame a centro monitor
		this.setUndecorated(true);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().setBackground(new Color(0xf3ecd7));

		// PANELS INITIALIZER
		topPanel = new JPanel();
		topPanel.setBounds(0, 0, 600, 400);
		topPanel.setBackground(null);
		topPanel.setLayout(new BorderLayout());
		this.getContentPane().add(topPanel, BorderLayout.NORTH);

		loginPanel = new JPanel();
		loginPanel.setBounds(0, 401, 600, 512);
		loginPanel.setBackground(null);
		this.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);
		loginPanel.setBounds(0, 401, 584, 512);
		loginPanel.setBackground(null);
		this.getContentPane().add(loginPanel, BorderLayout.CENTER);
		loginPanel.setLayout(null);
		
		JPanel framePanel = new JPanel();
		framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.LINE_AXIS));
		framePanel.setPreferredSize(new Dimension(200,35));
		framePanel.setBackground(new Color(0x771007));
		framePanel.setVisible(true);
		topPanel.add(framePanel, BorderLayout.NORTH);
		
		JLabel iconifierFrameButton = new JLabel();
		iconifierFrameButton.setIcon(iconifier_button);
		
		JLabel closeFrameButton = new JLabel();
		closeFrameButton.setIcon(close_button);
		
		JLabel logoFrameButton = new JLabel("<html> <font face='Calibri' size='4' color=rgb(243,236,215)>FOOD OVERFLOW</font> </html>");
		logoFrameButton.setForeground(new Color(0xf3ecd7));;
		logoFrameButton.setIcon(logoIcon);
		framePanel.add(logoFrameButton);
		framePanel.add(Box.createHorizontalGlue());
		framePanel.add(iconifierFrameButton);
		framePanel.add(closeFrameButton);
		
		// LABELS INITIALIZER
		logoLabel = new JLabel();
		logoLabel.setIcon(logoImage);
		logoLabel.setHorizontalAlignment(JLabel.CENTER);
		topPanel.add(logoLabel);
		usernameImage = new JLabel();
		usernameImage.setIcon(usernameIcon);
		usernameImage.setBounds(141, 46, 25, 25);
		usernameImage.setVisible(false);
		loginPanel.add(usernameImage);

		passwordImage = new JLabel();
		passwordImage.setIcon(passwordIcon);
		passwordImage.setBounds(141, 91, 25, 25);
		passwordImage.setVisible(false);
		loginPanel.add(passwordImage);

		// USERNAME TEXF FIELD INITIALIZER
		usernameTF = new RoundJTextField(new Color(0x771007));
		usernameTF.setText("Inserisci ID");
		usernameTF.setBounds(177, 45, 250, 30);
		usernameTF.setVisible(false);
		loginPanel.add(usernameTF);

		// PASSWORD TEXT FIELD INITIALIZER
		passwordTF = new RoundJPasswordField(new Color(0x771007));
		passwordTF.setText("Inserisci password");
		passwordTF.setEchoChar((char) 0);
		passwordTF.setBounds(177, 90, 250, 30);
		passwordTF.setVisible(false);
		loginPanel.add(passwordTF);

		// BUTTONS INITIALIZER
		adminButton = new JButton();
		adminButton.setIcon(adminButtonInactive);
		adminButton.setBorder(null);
		adminButton.setBounds(322, 80, 150, 150);
		adminButton.setFocusable(false);
		adminButton.setContentAreaFilled(false);
		loginPanel.add(adminButton);

		shopButton = new JButton();
		shopButton.setIcon(shopButtonInactive);
		shopButton.setBorder(null);
		shopButton.setBounds(112, 80, 150, 150);
		shopButton.setFocusable(false);
		shopButton.setContentAreaFilled(false);
		loginPanel.add(shopButton);

		loginButton = new JButton();
		loginButton.setIcon(loginButtonInactive);
		loginButton.setBounds(167, 150, 250, 30);
		loginButton.setBorder(null);
		loginButton.setFocusable(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setVisible(false);
		loginPanel.add(loginButton);

		homeButton = new JButton();
		homeButton.setIcon(homeButtonInactive);
		homeButton.setBounds(267, 280, 50, 50);
		homeButton.setBorder(null);
		homeButton.setFocusable(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setVisible(false);
		loginPanel.add(homeButton);

		// JSCROLLPANE INITIALIZER
		scrollPane = new JScrollPane();
		scrollPane.setBounds(123, 68, 372, 238);
		loginPanel.add(scrollPane);
		scrollPane.setVisible(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		// JTABLE INITIALIZER
		table = new JTable();
		scrollPane.setViewportView(table);

		// ACTIONS
		framePanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				LoginFrame.this.setLocation (LoginFrame.this.getX()+e.getX()-mouseX,LoginFrame.this.getY()+e.getY()-mouseY);
				
			}
		});
		framePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				mouseX = e.getX();
				mouseY = e.getY();
				
				}
		});
		
		closeFrameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.exit(0);
				
			}
		});
		
		iconifierFrameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				LoginFrame.this.setState(Frame.ICONIFIED);
				
			}
		});
		
		adminButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent click_su_adminButton) {

				homeButton.setVisible(true);
				shopButton.setVisible(false);
				usernameTF.setVisible(true);
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

				homeButton.setVisible(true);
				adminButton.setVisible(false);
				shopButton.setVisible(false);
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
				loginButton.setVisible(false);
				usernameImage.setVisible(false);
				passwordImage.setVisible(false);
				shopButton.setVisible(true);
				adminButton.setVisible(true);
				usernameTF.setText("Inserisci ID");
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

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent validazione_credenziali_admin) {
				
				LoginController login_controller = new LoginController();
				login_controller.accessAuthentication(LoginFrame.this);
				
			}
		});
		
		usernameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				if (usernameTF.getText().equals("Inserisci ID"))
					usernameTF.setText("");

			}

			@Override
			public void focusLost(FocusEvent e) {

				if (usernameTF.getText().equals(""))

					usernameTF.setText("Inserisci ID");
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
	public RoundJTextField getUsernameTF() {
		return usernameTF;
	}
	public void setUsernameTF(RoundJTextField usernameTF) {
		this.usernameTF = usernameTF;
	}
	public RoundJPasswordField getPasswordTF() {
		return passwordTF;
	}
	public void setPasswordTF(RoundJPasswordField passwordTF) {
		this.passwordTF = passwordTF;
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
	public JButton getLoginButton() {
		return loginButton;
	}
	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
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
	public ImageIcon getLogoImage() {
		return logoImage;
	}
	public void setLogoImage(ImageIcon logoImage) {
		this.logoImage = logoImage;
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
	public Dimension getDim() {
		return dim;
	}
	public void setDim(Dimension dim) {
		this.dim = dim;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	public ImageIcon getClose_button() {
		return close_button;
	}
	public void setClose_button(ImageIcon close_button) {
		this.close_button = close_button;
	}
	public ImageIcon getIconifier_button() {
		return iconifier_button;
	}
	public void setIconifier_button(ImageIcon iconifier_button) {
		this.iconifier_button = iconifier_button;
	}
	public ImageIcon getLogoIcon() {
		return logoIcon;
	}
	public void setLogoIcon(ImageIcon logoIcon) {
		this.logoIcon = logoIcon;
	}
	public int getMouseX() {
		return mouseX;
	}
	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}
	public int getMouseY() {
		return mouseY;
	}
	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
}
