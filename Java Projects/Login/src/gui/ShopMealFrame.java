package gui;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controllers.ShopController;
import gui_support.RoundJTextField;


public class ShopMealFrame extends ComplexFrame{

	private ImageIcon view_all_meals_inactiveIMG;
	private ImageIcon view_all_meals_activeIMG;
	private String[] columns = {"Nome", "Categoria", "Prezzo in €", "Ingredienti", "Allergeni"};
	private JButton view_all_mealsJB;
	private JTextField mealTF;
	private ShopController shop_controller;


	//Costruttore
	public ShopMealFrame(ShopController shop_controller) {
		initialize();
		setupFrame();
		events();
		this.shop_controller = shop_controller;
	}


	private void initialize() {
		setTable_title(new ImageIcon("src\\images\\tableTitles\\shopMeals.png"));
		view_all_meals_inactiveIMG = new ImageIcon("src\\images\\SqlButtons\\viewAllMealsInactive.png");
		view_all_meals_activeIMG = new ImageIcon("src\\images\\SqlButtons\\viewAllMealsActive.png");
		mealTF = new RoundJTextField(new Color(0x771007));
		view_all_mealsJB = new JButton();
		getTable().setModel(model = new DefaultTableModel(columns, 0));
	}


	private void setupFrame() {

		//Layout setup
		this.setTitle("[Shop Panel] Gestione scorte alimenti");
		getTable_titleLB().setIcon(getTable_title());


		//Textfields setup
		createTextField(mealTF, "Inserisci il nome dell'alimento", getLong_dim_of_textfield());
		getAttributes_panel().add(mealTF);

		setupButton(view_all_mealsJB, view_all_meals_inactiveIMG, new Dimension(335,30));
		getAttributes_panel().add(view_all_mealsJB);


		//Buttons & Label setup
		getTable_titleLB().setIcon(getTable_title());
		getButtons_panel().add(getInsert_sqlJB());
		getButtons_panel().add(getDelete_sqlJB());
		getButtons_panel().add(getGo_backJB());

	}

	private void events() {

		getInsert_sqlJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.addMeal(ShopMealFrame.this);
			}
		});

		getDelete_sqlJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.removeMeal(ShopMealFrame.this);
			}
		});

		getGo_backJB().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.openShopFrame(ShopMealFrame.this);
			}
		});

		mealTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldFocusGained(mealTF, "Inserisci il nome dell'alimento");
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldFocusLost(mealTF, "Inserisci il nome dell'alimento");
			}
		});

		view_all_mealsJB.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shop_controller.openShopAllMealsFrame();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				view_all_mealsJB.setIcon(view_all_meals_activeIMG);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				view_all_mealsJB.setIcon(view_all_meals_inactiveIMG);
			}
		});


		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				shop_controller.releaseAllDaoResourcesAndDisposeFrame(ShopMealFrame.this);
			}
		});

	}



	public JTextField getMealTF() {
		return mealTF;
	}

	public void setMealTF(JTextField mealTF) {
		this.mealTF = mealTF;
	}

}
