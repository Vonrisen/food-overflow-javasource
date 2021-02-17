package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class AdminRiderFrame extends StandardFrame {
	
	private String[] columns = {"CF", "Nome", "Cognome", "Data di nascita", "Luogo di nascita", "Indirizzo", "Sesso", "Cellulare", "Veicolo", "Orario di lavoro","Numero di consegne"};
	
	
	//Costruttore
	public AdminRiderFrame() {
		frame();
	}
	
	
	private void frame() {
		
		setTitle("Food Overflow - Admin Panel: Visualizza riders");

		setTable_title(new ImageIcon("src\\images\\others\\riders.png"));
		getTable_titleLB().setIcon(getTable_title());
		getTable().setModel(model = new DefaultTableModel(columns, 0));
		getScroll_pane().setBorder(BorderFactory.createLineBorder(new Color(0x771007), 5));
		
		getGo_backJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				AdminRiderFrame.this.dispose();
			
			}
		});
	
		
	}

}
