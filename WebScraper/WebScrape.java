import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


/* This is a utility class which is used to grab the draft values from a list of cards that is located in
 * a text file. This method opens the text file with the string fileName which should be the name of a file located in the 
 * source package of the project. It then makes calls to the website tappedout.net and manipulates the url to search for the
 * average draft pick of a card by looking through the html for the value. It then writes all of these values into a text file
 * called draft-values.txt line by line for each card and its draft value.
 * */
public class WebScrape {

	public static void main(String[] args) throws IOException {
		String fileName = "bens_cube.txt";
		String line = null;
		UrlUtil uu = new UrlUtil();
		HashMap<String, Double> draftNumber = new HashMap<String, Double>();
		PrintWriter writer = new PrintWriter("draft-values.txt", "UTF-8");
		
		 try {
	            FileReader fileReader = new FileReader(fileName);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            while((line = bufferedReader.readLine()) != null) {
	            	double num = uu.getDraftPick(line);
	            	draftNumber.put(line, num);
	            	writer.println(line + " $Draft Number:" + num);
	                System.out.println(line + " $Draft Number: " + num);
	            }
	            bufferedReader.close();         
	            writer.close();
	        } catch(FileNotFoundException ex) {
	            System.out.println("Unable to open file '" + fileName + "'");                
	        } catch(IOException ex) {
	            System.out.println("Error reading file '" + fileName + "'");                  
	            // Or we could just do this: 
	            // ex.printStackTrace();
	        }		
		 
		 System.out.println(uu.getDraftPick("Black Lotus"));	     
	}

}
