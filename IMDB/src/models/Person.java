package models;

import java.util.Scanner;

abstract public class Person extends Entity {
    protected String lastName;
    protected String firstName;
    protected String email;
    protected String password;

    public Person(int id, String lastName, String firstName, String email, String password) {
        super(id);
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

    public Person() {
    }

    @Override
    public void read(Scanner scanner) {
        super.read(scanner);
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
                super.toString() +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
