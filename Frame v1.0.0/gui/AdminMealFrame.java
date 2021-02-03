package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controllers.AdminController;
import gui_support.ComplexFrame;
import gui_support.RoundJTextField;


public class AdminMealFrame extends ComplexFrame{
	
	private String[] dish_array_strings = {"Primo piatto", "Carne", "Pesce", "Pizza","Panino", "Fritto", "Dolce", "Bevande analcoliche", "Bevande alcoliche" };
	private String[] allergens_array_strings = {"Cereali e derivati", "Crostacei", "Uova", "Pesce", "Arachidi", "Soia", "Latte e derivati", "Frutta a guscio", "Sedano", "Senape", "Sesamo", "An. solforosa e solfiti", "Lupini", "Molluschi"};
	private String[] columns = {"Nome", "Categoria", "Prezzo in €", "Ingredienti", "Allergeni"};
	private JComboBox<Object> dishJCB;
	private JPanel dish_panel;
	private JPanel allergens_panel;
	private JPanel allergens_panel2;
	private JLabel allergensLB;
	AdminController controller = new AdminController();
	private JCheckBox[] allergens = new JCheckBox[14];
	private JTextField nameTF;
	private JTextField priceTF;
	private JTextField ingredientsTF;
	
	
	//Costruttore
	public AdminMealFrame(AdminController controller) {
		initialize();
		frameSetup();
		events();
		this.controller = controller;
	}
	
	
	private void initialize() {
		
		setTable_title(new ImageIcon("src\\images\\tableTitles\\meals.png"));
		
		dish_panel = new JPanel();
		allergens_panel = new JPanel();
		allergens_panel2 = new JPanel();
		
		allergensLB = new JLabel("Allergeni");
		
		dishJCB = new JComboBox<Object>(dish_array_strings);
		
		nameTF = new RoundJTextField(new Color(0x771007));
		priceTF = new RoundJTextField(new Color(0x771007));
		ingredientsTF = new RoundJTextField(new Color(0x771007));
		
		getTable().setModel(model = new DefaultTableModel(columns, 0));
		
	}
	
	
	private void frameSetup() {
		
		//Layout setup
		setTitle("[Admin Panel] Gestione alimenti");
		getTable_titleLB().setIcon(getTable_title());
		
		
		//Sottopannelli di "attributes_panel"
		createStandardPanel(dish_panel, null, new Dimension(335,75));
		getAttributes_panel().add(dish_panel);
		
		
		//Textfields setup
		dish_panel.add(getLabel());
		
		dishJCB.setPreferredSize(getLong_dim_of_textfield());
		dish_panel.add(dishJCB);
		
		createTextField(nameTF, "Nome", getShort_dim_of_textfield());
		getAttributes_panel().add(nameTF);
		
		createTextField(priceTF, "Prezzo in €", getShort_dim_of_textfield());
		getAttributes_panel().add(priceTF);
		
		createTextField(ingredientsTF, "Ingredienti", getLong_dim_of_textfield());
		getAttributes_panel().add(ingredientsTF);
		
		
		//Button setup
		getButtons_panel().add(getInsert_sqlJB());
		getButtons_panel().add(getDelete_sqlJB());
		getButtons_panel().add(getGo_backJB());
		
		
		//Checkboxes setup
		allergensLB.setHorizontalAlignment(JLabel.CENTER);
		allergensLB.setPreferredSize(getLong_dim_of_textfield());
		getAttributes_panel().add(allergensLB);
		
		allergens_panel.setLayout(new BoxLayout(allergens_panel, BoxLayout.Y_AXIS));
		createStandardPanel(allergens_panel, null, new Dimension(150,200));
		getAttributes_panel().add(allergens_panel);
		
		allergens_panel2.setLayout(new BoxLayout(allergens_panel2, BoxLayout.Y_AXIS));
		createStandardPanel(allergens_panel2, null, new Dimension(150,200));
		getAttributes_panel().add(allergens_panel2);
		
		allergensLoader();
		
	}
	
	
	private void events() {
		
		getInsert_sqlJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.addMeal(AdminMealFrame.this);
			}
		});
		
		getDelete_sqlJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.removeMeal(AdminMealFrame.this);
			}
		});
		
		getGo_backJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.openAdminFrame(AdminMealFrame.this);
			}
		});
		
		nameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(nameTF, "Nome");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(nameTF, "Nome");
			}
		});
		
		priceTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(priceTF, "Prezzo in €");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(priceTF, "Prezzo in €");
			}
		});
		
		ingredientsTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(ingredientsTF, "Ingredienti");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(ingredientsTF, "Ingredienti");
			}
		});
		
	}
	
	
	private void allergensLoader() {
		
		int i=0;
		for(String a: allergens_array_strings) {
			
			allergens[i] = new JCheckBox(a);
			allergens[i].setFocusable(false);
			allergens[i].setContentAreaFilled(false);
			
			if(i<=(allergens_array_strings.length/2)-1)  
				allergens_panel.add(allergens[i]);
			else	
				allergens_panel2.add(allergens[i]);
			
			i++;
			
		}
		
	}
	
	
	public JTextField getNameTF() {
		return nameTF;
	}
	
	public void setNameTF(JTextField nameTF) {
		this.nameTF = nameTF;
	}

	public JTextField getPriceTF() {
		return priceTF;
	}

	public void setPriceTF(JTextField priceTF) {
		this.priceTF = priceTF;
	}

	public JTextField getIngredientsTF() {
		return ingredientsTF;
	}

	public void setIngredientsTF(JTextField ingredientsTF) {
		this.ingredientsTF = ingredientsTF;
	}

	public JComboBox<Object> getDishJCB() {
		return dishJCB;
	}

	public void setDishJCB(JComboBox<Object> dishJCB) {
		this.dishJCB = dishJCB;
	}

	public JCheckBox[] getAllergens() {
		return allergens;
	}

	public void setAllergens(JCheckBox[] allergens) {
		this.allergens = allergens;
	}
	
}
