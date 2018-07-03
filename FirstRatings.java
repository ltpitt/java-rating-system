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
            System.out.println(currentRow);
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
        //ArrayList<Movie> mal = loadMovies("data/ratedmoviesfull.csv");
        ArrayList<Movie> mal = loadMovies("data/ratedmovies_short.csv");
        int counter = 0;
        String searchedGenre = "comedy";
        int searchedMinutes = 150;
        ArrayList<Movie> longMovies = new ArrayList<Movie>();

        for (Movie currentMovie : mal) {
            List<String> genresList = Arrays.asList(currentMovie.getGenres().split(","));
            for (String genre : genresList) {
                if (genre.trim().toLowerCase().equals(searchedGenre.toLowerCase())) {
                    counter ++;
                }
            }
            if (currentMovie.getMinutes() > searchedMinutes) {
                longMovies.add(currentMovie);
            }
            //System.out.println(currentMovie);
        }
        System.out.println("Movies ArrayList Length: " + mal.size());
        System.out.println("Long Movies ArrayList Length: " + longMovies.size());
        System.out.println("Total movies with genre " + searchedGenre + ": " + counter);

    }

    public static void main(String args[]){
        FirstRatings fr = new FirstRatings();
        fr.testLoadMovies();
    }

}
