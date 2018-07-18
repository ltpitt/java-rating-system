import java.lang.reflect.Array;
import java.util.*;

public class MovieRunnerAverage {

   public void printAverageRatings(){
       SecondRatings sr = new SecondRatings();
       //SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
       //sr.getMovieSize();
       //sr.getRaterSize();
       //sr.getAverageByID("0006414",1);
       //sr.getAverageRatings(10);
       sr.getTitle("0006414");
   }

   public static void main(String args[]){
       MovieRunnerAverage mra = new MovieRunnerAverage();
       mra.printAverageRatings();
   }
}
