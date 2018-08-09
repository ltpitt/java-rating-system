
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.HashMap;

public class FourthRatings {

    public double getAverageByID(String id, int minimalRaters){

        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        HashMap<String, Integer> moviesHm = new HashMap<String, Integer>();
        int totalRatings = 0;
        double totalVotes = 0.0;
        double average = 0.0;
        for (Rater currentRater : myRaters){
            if (currentRater.getItemsRated().contains(id)){
                totalRatings++;
                totalVotes += currentRater.getRating(id);
            }
        }
        if (totalRatings >= minimalRaters){
            average = (double)totalVotes/totalRatings;
        }
        System.out.println();
        System.out.println(MovieDatabase.getMovie(id).getTitle());
        System.out.println("Total ratings for " + id + ":\t\t" + totalRatings);
        System.out.println("Total votes for " + id + ":\t\t" + totalVotes);
        System.out.println("Average for  " + id + ":\t\t\t" + average);
        return average;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ro = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for (String movieID : myMovies){
            double currentAverage = getAverageByID(movieID, minimalRaters);
            if (currentAverage > 0.0) {
                Rating currentRating = new Rating(movieID, currentAverage);
                ro.add(currentRating);
            }
        }
        System.out.println(ro);
        return ro;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> ro = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);

        for (String movieID : myMovies){
            double currentAverage = getAverageByID(movieID, minimalRaters);
            if (currentAverage > 0.0) {
                Rating currentRating = new Rating(movieID, currentAverage);
                ro.add(currentRating);
            }
        }
        System.out.println(ro);
        return ro;
    }


    public static void main(String args[]){
        System.out.println("Yo!");
        FourthRatings fr = new FourthRatings();
        fr.getAverageByID("0006414",1);
    }


}
