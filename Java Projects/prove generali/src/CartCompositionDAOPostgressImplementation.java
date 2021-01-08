package daos_implementation;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daos_interfaces.CartCompositionDAO;
import entities.CartComposition;

public class CartCompositionDAOPostgressImplementation implements CartCompositionDAO {

	private PreparedStatement get_all_cartComps_PS;
	private CallableStatement insert_cartComp_CS, delete_cartComp_CS, update_cartComp_CS;
	
public ArrayList<CartComposition> getAllCartComps() throws SQLException {
		
//		ResultSet rs = get_all_cartComps_PS.executeQuery();
		
		return null;
	}

	
	public void insertCartComp(CartComposition cartComp) throws SQLException {
		
		insert_cartComp_CS.setObject(1, cartComp.getCart());
		insert_cartComp_CS.setObject(2, cartComp.getMeal());
		insert_cartComp_CS.setInt(3, cartComp.getQuantity());
		insert_cartComp_CS.executeUpdate();
	}

	
	public void deleteCartComp(CartComposition cartComp) throws SQLException {
		
		delete_cartComp_CS.setObject(1, cartComp.getCart());
		delete_cartComp_CS.setObject(2, cartComp.getMeal());
		delete_cartComp_CS.executeUpdate();
	}

	
	public void updateCartComp(CartComposition cartComp) throws SQLException {
		
//		update_cartComp_CS.setObject(1, cartComp.getCart());
//		update_cartComp_CS.setObject(2, cartComp.getMeal());
		update_cartComp_CS.setInt(3, cartComp.getQuantity());
		update_cartComp_CS.executeUpdate();
	}

}
