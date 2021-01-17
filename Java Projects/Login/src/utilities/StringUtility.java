package utilities;

import java.util.ArrayList;

import entities.Address;

public class StringUtility {
	
	
	public ArrayList<String> tokenizedStringToArrayList(String tokenized_list, String delimiters)
	{
		String[] tokens = tokenized_list.split(delimiters);
		ArrayList<String> splitted_values_list = new ArrayList<String>();
		for(int i=0; i<tokens.length; i++)
		{
			splitted_values_list.add(tokens[i]);
		}
		return splitted_values_list;
		
	}
}
