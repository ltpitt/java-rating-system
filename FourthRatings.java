
/**
 * Ratings class
 * 
 * @author Davide Nastri
 * @version 13/09/2018
 */

import java.util.*;

public class FourthRatings {
    
    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    
    //Returns the similarity rating of raters r and me as a double.
    //This is a private helper method called by getSimilarities.
    private double dotProduct(Rater me, Rater r) {
        //Stroes the similarity rating of me and r. Initialized to 0.
        double sim = 0;
        //Iterates over every movie id in the ArrayList<String> 
        //returned by r.getItemsRated.
        for(String item : r.getItemsRated()) {
            //Checks if me has also rated the movie whose ID is item
            if(me.hasRating(item)) {               
                //If above conition is satisfied, the product of the 
                //mean ratings is added to the similarity rating 
                sim += (me.getRating(item) - 5) * (r.getRating(item) - 5);
            }
        }
        return sim;
    }
    
    
    //Returns similarity ratings of other raters in the database to the 
    //rater whose ID is id. Excludes raters with negative similarity ratings.
    //This is a private helper method called by getSimilarRatings.
    private ArrayList<Rating> getSimilarities(String id) {
        Rater me = RaterDatabase.getRater(id);
        ArrayList<Rating> toRet = new ArrayList<Rating>();
        for(Rater r : RaterDatabase.getRaters()) {            
            //If r == me, moves to the next rater
            if(r.equals(me)) {
                continue;
            }        
            //Private helper method dotProduct is called to get the similarity 
            //between me and r
            double dp = dotProduct(me, r);           
            //If the two are completely dissimilar, moves on to the next rater.
            if(dp < 0) {
                continue;
            }             
            //Adds a new Rating to toRet of rater r and similarity rating dp
            toRet.add(new Rating(r.getID(), dp));
        }        
        //Sorts toRet by similarity rating in decreasing order.
        Collections.sort(toRet, Collections.reverseOrder());
        return toRet;
    }
    
    //This method returns an ArrayList<rating> where Rating is of movie and 
    //its weighted average rating. This weighted average rating is calculated 
    //using only the top numSimilarRaters raters. This list includes only those
    //movies that have at least minimalRaters in the top numSimilarRaters raters.
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        //This ArrayList will store the ratings of movies and be returned
        //after being sorted. 
        ArrayList<Rating> toRet = new ArrayList<Rating>();
        
        //Private helper method getSimilarities is called with parameter id
        //and the Ratings of other raters are returned sorted in descending order. 
        ArrayList<Rating> raters = getSimilarities(id);
        
        //Iterates over all Movie IDs of movies in MovieDatabase
        for(String movie : MovieDatabase.filterBy(new TrueFilter())) {
            double totalWeightedRating = 0.0;
            int numTopRaters = 0;
            //Iterates over the top numSimilarRaters ratings in raters
            for(int i = 0 ; i < numSimilarRaters ; i ++) {
                //Stores the rating at index i
                Rating raterSimilarityRating = raters.get(i); 
                //Stores the Rater whose rating is at index i
                Rater rater = RaterDatabase.getRater(raterSimilarityRating.getItem()); 
                
                if(RaterDatabase.getRater(id).hasRating(movie)) continue;
                //If rater has rated movie with id movie then his weightd rating is added to
                //totalWightedRating and numTopRaters is incremented.
                if(rater.hasRating(movie)) {
                    totalWeightedRating += (rater.getRating(movie) ) * raterSimilarityRating.getValue();
                    numTopRaters ++;
                }
            }
            //If numSimilarRaters or more topRaters have rated the movie, it is
            //added to toRet.
            if(numTopRaters >= minimalRaters) {
                toRet.add(new Rating(movie, totalWeightedRating/numTopRaters));
            }
        }
        Collections.sort(toRet, Collections.reverseOrder());
        //System.out.println(toRet);
        return toRet;
    }
    
    //This method returns an ArrayList<rating> where Rating is of movie and 
    //its weighted average rating. This weighted average rating is calculated 
    //using only the top numSimilarRaters raters. This list includes only those
    //movies that have at least minimalRaters in the top numSimilarRaters raters.
    //It only looks at the movies after they have been filtered by Filter f.
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter f) {
        //This ArrayList will store the ratings of movies and be returned
        //after being sorted. 
        ArrayList<Rating> toRet = new ArrayList<Rating>();
        
        //Private helper method getSimilarities is called with parameter id
        //and the Ratings of other raters are returned sorted in descending order. 
        ArrayList<Rating> raters = getSimilarities(id);
        
        //Iterates over all Movie IDs of movies in MovieDatabase
        for(String movie : MovieDatabase.filterBy(f)) {
            double totalWeightedRating = 0.0;
            int numTopRaters = 0;
            //Iterates over the top numSimilarRaters ratings in raters
            for(int i = 0 ; i < numSimilarRaters ; i ++) {
                //Stores the rating at index i
                Rating raterSimilarityRating = raters.get(i); 
                //Stores the Rater whose rating is at index i
                Rater rater = RaterDatabase.getRater(raterSimilarityRating.getItem()); 
                
                if(RaterDatabase.getRater(id).hasRating(movie)) continue;
                //If rater has rated movie with id movie then his weightd rating is added to
                //totalWightedRating and numTopRaters is incremented.
                if(rater.hasRating(movie)) {
                    totalWeightedRating += (rater.getRating(movie) ) * raterSimilarityRating.getValue();
                    numTopRaters ++;
                }
            }
            //If numSimilarRaters or more topRaters have rated the movie, it is
            //added to toRet.
            if(numTopRaters >= minimalRaters) {
                toRet.add(new Rating(movie, totalWeightedRating/numTopRaters));
            }
        }
        Collections.sort(toRet, Collections.reverseOrder());
        //System.out.println(toRet);
        return toRet;
    }
  
    //Constructor that takes in the name of a file and calls the initialize
    //method of RaterDatase.
    public FourthRatings(String ratingsFile) {
        RaterDatabase.initialize(ratingsFile);
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        for(String id : movies) {
            double avg = getAverageByID(id, minimalRaters);
            if(avg == 0.0) continue;
            avgRatings.add(new Rating(id, avg));
        }
        return avgRatings;
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        double total = 0.0;
        int count = 0;
        for(Rater r : RaterDatabase.getRaters()) {
            Double rating = r.getRating(id);
            if(rating != -1) {
                total += rating;
                count ++;
            }
        }
        return (count >= minimalRaters) ? total/count : 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String id : movies) {
            double avg = getAverageByID(id, minimalRaters);
            if(avg == 0.0) continue;
            avgRatings.add(new Rating(id, avg));
        }
        return avgRatings;
    }

}