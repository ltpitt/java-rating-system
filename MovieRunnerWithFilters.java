import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

   public void printAverageRatings(){
       //ThirdRatings tr = new ThirdRatings();
       ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
       tr.getRaterSize();
       System.out.println("MovieDatabase size:\t" + MovieDatabase.size() );
       //String ID = tr.getID("The Godfather");
       //tr.getAverageByID(ID,1);
       //tr.getAverageByID("0006414",1);
       ArrayList<Rating> averageRatingsAL = new ArrayList<Rating>();
       averageRatingsAL = tr.getAverageRatings(1);
       Collections.sort(averageRatingsAL);
       System.out.println("Number of Movie(s) found:\t\t" + averageRatingsAL.size());
       //tr.getTitle("0006414");
       //ArrayList<Rating> averageRatingsAL = new ArrayList<Rating>();
       //averageRatingsAL = tr.getAverageRatings(12);
       //Collections.sort(averageRatingsAL);
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
       MovieRunnerWithFilters mra = new MovieRunnerWithFilters();
       MovieDatabase.initialize("ratedmovies_short.csv");
       //mra.getAverageRatingOneMovie();
       mra.printAverageRatings();
   }
}
