package daos_interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.CartComposition;

public interface CartCompositionDAO {

	public ArrayList<CartComposition> getAllCartComps() throws SQLException;
	public void insertCartComp(CartComposition cartComp) throws SQLException;
	public void deleteCartComp(CartComposition cartComp) throws SQLException;
	public void updateCartComp(CartComposition cartComp) throws SQLException;
}

