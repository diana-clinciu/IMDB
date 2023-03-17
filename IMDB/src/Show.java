import java.util.List;

abstract public class Show {
    protected String name;
    protected int releaseYear;
    protected String description;
    protected List<Category> categories;
    protected List<Review> reviews;
    protected List<Actor> actors;
    public Show(String name, int releaseYear, String description, List<Category> categories, List<Review> reviews, List<Actor> actors) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.description = description;
        this.categories = categories;
        this.reviews = reviews;
        this.actors = actors;
    }
}
