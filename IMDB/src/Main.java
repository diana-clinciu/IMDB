import services.MainService;


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
        System.out.println("|   g. Log out                                |");
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
        System.out.println("|   d. Create a film                          |");
        System.out.println("|   e. Create a series                        |");
        System.out.println("|   f. View shows                             |");
        System.out.println("|   g. Delete a show                          |");
        System.out.println("|   h. Add actor to show                      |");
        System.out.println("|   i. Add category to show                   |");
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
        Scanner in = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            Main.printConnect();
            mainService.addFirstAdmin();
            String cmd = in.nextLine();
            try{
                switch (cmd){
                    case "a":
                        mainService.createUser(in);
                        break;
                    case "b":
                        int success = 0;
                        success = mainService.connect(in);
                        while(success > 0)
                         {
                            switch (success){
                                case 1:
                                    Main.printMenuUser();
                                    cmd = in.nextLine();
                                    switch (cmd) {
                                            case "a":
                                                mainService.printAllShows();
                                                break;
                                            case "b":
                                                mainService.addShowToWatchList(in);
                                                break;
                                            case "c":
                                                mainService.addReviewToShow(in);
                                                break;
                                            case "d":
                                                mainService.calculateShowRating(in);
                                                break;
                                            case "e":
                                                mainService.mostAwardedActors(in);
                                                break;
                                            case "f":
                                                mainService.sortShows();
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
                                            break;
                                        case "b":
                                            mainService.createActor(in);
                                            break;
                                        case "c":
                                            mainService.CRUDCategory(in);
                                            break;
                                        case "d":
                                            mainService.createFilm(in);
                                            break;
                                        case "e":
                                            mainService.createSeries(in);
                                            break;
                                        case "f":
                                            mainService.printAllShows();
                                            break;
                                        case "g":
                                            mainService.deleteShow(in);
                                            break;
                                        case "h":
                                            mainService.addActorToShow(in);
                                            break;
                                        case "i":
                                            mainService.addCategoryToShow(in);
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

    }


}