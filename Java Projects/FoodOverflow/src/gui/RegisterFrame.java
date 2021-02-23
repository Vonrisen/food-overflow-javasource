package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.LoginController;
import gui_support.RoundJPasswordField;
import gui_support.RoundJTextField;

@SuppressWarnings("serial")
public class RegisterFrame extends JFrame {

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel top_panel;
	private JLabel logoLB;
	private JPanel login_panel;
	private JButton homeJB;
	private JButton forwardJB;
	private JButton backJB;
	private JButton registerJB;

	private ImageIcon home_button_activeIMG;
	private ImageIcon home_button_inactiveIMG;
	private ImageIcon register_logoIMG;
	private ImageIcon forward_button_activeIMG;
	private ImageIcon forward_button_inactiveIMG;
	private ImageIcon back_button_activeIMG;
	private ImageIcon back_button_inactiveIMG;
	private ImageIcon register_button_activeIMG;
	private ImageIcon register_button_inactiveIMG;

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
	private JPasswordField repeat_passwordTF;

	private JComboBox<String> genderCB;
	private JComboBox<String> birth_nationCB;
	private JComboBox<String> birth_provinceCB;
	private JComboBox<String> birth_townCB;

	private String[] gender_strings = { "Maschio", "Femmina" };

	private JLabel birth_cityLB;
	private JLabel birth_provinceLB;
	private JLabel birth_townLB;
	private JLabel birth_dateLB;

	private LoginController login_controller;

	public RegisterFrame(LoginController login_controller) {

		initialize();
		setupFrame();
		events();
		this.login_controller = login_controller;
	}

	private void initialize() {

		register_logoIMG = new ImageIcon("src\\images\\startup\\registerLogo.png");
		home_button_activeIMG = new ImageIcon("src\\images\\startup\\homeButtonActive.png");
		home_button_inactiveIMG = new ImageIcon("src\\images\\startup\\homeButtonInactive.png");
		forward_button_inactiveIMG = new ImageIcon("src\\images\\startup\\avantiButtonInactive.png");
		forward_button_activeIMG = new ImageIcon("src\\images\\startup\\avantiButtonActive.png");
		back_button_inactiveIMG = new ImageIcon("src\\images\\startup\\indietroButtonInactive.png");
		back_button_activeIMG = new ImageIcon("src\\images\\startup\\indietroButtonActive.png");
		register_button_activeIMG = new ImageIcon("src\\images\\startup\\registerButtonActive.png");
		register_button_inactiveIMG = new ImageIcon("src\\images\\startup\\registerButtonInactive.png");

		top_panel = new JPanel();
		login_panel = new JPanel();
		logoLB = new JLabel();
		homeJB = new JButton();
		forwardJB = new JButton();
		registerJB = new JButton();
		backJB = new JButton();

		birth_cityLB = new JLabel("Inserisci lo stato di nascita: ");
		birth_townCB = new JComboBox<String>();

		nameTF = new RoundJTextField(new Color(0x771007));
		surnameTF = new RoundJTextField(new Color(0x771007));
		birth_dateTF = new RoundJTextField(new Color(0x771007));
		address_nameTF = new RoundJTextField(new Color(0x771007));
		address_civic_numberTF = new RoundJTextField(new Color(0x771007));
		address_capTF = new RoundJTextField(new Color(0x771007));
		cellphoneTF = new RoundJTextField(new Color(0x771007));
		emailTF = new RoundJTextField(new Color(0x771007));
		passwordTF = new RoundJPasswordField(new Color(0x771007));
		repeat_passwordTF = new RoundJPasswordField(new Color(0x771007));

		address_townCB = new JComboBox<String>();
		address_provinceCB = new JComboBox<String>();

		genderCB = new JComboBox<String>(gender_strings);
		birth_nationCB = new JComboBox<String>();
		birth_provinceCB = new JComboBox<String>();
		birth_townLB = new JLabel("Inserisci il comune di nascita: ");
		birth_dateLB = new JLabel("Inserisci la data di nascita:");
		birth_provinceLB = new JLabel("Inserisci la provincia di nascita: ");

	}

