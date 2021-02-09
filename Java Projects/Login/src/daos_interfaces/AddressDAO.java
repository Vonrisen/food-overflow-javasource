package daos_interfaces;

import java.util.List;

import exceptions.DaoException;

public interface AddressDAO {
	
	public boolean isProvinceValid(String province) throws DaoException;
	public List<String> getAllNations();
	public List<String> getAllProvinces();
	public List<String> getTownsByProvince(String province);
	
}
