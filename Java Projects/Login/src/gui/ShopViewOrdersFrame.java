package gui;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import javax.swing.table.DefaultTableModel;
import controllers.ShopController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class ShopViewOrdersFrame extends StandardFrame {

	private String[] columns = { "ID", "Data", "Orario di consegna", "Indirizzo", "Stato", "Pagamento", "Note",
			"Cliente", "Rider" };
	private ShopController shop_controller;

	// Costruttore
	public ShopViewOrdersFrame(ShopController shop_controller) {
		initialize();
		this.shop_controller = shop_controller;
	}

	private void initialize() {

		setTable_title(new ImageIcon("src\\images\\others\\completedOrders.png"));
		getTable().setModel(model = new DefaultTableModel(columns, 0));

		this.setTitle("Food Overflow - Shop Panel: Ordini completati");
		getTable_titleLB().setPreferredSize(new Dimension(500, 50));
		getTable_titleLB().setIcon(getTable_title());

		getScroll_pane().setBorder(BorderFactory.createLineBorder(new Color(0x771007), 5));

		getGo_backJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.openShopOrderManagementFrame(ShopViewOrdersFrame.this);
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				shop_controller.releaseAllDaoResourcesAndDisposeFrame(ShopViewOrdersFrame.this);
			}
		});

	}

}
