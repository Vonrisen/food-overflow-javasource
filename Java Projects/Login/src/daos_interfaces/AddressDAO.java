package daos_interfaces;

import exceptions.DaoException;

public interface AddressDAO {
	
	public boolean isProvinceValid(String province) throws DaoException;
	
}
