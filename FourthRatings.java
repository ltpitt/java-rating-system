import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FourthRatings {
    public FourthRatings() {
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
        for (String movieId : MovieDatabase.filterBy(new TrueFilter())) {
            double averageRating = getAverageByID(movieId, minimalRaters);
            if (averageRating != 0.0) {
                ratingArrayList.add(new Rating(movieId, averageRating));
            }
        }
        return ratingArrayList;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter criteriaFilter) {
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
        for (String aMovieList : MovieDatabase.filterBy(criteriaFilter)) {
            double averageRating = getAverageByID(aMovieList, minimalRaters);
            if (averageRating != 0.0) {
                ratingArrayList.add(new Rating(aMovieList, averageRating));
            }
        }
        return ratingArrayList;
    }

    private double getAverageByID(String movieId, int minimalRaters) {
        double totalRatings = 0.0;
        int numberOfRatings = 0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        for (Rater rater : myRaters) {
            if (rater.hasRating(movieId)) {
                numberOfRatings++;
                totalRatings += rater.getRating(movieId);
            }
        }
        if (numberOfRatings < minimalRaters) {
            return 0.0;
        }
        return totalRatings / (double) numberOfRatings;
    }

    private double dotProduct(Rater me, Rater rater) {
        double result = 0.0;
        for (String movieId : me.getItemsRated()) {
            if (rater.hasRating(movieId)) {
                result += (me.getRating(movieId) - 5) * (rater.getRating(movieId) - 5);
            }
        }
        return result;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater rater : RaterDatabase.getRaters()) {
            if (!rater.getID().equals(me.getID()) && dotProduct(rater, me) >= 0.0) {
                list.add(new Rating(rater.getID(), dotProduct(rater, me)));
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,
                                                       Filter filterCriteria) {
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
        ArrayList<Rating> similarRaterList = getSimilarities(id);

        for (String movieId : MovieDatabase.filterBy(filterCriteria)) {
            int numRatings = 0;
            double weightedTotal = 0.0;
            for (int index = 0; index < numSimilarRaters; index++) {
                Rating similarityRating = similarRaterList.get(index);
                double closeness = similarityRating.getValue();
                String raterId = similarityRating.getItem();
                Rater rater = RaterDatabase.getRater(raterId);
                if (rater.hasRating(movieId)) {
                    numRatings++;
                    double movieRating = rater.getRating(movieId);
                    weightedTotal += closeness * movieRating;
                }
            }
            if (numRatings >= minimalRaters) {
                ratingArrayList.add(new Rating(movieId, weightedTotal / numRatings));
            }
        }
        Collections.sort(ratingArrayList, Comparator.reverseOrder());
        return ratingArrayList;
    }

    public ArrayList<Rating> getRecommendations(String id, int numRaters, int minimalRaters){
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movieID: movies){
            double ratingSum = 0;
            int count = 0;
            for(int k=0; k< numRaters;k++){
                Rating r = list.get(k);
                String raterID = r.getItem();
                double weight = r.getValue();
                if(r.getValue() > 0){
                    Rater tempRater = RaterDatabase.getRater(raterID);
                    if(tempRater.hasRating(movieID)){
                        ratingSum += tempRater.getRating(movieID)*weight;
                        count++;
                    }
                    //use rater id and weight in r to update running totals
                }
            }
            if(count>=minimalRaters){
                ret.add(new Rating(movieID, ratingSum/count));
            }
            //add rating for movie ID to ret
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret; // sort first
    }

    public static void main(String[] args) {
        String shortMovieCsv = "ratedmovies_short.csv";
        String shortRatingsCsv = "ratings_do_product.csv";
        //String bigMovieCsv = "ratedmoviesfull.csv";
        //String bigRatingsCsv = "ratings.csv";

        MovieDatabase.initialize(shortMovieCsv);
        //MovieDatabase.initialize(bigMovieCsv);
        RaterDatabase.initialize(shortRatingsCsv);
        //RaterDatabase.initialize(bigRatingsCsv);

        Rater rater_id_2 = RaterDatabase.getRater("15");
        Rater rater_id_4 = RaterDatabase.getRater("20");

        FourthRatings fourthRatings = new FourthRatings();

        double result = fourthRatings.dotProduct(rater_id_2, rater_id_4);
        System.out.println("Dot product for raters 15 and 20: " + result);
        System.out.println(fourthRatings.getAverageByID("3285", 1));
//        ArrayList<Rating> similarities = fourthRatings.getSimilarities("2");
//        System.out.println("List of raters closest to ID 2 and their dot product:");
//        for (Rating rating : similarities) {
//            System.out.println(rating.getItem() + " " + rating.getValue());
//        }
    }
}