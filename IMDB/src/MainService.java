import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class MainService {
    private List<User> users = new ArrayList<>();
    private List<Actor> actors = new ArrayList<>();
    private List<Film> films = new ArrayList<>();
    private List<Series> series = new ArrayList<>();

    public MainService() {}

    public List<User> getUsers() {
        return users;
    }
    public List<Film> getFilms() {
        return films;
    }
    public List<Series> getSeries(){
        return series;
    }
    public void setUsers(List<User> users){
        this.users = users;
    }
    public void setFilms(List<Film> films) {
        this.films = films;
    }
    public void setSeries(List<Series> series){
        this.series = series;
    }

    public void createUser(Scanner in) throws ParseException {

        User user = new User();
        user.read(in);
        this.users.add(user);
        System.out.println("User added");
    }

    public void createFilm(Scanner in) throws ParseException {

        Film newFilm = new Film();
        newFilm.read(in);
        this.films.add(newFilm);
        System.out.println("Film added to the website");
    }
    public void deleteFilm(Scanner in) throws ParseException{
        System.out.println("Enter the name of the movie you want to delete:");
        String nameToDelete=in.nextLine();
        boolean foundFilm = false;
        for (Iterator<Film> iterator = films.iterator(); iterator.hasNext();) {
            Film film = iterator.next();
            if (film.getShowName().equals(nameToDelete)) {
                iterator.remove();
                foundFilm = true;
            }
        }
        if (foundFilm) {
            System.out.println("The film was successfully deleted.");
        } else {
            System.out.println("The film was not found in the list.");
        }
    }
    public void showFilms(){
        System.out.println("--- IMBD MOVIES ---");
        for(Film f : films) {
            System.out.println("Movie: " + f.getShowName());
        }
    }
    public void createSeries(Scanner in) throws ParseException {

        Series newSeries = new Series();
        newSeries.read(in);
        this.series.add(newSeries);
        System.out.println("Series added to the website");
    }
    public void deleteSeries(Scanner in)throws ParseException{
       System.out.println("Enter the name of the series you want to delete:");
        String nameToDelete=in.nextLine();
        boolean foundSeries = false;
        for (Iterator<Series> iterator = series.iterator(); iterator.hasNext();) {
            Series series = iterator.next();
            if (series.getShowName().equals(nameToDelete)) {
                iterator.remove();
                foundSeries = true;
            }
        }
        if (foundSeries) {
            System.out.println("The series was successfully deleted.");
        } else {
            System.out.println("The series was not found in the list.");
        }
    }
    public void showSeries(){
        System.out.println("--- IMBD SERIES ---");
        for(Series s : series) {
            System.out.println("Series: " + s.getShowName());
        }
    }

    private User findUser(int id) throws Exception {
        for(User user : users){
            if(user.id==id)
                return user;
        }
        throw new Exception("User not found!");
    }

    private Show findShow(int id) throws Exception {

        for(Film film : films){
            if(film.id==id)
                return film;
        }

        for(Series series : series){
            if(series.id==id)
                return series;
        }

        throw new Exception("Show not found!");
    }

    public void addShowToWatchList(Scanner scanner){
        try{
            System.out.println("User id: ");
            int userId = Integer.parseInt(scanner.nextLine());
            System.out.println("Show id: ");
            int showId = Integer.parseInt(scanner.nextLine());
            User user = this.findUser(userId);
            Show show = this.findShow(showId);
            user.addShow(show);
            System.out.println("Show added successfully");
            for(User u:users){
                System.out.println(u);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        };
    }
    public void createActor(Scanner in) throws ParseException {
        Actor actor = new Actor();
        actor.read(in);
        this.actors.add(actor);
        System.out.println("Actor added");
        for(Actor a: actors){
            System.out.println(a);
        }
    }
}
