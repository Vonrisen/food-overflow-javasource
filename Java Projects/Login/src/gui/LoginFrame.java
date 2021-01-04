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
import controllers.AdminController;
import gui_support.RoundJPasswordField;
import gui_support.RoundJTextField;
import controllers.AdminController;
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
	/**a
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
	AdminController admin_controller = new AdminController();
	private final String username = "admin";
	private final String password = "admin";
	private int mouseX=0;
	private int mouseY=0;
	/**
	 * Create the application.
	 */

	/**
	 * Initialize the contents of the frame.
	 * 
	 */
	public LoginFrame()
	{
		initialize();
		this.setVisible(true);
	}
	public void initialize() {

		// IMAGES INITIALIZER

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
		
		ImageIcon close_button = new ImageIcon("src\\images\\FrameIcons\\closeFrameButton.png");
		ImageIcon iconifier_button = new ImageIcon("src\\images\\FrameIcons\\iconifierFrameButton.png");
		ImageIcon logoIcon = new ImageIcon("src\\images\\FrameIcons\\logoIcon.png");

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
				
				System.exit(0); // Da rivedere la chiusura del frame
				
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
				if (!usernameTF.getText().equals(username) || !passwordTF.getText().equals(password))
					System.out.println("Username o password sbagliati, riprovare");
				else
				{
					System.out.println("Login avvenuto con successo");
					LoginFrame.this.setVisible(false);
					admin_controller.openAdminFrame();	
				}
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
	public RoundJTextField getUsernameTF() {
		return usernameTF;
	}
	public RoundJPasswordField getPasswordTF() {
		return passwordTF;
	}
	public JLabel getUsernameImage() {
		return usernameImage;
	}
	public JLabel getPasswordImage() {
		return passwordImage;
	}
	public JButton getLoginButton() {
		return loginButton;
	}
	public JButton getAdminButton() {
		return adminButton;
	}
	public JButton getShopButton() {
		return shopButton;
	}
	public JButton getHomeButton() {
		return homeButton;
	}
	
}
