import java.lang.reflect.Array;
import java.util.*;

public class MovieRunnerAverage {

   public void printAverageRatings(){
       SecondRatings sr = new SecondRatings();
       //SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
       //sr.getMovieSize();
       //sr.getRaterSize();
       //String ID = sr.getID("The Godfather");
       //sr.getAverageByID(ID,1);
       //sr.getAverageByID("0006414",1);
       //sr.getAverageRatings(10);
       //sr.getTitle("0006414");
       ArrayList<Rating> averageRatingsAL = new ArrayList<Rating>();
       averageRatingsAL = sr.getAverageRatings(12);

       Collections.sort(averageRatingsAL);

       for (Rating currentRating : averageRatingsAL){
           System.out.println(currentRating.getValue() + " " + sr.getTitle(currentRating.getItem()));
           break;
       }

        //System.out.println(averageRatingsAL.size());
   }

    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings();
        //SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        System.out.println("\ngetAverageRatingOneMovie():");
        String ID = sr.getID("Vacation");
        sr.getAverageByID(ID, 1);

    }


   public static void main(String args[]){
       MovieRunnerAverage mra = new MovieRunnerAverage();
       mra.printAverageRatings();
       mra.getAverageRatingOneMovie();
   }
}
