package daos_implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import daos_interfaces.AddressDAO;
import db_connection.DBconnection_ProvincesAndCities;
import exceptions.DaoException;
import utilities.DButility;

public class AddressDAOPostgresImplementation implements AddressDAO{

	private Connection connection;
	private PreparedStatement  look_for_province_PS;
	public AddressDAOPostgresImplementation() {
		
		try {
			DBconnection_ProvincesAndCities instance = DBconnection_ProvincesAndCities.getInstance();
			connection = instance.getConnection();
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Network error, please try again","Error",JOptionPane.ERROR_MESSAGE);
		}
		try {
			 look_for_province_PS = connection.prepareStatement("SELECT * FROM Province WHERE name=UPPER(?)");
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Generic error, please contact your administrator","Error",JOptionPane.ERROR_MESSAGE);
		}
		
}
	
	public boolean isProvinceValid(String province) throws DaoException {
	
		ResultSet rs = null;
		Boolean valid = false;
		DButility db_util = new DButility();
		try
		{
		look_for_province_PS.setString(1, province);
		rs = look_for_province_PS.executeQuery();
		if(rs.next())
			valid = true;
		}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(rs, look_for_province_PS, connection);
		}
		return valid;
	}
}
