package utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public ArrayList<String> tokenizedStringToArrayList(String tokenized_list, String regex_delimiters)
	{
		String[] tokens = tokenized_list.split(regex_delimiters);
		ArrayList<String> splitted_values_list = new ArrayList<String>();
		for(int i=0; i<tokens.length; i++)
		{
			splitted_values_list.add(tokens[i]);
		}
		return splitted_values_list;	
	}
	
	public String addressToTokenizedString(Address address, String delimiters)
	{
		String tokenized_address;
		tokenized_address = address.getName()+delimiters+address.getCivic_number()+delimiters+address.getCap()+delimiters+address.getCity()+delimiters+address.getProvince_abbrevation();
		return tokenized_address;
	}
	
	
	public String closingDaysToTokenizedString(ArrayList<String>closing_days, String delimiters)
	{
		String tokenized_closing_days = "";
		for(int i=0; i<closing_days.size(); i++)
		{
			tokenized_closing_days+=closing_days.get(i)+", ";
		}
		return tokenized_closing_days.substring(0, tokenized_closing_days.length()-2);
	}
	
	
}
