package daos_implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import daos_interfaces.AddressDAO;
import db_connection.DBconnection_ProvincesAndCities;
import exceptions.DaoException;
import utilities.DButility;

public class AddressDAOPostgresImplementation implements AddressDAO{

	private Connection connection;
	private PreparedStatement  look_for_province_PS, get_all_nations_PS,  get_all_provinces_PS, get_towns_by_province_PS;
	public AddressDAOPostgresImplementation() {
		
		try {
			DBconnection_ProvincesAndCities instance = DBconnection_ProvincesAndCities.getInstance();
			connection = instance.getConnection();
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Network error, please try again","Error",JOptionPane.ERROR_MESSAGE);
		}
		try {
			 look_for_province_PS = connection.prepareStatement("SELECT * FROM Province WHERE name=UPPER(?) ORDER BY name");
			 get_all_nations_PS = connection.prepareStatement("SELECT name FROM Town WHERE province_abbr is null ORDER BY name");
			 get_all_provinces_PS = connection.prepareStatement("SELECT name FROM Province ORDER BY name");
			 get_towns_by_province_PS = connection.prepareStatement("SELECT * FROM town WHERE province_abbr = (SELECT abbreviation FROM Province WHERE name=UPPER(?)) ORDER BY name");
			 
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
	
	public List<String> getAllNations()
	{
		ResultSet rs = null;
		List<String>nations = new ArrayList<String>();
		try {
			rs = get_all_nations_PS.executeQuery();
			while(rs.next()) 
				nations.add(rs.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nations;
}

	public List<String> getAllProvinces()
	{
		ResultSet rs = null;
		List<String>provinces = new ArrayList<String>();
		try {
			rs = get_all_provinces_PS.executeQuery();
			while(rs.next()) 
				provinces.add(rs.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return provinces;

	}
	
	public List<String> getTownsByProvince(String province)
	{
		ResultSet rs = null;
		List<String>towns = new ArrayList<String>();
		try {
			get_towns_by_province_PS.setString(1, province);
			rs = get_towns_by_province_PS.executeQuery();
			while(rs.next()) 
				towns.add(rs.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return towns;

	}
	
}
	
