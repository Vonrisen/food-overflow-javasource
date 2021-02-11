package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IstatUtils {

	public IstatUtils() {}
	public List<String>getNations () {
	List<String> nations = new ArrayList<String>();
	try (BufferedReader br = new BufferedReader(new FileReader("NazioniIstat.txt"))) {
	    String line;
	    while ((line = br.readLine()) != null) {
	        nations.add(line);
	    }
	} catch (FileNotFoundException e) {
	} catch (IOException i) {
	}
	return nations;
	}
	public List<String>getProvinces (){
		
		List<String> provinces = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("ProvinceIstat.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        	provinces.add(line);
		    }
		} catch (FileNotFoundException e) {
		} catch (IOException i) {
		}
		return provinces;
	}
	public String getCadastral_codeByTownName(String town_name) {
		
		try (BufferedReader br = new BufferedReader(new FileReader("CittaProvinceCodiciCatastaliIstat.txt"))) {

		    String line;
		    town_name = town_name.toUpperCase();
		    while ((line = br.readLine()) != null) {
			    if(line.split(",")[3].equals(town_name))
			    	return line.split(",")[2];
		    }
		} catch (FileNotFoundException e) {
		} catch (IOException i) {
		}
		return null;
	}
	public boolean isProvinceValid(String province) {
		
		try (BufferedReader br = new BufferedReader(new FileReader("ProvinceIstat.txt"))) {
		    String line;
		    province = province.toUpperCase();
		    while ((line = br.readLine()) != null) {
			    if(line.equals(province))
			    	return true;
		    }
		} catch (FileNotFoundException e) {
		} catch (IOException i) {
		}
		return false;
	}
    public List<String> getTownsByProvince(String province) {
		
    	
		List<String> towns = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("CittaProvinceCodiciCatastaliIstat.txt"))) {
		    String line;
		    province = province.toUpperCase();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        if(values[0].equals(province))
		        	towns.add(values[3]);
		    }
		} catch (FileNotFoundException e) {
		} catch (IOException i) {
		}
		return towns;
	}
	}