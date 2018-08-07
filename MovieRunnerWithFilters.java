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

    public void printAverageRatingsByYear() {
        //ThirdRatings tr = new ThirdRatings();
        ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Read data for \t" + tr.getRaterSize() + " raters");
        System.out.println("Read data for \t" + MovieDatabase.size() + " movies" );

        YearAfterFilter ya = new YearAfterFilter(2000);
        ArrayList<Rating> averageRatingsAL = new ArrayList<Rating>();
        averageRatingsAL = tr.getAverageRatingsByFilter(1, ya);
        Collections.sort(averageRatingsAL);

        System.out.println("Found \t" + averageRatingsAL.size() + " movies" );
        for (Rating currentRating : averageRatingsAL){
            double currentRatingAverage = currentRating.getValue();
            String currentMovieID = currentRating.getItem();
            int currentYear = MovieDatabase.getYear(currentMovieID);
            String currentTitle = MovieDatabase.getMovie(currentMovieID).getTitle();
            System.out.println(currentRatingAverage + " " + currentYear + " " + currentTitle);
        }

    }

    public void printAverageRatingsByGenre() {
        //ThirdRatings tr = new ThirdRatings();
        ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Read data for \t" + tr.getRaterSize() + " raters");
        System.out.println("Read data for \t" + MovieDatabase.size() + " movies" );

        GenreFilter gf = new GenreFilter("Crime");
        ArrayList<Rating> averageRatingsAL = new ArrayList<Rating>();
        averageRatingsAL = tr.getAverageRatingsByFilter(1, gf);
        Collections.sort(averageRatingsAL);

        System.out.println("Found \t" + averageRatingsAL.size() + " movies" );
        System.out.println();
        for (Rating currentRating : averageRatingsAL){
            double currentRatingAverage = currentRating.getValue();
            String currentMovieID = currentRating.getItem();
            String currentTitle = MovieDatabase.getMovie(currentMovieID).getTitle();
            String genres = MovieDatabase.getMovie(currentMovieID).getGenres();
            System.out.println(currentRatingAverage + " " + currentTitle);
            System.out.println(genres);
        }

    }



    public static void main(String args[]){
       MovieRunnerWithFilters mra = new MovieRunnerWithFilters();
       MovieDatabase.initialize("ratedmovies_short.csv");
       //mra.getAverageRatingOneMovie();
       //mra.printAverageRatings();
       //mra.printAverageRatingsByYear();
        mra.printAverageRatingsByGenre();
   }
}
