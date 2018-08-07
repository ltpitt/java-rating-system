public class MinutesFilter implements Filter {

    int myMinMinutes;
    int myMaxMinutes;

    public MinutesFilter(int minMinutes, int maxMinutes) {
        myMinMinutes = minMinutes;
        myMaxMinutes = maxMinutes;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= myMinMinutes & MovieDatabase.getMinutes(id) <= myMaxMinutes;
    }
}
