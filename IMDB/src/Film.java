import java.util.List;

public class Film extends Show{
    private int duration;
    public Film(String name, int releaseYear, String description, List<Category> categories, List<Review> reviews, List<Actor> actors, int duration) {
        super(name, releaseYear, description, categories, reviews, actors);
        this.duration = duration;
    }
}
