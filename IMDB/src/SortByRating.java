import java.util.Comparator;

public class SortByRating implements Comparator<Show> {
    @Override
    public int compare(Show a, Show b) {
        return Double.compare(a.getAveragaRating(), b.getAveragaRating());
    }
}