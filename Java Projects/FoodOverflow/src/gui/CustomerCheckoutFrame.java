package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import controllers.CustomerController;
import gui_support.RoundJTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class CustomerCheckoutFrame extends JFrame {

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	private ImageIcon summaryIMG;
	private ImageIcon addressIMG;
	private ImageIcon total_priceIMG;
	private ImageIcon cellphoneIMG;
	private ImageIcon shopIMG;
	private ImageIcon noteIMG;
	private ImageIcon order_inactive;
	private ImageIcon order_active;

	private JLabel summaryLB;
	private JLabel backgroundLB;
	private JLabel addressLB;
	private JLabel total_priceLB;
	private JLabel cellphoneLB;
	private JLabel shopLB;
	private JLabel noteLB;
	private JButton orderJB;

	private JTextField addressTF;
	private JTextField total_priceTF;
	private JTextField shopTF;
	private JTextField cellphoneTF;
	private JTextField noteTF;
	private CustomerController customer_controller;
	private CustomerCartFrame customer_cart_frame;

	public CustomerCheckoutFrame(CustomerController customer_controller, CustomerCartFrame customer_cart_frame) {

		initialize();
		frameSetup();
		events();
		this.customer_controller = customer_controller;
		this.customer_cart_frame = customer_cart_frame;

	}

	private void initialize() {

		summaryIMG = new ImageIcon("src\\images\\customer\\riepilogo.PNG");
		addressIMG = new ImageIcon("src\\images\\customer\\indirizzoConsegna.PNG");
		total_priceIMG = new ImageIcon("src\\images\\customer\\totalePrezzo.PNG");
		cellphoneIMG = new ImageIcon("src\\images\\customer\\telefonoRistorante.PNG");
		shopIMG = new ImageIcon("src\\images\\customer\\ristorante.PNG");
		noteIMG = new ImageIcon("src\\images\\customer\\note.PNG");
		order_inactive = new ImageIcon("src\\images\\customer\\ordinaInactive.PNG");
		order_active = new ImageIcon("src\\images\\customer\\ordinaActive.PNG");

		summaryLB = new JLabel();
		backgroundLB = new JLabel();
		addressLB = new JLabel();
		total_priceLB = new JLabel();
		cellphoneLB = new JLabel();
		shopLB = new JLabel();
		noteLB = new JLabel();

		orderJB = new JButton();

		addressTF = new RoundJTextField(new Color(0x771007));
		total_priceTF = new RoundJTextField(new Color(0x771007));
		shopTF = new RoundJTextField(new Color(0x771007));
		cellphoneTF = new RoundJTextField(new Color(0x771007));
		noteTF = new RoundJTextField(new Color(0x771007));

	}

	private void frameSetup() {

		this.setTitle("Food Overflow - Riepilogo dell'ordine");
		setIconImage(new ImageIcon("src\\images\\startup\\icon.png").getImage());
		this.setSize(400, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int central_width = screen_dim.width / 2 - this.getSize().width / 2;
		int central_height = screen_dim.height / 2 - this.getSize().height / 2;
		this.setLocation(central_width, central_height); // Setta il frame a centro monitor
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(new Color(0xf49969));

		summaryLB.setBounds(90, 30, 200, 50);
		summaryLB.setIcon(summaryIMG);
		this.getContentPane().add(summaryLB);

		addressLB.setIcon(addressIMG);
		addressLB.setBounds(90, 110, 200, 25);
		this.getContentPane().add(addressLB);

		addressTF.setEditable(false);
		addressTF.setBounds(50, 140, 280, 25);
		addressTF.setHorizontalAlignment(JTextField.CENTER);
		this.getContentPane().add(addressTF);

		shopLB.setIcon(shopIMG);
		shopLB.setBounds(115, 176, 150, 25);
		this.getContentPane().add(shopLB);

		shopTF.setEditable(false);
		shopTF.setBounds(50, 201, 280, 25);
		shopTF.setHorizontalAlignment(JTextField.CENTER);
		this.getContentPane().add(shopTF);

		cellphoneLB.setIcon(cellphoneIMG);
		cellphoneLB.setBounds(90, 237, 200, 25);
		this.getContentPane().add(cellphoneLB);

		cellphoneTF.setEditable(false);
		cellphoneTF.setBounds(116, 266, 150, 25);
		cellphoneTF.setHorizontalAlignment(JTextField.CENTER);
		this.getContentPane().add(cellphoneTF);

		total_priceLB.setIcon(total_priceIMG);
		total_priceLB.setBounds(115, 302, 150, 25);
		this.getContentPane().add(total_priceLB);

		total_priceTF.setEditable(false);
		total_priceTF.setBounds(164, 331, 50, 25);
		total_priceTF.setHorizontalAlignment(JTextField.CENTER);
		this.getContentPane().add(total_priceTF);

		noteLB.setIcon(noteIMG);
		noteLB.setBounds(115, 367, 150, 25);
		this.getContentPane().add(noteLB);

		noteTF.setEditable(true);
		noteTF.setBounds(50, 396, 280, 25);
		noteTF.setHorizontalAlignment(JTextField.LEFT);
		this.getContentPane().add(noteTF);

		backgroundLB.setBackground(new Color(0xf3ecd7));
		backgroundLB.setLayout(null);
		backgroundLB.setOpaque(true);
		backgroundLB.setBounds(40, 100, 300, 360);
		backgroundLB.setBorder(BorderFactory.createLineBorder(new Color(0x771007), 5));
		this.getContentPane().add(backgroundLB);

		orderJB.setIcon(order_inactive);
		orderJB.setBounds(90, 495, 200, 30);
		orderJB.setBorder(null);
		orderJB.setFocusable(false);
		orderJB.setContentAreaFilled(false);
		this.getContentPane().add(orderJB);

	}

	private void events() {

		orderJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				orderJB.setIcon(order_active);

			}

			@Override
			public void mouseExited(MouseEvent e) {

				orderJB.setIcon(order_inactive);

			}

			@Override
			public void mousePressed(MouseEvent e) {

				// Effettuo ordine, cancello il carrello, chiudo questo frame
				customer_controller.completeOrder(CustomerCheckoutFrame.this, customer_cart_frame);

			}
		});

	}

	public JButton getOrderJB() {
		return orderJB;
	}

	public JTextField getAddressTF() {
		return addressTF;
	}

	public JTextField getTotal_priceTF() {
		return total_priceTF;
	}

	public JTextField getShopTF() {
		return shopTF;
	}

	public JTextField getCellphoneTF() {
		return cellphoneTF;
	}

	public JTextField getNoteTF() {
		return noteTF;
	}

}
