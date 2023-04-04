import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract public class Show extends Entity {
    protected String name;
    protected int releaseYear;
    protected String description;
    protected List<Category> categories;
    protected List<Review> reviews; // !!compunere!!
    protected List<Actor> actors;

    public Show() {
        this.categories = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.actors = new ArrayList<>();
    }

    public Show(String name) {
        this.name = name;
    }

    public Show(String name, int releaseYear, String description) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.description = description;
    }

    public Show(int id, String name, int releaseYear, String description, List<Category> categories,
                List<Review> reviews, List<Actor> actors) {
        super(id);
        this.name = name;
        this.releaseYear = releaseYear;
        this.description = description;
        this.categories = categories;
        // composition
        for (Review r : reviews) {
            this.reviews.add(new Review(r));    // cream interne copii ale review-urilor
        }
        this.actors = actors;
    }

    public String getShowName() {
        return name;
    }

    public void read(Scanner in) {
        super.read(in);
        System.out.println("Name: ");
        this.name = in.nextLine();
        System.out.println("Release year: ");
        String rYear = in.nextLine();
        this.releaseYear = Integer.parseInt(rYear);
        System.out.println("Description: ");
        this.description = in.nextLine();
    }

    public void print(){
        System.out.println("Id " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Release year: " + this.releaseYear);
        System.out.println("Description: " + this.description);
        System.out.println("Categories: ");
        for(Category c: this.categories){
            c.print();
            System.out.println("- - - - - - - - - - - - - - - ");
        }
        System.out.println("Reviews: ");
        for(Review r: this.reviews){
            r.print();
            System.out.println("- - - - - - - - - - - - - - - ");
        }
        System.out.println("Actors: ");
        for(Actor a: this.actors){
            a.print();
            System.out.println("- - - - - - - - - - - - - - - ");
        }
    }

    @Override
    public String toString() {
        return "Show{" +
                super.toString()+
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", description='" + description + '\'' +
                ", categories=" + categories +
                ", reviews=" + reviews +
                ", actors=" + actors +
                '}';
    }

    public void addCategory(Category category) {
        this.categories.add(category); // Aggregation
    }
    public void addReview(Review review) {
        this.reviews.add(new Review(review));
    }

    public void addActor(Actor actor){
        this.actors.add(actor); // agregare
    }

}
