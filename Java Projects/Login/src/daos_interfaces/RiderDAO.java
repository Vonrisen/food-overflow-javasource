package daos_interfaces;
import java.util.List;

import entities.Rider;
import exceptions.DaoException;

public interface RiderDAO {

	public List<Rider> getAllRiders() throws DaoException;
	public void insertRider(Rider rider, String shop_email) throws DaoException;
	public void dismissRider(Rider rider) throws DaoException;
	public void updateRider(Rider rider) throws DaoException;
	public void closeStatements() throws DaoException;
}
