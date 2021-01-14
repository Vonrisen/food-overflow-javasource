package daos_implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import daos_interfaces.MealDAO;
import db_connection.DBconnection;
import entities.Meal;

public class MealDAOPostgresImplementation implements MealDAO {

	private Connection connection;
	PreparedStatement get_meal_by_id_PS, get_allergens_of_a_meal_PS;
	public MealDAOPostgresImplementation() {
		
		try {
			DBconnection instance = DBconnection.getInstance();
			connection = instance.getConnection();
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore di connessione");
		}
		try {
			
			get_meal_by_id_PS = connection.prepareStatement("SELECT SUBSTR(meal_id,1,2) AS prefix, name, price, description "
														   + "FROM Meal WHERE meal_id=?");
			get_allergens_of_a_meal_PS = connection.prepareStatement("SELECT allergen::VARCHAR FROM MealComposition WHERE meal_id=?");
			
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore durante il prepare degli statements");
		}
		

}
	@Override
	public Meal getMealById(String meal_id) throws SQLException {
		
		get_meal_by_id_PS.setString(1, meal_id);
		ResultSet rs = get_meal_by_id_PS.executeQuery();
		get_allergens_of_a_meal_PS.setString(1, meal_id);
		ResultSet rs1 = get_allergens_of_a_meal_PS.executeQuery();
		List<String>allergen_list = new ArrayList<String>();
		Meal meal = null;
		while(rs1.next())
		{
			allergen_list.add(rs1.getString("allergen"));
		}
		while(rs.next())
		{
			
			meal = new Meal(rs.getString("prefix"),rs.getString("name"),rs.getFloat("price"),rs.getString("description"),allergen_list);
		}
		rs.close();
		connection.close();
		return meal;
	}

	
}
