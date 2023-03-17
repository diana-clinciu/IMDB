import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
        static List<String> availableOptions = Arrays.asList("Create a new user","Add a new film to the website",
                "Add a new series to the website","Delete a film from the website",
                "Delete a series from the website","Show movies","Show series","Leave IMDB");
        private static void printAllOptions() {
        for(int i=0;i<availableOptions.size();++i)
            System.out.println((i+1) + ". " + availableOptions.get(i) );
        }
        public static void main(String[] args) {

            MainService mainService = new MainService();
            Scanner in = new Scanner(System.in);
            boolean end= false;
            while (!end) {
                System.out.println("---- Welcome to IMDB ----");
                System.out.println("The following options are available:");
                Main.printAllOptions();
                System.out.println("Input the number of your option:");
                String command = in.nextLine();
                try{
                    switch (command) {
                        case "1" -> System.out.println("User added");
                        case "2" -> mainService.createFilm(in);
                        case "3" -> mainService.createSeries(in);
                        case "4" -> mainService.deleteFilm(in);
                        case "5" -> mainService.deleteSeries(in);
                        case "6" -> mainService.showFilms();
                        case "7" -> mainService.showSeries();
                        case "8" -> end=true;
                    }
                }catch (Exception e){
                    System.out.println(e.toString());
                }
            }
        }

}