	public void setupFrame() {

		// Layout setup

		this.setResizable(false);
		this.setSize(600, 800);
		setIconImage(new ImageIcon("src\\images\\startup\\icon.png").getImage());
		this.setTitle("Food Overflow - Registrazione");
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

		// 1# Textfield setup

		createTextField(nameTF, "Nome");
		nameTF.setBounds(120, 25, 150, 25);
		login_panel.add(nameTF);

		createTextField(surnameTF, "Cognome");
		surnameTF.setBounds(314, 25, 150, 25);
		login_panel.add(surnameTF);

		createTextField(address_nameTF, "Indirizzo di consegna");
		address_nameTF.setBounds(120, 97, 150, 25);
		login_panel.add(address_nameTF);

		createTextField(address_civic_numberTF, "Numero civico");
		address_civic_numberTF.setBounds(314, 97, 150, 25);
		login_panel.add(address_civic_numberTF);

		createTextField(address_capTF, "CAP");
		address_capTF.setBounds(120, 133, 150, 25);
		login_panel.add(address_capTF);

		address_townCB.setBounds(314, 61, 150, 25);
		setupComboBox(address_townCB);
		login_panel.add(address_townCB);

		address_provinceCB.setBounds(120, 61, 150, 25);
		setupComboBox(address_provinceCB);
		login_panel.add(address_provinceCB);

		genderCB.setBounds(314, 133, 150, 25);
		setupComboBox(genderCB);
		login_panel.add(genderCB);

		// 2# Textfield setup

		birth_provinceLB.setBounds(120, 66, 246, 14);
		birth_provinceLB.setVisible(false);
		login_panel.add(birth_provinceLB);

		birth_townLB.setBounds(120, 102, 246, 14);
		birth_townLB.setVisible(false);
		login_panel.add(birth_townLB);

		birth_cityLB.setBounds(122, 30, 267, 14);
		birth_cityLB.setVisible(false);
		login_panel.add(birth_cityLB);

		birth_nationCB.setBounds(305, 25, 150, 25);
		setupComboBox(birth_nationCB);
		birth_nationCB.setVisible(false);
		login_panel.add(birth_nationCB);

		birth_provinceCB.setBounds(305, 61, 150, 25);
		setupComboBox(birth_provinceCB);
		birth_provinceCB.setVisible(false);
		login_panel.add(birth_provinceCB);

		birth_townCB.setBounds(305, 97, 150, 25);
		setupComboBox(birth_townCB);
		birth_townCB.setVisible(false);
		login_panel.add(birth_townCB);

		createTextField(birth_dateTF, "dd/MM/yyyy");
		birth_dateTF.setVisible(false);
		birth_dateTF.setBounds(305, 133, 150, 25);
		login_panel.add(birth_dateTF);

		birth_dateLB.setBounds(120, 138, 246, 14);
		birth_dateLB.setVisible(false);
		login_panel.add(birth_dateLB);

		// 3# Textfield setup

		createTextField(emailTF, "E-mail");
		emailTF.setVisible(false);
		emailTF.setBounds(135, 30, 310, 25);
		login_panel.add(emailTF);

		createTextField(cellphoneTF, "Cellulare");
		cellphoneTF.setVisible(false);
		cellphoneTF.setBounds(135, 138, 310, 25);
		login_panel.add(cellphoneTF);

		passwordTF.setText("Inserisci password");
		passwordTF.setVisible(false);
		passwordTF.setHorizontalAlignment(JTextField.CENTER);
		passwordTF.setEchoChar((char) 0);
		passwordTF.setBounds(135, 66, 310, 25);
		login_panel.add(passwordTF);

		repeat_passwordTF.setText("Reinserisci la password");
		repeat_passwordTF.setVisible(false);
		repeat_passwordTF.setHorizontalAlignment(JTextField.CENTER);
		repeat_passwordTF.setEchoChar((char) 0);
		repeat_passwordTF.setBounds(135, 102, 310, 25);
		login_panel.add(repeat_passwordTF);

		// Buttons & Label setup

		logoLB.setIcon(register_logoIMG);
		logoLB.setFocusable(true);
		logoLB.setHorizontalAlignment(JLabel.CENTER);
		top_panel.add(logoLB);

		setupButton(forwardJB, forward_button_inactiveIMG);
		forwardJB.setBounds(314, 180, 150, 30);
		login_panel.add(forwardJB);

		setupButton(backJB, back_button_inactiveIMG);
		backJB.setVisible(false);
		backJB.setBounds(120, 180, 150, 30);
		login_panel.add(backJB);

		setupButton(registerJB, register_button_inactiveIMG);
		registerJB.setVisible(false);
		registerJB.setBounds(314, 180, 150, 30);
		login_panel.add(registerJB);

		setupButton(homeJB, home_button_inactiveIMG);
		homeJB.setBounds(267, 280, 50, 50);
		login_panel.add(homeJB);

	}

