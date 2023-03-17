public class Admin extends Person {
    private int salary;

    public Admin(String lastName, String firstName, String email, String password, int salary) {
        super(lastName, firstName, email, password);
        this.salary = salary;
    }
}
