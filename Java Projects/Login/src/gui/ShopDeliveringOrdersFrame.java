package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.ShopController;
import gui_support.RoundJTextField;

@SuppressWarnings("serial")
public class ShopDeliveringOrdersFrame extends ComplexFrame {

	private String[] columns = { "ID", "Data", "Indirizzo", "Pagamento", "Note", "Cliente", "Rider" };
	private String[] status = { "Consegnato", "Errore" };
	private JTextField orderTF;
	private JComboBox<String> statusCB;
	private ShopController shop_controller;

	// Costruttore
	public ShopDeliveringOrdersFrame(ShopController shop_controller) {
		initialize();
		frameSetup();
		events();
		this.shop_controller = shop_controller;
	}

	private void initialize() {
		table_title = new ImageIcon("src\\images\\others\\deliveringOrders.png");
		statusCB = new JComboBox<String>(status);
		orderTF = new RoundJTextField(new Color(0x771007));
		table.setModel(model = new DefaultTableModel(columns, 0));
	}

	private void frameSetup() {

		// Layout setup
		setTitle("Food Overflow - Shop Panel: Ordini in consegna");
		table_titleLB.setPreferredSize(new Dimension(500, 50));
		table_titleLB.setIcon(table_title);

		// Textfields setup
		createTextField(orderTF, "Inserisci l'ID dell'ordine", long_dim_of_textfield);
		attributes_panel.add(orderTF);

		statusCB.setPreferredSize(long_dim_of_textfield);
		statusCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		statusCB.setFocusable(false);
		statusCB.setBackground(Color.white);
		attributes_panel.add(statusCB);

		// Buttons & Label setup
		table_titleLB.setIcon(table_title);
		table_titleLB.setSize(225, 100);
		scroll_pane.setBorder(BorderFactory.createLineBorder(new Color(0x771007), 5));

		buttons_panel.add(update_sqlJB);
		buttons_panel.add(go_backJB);

	}

	private void events() {

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (!table.getSelectionModel().isSelectionEmpty()) {

					orderTF.setText(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());

				}
			}

		});

		update_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.updateDeliveringOrder(ShopDeliveringOrdersFrame.this);
			}
		});

		go_backJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.openShopOrderManagementFrame(ShopDeliveringOrdersFrame.this);
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

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				shop_controller.releaseAllDaoResourcesAndDisposeFrame(ShopDeliveringOrdersFrame.this);
			}
		});

	}

	public JTextField getOrderTF() {
		return orderTF;
	}

	public JComboBox<String> getStatusCB() {
		return statusCB;
	}

}
