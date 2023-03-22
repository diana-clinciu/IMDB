import java.util.Scanner;

public class Main {

    private static void printMenu() {
        System.out.println("+---------------------------------------------+");
        System.out.println("|                     IMDB                    |");
        System.out.println("+---------------------------------------------+");
        System.out.println("|   a. Create a new user                      |");
        System.out.println("|   b. Create an actor *                      |");
        System.out.println("|   c. Add a new film to the website          |");
        System.out.println("|   d. Add a new series to the website        |");
        System.out.println("|   e. Delete a film from the website         |");
        System.out.println("|   f. Delete a series from the website       |");
        System.out.println("|   g. Add show to watchlist   *              |");
        System.out.println("|   h. Show movies                            |");
        System.out.println("|   i. Show series                            |");
        System.out.println("|   q. Quit                                   |");
        System.out.println("+---------------------------------------------+");
        System.out.println("Introduce the command letter:");
    }

    public static void main(String[] args) {
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
                        mainService.createFilm(in);
                        break;
                    case "d":
                        mainService.createSeries(in);
                        break;
                    case "e":
                        mainService.deleteFilm(in);
                        break;
                    case "f":
                        mainService.deleteSeries(in);
                        break;
                    case "g":
                        mainService.addShowToWatchList(in);
                        break;
                    case "h":
                        mainService.showFilms();
                        break;
                    case "i":
                        mainService.showSeries();
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