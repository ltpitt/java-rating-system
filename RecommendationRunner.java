
/**
 * Class generating a webpage containing a recommendation based on user input
 *
 * @author Davide Nastri
 * @version 13/09/2018
 */
import java.util.ArrayList;
public class RecommendationRunner implements Recommender {
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> toRet = new ArrayList<String>();
        for(int i = 0; i < 25; i ++) {
            toRet.add(movies.get(i));
        }
        return toRet;
    }

    public void printRecommendationsFor(String webRaterID) {

        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> movies = fr.getSimilarRatingsByFilter(webRaterID, 50, 2, new TrueFilter());
        if(movies.size() == 0) {
            System.out.println("<p>Sorry, there are no recommendations for you.</p>");
            System.exit(1);
        }
        System.out.println("<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
        System.out.println("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\" integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\" crossorigin=\"anonymous\"></script>");
        System.out.println("<table class=\"table table-hover\">");
        System.out.println("<thead><tr><th scope=\"col\">Rank</th><th>Poster</th><th>Movie Title</th></tr></thead>");
        for(int i = 0; i < 15; i ++) {
            System.out.println("<tbody><tr><td>"+(i+1)+"</td><td><img src=\"" + MovieDatabase.getPoster((movies.get(i).getItem())) + "\" /></td><td>" + MovieDatabase.getTitle(movies.get(i).getItem()) + "</td></tr></tbody>");
        }
        System.out.println("</table>");
    }
    
}