import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

   public void printAverageRatings(){
       ThirdRatings tr = new ThirdRatings();
       //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
       tr.getRaterSize();
       System.out.println("MovieDatabase size:\t" + MovieDatabase.size() );
       //String ID = tr.getID("The Godfather");
       //tr.getAverageByID(ID,1);
       //tr.getAverageByID("0006414",1);
       ArrayList<Rating> averageRatingsAL = new ArrayList<Rating>();
       averageRatingsAL = tr.getAverageRatings(35);
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
        ThirdRatings tr = new ThirdRatings();
        //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Read data for \t" + tr.getRaterSize() + " raters");
        System.out.println("Read data for \t" + MovieDatabase.size() + " movies" );

        YearAfterFilter ya = new YearAfterFilter(2000);
        ArrayList<Rating> averageRatingsAL = new ArrayList<Rating>();
        averageRatingsAL = tr.getAverageRatingsByFilter(20, ya);
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
        ThirdRatings tr = new ThirdRatings();
        //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Read data for \t" + tr.getRaterSize() + " raters");
        System.out.println("Read data for \t" + MovieDatabase.size() + " movies" );

        GenreFilter gf = new GenreFilter("Comedy");
        ArrayList<Rating> averageRatingsAL = new ArrayList<Rating>();
        averageRatingsAL = tr.getAverageRatingsByFilter(20, gf);
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

    public void printAverageRatingsByMinutes() {
        ThirdRatings tr = new ThirdRatings();
        //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Read data for \t" + tr.getRaterSize() + " raters");
        System.out.println("Read data for \t" + MovieDatabase.size() + " movies" );

        MinutesFilter mf = new MinutesFilter(105, 135);
        ArrayList<Rating> averageRatingsAL = new ArrayList<Rating>();
        averageRatingsAL = tr.getAverageRatingsByFilter(5, mf);
        Collections.sort(averageRatingsAL);

        System.out.println("Found \t" + averageRatingsAL.size() + " movies" );
        System.out.println();
        for (Rating currentRating : averageRatingsAL){
            double currentRatingAverage = currentRating.getValue();
            String currentMovieID = currentRating.getItem();
            String currentTitle = MovieDatabase.getMovie(currentMovieID).getTitle();
            int minutes = MovieDatabase.getMovie(currentMovieID).getMinutes();
            System.out.println(currentRatingAverage + "\tTime:\t" + minutes + "\t" + currentTitle);
        }

    }

    public void printAverageRatingsByDirector() {
        ThirdRatings tr = new ThirdRatings();
        //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Read data for \t" + tr.getRaterSize() + " raters");
        System.out.println("Read data for \t" + MovieDatabase.size() + " movies" );

        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> averageRatingsAL = new ArrayList<Rating>();
        averageRatingsAL = tr.getAverageRatingsByFilter(4, df);
        Collections.sort(averageRatingsAL);

        System.out.println("Found \t" + averageRatingsAL.size() + " movies" );
        System.out.println();
        for (Rating currentRating : averageRatingsAL){
            double currentRatingAverage = currentRating.getValue();
            String currentMovieID = currentRating.getItem();
            String currentTitle = MovieDatabase.getMovie(currentMovieID).getTitle();
            String currentDirector = MovieDatabase.getDirector(currentMovieID);
            System.out.println(currentRatingAverage + "\t" + currentTitle + "\n" + currentDirector);
        }

    }


    public static void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings tr = new ThirdRatings();
        //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("Read data for \t" + tr.getRaterSize() + " raters");
        System.out.println("Read data for \t" + MovieDatabase.size() + " movies" );

        AllFilters af = new AllFilters();
        YearAfterFilter ya = new YearAfterFilter(1990);
        GenreFilter gf = new GenreFilter("Drama");
        af.addFilter(ya);
        af.addFilter(gf);

        ArrayList<Rating> averageRatingsAL = new ArrayList<Rating>();
        averageRatingsAL = tr.getAverageRatingsByFilter(8, af);
        Collections.sort(averageRatingsAL);

        System.out.println("Found \t" + averageRatingsAL.size() + " movies" );
        System.out.println();
        for (Rating currentRating : averageRatingsAL){
            double currentRatingAverage = currentRating.getValue();
            String currentMovieID = currentRating.getItem();
            String currentTitle = MovieDatabase.getMovie(currentMovieID).getTitle();
            String currentDirector = MovieDatabase.getDirector(currentMovieID);
            int currentYear = MovieDatabase.getYear(currentMovieID);
            String currentGenre = MovieDatabase.getGenres(currentMovieID);
            System.out.println(currentRatingAverage + " - " + currentTitle + " - " + currentDirector + " - " + currentYear + " - " + currentGenre);
        }

    }

    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr = new ThirdRatings();
        //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        int minimalRaters = 3;
        int min = 90;
        int max = 180;
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        AllFilters filterCriteria = new AllFilters();
        filterCriteria.addFilter(new DirectorsFilter(directors));
        filterCriteria.addFilter(new MinutesFilter(min, max));
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
        if (ratings.size() == 0 || ratings.size() == 1)
            System.out.println(ratings.size() + " movie matched");
        else
            System.out.println(ratings.size() + " movies matched");
        Collections.sort(ratings);
        for(Rating r: ratings) {
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()) + "\n    " + MovieDatabase.getDirector(r.getItem()));
        }
    }

    public static void main(String args[]){
       MovieRunnerWithFilters mra = new MovieRunnerWithFilters();
       //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
       //mra.getAverageRatingOneMovie();
       //mra.printAverageRatings();
       //mra.printAverageRatingsByYear();
        //mra.printAverageRatingsByGenre();
        //mra.printAverageRatingsByMinutes();
        mra.printAverageRatingsByDirector();
        //mra.printAverageRatingsByYearAfterAndGenre();
        //mra.printAverageRatingsByDirectorsAndMinutes();
   }
}
