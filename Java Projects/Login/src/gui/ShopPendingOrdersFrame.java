package gui;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controllers.ShopController;
import gui_support.RoundJTextField;


public class ShopPendingOrdersFrame extends ComplexFrame{

	private ImageIcon search_riders_inactiveIMG;
	private ImageIcon search_riders_activeIMG;
	private String[] columns = {"ID","Data","Indirizzo","Pagamento","Note","Cliente"};
	private JButton	riders_searchJB;
	private JTextField orderTF;
	private JTextField riderTF;
	private ShopController shop_controller;


	//Costruttore
	public ShopPendingOrdersFrame(ShopController shop_controller) {
		initialize();
		frameSetup();
		events();
		this.shop_controller = shop_controller;
	}


	private void initialize() {
		setTable_title(new ImageIcon("src\\images\\tableTitles\\orders.png"));
		search_riders_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\searchRidersButtonInactive.png");
		search_riders_activeIMG = new ImageIcon("src\\images\\SqlButtons\\searchRidersButtonActive.png");
		orderTF = new RoundJTextField(new Color(0x771007));
		riderTF = new RoundJTextField(new Color(0x771007));
		riders_searchJB = new JButton();
		getTable().setModel(model = new DefaultTableModel(columns, 0));
	}

	private void frameSetup() {

		//Layout setup
		this.setTitle("[Shop Panel] Ordini in attesa");


		//Textfields setup
		createTextField(orderTF, "Inserisci l'ID dell'ordine", getLong_dim_of_textfield());
		getAttributes_panel().add(orderTF);

		createTextField(riderTF, "Inserisci il CF del rider", getLong_dim_of_textfield());
		getAttributes_panel().add(riderTF);


		//Buttons & Label setup
		getTable_titleLB().setIcon(getTable_title());

		setupButton(riders_searchJB, search_riders_inactiveIMG, new Dimension(335,30));
		getAttributes_panel().add(riders_searchJB);

		getButtons_panel().add(getUpdate_sqlJB());
		getButtons_panel().add(getGo_backJB());

	}

	private void events() {

		getUpdate_sqlJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.updatePendingOrder(ShopPendingOrdersFrame.this);
			}
		});

		getGo_backJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.openShopOrderManagementFrame(ShopPendingOrdersFrame.this);
			}
		});

		orderTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(orderTF, "Inserisci l'ID dell'ordine");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(orderTF, "Inserisci l'ID dell'ordine");
			}
		});

		riderTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(riderTF, "Inserisci il CF del rider");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(riderTF, "Inserisci il CF del rider");
			}
		});


		riders_searchJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.openAdminRiderFrame();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				riders_searchJB.setIcon(search_riders_activeIMG);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				riders_searchJB.setIcon(search_riders_inactiveIMG);
			}

		});


	}


	public JTextField getOrderTF() {
		return orderTF;
	}

	public void setOrderTF(JTextField orderTF) {
		this.orderTF = orderTF;
	}

	public JTextField getRiderTF() {
		return riderTF;
	}

	public void setRiderTF(JTextField riderTF) {
		this.riderTF = riderTF;
	}

}
