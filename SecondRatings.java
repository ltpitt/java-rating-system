
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings fR=new FirstRatings();
        myMovies=fR.loadMovies(moviefile);
        myRaters=fR.loadRaters(ratingsfile);
    }

    public SecondRatings() {
        // Default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public int getMovieSize(){
        System.out.println("myMovies size:\t" + myMovies.size());
        return myMovies.size();
    }

    public int getRaterSize(){
        System.out.println("myRaters size:\t" + myRaters.size());
        return myRaters.size();
    }

    public double getAverageByID(String id, int minimalRaters){
        double average = 0.0;
        if (minimalRaters < minimalRaters) {
            return average;
        }
        HashMap<String, Integer> moviesHm = new HashMap<String, Integer>();
        int totalRatings = 0;
        double totalVotes = 0;
        for (Rater currentRater : myRaters){
            for (String currentMovie : currentRater.getItemsRated()) {
                if (currentMovie.equals(id)) {
                    if (currentRater.getRating(id) != -1) {
                        totalRatings++;
                        totalVotes = totalVotes + currentRater.getRating(id);
                    }
                }
            }
        }
        average = totalVotes / totalRatings;
        System.out.println();
        System.out.println("Total ratings for " + id + ":\t\t" + totalRatings);
        System.out.println("Total votes for " + id + ":\t\t" + totalVotes);
        System.out.println("Average for  " + id + ":\t\t\t" + average);
        return average;
    }

}
