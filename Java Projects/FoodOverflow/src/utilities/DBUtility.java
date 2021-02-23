package utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import daos_interfaces.CustomerDAO;
import daos_interfaces.MealDAO;
import daos_interfaces.OrderDAO;
import daos_interfaces.RiderDAO;
import daos_interfaces.ShopDAO;
import exceptions.DAOException;

public class DBUtility {

	public DBUtility() {

	}

	public void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				System.exit(-1);
			}
		}
		return;
	}

	public void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.exit(-1);
			}
		}
		return;
	}

	public void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.exit(-1);
			}
		}
		return;
	}

	public void closeAllResources(ShopDAO shop_dao, OrderDAO order_dao, MealDAO meal_dao, RiderDAO rider_dao,
			CustomerDAO customer_dao, Connection connection, JFrame frame) {
		try {
			if (shop_dao != null)
				shop_dao.closeStatements();
			if (order_dao != null)
				order_dao.closeStatements();
			if (meal_dao != null)
				meal_dao.closeStatements();
			if (rider_dao != null)
				rider_dao.closeStatements();
			if (customer_dao != null)
				customer_dao.closeStatements();
		} catch (DAOException e) {
			System.exit(-1);
		} finally {
			closeConnection(connection);
			frame.dispose();
		}
	}

}
