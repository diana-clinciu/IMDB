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
        Actor actor = new Actor();
        actor.read(in);
        this.actors.add(actor);
        System.out.println("Actor added");
        for (Actor a : actors) {
            System.out.println(a);
        }
    }

    public void CRUDCategory(Scanner in) {
        CategoryService categoryService = CategoryService.getInstance();
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
                    this.categories = categoryService.getAllCategories();
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
                    System.out.println("Enter the ID of the category you want to update: ");
                    int categoryId = Integer.parseInt(in.nextLine());
                    Category categoryToUpdate = findById(categoryId, categoryService.getAllCategories());
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

    public void addReviewToShow(Scanner in) {
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

    public void addActorToShow(Scanner in) {
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

    public void mostAwardedActors(Scanner in) {
        System.out.println("Give the number actors for the top: ");
        int topNumber = Integer.parseInt(in.nextLine());
        actors.sort(Comparator.comparingInt(actor -> actor.getAwards().size()));
        System.out.println("The most awarded" + topNumber + " actors are:");
        if (topNumber > actors.size())
            topNumber = actors.size();
        for (int i = actors.size() - 1; i >= actors.size() - topNumber; i--) {
            Actor a = actors.get(i);
            a.print();
            System.out.println("- - - - - - - - - - - - - - - ");
        }
    }

    // am modificat si sper ca si nu stricat :)))
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
