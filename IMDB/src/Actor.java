import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Actor {
    private String lastName;
    private String firstName;
    private int age;
    private List<String> awards;

    public Actor(String lastName, String firstName, int age, List<String> awards) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.awards = awards;
    }

    public Actor() {
        this.awards = new ArrayList<>();
    }

    public void read(Scanner scanner) {
        System.out.println("Last name: ");
        this.lastName = scanner.nextLine();
        System.out.println("First name: ");
        this.firstName = scanner.nextLine();
        System.out.println("Age: ");
        this.age = Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String toString() {
        return "Actor{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", awards=" + awards +
                '}';
    }
}
