package utilities;
import java.util.Date;
import java.util.TimeZone;

import db_connection.DBconnection;
import db_connection.DBconnection_ProvincesAndCities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Calendar;


public class CodiceFiscaleUtility {

 
	private String codice_fiscale = "";
	private Connection connection;
	public CodiceFiscaleUtility() {
		try
		{
			DBconnection_ProvincesAndCities instance = DBconnection_ProvincesAndCities.getInstance();
			this.connection = instance.getConnection();
		}
		catch (SQLException e)
		{
			System.out.println("Connection error");
		}
	}
	public String getCF(String name, String surname, Date birth_date, String birth_place_town, char sex) {
		int n_consonanti=0;
		int n_vocali = 0;
		int conta = 0;
		boolean vocale = false;
		PreparedStatement getCodiceCatastalePS;
		surname = surname.toUpperCase();
		name = name.toUpperCase();
		birth_place_town = birth_place_town.toUpperCase();
		// FETCH COGNOME
		char char1=surname.charAt(0);
		char char2=surname.charAt(1);
		boolean comune_trovato=false;
		if (surname.length() < 3) {
			if (surname.length() == 1)
				System.out.println("Errore! Il cognome deve avere almeno 2 caratteri");
			else {
				if ((char1 != 'A' && char1 != 'E' && char1 != 'I'&& char1 != 'O' && char1 != 'U')&& (char2 == 'A' || char2 == 'E' || char2 == 'I'|| char2 == 'O' || char2 == 'U'))
					codice_fiscale += String.valueOf(char1) + String.valueOf(char2) + "X";
				else if ((char1 == 'A' || char1 == 'E' || char1 == 'I' || char1 == 'O' || char1 == 'U') && (char2 != 'A' && char2 != 'E' && char2 != 'I' && char2 != 'O' && char2 != 'U'))
					codice_fiscale += String.valueOf(char2) + String.valueOf(char1) + "X";
				else if ((char1 == 'A' || char1 == 'E' || char1 == 'I' || char1 == 'O' || char1 == 'U') && (char2 == 'A' || char2 == 'E' || char2 == 'I' || char2 == 'O' || char2 == 'U'))
					codice_fiscale += String.valueOf(char1) + String.valueOf(char2) + "X";
				else if ((char1 != 'A' && char1 != 'E' && char1 != 'I' && char1 != 'O' && char1 != 'U') && (char2 != 'A' && char2 != 'E' && char2 != 'I'&& char2 != 'O' && char2 != 'U'))
					System.out.println("Errore! Il cognome non puo' essere costituito esclusivamente da 2 consonanti");
			}
		} else {
			while (n_consonanti + n_vocali != 3) {
				if ((surname.charAt(conta) != 'A' && surname.charAt(conta) != 'E' && surname.charAt(conta) != 'I'
						&& surname.charAt(conta) != 'O' && surname.charAt(conta) != 'U') && !vocale) {
					n_consonanti++;
					codice_fiscale += surname.charAt(conta);
				}
				if ((surname.charAt(conta) == 'A' || surname.charAt(conta) == 'E' || surname.charAt(conta) == 'I'
						|| surname.charAt(conta) == 'O' || surname.charAt(conta) == 'U') && vocale) {
					n_vocali++;
					codice_fiscale += surname.charAt(conta);
				}

				if (conta == surname.length() - 1 && n_consonanti < 3) {
					conta = 0;
					vocale = true;
				}
				conta++;
			}
		}
		n_consonanti = 0;
		n_vocali = 0;
		conta = 0;
		ArrayList<String> array_vocali = new ArrayList<String>();
		ArrayList<String> array_consonanti = new ArrayList<String>();
		// FETCH NOME
		while (conta < name.length()) {
			if ((name.charAt(conta) != 'A' && name.charAt(conta) != 'E' && name.charAt(conta) != 'I'
					&& name.charAt(conta) != 'O' && name.charAt(conta) != 'U')) {
				n_consonanti++;
				array_consonanti.add(String.valueOf(name.charAt(conta)));
			} else {
				n_vocali++;
				array_vocali.add(String.valueOf(name.charAt(conta)));
			}
			conta++;
		}
		if (n_consonanti >= 4)
			codice_fiscale += array_consonanti.get(0) + array_consonanti.get(2) + array_consonanti.get(3);
		else if (n_consonanti == 3)
			codice_fiscale += array_consonanti.get(0) + array_consonanti.get(1) + array_consonanti.get(2);
		else if (n_consonanti == 2 && n_vocali >= 1)
			codice_fiscale += array_consonanti.get(0) + array_consonanti.get(1) + array_vocali.get(0);
		else if (n_consonanti == 1 && n_vocali >= 2)
			codice_fiscale += array_consonanti.get(0) + array_vocali.get(0) + array_vocali.get(1);
		else if (n_consonanti == 1 && n_vocali >= 1)
			codice_fiscale += array_consonanti.get(0) + array_vocali.get(1) + "X";
		else if (n_vocali >= 2 && n_consonanti == 0)
			codice_fiscale += array_vocali.get(0) + array_vocali.get(1) + "X";
		else
			System.out.println("Errore generico sul nome!");
		// FETCH DATA NASCITA
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
		cal.setTime(birth_date);
		int year = cal.get(Calendar.YEAR);
		codice_fiscale += String.valueOf(year).substring(2);
		// FETCH MESE NASCITA
		char conversione[] = { 'A', 'B', 'C', 'D', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'T' };
		int month = cal.get(Calendar.MONTH);
		codice_fiscale += conversione[month];
		// FETCH GIORNO NASCITA E SESSO
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (sex == 'M') {
			if (day < 10)
				codice_fiscale += "0" + String.valueOf(day);
			else
				codice_fiscale += String.valueOf(day);
		} else if (sex == 'F') {
			codice_fiscale += String.valueOf(day + 40);
		}
		// FETCH CODICE CATASTALE
		try {
			getCodiceCatastalePS = connection.prepareStatement("SELECT cadastral_code FROM town WHERE name =?");
			getCodiceCatastalePS.setString(1, birth_place_town);
			ResultSet rs = getCodiceCatastalePS.executeQuery();
			if(rs.next())
			{
				codice_fiscale += rs.getString("cadastral_code");
				comune_trovato=true;
			}
			else
				System.out.println("Non sono riuscito a trovare il tuo luogo di nascita nel Database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// FETCH CARATTERE DI CONTROLLO
		conta = 1;
		char curr;
		int somma = 0;
		while (conta <= codice_fiscale.length()) {
			curr = codice_fiscale.charAt(conta - 1);
			if (conta % 2 != 0) {

				if (curr == 'A' || curr == '0')
					somma += 1;
				else if (curr == 'B' || curr == '1')
					somma += 0;
				else if (curr == 'C' || curr == '2')
					somma += 5;
				else if (curr == 'D' || curr == '3')
					somma += 7;
				else if (curr == 'E' || curr == '4')
					somma += 9;
				else if (curr == 'F' || curr == '5')
					somma += 13;
				else if (curr == 'G' || curr == '6')
					somma += 15;
				else if (curr == 'H' || curr == '7')
					somma += 17;
				else if (curr == 'I' || curr == '8')
					somma += 19;
				else if (curr == 'J' || curr == '9')
					somma += 21;
				else if (curr == 'K')
					somma += 2;
				else if (curr == 'L')
					somma += 4;
				else if (curr == 'M')
					somma += 18;
				else if (curr == 'N')
					somma += 20;
				else if (curr == 'O')
					somma += 11;
				else if (curr == 'P')
					somma += 3;
				else if (curr == 'Q')
					somma += 6;
				else if (curr == 'R')
					somma += 8;
				else if (curr == 'S')
					somma += 12;
				else if (curr == 'T')
					somma += 14;
				else if (curr == 'U')
					somma += 16;
				else if (curr == 'V')
					somma += 10;
				else if (curr == 'W')
					somma += 22;
				else if (curr == 'X')
					somma += 25;
				else if (curr == 'Y')
					somma += 24;
				else if (curr == 'Z')
					somma += 23;

			} else {

				if (curr == 'A' || curr == '0')
					somma += 0;
				else if (curr == 'B' || curr == '1')
					somma += 1;
				else if (curr == 'C' || curr == '2')
					somma += 2;
				else if (curr == 'D' || curr == '3')
					somma += 3;
				else if (curr == 'E' || curr == '4')
					somma += 4;
				else if (curr == 'F' || curr == '5')
					somma += 5;
				else if (curr == 'G' || curr == '6')
					somma += 6;
				else if (curr == 'H' || curr == '7')
					somma += 7;
				else if (curr == 'I' || curr == '8')
					somma += 8;
				else if (curr == 'J' || curr == '9')
					somma += 9;
				else if (curr == 'K')
					somma += 10;
				else if (curr == 'L')
					somma += 11;
				else if (curr == 'M')
					somma += 12;
				else if (curr == 'N')
					somma += 13;
				else if (curr == 'O')
					somma += 14;
				else if (curr == 'P')
					somma += 15;
				else if (curr == 'Q')
					somma += 16;
				else if (curr == 'R')
					somma += 17;
				else if (curr == 'S')
					somma += 18;
				else if (curr == 'T')
					somma += 19;
				else if (curr == 'U')
					somma += 20;
				else if (curr == 'V')
					somma += 21;
				else if (curr == 'W')
					somma += 22;
				else if (curr == 'X')
					somma += 23;
				else if (curr == 'Y')
					somma += 24;
				else if (curr == 'Z')
					somma += 25;
			}
			conta++;
		}
		char tabella_resti[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
				'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		codice_fiscale += String.valueOf(tabella_resti[somma % 26]);
		if(!comune_trovato)
			return "Errore!";
		return codice_fiscale;
	}
}
