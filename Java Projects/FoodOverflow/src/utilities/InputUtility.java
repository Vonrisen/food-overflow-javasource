package utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import entities.Address;

public class InputUtility {

	public String formatDate(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		fmt.setCalendar(gc);
		String formatted_date = fmt.format(gc.getTime());
		return formatted_date;
	}

	public List<String> tokenizedStringToList(String tokenized_list, String regex_delimiters) {
		String[] tokens = tokenized_list.split(regex_delimiters);
		List<String> splitted_values_list = new ArrayList<String>();
		for (int i = 0; i < tokens.length; i++) {
			splitted_values_list.add(tokens[i]);
		}
		return splitted_values_list;
	}

	public Address tokenizedStringToAddress(String address, String regex_delimiters) {
		String[] tokens = address.split(regex_delimiters);
		Address addr = new Address(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
		return addr;
	}

	public String arrayListToTokenizedString(List<String> strings, String delimiters) {
		String tokenized_string = "";
		if (strings == null || strings.isEmpty())
			return "";
		for (String s : strings)
			tokenized_string += s + ", ";
		return tokenized_string.substring(0, tokenized_string.length() - 2);

	}

}
