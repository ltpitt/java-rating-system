import java.util.*;

public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate (){
        ArrayList<String> moviesToRate = new ArrayList<String>();
        int moviesCount = MovieDatabase.size();

        String[] movies =  {"2637276",
                "2097298",
                "3682448",
                "3715320",
                "4229236",
                "3248600",
                "4142022",
                "1617661",
                "1951266",
                "1243974",
                "2381249",
                "3503840",
                "0470752",
                "2561572",
                "2273657"};


        for(int i=0;i<15;i++){

            moviesToRate.add(movies[i]);
        }
        return moviesToRate;
    }

    public void printRecommendationsFor (String webRaterID){
        FourthRatings fr = new FourthRatings();
        int numRaters = 20;
        int minRaters = 5;
        ArrayList<Rating> rec = fr.getRecommendations(webRaterID, numRaters, minRaters);
        printCSS();
        if(rec.size() == 0){
            printError();
        }
        else{
            printUpperPart();
            int i=0;
            for(Rating r: rec){
                i++;
                if((i+1)%2 == 0)
                    System.out.println("<tr class=\"even_rows\"><td>" + i + "</td>");
                else
                    System.out.println("<tr class=\"odd_rows\"><td>" + i + "</td>");
                String URL = MovieDatabase.getPoster(r.getItem());
                String title = MovieDatabase.getTitle(r.getItem());
                String director = MovieDatabase.getDirector(r.getItem());
                String country = MovieDatabase.getCountry(r.getItem());
                int year = MovieDatabase.getYear(r.getItem());
                String genre = MovieDatabase.getGenres(r.getItem());
                int minutes = MovieDatabase.getMinutes(r.getItem());
                System.out.println("<td><table><tr><td class = \"pic\">");
                if(URL.length()>5)
                    System.out.println("<img src = \""+URL+"\" target=_blank></td>");
                    /*
                 System.out.println("<td><h3>"+ title+"</h3>");
                 System.out.println("<b>by "+ director+"</b><br>");
                 System.out.println(country+", "+ year+"<br>");
                 System.out.println(genre+"<br>");
                 System.out.println(minutes+" minutes</td></tr></table></td></tr>");
                 */
                System.out.println("<td><h3>"+ title+"</h3>");
                System.out.println("<b>by "+ genre+"</b><br>");
                System.out.println(year+"<br>");
                System.out.println(country+"<br>");
                System.out.println(minutes+" minutes</td></tr></table></td></tr>");
                if(i>30) break;
            }
            printLowerPart();
        }
    }

    private void printUpperPart(){
        System.out.println("<link href=\"https://fonts.googleapis.com/css?family=Syncopate\" rel=\"stylesheet\"><link href=\"https://fonts.googleapis.com/css?family=Roboto|Syncopate\" rel=\"stylesheet\"><div id=\"header\"><h2>Recommended movies for you:</h2></div><table class=\"outside_table\"><tr  class=\"table-header\"><th>&nbsp</th><th class=\"movie_title\">Title</th></tr>");
    }

    private void printCSS(){
        System.out.println("<style>* {margin: 0;padding: 0;}img{height: 150px;margin-right:10px;}#header{font-family: 'Syncopate', sans-serif;background-color: #F49F58;margin-top: 0;height: 100px;}h2{padding-left: 15px;padding-top: 40px;color: #FFFFFF;}h3{color: #B35D17;}body{margin-top: 0;font-family: 'Roboto', sans-serif;}th{text-align: left;font-family: 'Roboto', sans-serif;padding-top:15px;padding-bottom: 7px;}td{padding-top: 10px;padding-right: 10px;padding-left: 10px;padding-bottom: 5px;}tr{padding-bottom: 10px;}.table-header{background-color: #FFB97F;}.odd_rows{background-color: #FFE4CC;}.even_rows{background-color: #FFFFFF;}.outside_table{width: 100%;border-collapse: collapse;}.movie_title{width = 40%;}</style>");
    }

    private void printLowerPart(){
        System.out.println("</table>");
    }

    private void printError(){
        System.out.println("<style>#header{height: 150px;}</style>");
        System.out.println("<link href=\"https://fonts.googleapis.com/css?family=Syncopate\" rel=\"stylesheet\"><link href=\"https://fonts.googleapis.com/css?family=Roboto|Syncopate\" rel=\"stylesheet\"><div id=\"header\"><h2>Unfortunately<br> there's not enough ratings similar to yours<br>to make a personalized recommendation.</h2></div>");

    }
}