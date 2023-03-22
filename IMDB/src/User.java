import java.util.*;

public class User extends Person {
    private Set<Show> watchList;
    private List<Review> reviews;
    public User(int id, String lastName, String firstName, String email, String password, Set<Show> watchList, List<Review> reviews) {
        super(id, lastName, firstName, email, password);
        this.watchList = watchList;
        this.reviews = reviews;
    }

    public void addShow(Show show){
        this.watchList.add(show);
    }

    public User(){
        super();
        this.watchList = new HashSet<>();
        this.reviews = new ArrayList<>();
    }
    @Override
    public void read(Scanner scanner){
        super.read(scanner);
    }

    @Override
    public String toString() {
        return "User{" +
                "watchList=" + watchList +
                ", reviews=" + reviews +
                ", id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
