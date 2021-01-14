package daos_implementation;

import java.sql.CallableStatement;
import java.sql.SQLException;

import daos_interfaces.CartDAO;
import entities.Cart;

public class CartDAOPostgressImplementation implements CartDAO {
	
	private CallableStatement create_cart_CS,delete_cart_CS, update_cart_CS;

	public void createCart(Cart cart) throws SQLException{
		
		create_cart_CS.setString(1, cart.getComplete());
		create_cart_CS.setObject(2, cart.getCustomer());
//		create_cart_CS.setObject(3, cart.getCartComp_list());
		create_cart_CS.executeUpdate();
	}

	public void deleteCart(Cart cart) throws SQLException {
		
		delete_cart_CS.setObject(1, cart.getCustomer());
		delete_cart_CS.executeUpdate();
	}

	public void updateCart(Cart cart) throws SQLException {
		
		update_cart_CS.setString(1, cart.getComplete());
//		update_cart_CS.setObject(2, cart.getCustomer());
//		update_cart_CS.setObject(3, cart.getCartComp_list());
		update_cart_CS.executeUpdate();
	}

}
