import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Actor extends Entity{
    private String lastName;
    private String firstName;
    private int age;
    private List<String> awards; // am uitat sa citesc premiile

    public Actor() {
        this.awards = new ArrayList<>();
    }

    public void read(Scanner scanner) {
        super.read(scanner);
        System.out.println("Last name: ");
        this.lastName = scanner.nextLine();
        System.out.println("First name: ");
        this.firstName = scanner.nextLine();
        System.out.println("Age: ");
        this.age = Integer.parseInt(scanner.nextLine());
        System.out.println("Number of awards: ");
        int n = Integer.parseInt(scanner.nextLine());
        System.out.println("Awards: ");
        for(int i=1;i<=n;i++){
            String award = scanner.nextLine();
            this.awards.add(award);
        }
    }

    @Override
    public String toString() {
        return "Actor{" +
                super.toString()+
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", awards=" + awards +
                '}';
    }

    public void print(){
        System.out.println("Actor id: "+this.id);
        System.out.println("Name: "+this.lastName+' '+this.firstName);
        System.out.println("Age: "+this.age);
        System.out.println("Awards: ");
        for(String a:this.awards){
            System.out.println(a);
        }
    }
}
