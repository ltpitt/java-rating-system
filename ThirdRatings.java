
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.HashMap;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    public ThirdRatings(String ratingsfile){
        FirstRatings fR=new FirstRatings();
        myRaters=fR.loadRaters(ratingsfile);
    }

    public ThirdRatings() {
        // Default constructor
        this("data/ratings.csv");
    }

    public int getRaterSize(){
        System.out.println("myRaters size:\t" + myRaters.size());
        return myRaters.size();
    }

    public double getAverageByID(String id, int minimalRaters){
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

}
