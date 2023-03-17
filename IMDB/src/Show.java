import java.util.List;
import java.util.Scanner;

abstract public class Show {
    protected String name;
    protected int releaseYear;
    protected String description;
    protected List<Category> categories;
    protected List<Review> reviews;
    protected List<Actor> actors;

    public Show() {
    }

    public Show(String name) {
        this.name = name;
    }

    public Show(String name, int releaseYear, String description) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.description = description;
    }

    public Show(String name, int releaseYear, String description, List<Category> categories, List<Review> reviews,
                List<Actor> actors) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.description = description;
        this.categories = categories;
        this.reviews = reviews;
        this.actors = actors;
    }

    public String getShowName() {
        return name;
    }

    public void read(Scanner in)  {
        System.out.println("Name: ");
        this.name = in.nextLine();
        System.out.println("Release year: ");
        String rYear=in.nextLine();
        this.releaseYear= Integer.parseInt(rYear);
        System.out.println("Description: ");
        this.description = in.nextLine();
    }
}
