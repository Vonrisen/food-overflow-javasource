package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class IstatUtility {

	public IstatUtility() {
	}

	public List<String> getNations() {
		List<String> nations = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("NazioniIstat.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				nations.add(line);
			}
		} catch (IOException i) {
			JOptionPane.showMessageDialog(null, "Errore mentre cercavo di risalire alle nazioni", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
		return nations;
	}

	public List<String> getProvinces() {

		List<String> provinces = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("ProvinceIstat.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				provinces.add(line);
			}
		} catch (IOException i) {
			JOptionPane.showMessageDialog(null, "Errore mentre cercavo di risalire alle province", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
		return provinces;
	}

	public String getCadastral_codeByTownName(String town_name) {

		try (BufferedReader br = new BufferedReader(new FileReader("CittaProvinceCodiciCatastaliIstat.txt"))) {

			String line;
			town_name = town_name.toUpperCase();
			while ((line = br.readLine()) != null) {
				if (line.split(",")[3].equals(town_name))
					return line.split(",")[2];
			}
		} catch (IOException i) {
			JOptionPane.showMessageDialog(null, "Errore mentre cercavo di risalire al codice catastale", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public boolean isProvinceValid(String province) {

		try (BufferedReader br = new BufferedReader(new FileReader("ProvinceIstat.txt"))) {
			String line;
			province = province.toUpperCase();
			while ((line = br.readLine()) != null) {
				if (line.equals(province))
					return true;
			}
		} catch (IOException i) {
			JOptionPane.showMessageDialog(null, "Errore mentre cercavo di risalire alle province", "Errore",
					JOptionPane.ERROR_MESSAGE);
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
				if (values[0].equals(province))
					towns.add(values[3]);
			}
		} catch (IOException i) {
			JOptionPane.showMessageDialog(null, "Errore mentre cercavo di risalire ai comuni", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
		return towns;
	}
	
	public String getProvinceOfATown(String town) {
		
		try (BufferedReader br = new BufferedReader(new FileReader("CittaProvinceCodiciCatastaliIstat.txt"))) {
			String line;
			town = town.toUpperCase();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if (values[3].equals(town))
					return values[0];
			}
		} catch (IOException i) {
			JOptionPane.showMessageDialog(null, "Errore mentre cercavo di risalire ai comuni", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
		return "";
	}
	
	public boolean isBirthPlaceANation(String birth_place) {
		
		try (BufferedReader br = new BufferedReader(new FileReader("CittaProvinceCodiciCatastaliIstat.txt"))) {
			String line;
			birth_place = birth_place.toUpperCase();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if (values[0].equals("n") && values[3].equals(birth_place))
					return true;
			}
		} catch (IOException i) {
			JOptionPane.showMessageDialog(null, "Errore mentre cercavo di risalire ai comuni", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

}