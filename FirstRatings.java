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
        }
        return moviesArrayList;
    }

    public void testLoadMovies(){
        this.loadMovies("data/ratedmovies_short.csv");
    }

    public static void main(String args[]){
        FirstRatings fr = new FirstRatings();
        fr.testLoadMovies();
    }

}
