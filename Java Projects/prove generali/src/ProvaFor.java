import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ProvaFor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProvaFor frame = new ProvaFor();
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
	public ProvaFor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
//		ImageIcon imm = new ImageIcon("src\\adminLogo.png");
//		Image image= imm.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//		imm.setImage(image);
		
		Cibo alimento1=new Cibo("Spaghetti e vongole",5.50,"Ingredienti:Spaghetti, vongole, pomodorini");
		Cibo alimento2=new Cibo("Cotoletta",8.50,"Ingredienti:Carne di vitello, uova, farina, burro");
		Cibo alimento3=new Cibo("Costine al forno",12.00,"Ingredienti:Costine di carne, rosmarino, pepe nero, aglio");
		Cibo alimento4=new Cibo("Insalata di polpo e patate",9.00,"Ingredienti:Polpo, Patate, prezzemolo, olio, succo di limone");
		Cibo alimento5=new Cibo("CocaCola",2.00,"Ingredienti:Acqua, Anidride carbonica, zucchero, colorante, acido fosforico, aromi naturali. caffeina");
		Cibo alimento6=new Cibo("Bottiglina d'acqua",1.00);
		Cibo alimento7=new Cibo("Patatine Fritte p",2.00,"Porzione piccola. Ingredienti:Patate, sale, olio");
		Cibo alimento8=new Cibo("Pizza margherita",4.00);
		Cibo alimento9=new Cibo("Gelato alla vaniglia",2.00);
		Cibo alimento10=new Cibo("Pasticceria mignon",6.00,"Ingredienti:vari");
//		
		
		ArrayList<Cibo> arrayCibo= new ArrayList<Cibo>();
		arrayCibo.add(alimento1);
		arrayCibo.add(alimento2);
		arrayCibo.add(alimento3);
		arrayCibo.add(alimento4);
		arrayCibo.add(alimento5);
		arrayCibo.add(alimento6);
		arrayCibo.add(alimento7);
		arrayCibo.add(alimento8);
		arrayCibo.add(alimento9);
		arrayCibo.add(alimento10);
		
		ArrayList<JButton> arrayButton = new ArrayList<JButton>();
		for(Cibo c:arrayCibo) {
			JButton ciboButton = new JButton();

			ciboButton.setName(c.getNome());
			arrayButton.add(ciboButton);
		}
		
		JScrollPane bar = new JScrollPane();
		this.getContentPane().add(bar);
		bar.setBounds(90, 132, 850, 600);

		
		for(JButton b:arrayButton) {
			b.setPreferredSize(new Dimension(200,200));
			ImageIcon imm = new ImageIcon("src\\"+b.getName()+".png");
			Image image= imm.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			imm.setImage(image);
			b.setIcon(imm);
			if(imm.getIconHeight()<1) {
				b.setBackground(Color.gray);
				b.setText(b.getName());
			}
			contentPane.add(b);
			b.setVisible(true);
			
		}
		bar.setVisible(true);
	}

}
