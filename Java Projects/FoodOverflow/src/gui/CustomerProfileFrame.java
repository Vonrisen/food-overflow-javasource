package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.CustomerController;
import gui_support.RoundJPasswordField;
import gui_support.RoundJTextField;

@SuppressWarnings("serial")
public class CustomerProfileFrame extends JFrame {

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
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
	private ImageIcon change_fiscal_code_inactiveIMG;
	private ImageIcon change_fiscal_code_activeIMG;
	private ImageIcon change_password_inactiveIMG;
	private ImageIcon change_password_activeIMG;

	private JLabel avatarLB;
	private JLabel background;

	private JButton edit_authJB;
	private JButton edit_addressJB;
	private JButton updateJB;
	private JButton change_fiscal_codeJB;
	private JButton change_passwordJB;
	private JButton go_backJB;

	private JTextField address_nameTF;
	private JTextField address_civic_numberTF;
	private JTextField address_capTF;
	private JTextField fiscal_codeTF;
	private JComboBox<String> address_provinceCB;
	private JComboBox<String> address_townCB;

	private JPasswordField old_passwordTF;
	private JPasswordField passwordTF;
	private CustomerController customer_controller;

	public CustomerProfileFrame(CustomerController customer_controller) {

		initialize();
		frameSetup();
		events();
		this.customer_controller = customer_controller;
	}

	// Initialize variables
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
		change_fiscal_code_inactiveIMG = new ImageIcon("src\\images\\buttons\\changeFiscalCodeInactive.png");
		change_fiscal_code_activeIMG = new ImageIcon("src\\images\\buttons\\changeFiscalCodeActive.png");
		change_password_inactiveIMG = new ImageIcon("src\\images\\buttons\\changePasswordInactive.png"); 
		change_password_activeIMG = new ImageIcon("src\\images\\buttons\\changePasswordActive.png"); 

		avatarLB = new JLabel();

		edit_authJB = new JButton();
		edit_addressJB = new JButton();
		updateJB = new JButton();
		change_passwordJB = new JButton();
		change_fiscal_codeJB = new JButton();
		go_backJB = new JButton();

