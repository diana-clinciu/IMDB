import services.MainService;
import services.AuditService;

import java.util.Scanner;

public class Main {

    private static void printMenuUser() {
        System.out.println("+---------------------------------------------+");
        System.out.println("|                     IMDB                    |");
        System.out.println("+---------------------------------------------+");
        System.out.println("|   a. View shows                             |");
        System.out.println("|   b. Add show to watchlist                  |");
        System.out.println("|   c. Add review to show                     |");
        System.out.println("|   d. Get show rating                        |");
        System.out.println("|   e. Discover most awarded actors           |");
        System.out.println("|   f. Sort shows by rating                   |");
        System.out.println("|   q. Log out                                |");
        System.out.println("+---------------------------------------------+");
        System.out.println("Introduce the command letter:");
    }
    private static void printMenuAdmin() {
        System.out.println("+---------------------------------------------+");
        System.out.println("|                     IMDB                    |");
        System.out.println("+---------------------------------------------+");
        System.out.println("|   a. Add a new admin                        |");
        System.out.println("|   b. CRUD for an actor                      |");
        System.out.println("|   c. CRUD for a category                    |");
        System.out.println("|   d. CRUD for a review                      |");
        System.out.println("|   e. Create a film                          |");
        System.out.println("|   f. Create a series                        |");
        System.out.println("|   g. View shows                             |");
        System.out.println("|   h. Delete a show                          |");
        System.out.println("|   i. Add actor to show                      |");
        System.out.println("|   j. Add category to show                   |");
        System.out.println("|   q. Log out                                |");
        System.out.println("+---------------------------------------------+");
        System.out.println("Introduce the command letter:");
    }
    private static void printConnect() {
        System.out.println("+---------------------------------------------+");
        System.out.println("|                     IMDB                    |");
        System.out.println("+---------------------------------------------+");
        System.out.println("  Connect to IMDB:                             ");
        System.out.println("+---------------------------------------------+");
        System.out.println("|   a. Sign up to IMDB                        |");
        System.out.println("|   b. Log in to IMDB                         |");
        System.out.println("|   q. Quit                                   |");
        System.out.println("+---------------------------------------------+");
        System.out.println("Introduce the command letter:");
    }
    public static void main(String[] args) {

        MainService mainService = new MainService();
        AuditService auditService = new AuditService();
        Scanner in = new Scanner(System.in);
        boolean quit = false;
        try{
            while (!quit) {
                Main.printConnect();
                mainService.addFirstAdmin();
                String cmd = in.nextLine();
                try{
                    switch (cmd){
                        case "a":
                            mainService.createUser(in);
                            auditService.recordActionToCSV("User created");
                            break;
                        case "b":
                            int success = 0;
                            success = mainService.connect(in);
                            if(success == 1)
                                auditService.recordActionToCSV("User log in");
                            else
                                auditService.recordActionToCSV("Admin log in");
                            while(success > 0)
                            {
                                switch (success){
                                    case 1:
                                        Main.printMenuUser();
                                        cmd = in.nextLine();
                                        switch (cmd) {
                                            case "a":
                                                mainService.printAllShows();
                                                auditService.recordActionToCSV("Listed all shows");
                                                break;
                                            case "b":
                                                mainService.addShowToWatchList(in);
                                                auditService.recordActionToCSV("User added show to watchlist");
                                                break;
                                            case "c":
                                                mainService.addReviewToShow(in);
                                                auditService.recordActionToCSV("User added review to show");
                                                break;
                                            case "d":
                                                mainService.calculateShowRating(in);
                                                auditService.recordActionToCSV("Show rating inquiry");
                                                break;
                                            case "e":
                                                mainService.mostAwardedActors(in);
                                                auditService.recordActionToCSV("Most awarded actors inquiry");
                                                break;
                                            case "f":
                                                mainService.sortShows();
                                                auditService.recordActionToCSV("Sort shows request");
                                                break;
                                            case "q":
                                                success = 0;
                                                break;
                                            default:
                                                System.out.println("Wrong command!");
                                        }
                                        break;
                                    case 2:
                                        Main.printMenuAdmin();
                                        cmd = in.nextLine();
                                        switch (cmd) {
                                            case "a":
                                                mainService.createAdmin(in);
                                                auditService.recordActionToCSV("Admin added");
                                                break;
                                            case "b":
                                                int option;
                                                option = mainService.CRUDActor(in);
                                                if (option == 1)
                                                    auditService.recordActionToCSV("Listed all actors");
                                                else if(option == 2)
                                                    auditService.recordActionToCSV("Actor added");
                                                else if(option == 3)
                                                    auditService.recordActionToCSV("Award added to an actor");
                                                else if (option == 4)
                                                    auditService.recordActionToCSV("Actor updated");
                                                else if (option == 5)
                                                    auditService.recordActionToCSV("Actor deleted");
                                                break;
                                            case "c":
                                                option = mainService.CRUDCategory(in);
                                                if (option == 1)
                                                    auditService.recordActionToCSV("Listed all categories");
                                                else if(option == 2)
                                                    auditService.recordActionToCSV("Category added");
                                                else if (option == 3)
                                                    auditService.recordActionToCSV("Category updated");
                                                else if (option == 4)
                                                    auditService.recordActionToCSV("Category deleted");
                                                break;
                                            case "d":
                                                option = mainService.CRUDReview(in);
                                                if (option == 1)
                                                    auditService.recordActionToCSV("Listed all reviews");
                                                else if(option == 2)
                                                    auditService.recordActionToCSV("Review added");
                                                else if (option == 3)
                                                    auditService.recordActionToCSV("Review updated");
                                                else if (option == 4)
                                                    auditService.recordActionToCSV("Review deleted");
                                                break;
                                            case "e":
                                                mainService.createFilm(in);
                                                auditService.recordActionToCSV("Added film");
                                                break;
                                            case "f":
                                                mainService.createSeries(in);
                                                auditService.recordActionToCSV("Added series");
                                                break;
                                            case "g":
                                                mainService.printAllShows();
                                                auditService.recordActionToCSV("Listed all shows");
                                                break;
                                            case "h":
                                                mainService.deleteShow(in);
                                                auditService.recordActionToCSV("Removed show");
                                                break;
                                            case "i":
                                                mainService.addActorToShow(in);
                                                auditService.recordActionToCSV("Added actor in a show's cast");
                                                break;
                                            case "j":
                                                mainService.addCategoryToShow(in);
                                                auditService.recordActionToCSV("Added a category to a show");
                                                break;
                                            case "q":
                                                success = 0;
                                                break;
                                            default:
                                                System.out.println("Wrong command!");
                                        }
                                        break;
                                }
                            }
                            break;
                        case "q":
                            quit = true;
                            break;
                        default:
                            System.out.println("Wrong command!");
                            break;
                    }
                }catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }catch(Exception e) {
            System.out.println(e.toString());
        }finally {
            auditService.close(); //ma asigur ca fisierul audit e mereu inchis la final de program
        }


    }


}