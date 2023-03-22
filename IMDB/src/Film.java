import java.util.List;
import java.util.Scanner;

public class Film extends Show{
    private int duration;

    public Film() {
    }

    public Film(String name) {
        super(name);
    }

    public Film(String name, int releaseYear, String description, int duration){
        super(name,releaseYear,description);
        this.duration=duration;
    }
    public Film(int id, String name, int releaseYear, String description, List<Category> categories, List<Review> reviews,
                List<Actor> actors, int duration) {
        super(id, name, releaseYear, description, categories, reviews, actors);
        this.duration = duration;
    }
    @Override
    public void read(Scanner in)  {
        super.read(in);
        System.out.println("Duration: ");
        String dur= in.nextLine();
        this.duration=Integer.parseInt(dur);
    }

    @Override
    public String toString() {
        return "Film{" +
                super.toString() +
                "duration=" + duration +
                '}';
    }
}
