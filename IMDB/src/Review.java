import java.util.List;

public class Review {
    private String description;
    private List<User> users;

    public Review(String description, List<User> users) {
        this.description = description;
        this.users = users;
    }
}
