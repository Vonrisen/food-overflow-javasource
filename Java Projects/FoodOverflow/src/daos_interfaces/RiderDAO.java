package daos_interfaces;

import java.util.List;
import entities.Rider;
import exceptions.DAOException;

public interface RiderDAO {

	public List<Rider> getAllRiders() throws DAOException;

	public void insertRider(Rider rider, String shop_email) throws DAOException;

	public void dismissRider(Rider rider) throws DAOException;

	public void updateRider(Rider rider, String cf_of_rider_to_update) throws DAOException;

	public void closeStatements() throws DAOException;

	public Rider getRiderByCf(String cf) throws DAOException;

}
