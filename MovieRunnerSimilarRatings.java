import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

    public void printAverageRatings() {
        FourthRatings fr = new FourthRatings();
        //FourthRatings fr = new FourthRatings("data/ratings_short.csv");
        System.out.println("MovieDatabase size:\t" + MovieDatabase.size() );
        //String ID = tr.getID("The Godfather");
        //tr.getAverageByID(ID,1);
        //tr.getAverageByID("0006414",1);
        ArrayList<Rating> averageRatingsAL = new ArrayList<Rating>();
        averageRatingsAL = fr.getAverageRatings(35);
        Collections.sort(averageRatingsAL);
        System.out.println("Number of Movie(s) found:\t\t" + averageRatingsAL.size());
        //tr.getTitle("0006414");
        //ArrayList<Rating> averageRatingsAL = new ArrayList<Rating>();
        //averageRatingsAL = tr.getAverageRatings(12);
        //Collections.sort(averageRatingsAL);
        //System.out.println(averageRatingsAL.size());
    }

    public void printAverageRatingsByYear() {
        FourthRatings fr = new FourthRatings();
        //FourthRatings fr = new FourthRatings("data/ratings_short.csv");
        //System.out.println("Read data for \t" + fr.getRaterSize() + " raters");
        System.out.println("Read data for \t" + MovieDatabase.size() + " movies" );

        YearAfterFilter ya = new YearAfterFilter(2000);
        ArrayList<Rating> averageRatingsAL = new ArrayList<Rating>();
        averageRatingsAL = fr.getAverageRatingsByFilter(20, ya);
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

    public static void main(String args[]){
        MovieRunnerWithFilters mra = new MovieRunnerWithFilters();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //mra.getAverageRatingOneMovie();
        mra.printAverageRatings();
        mra.printAverageRatingsByYear();
        //mra.printAverageRatingsByGenre();
        //mra.printAverageRatingsByMinutes();
        //mra.printAverageRatingsByDirector();
        //mra.printAverageRatingsByYearAfterAndGenre();
        //mra.printAverageRatingsByDirectorsAndMinutes();
    }

}
