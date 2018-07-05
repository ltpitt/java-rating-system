import edu.duke.*;

import java.io.FileReader;
import java.io.Reader;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> moviesArrayList = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        for (CSVRecord currentRow : fr.getCSVParser()) {
            //System.out.println(currentRow);
            String anID = currentRow.get(0);
            String aTitle = currentRow.get(1);
            String aYear = currentRow.get(2);
            String theGenres = currentRow.get(4);
            String aDirector = currentRow.get(5);
            String aCountry = currentRow.get(3);
            String aPoster = currentRow.get(7);
            int theMinutes = Integer.parseInt(currentRow.get(6));

            Movie currentMovie = new Movie(anID, aTitle, aYear, theGenres, aDirector, aCountry, aPoster, theMinutes);
            moviesArrayList.add(currentMovie);
        }
        return moviesArrayList;
    }

    public void testLoadMovies(){

        ArrayList<Movie> mal = loadMovies("data/ratedmoviesfull.csv");
        //ArrayList<Movie> mal = loadMovies("data/ratedmovies_short.csv");
        int counter = 0;
        String searchedGenre = "comedy";
        int searchedMinutes = 150;
        ArrayList<Movie> longMovies = new ArrayList<Movie>();

        HashMap<String, ArrayList<String>> mostMoviesPerDirector = new HashMap<String, ArrayList<String>>();

        for (Movie currentMovie : mal) {
            // Count number of movies for searchedGenre
            List<String> genresList = Arrays.asList(currentMovie.getGenres().split(","));
            for (String genre : genresList) {
                if (genre.trim().toLowerCase().equals(searchedGenre.toLowerCase())) {
                    counter ++;
                }
            }
            // Count number of movies longer than searchedMinutes
            if (currentMovie.getMinutes() > searchedMinutes) {
                longMovies.add(currentMovie);
            }
            // Count movies per director
            for (String director: currentMovie.getDirector().split(",")) {
                director = director.toLowerCase().trim();
                if (!mostMoviesPerDirector.containsKey(director)) {
                    mostMoviesPerDirector.put(director, new ArrayList<String>(Arrays.asList(currentMovie.getTitle())));
                } else {
                    mostMoviesPerDirector.get(director).add(currentMovie.getTitle());
                }
            }

            //System.out.println(currentMovie);
        }
        System.out.println("Movies ArrayList Length: " + mal.size());
        System.out.println("Long Movies ArrayList Length: " + longMovies.size());
        System.out.println("Total movies with genre " + searchedGenre + ": " + counter);

        int maxCount = 0;
        String resultDirector = "";
        for (String director : mostMoviesPerDirector.keySet()) {
            ArrayList<String> movies = mostMoviesPerDirector.get(director);
            if (movies.size() > maxCount) {
                resultDirector = director;
                maxCount = movies.size();
            }
        }
        System.out.println("Director with most movies is: " + resultDirector + " with " + maxCount + " movies");
    }

    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> ratersArrayList = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        for (CSVRecord currentRow : fr.getCSVParser()) {
            //System.out.println(currentRow);
            String raterID = currentRow.get(0);
//            String movieID = currentRow.get(1);
            String ratings = currentRow.get(2);
//            String time = currentRow.get(3);
            Rater currentRater = new Rater(raterID);
            currentRater.addRating();
            ratersArrayList.add(currentRater);
        }
        return ratersArrayList;
    }

    public void testLoadRaters(){
        ArrayList<Rater> mal = loadRaters("data/ratings_short.csv");
        //ArrayList<Rating> mal = loadRaters("data/ratings.csv");
    }

    public static void main(String args[]){
        FirstRatings fr = new FirstRatings();
        //fr.testLoadMovies();
        fr.testLoadRaters();
    }

}
