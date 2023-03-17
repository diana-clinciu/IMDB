abstract public class Person {
    protected String lastName;
    protected String firstName;
    protected String email;
    protected String password;
    public Person(String lastName, String firstName, String email, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }
}
