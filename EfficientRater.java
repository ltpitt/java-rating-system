
/**
 * Efficient version of Rater, using hashmap.
 * 
 * @author Davide Nastri
 * @version 7/25/2018
 */

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {

        myRatings.put(item,new Rating(item,rating));

    }

    public boolean hasRating(String item) {
        if(myRatings.containsKey(item)){
            return true;
        }else{
            return false;
        }
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        for(String currentItem : myRatings.keySet()){
            if (myRatings.get(currentItem).getItem().equals(item)){
                return myRatings.get(currentItem).getValue();
            }
        }
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        return new ArrayList<String>(myRatings.keySet());
    }
}
