package models;

import java.util.Scanner;

public class Admin extends Person {
    private int salary;

    @Override
    public void read(Scanner scanner) {
        super.read(scanner);
        System.out.println("Salary: ");
        this.salary = Integer.parseInt(scanner.nextLine());
    }

    public Admin(int id, String lastName, String firstName, String email, String password) {
        super(id, lastName, firstName, email, password);
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                super.toString() +
                "salary=" + salary +
                '}';
    }

    public int getSalary() {
        return salary;
    }

}
