import java.util.Scanner;

abstract public class Person {
    protected int id;
    protected String lastName;
    protected String firstName;
    protected String email;
    protected String password;
    public Person(int id, String lastName, String firstName, String email, String password) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

    public Person() {
    }
    public void read(Scanner scanner){
        System.out.println("Id: ");
        this.id = Integer.parseInt(scanner.nextLine());
        System.out.println("Last name: ");
        this.lastName = scanner.nextLine();
        System.out.println("First name: ");
        this.firstName = scanner.nextLine();
        System.out.println("Email: ");
        this.email = scanner.nextLine();
        System.out.println("Password: ");
        this.password = scanner.nextLine();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
