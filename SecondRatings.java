
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
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
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
        System.out.println("Total ratings for " + id + ":\t\t" + totalRatings);
        System.out.println("Total votes for " + id + ":\t\t" + totalVotes);
        System.out.println("Average for  " + id + ":\t\t\t" + average);
        return average;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ro = new ArrayList<Rating>();
        for (Movie currentMovie : myMovies){
            double currentAverage = getAverageByID(currentMovie.getID(), minimalRaters);
            if (currentAverage > 0.0) {
                Rating currentRating = new Rating(currentMovie.getID(), getAverageByID(currentMovie.getID(), minimalRaters));
                ro.add(currentRating);
            }
        }
        System.out.println(ro);
        return ro;
    }

    public String getTitle(String id){
        String result = "ID not found";
        for (Movie currentMovie : myMovies) {
            if (currentMovie.getID().equals(id)){
                result = currentMovie.getTitle();
                break;
            }
        }
        System.out.println("\ngetTitle for id " + id + ":\t" + result);
        return result;
    }


}
