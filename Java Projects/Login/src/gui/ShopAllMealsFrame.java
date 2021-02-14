package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import controllers.ShopController;

public class ShopAllMealsFrame extends StandardFrame {
	
	private String[] columns = {"Nome", "Categoria", "Prezzo in €", "Ingredienti", "Allergeni"};


	
	//Costruttore
	public ShopAllMealsFrame() {
		frame();
	}

	
	private void frame() {
		
		setTable_title(new ImageIcon("src\\images\\tableTitles\\meals.png"));
		getTable().setModel(model = new DefaultTableModel(columns, 0));
		
		this.setTitle("[Shop Panel] Lista degli alimenti");
		getTable_titleLB().setIcon(getTable_title());
		
		getGo_backJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ShopAllMealsFrame.this.dispose();
			}
		});
	}
	

}
