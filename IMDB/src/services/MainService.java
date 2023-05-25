package services;

import models.*;
import utils.SortByRating;

import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MainService {
    private List<User> users = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();
    private List<Actor> actors = new ArrayList<>();
    private List<Show> shows = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();

    ActorService actorService = ActorService.getInstance();
    CategoryService categoryService = CategoryService.getInstance();
    ReviewService reviewService = ReviewService.getInstance();
    ShowService showService = ShowService.getInstance();
    FilmService filmService = FilmService.getInstance();

    SeriesService seriesService = SeriesService.getInstance();

    public MainService() {
    }

    public void createUser(Scanner in) {

        User user = new User();
        user.read(in);
        this.users.add(user);
        System.out.println("User added");
    }
    public void addFirstAdmin() {
        Admin a = new Admin(1,"Admin","Nr1","admin1@gmail.com","admin1");
        this.admins.add(a);
    }
    public void createAdmin(Scanner in) {

        Admin admin = new Admin();
        admin.read(in);
        this.admins.add(admin);
        System.out.println("Admin added");
    }

    public void createFilm(Scanner in) {
        Film newFilm = new Film();
        newFilm.read(in);
        this.shows.add(newFilm);
        System.out.println("Film added to the website");
    }

    public void createSeries(Scanner in) {

        Series newSeries = new Series();
        newSeries.read(in);
        this.shows.add(newSeries);
        System.out.println("Series added to the website");
    }

    public void createActor(Scanner in) {
       /* Actor actor = new Actor();
        actor.read(in);
        this.actors.add(actor);
        System.out.println("Actor added");
        */
        this.actors= actorService.getAllActors();
        System.out.println("Enter what operation you want to complete:");
        System.out.println("+---------------------------------------------+");
        System.out.println("|   a. Show all actors                        |");
        System.out.println("|   b. Add an actor                           |");
        System.out.println("|   c. Add an award to an actor               |");
        System.out.println("|   d. Update an actor                        |");
        System.out.println("|   e. Delete an actor                        |");
        System.out.println("+---------------------------------------------+");
        String option = in.nextLine();
        try{
            switch (option){
                case "a":
                    for (Actor a : actors) {
                        a.print();
                        System.out.println("+---------------------------------------------+");
                    }
                    System.out.println("\n");
                    break;
                case "b":
                    Actor actor = new Actor();
                    actor.read(in);
                    boolean created = actorService.createActor(actor);
                    if (created) {
                        System.out.println("Actor added successfully");
                    } else {
                        System.out.println("Failed to add actor");
                    }
                    break;
                case "c":
                    System.out.println("Enter the ID of the actor you want to add an award to: ");
                    int actorId = Integer.parseInt(in.nextLine());
                    System.out.println("Enter the ID of the award: ");
                    int awardId = Integer.parseInt(in.nextLine());
                    System.out.println("Enter the name of the award: ");
                    String name = in.nextLine();
                    boolean updated = actorService.addAwardToActor(awardId,actorId,name);
                    if (updated) {
                        System.out.println("Award added successfully");
                    } else {
                        System.out.println("Failed to add award");
                    }
                    this.actors = actorService.getAllActors();
                    break;
                case "d":
                    this.actors = actorService.getAllActors();
                    System.out.println("Enter the ID of the actor you want to update: ");
                    actorId = Integer.parseInt(in.nextLine());
                    Actor actorToUpdate = findById(actorId, actors);
                    System.out.println("Enter the new lastname for the actor: ");
                    String newName = in.nextLine();
                    actorToUpdate.setLastName(newName);
                    System.out.println("Enter the new firstname for the actor: ");
                    newName = in.nextLine();
                    actorToUpdate.setFirstName(newName);
                    System.out.println("Enter the new age for the actor: ");
                    int age = Integer.parseInt(in.nextLine());
                    actorToUpdate.setAge(age);
                    updated = actorService.updateActor(actorToUpdate);
                    if (updated) {
                        System.out.println("Actor updated successfully");
                    } else {
                        System.out.println("Failed to update actor");
                    }
                    break;
                case "e":
                    System.out.println("Enter the ID of the actor you want to delete: ");
                    actorId = Integer.parseInt(in.nextLine());
                    boolean deleted = actorService.deleteActor(actorId);
                    if (deleted) {
                        System.out.println("Actor deleted successfully");
                    } else {
                        System.out.println("Failed to delete actor");
                    }
                    break;
                default:
                    System.out.println("Wrong command!");
                    break;
            }
        }catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void CRUDReview(Scanner in) {
        this.reviews = reviewService.getAllReviews();
        System.out.println("Enter what operation you want to complete:");
        System.out.println("+---------------------------------------------+");
        System.out.println("|   a. Show all reviews                       |");
        System.out.println("|   b. Create a review                        |");
        System.out.println("|   c. Update a review                        |");
        System.out.println("|   d. Delete a review                        |");
        System.out.println("+---------------------------------------------+");
        String option = in.nextLine();
        try{
            switch (option){
                case "a":
                    for (Review r : reviews) {
                        r.print();
                        System.out.println("+---------------------------------------------+");
                    }
                    System.out.println("\n");
                    break;
                case "b":
                    Review review = new Review();
                    review.read(in);
                    this.reviews.add(review);
                    boolean created = reviewService.createReview(review);
                    if (created) {
                        System.out.println("Review added successfully");
                    } else {
                        System.out.println("Failed to add review");
                    }
                    break;
                case "c":
                    this.reviews= reviewService.getAllReviews();
                    System.out.println("Enter the ID of the review you want to update: ");
                    int reviewId = Integer.parseInt(in.nextLine());
                    Review reviewToUpdate = findById(reviewId, reviews);
                    System.out.println("Enter the new grade for the review: ");
                    int newGrade = Integer.parseInt(in.nextLine());
                    reviewToUpdate.setGrade(newGrade);
                    System.out.println("Enter the new description for the review: ");
                    String newDescription = in.nextLine();
                    reviewToUpdate.setDescription(newDescription);
                    System.out.println("Enter the new user ID for the review: ");
                    int newUserId = Integer.parseInt(in.nextLine());
                    reviewToUpdate.setUserId(newUserId);
                    boolean updated = reviewService.updateReview(reviewToUpdate);
                    if (updated) {
                        System.out.println("Review updated successfully");
                    } else {
                        System.out.println("Failed to update review");
                    }
                    break;
                case "d":
                    System.out.println("Enter the ID of the review you want to delete: ");
                    reviewId = Integer.parseInt(in.nextLine());
                    boolean deleted = reviewService.deleteReview(reviewId);
                    if (deleted) {
                        System.out.println("Review deleted successfully");
                    } else {
                        System.out.println("Failed to delete review");
                    }
                    break;
                default:
                    System.out.println("Wrong command!");
                    break;
            }
        }catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    public void CRUDCategory(Scanner in) {
        this.categories = categoryService.getAllCategories();
        System.out.println("Enter what operation you want to complete:");
        System.out.println("+---------------------------------------------+");
        System.out.println("|   a. Show all categories                    |");
        System.out.println("|   b. Create a category                      |");
        System.out.println("|   c. Update a category                      |");
        System.out.println("|   d. Delete a category                      |");
        System.out.println("+---------------------------------------------+");
        String option = in.nextLine();
        try{
            switch (option){
                case "a":
                    for (Category c : categories) {
                        c.print();
                        System.out.println("+---------------------------------------------+");
                    }
                    System.out.println("\n");
                    break;
                case "b":
                    Category category = new Category();
                    category.read(in);
                    this.categories.add(category);
                    boolean created = categoryService.createCategory(category);
                    if (created) {
                        System.out.println("Category added successfully");
                    } else {
                        System.out.println("Failed to add category");
                    }
                    break;
                case "c":
                    this.categories= categoryService.getAllCategories();
                    System.out.println("Enter the ID of the category you want to update: ");
                    int categoryId = Integer.parseInt(in.nextLine());
                    Category categoryToUpdate = findById(categoryId, categories);
                    System.out.println("Enter the new name for the category: ");
                    String newName = in.nextLine();
                    categoryToUpdate.setName(newName);
                    boolean updated = categoryService.updateCategory(categoryToUpdate);
                    if (updated) {
                        System.out.println("Category updated successfully");
                    } else {
                        System.out.println("Failed to update category");
                    }
                    break;
                case "d":
                    System.out.println("Enter the ID of the category you want to delete: ");
                    categoryId = Integer.parseInt(in.nextLine());
                    boolean deleted = categoryService.deleteCategory(categoryId);
                    if (deleted) {
                        System.out.println("Category deleted successfully");
                    } else {
                        System.out.println("Failed to delete category");
                    }
                    break;
                default:
                    System.out.println("Wrong command!");
                    break;
            }
        }catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    public void CRUDFilm(Scanner in) {
        this.shows = showService.getAllShows();
        System.out.println("Enter what operation you want to complete:");
        System.out.println("+---------------------------------------------+");
        System.out.println("|   a. Show all shows                         |");
        System.out.println("|   b. Create a film                          |");
        System.out.println("|   c. Update a film                          |");
        System.out.println("|   d. Delete a film                          |");
        System.out.println("+---------------------------------------------+");
        String option = in.nextLine();
        try{
            switch (option){
                case "a":
                    for (Show c : shows) {
                        c.print();
                        System.out.println("+---------------------------------------------+");
                    }
                    System.out.println("\n");
                    break;
                case "b":
                    Film film = new Film();
                    film.read(in);
                    this.shows.add(film);
                    boolean created = filmService.createFilm(film);
                    if (created) {
                        System.out.println("Film added successfully");
                    } else {
                        System.out.println("Failed to add film");
                    }
                    break;
                case "c":
                    this.shows= showService.getAllShows();
                    System.out.println("Enter the ID of the film you want to update: ");
                    int showId = Integer.parseInt(in.nextLine());
                    Film showToUpdate = (Film) findById(showId, shows);
                    System.out.println("Enter the new name for the film: ");
                    String newName = in.nextLine();
                    showToUpdate.setName(newName);
                    System.out.println("Enter the new release year for the film: ");
                    int newReleaseYear = Integer.parseInt(in.nextLine());
                    showToUpdate.setReleaseYear(newReleaseYear);
                    System.out.println("Enter the new description for the film: ");
                    String newDescription = in.nextLine();
                    showToUpdate.setDescription(newDescription);
                    System.out.println("Enter the new duration for the film: ");
                    int newDuration = Integer.parseInt(in.nextLine());
                    showToUpdate.setDuration(newDuration);
                    boolean updated = filmService.updateFilm(showToUpdate);
                    if (updated) {
                        System.out.println("Film updated successfully");
                    } else {
                        System.out.println("Failed to update film");
                    }
                    break;
                case "d":
                    System.out.println("Enter the ID of the film you want to delete: ");
                    showId = Integer.parseInt(in.nextLine());
                    boolean deleted = showService.deleteShow(showId);
                    if (deleted) {
                        System.out.println("Film deleted successfully");
                    } else {
                        System.out.println("Failed to delete film");
                    }
                    break;
                default:
                    System.out.println("Wrong command!");
                    break;
            }
        }catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void CRUDSeries(Scanner in) {
        this.shows = showService.getAllShows();
        System.out.println("Enter what operation you want to complete:");
        System.out.println("+---------------------------------------------+");
        System.out.println("|   a. Show all shows                         |");
        System.out.println("|   b. Create a series                        |");
        System.out.println("|   c. Update a series                        |");
        System.out.println("|   d. Delete a series                        |");
        System.out.println("+---------------------------------------------+");
        String option = in.nextLine();
        try{
            switch (option){
                case "a":
                    for (Show c : shows) {
                        c.print();
                        System.out.println("+---------------------------------------------+");
                    }
                    System.out.println("\n");
                    break;
                case "b":
                    Series series = new Series();
                    series.read(in);
                    this.shows.add(series);
                    boolean created = seriesService.createSeries(series);
                    if (created) {
                        System.out.println("Series added successfully");
                    } else {
                        System.out.println("Failed to add series");
                    }
                    break;
                case "c":
                    this.shows= showService.getAllShows();
                    System.out.println("Enter the ID of the series you want to update: ");
                    int showId = Integer.parseInt(in.nextLine());
                    Series showToUpdate = (Series) findById(showId, shows);
                    System.out.println("Enter the new name for the series: ");
                    String newName = in.nextLine();
                    showToUpdate.setName(newName);
                    System.out.println("Enter the new release year for the series: ");
                    int newReleaseYear = Integer.parseInt(in.nextLine());
                    showToUpdate.setReleaseYear(newReleaseYear);
                    System.out.println("Enter the new description for the series: ");
                    String newDescription = in.nextLine();
                    showToUpdate.setDescription(newDescription);
                    System.out.println("Enter the new number of episodes for the series: ");
                    int episodesNr = Integer.parseInt(in.nextLine());
                    showToUpdate.setEpisodesNr(episodesNr);
                    System.out.println("Enter the new duration of an episode for the series: ");
                    int newEpisodeDuration = Integer.parseInt(in.nextLine());
                    showToUpdate.setEpisodeDuration(newEpisodeDuration);
                    boolean updated = seriesService.updateSeries(showToUpdate);
                    if (updated) {
                        System.out.println("Series updated successfully");
                    } else {
                        System.out.println("Failed to update series");
                    }
                    break;
                case "d":
                    System.out.println("Enter the ID of the series you want to delete: ");
                    showId = Integer.parseInt(in.nextLine());
                    boolean deleted = showService.deleteShow(showId);
                    if (deleted) {
                        System.out.println("Series deleted successfully");
                    } else {
                        System.out.println("Failed to delete series");
                    }
                    break;
                default:
                    System.out.println("Wrong command!");
                    break;
            }
        }catch (Exception e) {
            System.out.println(e.toString());
        }
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
    public int connect(Scanner in){
        int success = 0;
        System.out.println("| Log in options:                             |");
        System.out.println("|   a. Log in as a user                       |");
        System.out.println("|   b. Log in as an admin                     |");
        String option = in.nextLine();
        switch (option) {
            case "a":
                System.out.println("Enter your id: ");
                int Id = Integer.parseInt(in.nextLine());
                try {
                    User user = this.findById(Id, users);
                    System.out.println("Logged in successfully!");
                    success = 1;
                } catch (Exception e) {
                    System.out.println("Given id was not found! Try again or sign up to IMDB!");
                }
                break;
            case "b":
                System.out.println("Enter your id: ");
                Id = Integer.parseInt(in.nextLine());
                try {
                    Admin admin = this.findById(Id, admins);
                    System.out.println("Logged in successfully!");
                    success = 2;
                } catch (Exception e) {
                    System.out.println("Given id was not found! ");
                }
                break;
            default:
                System.out.println("Wrong command!");
                break;
        }
        return success;
    }
    public void printAllShows() {
        if (shows.size() == 0 )
            System.out.println("Sorry, no shows to list yet :(");
        for (Show s : shows) {
            s.print(); // poly
            System.out.println("---------------------------------------");
        }
    }

    private <T extends Entity> T findById(int id, List<T> array) throws Exception {
        for (T e : array) {
            if (e.getId() == id)
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

    public void addCategoryToShow(Scanner scanner) {
        this.categories = categoryService.getAllCategories();
        this.shows = showService.getAllShows();
        try {
            System.out.println("Category id: ");
            int categoryId = Integer.parseInt(scanner.nextLine());
            System.out.println("Show id: ");
            int showId = Integer.parseInt(scanner.nextLine());
            Category category = this.findById(categoryId, categories);
            Show show = this.findById(showId, shows);
            showService.addCategory(show, category);
            show.addCategory(category);

            System.out.println("Category added successfully");
            for (Show s : shows) {
                System.out.println(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addReviewToShow(Scanner in) {
        System.out.println("Show id: ");
        int id = Integer.parseInt(in.nextLine());
        shows = showService.getAllShows();
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

    public void addActorToShow(Scanner in) {
        this.actors = actorService.getAllActors();
        this.shows = showService.getAllShows();
        System.out.println("Show id: ");
        int showId = Integer.parseInt(in.nextLine());
        System.out.println("Actor id: ");
        int actorId = Integer.parseInt(in.nextLine());
        try {
            Show show = this.findById(showId, shows);
            Actor actor = this.findById(actorId, actors);
            show.addActor(actor);
            showService.addActor(show,actor);
            System.out.println(show);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostAwardedActors(Scanner in) {
        System.out.println("Give the number actors for the top: ");
        this.actors= actorService.getAllActors();
        int topNumber = Integer.parseInt(in.nextLine());
        actors.sort(Comparator.comparingInt(actor -> actor.getAwards().size()));
        System.out.println("The most awarded " + topNumber + " actors are:");
        if (topNumber > actors.size())
            topNumber = actors.size();
        for (int i = actors.size() - 1; i >= actors.size() - topNumber; i--) {
            Actor a = actors.get(i);
            a.print();
            System.out.println("- - - - - - - - - - - - - - - ");
        }
    }

    public void calculateShowRating(Scanner in) {
        System.out.println("Give the show id: ");
        int id = Integer.parseInt(in.nextLine());
        try {
            Show show = this.findById(id, shows);
            System.out.println("The show " + show.getShowName() + " has a rating of " + show.getAveragaRating());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sortShows() {
        shows.sort(new SortByRating().reversed());
        int i = 0;
        for (Show s : shows) {
            System.out.println((++i) + ". " + s.getName() + " rating: " + s.getAveragaRating());
        }
    }

}
