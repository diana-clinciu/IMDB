package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Actor extends Entity {
    private String lastName;
    private String firstName;
    private int age;
    private List<String> awards; // one-to-many =>tabela separata in bd "award" cu foreign key idActor

    public Actor() {
        this.awards = new ArrayList<>();
    }

    public Actor(int id, String lastName, String firstName, int age) {
        super(id);
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.awards = new ArrayList<>();
    }

    @Override
    public void read(Scanner scanner) {
        super.read(scanner);
        System.out.println("Last name: ");
        this.lastName = scanner.nextLine();
        System.out.println("First name: ");
        this.firstName = scanner.nextLine();
        System.out.println("Age: ");
        this.age = Integer.parseInt(scanner.nextLine());

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Actor{" +
                super.toString() +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", awards=" + awards +
                '}';
    }

    public List<String> getAwards() {
        return awards;
    }

    public void setAwards(List<String> awards) {
        this.awards = awards;
    }

    public void print() {
        System.out.println("Actor id: " + this.id);
        System.out.println("Name: " + this.lastName + ' ' + this.firstName);
        System.out.println("Age: " + this.age);
        System.out.println("Awards: ");
        for (String a : this.awards) {
            System.out.println(a);
        }
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }
}
