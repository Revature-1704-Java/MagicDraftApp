import java.util.ArrayList;
import java.util.Map.Entry;

/*Driver class that shows how to use the utilities*/
public class CardUtilDriver {
	
	public static void main(String[] args) {
		CardPool cp = new CardPool();
		//Populates the array list with names of the cards
		cp.initCardPool("bens_cube.txt");
		
		DraftValuePool dvp = new DraftValuePool();		
		//Creates a text file with name of the card and its draft value
		dvp.setupDraftValue("draft-values.txt");
		
		//Print out all the card names and their draft value
		for(Entry<String, Double> e: dvp.getDraftValue().entrySet()) {
			String key = e.getKey();
			double value = e.getValue();
			System.out.println(key + " with value: " + value);					
		}
		
		System.out.println();
		System.out.println();
		System.out.println();

		//Creates 6 packs because it takes the number of players as input (3 packs per person)
		ArrayList<ArrayList<String>> booster_packs = cp.getDraftPacks(2);
		//Print out the first booster pack
		System.out.println(booster_packs.get(0));

		//This is how to remove a card from a pack (how the computer picks a card)
		//The first method grabs the index for which card is to be removed
		System.out.println(dvp.getLowestDraftPick(booster_packs.get(0)));
		//This method calls the above method from within its function call to get the index and then
		//this method will  remove the lowest draft pick from a a given pack
		System.out.println("We removed this card " + dvp.removeLowestFromPack(booster_packs.get(0)));
		
		//Print out the pack showing that the card we removed is no longer in the pack
		System.out.println(booster_packs.get(0));
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		//Testing that multiple cards can be removed
		System.out.println("We removed this card " + dvp.removeLowestFromPack(booster_packs.get(0)));
		System.out.println(booster_packs.get(0));
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		System.out.println("We removed this card " + dvp.removeLowestFromPack(booster_packs.get(0)));
		System.out.println(booster_packs.get(0));
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		System.out.println("We removed this card " + dvp.removeLowestFromPack(booster_packs.get(0)));
		System.out.println(booster_packs.get(0));
		
		System.out.println(booster_packs);
	}

}
