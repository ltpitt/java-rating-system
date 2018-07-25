/**
 * Write a description of class Rater here.
 *
 * @author Davide Nastri
 * @version 7/24/2018
 */

import java.util.*;

public interface Rater {

    public void addRating(String item, double rating);
    public boolean hasRating(String item);
    public String getID();
    public double getRating(String item);
    public int numRatings();
    public ArrayList<String> getItemsRated();

}

