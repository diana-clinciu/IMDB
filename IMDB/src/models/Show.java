package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract public class Show extends Entity {
    protected String name;
    protected int releaseYear;
    protected String description;
    protected List<Category> categories = new ArrayList<>(); // agregare (weak association)
    protected List<Review> reviews = new ArrayList<>(); // compozitie (strong association)
    protected List<Actor> actors = new ArrayList<>(); // agregare (weak association)

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
    public Show(int id, String name, int releaseYear, String description) {
        super(id);
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

    @Override
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

    public void print() {
        System.out.println("Id " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Release year: " + this.releaseYear);
        System.out.println("Description: " + this.description);
        System.out.println("Categories: ");
        for (Category c : this.categories) {
            c.print();
            System.out.println("- - - - - - - - - - - - - - - ");
        }
        System.out.println("Reviews: ");
        for (Review r : this.reviews) {
            r.print();
            System.out.println("- - - - - - - - - - - - - - - ");
        }
        System.out.println("Actors: ");
        for (Actor a : this.actors) {
            a.print();
            System.out.println("- - - - - - - - - - - - - - - ");
        }
    }

    @Override
    public String toString() {
        return "Show{" +
                super.toString() +
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", description='" + description + '\'' +
                ", categories=" + categories +
                ", reviews=" + reviews +
                ", actors=" + actors +
                '}';
    }

    public void addCategory(Category category) {
        this.categories.add(category); // aggregation
    }

    public void addReview(Review review) {
        this.reviews.add(new Review(review));
    }

    public void addActor(Actor actor) {
        this.actors.add(actor); // agregare
    }

    public double getAveragaRating() {
        double rating = 0;
        int reviewNumber = this.reviews.size();
        for (Review r : this.reviews) {
            rating = rating + r.getGrade();
        }
        rating = rating / reviewNumber;
        return rating;
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
