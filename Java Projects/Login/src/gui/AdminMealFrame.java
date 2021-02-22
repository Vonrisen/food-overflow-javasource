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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controllers.AdminController;
import gui_support.RoundJTextField;

@SuppressWarnings("serial")
public class AdminMealFrame extends ComplexFrame {

	private String[] dish_array_strings = { "Seleziona categoria", "-------------------", "Primo piatto", "Carne",
			"Pesce", "Pizza", "Panino", "Fritto", "Dolce", "Bevande analcoliche", "Bevande alcoliche" };
	private String[] allergens_array_strings = { "Cereali e derivati", "Crostacei", "Uova", "Pesce", "Arachidi", "Soia",
			"Latte e derivati", "Frutta a guscio", "Sedano", "Senape", "Sesamo", "An. solforosa e solfiti", "Lupini",
			"Molluschi" };
	private String[] columns = { "Nome", "Categoria", "Prezzo", "Ingredienti", "Allergeni" };
	private JComboBox<Object> dishJCB;
	private JPanel dish_panel;
	private JPanel allergens_panel;
	private JPanel allergens_panel2;
	private JLabel allergensLB;
	private AdminController admin_controller;
	private JCheckBox[] allergens = new JCheckBox[14];
	private JTextField nameTF;
	private JTextField priceTF;
	private JTextField ingredientsTF;

	// Costruttore
	public AdminMealFrame(AdminController controller) {
		initialize();
		frameSetup();
		events();
		this.admin_controller = controller;
	}

	private void initialize() {

		table_title = new ImageIcon("src\\images\\others\\meals.png");

		dish_panel = new JPanel();
		allergens_panel = new JPanel();
		allergens_panel2 = new JPanel();

		allergensLB = new JLabel("Allergeni");

		dishJCB = new JComboBox<Object>(dish_array_strings);

		nameTF = new RoundJTextField(new Color(0x771007));
		priceTF = new RoundJTextField(new Color(0x771007));
		ingredientsTF = new RoundJTextField(new Color(0x771007));

		table.setModel(model = new DefaultTableModel(columns, 0));

	}

	private void frameSetup() {

		// Layout setup
		setTitle("Food Overflow - Admin Panel: Gestione alimenti");
		table_titleLB.setIcon(table_title);
		scroll_pane.setBorder(BorderFactory.createLineBorder(new Color(0x771007), 5));

		// Sottopannelli di "attributes_panel"
		createStandardPanel(dish_panel, null, new Dimension(335, 75));
		attributes_panel.add(dish_panel);

		// Textfields setup
		dish_panel.add(label);

		dishJCB.setPreferredSize(long_dim_of_textfield);
		dishJCB.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0x771007)));
		dishJCB.setFocusable(false);
		dishJCB.setBackground(Color.white);
		dish_panel.add(dishJCB);

		createTextField(nameTF, "Nome", short_dim_of_textfield);
		attributes_panel.add(nameTF);

		createTextField(priceTF, "Prezzo in €", short_dim_of_textfield);
		attributes_panel.add(priceTF);

		createTextField(ingredientsTF, "Ingredienti", long_dim_of_textfield);
		attributes_panel.add(ingredientsTF);

		// Button setup
		buttons_panel.add(insert_sqlJB);
		buttons_panel.add(delete_sqlJB);
		buttons_panel.add(go_backJB);

		// Checkboxes setup
		allergensLB.setHorizontalAlignment(JLabel.CENTER);
		allergensLB.setPreferredSize(long_dim_of_textfield);
		attributes_panel.add(allergensLB);

		allergens_panel.setLayout(new BoxLayout(allergens_panel, BoxLayout.Y_AXIS));
		createStandardPanel(allergens_panel, null, new Dimension(150, 200));
		attributes_panel.add(allergens_panel);

		allergens_panel2.setLayout(new BoxLayout(allergens_panel2, BoxLayout.Y_AXIS));
		createStandardPanel(allergens_panel2, null, new Dimension(150, 200));
		attributes_panel.add(allergens_panel2);

		allergensLoader();

	}

	private void events() {

		insert_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.addMeal(AdminMealFrame.this);
			}
		});

		delete_sqlJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.removeMeal(AdminMealFrame.this);
			}
		});

		go_backJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				admin_controller.openAdminFrame(AdminMealFrame.this);
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

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				admin_controller.releaseAllDaoResourcesAndDisposeFrame(AdminMealFrame.this);
			}
		});

	}

	private void allergensLoader() {

		int i = 0;
		for (String a : allergens_array_strings) {

			allergens[i] = new JCheckBox(a);
			allergens[i].setFocusable(false);
			allergens[i].setContentAreaFilled(false);

			if (i <= (allergens_array_strings.length / 2) - 1)
				allergens_panel.add(allergens[i]);
			else
				allergens_panel2.add(allergens[i]);

			i++;

		}

	}

	public JTextField getNameTF() {
		return nameTF;
	}

	public JTextField getPriceTF() {
		return priceTF;
	}

	public JTextField getIngredientsTF() {
		return ingredientsTF;
	}

	public JComboBox<Object> getDishJCB() {
		return dishJCB;
	}

	public JCheckBox[] getAllergens() {
		return allergens;
	}

}
