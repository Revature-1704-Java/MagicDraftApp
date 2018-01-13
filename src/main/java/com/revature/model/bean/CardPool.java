import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/* This class holds the Card Pool Object which is just an Array List of Strings with
 * the name of a card pulled line by line from the supplied text document
 * */
public class CardPool {
	ArrayList<String> cardpool;

	public void setCardpool(ArrayList<String> cardpool) {
		this.cardpool = cardpool;
	}

	@Override
	public String toString() {
		return "CardPool [cardpool=" + cardpool + "]";
	}

	public CardPool(ArrayList<String> cardpool) {
		super();
		this.cardpool = cardpool;
	}

	public CardPool() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/* Reads the filename of a text document which should be formatted with the name of a
	 * card on each line. Reads the file line by line and stores the name of the card into
	 * the array list
	 * */
	public ArrayList<String> getCardPool(String filename) {

		String line = null;
		ArrayList<String> cardpool = new ArrayList<String>();
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				cardpool.add(line);
			}		
			bufferedReader.close();

		} catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + filename + "'");  
        } 
		return cardpool;
	}
	
	/*sets up the arraylist*/
	public void initCardPool(String filename) {
		this.cardpool = getCardPool(filename);
	}
	
	/*Creates an array list of packs given the number of players.
	 * There are 3 rounds of drafting so there are 3 packs per person*/
	public ArrayList<ArrayList<String>> getDraftPacks(int players) {
		ArrayList<ArrayList<String>> packs = new ArrayList<ArrayList<String>>();
		for(int x = 0; x < players * 3; x++) {
			packs.add(getPack(15));
		}
		return packs;
	}
	
	/*Creates a booster pack from the total card pool without repeats by deleting the entries that 
	 * have been picked and returns the array list that has all the cards for a pack*/
	public ArrayList<String> getPack(int pack_size) {
		int max = cardpool.size();
		int min = 0;
		Random rand = new Random();
		ArrayList<String> pack = new ArrayList<String>();
		int next_rand = 0;
		for(int x = 0; x < pack_size; x++) {
			next_rand = rand.nextInt(max - min) + min;
			pack.add(cardpool.get(next_rand));
			cardpool.remove(next_rand);
			max = cardpool.size();
		}		
		return pack;
	}
	
}
