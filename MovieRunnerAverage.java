import java.util.*;

public class MovieRunnerAverage {

   public void printAverageRatings(){
       SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
       System.out.println(sr.getMovieSize());
       System.out.println(sr.getRaterSize());

   }

   public static void main(String args[]){
       MovieRunnerAverage mra = new MovieRunnerAverage();
       mra.printAverageRatings();
   }
}
