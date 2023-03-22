import java.util.List;
import java.util.Scanner;

public class Series extends Show{
    private int episodesNr;
    private int episodeDuration; //in minutes
    public Series(){}

    public Series(String name) {
        super(name);
    }

    public Series(String name, int releaseYear, String description, int episodesNr, int episodeDuration) {
        super(name, releaseYear, description);
        this.episodesNr = episodesNr;
        this.episodeDuration = episodeDuration;
    }

    public Series(int id, String name, int releaseYear, String description, List<Category> categories, List<Review> reviews,
                  List<Actor> actors, int episodesNr, int episodeDuration) {
        super(id, name, releaseYear, description, categories, reviews, actors);
        this.episodesNr = episodesNr;
        this.episodeDuration = episodeDuration;
    }
    @Override
    public void read(Scanner in)  {
        super.read(in);
        System.out.println("Number of episodes: ");
        String nr= in.nextLine();
        this.episodeDuration=Integer.parseInt(nr);
        System.out.println("Duration: ");
        String dur= in.nextLine();
        this.episodeDuration=Integer.parseInt(dur);
    }

    @Override
    public String toString() {
        return "Series{" +
                super.toString() +
                "episodesNr=" + episodesNr +
                ", episodeDuration=" + episodeDuration +
                '}';
    }
}
