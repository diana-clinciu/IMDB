import java.util.List;
import java.util.Set;

public class User extends Person {
    private Set<Show> watchList;
    private List<Review> reviews;
    public User(String lastName, String firstName, String email, String password, Set<Show> watchList, List<Review> reviews) {
        super(lastName, firstName, email, password);
        this.watchList = watchList;
        this.reviews = reviews;
    }
}
