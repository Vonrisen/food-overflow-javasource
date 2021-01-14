package daos_implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import daos_interfaces.CustomerOrderDAO;
import daos_interfaces.MealDAO;
import daos_interfaces.OrderCompositionDAO;
import db_connection.DBconnection;
import entities.CustomerOrder;
import entities.Meal;
import entities.OrderComposition;

public class OrderCompositionDAOPostgresImplementation implements OrderCompositionDAO {

	private Connection connection;
	PreparedStatement get_order_composition_of_the_order_PS; 
	public OrderCompositionDAOPostgresImplementation() {
		try {
			DBconnection instance = DBconnection.getInstance();
			connection = instance.getConnection();
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore di connessione");
		}
		try {
			
			get_order_composition_of_the_order_PS = connection.prepareStatement("SELECT * FROM OrderComposition WHERE order_id=?");
		
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore durante il prepare degli stateinments");
		}
	}
	public List<OrderComposition> getOrderCompositionOfTheOrder(String order_id) throws SQLException {
		
		get_order_composition_of_the_order_PS.setString(1, order_id);
		ResultSet rs = get_order_composition_of_the_order_PS.executeQuery();
		List<OrderComposition>order_composition_of_the_order = new ArrayList<OrderComposition>();
		MealDAO meal_DAO = new MealDAOPostgresImplementation();
		CustomerOrderDAO order_DAO = new CustomerOrderDAOPostgresImplementation();
		CustomerOrder order = order_DAO.getCustomerOrderByOrderId(order_id);
		while(rs.next())
		{
			Meal meal = meal_DAO.getMealById(rs.getString("meal_id"));
			OrderComposition order_composition = new OrderComposition(order, meal, rs.getInt("quantity"));
			order_composition_of_the_order.add(order_composition);
		}
		rs.close();
		connection.close();
		return order_composition_of_the_order;
		}
	
}
