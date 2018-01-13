package com.revature.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class UrlUtil {
	/* This is the method that makes calls to the website tappedout.net.*/
	public double getDraftPick(String name) {
		
		/*The url formats the names of the card by substituting a "+" for spaces*/
		String input_name = name.replace(" ", "+");
		
		//Special escape code for apostrophe for the url parameter
		if(name.contains("'")) {
			input_name = input_name.replace("'", "&#39;");
		}
		
		//Unique case for cards with '//' in it's name
		if(name.contains("//")) {
			input_name = input_name.replace("//", "/");
			input_name = input_name.replace("+", "");
		}				
		
		//Unique case for the card Aetherling because the 'AE' is a special character in the card's name
		if(name.equals("Aetherling")) {
			input_name = "AEtherling";
		}
		String url_text = "https://tappedout.net/mtg-cards/search/?name=" + input_name + "&mana_cost=&mana_cost_converted_0=&mana_cost_converted_1=&rules=&subtype=&formats=&blocks=&rarity=&o=name_sort&submit=Filter";
		URL url;
		//Reinserts the space character for '+', needed to match the value inside the table tag on the page's html
		String htmlName = input_name.replace("+", " ");
		
		//Special Case for Far // Away card that doesn't match convention for other cards with '//' in it's card name
		if(name.equals("Far // Away")) {
			htmlName = "Far / Away";
		}
		String answer = "";
		
		//Flag to only grab the draft pick for the exact card name because the website can sometimes show mulitple results for a card name
		boolean searchFlag = false;
		
		try {
			url = new URL(url_text);
			URLConnection con = url.openConnection();
			InputStream is =con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			ArrayList<String> cardInfo = new ArrayList<String>();
			
			// read each line and write to System.out
			while ((line = br.readLine()) != null) {
				
				//Found the exact card name
				if(line.contains("data-name=\"" + htmlName)) {
					searchFlag = true;
				}
				
				//Grabs the draft value
				if(line.contains("<td class=\"center\">") && searchFlag == true) {
					cardInfo.add(line);
				}
			}
			//Formats the String and gets rid of the html tags
			answer = cardInfo.get(2).replace("<td class=\"center\">", "");
			answer = answer.replaceAll("</td>", "");
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Double.parseDouble(answer);
	}

	
}