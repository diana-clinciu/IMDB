import java.util.Scanner;

public class Admin extends Person {
    private int salary;

    @Override
    public void read(Scanner scanner) {
        super.read(scanner);
        System.out.println("Salary: ");
        this.salary = Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String toString() {
        return "Admin{" +
                super.toString() +
                "salary=" + salary +
                '}';
    }
}
