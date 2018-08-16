import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MovieRunnerSimilarRatings {

    private String movieCsv, ratingsCsv;
    private FourthRatings fourthRatings;
    private ArrayList<Rating> ratingArrayList, similarRatings, similarRatingsByFilter;
    private AllFilters filterList;

    public MovieRunnerSimilarRatings() {
        movieCsv = "ratedmovies_short.csv";
        //movieCsv = "ratedmoviesfull.csv";
        ratingsCsv = "ratings_short.csv";
        //ratingsCsv = "ratings.csv";

        MovieDatabase.initialize(movieCsv);
        RaterDatabase.initialize(ratingsCsv);

        fourthRatings = new FourthRatings();
        System.out.println("Number of movies loaded from file: " + MovieDatabase.size());
        System.out.println("Number of raters loaded from file: " + RaterDatabase.size());
        filterList = new AllFilters();
    }

    public void printAverageRatings(int minimalRaters) {
        ratingArrayList = fourthRatings.getAverageRatings(minimalRaters);
        ratingArrayList.sort(Comparator.naturalOrder());
        System.out.println("Total of movies with at least " + minimalRaters + " raters: " + ratingArrayList.size());
        for (Rating rating : ratingArrayList) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(int minimalRaters, int year, String genre) {
        filterList.addFilter(new YearAfterFilter(year));
        filterList.addFilter(new GenreFilter(genre));
        ratingArrayList = fourthRatings.getAverageRatingsByFilter(minimalRaters, filterList);

        ratingArrayList.sort(Comparator.naturalOrder());
        System.out.println("Total movies: " + ratingArrayList.size());
        for (Rating rating : ratingArrayList) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) +
                    " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println(MovieDatabase.getGenres(rating.getItem()));

        }
    }

    public void printSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        /*
        This method creates a new FourthRatings object, reads data into the MovieDatabase and RaterDatabase,
        and then calls getSimilarRatings for a particular rater ID, a number for the top number of similar
        raters, and a number of minimal rateSimilarRatings, and then lists recommended movies and their
        similarity ratings. For example, using the files ratedmoviesfull.csv and ratings.csv and the rater
        ID 65, the number of minimal raters 5, and the number of top similar raters set to 20, the movie
        returned with the top rated average is “The Fault in Our Stars”.
         */
        similarRatings = fourthRatings.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        for (Rating rating : similarRatings) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " - " + rating.getValue());
        }

    }

    public void printSimilarRatingsByGenre(String id, int numSimilarRaters, int minimalRaters,
                                           String genre) {
        /*
        This method is similar to printSimilarRatings but also uses a genre filter and then lists recommended
        movies and their similarity ratings, and for each movie prints the movie and its similarity rating on
        one line and its genres on a separate line below it. For example, using the files ratedmoviesfull.csv
        and ratings.csv, the genre “Action”, the rater ID 65, the number of minimal raters set to 5, and the
        number of top similar raters set to 20, the movie returned with the top rated average is “Rush”.
         */
        similarRatingsByFilter = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters,
                new GenreFilter(genre));
        for (Rating rating : similarRatingsByFilter) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " - " + rating.getValue());
            System.out.println(MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printSimilarRatingsByDirector(String id, int numSimilarRaters, int minimalRaters,
                                              String director) {
        /*
        This method is similar to printSimilarRatings but also uses a director filter and then lists recommended
        movies and their similarity ratings, and for each movie prints the movie and its similarity rating on one
        line and its directors on a separate line below it. For example, using the files ratedmoviesfull.csv and
        ratings.csv, the directors “Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone”, the rater ID
        1034, the number of minimal raters set to 3, and the number of top similar raters set to 10, the movie
        returned with the top rated average is “Unforgiven”.
         */
        similarRatingsByFilter = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters,
                new DirectorsFilter(director));
        for (Rating rating : similarRatingsByFilter) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " - " + rating.getValue());
            System.out.println(MovieDatabase.getDirector(rating.getItem()));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes(String id, int numSimilarRaters, int minimalRaters,
                                                     String genre, int lowMinutes, int highMinutes) {
        /*
        This method is similar to printSimilarRatings but also uses a genre filter and a minutes filter and
        then lists recommended movies and their similarity ratings, and for each movie prints the movie, its
        minutes, and its similarity rating on one line and its genres on a separate line below it. For example,
        using the files ratedmoviesfull.csv and ratings.csv, the genre “Adventure”, minutes between 100 and 200
        inclusive, the rater ID 65, the number of minimal raters set to 5, and the number of top similar raters
        set to 10, the movie returned with the top rated average is “Interstellar”.
         */
        filterList.addFilter(new GenreFilter(genre));
        filterList.addFilter(new MinutesFilter(lowMinutes, highMinutes));
        similarRatingsByFilter = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, filterList);
        for (Rating rating : similarRatingsByFilter) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " - " + rating.getValue());
            System.out.println(MovieDatabase.getGenres(rating.getItem()) + " - Length: "
                    + MovieDatabase.getMinutes(rating.getItem()));
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes(String id, int numSimilarRaters, int minimalRaters,
                                                         int year, int lowMinutes, int highMinutes) {
        /*
        This method is similar to printSimilarRatings but also uses a year-after filter and a minutes filter
        and then lists recommended movies and their similarity ratings, and for each movie prints the movie,
        its year, its minutes, and its similarity rating on one line. For example, using the files
        ratedmoviesfull.csv and ratings.csv, the year 2000, minutes between 80 and 100 inclusive, the rater
        ID 65, the number of minimal raters set to 5, and the number of top similar raters set to 10, the movie
        returned with the top rated average is “The Grand Budapest Hotel”.
         */
        filterList.addFilter(new YearAfterFilter(year));
        filterList.addFilter(new MinutesFilter(lowMinutes, highMinutes));
        similarRatingsByFilter = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, filterList);
        for (Rating rating : similarRatingsByFilter) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + " - " + rating.getValue());
            System.out.println(MovieDatabase.getYear(rating.getItem()) + " - Length: "
                    + MovieDatabase.getMinutes(rating.getItem()));
        }
    }

    public static void main(String args[]){
        MovieRunnerSimilarRatings movieRunnerSimilarRatings = new MovieRunnerSimilarRatings();
        int minimalRaters = 5;
        int year = 1975;
        String genre = "Drama";
        int minMinutes = 70;
        int maxMinutes = 200;
        String director = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        String id = "314";
        int numSimilarRaters = 10;
        movieRunnerSimilarRatings.printAverageRatings(minimalRaters);
        //movieRunnerSimilarRatings.printAverageRatingsByYearAfterAndGenre(minimalRaters, year, genre);
        //movieRunnerSimilarRatings.printSimilarRatings(id, numSimilarRaters, minimalRaters);
        //movieRunnerSimilarRatings.printSimilarRatingsByGenre(id,numSimilarRaters,minimalRaters,genre);
        //movieRunnerSimilarRatings.printSimilarRatingsByDirector(id,numSimilarRaters,minimalRaters,director);
        //movieRunnerSimilarRatings.printSimilarRatingsByGenreAndMinutes(id,numSimilarRaters,minimalRaters,genre,minMinutes,maxMinutes);
        //movieRunnerSimilarRatings.printSimilarRatingsByYearAfterAndMinutes(id, numSimilarRaters, minimalRaters, year, minMinutes, maxMinutes);
    }

}
