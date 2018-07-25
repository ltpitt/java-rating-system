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



    public ArrayList<Rater> addRaterToRaters(String raterID, String item, double value, ArrayList<Rater> ratersArrayList){
        Rater newRater = new EfficientRater(raterID);
        newRater.addRating(item, value);
        ratersArrayList.add(newRater);
        return ratersArrayList;
    }

    public ArrayList<Rater> loadRaters(String filename){
        System.out.println();
        System.out.println("Loading raters from file: " + filename);
        ArrayList<Rater> ratersArrayList = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);

        int index = 0;
        for (CSVRecord currentRow : fr.getCSVParser()) {

            String raterID = currentRow.get(0);
            String item = currentRow.get(1);
            double value = Double.parseDouble(currentRow.get(2));

            if (ratersArrayList.size() == 0) {
                System.out.println("New rater:\t\t\t\t\t" + raterID + " - creating new entry");
                addRaterToRaters(raterID, item, value, ratersArrayList);
                index++;
            } else {
                if (ratersArrayList.get(index-1).getID().equals(raterID)) {
                    System.out.println("Rater:\t\t\t\t\t\t" + raterID + " - already in Raters, appending data");
                    ratersArrayList.get(index-1).addRating(item, value);

                } else {
                    System.out.println("New rater:\t\t\t\t\t" + raterID + " - creating new entry");
                    addRaterToRaters(raterID, item, value, ratersArrayList);
                    index++;
                }
            }
        }



        return ratersArrayList;

    }

    public void getRaterRatings(String rater_id, ArrayList<Rater> ratersArrayList){
        System.out.println();
        System.out.println("Retrieving specific rater data");
        for (Rater currentRater : ratersArrayList){
            if (currentRater.getID().equals(rater_id)) {
                System.out.println("Rater ID:\t\t\t\t\t" + currentRater.getID());
                System.out.println("Number of ratings:\t\t\t" + currentRater.numRatings());
            }
        }
    }

    public int getMovieRatings(String movie, ArrayList<Rater> raterArrayList){

        int totalRatings = 0;

        for (Rater currentRater : raterArrayList){
            if (currentRater.hasRating(movie)) {
                totalRatings++;
            }
        }

        System.out.println();
        System.out.println("Ratings for " + movie + ":\t\t" + totalRatings);

        return totalRatings;

    }

    public void getMaximumRatings(ArrayList<Rater> ratersArrayList){

        System.out.println();
        System.out.println("Retrieving maximum rater in ratersArrayList");

        int maxRatings = 0;
        String maxRater = "None";

        for (Rater currentRater : ratersArrayList){
            if (currentRater.numRatings() > maxRatings){
                maxRatings = currentRater.numRatings();
                maxRater = currentRater.getID();
            }
        }

        System.out.println("maxRater:\t\t\t\t\t" + maxRater);
        System.out.println("maxRatings:\t\t\t\t\t" + maxRatings);

    }

    public int getNumberOfDifferentMoviesRated(ArrayList<Rater> ratersArrayList){
        HashSet<String> moviesHs = new HashSet<String>();
        for (Rater currentRater : ratersArrayList) {
            if (!currentRater.getItemsRated().isEmpty()){
                for (String movie : currentRater.getItemsRated()) {
                    moviesHs.add(movie);
                }
            }
        }
        System.out.println("Total movies rated:\t\t\t" + moviesHs.size());
        return moviesHs.size();
    }

    public void testLoadRaters(){

        //ArrayList<Rater> ral = loadRaters("data/ratings_short.csv");
        ArrayList<Rater> ral = loadRaters("data/ratings.csv");

        getRaterRatings("193", ral);
        getMaximumRatings(ral);
        getMovieRatings("1798709", ral);
        System.out.println();
        getNumberOfDifferentMoviesRated(ral);
        System.out.println();
        System.out.println("Raters ArrayList Length:\t" + ral.size());


    }

    public static void main(String args[]){
        FirstRatings fr = new FirstRatings();
        fr.testLoadMovies();
        fr.testLoadRaters();
    }

}
