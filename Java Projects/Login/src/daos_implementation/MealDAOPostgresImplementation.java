package daos_implementation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import daos_interfaces.MealDAO;
import db_connection.DBconnection;
import entities.Meal;

public class MealDAOPostgresImplementation implements MealDAO {

	private Connection connection;
	PreparedStatement get_meals_of_a_shop_by_shop_id_PS, get_allergens_of_a_meal_PS;
	public MealDAOPostgresImplementation() {
		
		try {
			DBconnection instance = DBconnection.getInstance();
			connection = instance.getConnection();
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore di connessione con il database","Errore",JOptionPane.ERROR_MESSAGE);
		}
		try {
			
			get_meals_of_a_shop_by_shop_id_PS = connection.prepareStatement("SELECT * FROM MEAL WHERE id IN(SELECT meal_id FROM Supply WHERE shop_id=?)");
			
			get_allergens_of_a_meal_PS = connection.prepareStatement("SELECT allergen_name FROM MEALCOMPOSITION WHERE meal_id=?");
			
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore durante il prepare degli statement","Errore",JOptionPane.ERROR_MESSAGE);
		}
		
    }
	public ArrayList<Meal> getMealsOfAShopByShopId(String shop_id) throws SQLException {
		
		get_meals_of_a_shop_by_shop_id_PS.setString(1, shop_id);
		ResultSet rs1 = get_meals_of_a_shop_by_shop_id_PS.executeQuery();
		ResultSet rs2 = null;
		ArrayList<Meal> meal_list = new ArrayList<Meal>();
		ArrayList<String> allergens = null;
		while(rs1.next())
		{
			get_allergens_of_a_meal_PS.setString(1, rs1.getString("id"));
			rs2 = get_allergens_of_a_meal_PS.executeQuery();
			allergens = new ArrayList<String>();
			while(rs2.next())
			{
				allergens.add(rs2.getString("allergen_name"));
			}
			meal_list.add(new Meal(rs1.getString("name"),rs1.getFloat("price"),rs1.getString("ingredients"),rs1.getString("category"),allergens));
		}
		return meal_list;
	}
	

}
