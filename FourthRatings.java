
/**
 * Write a description of SecondRatings here.
 *
 * @author Davide Nastri
 * @version 8/11/2018
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FourthRatings {

//    This method should return an ArrayList of type Rating, of movies and their weighted average ratings using
//    only the top numSimilarRaters with positive ratings and including only those movies that have at least
//    minimalRaters ratings from those most similar raters (not just minimalRaters ratings overall).
//    For example, if minimalRaters is 3 and a movie has 4 ratings but only 2 of those ratings were made by
//    raters in the top numSimilarRaters, that movie should not be included.
//    These Rating objects should be returned in sorted order by weighted average rating from largest to smallest
//    ratings.
//    This method is very much like the getAverageRatings method you have written previously.
//    In particular this method should:
//    For every rater, get their similarity rating to the given parameter rater id.
//    Include only those raters with positive similarity ratings—those that are more similar to rater id.
//    Which method could you call?
//    For each movie, calculate a weighted average movie rating based on: Use only the top (largest)
//    numSimilarRaters raters.
//    For each of these raters, multiply their similarity rating by the rating they gave that movie.
//    This will emphasize those raters who are closer to the rater id, since they have greater weights.
//    The weighted average movie rating for a particular movie is the sum of these weighted average ratings
//    (for each rater multiply their similarity rating by their rating for the movie), divided by the total
//    number of such ratings.
//    This method returns an ArrayList of Ratings for movies and their calculated weighted ratings, in sorted order.

    public ArrayList<Rating> getSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> similarRatings = new ArrayList<Rating>();
        ArrayList<Rating> similarities = getSimilarities(raterID);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String movieID : movies){
            double rating = 0.0;
            int n = 0;
            for (int i=0; i < numSimilarRaters; i++){
                Rating currentRating = similarities.get(i);
                String currentRatingID = currentRating.getItem();
                double currentRatingValue = currentRating.getValue();
                double o_rt = 0;
                try {
                    o_rt = RaterDatabase.getRater(currentRatingID).getRating(movieID);
                }
                catch(NullPointerException e) {
                    continue;
                }
                rating += currentRatingValue * o_rt;
                n++;
            }
            if (n <= minimalRaters){
                similarRatings.add(new Rating(movieID, (rating/n)));
            }
            Collections.sort(similarRatings, Collections.reverseOrder());
        }
        return similarRatings;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String raterID, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> similarRatings = new ArrayList<Rating>();
        ArrayList<Rating> similarities = getSimilarities(raterID);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String movieID : movies){
            double rating = 0.0;
            int n = 0;
            for (int i=0; i < numSimilarRaters; i++){
                Rating currentRating = similarities.get(i);
                String currentRatingID = currentRating.getItem();
                double currentRatingValue = currentRating.getValue();
                double o_rt = 0;
                try {
                    o_rt = RaterDatabase.getRater(currentRatingID).getRating(movieID);
                }
                catch(NullPointerException e) {
                    continue;
                }
                rating += currentRatingValue * o_rt;
                n++;
            }
            if (n <= minimalRaters){
                similarRatings.add(new Rating(movieID, (rating/n)));
            }
            Collections.sort(similarRatings, Collections.reverseOrder());
        }
        return similarRatings;
    }

    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> similarities = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        for (Rater currentRater : raters){
            if (!currentRater.getID().equals(id)){
                double product = dotProduct(me, currentRater);
                if (product >= 0){
                    Rating rating = new Rating(currentRater.getID(), product);
                    similarities.add(rating);
                }
            }
        }
        Collections.sort(similarities);
        return similarities;
    }

    private double dotProduct(Rater me, Rater r){
        double product = 0.0;
        ArrayList<String> myItemsRated = me.getItemsRated();
        ArrayList<String> raterItemsRater = r.getItemsRated();
        for (String id : myItemsRated){
            if (raterItemsRater.contains(id)){
                product += (me.getRating(id) - 5) * (r.getRating(id) - 5);
            }
        }
        return product;
    }

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
