import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DraftValuePool {
	private HashMap<String, Double> draftValue;

	public HashMap<String, Double> getDraftValue() {
		return draftValue;
	}

	@Override
	public String toString() {
		return "DraftValuePool [draftValue=" + draftValue + "]";
	}

	public DraftValuePool(HashMap<String, Double> draftValue) {
		super();
		this.draftValue = draftValue;
	}

	public DraftValuePool() {
		super();
		// TODO Auto-generated constructor stub
	}
	/*Initialization method to setup the draft value hashmap for lookup from a passed in file name which holds
	 * the name of the card and the draft pick value*/
	public void setupDraftValue(String filename) {		
		this.draftValue = new HashMap<String, Double>();
		try {
			String line = null;
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				//Regular expression that breaks up line of text between name and its value
				String[] draft_pair = line.split(" \\WDraft Number\\W");
				double dub = Double.parseDouble(draft_pair[1]);
				this.draftValue.put(draft_pair[0], dub);
			}
			bufferedReader.close();
		} catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");                
        } catch(IOException ex) {
            System.out.println("Error reading file '" + filename + "'");  
        }			
	}

	/*  Grabs the lowest draft pick from a given pack. The lowest number denotes that this card was picked 
	 *  on that iteration during a round of drafting. I.E. a draft value of 1 means this card was picked 
	 *  first from a full pack of 15. In the case of a tie we just return the first card we found that had 
	 *  the lowest number. We return the index of this card so that we can remove it from the Array List.
	 * */
	public int getLowestDraftPick(ArrayList<String> pack) {
		double lowestValue = draftValue.get(pack.get(0));
		int index = 0;
		int return_index = index;
		for(String s: pack) {
			double temp = draftValue.get(s);
			if(temp < lowestValue) {
				lowestValue = temp;
				return_index = index;
			}
			index++;
		}
		return return_index;		
	}
	
	/* Removes the lowest draft pick from a pack and returns the name of the card that was
	 * removed.
	 * */
	public String removeLowestFromPack(ArrayList<String> pack) {
		int index = getLowestDraftPick(pack);
		String answer = pack.get(index);
		pack.remove(index);
		return answer;
	}
	

}
