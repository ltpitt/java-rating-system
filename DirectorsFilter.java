public class DirectorsFilter implements Filter {

    String[] myDirectors;

    public DirectorsFilter(String directors) {
        myDirectors = directors.split(",");
    }

    @Override
    public boolean satisfies(String id) {
        String movieDirectors = MovieDatabase.getDirector(id);
        for (String director : myDirectors) {
            if (MovieDatabase.getDirector(id).contains(director)){
                return true;
            }
        }
        return false;
    }

}