		address_nameTF = new RoundJTextField(new Color(0x771007));
		address_civic_numberTF = new RoundJTextField(new Color(0x771007));
		address_capTF = new RoundJTextField(new Color(0x771007));
		address_provinceCB = new JComboBox<String>();
		address_townCB = new JComboBox<String>();
		fiscal_codeTF = new RoundJTextField(new Color(0x771007));
		old_passwordTF = new RoundJPasswordField(new Color(0x771007));
		passwordTF = new RoundJPasswordField(new Color(0x771007));

	}

	private void frameSetup() {

		// Layout setup

		this.setTitle("Food Overflow - Profilo");
		this.setSize(500, 720);
		background.setIcon(resize(backgroundIMG, this.getWidth(), this.getHeight()));
		setIconImage(new ImageIcon("src\\images\\startup\\icon.png").getImage());
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int central_width = screen_dim.width / 2 - this.getSize().width / 2;
		int central_height = screen_dim.height / 2 - this.getSize().height / 2;
		this.setLocation(central_width, central_height); // Setta il frame a centro monitor
		this.getContentPane().setLayout(null);
		this.setContentPane(background);
		this.getContentPane().setLayout(null);

		//

		avatarLB.setBounds(118, 20, 500, 280);
		this.getContentPane().add(avatarLB);

		//
		createTextField(address_nameTF, "Nome dell'indirizzo");
		address_nameTF.setBounds(83, 325, 150, 25);
		this.getContentPane().add(address_nameTF);

		createTextField(address_civic_numberTF, "Numero civico");
		address_civic_numberTF.setBounds(253, 325, 150, 25);
		this.getContentPane().add(address_civic_numberTF);

		this.getContentPane().add(address_provinceCB);
		address_provinceCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		address_provinceCB.setFocusable(false);
		address_provinceCB.setBackground(Color.white);
		address_provinceCB.setVisible(false);
		address_provinceCB.setBounds(83, 375, 150, 25);

		this.getContentPane().add(address_townCB);
		address_townCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		address_townCB.setFocusable(false);
		address_townCB.setBackground(Color.white);
		address_townCB.setVisible(false);
		address_townCB.setBounds(253, 375, 150, 25);

		createTextField(address_capTF, "CAP");
		address_capTF.setBounds(168, 425, 150, 25);
		this.getContentPane().add(address_capTF);

		//

		createTextField(fiscal_codeTF, "Codice fiscale nuovo");
		fiscal_codeTF.setBounds(83,340,320,25);
		this.getContentPane().add(fiscal_codeTF);
		
		createTextField(old_passwordTF, "Vecchia password");
		old_passwordTF.setEchoChar((char) 0);
		old_passwordTF.setBounds(83, 375, 150, 25);
		this.getContentPane().add(old_passwordTF);

		createTextField(passwordTF, "Nuova password");
		passwordTF.setEchoChar((char) 0);
		passwordTF.setBounds(253, 375, 150, 25);
		this.getContentPane().add(passwordTF);

		//

		setupButton(edit_authJB, edit_auth_inactiveIMG);
		edit_authJB.setBounds(285, 400, 150, 100);
		this.getContentPane().add(edit_authJB);

		setupButton(edit_addressJB, edit_address_inactiveIMG);
		edit_addressJB.setBounds(50, 400, 150, 100);
		this.getContentPane().add(edit_addressJB);

		setupButton(updateJB, update_inactiveIMG);
		updateJB.setBounds(168, 480, 150, 30);
		updateJB.setVisible(false);
		this.getContentPane().add(updateJB);

		setupButton(change_fiscal_codeJB, change_fiscal_code_inactiveIMG);
		change_fiscal_codeJB.setBounds(78,440,335,30);
		change_fiscal_codeJB.setVisible(false);
		this.getContentPane().add(change_fiscal_codeJB);
		
		setupButton(change_passwordJB, change_password_inactiveIMG);
		change_passwordJB.setBounds(78,480,335,30);
		change_passwordJB.setVisible(false);
		this.getContentPane().add(change_passwordJB);
		
		setupButton(go_backJB, go_back_inactiveIMG);
		go_backJB.setBounds(168, 625, 150, 30);
		go_backJB.setVisible(false);
		this.getContentPane().add(go_backJB);

		address_provinceCB.addItemListener(this::addressProvinceCBitemStateChanged);

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
				old_passwordTF.setVisible(true);
				passwordTF.setVisible(true);
				change_passwordJB.setVisible(true);
				change_fiscal_codeJB.setVisible(true);
				go_backJB.setVisible(true);
				fiscal_codeTF.setVisible(true);
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


					customer_controller.updateDeliveryAddress(CustomerProfileFrame.this);

			}
		});
		
		
		change_fiscal_codeJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				change_fiscal_codeJB.setIcon(change_fiscal_code_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent e) {

				change_fiscal_codeJB.setIcon(change_fiscal_code_inactiveIMG);

			}

			public void mousePressed(MouseEvent e) {


				customer_controller.updateCustomerCF(CustomerProfileFrame.this);

			}
		});
		
		change_passwordJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				change_passwordJB.setIcon(change_password_activeIMG);

			}

			@Override
			public void mouseExited(MouseEvent e) {

				change_passwordJB.setIcon(change_password_inactiveIMG);

			}

			public void mousePressed(MouseEvent e) {


				customer_controller.updateCustomerPassword(CustomerProfileFrame.this);

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

				if (address_nameTF.isVisible()) {

					address_nameTF.setVisible(false);
					address_civic_numberTF.setVisible(false);
					address_capTF.setVisible(false);
					address_provinceCB.setVisible(false);
					address_townCB.setVisible(false);
					updateJB.setVisible(false);

				} else {

					old_passwordTF.setVisible(false);
					passwordTF.setVisible(false);
					fiscal_codeTF.setVisible(false);
					change_passwordJB.setVisible(false);
					change_fiscal_codeJB.setVisible(false);

				}

				
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

		fiscal_codeTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {

				textFieldFocusGained(fiscal_codeTF, "Codice fiscale nuovo");

			}

			@Override
			public void focusLost(FocusEvent e) {

				textFieldFocusLost(fiscal_codeTF, "Codice fiscale nuovo");

			}
		});

		old_passwordTF.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {

				if (old_passwordTF.getText().equals("Vecchia password")) {

					old_passwordTF.setEchoChar('•');
					old_passwordTF.setText("");
				}
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {

				if (old_passwordTF.getText().equals("")) {

					old_passwordTF.setText("Vecchia password");
					old_passwordTF.setEchoChar((char) 0);
				}
			}
		});

		passwordTF.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {

				if (passwordTF.getText().equals("Nuova password")) {

					passwordTF.setEchoChar('•');
					passwordTF.setText("");
				}
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {

				if (passwordTF.getText().equals("")) {

					passwordTF.setText("Nuova password");
					passwordTF.setEchoChar((char) 0);
				}
			}
		});

	}

	private void addressProvinceCBitemStateChanged(ItemEvent e) {

		if (e.getStateChange() == ItemEvent.SELECTED) {
			String selected_item = (String) e.getItem();
			if (!selected_item.equals("-------------------") || !selected_item.equals("Seleziona provincia"))
				customer_controller.updateAddressTownsCB(selected_item, CustomerProfileFrame.this);
			if (selected_item.equals("Seleziona provincia")) {
				this.getAddress_townCB().removeAllItems();
				this.getAddress_townCB().addItem("Seleziona comune");
			}

		}

	}

	private ImageIcon resize(ImageIcon im, int w, int h) {

		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		Graphics2D gd = (Graphics2D) bi.createGraphics();
		gd.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		gd.drawImage(im.getImage(), 0, 0, w, h, null);
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

	public JLabel getAvatarLB() {
		return avatarLB;
	}

	public void setAvatarLB(JLabel avatarLB) {
		this.avatarLB = avatarLB;
	}

	public ImageIcon getMale_avatarIMG() {
		return male_avatarIMG;
	}

	public ImageIcon getFemale_avatarIMG() {
		return female_avatarIMG;
	}

	public JComboBox<String> getAddress_provinceCB() {
		return address_provinceCB;
	}

	public JComboBox<String> getAddress_townCB() {
		return address_townCB;
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

	public JPasswordField getOld_passwordTF() {
		return old_passwordTF;
	}

	public JPasswordField getPasswordTF() {
		return passwordTF;
	}
	
	public JTextField getFiscal_codeTF() {
		return fiscal_codeTF;
	}

}
