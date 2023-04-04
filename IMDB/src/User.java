import java.util.*;

public class User extends Person {
    private Set<Show> watchList;
    private List<Review> reviews;

    public void addShow(Show show) {
        this.watchList.add(show);
    }

    public User() {
        super();
        this.watchList = new HashSet<>();
        this.reviews = new ArrayList<>();
    }

    @Override
    public void read(Scanner scanner) {
        super.read(scanner);
    }

    @Override
    public String toString() {
        return "User{" +
                super.toString()+
                "watchList=" + watchList +
                ", reviews=" + reviews +
                '}';
    }
}

