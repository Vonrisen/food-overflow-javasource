package daos_interfaces;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Address;
import entities.Rider;
import exceptions.DaoException;
import utilities.InputUtility;

public interface RiderDAO {

	public List<Rider> getAllRiders() throws DaoException;
	public void insertRider(Rider rider, String shop_email) throws DaoException;
	public void dismissRider(Rider rider) throws DaoException;
	public void updateRider(Rider rider) throws DaoException;
	public void closeStatements() throws DaoException;
	public Rider getRiderByCf(String cf) throws DaoException;

}
