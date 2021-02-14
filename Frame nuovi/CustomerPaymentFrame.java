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

import gui_support.RoundJTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerPaymentFrame extends JFrame{

	private Dimension screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
	private ImageIcon summaryIMG;
	private ImageIcon addressIMG;
	private ImageIcon total_priceIMG;
	private ImageIcon cellphoneIMG;
	private ImageIcon shopIMG;
	private ImageIcon ordina_inactiveIMG;
	private ImageIcon ordina_activeIMG;
	private JLabel summaryLB;
	private JLabel backgroundLB;
	private JLabel addressLB;
	private JLabel total_priceLB;
	private JLabel cellphoneLB;
	private JLabel shopLB;
	private JButton orderJB;
	
	private JTextField addressTF;
	private JTextField total_priceTF;
	private JTextField shopTF;
	private JTextField cellphoneTF;

	public CustomerPaymentFrame() {
		initialize();
		frameSetup();
		events();
	}

	private void initialize() {
		
		summaryIMG = new ImageIcon("src\\images\\CustomerImages\\riepilogo.PNG");
		addressIMG = new ImageIcon("src\\images\\CustomerImages\\indirizzoConsegna.PNG");
		total_priceIMG = new ImageIcon("src\\images\\CustomerImages\\totalePrezzo.PNG");
		cellphoneIMG = new ImageIcon("src\\images\\CustomerImages\\telefonoRistorante.PNG");
		shopIMG = new ImageIcon("src\\images\\CustomerImages\\ristorante.PNG");
		ordina_inactiveIMG = new ImageIcon("src\\images\\CustomerImages\\ordinaInactive.PNG");
		ordina_activeIMG = new ImageIcon("src\\images\\CustomerImages\\ordinaActive.PNG");
		
		summaryLB = new JLabel();
		backgroundLB = new JLabel();
		addressLB = new JLabel();
		total_priceLB = new JLabel();
		cellphoneLB = new JLabel();
		shopLB = new JLabel();
		
		orderJB = new JButton();
		
		addressTF = new RoundJTextField(new Color(0x771007));
		total_priceTF = new RoundJTextField(new Color(0x771007));
		shopTF = new RoundJTextField(new Color(0x771007));
		cellphoneTF = new RoundJTextField(new Color(0x771007));

		
		
	}
	
	private void frameSetup() {
		
		this.setTitle("Food Overflow - Riepilogo dell'ordine");
		this.setSize(400,500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int central_width = screen_dim.width/2-this.getSize().width/2;
		int central_height = screen_dim.height/2-this.getSize().height/2;
		this.setLocation(central_width, central_height); //Setta il frame a centro monitor
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(new Color(0xf49969));
		
		summaryLB.setBounds(90,30,200,50);
		summaryLB.setIcon(summaryIMG);
		this.getContentPane().add(summaryLB);
		
		addressLB.setIcon(addressIMG);
		addressLB.setBounds(90,110,200,25);
		this.getContentPane().add(addressLB);
		
		addressTF.setEditable(false);
		addressTF.setBounds(50,140,280,25);
		this.getContentPane().add(addressTF);
		
		shopLB.setIcon(shopIMG);
		shopLB.setBounds(115,176,150,25);
		this.getContentPane().add(shopLB);
		
		shopTF.setEditable(false);
		shopTF.setBounds(115,201,150,25);
		this.getContentPane().add(shopTF);
		
		cellphoneLB.setIcon(cellphoneIMG);
		cellphoneLB.setBounds(90,237,200,25);
		this.getContentPane().add(cellphoneLB);
		
		cellphoneTF.setEditable(false);
		cellphoneTF.setBounds(116,266,150,25);
		this.getContentPane().add(cellphoneTF);
		
		total_priceLB.setIcon(total_priceIMG);
		total_priceLB.setBounds(115,302,150,25);
		this.getContentPane().add(total_priceLB);
		
		total_priceTF.setEditable(false);
		total_priceTF.setBounds(164,331,50,25);
		this.getContentPane().add(total_priceTF);
		
		backgroundLB.setBackground(new Color(0xf3ecd7));
		backgroundLB.setLayout(null);
		backgroundLB.setOpaque(true);
		backgroundLB.setBounds(40,100,300,270);
		backgroundLB.setBorder(BorderFactory.createLineBorder(new Color(0x771007), 5));
		this.getContentPane().add(backgroundLB);

		orderJB.setIcon(ordina_inactiveIMG);
		orderJB.setBounds(90,401,200,30);
		orderJB.setBorder(null);
		orderJB.setFocusable(false);
		orderJB.setContentAreaFilled(false);
		this.getContentPane().add(orderJB);
		
	}
	
	private void events() {
		
		orderJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				orderJB.setIcon(ordina_activeIMG);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				orderJB.setIcon(ordina_inactiveIMG);
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				//Effettuo ordine, cancello il carrello, chiudo questo frame
				
			}
		});
		
	}

	public JButton getOrderJB() {
		return orderJB;
	}

	public void setOrderJB(JButton orderJB) {
		this.orderJB = orderJB;
	}

	public JTextField getAddressTF() {
		return addressTF;
	}

	public void setAddressTF(JTextField addressTF) {
		this.addressTF = addressTF;
	}

	public JTextField getTotal_priceTF() {
		return total_priceTF;
	}

	public void setTotal_priceTF(JTextField total_priceTF) {
		this.total_priceTF = total_priceTF;
	}

	public JTextField getShopTF() {
		return shopTF;
	}

	public void setShopTF(JTextField shopTF) {
		this.shopTF = shopTF;
	}

	public JTextField getCellphoneTF() {
		return cellphoneTF;
	}

	public void setCellphoneTF(JTextField cellphoneTF) {
		this.cellphoneTF = cellphoneTF;
	}

}
