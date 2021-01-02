package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminFramee extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumeroShopDrfgh;
	private JTextField txtNumeroAlimentiEafhg;
	private JTextField txtRicercaComplessa;
	private JTextField txtNumeroRiderAdfh;
	private JTextField txtRicercaComplessa_3;
	private JTextField txtRicercaComplessa_2;
	private JTextField txtNumeroUtentiAefh;
	private JTextField txtRicercaComplessa_1;
	private JTextField txtRicercaComplessa_4;
	private JButton mealButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFramee frame = new AdminFramee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminFramee() {
		
		
		
		String data[][]={ {"101","Amit","670000"},    
                {"102","Jai","780000"},
                {"101","Amit","670000"},    
                {"102","Jai","780000"},    
                {"101","Sachin","700000"},
                {"101","Amit","670000"},    
                {"102","Jai","780000"},    
                {"101","Sachin","700000"},
                {"101","Amit","670000"},    
                {"102","Jai","780000"},    
                {"101","Sachin","700000"},
                {"101","Sachin","700000"}};    
		String column[]={"ID","NAME","SALARY"};
		
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(2, 0));
		
		
		
					//Pannello di sinistra
		JPanel leftpanel = new JPanel();
		contentPane.add(leftpanel, BorderLayout.WEST);
		leftpanel.setLayout(new GridLayout(7, 1, 5, 5));
		
		JLabel logoLabel = new JLabel("LOGO");
		leftpanel.add(logoLabel);
		
		JButton homeButton = new JButton("Home");
		leftpanel.add(homeButton);
		
		JButton shopButton = new JButton("Shop");
		leftpanel.add(shopButton);
		
		JButton riderButton = new JButton("Rider");
		leftpanel.add(riderButton);
		
		JButton mealButton = new JButton("Meal");
		leftpanel.add(mealButton);
		
		JButton userButton = new JButton("User");
		leftpanel.add(userButton);
		
		JButton exitButton = new JButton("Exit");
		leftpanel.add(exitButton);
		
		
		
					//Pannello Home
		JPanel homePanel = new JPanel();
		contentPane.add(homePanel, BorderLayout.CENTER);
		homePanel.setLayout(new GridLayout(3, 3, 50, 50));

		txtNumeroShopDrfgh = new JTextField();
		txtNumeroShopDrfgh.setText("Numero Shop: drfgh");
		homePanel.add(txtNumeroShopDrfgh);
		txtNumeroShopDrfgh.setColumns(10);
		
		txtNumeroAlimentiEafhg = new JTextField();
		txtNumeroAlimentiEafhg.setText("Numero Alimenti: eafhg");
		homePanel.add(txtNumeroAlimentiEafhg);
		txtNumeroAlimentiEafhg.setColumns(10);
		
		txtNumeroRiderAdfh = new JTextField();
		txtNumeroRiderAdfh.setText("Numero Rider: adfh");
		homePanel.add(txtNumeroRiderAdfh);
		txtNumeroRiderAdfh.setColumns(10);
		
		txtNumeroUtentiAefh = new JTextField();
		txtNumeroUtentiAefh.setText("Numero Utenti: aefh");
		homePanel.add(txtNumeroUtentiAefh);
		txtNumeroUtentiAefh.setColumns(10);
		
		txtRicercaComplessa = new JTextField();
		txtRicercaComplessa.setText("Numero Alimenti: dfsh");
		homePanel.add(txtRicercaComplessa);
		txtRicercaComplessa.setColumns(10);
		
		txtRicercaComplessa_1 = new JTextField();
		txtRicercaComplessa_1.setText("Ricerca Complessa 1");
		homePanel.add(txtRicercaComplessa_1);
		txtRicercaComplessa_1.setColumns(10);
		
		txtRicercaComplessa_2 = new JTextField();
		txtRicercaComplessa_2.setText("Ricerca Complessa 2");
		homePanel.add(txtRicercaComplessa_2);
		txtRicercaComplessa_2.setColumns(10);
		
		txtRicercaComplessa_3 = new JTextField();
		txtRicercaComplessa_3.setText("Ricerca Complessa 3");
		homePanel.add(txtRicercaComplessa_3);
		txtRicercaComplessa_3.setColumns(10);
		
		txtRicercaComplessa_4 = new JTextField();
		txtRicercaComplessa_4.setText("Ricerca Complessa 4");
		homePanel.add(txtRicercaComplessa_4);
		txtRicercaComplessa_4.setColumns(10);
		
		
		
		
		JPanel shopPanel = new JPanel();
//		contentPane.add(shopPanel, BorderLayout.CENTER);
		shopPanel.setLayout(new GridLayout(2, 2, 75, 75));
		shopPanel.setVisible(false);
		
		JTable table = new JTable(data,column);
		table.getTableHeader().setReorderingAllowed(false);
		table.setEnabled(false);
		
		JScrollPane bar=new JScrollPane(table);
		bar.setBorder(BorderFactory.createEmptyBorder());
		shopPanel.add(bar);

		
		
		
		
		homeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shopPanel.setVisible(false);
				homePanel.setVisible(true);
			}
		});
		
		
		
		shopButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shopPanel.setVisible(true);
				homePanel.setVisible(false);
			}
		});
		
		
	}

}
