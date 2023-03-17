import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class MainService {
    private List<User> users = new ArrayList<>();
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
    public void createFilm(Scanner in) throws ParseException {

        Film newFilm = new Film();
        newFilm.read(in);
        this.films.add(newFilm);
        System.out.println("Film added to the website");
    }
    public void deleteFilm(Scanner in)throws ParseException{
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
}