	private void events() {

		nameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(nameTF, "Nome");

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(nameTF, "Nome");

			}
		});

		surnameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(surnameTF, "Cognome");

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(surnameTF, "Cognome");

			}
		});

		address_nameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(address_nameTF, "Indirizzo di consegna");

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(address_nameTF, "Indirizzo di consegna");

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

		emailTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(emailTF, "E-mail");

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(emailTF, "E-mail");

			}
		});

		cellphoneTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(cellphoneTF, "Cellulare");

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(cellphoneTF, "Cellulare");

			}
		});

		birth_dateTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(birth_dateTF, "dd/MM/yyyy");

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(birth_dateTF, "dd/MM/yyyy");

			}
		});

		forwardJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				forwardJB.setIcon(forward_button_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent e) {

				forwardJB.setIcon(forward_button_inactiveIMG);

			}

			@Override
			public void mousePressed(MouseEvent e) {

				if (nameTF.isVisible() && !birth_nationCB.isVisible()) {

					nameTF.setVisible(false);
					surnameTF.setVisible(false);
					address_nameTF.setVisible(false);
					address_civic_numberTF.setVisible(false);
					address_capTF.setVisible(false);
					address_townCB.setVisible(false);
					address_provinceCB.setVisible(false);
					genderCB.setVisible(false);

					birth_provinceLB.setVisible(true);
					birth_townLB.setVisible(true);
					birth_cityLB.setVisible(true);
					birth_nationCB.setVisible(true);
					birth_provinceCB.setVisible(true);
					birth_townCB.setVisible(true);
					birth_dateTF.setVisible(true);
					birth_dateLB.setVisible(true);

					backJB.setVisible(true);

				} else if (birth_nationCB.isVisible() && !emailTF.isVisible()) {

					birth_provinceLB.setVisible(false);
					birth_townLB.setVisible(false);
					birth_cityLB.setVisible(false);
					birth_nationCB.setVisible(false);
					birth_provinceCB.setVisible(false);
					birth_townCB.setVisible(false);
					birth_dateTF.setVisible(false);
					birth_dateLB.setVisible(false);

					emailTF.setVisible(true);
					passwordTF.setVisible(true);
					repeat_passwordTF.setVisible(true);
					cellphoneTF.setVisible(true);

					forwardJB.setVisible(false);
					registerJB.setVisible(true);

				}

			}
		});

		backJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				backJB.setIcon(back_button_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent e) {

				backJB.setIcon(back_button_inactiveIMG);

			}

			@Override
			public void mousePressed(MouseEvent e) {

				if (!nameTF.isVisible() && birth_nationCB.isVisible()) {

					
					nameTF.setVisible(true);
					surnameTF.setVisible(true);
					address_nameTF.setVisible(true);
					address_civic_numberTF.setVisible(true);
					address_capTF.setVisible(true);
					address_townCB.setVisible(true);
					address_provinceCB.setVisible(true);
					genderCB.setVisible(true);

					birth_provinceLB.setVisible(false);
					birth_townLB.setVisible(false);
					birth_cityLB.setVisible(false);
					birth_nationCB.setVisible(false);
					birth_provinceCB.setVisible(false);
					birth_townCB.setVisible(false);
					birth_dateTF.setVisible(false);
					birth_dateLB.setVisible(false);
					backJB.setVisible(false);

				} else if (emailTF.isVisible() && !birth_nationCB.isVisible()) {

					birth_provinceLB.setVisible(true);
					birth_townLB.setVisible(true);
					birth_cityLB.setVisible(true);
					birth_nationCB.setVisible(true);
					birth_provinceCB.setVisible(true);
					birth_townCB.setVisible(true);
					birth_dateTF.setVisible(true);
					birth_dateLB.setVisible(true);

					emailTF.setVisible(false);
					passwordTF.setVisible(false);
					repeat_passwordTF.setVisible(false);
					cellphoneTF.setVisible(false);

					forwardJB.setVisible(true);
					registerJB.setVisible(false);

				}

			}
		});

		registerJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				registerJB.setIcon(register_button_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent e) {

				registerJB.setIcon(register_button_inactiveIMG);

			}

			@Override
			public void mousePressed(MouseEvent e) {

				
				login_controller.registerCustomer(RegisterFrame.this);

			}
		});

		homeJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent click_su_shopButton) {
				login_controller.openLoginFrame(RegisterFrame.this);

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

		passwordTF.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {

				if (passwordTF.getText().equals("Inserisci password")) {

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
					passwordTF.setText("Inserisci password");
					passwordTF.setEchoChar((char) 0);
				}
			}
		});

		repeat_passwordTF.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {

				if (repeat_passwordTF.getText().equals("Reinserisci la password")) {

					repeat_passwordTF.setHorizontalAlignment(JTextField.LEFT);
					repeat_passwordTF.setEchoChar('•');
					repeat_passwordTF.setText("");
				}
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {

				if (repeat_passwordTF.getText().equals("")) {

					repeat_passwordTF.setHorizontalAlignment(JTextField.CENTER);
					repeat_passwordTF.setText("Reinserisci la password");
					repeat_passwordTF.setEchoChar((char) 0);
				}
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				login_controller.releaseAllDaoResourcesAndDisposeFrame(RegisterFrame.this);
			}
		});

		birth_provinceCB.addItemListener(this::provincesCBitemStateChanged);
		birth_nationCB.addItemListener(this::townsCBitemStateChanged);
		address_provinceCB.addItemListener(this::addressProvinceCBitemStateChanged);

	}
	
	private void setupComboBox(JComboBox<String> cb) {

		cb.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		cb.setFocusable(false);
		cb.setBackground(Color.white);

	}

	private void provincesCBitemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String selected_item = (String) e.getItem();
			login_controller.updateTownsCB(selected_item, RegisterFrame.this);
		}
	}

	private void addressProvinceCBitemStateChanged(ItemEvent e) {

		if (e.getStateChange() == ItemEvent.SELECTED) {
			String selected_item = (String) e.getItem();
			if (!selected_item.equals("-------------------")
					|| !selected_item.equals("Provincia"))
				login_controller.updateAddressTownsCB(selected_item, RegisterFrame.this);
			;
			if (selected_item.equals("Provincia")) {
				this.getAddress_townCB().removeAllItems();
				this.getAddress_townCB().addItem("Comune");
			}

		}

	}

	private void townsCBitemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (birth_nationCB.getSelectedItem().equals("ITALIA")) {

				if (!nameTF.isVisible()) {
					birth_provinceCB.setVisible(true);
					birth_townCB.setVisible(true);
				}

			} else {

				birth_provinceCB.setVisible(false);
				birth_townCB.setVisible(false);

			}

		}
	}

	private void createTextField(JTextField text_field, String string) {

		text_field.setHorizontalAlignment(JTextField.CENTER);
		text_field.setText(string);

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
		return repeat_passwordTF;
	}

	public JComboBox<String> getGenderCB() {
		return genderCB;
	}

	public JTextField getNameTF() {
		return nameTF;
	}

}
