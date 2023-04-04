import java.util.Scanner;

public class Main {
    private static void printMenu() {
        System.out.println("+---------------------------------------------+");
        System.out.println("|                     IMDB                    |");
        System.out.println("+---------------------------------------------+");
        System.out.println("|   a. Create a new user                      |");
        System.out.println("|   b. Create an actor *                      |");
        System.out.println("|   c. Create a category *                    |");
        System.out.println("|   d. Create a film                          |");
        System.out.println("|   e. Create a series                        |");
        System.out.println("|   f. Show shows :) _                        |");
        System.out.println("|   g. Delete a show                          |");
        System.out.println("|   h. Add show to watchlist   *              |");
        System.out.println("|   i. Add review to show      *              |");
        System.out.println("|   j. Add actor to show      *               |");
        System.out.println("|   k. Add category to show      *            |");
        System.out.println("|   l. Get show rating                        |");
        System.out.println("|   m. Discover most awarded actors           |");
        System.out.println("|   q. Quit                                   |");
        System.out.println("+---------------------------------------------+");
        System.out.println("Introduce the command letter:");
    }

    public static void main(String[] args) {

        Object obj = new User();
        Class a = obj.getClass();
        System.out.println(a);

        MainService mainService = new MainService();
        Scanner in = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            Main.printMenu();
            String cmd = in.nextLine();
            try {
                switch (cmd) {
                    case "a":
                        mainService.createUser(in);
                        break;
                    case "b":
                        mainService.createActor(in);
                        break;
                    case "c":
                        mainService.createCategory(in);
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
                        mainService.addShowToWatchList(in);
                        break;
                    case "i":
                        mainService.addReviewToShow(in);
                        break;
                    case "j":
                        mainService.addActorToShow(in);
                        break;
                    case "k":
                        mainService.addCategoryToShow(in);
                        break;
                    case "l":
                        mainService.calculateShowRating(in);
                        break;
                    case "m":
                        mainService.mostAwardedActors(in);
                        break;
                    case "q":
                        quit = true;
                        break;
                    default:
                        System.out.println("Wrong command!");
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }


}