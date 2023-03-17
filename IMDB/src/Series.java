import java.util.List;

public class Series extends Show{
    private int episodesNr;
    private int episodeDuration; //in minute

    public Series(String name, int releaseYear, String description, List<Category> categories, List<Review> reviews,
                  List<Actor> actors, int episodesNr, int episodeDuration) {
        super(name, releaseYear, description, categories, reviews, actors);
        this.episodesNr = episodesNr;
        this.episodeDuration = episodeDuration;
    }
}
