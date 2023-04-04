import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MainService {
    private List<User> users = new ArrayList<>();
    private List<Actor> actors = new ArrayList<>();
    private List<Show> shows = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    public MainService() {
    }

    public void createUser(Scanner in){

        User user = new User();
        user.read(in);
        this.users.add(user);
        System.out.println("User added");
    }

    public void createFilm(Scanner in){
        Film newFilm = new Film();
        newFilm.read(in);
        this.shows.add(newFilm);
        System.out.println("Film added to the website");
    }

    public void createSeries(Scanner in){

        Series newSeries = new Series();
        newSeries.read(in);
        this.shows.add(newSeries);
        System.out.println("Series added to the website");
    }

    public void createActor(Scanner in){
        Actor actor = new Actor();
        actor.read(in);
        this.actors.add(actor);
        System.out.println("Actor added");
        for (Actor a : actors) {
            System.out.println(a);
        }
    }

    public void createCategory(Scanner in){
        Category category = new Category();
        category.read(in);
        this.categories.add(category);
        System.out.println("Category added");
    }
    public void deleteShow(Scanner in) {
        System.out.println("Enter the name of the show you want to delete:");
        String nameToDelete = in.nextLine();
        boolean found = false;
        for (Iterator<Show> iterator = shows.iterator(); iterator.hasNext(); ) {
            Show show = iterator.next();
            if (show.getShowName().equals(nameToDelete)) {
                iterator.remove();
                found = true;
            }
        }
        if (found) {
            System.out.println("The show was successfully deleted.");
        } else {
            System.out.println("The show was not found in the list.");
        }
    }

    public void printAllShows(){
        for(Show s: shows){
            s.print(); // poly
            System.out.println("---------------------------------------");
        }
    }

    private <T extends Entity> T findById(int id, List<T> array) throws Exception {
        for (T e : array) {
            if (e.id == id)
                return e;
        }
        throw new Exception("Entity not found!");
    }
    public void addShowToWatchList(Scanner scanner) {
        try {
            System.out.println("User id: ");
            int userId = Integer.parseInt(scanner.nextLine());
            System.out.println("Show id: ");
            int showId = Integer.parseInt(scanner.nextLine());
            User user = this.findById(userId, users);
            Show show = this.findById(showId, shows);
            user.addShow(show);
            System.out.println("Show added successfully");
            for (User u : users) {
                System.out.println(u);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addCategoryToShow(Scanner scanner){
        try {
            System.out.println("Category id: ");
            int categoryId = Integer.parseInt(scanner.nextLine());
            System.out.println("Show id: ");
            int showId = Integer.parseInt(scanner.nextLine());
            Category category = this.findById(categoryId, categories);
            Show show = this.findById(showId, shows);
            show.addCategory(category);
            System.out.println("Category added successfully");
            for (Show s : shows) {
                System.out.println(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addReviewToShow(Scanner in){
        System.out.println("Show id: ");
        int id = Integer.parseInt(in.nextLine());
        try {
            Show show = this.findById(id, shows);
            Review review = new Review();
            review.read(in);
            show.addReview(review);
            System.out.println("Review added successfully to show " + show.getShowName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void addActorToShow(Scanner in){
        System.out.println("Show id: ");
        int showId = Integer.parseInt(in.nextLine());
        System.out.println("Actor id: ");
        int actorId = Integer.parseInt(in.nextLine());
        try {
            Show show = this.findById(showId, shows);
            Actor actor = this.findById(actorId, actors);
            show.addActor(actor);
            System.out.println(show);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void mostAwardedActors(Scanner in){
        System.out.println("Give the number actors for the top: ");
        int topNumber = Integer.parseInt(in.nextLine());
        actors.sort(Comparator.comparingInt(actor -> actor.getAwards().size()));
        System.out.println("The most awarded" + topNumber + " actors are:");
        if(topNumber > actors.size())
            topNumber = actors.size();
        for (int i = actors.size() - 1; i >= actors.size() - topNumber; i--) {
           Actor a = actors.get(i);
           a.print();
           System.out.println("- - - - - - - - - - - - - - - ");
        }
    }
    public void calculateShowRating(Scanner in){
        System.out.println("Give the show id: ");
        int id = Integer.parseInt(in.nextLine());
        try {
            Show show = this.findById(id, shows);
            double rating = 0;
            int reviewNumber = show.reviews.size();
            for(Review r: show.reviews){
                rating = rating + r.getGrade();
            }
            rating = rating / reviewNumber;
            System.out.println("The show " + show.getShowName() + " has a rating of " + rating);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
