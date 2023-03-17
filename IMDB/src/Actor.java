import java.util.ArrayList;
import java.util.List;

public class Actor {
        private String lastName;
        private String firstName;
        private int age;
        private List<Show> shows;
        private List<String> awards;

        public Actor(String lastName, String firstName, int age, List<Show> shows,  List<String> awards) {
            this.lastName = lastName;
            this.shows = shows;
            this.firstName = firstName;
            this.age = age;
            this.awards = awards;
        }
}
