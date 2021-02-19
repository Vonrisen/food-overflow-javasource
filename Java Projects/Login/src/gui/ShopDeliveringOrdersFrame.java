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
import entities.Address;
import gui_support.RoundJTextField;
import utilities.InputUtility;


public class ShopDeliveringOrdersFrame extends ComplexFrame{

	private String[] columns = {"ID","Data","Indirizzo","Pagamento","Note","Cliente","Rider"};
	private String[] status = {"Consegnato", "Errore"};
	private JTextField orderTF;
	private JComboBox<String> statusCB;
	private ShopController shop_controller;


	//Costruttore
	public ShopDeliveringOrdersFrame(ShopController shop_controller) {
		initialize();
		frameSetup();
		events();
		this.shop_controller = shop_controller;
	}


	private void initialize() {
		setTable_title(new ImageIcon("src\\images\\others\\deliveringOrders.png"));
		statusCB = new JComboBox<String>(status);
		orderTF = new RoundJTextField(new Color(0x771007));
		getTable().setModel(model = new DefaultTableModel(columns, 0));
	}

	private void frameSetup() {

		//Layout setup
		setTitle("Food Overflow - Shop Panel: Ordini in consegna");
		getTable_titleLB().setPreferredSize(new Dimension(500,50));
		getTable_titleLB().setIcon(getTable_title());


		//Textfields setup
		createTextField(orderTF, "Inserisci l'ID dell'ordine", getLong_dim_of_textfield());
		getAttributes_panel().add(orderTF);

		statusCB.setPreferredSize(getLong_dim_of_textfield());
		statusCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		statusCB.setFocusable(false);
		statusCB.setBackground(Color.white);
		getAttributes_panel().add(statusCB);


		//Buttons & Label setup
		getTable_titleLB().setIcon(getTable_title());
		getTable_titleLB().setSize(225,100);
		getScroll_pane().setBorder(BorderFactory.createLineBorder(new Color(0x771007), 5));

		getButtons_panel().add(getUpdate_sqlJB());
		getButtons_panel().add(getGo_backJB());

	}

	private void events() {

		getTable().addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (!getTable().getSelectionModel().isSelectionEmpty()) {
					
					orderTF.setText(getTable().getModel().getValueAt(getTable().getSelectedRow(), 0).toString());
					
				}
			}
		
			
		});
		
		getUpdate_sqlJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.updateDeliveringOrder(ShopDeliveringOrdersFrame.this);
			}
		});

		getGo_backJB().addMouseListener(new MouseAdapter() {
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
		
		addWindowListener(new WindowAdapter()
	     {
	         @Override
	         public void windowClosing(WindowEvent e)
	         {
	             shop_controller.releaseAllDaoResourcesAndDisposeFrame(ShopDeliveringOrdersFrame.this);
	         }
	     });

	}

	public JTextField getOrderTF() {
		return orderTF;
	}

	public void setOrderTF(JTextField orderTF) {
		this.orderTF = orderTF;
	}

	public JComboBox<String> getStatusCB() {
		return statusCB;
	}

	public void setStatusCB(JComboBox<String> statusCB) {
		this.statusCB = statusCB;
	}

}
