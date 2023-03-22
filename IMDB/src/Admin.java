import java.util.Scanner;

public class Admin extends Person {
    private int salary;

    public Admin(int id, String lastName, String firstName, String email, String password, int salary) {
        super(id, lastName, firstName, email, password);
        this.salary = salary;
    }

    public Admin(){
    }
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
