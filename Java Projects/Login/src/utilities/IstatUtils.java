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
	try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Utente\\Desktop\\CittaProvinceCodiciCatastali_ISTAT.txt"))) {
	    String line;
	    while ((line = br.readLine()) != null) {
	        String[] values = line.split(",");
	        if(values[0].equals("n"))
	        nations.add(values[3]);
	    }
	} catch (FileNotFoundException e) {
	} catch (IOException i) {
	}
	return nations;
	}
	public List<String>getProvinces (){
		
		List<String> provinces = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Utente\\Desktop\\CittaProvinceCodiciCatastali_ISTAT.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        if(!values[0].equals("n"))
		        	provinces.add(values[0]);
		    }
		} catch (FileNotFoundException e) {
		} catch (IOException i) {
		}
		return provinces;
	}
	public String getCadastral_codeByTownName(String town_name) {
		
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Utente\\Desktop\\CittaProvinceCodiciCatastali_ISTAT.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null && !line.split(",")[2].equals(town_name)) {
		    }
		    if(line.split(",")[2].equals("town_name"))
		    	return line.split(",")[2];
		} catch (FileNotFoundException e) {
		} catch (IOException i) {
		}
		return null;
	}
	public boolean isProvinceValid(String province) {
		
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Utente\\Desktop\\CittaProvinceCodiciCatastali_ISTAT.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null && !line.split(",")[0].equals(province)) {
		    }
		    if(line.split(",")[0].equals(province))
		    	return true;
		} catch (FileNotFoundException e) {
		} catch (IOException i) {
		}
		return false;
	}
    public List<String> getTownsByProvince(String province) {
		
    	
		List<String> towns = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Utente\\Desktop\\CittaProvinceCodiciCatastali_ISTAT.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        if(!values[0].equals(province))
		        	towns.add(values[3]);
		    }
		} catch (FileNotFoundException e) {
		} catch (IOException i) {
		}
		return towns;
	}
	}