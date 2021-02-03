package gui;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import controllers.ShopController;
import gui_support.StandardFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ShopViewOrdersFrame extends StandardFrame{

	private String[] columns = {"ID","Data","Orario di consegna","Indirizzo","Stato","Pagamento","Note","Cliente","Rider"};
	private ShopController shop_controller;


	//Costruttore
	public ShopViewOrdersFrame(ShopController shop_controller) {
		frame();
		this.shop_controller = shop_controller;
	}
	

	private void frame() {

		setTable_title(new ImageIcon("src\\images\\tableTitles\\orders.png"));
		getTable().setModel(model = new DefaultTableModel(columns, 0));

		this.setTitle("[Shop Panel] Ordini completati");
		getTable_titleLB().setIcon(getTable_title());

		getGo_backJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.openShopOrderManagementFrame(ShopViewOrdersFrame.this);
			}
		});

	}


}
