import java.util.*;

public class MovieRunnerAverage {

   public void printAverageRatings(){
       SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
       sr.getMovieSize();
       sr.getRaterSize();
       sr.getAverageByID("1798709",2);
   }

   public static void main(String args[]){
       MovieRunnerAverage mra = new MovieRunnerAverage();
       mra.printAverageRatings();
   }
}
