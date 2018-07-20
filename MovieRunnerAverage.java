import java.lang.reflect.Array;
import java.util.*;

public class MovieRunnerAverage {

   public void printAverageRatings(){
       //SecondRatings sr = new SecondRatings();
       SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
       //sr.getMovieSize();
       //sr.getRaterSize();
       //sr.getAverageByID("0006414",1);
       //sr.getAverageRatings(10);
       //sr.getTitle("0006414");
       ArrayList<Rating> averageRatingsAL = new ArrayList<Rating>();
       averageRatingsAL = sr.getAverageRatings(3);

       Collections.sort(averageRatingsAL);

       for (Rating currentRating : averageRatingsAL){
           System.out.println(currentRating.getValue() + " " + sr.getTitle(currentRating.getItem()));
       }

   }




   public static void main(String args[]){
       MovieRunnerAverage mra = new MovieRunnerAverage();
       mra.printAverageRatings();
   }
}
