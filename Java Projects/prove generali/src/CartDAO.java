package daos_interfaces;

import java.sql.SQLException;

import entities.Cart;

public interface CartDAO {
	
	public void createCart(Cart cart) throws SQLException;
	public void deleteCart(Cart cart) throws SQLException;
	public void updateCart(Cart cart) throws SQLException;

}
